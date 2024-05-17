package com.main.utils;

import com.badlogic.gdx.graphics.Texture;
import com.main.Main;

/**
 * Handles the integration of the Energy class and visualisation of the energy.
 * Inherits from Energy.
 */
public class EnergyTexture extends Energy {

    private float energyBarY, energyBarX, energyBarWidth, energyBarHeight;
    private Texture energyTexture;

    /**
     * Constructs a new EnergyTexture object. Inherits instantiation from Energy.
     *
     * @param game the main game passed in for texture scaling.
     */
    public EnergyTexture(Main game) {
        super();
        onResize(game);
        energyTexture = new Texture(super.getTextureName());
    }

    /**
     * Called in constructor. Calculates texture dimensions according to screen size.
     *
     * @param game the main game passed in for texture scaling.
     */
    public void onResize(Main game) {
        final float scaleX = game.scaleFactorX, scaleY = game.scaleFactorY, screenWidth = game.screenWidth, screenHeight = game.screenHeight;
        energyBarWidth = 200 * scaleX;
        energyBarHeight = 64 * scaleY;
        energyBarY = screenHeight - energyBarHeight - 10 * scaleY;
        energyBarX = 30 * scaleX + 64 * scaleX;
    }

    /**
     * Draws the energy texture to the screen.
     *
     * @param game the main game passed in for texture scaling.
     */
    public void draw(Main game) {
        game.batch.draw(energyTexture, energyBarX, energyBarY, energyBarWidth, energyBarHeight);
    }

    /**
     * Changes the texture when the energy value changes.
     */
    private void updateTexture() {
        energyTexture.dispose();
        energyTexture = new Texture(getTextureName());
    }

    /**
     * Increases energy level by a specified amount, ensuring that it does not exceed the max of 10.
     * Updates the energy bar texture accordingly.
     *
     * @param amount the amount to increase the energy by; should be non-negative
     */
    @Override
    public void increaseEnergy(int amount) {
        super.increaseEnergy(amount);
        updateTexture();
    }

    /**
     * Decreases energy level by a specified amount, ensuring that it does not exceed the max of 10.
     * Updates the energy bar texture accordingly.
     *
     * @param amount the amount to decrease the energy by; should be non-negative
     */
    @Override
    public void decreaseEnergy(int amount) {
        super.decreaseEnergy(amount);
        updateTexture();
    }

    /**
     * Dispose of energy bar texture when it is not needed.
     */
    public void disposeTexture()
    {
        energyTexture.dispose();
    }
}
