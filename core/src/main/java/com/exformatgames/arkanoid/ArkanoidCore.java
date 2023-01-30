package com.exformatgames.arkanoid;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.exformatgames.arkanoid.entities.*;
import com.exformatgames.arkanoid.entities.ui.LivesEntityBuilder;
import com.exformatgames.arkanoid.entities.ui.ScoreEntityBuilder;
import com.exformatgames.arkanoid.systems.*;
import com.exformatgames.arkanoid.systems.bonus_systems.BonusRemoveSystem;
import com.exformatgames.arkanoid.systems.bonus_systems.BonusSpawnSystem;
import com.exformatgames.arkanoid.systems.bonus_systems.ScoreMultipleSystem;
import com.exformatgames.arkanoid.systems.defender.TimeActionSystem;
import com.exformatgames.arkanoid.systems.ui.LivesSystem;
import com.exformatgames.arkanoid.systems.ui.UIScoreSystem;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.Core;

public class ArkanoidCore extends Core {


    public ArkanoidCore(Vector2 viewportSize, Vector2 uiViewportSize, Vector2 gravity, InputMultiplexer inputMultiplexer, TextureAtlas atlas, AssetManager assetManager) {
        super(viewportSize, uiViewportSize, gravity, inputMultiplexer, atlas, assetManager);
    }

    @Override
    protected void initEntities() {
        new BackgroundEntityBuilder().create();
        new BallEntityBuilder().create(new Vector2(Configurations.WORLD_WIDTH / 2, 1.3f));

        new WallEntityBuilder().create(new Vector2(0, Configurations.WORLD_HEIGHT / 2));
        new WallEntityBuilder().create(new Vector2(Configurations.WORLD_WIDTH, Configurations.WORLD_HEIGHT / 2));
        new WallEntityBuilder().create(new Vector2(Configurations.WORLD_WIDTH / 2, Configurations.WORLD_HEIGHT), new Vector2(Configurations.WORLD_WIDTH, 0.1f));
        new WallEntityBuilder().create(new Vector2(Configurations.WORLD_WIDTH / 2, 0f), new Vector2(Configurations.WORLD_WIDTH, 0.1f));

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

        new ScoreEntityBuilder().create();
        new LivesEntityBuilder().create();
    }

    @Override
    protected void initGameSystems() {
        addSystem(new TimeActionSystem());
        addSystem(new ScoreMultipleSystem());
        addSystem(new ScoreSystem());
        addSystem(new BallCollisionSystem(assetManager));
        addSystem(new LivesSystem());
        addSystem(new UIScoreSystem());
        addSystem(new BallControlSystem());
        addSystem(new PaddleControlSystem());
        addSystem(new PaddleCollisionSystem());
        addSystem(new DamageSystem(textureAtlas));
        addSystem(new BonusSpawnSystem());
        addSystem(new BonusRemoveSystem());
        addSystem(new RestartSystem());
    }
}
