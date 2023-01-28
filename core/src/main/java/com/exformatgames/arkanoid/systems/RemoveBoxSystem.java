package com.exformatgames.arkanoid.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.arkanoid.components.BoxComponent;
import com.exformatgames.arkanoid.components.HPComponent;
import com.github.exformatgames.defender.components.util_components.RemoveEntityComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class RemoveBoxSystem extends IteratingSystem {

    public RemoveBoxSystem() {
        super(Family.all(BoxComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HPComponent hpComponent = HPComponent.getComponent(entity);

        if (hpComponent.hp <= 0) {
            EntityBuilder.createComponent(entity, RemoveEntityComponent.class);
        }
    }
}
