package com.exformatgames.arkanoid.systems.bonus_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.exformatgames.arkanoid.components.bonuses.ScoreMulBonusComponent;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.box2d.contact_components.BeginContactComponent;
import com.github.exformatgames.defender.components.rendering_components.ui.TextRenderComponent;
import com.github.exformatgames.defender.components.transform_components.PositionComponent;
import com.github.exformatgames.defender.components.util_components.RemoveEntityComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class ScoreMulBonusApplySystem extends IteratingSystem {

    private final AssetManager assetManager;

    public ScoreMulBonusApplySystem(AssetManager assetManager) {
        super(Family.all(ScoreMulBonusComponent.class, BeginContactComponent.class).get());
        this.assetManager = assetManager;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Family family = Family.all(ScoreMulBonusComponent.class, RemoveEntityComponent.class).get();
        if (getEngine().getEntitiesFor(family).size() > 0) {
            RemoveEntityComponent removeEntityComponent = RemoveEntityComponent.getComponent(getEngine().getEntitiesFor(family).first());
            removeEntityComponent.timer += 5;
        } else {
            Entity en = getEngine().createEntity();
            getEngine().addEntity(en);

            EntityBuilder.createComponent(en, ScoreMulBonusComponent.class);
            EntityBuilder.createComponent(en, RemoveEntityComponent.class).timer = 5;
            EntityBuilder.createComponent(en, PositionComponent.class).init(Configurations.WORLD_WIDTH / 2, Configurations.WORLD_HEIGHT);
            EntityBuilder.createComponent(en, TextRenderComponent.class).init(assetManager.get("font.fnt", BitmapFont.class), "X2")

                    .setScale(2);
        }
    }
}
