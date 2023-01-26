package com.exformatgames.arkanoid.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class HPComponent implements Component {

    public int hp = 1;

    private static ComponentMapper<HPComponent> mapper = ComponentMapper.getFor(HPComponent.class);

    public static HPComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
