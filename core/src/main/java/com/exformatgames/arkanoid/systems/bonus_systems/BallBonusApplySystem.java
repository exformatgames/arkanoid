package com.exformatgames.arkanoid.systems.bonus_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.exformatgames.arkanoid.ArkanoidGame;
import com.exformatgames.arkanoid.GameState;
import com.exformatgames.arkanoid.components.bonuses.BallBonusComponent;
import com.exformatgames.arkanoid.entities.BallEntityBuilder;
import com.github.exformatgames.defender.components.box2d.contact_components.BeginContactComponent;
import com.github.exformatgames.defender.components.transform_components.PositionComponent;
import com.github.exformatgames.defender.components.transform_components.SizeComponent;
import com.github.exformatgames.defender.components.util_components.RemoveEntityComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class BallBonusApplySystem extends IteratingSystem {

    public BallBonusApplySystem() {
        super(Family.all(BallBonusComponent.class, BeginContactComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (ArkanoidGame.GAME_STATE == GameState.IN_GAME) {
            PositionComponent positionComponent = PositionComponent.getComponent(entity);
            SizeComponent sizeComponent = SizeComponent.getComponent(entity);

            BallEntityBuilder.create(positionComponent.x + sizeComponent.halfWidth, positionComponent.y + 0.5f, MathUtils.random(-1, 1));
        }
        EntityBuilder.createComponent(entity, RemoveEntityComponent.class);
    }
}
