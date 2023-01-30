package com.exformatgames.arkanoid.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.arkanoid.ArkanoidGame;
import com.exformatgames.arkanoid.components.ScoreComponent;

public class ScoreSystem extends IteratingSystem {

    public ScoreSystem() {
        super(Family.all(ScoreComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ScoreComponent scoreComponent = ScoreComponent.getComponent(entity);

        ArkanoidGame.SCORE += scoreComponent.score;
        entity.remove(ScoreComponent.class);
    }
}
