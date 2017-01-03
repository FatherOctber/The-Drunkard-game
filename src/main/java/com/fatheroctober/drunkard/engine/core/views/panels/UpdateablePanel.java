package com.fatheroctober.drunkard.engine.core.views.panels;

import com.fatheroctober.drunkard.engine.core.common.Observer;
import com.fatheroctober.drunkard.engine.core.common.Observerable;
import com.fatheroctober.drunkard.engine.core.common.Updateable;

import javax.swing.*;

/**
 * Panel with updateable, Observerable and Observer functions
 */
public abstract class UpdateablePanel extends JPanel implements Updateable, Observerable, Observer {

    public UpdateablePanel() {
        super();
    }
}
