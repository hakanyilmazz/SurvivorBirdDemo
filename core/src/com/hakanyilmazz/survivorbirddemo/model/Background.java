package com.hakanyilmazz.survivorbirddemo.model;

import com.badlogic.gdx.graphics.Texture;
import com.hakanyilmazz.survivorbirddemo.abstract_class.AbstractGameTexture;

public class Background extends AbstractGameTexture {
    public Background(Texture texture, float x, float y, float width, float height) {
        super(texture, x, y, width, height);
    }
}
