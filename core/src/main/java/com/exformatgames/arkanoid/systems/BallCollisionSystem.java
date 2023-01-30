package com.exformatgames.arkanoid.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.exformatgames.arkanoid.ArkanoidGame;
import com.exformatgames.arkanoid.components.BallComponent;
import com.exformatgames.arkanoid.components.BallInDeadzoneComponent;
import com.exformatgames.arkanoid.components.BlockContactComponent;
import com.exformatgames.arkanoid.components.DamageComponent;
import com.github.exformatgames.defender.components.audio_components.SoundComponent;
import com.github.exformatgames.defender.components.box2d.contact_components.BeginContactComponent;
import com.github.exformatgames.defender.components.util_components.RemoveEntityComponent;
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

        switch (contactComponent.contactBody.getFixtureList().first().getFilterData().categoryBits) {
            case ArkanoidGame.CATEGORY_BLOCK: {
                entity.add(getEngine().createComponent(BlockContactComponent.class));
                EntityBuilder.createComponent(contactComponent.contactEntity, DamageComponent.class).damage = 1;
                EntityBuilder.createComponent(entity, SoundComponent.class).init(assetManager.get("audio/ball-contact-with-block-sound.ogg", Sound.class), 1, true, 0);
                break;
            }
            case ArkanoidGame.CATEGORY_DEADZONE: {
                EntityBuilder.createComponent(entity, RemoveEntityComponent.class);
                EntityBuilder.createComponent(entity, SoundComponent.class).init(assetManager.get("audio/ball-in-deadzone-sound.ogg", Sound.class), 1, true, 0);
                break;
            }
            case ArkanoidGame.CATEGORY_PADDLE: {
                EntityBuilder.createComponent(entity, SoundComponent.class).init(assetManager.get("audio/ball-contact-with-paddle-sound.ogg", Sound.class), 1, true, 0);
                break;
            }
            case ArkanoidGame.CATEGORY_WALL: {
                EntityBuilder.createComponent(entity, SoundComponent.class).init(assetManager.get("audio/ball-contact-with-wall-sound.ogg", Sound.class), 1, true, 0);
                break;
            }
        }

        entity.remove(BeginContactComponent.class);
    }
}
