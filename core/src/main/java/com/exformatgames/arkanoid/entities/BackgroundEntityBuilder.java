package com.exformatgames.arkanoid.entities;

import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;
import com.github.exformatgames.defender.components.rendering_components.ZIndexComponent;
import com.github.exformatgames.defender.components.transform_components.SizeComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class BackgroundEntityBuilder extends EntityBuilder {

    @Override
    public void create() {
        createComponent(SpriteComponent.class).init(atlas.findRegion("background"), 0.01f);
        createComponent(ZIndexComponent.class).zIndex = 1;
        createComponent(SizeComponent.class).init(12.7f, 7.2f);
    }
}
