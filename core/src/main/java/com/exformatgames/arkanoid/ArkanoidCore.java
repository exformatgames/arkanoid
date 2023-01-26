package com.exformatgames.arkanoid;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.exformatgames.arkanoid.entities.BackgroundEntityBuilder;
import com.exformatgames.arkanoid.entities.BallEntityBuilder;
import com.exformatgames.arkanoid.entities.PaddleEntityBuilder;
import com.exformatgames.arkanoid.entities.WallEntityBuilder;
import com.exformatgames.arkanoid.systems.*;
import com.github.exformatgames.defender.Core;

public class ArkanoidCore extends Core {

    public ArkanoidCore(Vector2 viewportSize, Vector2 gravity, InputMultiplexer inputMultiplexer, TextureAtlas atlas, AssetManager assetManager) {
        super(viewportSize, gravity, inputMultiplexer, atlas, assetManager);
    }

    @Override
    protected void initEntities() {
        new BackgroundEntityBuilder().create();
        new BallEntityBuilder().create(new Vector2(5.1f, 5));

        new WallEntityBuilder().create(new Vector2(0, 9.6f));
        new WallEntityBuilder().create(new Vector2(10.2f, 9.6f));
        new WallEntityBuilder().create(new Vector2(5.1f, 19.2f), new Vector2(10.2f, 0.1f));
        new WallEntityBuilder().create(new Vector2(5.1f, 19.2f), new Vector2(10.2f, 0.1f));
        new PaddleEntityBuilder().create();
    }

    @Override
    protected void initGameSystems() {
        addSystem(new BallCollisionSystem(assetManager));
        addSystem(new BallControlSystem());
        addSystem(new PlatformControlSystem());
        addSystem(new DamageSystem());
        addSystem(new BoxSystem());
    }
}
