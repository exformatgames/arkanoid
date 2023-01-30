package com.exformatgames.arkanoid.components.bonuses;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class BonusComponent implements Component {

    public BonusType bonusType;

    private final static ComponentMapper<BonusComponent> mapper = ComponentMapper.getFor(BonusComponent.class);

    public static BonusComponent getComponent(Entity entity){
        return mapper.get(entity);
    }
}
