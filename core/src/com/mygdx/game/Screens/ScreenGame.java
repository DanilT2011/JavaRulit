package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Charters.Monster;
import com.mygdx.game.Components.Background;
import com.mygdx.game.Components.MoneyCounter;
import com.mygdx.game.Components.TextButton;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Charters.Tower;

import java.time.Clock;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;
import static java.lang.Math.abs;

public class ScreenGame implements Screen {

    MyGdxGame myGdxGame;
    Tower tower;
    MoneyCounter MC;
    MoneyCounter text;
    TextButton buttonPause;
    float intervalForSpawn = 5.0f;
    boolean yN = false;
    Clock clock = Clock.systemDefaultZone();
    long lastTimeSpawn = clock.millis();
    long lastTimeCountDown = lastTimeSpawn;
    boolean gameOver = false;
    boolean isUpdateScreen = false;
    Background background = new Background("background/BackgroundOfGame.png");
    Monster[] monsters;

    public ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        tower = new Tower(300, 350, 10, SCR_HEIGHT / 2 - 100);
        initMonsters();
        MC = new MoneyCounter(SCR_WIDTH - 500, SCR_HEIGHT - 50);
        text = new MoneyCounter(10, SCR_HEIGHT - 50);
        buttonPause = new TextButton(SCR_WIDTH - 200, SCR_HEIGHT - 100, 200, 100,
                "buttons/pause.png");
    }


    @Override
    public void show() {
        if (isUpdateScreen){
            gameOver = false;
            monsters[0].setMonet(0);
            lastTimeSpawn = clock.millis();
            lastTimeCountDown = lastTimeSpawn;
            isUpdateScreen = false;
        }
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {

            Vector3 touch = myGdxGame.camera.unproject(
                    new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)
            );

            if (buttonPause.isHit((int) touch.x, (int) touch.y)) {
                myGdxGame.setScreen(myGdxGame.screenPause);
            }
        }
        if (gameOver){
            isUpdateScreen = true;
            myGdxGame.setScreen(myGdxGame.screenRestart);
        }
        if (tower.isTowerBreak()){
            for (Monster monster : monsters) {
                monster.setX();
                gameOver = true;
                tower.setLiveTower();
            }
        }
        if (!yN){
            yN = isCountDown(lastTimeCountDown);
        }
        if (yN){
            if (Gdx.input.justTouched()) {

                Vector3 touch = myGdxGame.camera.unproject(
                        new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)
                );


                for (int i = 0; i < 5; i++) {
                    if (monsters[i].isPress((int) touch.x, (int) touch.y)) {
                        monsters[i].hit();
                        lastTimeCountDown = clock.millis();
                        yN = false;
                    }
                }
            }
        }

        if (isNeedTime(intervalForSpawn, lastTimeSpawn)){
            lastTimeSpawn = clock.millis();
            for (Monster monster : monsters){
                if (monster.getMonsterDeath()){
                    monster.revivalMonster();
                    break;
                }
            }
        }


        for (int i = 0; i < 5; i++) {
            if (monsters[i].isAttack(tower)) {
                System.out.println("Mines one live of tower");
                tower.hit();
                monsters[i].pushMonster(tower);
            } else {
                for (Monster monster : monsters) {
                    monster.move();
                }
            }
        }

        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        background.draw(myGdxGame.batch);

        tower.draw(myGdxGame.batch);
        for (Monster monster : monsters) {
            monster.draw(myGdxGame.batch);
        }
        MC.draw(myGdxGame.batch);
        text.text(myGdxGame.batch, "LiveOfTower:" + tower.getLiveTower());
        buttonPause.draw(myGdxGame.batch);

        myGdxGame.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        tower.dispose();
        for (int i = 0; i < 10; i++) {
            monsters[i].dispose();
        }
        MC.dispose();
        background.dispose();
        text.dispose();
        buttonPause.dispose();
    }

    public boolean isNeedTime(float interval, long lastTime) {
        return abs(((clock.millis() / 1000) - (lastTime / 1000))) >= interval;
    }

    public boolean isCountDown(long lastTimeCountDown){
        if (isNeedTime(monsters[0].getCD(), lastTimeCountDown)){
            System.out.println("CountDown");
            return true;
        }
        return false;
    }

    void initMonsters() {
        monsters = new Monster[5];
        for (int i = 0; i < 5; i++) {
            monsters[i] = new Monster(170, 200, SCR_WIDTH + 100, SCR_HEIGHT / 2, true);
        }
    }
}