package com.fatheroctober.drunkard.engine.core.views.panels;

import com.fatheroctober.drunkard.engine.core.controller.AbstractControllerFactory;
import com.fatheroctober.drunkard.engine.core.controller.toolbarcontrollerfactory.ToolBarControllerFactory;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.InjectBundle;
import com.fatheroctober.drunkard.engine.core.tools.utils.IfceLoger;
import com.fatheroctober.drunkard.engine.core.tools.utils.Log;
import com.fatheroctober.drunkard.engine.core.views.render.ToolBarRender;
import com.fatheroctober.drunkard.engine.core.common.PropertiesReader;
import com.google.inject.Inject;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.logging.Level;

public class ToolBarPanel extends AbstractToolBarPanel {

    @Inject
    private IfceLoger logger;

    private PropertiesReader propertiesReader;
    private AbstractControllerFactory toolBarControllerFactory;

    private JList list = new JList();
    private JLabel labelLog = new JLabel("Game events: ");
    private List<Log> logs = new ArrayList<>();
    private int LOG_BOX_W = 0;
    private int LOG_BOX_H = 0;

    public ToolBarPanel() {
        super();
        render = InjectBundle.getInstance(ToolBarRender.class);
        propertiesReader = InjectBundle.getInstance(PropertiesReader.class);
        LOG_BOX_W = (int) (0.8 * propertiesReader.toolBarPanelWidth());
        LOG_BOX_H = (int) (0.8 * propertiesReader.toolBarPanelHeight());
        try {
            render.init(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        listConfigure();
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(LOG_BOX_W, LOG_BOX_H));
        add(labelLog);
        add(listScroller);
        //controller factorization
        toolBarControllerFactory = new ToolBarControllerFactory();
        keyController = toolBarControllerFactory.getKeyListener();
        mouseController = toolBarControllerFactory.getMouseListener();
        addKeyListener(keyController);
        addMouseListener(mouseController);
    }

    private void listConfigure() {
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBackground(Color.CYAN);
                if (value instanceof Log) {
                    Log var = (Log) value;
                    setText(var.getLogText());
                    if (var.getLevel() == Level.SEVERE) {
                        setForeground(Color.GRAY);
                    } else {
                        if (var.getLevel() == Level.WARNING)
                            setForeground(Color.RED);
                        else setForeground(Color.BLACK);
                    }
                    if (isSelected) {
                        setForeground(getBackground().darker());
                    }
                } else {
                    setText("Unknown object (Expected Log)");
                }
                return c;
            }
        });
    }

    public void update() {
        showLogs();
    }

    private void showLogs() {
        Log log = logger.getLog();
        if (log != null) {
            logs.add(log);
        }
        if (logs.size() > 20) logs.remove(0);
        ListIterator<Log> li = logs.listIterator(logs.size());
        DefaultListModel listModel = new DefaultListModel();
        while (li.hasPrevious()) {
            listModel.addElement(li.previous());
        }
        list.setModel(listModel);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        render.draw(g2d);
        paintComponents(g2d);
    }
}
