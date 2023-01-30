package com.exformatgames.arkanoid.systems.bonus_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.arkanoid.components.bonuses.BonusComponent;
import com.github.exformatgames.defender.components.box2d.contact_components.BeginContactComponent;
import com.github.exformatgames.defender.components.rendering_components.CullingComponent;
import com.github.exformatgames.defender.components.util_components.RemoveEntityComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class BonusRemoveSystem extends IteratingSystem {

    public BonusRemoveSystem() {
        super(Family.all(BonusComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CullingComponent cullingComponent = CullingComponent.getComponent(entity);
        BeginContactComponent beginContactComponent = BeginContactComponent.getComponent(entity);

        if ( ! cullingComponent.inViewport || beginContactComponent != null) {
            EntityBuilder.createComponent(entity, RemoveEntityComponent.class);
        }
    }
}
