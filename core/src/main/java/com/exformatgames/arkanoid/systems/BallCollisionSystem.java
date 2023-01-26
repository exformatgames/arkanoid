package com.exformatgames.arkanoid.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.exformatgames.arkanoid.ArkanoidGame;
import com.exformatgames.arkanoid.components.BallComponent;
import com.exformatgames.arkanoid.components.BallInDeadzoneComponent;
import com.exformatgames.arkanoid.components.BoxContactComponent;
import com.exformatgames.arkanoid.components.DamageComponent;
import com.github.exformatgames.defender.components.audio_components.SoundComponent;
import com.github.exformatgames.defender.components.box2d.contact_components.BeginContactComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class BallCollisionSystem extends IteratingSystem {

    private final AssetManager assetManager;

    public BallCollisionSystem(AssetManager assetManager) {
        super(Family.all(BallComponent.class, BeginContactComponent.class).get());

        this.assetManager = assetManager;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BeginContactComponent contactComponent = BeginContactComponent.getComponent(entity);
        if (ArkanoidGame.CATEGORY_BOX == contactComponent.contactBody.getFixtureList().first().getFilterData().categoryBits){
            entity.add(getEngine().createComponent(BoxContactComponent.class));
            EntityBuilder.createComponent(contactComponent.contactEntity, DamageComponent.class).damage = 1;

            return;
        }
        if (ArkanoidGame.CATEGORY_WALL == contactComponent.contactBody.getFixtureList().first().getFilterData().categoryBits){
            EntityBuilder.createComponent(entity, SoundComponent.class).init(assetManager.get("audio/sound/Cursor_tones/cursor_style_2.ogg", Sound.class), 1, true, 0);
            return;
        }
        if (ArkanoidGame.CATEGORY_DEADZONE == contactComponent.contactBody.getFixtureList().first().getFilterData().categoryBits){
            entity.add(getEngine().createComponent(BallInDeadzoneComponent.class));
            EntityBuilder.createComponent(entity, SoundComponent.class).init(assetManager.get("audio/sound/Error_tones/style1/error_style_1_001.ogg", Sound.class), 1, true, 0);
        }
        entity.remove(BeginContactComponent.class);
    }
}
