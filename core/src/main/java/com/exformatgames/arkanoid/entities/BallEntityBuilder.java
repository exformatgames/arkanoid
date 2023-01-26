package com.exformatgames.arkanoid.entities;

import com.badlogic.gdx.math.Vector2;
import com.exformatgames.arkanoid.components.BallComponent;
import com.github.exformatgames.defender.components.box2d.BodyComponent;
import com.github.exformatgames.defender.components.box2d.LinearImpulseComponent;
import com.github.exformatgames.defender.components.input_components.gesture_components.GesturePanComponent;
import com.github.exformatgames.defender.components.input_components.gesture_components.GestureTapComponent;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;
import com.github.exformatgames.defender.components.rendering_components.ZIndexComponent;
import com.github.exformatgames.defender.components.transform_components.SizeComponent;
import com.github.exformatgames.defender.utils.BodyBuilder;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class BallEntityBuilder extends EntityBuilder {
    @Override
    public void create() {}

    @Override
    public void create(Vector2 position) {
        createComponent(BallComponent.class);

        createComponent(GesturePanComponent.class);
        createComponent(GestureTapComponent.class);

        createComponent(SpriteComponent.class).init(atlas.findRegion("ball"), 0.01f)
                .setPosition(position.x, position.y);
        createComponent(ZIndexComponent.class).zIndex = 2;
        createComponent(SizeComponent.class).init(0.2f, 0.2f);

        createComponent(BodyComponent.class)
                .init(BodyBuilder.buildBulletCircle(position.x, position.y, 0, 0, 0.1f))
                .setRestitution(1.01f)
                .setUserData(entity)
                .setFriction(0);

        createComponent(LinearImpulseComponent.class).init(0.3f, 5, 0, 0);
    }
}
