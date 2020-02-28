package entities;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BanderaEntity extends Actor {

    private Texture textura;
    private World world;
    public Body body;
    private Fixture fixture;
    private float velocidad;



    public BanderaEntity(Texture textura, World world, Vector2 posicion) {

        this.textura = textura;
        this.world = world;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(posicion.x, posicion.y);
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1f, 1f);

        fixture = body.createFixture(shape, 1);

        fixture.setUserData("bandera");
        shape.dispose();

        setSize(32f, 80f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        setPosition((body.getPosition().x) * 32f, 10);
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
