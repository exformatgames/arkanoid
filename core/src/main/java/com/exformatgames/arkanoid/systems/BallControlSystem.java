package com.exformatgames.arkanoid.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.arkanoid.ArkanoidGame;
import com.exformatgames.arkanoid.GameState;
import com.exformatgames.arkanoid.components.BallComponent;
import com.github.exformatgames.defender.components.box2d.TransformBodyComponent;
import com.github.exformatgames.defender.components.box2d.transform_components.LinearVelocityBodyComponent;
import com.github.exformatgames.defender.components.input_components.gesture_components.GesturePanComponent;
import com.github.exformatgames.defender.components.input_components.gesture_components.GestureTapComponent;
import com.github.exformatgames.defender.components.transform_components.PositionComponent;
import com.github.exformatgames.defender.components.transform_components.SizeComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class BallControlSystem extends IteratingSystem {

    public BallControlSystem() {
        super(Family.all(BallComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (ArkanoidGame.GAME_STATE == GameState.READY) {
            GesturePanComponent panComponent = GesturePanComponent.getComponent(entity);
            GestureTapComponent tapComponent = GestureTapComponent.getComponent(entity);
            PositionComponent positionComponent = PositionComponent.getComponent(entity);
            SizeComponent sizeComponent = SizeComponent.getComponent(entity);

            if (! panComponent.position.isZero())
                EntityBuilder.createComponent(entity, TransformBodyComponent.class).position.set(panComponent.position.x, positionComponent.y + sizeComponent.halfHeight);

            if (! tapComponent.position.isZero()){
                EntityBuilder.createComponent(entity, LinearVelocityBodyComponent.class).init(0, 10);
                ArkanoidGame.GAME_STATE = GameState.IN_GAME;
            }
        }
    }
}
