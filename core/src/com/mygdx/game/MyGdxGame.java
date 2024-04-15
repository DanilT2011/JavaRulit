package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.ScreenGame;
import com.mygdx.game.Screens.ScreenMenu;
import com.mygdx.game.Screens.ScreenShop;
import com.mygdx.game.Screens.ScreenRestart;
import com.mygdx.game.Screens.ScreenPause;

public class MyGdxGame extends Game {

	public SpriteBatch batch;
	public OrthographicCamera camera;
	public ScreenGame screenGame;
	public ScreenRestart screenRestart;
	public ScreenMenu screenMenu;
	public ScreenShop screenShop;
	public ScreenPause screenPause;
	public static final int SCR_WIDTH = 1280;
	public static final int SCR_HEIGHT = 720;

	@Override
	public void create() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);

		screenGame = new ScreenGame(this);
		screenRestart = new ScreenRestart(this);
		screenMenu = new ScreenMenu(this);
		screenShop = new ScreenShop(this);
		screenPause = new ScreenPause(this);

		setScreen(screenMenu);
	}


	@Override
	public void dispose() {
		batch.dispose();
	}
}