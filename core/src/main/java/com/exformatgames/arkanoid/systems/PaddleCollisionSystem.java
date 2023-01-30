package com.exformatgames.arkanoid.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Filter;
import com.exformatgames.arkanoid.ArkanoidGame;
import com.exformatgames.arkanoid.components.LivesComponent;
import com.exformatgames.arkanoid.components.PaddleComponent;
import com.exformatgames.arkanoid.components.ScoreComponent;
import com.exformatgames.arkanoid.components.bonuses.BonusComponent;
import com.exformatgames.arkanoid.components.defender.TimeActionComponent;
import com.exformatgames.arkanoid.entities.BallEntityBuilder;
import com.github.exformatgames.defender.components.box2d.BodyComponent;
import com.github.exformatgames.defender.components.box2d.contact_components.BeginContactComponent;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;
import com.github.exformatgames.defender.components.transform_components.PositionComponent;
import com.github.exformatgames.defender.components.transform_components.SizeComponent;
import com.github.exformatgames.defender.components.util_components.RemoveEntityComponent;
import com.github.exformatgames.defender.utils.BodyBuilder;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class PaddleCollisionSystem extends IteratingSystem {

    private final Filter filter = new Filter();

    public PaddleCollisionSystem() {
        super(Family.all(PaddleComponent.class, BeginContactComponent.class).get());
        filter.categoryBits = ArkanoidGame.CATEGORY_PADDLE;
        filter.maskBits = ArkanoidGame.MASK_PADDLE;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BeginContactComponent contactComponent = BeginContactComponent.getComponent(entity);

        if (contactComponent.contactBody.getFixtureList().first().getFilterData().categoryBits == ArkanoidGame.CATEGORY_BONUS) {
            BonusComponent bonusComponent = BonusComponent.getComponent(contactComponent.contactEntity);

            switch (bonusComponent.bonusType){
                case LENGTH: {
                    BodyComponent bodyComponent = BodyComponent.getComponent(entity);
                    SpriteComponent spriteComponent = SpriteComponent.getComponent(entity);
                    SizeComponent sizeComponent = SizeComponent.getComponent(entity);


                    if (bodyComponent.body.getFixtureList().size > 1) {
                        bodyComponent.body.getFixtureList().get(1).setSensor(false);
                        bodyComponent.body.getFixtureList().get(1).setFilterData(filter);
                    } else {
                        BodyBuilder.buildSensor(bodyComponent.body, 2, 0.18f).setSensor(false);
                        bodyComponent.body.getFixtureList().get(1).setFilterData(filter);
                    }
                    spriteComponent.setSize(2, 0.18f);
                    sizeComponent.init(2, 0.18f);
                    break;
                }
                case BALLS: {
                    PositionComponent positionComponent = PositionComponent.getComponent(entity);
                    SizeComponent sizeComponent = SizeComponent.getComponent(entity);

                    BallEntityBuilder.create(positionComponent.x + sizeComponent.halfWidth, positionComponent.y + 0.5f, MathUtils.random(-1, 1));
                    break;
                }
                case LIVE: {
                    LivesComponent.getComponent(getEngine().getEntitiesFor(Family.one(LivesComponent.class).get()).first()).lives += 1;
                }
                case BOMB: {

                    break;
                }
                case SCORE_MUL: {
                    for (Entity scoreEntity : getEngine().getEntitiesFor(Family.one(ScoreComponent.class).get()))
                        EntityBuilder.createComponent(scoreEntity, TimeActionComponent.class).targetTime = 5;
                    break;
                }
            }

            EntityBuilder.createComponent(contactComponent.contactEntity, RemoveEntityComponent.class);
        }

        entity.remove(BeginContactComponent.class);
    }
}
