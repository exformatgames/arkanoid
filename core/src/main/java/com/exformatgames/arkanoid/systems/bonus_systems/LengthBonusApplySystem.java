package com.exformatgames.arkanoid.systems.bonus_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.Filter;
import com.exformatgames.arkanoid.ArkanoidGame;
import com.exformatgames.arkanoid.components.PaddleComponent;
import com.exformatgames.arkanoid.components.bonuses.LengthBonusComponent;
import com.github.exformatgames.defender.components.box2d.BodyComponent;
import com.github.exformatgames.defender.components.box2d.contact_components.BeginContactComponent;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;
import com.github.exformatgames.defender.components.transform_components.SizeComponent;
import com.github.exformatgames.defender.components.util_components.RemoveEntityComponent;
import com.github.exformatgames.defender.utils.BodyBuilder;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class LengthBonusApplySystem extends IteratingSystem {

    private final Filter filter = new Filter();
    private final Family paddleFamily;

    public LengthBonusApplySystem() {
        super(Family.all(LengthBonusComponent.class, BeginContactComponent.class).get());
        filter.categoryBits = ArkanoidGame.CATEGORY_PADDLE;
        filter.maskBits = ArkanoidGame.MASK_PADDLE;
        paddleFamily = Family.all(PaddleComponent.class).get();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Entity paddleEntity = getEngine().getEntitiesFor(paddleFamily).first();

        BodyComponent bodyComponent = BodyComponent.getComponent(paddleEntity);
        SpriteComponent spriteComponent = SpriteComponent.getComponent(paddleEntity);
        SizeComponent sizeComponent = SizeComponent.getComponent(paddleEntity);


        if (bodyComponent.body.getFixtureList().size > 1) {
            bodyComponent.body.getFixtureList().get(1).setSensor(false);
            bodyComponent.body.getFixtureList().get(1).setFilterData(filter);
        } else {
            BodyBuilder.buildSensor(bodyComponent.body, 2, 0.18f).setSensor(false);
            bodyComponent.body.getFixtureList().get(1).setFilterData(filter);
        }
        spriteComponent.setSize(2, 0.18f);
        sizeComponent.init(2, 0.18f);

        EntityBuilder.createComponent(entity, RemoveEntityComponent.class);
    }
}
