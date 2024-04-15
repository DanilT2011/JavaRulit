package com.mygdx.game.Components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class TextButton {

    Texture texture;

    private int x, y;
    private int buttonWidth;
    private int buttonHeight;

    public TextButton(int x, int y, int width, int height, String path) {
        this.x = x;
        this.y = y;
        this.buttonWidth = width;
        this.buttonHeight = height;

        texture = new Texture(path);
    }
    public void draw(Batch batch) {
        batch.draw(texture, x, y, buttonWidth, buttonHeight);
    }

    public void dispose() {
        texture.dispose();
    }
    public boolean isHit(int tx, int ty) {
        return tx >= x && tx <= x + buttonWidth && ty >= y && ty <= y + buttonHeight;
    }
}