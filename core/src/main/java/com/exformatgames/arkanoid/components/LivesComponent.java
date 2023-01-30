package com.exformatgames.arkanoid.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class LivesComponent implements Component {

    public int lives = 3;

    private final static ComponentMapper<LivesComponent> mapper = ComponentMapper.getFor(LivesComponent.class);

    public static LivesComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
