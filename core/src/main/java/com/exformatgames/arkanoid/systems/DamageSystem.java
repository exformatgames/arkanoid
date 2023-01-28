package com.exformatgames.arkanoid.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.exformatgames.arkanoid.components.DamageComponent;
import com.exformatgames.arkanoid.components.HPComponent;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;

public class DamageSystem extends IteratingSystem {

    private final TextureAtlas textureAtlas;

    public DamageSystem(TextureAtlas textureAtlas) {
        super(Family.all(DamageComponent.class).get());
        this.textureAtlas = textureAtlas;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HPComponent hpComponent = HPComponent.getComponent(entity);
        DamageComponent damageComponent = DamageComponent.getComponent(entity);
        SpriteComponent spriteComponent = SpriteComponent.getComponent(entity);

        hpComponent.hp -= damageComponent.damage;
        if (hpComponent.hp > 0)
            spriteComponent.setUV(textureAtlas.findRegion("block", hpComponent.hp));

        entity.remove(DamageComponent.class);
    }
}
