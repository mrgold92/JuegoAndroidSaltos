package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class EnemigoEntity extends Actor {

    private Texture textura;
    private World world;
    private Body body;
    private Fixture fixture;

    public EnemigoEntity(World world, Texture textura, float x, float y){
        this.world = world;
        this.textura = textura;
        BodyDef def = new BodyDef();                // (1) Give it some definition.
        def.position.set(x-0.4f, y-0.1f);              // (2) Position the body on the world.
        body = world.createBody(def);               // (3) Create the body.

        // Now give it a shape.
        PolygonShape box = new PolygonShape();      // (1) We will make a polygon.
        box.setAsBox(0.4f, 0.01f);
        fixture = body.createFixture(box, 1);       // (5) Create the fixture.
        fixture.setUserData("enemigo");               // (6) And set the user data to enemy.
        box.dispose();

        setPosition((x - 0.5f) * 30f, y * 9f);
        setSize(90f, 55f);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textura, getX(), getY(), getWidth(), getHeight());
    }

    public void detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
}
