package com.exformatgames.arkanoid;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.exformatgames.arkanoid.entities.*;
import com.exformatgames.arkanoid.systems.*;
import com.github.exformatgames.defender.Constants;
import com.github.exformatgames.defender.Core;

public class ArkanoidCore extends Core {

    public ArkanoidCore(OrthographicCamera gameCamera, OrthographicCamera uiCamera, World box2DWorld, SpriteBatch spriteBatch, InputMultiplexer inputMultiplexer, TextureAtlas atlas, AssetManager assetManager) {
        super(gameCamera, uiCamera, box2DWorld, spriteBatch, inputMultiplexer, atlas, assetManager);
    }

    @Override
    protected void initEntities() {
        new BackgroundEntityBuilder().create();
        new BallEntityBuilder().create(new Vector2(Constants.WORLD_WIDTH / 2, 1.3f));

        new WallEntityBuilder().create(new Vector2(0, Constants.WORLD_HEIGHT / 2));
        new WallEntityBuilder().create(new Vector2(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT / 2));
        new WallEntityBuilder().create(new Vector2(Constants.WORLD_WIDTH / 2, Constants.WORLD_HEIGHT), new Vector2(Constants.WORLD_WIDTH, 0.1f));
        new WallEntityBuilder().create(new Vector2(Constants.WORLD_WIDTH / 2, 0f), new Vector2(Constants.WORLD_WIDTH, 0.1f));

        new PaddleEntityBuilder().create();
        new DeadzoneEntityBuilder().create();

        for (int x = 0; x < 17; x++) {
            for (int y = 0; y < 20; y++) {
                if (y > 7) {
                    BlockEntityBuilder.create(new Vector2(x * 0.61f + 0.54f, 12 + 0.28f * y), new Vector2(0.61f, 0.28f));
                } else {
                    BlockEntityBuilder.create(new Vector2(x * 0.61f + 0.54f, 12 + 0.28f * y), new Vector2(0.61f, 0.28f), y + 1);
                }
            }
        }
    }

    @Override
    protected void initGameSystems() {
        addSystem(new BallCollisionSystem(assetManager));
        addSystem(new BallControlSystem());
        addSystem(new PlatformControlSystem());
        addSystem(new DamageSystem(atlas));
        addSystem(new RemoveBoxSystem());
    }
}
