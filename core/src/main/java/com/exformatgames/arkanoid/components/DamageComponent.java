package com.exformatgames.arkanoid.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class DamageComponent implements Component {

    public int damage = 0;

    private final static ComponentMapper<DamageComponent> mapper = ComponentMapper.getFor(DamageComponent.class);

    public static DamageComponent getComponent(Entity entity){
        return mapper.get(entity);
    }
}
