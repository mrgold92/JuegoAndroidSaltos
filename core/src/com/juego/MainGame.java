package com.juego;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class MainGame extends Game {
    private AssetManager manager;
    boolean ok = false;
    public BaseScreen menuScreen, gamescreen, finScreen;

    @Override
    public void create() {
        manager = new AssetManager();

        //load
        manager.load("lo.png", Texture.class);
        manager.load("suelo.png", Texture.class);
        manager.load("player.png", Texture.class);
        manager.load("enemigo.png", Texture.class);
        manager.load("musica/friends.wav", Music.class);



        menuScreen = new MenuScreen(this);
        setPantalla(menuScreen);


    }

    public void setPantalla(BaseScreen screen) {
        setScreen(screen);
    }


    public AssetManager getManager() {
        return this.manager;
    }

    @Override
    public void render() {
        super.render();

    }
}
