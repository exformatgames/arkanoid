package com.exformatgames.arkanoid.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.exformatgames.arkanoid.ArkanoidGame;
import com.exformatgames.arkanoid.components.WallComponent;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.box2d.BodyComponent;
import com.github.exformatgames.defender.components.transform_components.PositionComponent;
import com.github.exformatgames.defender.components.transform_components.SizeComponent;
import com.github.exformatgames.defender.utils.BodyBuilder;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class WallEntityBuilder extends EntityBuilder {

    @Override
    public void create() {}

    @Override
    public void create(Vector2 position) {
        create(position, new Vector2(0.1f, Configurations.WORLD_HEIGHT));
    }

    public void create(Vector2 position, Vector2 size) {
        createComponent(WallComponent.class);
        createComponent(SizeComponent.class).init(size.x, size.y);
        createComponent(PositionComponent.class);
        createComponent(BodyComponent.class).init(BodyBuilder.buildBox(BodyDef.BodyType.KinematicBody, position, size.x, size.y))
                .setUserData(entity)
                .setFilter(ArkanoidGame.MASK_WALL, ArkanoidGame.CATEGORY_WALL);
    }
}
