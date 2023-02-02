package com.exformatgames.arkanoid.entities;

import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class BackgroundEntityBuilder extends EntityBuilder {

    @Override
    public void create() {
        createComponent(SpriteComponent.class).init(textureAtlas.findRegion("background"))
                .setSize(Configurations.WORLD_WIDTH, Configurations.WORLD_HEIGHT)
                .setPosition(0, 0);
    }
}
