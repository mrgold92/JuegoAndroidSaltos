package com.juego;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import javax.swing.JLabel;

public class GameOverScreen extends BaseScreen{
    private Stage stage;
    private Skin skin;
    private TextButton volver;
    private Label texto;
    private Sound gameover;


    public GameOverScreen(final MainGame game){
        super(game);
        stage= new Stage(new FitViewport(640,320));
        skin= new Skin(Gdx.files.internal("skin/uiskin.json"));
        volver= new TextButton("Reintentar",skin);
        texto= new Label("Game Over", skin);
        texto.setFontScale(1.5f);

        volver.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameScreen gameScreen = new GameScreen(game);
                game.setPantalla(gameScreen);
                gameover.stop();

            }
        });
        volver.setSize(150,60);
        volver.setPosition(100,50);
        stage.addActor(volver);
        texto.setPosition(270, 200);
        texto.setScale(300,300);
        stage.addActor(texto);

    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(stage);
        gameover = game.getManager().get("musica/gameover.ogg");
        gameover.play();

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClearColor(1, 1,0f, 0f); // color al fondo
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);//Limpiar el buffer
        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {
        super.hide();
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
