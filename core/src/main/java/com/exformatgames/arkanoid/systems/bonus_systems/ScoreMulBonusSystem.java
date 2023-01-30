package com.exformatgames.arkanoid.systems.bonus_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.arkanoid.components.ScoreComponent;
import com.exformatgames.arkanoid.components.bonuses.ScoreMulBonusComponent;
import com.github.exformatgames.defender.components.rendering_components.ui.TextRenderComponent;
import com.github.exformatgames.defender.components.util_components.RemoveEntityComponent;

public class ScoreMulBonusSystem extends IteratingSystem {


    public ScoreMulBonusSystem() {
        super(Family.all(ScoreMulBonusComponent.class, TextRenderComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TextRenderComponent textRenderComponent = TextRenderComponent.getComponent(entity);
        RemoveEntityComponent removeEntityComponent = RemoveEntityComponent.getComponent(entity);

        textRenderComponent.text = "X2: " + removeEntityComponent.timer;
        for (Entity scoreEntity: getEngine().getEntitiesFor(Family.one(ScoreComponent.class).get())) {
            ScoreComponent scoreComponent = ScoreComponent.getComponent(scoreEntity);
            scoreComponent.score *= 2;
        }
    }
}
