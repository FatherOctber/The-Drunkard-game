package com.fatheroctober.drunkard.engine.core.views.render;

import java.awt.*;
import java.util.ArrayList;

public class ImageCollector {

    private ArrayList<Image> images = new ArrayList<>();

    public ImageCollector addImages(Image... imgs) {
        for (Image image : imgs) {
            images.add(image);
        }
        return this;
    }

    public Image getImages(int index) {
        return images.get(index);
    }

    public int size() {
        return images.size();
    }

}
