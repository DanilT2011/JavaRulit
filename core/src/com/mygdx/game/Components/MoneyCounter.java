package com.mygdx.game.Components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.Charters.Monster;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

public class MoneyCounter {
    int x;
    int y;
    BitmapFont font;
    Monster monster = new Monster(170, 200, SCR_WIDTH + 100, SCR_HEIGHT / 2, true);

    public MoneyCounter(int x, int y){
        this.x = x;
        this.y = y;

        font = new BitmapFont();
        font.getData().setScale(5f);
        font.setColor(Color.WHITE);
    }

    public void draw(Batch batch) {
        font.draw(batch, "Money: " + monster.getMoney(), x, y);
    }

    public void text(Batch batch, String text){
        font.draw(batch, text, x, y);
    }

    public void dispose() {
        font.dispose();
    }
}
