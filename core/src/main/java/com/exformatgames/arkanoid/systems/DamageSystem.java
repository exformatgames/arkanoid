package com.exformatgames.arkanoid.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.arkanoid.components.DamageComponent;
import com.exformatgames.arkanoid.components.HPComponent;

public class DamageSystem extends IteratingSystem {

    public DamageSystem() {
        super(Family.all(DamageComponent.class, HPComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HPComponent healthComponent = HPComponent.getComponent(entity);
        DamageComponent damageComponent = DamageComponent.getComponent(entity);

        healthComponent.hp -= damageComponent.damage;

        entity.remove(DamageComponent.class);
    }
}
