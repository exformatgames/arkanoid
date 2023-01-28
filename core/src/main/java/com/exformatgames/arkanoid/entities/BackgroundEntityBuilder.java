package com.exformatgames.arkanoid.entities;

import com.github.exformatgames.defender.Constants;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;
import com.github.exformatgames.defender.components.rendering_components.ZIndexComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class BackgroundEntityBuilder extends EntityBuilder {

    @Override
    public void create() {
        createComponent(SpriteComponent.class).init(atlas.findRegion("background"))
                .setSize(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        createComponent(ZIndexComponent.class).zIndex = 1;
    }
}
