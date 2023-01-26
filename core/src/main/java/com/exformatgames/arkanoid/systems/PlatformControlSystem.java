package com.exformatgames.arkanoid.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.arkanoid.components.PaddleComponent;
import com.github.exformatgames.defender.components.box2d.TransformBodyComponent;
import com.github.exformatgames.defender.components.input_components.gesture_components.GesturePanComponent;
import com.github.exformatgames.defender.components.transform_components.PositionComponent;
import com.github.exformatgames.defender.components.transform_components.SizeComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class PlatformControlSystem extends IteratingSystem {

    public PlatformControlSystem() {
        super(Family.all(GesturePanComponent.class, PaddleComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        GesturePanComponent gesturePanComponent = GesturePanComponent.getComponent(entity);
        PositionComponent positionComponent = PositionComponent.getComponent(entity);
        SizeComponent sizeComponent = SizeComponent.getComponent(entity);

        if (! gesturePanComponent.position.isZero())
            EntityBuilder.createComponent(entity, TransformBodyComponent.class).position.set(gesturePanComponent.position.x, positionComponent.y + sizeComponent.halfHeight);
    }
}
