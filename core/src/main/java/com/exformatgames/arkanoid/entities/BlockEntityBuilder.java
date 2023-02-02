package com.exformatgames.arkanoid.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.exformatgames.arkanoid.ArkanoidGame;
import com.exformatgames.arkanoid.components.BlockComponent;
import com.exformatgames.arkanoid.components.HPComponent;
import com.github.exformatgames.defender.components.box2d.BodyComponent;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;
import com.github.exformatgames.defender.components.rendering_components.ZIndexComponent;
import com.github.exformatgames.defender.components.transform_components.PositionComponent;
import com.github.exformatgames.defender.components.transform_components.SizeComponent;
import com.github.exformatgames.defender.utils.BodyBuilder;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class BlockEntityBuilder extends EntityBuilder {

    @Override
    public void create() {}

    public static void create(Vector2 position, Vector2 size) {
        create(position, size, MathUtils.random(1, 7));
    }

    public static void create(Vector2 position, Vector2 size, int index) {
        Entity en = engine.createEntity();
        engine.addEntity(en);

        createComponent(en, BlockComponent.class);
        createComponent(en, HPComponent.class).hp = index;
        createComponent(en, SpriteComponent.class).init(textureAtlas.findRegion("block", index))
                .setPosition(position.x, position.y);
        createComponent(en, ZIndexComponent.class).zIndex = 2;
        createComponent(en, SizeComponent.class).init(size);
        createComponent(en, PositionComponent.class);
        createComponent(en, BodyComponent.class).init(BodyBuilder.buildBox(BodyDef.BodyType.StaticBody, position, size.x, size.y))
                .setFilter(ArkanoidGame.MASK_BLOCK, ArkanoidGame.CATEGORY_BLOCK);

    }
}
