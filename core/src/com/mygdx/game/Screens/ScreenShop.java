package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Charters.Monster;
import com.mygdx.game.Charters.Tower;
import com.mygdx.game.Components.Background;
import com.mygdx.game.Components.Memory;
import com.mygdx.game.Components.MoneyCounter;
import com.mygdx.game.Components.TextButton;
import com.mygdx.game.MyGdxGame;


import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

public class ScreenShop implements Screen {
    MyGdxGame myGdxGame;
    TextButton buttonMenu;
    TextButton buttonBuyCD;
    TextButton buttonBuyShield;
    MoneyCounter MC;
    MoneyCounter text1;
    MoneyCounter text2;
    Tower tower;
    Monster monster;
    Background background = new Background("background/Menu.png");
    boolean delBCD = false;

    public ScreenShop(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        MC = new MoneyCounter(SCR_WIDTH - 500, SCR_HEIGHT - 50);
        buttonMenu = new TextButton(0, 0, 400, 200,  "buttons/menuButton.png");
        buttonBuyCD = new TextButton(100, 150, 500, 300, "buttons/buyCD.png");
        buttonBuyShield = new TextButton(700, 150, 500, 300, "buttons/buyS.png");
        tower = new Tower(300, 350, 10, SCR_HEIGHT / 2 - 100);
        monster = new Monster(170, 200, SCR_WIDTH + 100, SCR_HEIGHT / 2, true);
        text1 = new MoneyCounter(100, 300);
        text2 = new MoneyCounter(700, 300);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {

            Vector3 touch = myGdxGame.camera.unproject(
                    new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)
            );

            if (buttonMenu.isHit((int) touch.x, (int) touch.y)) {
                myGdxGame.setScreen(myGdxGame.screenMenu);
            }
            if (buttonBuyCD.isHit((int) touch.x, (int) touch.y)) {
                if (monster.getMoney() >= 30) {
                    monster.setMonet(monster.getMoney() - 30);
                    delBCD = true;
                    monster.setLowCD();
                    Memory.saveCD(0.01f);
                }
            }
            if (buttonBuyShield.isHit((int) touch.x, (int) touch.y)) {
                if (monster.getMoney() >= 20) {
                    monster.setMonet(monster.getMoney() - 20);
                    tower.addShield();
                }
            }
        }

        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();

        background.draw(myGdxGame.batch);
        MC.draw(myGdxGame.batch);
        buttonMenu.draw(myGdxGame.batch);
        if (!delBCD) {
            buttonBuyCD.draw(myGdxGame.batch);
            text1.text(myGdxGame.batch, "30 money");
        }
        else {
            text1.text(myGdxGame.batch, "SOLD OUT");
        }
        buttonBuyShield.draw(myGdxGame.batch);
        text2.text(myGdxGame.batch, "20 money");


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
        MC.dispose();
        background.dispose();
        buttonMenu.dispose();
        buttonBuyCD.dispose();
        buttonBuyShield.dispose();
        text1.dispose();
        text2.dispose();
    }
}
