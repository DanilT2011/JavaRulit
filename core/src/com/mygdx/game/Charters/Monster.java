package com.mygdx.game.Charters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Components.Memory;

import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

public class Monster {

    int width;
    int height;
    float x;
    int y;
    int liveMonster;
    boolean monsterDeath;
    static int money = Memory.loadMoney();
    float speed = 0.4f;
    static float CD = Memory.loadCD();
    Texture texture;
    int frameCounter;
    Texture[] framesArray;

    public Monster(int width, int height, int x, int y, boolean monsterDeath) {
        liveMonster = 3;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.monsterDeath = monsterDeath;

        frameCounter = 0;

        framesArray = new Texture[]{
                new Texture("Monsters/monster1.png"),
                new Texture("Monsters/monster2.png")
        };
    }

    public void draw(Batch batch) {
        int frameMultiplier = 10;
        batch.draw(framesArray[frameCounter / frameMultiplier], x, y, width, height);
        if (frameCounter++ == framesArray.length * frameMultiplier - 1) {
            frameCounter = 0;
        }
    }

    public void move(){
        if (!monsterDeath) {
            x -= speed;
        }
    }

    public boolean isAttack(Tower tower){
        if (x <= tower.getX() + tower.getWidth() - 100){
            return true;
        }
        return false;
    }

    public boolean isPress(int tx, int ty){
        return tx >= x && tx <= x + width && ty >= y && ty <= y + height;
    }

    public void setX(){
        x = SCR_WIDTH + 100;
    }

    public void pushMonster(Tower tower){
        x = tower.getX() + tower.getWidth() + 450;
    }

    public void hit(){
        liveMonster--;
        if (liveMonster == 0){
            money++;
            System.out.println("MONSTER FAIL! YOU WIN!");
            liveMonster = 3;
            setX();
            monsterDeath = true;
            Memory.saveMoney(money);
        }
    }

    public void revivalMonster(){
        System.out.println("Revival");
        monsterDeath = false;
    }

    public void setMonet(int moneyInp){
        money = moneyInp;
    }

    public float getCD(){
        return CD;
    }

    public void setLowCD(){
        CD = 0.01f;
    }

    public int getMoney(){
        return money;
    }

    public boolean getMonsterDeath(){
        return monsterDeath;
    }

    public void dispose() {
        texture.dispose();
    }
}
