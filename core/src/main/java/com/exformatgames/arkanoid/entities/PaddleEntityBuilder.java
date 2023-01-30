package com.exformatgames.arkanoid.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.exformatgames.arkanoid.ArkanoidGame;
import com.exformatgames.arkanoid.components.PaddleComponent;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.box2d.BodyComponent;
import com.github.exformatgames.defender.components.input_components.gesture_components.GesturePanComponent;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;
import com.github.exformatgames.defender.components.rendering_components.ZIndexComponent;
import com.github.exformatgames.defender.components.transform_components.PositionComponent;
import com.github.exformatgames.defender.components.transform_components.SizeComponent;
import com.github.exformatgames.defender.utils.BodyBuilder;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class PaddleEntityBuilder extends EntityBuilder {

    @Override
    public void create() {
        createComponent(PaddleComponent.class);
        createComponent(SpriteComponent.class).init(textureAtlas.findRegion("paddle"));
        createComponent(ZIndexComponent.class).zIndex = 2;
        createComponent(SizeComponent.class).init(1.12f, 0.18f);
        createComponent(PositionComponent.class);
        createComponent(BodyComponent.class).init(BodyBuilder.buildBox(BodyDef.BodyType.KinematicBody, new Vector2(Configurations.WORLD_WIDTH / 2, 1), 1.12f, 0.09f))
                .setUserData(entity)
                .setFixedRotation(true)
                .setFilter(ArkanoidGame.MASK_PADDLE, ArkanoidGame.CATEGORY_PADDLE);
        createComponent(GesturePanComponent.class);
    }
}
