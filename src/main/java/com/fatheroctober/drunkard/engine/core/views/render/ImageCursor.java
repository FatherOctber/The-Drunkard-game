package com.fatheroctober.drunkard.engine.core.views.render;

import com.fatheroctober.drunkard.engine.core.common.GlobalTimer;

import java.awt.*;

public class ImageCursor {
    private int left;
    private int right;
    private int currentPosition;
    private int distance = 0;

    public ImageCursor(int leftBorder, int rightBorder) {
        left = leftBorder;
        right = rightBorder;
        currentPosition = left;
    }

    public Image nextImage(ImageCollector collector) throws Exception {
        Image img;
        if (currentPosition >= left && currentPosition <= right) {
            img = collector.getImages(currentPosition);
            if (distance < GlobalTimer.getInstance().getSpriteDistanceByFrameCounter()) distance++;
            else {
                currentPosition++;
                distance = 0;
            }
        } else {
            currentPosition = left;
            img = collector.getImages(currentPosition);
        }
        return img;

    }
}