package com.exformatgames.arkanoid.components.bonuses;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class BonusSpawnComponent implements Component {

    public BonusType bonusType;
    public float x;
    public float y;

    public BonusSpawnComponent init(BonusType type, float x, float y){
        this.bonusType = type;
        this.x = x;
        this.y = y;

        return this;
    }

    private final static ComponentMapper<BonusSpawnComponent> mapper = ComponentMapper.getFor(BonusSpawnComponent.class);

    public static BonusSpawnComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}

