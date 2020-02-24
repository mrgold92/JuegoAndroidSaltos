package entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

import java.rmi.dgc.DGC;

public class SueloEntity extends Actor {

    private Texture textura;
    private World world;
    public Body body;
    private Fixture fixture;
    private Vector2 posicion;
    private TextureRegion texturaRegion;

    public SueloEntity(Texture textura, World world, Vector2 posicion, float width, float height) {
        this.textura = textura;
        this.world = world;
        this.posicion = posicion;



        //fisicas
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(posicion.x, posicion.y);
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(640, 0.9f);
        fixture = body.createFixture(shape, 1);
        fixture.setUserData("suelo");
        shape.dispose();;

        setSize(width*32f, 50);
        setPosition(0, -38);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(textura, getX(), getY(), getWidth(), getHeight());
    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
