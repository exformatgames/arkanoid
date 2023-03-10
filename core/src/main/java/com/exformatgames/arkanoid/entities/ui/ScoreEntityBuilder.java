package com.exformatgames.arkanoid.entities.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.exformatgames.arkanoid.components.UIScoreComponent;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;
import com.github.exformatgames.defender.components.rendering_components.ZIndexComponent;
import com.github.exformatgames.defender.components.rendering_components.ui.TextRenderComponent;
import com.github.exformatgames.defender.components.transform_components.PositionComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class ScoreEntityBuilder extends EntityBuilder {

    @Override
    public void create() {
        createComponent(UIScoreComponent.class);
        createComponent(PositionComponent.class).init(Configurations.WORLD_WIDTH - 1.8f, Configurations.WORLD_HEIGHT);
        createComponent(SpriteComponent.class).init(textureAtlas.findRegion("paddle"))
                .setPosition(Configurations.WORLD_WIDTH - 2, Configurations.WORLD_HEIGHT - 0.3f)
                .setSize(2, 0.3f);
        createComponent(ZIndexComponent.class).zIndex = 4;
        createComponent(TextRenderComponent.class).init(assetManager.get("font2.fnt", BitmapFont.class), "").setScale(2);
    }
}
