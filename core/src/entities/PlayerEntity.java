package entities;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.juego.BodyEditorLoader;

public class PlayerEntity extends Actor {

    private Texture textura;
    private World world;
    public Body body;
    private Fixture fixture;
    private Vector2 posicion;
    private float y, x;
    public boolean jumping = true;
    private Vector2 pos;
    private boolean vivo = true;
    private boolean saltando = false;
    private boolean debeSaltar = false;
    private Music salto;
    private float velocidad;


    public PlayerEntity(Texture textura, World world, Vector2 posicion) {

        this.textura = textura;
        this.world = world;
        salto = Gdx.audio.newMusic(Gdx.files.getFileHandle("musica/salto.wav", Files.FileType.Internal));
        velocidad = 5;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(posicion.x, posicion.y);
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.4f, 0.4f);

        fixture = body.createFixture(shape, 1);

        fixture.setUserData("player");
        shape.dispose();

        setSize(32f, 32f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        setPosition((body.getPosition().x) * 32, (body.getPosition().y - 1) * 32);
        batch.draw(textura, getX(), getY(), getWidth(), getHeight());

    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);

    }

    @Override
    public void act(float delta) {

        super.act(delta);

        if (Gdx.input.justTouched()) {

            saltar();


        }
        if (debeSaltar) {
            debeSaltar = false;
            saltar();
        }
        if (vivo) {
            float velY = body.getLinearVelocity().y;
            body.setLinearVelocity(velocidad, velY);

        }else{
            velocidad=0;
        }

        if (saltando) {
            body.applyForceToCenter(0, -2f * 1.5f, true);
        }
    }

    private void saltar() {

        if (!saltando && vivo) {
            velocidad+=1.5f;
            salto.play();
            saltando = true;
            Vector2 position = body.getPosition();
            body.applyLinearImpulse(0, 6.3f, position.x, position.y, true);
        }
    }

    public boolean isSaltando() {
        return saltando;
    }

    public void setSaltando(boolean saltando) {
        this.saltando = saltando;
    }

    public boolean isDebeSaltar() {
        return debeSaltar;
    }

    public void setDebeSaltar(boolean debeSaltar) {
        this.debeSaltar = debeSaltar;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }


    public boolean isJumping() {

        return jumping;
    }

    public void setJumping(boolean jumping) {

        this.jumping = jumping;
    }
}
