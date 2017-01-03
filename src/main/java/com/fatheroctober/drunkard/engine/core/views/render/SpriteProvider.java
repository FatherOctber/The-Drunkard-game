package com.fatheroctober.drunkard.engine.core.views.render;

import com.fatheroctober.drunkard.engine.core.models.Conditions;
import com.fatheroctober.drunkard.engine.core.models.MovingOrientation;
import com.fatheroctober.drunkard.engine.core.common.PropertiesReader;
import com.fatheroctober.drunkard.engine.core.models.abstraction.*;
import com.google.inject.Inject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class SpriteProvider {

    @Inject
    PropertiesReader propertiesReader;

    private BufferedImage bigImg;

    private enum SpriteModels {
        AREA,
        SELECTED_AREA,
        POLICE_STATION,
        TAVERN,
        DRINKER,
        POLICEMAN,
        PILLAR,
        BOTTLE,
        LAMP,
        SLEEP_DRINKER;
    }

    private int internalCounter = 0;
    private Map<SpriteModels, ImageCollector> spriteMap = new HashMap<SpriteModels, ImageCollector>();
    private Map<MovingOrientation, ImageCursor> drinkerSpriteCursor = new HashMap<>();
    private Map<MovingOrientation, ImageCursor> policemanSpriteCursor = new HashMap<>();

    private int sheetWidth;
    private int sheetHeight;
    private int frameW;
    private int frameH;
    private int sheetRow;
    private int sheetCollumn;

    public void init() throws Exception {
        //ClassLoader loader = Thread.currentThread().getContextClassLoader();
        //File f =new File(URLDecoder.decode(loader.getResource(PropertiesReader.getInstance().spritesLocation()).getPath(), "UTF-8"));
        bigImg = ImageIO.read(this.getClass().getResource(propertiesReader.spritesLocation()));
        sheetWidth = bigImg.getWidth();
        sheetHeight = bigImg.getHeight();
        frameW = propertiesReader.gamePanelWidth();
        frameH = propertiesReader.gamePanelHeight();
        sheetRow = 10;
        sheetCollumn = 4;
        initSpriteMap();
        initCursors();


    }

    private void initCursors() {
        drinkerSpriteCursor.put(MovingOrientation.NONE, new ImageCursor(0, 0));
        drinkerSpriteCursor.put(MovingOrientation.NORTH, new ImageCursor(9, 11));
        drinkerSpriteCursor.put(MovingOrientation.WEST, new ImageCursor(3, 5));
        drinkerSpriteCursor.put(MovingOrientation.EAST, new ImageCursor(6, 8));
        drinkerSpriteCursor.put(MovingOrientation.SOUTH, new ImageCursor(0, 2));

        policemanSpriteCursor.put(MovingOrientation.NONE, new ImageCursor(0, 0));
        policemanSpriteCursor.put(MovingOrientation.NORTH, new ImageCursor(9, 11));
        policemanSpriteCursor.put(MovingOrientation.WEST, new ImageCursor(3, 5));
        policemanSpriteCursor.put(MovingOrientation.EAST, new ImageCursor(6, 8));
        policemanSpriteCursor.put(MovingOrientation.SOUTH, new ImageCursor(0, 2));

    }

    private void initSpriteMap() {
        spriteMap.put(SpriteModels.AREA, new ImageCollector().addImages(bigImg.getSubimage(0, 0, sheetWidth / sheetCollumn, sheetHeight / sheetRow)));
        spriteMap.put(SpriteModels.SELECTED_AREA, new ImageCollector().addImages(bigImg.getSubimage(sheetWidth / sheetCollumn, 0, sheetWidth / sheetCollumn, sheetHeight / sheetRow)));
        spriteMap.put(SpriteModels.TAVERN, new ImageCollector().addImages(bigImg.getSubimage(2 * (sheetWidth / sheetCollumn), 0, sheetWidth / sheetCollumn, sheetHeight / sheetRow)));
        spriteMap.put(SpriteModels.POLICE_STATION, new ImageCollector().addImages(bigImg.getSubimage(3 * (sheetWidth / sheetCollumn), 0, sheetWidth / sheetCollumn, sheetHeight / sheetRow)));
        spriteMap.put(SpriteModels.PILLAR, new ImageCollector().addImages(bigImg.getSubimage(2 * (sheetWidth / sheetCollumn), sheetHeight / sheetRow, sheetWidth / sheetCollumn, sheetHeight / sheetRow)));
        spriteMap.put(SpriteModels.LAMP, new ImageCollector().addImages(bigImg.getSubimage(3 * (sheetWidth / sheetCollumn), sheetHeight / sheetRow, sheetWidth / sheetCollumn, sheetHeight / sheetRow)));
        spriteMap.put(SpriteModels.BOTTLE, new ImageCollector().addImages(bigImg.getSubimage(3 * (sheetWidth / sheetCollumn), 2 * (sheetHeight / sheetRow), sheetWidth / sheetCollumn, sheetHeight / sheetRow)));
        spriteMap.put(SpriteModels.SLEEP_DRINKER, new ImageCollector().addImages(bigImg.getSubimage(0, sheetHeight / sheetRow, sheetWidth / sheetCollumn, sheetHeight / sheetRow)));

        ImageCollector drinkerSprites = new ImageCollector();
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                drinkerSprites.addImages(bigImg.getSubimage(j * (sheetHeight / sheetRow), i * (sheetHeight / sheetRow), sheetWidth / sheetCollumn, sheetHeight / sheetRow));
            }
        }
        spriteMap.put(SpriteModels.DRINKER, drinkerSprites);
        ImageCollector policemanSprites = new ImageCollector();
        for (int i = 6; i < 10; i++) {
            for (int j = 0; j < 3; j++) {
                policemanSprites.addImages(bigImg.getSubimage(j * (sheetHeight / sheetRow), i * (sheetHeight / sheetRow), sheetWidth / sheetCollumn, sheetHeight / sheetRow));
            }
        }
        spriteMap.put(SpriteModels.POLICEMAN, policemanSprites);
    }


    public Image spriteOf(Object modelClass) throws Exception {
        if (modelClass instanceof IArea) {
            IArea area = (IArea) modelClass;
            if (area.isSelected()) return spriteMap.get(SpriteModels.SELECTED_AREA).getImages(0);
            else return spriteMap.get(SpriteModels.AREA).getImages(0);
        }
        if (modelClass instanceof ITavern) {
            return spriteMap.get(SpriteModels.TAVERN).getImages(0);
        }
        if (modelClass instanceof IPoliceStation) {
            return spriteMap.get(SpriteModels.POLICE_STATION).getImages(0);
        }
        if (modelClass instanceof IPillar) {
            return spriteMap.get(SpriteModels.PILLAR).getImages(0);
        }
        if (modelClass instanceof IDrinker) {
            if (((IDrinker) modelClass).getCondition() == Conditions.DrinkerCondition.SLEEP)
                return spriteMap.get(SpriteModels.SLEEP_DRINKER).getImages(0);
            else {
                return drinkerSpriteCursor
                        .get(((IDrinker) modelClass).getOrientation())
                        .nextImage(spriteMap.get(SpriteModels.DRINKER));
            }
        }
        if (modelClass instanceof IPoliceman) {
            return policemanSpriteCursor
                    .get(((IPoliceman) modelClass).getOrientation())
                    .nextImage(spriteMap.get(SpriteModels.POLICEMAN));
        }
        if (modelClass instanceof ILamp) {
            return spriteMap.get(SpriteModels.LAMP).getImages(0);
        }
        if (modelClass instanceof IBottle) {
            return spriteMap.get(SpriteModels.BOTTLE).getImages(0);
        }
        return null;
    }


}
