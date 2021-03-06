package com.juego;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

import entities.BanderaEntity;
import entities.EnemigoEntity;
import entities.PlayerEntity;
import entities.SueloEntity;

class GameScreen extends BaseScreen {
    private Stage stage;
    private World world;
    private SpriteBatch batch;
    private SueloEntity sueloEntity;
    private PlayerEntity playerEntity;
    private ArrayList<EnemigoEntity> enemigos;
    public SpriteBatch spriteBatch;
    private Texture fondo;
    private Sprite sprite;
    public Music musica, salto;
    private Label puntuacionLabel;
    private BitmapFont font;
    private int puntuacion;
    private BanderaEntity bandera;

    public GameScreen(final MainGame game) {
        super(game);
        batch = new SpriteBatch();
        musica = Gdx.audio.newMusic(Gdx.files.getFileHandle("musica/friends.wav", Files.FileType.Internal));

        font = new BitmapFont();
        font.setColor(Color.NAVY);
        font.getData().setScale(3, 3);
        puntuacion = -1;


        enemigos = new ArrayList<>();

        stage = new Stage(new FitViewport(640, 320));
        world = new World(new Vector2(0, -10), true);

        world.setContactListener(new ContactListener() {

            private boolean areCollided(Contact contact, Object dataA, Object userB) {
                Object DataA = contact.getFixtureA().getUserData();
                Object DataB = contact.getFixtureB().getUserData();
                if (DataA == null || DataB == null) {
                    return false;
                }
                return (DataA.equals(dataA) && DataB.equals(userB)) ||
                        (DataA.equals(userB) && DataB.equals(dataA));
            }

            @Override
            public void beginContact(Contact contact) {
                if (areCollided(contact, "player", "suelo")) {

                    if (playerEntity.isVivo()) {
                        puntuacion++;
                    }
                    playerEntity.setSaltando(false);
                    if (Gdx.input.isTouched()) {
                        playerEntity.setSaltando(true);
                    }
                }

                if (areCollided(contact, "player", "enemigo")) {

                    musica.stop();
                    stage.addAction(Actions.sequence(
                            Actions.delay(0.5f),
                            Actions.run(new Runnable() {
                                @Override
                                public void run() {

                                    game.setPantalla(game.GameOverScreen);
                                }
                            })));
                }

                if (areCollided(contact, "player", "bandera")) {
                    musica.stop();
                    stage.addAction(Actions.sequence(
                            Actions.delay(0.5f),
                            Actions.run(new Runnable() {
                                @Override
                                public void run() {

                                    game.setPantalla(game.winnerScreen);
                                }
                            })));
                }
            }

            @Override
            public void endContact(Contact contact) {


            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });


        musica.setVolume(0.5f);
        musica.play();


    }

    @Override
    public void show() {

        CrearPersonajes crearPersonajes = new CrearPersonajes(game.getManager());
        super.show();
        //stage.setDebugAll(true);


        fondo = new Texture("fondo/img5.png");
        sprite = new Sprite(fondo);
        spriteBatch = new SpriteBatch(20);


        Texture texturaSuelo = game.getManager().get("suelo.png");
        Texture texturaPlayer = game.getManager().get("player.png");
        Texture texturaBandera = game.getManager().get("final.png");

        sueloEntity = new SueloEntity(texturaSuelo, world, new Vector2(0, 0), 320, 10);
        playerEntity = new PlayerEntity(texturaPlayer, world, new Vector2(0f, 1.4f));

        enemigos.add(crearPersonajes.nuevoEnemigo(world, 8, 1));
        enemigos.add(crearPersonajes.nuevoEnemigo(world, 23, 1));
        enemigos.add(crearPersonajes.nuevoEnemigo(world, 35, 1));
        enemigos.add(crearPersonajes.nuevoEnemigo(world, 50, 1));
        enemigos.add(crearPersonajes.nuevoEnemigo(world, 82, 1));
        enemigos.add(crearPersonajes.nuevoEnemigo(world, 104, 1));
        enemigos.add(crearPersonajes.nuevoEnemigo(world, 150, 1));
        enemigos.add(crearPersonajes.nuevoEnemigo(world, 170, 1));

        bandera = new BanderaEntity(texturaBandera, world, new Vector2(180f, 1.5f));

        for (EnemigoEntity enemigo : enemigos) {
            stage.addActor(enemigo);
        }


        stage.addActor(sueloEntity);
        stage.addActor(playerEntity);
        stage.addActor(bandera);


    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        spriteBatch.begin();

        spriteBatch.draw(sprite, 0, 12, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() + 60f);
        font.draw(spriteBatch, "Puntuación: " + puntuacion, 30, 400f);


        spriteBatch.end();


        stage.act();

        world.step(delta, 6, 2);

        if (playerEntity.getX() > 150) {
            stage.getCamera().position.x = playerEntity.getX() + 170;

        } else {
            stage.getCamera().position.x = 320;
        }


        stage.getCamera().update();
        stage.draw();
    }

    @Override
    public void hide() {
        super.hide();
        sueloEntity.detach();
        sueloEntity.remove();
        playerEntity.detach();
        playerEntity.remove();
        for (EnemigoEntity enemigo : enemigos) {
            enemigo.detach();
            enemigo.remove();
        }

        bandera.detach();
        bandera.remove();
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
        world.dispose();
    }

    public Music getMusica(){
        return this.musica;
    }
}
