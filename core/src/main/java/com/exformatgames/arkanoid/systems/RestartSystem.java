package com.exformatgames.arkanoid.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.exformatgames.arkanoid.ArkanoidGame;
import com.exformatgames.arkanoid.GameState;
import com.exformatgames.arkanoid.components.RestartComponent;
import com.exformatgames.arkanoid.entities.*;
import com.exformatgames.arkanoid.entities.ui.LivesEntityBuilder;
import com.exformatgames.arkanoid.entities.ui.ScoreEntityBuilder;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.box2d.WorldComponent;
import com.github.exformatgames.defender.components.util_components.RemoveEntityComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class RestartSystem extends IteratingSystem {

    public RestartSystem() {
        super(Family.one(RestartComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ArkanoidGame.GAME_STATE = GameState.READY;
        for (Entity en: getEngine().getEntities()) {
            if (WorldComponent.getComponent(en) != null) { //fixme need update lib
                continue;
            }
            EntityBuilder.createComponent(en, RemoveEntityComponent.class);
        }


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
}
