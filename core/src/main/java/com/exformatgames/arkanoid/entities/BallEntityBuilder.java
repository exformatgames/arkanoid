package com.exformatgames.arkanoid.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.exformatgames.arkanoid.ArkanoidGame;
import com.exformatgames.arkanoid.components.BallComponent;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.box2d.BodyComponent;
import com.github.exformatgames.defender.components.box2d.transform_components.LinearVelocityBodyComponent;
import com.github.exformatgames.defender.components.input_components.gesture_components.GesturePanComponent;
import com.github.exformatgames.defender.components.input_components.gesture_components.GestureTapComponent;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;
import com.github.exformatgames.defender.components.rendering_components.ZIndexComponent;
import com.github.exformatgames.defender.components.transform_components.PositionComponent;
import com.github.exformatgames.defender.components.transform_components.SizeComponent;
import com.github.exformatgames.defender.utils.BodyBuilder;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class BallEntityBuilder extends EntityBuilder {
    @Override
    public void create() {}

    @Override
    public void create(Vector2 position) {
        createComponent(BallComponent.class);

        createComponent(GesturePanComponent.class);
        createComponent(GestureTapComponent.class);

        createComponent(SpriteComponent.class).init(textureAtlas.findRegion("ball"), Configurations.SCL * 2)
                .setPosition(position.x, position.y);
        createComponent(ZIndexComponent.class).zIndex = 2;
        createComponent(SizeComponent.class).init(0.24f, 0.24f);

        createComponent(PositionComponent.class).init(position.x, position.y);

        createComponent(BodyComponent.class)
                .init(BodyBuilder.buildBulletCircle(position.x, position.y, 0, 0, 0.12f))
                .setUserData(entity)
                .setRestitution(1.0001f)
                .setFriction(0)
                .setFilter(ArkanoidGame.MASK_BALL, ArkanoidGame.CATEGORY_BALL);
    }

    public static void create(float x, float y, float impulse) {
        Entity en = engine.createEntity();
        engine.addEntity(en);

        createComponent(en, BallComponent.class);

        createComponent(en, GesturePanComponent.class);
        createComponent(en, GestureTapComponent.class);

        createComponent(en, SpriteComponent.class).init(textureAtlas.findRegion("ball"), Configurations.SCL * 2)
                .setPosition(x, y);
        createComponent(en, ZIndexComponent.class).zIndex = 2;
        createComponent(en, SizeComponent.class).init(0.24f, 0.24f);

        createComponent(en, PositionComponent.class).init(x, y);

        createComponent(en, BodyComponent.class)
                .init(BodyBuilder.buildBulletCircle(x, y, 0, 0, 0.12f))
                .setUserData(en)
                .setRestitution(1.0001f)
                .setFriction(0)
                .setFilter(ArkanoidGame.MASK_BALL, ArkanoidGame.CATEGORY_BALL);

        createComponent(en, LinearVelocityBodyComponent.class).init(impulse, 10);
    }
}
