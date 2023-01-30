package com.exformatgames.arkanoid.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.exformatgames.arkanoid.ArkanoidGame;
import com.exformatgames.arkanoid.components.bonuses.BonusComponent;
import com.exformatgames.arkanoid.components.bonuses.BonusType;
import com.github.exformatgames.defender.components.box2d.BodyComponent;
import com.github.exformatgames.defender.components.box2d.transform_components.LinearVelocityBodyComponent;
import com.github.exformatgames.defender.components.rendering_components.CullingComponent;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;
import com.github.exformatgames.defender.components.rendering_components.ZIndexComponent;
import com.github.exformatgames.defender.components.transform_components.PositionComponent;
import com.github.exformatgames.defender.components.transform_components.ScaleLoopComponent;
import com.github.exformatgames.defender.components.transform_components.SizeComponent;
import com.github.exformatgames.defender.utils.BodyBuilder;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class BonusEntityBuilder extends EntityBuilder {

    @Override
    public void create() {}

    public static void create(float x, float y, BonusType type) {
        Entity en = engine.createEntity();
        engine.addEntity(en);

        createComponent(en, BonusComponent.class).bonusType = type;

        switch (type) {
            case LENGTH: {
                createComponent(en, SpriteComponent.class).init(textureAtlas.findRegion("length_bonus"))
                        .setPosition(x, y);
                break;
            }
            case BALLS: {
                createComponent(en, SpriteComponent.class).init(textureAtlas.findRegion("balls_bonus"))
                        .setPosition(x, y);
                break;
            }
            case LIVE: {
                createComponent(en, SpriteComponent.class).init(textureAtlas.findRegion("live_bonus"))
                        .setPosition(x, y);
                break;
            }
            case BOMB: {
                createComponent(en, SpriteComponent.class).init(textureAtlas.findRegion("bomb_bonus"))
                        .setPosition(x, y);
                break;
            }
        }


        createComponent(en, ZIndexComponent.class).zIndex = 3;
        createComponent(en, ScaleLoopComponent.class).init(0.8f, 1.2f, 0.8f, 1.5f, 20, 10);
        createComponent(en, SizeComponent.class).init(0.64f, 0.32f);
        createComponent(en, PositionComponent.class).init(x, y);
        createComponent(en, CullingComponent.class).inViewport = true;
        createComponent(en, BodyComponent.class).init(BodyBuilder.buildBoxSensor(BodyDef.BodyType.DynamicBody, new Vector2(x, y), 0.64f, 0.32f, 0))
                .setUserData(en)
                .setFixedRotation(true)
                .setSensor(true)
                .setFilter(ArkanoidGame.MASK_BONUS, ArkanoidGame.CATEGORY_BONUS);
        createComponent(en, LinearVelocityBodyComponent.class).init(0, -5);
    }
}
