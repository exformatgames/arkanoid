package com.exformatgames.arkanoid.systems.bonus_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.arkanoid.components.ScoreComponent;
import com.exformatgames.arkanoid.components.defender.TimeActionComponent;

public class ScoreMultipleSystem extends IteratingSystem {

    public ScoreMultipleSystem() {
        super(Family.all(ScoreComponent.class, TimeActionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
