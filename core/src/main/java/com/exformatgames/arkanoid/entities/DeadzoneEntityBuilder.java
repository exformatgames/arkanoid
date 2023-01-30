package com.exformatgames.arkanoid.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.exformatgames.arkanoid.ArkanoidGame;
import com.exformatgames.arkanoid.components.DeadzoneComponent;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.box2d.BodyComponent;
import com.github.exformatgames.defender.components.transform_components.SizeComponent;
import com.github.exformatgames.defender.utils.BodyBuilder;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class DeadzoneEntityBuilder extends EntityBuilder {

    @Override
    public void create() {
        createComponent(DeadzoneComponent.class);
        createComponent(SizeComponent.class).init(Configurations.WORLD_WIDTH, 1);

        createComponent(BodyComponent.class).init(BodyBuilder.buildBox(BodyDef.BodyType.StaticBody, new Vector2(Configurations.WORLD_WIDTH / 2, 0), Configurations.WORLD_WIDTH, 1))
                .setUserData(entity)
                .setSensor(true)
                .setFilter(ArkanoidGame.MASK_DEADZONE, ArkanoidGame.CATEGORY_DEADZONE);
    }
}
