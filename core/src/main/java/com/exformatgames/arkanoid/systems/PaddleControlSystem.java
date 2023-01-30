package com.exformatgames.arkanoid.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.arkanoid.components.PaddleComponent;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.box2d.transform_components.TransformBodyComponent;
import com.github.exformatgames.defender.components.input_components.gesture_components.GesturePanComponent;
import com.github.exformatgames.defender.components.transform_components.PositionComponent;
import com.github.exformatgames.defender.components.transform_components.SizeComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class PaddleControlSystem extends IteratingSystem {

    public PaddleControlSystem() {
        super(Family.all(GesturePanComponent.class, PaddleComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        GesturePanComponent gesturePanComponent = GesturePanComponent.getComponent(entity);
        PositionComponent positionComponent = PositionComponent.getComponent(entity);
        SizeComponent sizeComponent = SizeComponent.getComponent(entity);

        if (! gesturePanComponent.position.isZero() && (gesturePanComponent.position.x - sizeComponent.halfWidth > 0 && gesturePanComponent.position.x + sizeComponent.halfWidth < Configurations.WORLD_WIDTH))
            EntityBuilder.createComponent(entity, TransformBodyComponent.class).position.set(gesturePanComponent.position.x, positionComponent.y + sizeComponent.halfHeight);
    }
}
