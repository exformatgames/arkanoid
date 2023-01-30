package com.exformatgames.arkanoid.components.defender;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class TimeActionComponent implements Component {

    public float timerAction = 0;

    public float targetTime = 0;

    private final static ComponentMapper<TimeActionComponent> mapper = ComponentMapper.getFor(TimeActionComponent.class);

    public static TimeActionComponent getComponent(Entity entity){
        return mapper.get(entity);
    }
}
