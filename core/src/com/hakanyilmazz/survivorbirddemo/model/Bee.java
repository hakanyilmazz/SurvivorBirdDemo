package com.hakanyilmazz.survivorbirddemo.model;

import com.badlogic.gdx.graphics.Texture;
import com.hakanyilmazz.survivorbirddemo.abstract_class.AbstractMoveableTexture;

public class Bee extends AbstractMoveableTexture {
    private boolean isEnable;

    public Bee(Texture texture, float x, float y, float width, float height, float velocity) {
        super(texture, x, y, width, height, velocity);
        isEnable = true;
    }

    @Override
    public void move() {
        x -= 10f;
    }

    public boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(boolean isEnable) {
        this.isEnable = isEnable;
    }
}
