package com.exformatgames.arkanoid.systems.bonus_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.arkanoid.components.BlockComponent;
import com.exformatgames.arkanoid.components.DamageComponent;
import com.exformatgames.arkanoid.components.bonuses.BombBonusComponent;
import com.github.exformatgames.defender.components.box2d.contact_components.BeginContactComponent;
import com.github.exformatgames.defender.components.util_components.RemoveEntityComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class BombBonusApplySystem extends IteratingSystem {

    public BombBonusApplySystem() {
        super(Family.all(BombBonusComponent.class, BeginContactComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        for (Entity en: getEngine().getEntitiesFor(Family.all(BlockComponent.class).get())) {
            EntityBuilder.createComponent(en, DamageComponent.class).damage = 1;
        }
        EntityBuilder.createComponent(entity, RemoveEntityComponent.class);
    }
}
