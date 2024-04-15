package com.mygdx.game.Components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Memory {

    private static final Preferences preferences = Gdx.app.getPreferences("User saves");


    public static int loadMoney() {
        return preferences.getInteger("money", 0);
    }

    public static void saveMoney(int money) {
        preferences.putInteger("money", money);
        preferences.flush();
    }

    public static float loadCD() {
        return preferences.getFloat("CD", 0.1f);
    }

    public static void saveCD(float CD) {
        preferences.putFloat("CD", CD);
        preferences.flush();
    }
}