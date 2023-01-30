package com.exformatgames.arkanoid.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.exformatgames.arkanoid.components.BlockComponent;
import com.exformatgames.arkanoid.components.DamageComponent;
import com.exformatgames.arkanoid.components.HPComponent;
import com.exformatgames.arkanoid.components.ScoreComponent;
import com.exformatgames.arkanoid.entities.BonusEntityBuilder;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;
import com.github.exformatgames.defender.components.transform_components.PositionComponent;
import com.github.exformatgames.defender.components.transform_components.SizeComponent;
import com.github.exformatgames.defender.components.util_components.RemoveEntityComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class DamageSystem extends IteratingSystem {

    private final TextureAtlas textureAtlas;

    public DamageSystem(TextureAtlas textureAtlas) {
        super(Family.all(DamageComponent.class, BlockComponent.class).get());
        this.textureAtlas = textureAtlas;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HPComponent hpComponent = HPComponent.getComponent(entity);
        DamageComponent damageComponent = DamageComponent.getComponent(entity);
        SpriteComponent spriteComponent = SpriteComponent.getComponent(entity);
        PositionComponent positionComponent = PositionComponent.getComponent(entity);
        SizeComponent sizeComponent = SizeComponent.getComponent(entity);

        hpComponent.hp -= damageComponent.damage;

        if (hpComponent.hp == 0) {
            if (MathUtils.random(10) > 0)
                BonusEntityBuilder.create(positionComponent.x + sizeComponent.halfWidth, positionComponent.y);
            EntityBuilder.createComponent(entity, ScoreComponent.class).score = 10;
            EntityBuilder.createComponent(entity, RemoveEntityComponent.class);
        }

        if (hpComponent.hp > 0) {
            spriteComponent.setUV(textureAtlas.findRegion("block", hpComponent.hp));
            EntityBuilder.createComponent(entity, ScoreComponent.class).score = 2;
        }

        entity.remove(DamageComponent.class);
    }
}
