package com.exformatgames.arkanoid.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class ScoreComponent implements Component {

    public int score = 0;

    private final static ComponentMapper<ScoreComponent> mapper = ComponentMapper.getFor(ScoreComponent.class);

    public static ScoreComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
