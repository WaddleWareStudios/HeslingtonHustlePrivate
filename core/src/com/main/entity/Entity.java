package com.main.entity;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Represents an entity in the game world.
 * This class extends {@link Sprite} and includes additional attributes for managing the entity's
 * position in the game world, speed, and current animation state.
 */
public class Entity extends Sprite {
    public float worldX, worldY;
    public float speed; // walking speed per frame
    public Animation<TextureRegion> currentAnimation;
    public float stateTime; // Tracks animation time

}
