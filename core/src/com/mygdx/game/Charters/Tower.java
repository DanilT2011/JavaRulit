package com.mygdx.game.Charters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Tower {

    int width;
    int height;
    int x;
    int y;
    Texture texture;
    static int liveTower = 3;

    public Tower(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;

        texture = new Texture("Tower/tower.png");
    }

    public void draw(Batch batch){
        batch.draw(texture, x, y, width, height);
    }

    public void hit(){
        liveTower--;
    }

    public boolean isTowerBreak(){
        if (liveTower == 0){
            return true;
        }
        return false;
    }

    public void setLiveTower(){
        liveTower = 3;
    }

    public void addShield(){
        liveTower++;
    }

    public int getLiveTower(){
        return liveTower;
    }

    public float getX(){
        return x;
    }

    public float getWidth(){
        return width;
    }

    public void dispose(){
        texture.dispose();
    }
}
