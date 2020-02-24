package com.juego;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

import entities.EnemigoEntity;

public class CrearPersonajes {

    private AssetManager manager;

    public CrearPersonajes(AssetManager manager) {

        this.manager = manager;
    }

    public EnemigoEntity nuevoEnemigo (World world, float x, float y) {

        Texture textura = manager.get("enemigo.png");
        return new EnemigoEntity(world, textura, x, y);

    }
}
