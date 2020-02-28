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

public class WinnerScreen extends BaseScreen{
    private Stage stage;
    private Skin skin;
    private TextButton volver;
    private Label texto;
    private Sound winnerSound;


    public WinnerScreen(final MainGame game){
        super(game);
        stage= new Stage(new FitViewport(640,320));
        skin= new Skin(Gdx.files.internal("skin/uiskin.json"));
        volver= new TextButton("Volver al menu",skin);
        texto= new Label("¡¡¡¡Has ganado!!!!", skin);





        volver.setSize(400, 60);

        volver.setPosition(320-200, 320 / 2 - volver.getHeight() / 2);
        texto.setSize(100, 20);
        texto.setPosition(320-50,10);
       // texto.setScale(1,1);

        stage.addActor(texto);
        stage.addActor(volver);

        volver.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MenuScreen menu = new MenuScreen(game);
                game.setPantalla(menu);
                winnerSound.stop();

            }
        });

    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(stage);
        winnerSound = game.getManager().get("musica/winnersound.ogg");
        winnerSound.play();

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClearColor(204/255f, 84/255f, 47/255f, 1); // color al fondo
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
