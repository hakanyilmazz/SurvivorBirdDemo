package com.hakanyilmazz.survivorbirddemo.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.hakanyilmazz.survivorbirddemo.model.Background;
import com.hakanyilmazz.survivorbirddemo.model.Bee;
import com.hakanyilmazz.survivorbirddemo.model.Bird;

public class TextureGenerator {
    public static Background createBackground() {
        return new Background(
                new Texture("background.png"),
                0f,
                0f,
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight()
        );
    }

    public static Bird createBird() {
        return new Bird(
                new Texture("bird.png"),
                Gdx.graphics.getWidth() / 4f,
                Gdx.graphics.getHeight() / 2.5f,
                Gdx.graphics.getWidth() / 15f,
                Gdx.graphics.getHeight() / 10f,
                5f,
                0.225f,
                0f,
                Gdx.graphics.getHeight() - Gdx.graphics.getWidth() / 15f + Gdx.graphics.getWidth() / 15f / 4f
        );
    }

    public static Bee[][] createBeeArray(int numberOfBee, int allNumberOfBee) {
        Bee[][] beeArray = new Bee[numberOfBee][allNumberOfBee];
        float distance = Gdx.graphics.getWidth() / 2f;
        for (int i = 0; i < beeArray.length; i++) {
            for (int j = 0; j < beeArray[i].length; j++) {
                beeArray[i][j] = TextureGenerator.createBee(Gdx.graphics.getWidth() / 2f + i * distance, (float) (Gdx.graphics.getHeight() * Math.random() - Gdx.graphics.getHeight() / 20f));
            }
        }

        return beeArray;
    }

    private static Bee createBee(float x, float y) {
        return new Bee(
                new Texture("bee.png"),
                x,
                y,
                Gdx.graphics.getWidth() / 15f,
                Gdx.graphics.getHeight() / 10f,
                3f
        );
    }
}
