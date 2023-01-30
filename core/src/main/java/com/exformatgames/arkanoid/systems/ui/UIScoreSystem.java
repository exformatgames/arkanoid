package com.exformatgames.arkanoid.systems.ui;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.arkanoid.ArkanoidGame;
import com.exformatgames.arkanoid.components.ScoreComponent;
import com.github.exformatgames.defender.components.rendering_components.ui.TextRenderComponent;

public class UIScoreSystem extends IteratingSystem {

    public UIScoreSystem() {
        super(Family.all(ScoreComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TextRenderComponent textRenderComponent = TextRenderComponent.getComponent(entity);

        textRenderComponent.text = "score: " + ArkanoidGame.SCORE;
    }
}
