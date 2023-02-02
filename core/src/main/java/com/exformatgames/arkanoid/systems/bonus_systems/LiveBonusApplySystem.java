package com.exformatgames.arkanoid.systems.bonus_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.arkanoid.ArkanoidGame;
import com.exformatgames.arkanoid.components.bonuses.LiveBonusComponent;
import com.github.exformatgames.defender.components.box2d.contact_components.BeginContactComponent;
import com.github.exformatgames.defender.components.util_components.RemoveEntityComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class LiveBonusApplySystem extends IteratingSystem {

    public LiveBonusApplySystem() {
        super(Family.all(LiveBonusComponent.class, BeginContactComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ArkanoidGame.LIVES += 1;
        EntityBuilder.createComponent(entity, RemoveEntityComponent.class);
    }
}
