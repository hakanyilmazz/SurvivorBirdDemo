package com.hakanyilmazz.survivorbirddemo.abstract_class;

import com.badlogic.gdx.graphics.Texture;

public abstract class AbstractMoveableTexture extends AbstractGameTexture {
    private final float tempY;
    private final float tempVelocity;

    protected boolean isAlive;
    protected float velocity;

    public AbstractMoveableTexture(Texture texture, float x, float y, float width, float height, float velocity) {
        super(texture, x, y, width, height);
        isAlive = true;
        this.velocity = velocity;
        tempY = y;
        tempVelocity = velocity;
    }

    public abstract void move();

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean alive) {
        isAlive = alive;
    }

    public void reset() {
        y = tempY;
        velocity = tempVelocity;
        isAlive = true;
    }
}
