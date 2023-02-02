package com.exformatgames.arkanoid.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.exformatgames.arkanoid.ArkanoidGame;
import com.exformatgames.arkanoid.GameState;
import com.exformatgames.arkanoid.components.BlockComponent;
import com.exformatgames.arkanoid.components.RestartComponent;
import com.exformatgames.arkanoid.entities.*;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.util_components.RemoveEntityComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class RestartSystem extends IteratingSystem {

    public RestartSystem() {
        super(Family.one(RestartComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ArkanoidGame.GAME_STATE = GameState.READY;
        ArkanoidGame.LIVES = 3;
        ArkanoidGame.SCORE = 0;

        for (Entity blockEntity: getEngine().getEntitiesFor(Family.one(BlockComponent.class).get())) {
            EntityBuilder.createComponent(blockEntity, RemoveEntityComponent.class);
        }

        new BallEntityBuilder().create(new Vector2(Configurations.WORLD_WIDTH / 2, 1.3f));


        for (int x = 0; x < 17; x++) {
            for (int y = 0; y < 20; y++) {
                if (y > 7) {
                    BlockEntityBuilder.create(new Vector2(x * 0.61f + 0.54f, 12 + 0.28f * y), new Vector2(0.61f, 0.28f));
                } else {
                    BlockEntityBuilder.create(new Vector2(x * 0.61f + 0.54f, 12 + 0.28f * y), new Vector2(0.61f, 0.28f), y + 1);
                }
            }
        }

        EntityBuilder.createComponent(entity, RemoveEntityComponent.class);
    }
}
