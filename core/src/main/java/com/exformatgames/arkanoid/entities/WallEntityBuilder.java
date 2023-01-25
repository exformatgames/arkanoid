package com.exformatgames.arkanoid.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.exformatgames.arkanoid.ArkanoidGame;
import com.exformatgames.arkanoid.components.WallComponent;
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
        create(position, new Vector2(0.1f, 19.2f));
    }

    public void create(Vector2 position, Vector2 size) {
        Entity en = engine.createEntity();
        engine.addEntity(en);

        createComponent(en, WallComponent.class);
        createComponent(en, SizeComponent.class).init(size.x, size.y);
        createComponent(en, PositionComponent.class);
        createComponent(en, BodyComponent.class).init(BodyBuilder.buildBox(BodyDef.BodyType.KinematicBody, position, size.x, size.y))
                .setUserData(en); //fixme;
    }
}
