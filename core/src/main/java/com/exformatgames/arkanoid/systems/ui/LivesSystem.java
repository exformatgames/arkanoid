package com.exformatgames.arkanoid.systems.ui;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.exformatgames.arkanoid.ArkanoidGame;
import com.exformatgames.arkanoid.GameState;
import com.exformatgames.arkanoid.components.BallComponent;
import com.exformatgames.arkanoid.components.UILivesComponent;
import com.exformatgames.arkanoid.components.PaddleComponent;
import com.exformatgames.arkanoid.components.RestartComponent;
import com.exformatgames.arkanoid.entities.BallEntityBuilder;
import com.github.exformatgames.defender.components.box2d.BodyComponent;
import com.github.exformatgames.defender.components.rendering_components.ui.TextRenderComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class LivesSystem extends IteratingSystem {

    private final Family ballsFamily;
    private final Family paddleFamily;

    public LivesSystem() {
        super(Family.all(UILivesComponent.class).get());
        ballsFamily = Family.one(BallComponent.class).get();
        paddleFamily = Family.one(PaddleComponent.class).get();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TextRenderComponent textRenderComponent = TextRenderComponent.getComponent(entity);

        BodyComponent paddleBodyComponent = BodyComponent.getComponent(getEngine().getEntitiesFor(paddleFamily).first());

        int balls = getEngine().getEntitiesFor(ballsFamily).size();

        if (balls == 0 && ArkanoidGame.LIVES > 0 && ArkanoidGame.GAME_STATE == GameState.IN_GAME) {
            ArkanoidGame.LIVES -= 1;
            ArkanoidGame.GAME_STATE = GameState.READY;
            new BallEntityBuilder().create(new Vector2(paddleBodyComponent.oldPosition.x, paddleBodyComponent.oldPosition.y + 0.3f));
        }

        if (ArkanoidGame.LIVES == 0 && getEngine().getEntitiesFor(ballsFamily).size() == 0 && ArkanoidGame.GAME_STATE == GameState.IN_GAME) {
            EntityBuilder.createComponent(entity, RestartComponent.class);
        }

        textRenderComponent.text = "lives: " + ArkanoidGame.LIVES;
    }
}
