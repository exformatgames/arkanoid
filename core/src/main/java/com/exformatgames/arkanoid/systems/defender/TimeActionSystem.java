package com.exformatgames.arkanoid.systems.defender;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.arkanoid.components.defender.TimeActionComponent;

public class TimeActionSystem extends IteratingSystem {

    public TimeActionSystem() {
        super(Family.all(TimeActionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TimeActionComponent actionComponent = TimeActionComponent.getComponent(entity);

        if ((actionComponent.timerAction += deltaTime) > actionComponent.targetTime) {
            entity.remove(TimeActionComponent.class);
        }
    }
}
