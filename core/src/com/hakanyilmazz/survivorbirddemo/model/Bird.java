package com.hakanyilmazz.survivorbirddemo.model;

import com.badlogic.gdx.graphics.Texture;
import com.hakanyilmazz.survivorbirddemo.abstract_class.AbstractMoveableTexture;

public class Bird extends AbstractMoveableTexture {
    public Bird(Texture texture, float x, float y, float width, float height, float velocity, float gravity, float minY, float maxY) {
        super(texture, x, y, width, height, velocity, gravity, minY, maxY);
    }

    public void fly() {
        velocity = -10;
    }

    @Override
    public void move() {
        float newY = y - velocity;

        if (newY >= minY && newY <= maxY) {
            y = newY;
            velocity += gravity;
        } else {
            isAlive = false;
        }
    }
}
