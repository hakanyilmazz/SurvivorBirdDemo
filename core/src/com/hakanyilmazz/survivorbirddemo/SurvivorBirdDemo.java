package com.hakanyilmazz.survivorbirddemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hakanyilmazz.survivorbirddemo.abstract_class.AbstractGameTexture;
import com.hakanyilmazz.survivorbirddemo.helper.TextureGenerator;
import com.hakanyilmazz.survivorbirddemo.model.Background;
import com.hakanyilmazz.survivorbirddemo.model.Bird;

public class SurvivorBirdDemo extends ApplicationAdapter {
    private SpriteBatch batch;
    private Background background;
    private Bird bird;
    private boolean isGameStarted = false;

    @Override
    public void create() {
        batch = new SpriteBatch();

        background = TextureGenerator.createBackground();
        bird = TextureGenerator.createBird();
    }

    @Override
    public void render() {
        batch.begin();
        drawTexture(background);

        if (isGameStarted && bird.getIsAlive()) {
            drawTexture(bird);

            if (Gdx.input.justTouched()) {
                bird.fly();
            } else {
                bird.move();
            }
        } else {
            if (Gdx.input.justTouched()) {
                isGameStarted = true;
                bird.reset();
            } else {
                // display message
            }
        }

        batch.end();
    }

    @Override
    public void dispose() {
        bird.getTexture().dispose();
        background.getTexture().dispose();
        batch.dispose();
    }

    private void drawTexture(AbstractGameTexture gameTexture) {
        batch.draw(
                gameTexture.getTexture(),
                gameTexture.getX(),
                gameTexture.getY(),
                gameTexture.getWidth(),
                gameTexture.getHeight()
        );
    }
}
