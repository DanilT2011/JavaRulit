package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Components.Background;
import com.mygdx.game.Components.TextButton;
import com.mygdx.game.MyGdxGame;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

public class ScreenMenu implements Screen {
    MyGdxGame myGdxGame;
    Background background = new Background("background/Menu.png");
    TextButton buttonGame;
    TextButton buttonExit;
    TextButton buttonShop;


    public ScreenMenu(MyGdxGame myGdxGame){
        this.myGdxGame = myGdxGame;

        buttonGame = new TextButton(SCR_WIDTH / 2 - 310, -10, 500, 300,
                "buttons/start.png");
        buttonExit = new TextButton(SCR_WIDTH / 2 - 580, SCR_HEIGHT / 2 - 350, 350, 250,
                "buttons/exit.png");
        buttonShop = new TextButton(SCR_WIDTH / 2 + 200, SCR_HEIGHT / 2 - 350, 350, 250,
                "buttons/shop.png");
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

            if (buttonGame.isHit((int) touch.x, (int) touch.y)) {
                myGdxGame.setScreen(myGdxGame.screenGame);
            }

            if (buttonShop.isHit((int) touch.x, (int) touch.y)) {
                myGdxGame.setScreen(myGdxGame.screenShop);
            }

            if (buttonExit.isHit((int) touch.x, (int) touch.y)) {
                Gdx.app.exit();
            }
        }

        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();

        background.draw(myGdxGame.batch);
        buttonGame.draw(myGdxGame.batch);
        buttonExit.draw(myGdxGame.batch);
        buttonShop.draw(myGdxGame.batch);

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
        background.dispose();
        buttonGame.dispose();
        buttonExit.dispose();
        buttonShop.dispose();
    }
}
