package com.exformatgames.arkanoid.systems.bonus_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.exformatgames.arkanoid.components.bonuses.BonusSpawnComponent;
import com.exformatgames.arkanoid.components.bonuses.BonusType;
import com.exformatgames.arkanoid.entities.BonusEntityBuilder;
import com.github.exformatgames.defender.components.util_components.RemoveEntityComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class BonusSpawnSystem extends IteratingSystem {

    public BonusSpawnSystem() {
        super(Family.all(BonusSpawnComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BonusSpawnComponent bonusSpawnComponent = BonusSpawnComponent.getComponent(entity);

        switch (MathUtils.random(4)) {
            case 0: {
                BonusEntityBuilder.create(bonusSpawnComponent.x, bonusSpawnComponent.y, BonusType.BALLS);
                break;
            }
            case 1: {
                BonusEntityBuilder.create(bonusSpawnComponent.x, bonusSpawnComponent.y, BonusType.LENGTH);
                break;
            }
            case 2: {
                BonusEntityBuilder.create(bonusSpawnComponent.x, bonusSpawnComponent.y, BonusType.LIVE);
                break;
            }
            case 3: {
                BonusEntityBuilder.create(bonusSpawnComponent.x, bonusSpawnComponent.y, BonusType.BOMB);
                break;
            }
            case 4: {
                BonusEntityBuilder.create(bonusSpawnComponent.x, bonusSpawnComponent.y, BonusType.SCORE_MUL);
                break;
            }
        }

        EntityBuilder.createComponent(entity, RemoveEntityComponent.class);
    }
}
