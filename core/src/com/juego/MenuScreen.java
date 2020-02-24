package com.juego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class MenuScreen extends BaseScreen {

    private Stage stage;
    private Skin skin;
    private Image logo;
    private TextButton.TextButtonStyle p;
    private TextButton play;
    private Label nombres;

    public MenuScreen(final MainGame game) {
        super(game);
        stage = new Stage(new FitViewport(640, 320));

        skin = new Skin();
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        BitmapFont fuente = new BitmapFont(Gdx.files.internal("fuente.fnt"));
        skin.add("default", fuente);


        p = new TextButton.TextButtonStyle();
        p.up = skin.newDrawable("white", new Color(1, 1, 1, 1));
        p.font = skin.getFont("default");

        p.fontColor = new Color(0, 0, 0, 1);
        skin.add("default", p);

        //elementos
        play = new TextButton("Jugar", skin);
        Texture t = new Texture(Gdx.files.internal("lo.png"));

        Label.LabelStyle label1Style = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont(Gdx.files.internal("fuente.fnt"));
        label1Style.font = myFont;
        label1Style.fontColor = Color.CORAL;
        nombres = new Label("",label1Style);
        nombres.setPosition(4f, 10f);
        nombres.setFontScaleY(0.4f);
        nombres.setFontScaleX(0.4f);



        logo = new Image(t);



        play.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (game.getManager().update(1000)) {
                    GameScreen gameScreen = new GameScreen(game);
                    game.setPantalla(gameScreen);
                }
            }
        });


        logo.setPosition(440 - logo.getWidth() / 2, 320 / 2 - logo.getHeight() / 2);
        play.setSize(200, 60);

        play.setPosition(40, 320 / 2 - play.getHeight() / 2);

        stage.addActor(play);
        stage.addActor(logo);
        stage.addActor(nombres);

    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {

        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        // Dispose assets.
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(80/255f, 91/255f, 117/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();

    }
}
