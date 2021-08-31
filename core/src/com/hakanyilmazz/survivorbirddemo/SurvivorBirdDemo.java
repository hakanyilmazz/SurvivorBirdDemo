package com.hakanyilmazz.survivorbirddemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.hakanyilmazz.survivorbirddemo.abstract_class.AbstractGameTexture;
import com.hakanyilmazz.survivorbirddemo.helper.TextureGenerator;
import com.hakanyilmazz.survivorbirddemo.model.Background;
import com.hakanyilmazz.survivorbirddemo.model.Bee;
import com.hakanyilmazz.survivorbirddemo.model.Bird;

public class SurvivorBirdDemo extends ApplicationAdapter {
    private final int numberOfBee = 3;
    private final int allNumberOfBee = 4;
    private SpriteBatch batch;
    private Background background;
    private Bird bird;
    private Bee[][] beeArray;
    private boolean isGameStarted = false;
    private Circle birdCircle;
    private Circle[][] beeArrayCircle;
    private BitmapFont scoreFont;
    private BitmapFont gameOverFont;
    private int score = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();

        background = TextureGenerator.createBackground();
        bird = TextureGenerator.createBird();

        beeArray = TextureGenerator.createBeeArray(numberOfBee, allNumberOfBee);

        scoreFont = new BitmapFont();
        gameOverFont = new BitmapFont();
    }

    @Override
    public void render() {
        batch.begin();

        createCircles();
        drawTexture(background);

        if (isGameStarted && bird.getIsAlive()) {
            drawTexture(bird);
            drawBeeArrayTexture();
            displayScore();

            if (Gdx.input.justTouched()) {
                bird.fly();
            } else {
                bird.move();
                moveBeeArray();
            }
        } else {
            if (Gdx.input.justTouched()) {
                resetGame();
            } else {
                displayGameOverMessages();
            }
        }

        batch.end();
    }

    private void resetGame() {
        isGameStarted = true;
        bird.reset();
        beeArray = TextureGenerator.createBeeArray(numberOfBee, allNumberOfBee);
        score = 0;
    }

    @Override
    public void dispose() {
        for (Bee[] bee : beeArray) {
            for (Bee tempBee : bee) {
                tempBee.getTexture().dispose();
            }
        }

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

    private void drawBeeArrayTexture() {
        float finishLine = 0f;

        for (Bee[] bee : beeArray) {
            for (Bee tempBee : bee) {
                if (tempBee.getX() >= finishLine) {
                    drawTexture(tempBee);
                } else {
                    float newX = Gdx.graphics.getWidth() + Gdx.graphics.getWidth() / 2f + tempBee.getWidth();

                    tempBee.setX(newX);
                    tempBee.setY((float) (Gdx.graphics.getHeight() * Math.random() - Gdx.graphics.getHeight() / 20f));
                }
            }
        }
    }

    private void moveBeeArray() {
        for (int i = 0; i < beeArray.length; i++) {
            for (int j = 0; j < beeArray[i].length; j++) {
                if (bird.getIsAlive()) {
                    beeArray[i][j].move();
                }
            }

            boolean shouldIncreaseScore = false;

            for (int j = 0; j < beeArray[i].length; j++) {
                if (Intersector.overlaps(birdCircle, beeArrayCircle[i][j])) {
                    bird.setIsAlive(false);
                } else if (beeArray[i][j].getIsEnable() && beeArray[i][j].getX() < bird.getX()) {
                    shouldIncreaseScore = true;
                    beeArray[i][j].setIsEnable(false);
                } else if (!beeArray[i][j].getIsEnable() && beeArray[i][j].getX() >= bird.getX()) {
                    beeArray[i][j].setIsEnable(true);
                }
            }

            if (shouldIncreaseScore) {
                score++;
            }
        }
    }

    private void createCircles() {
        float birdRadius = bird.getWidth() / 2.75f;
        birdCircle = new Circle(bird.getX() + birdRadius, bird.getY() + birdRadius, birdRadius);

        beeArrayCircle = new Circle[numberOfBee][allNumberOfBee];

        for (int i = 0; i < beeArrayCircle.length; i++) {
            for (int j = 0; j < beeArrayCircle[i].length; j++) {
                float beeRadius = beeArray[i][j].getWidth() / 2.75f;
                beeArrayCircle[i][j] = new Circle(beeArray[i][j].getX() + beeRadius, beeArray[i][j].getY() + beeRadius, beeRadius);
            }
        }
    }

    private void displayGameOverMessages() {
        displayScore();

        gameOverFont.draw(batch, "Game Over! Try Again..", Gdx.graphics.getWidth() / 5f, Gdx.graphics.getHeight() / 1.90f);
        gameOverFont.setColor(Color.WHITE);
        gameOverFont.getData().setScale(8);
    }

    private void displayScore() {
        scoreFont.draw(batch, "Score: " + score, 100, 100);
        scoreFont.setColor(Color.WHITE);
        scoreFont.getData().setScale(4);
    }
}
