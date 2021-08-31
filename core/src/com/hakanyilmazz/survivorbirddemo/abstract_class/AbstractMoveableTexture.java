package com.hakanyilmazz.survivorbirddemo.abstract_class;

import com.badlogic.gdx.graphics.Texture;

public abstract class AbstractMoveableTexture extends AbstractGameTexture {
    private float tempY, tempVelocity;

    protected boolean isAlive;
    protected float velocity, gravity, minY, maxY;

    public AbstractMoveableTexture(Texture texture, float x, float y, float width, float height, float velocity, float gravity, float minY, float maxY) {
        super(texture, x, y, width, height);
        isAlive = true;
        this.velocity = velocity;
        this.gravity = gravity;
        this.minY = minY;
        this.maxY = maxY;
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

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public float getMinY() {
        return minY;
    }

    public void setMinY(float minY) {
        this.minY = minY;
    }

    public float getMaxY() {
        return maxY;
    }

    public void setMaxY(float maxY) {
        this.maxY = maxY;
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
