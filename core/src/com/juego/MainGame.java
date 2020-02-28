package com.juego;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import java.util.BitSet;

public class MainGame extends Game {
    private AssetManager manager;
    boolean ok = false;
    public BaseScreen menuScreen, GameOverScreen, winnerScreen, GameScreen;
    public GameScreen gameScreen;

    @Override
    public void create() {
        manager = new AssetManager();

        //load
        manager.load("lo.png", Texture.class);
        manager.load("suelo.png", Texture.class);
        manager.load("player.png", Texture.class);
        manager.load("enemigo.png", Texture.class);
        manager.load("musica/friends.wav", Music.class);
        manager.load("musica/gameover.ogg", Sound.class);
        manager.load("musica/winnersound.ogg", Sound.class);
        manager.load("final.png", Texture.class);



        menuScreen = new MenuScreen(this);
        GameOverScreen = new GameOverScreen(this);
        winnerScreen = new WinnerScreen(this);
        gameScreen = new GameScreen(this);
        gameScreen.getMusica().stop();





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
