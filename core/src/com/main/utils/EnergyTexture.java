package com.main.utils;

import com.badlogic.gdx.graphics.Texture;
import com.main.Main;

public class EnergyTexture extends Energy {

    private float energyBarY, energyBarX, energyBarWidth, energyBarHeight;
    private Texture energyTexture;


    public EnergyTexture(Main game) {
        super();
        onResize(game);
        energyTexture = new Texture(super.getTextureName());
    }

    public void onResize(Main game) {
        final float scaleX = game.scaleFactorX, scaleY = game.scaleFactorY, screenWidth = game.screenWidth, screenHeight = game.screenHeight;
        energyBarWidth = 200 * scaleX;
        energyBarHeight = 64 * scaleY;
        energyBarY = screenHeight - energyBarHeight - 10 * scaleY;
        energyBarX = 30 * scaleX + 64 * scaleX;
    }

    public void draw(Main game) {
        game.batch.draw(energyTexture, energyBarX, energyBarY, energyBarWidth, energyBarHeight);
    }

    private void updateTexture() {
        energyTexture.dispose();
        energyTexture = new Texture(getTextureName());
    }

    @Override
    public void increaseEnergy(int amount) {
        super.increaseEnergy(amount);
        updateTexture();
    }

    @Override
    public void decreaseEnergy(int amount) {
        super.decreaseEnergy(amount);
        updateTexture();
    }

    public void disposeTexture()
    {
        energyTexture.dispose();
    }
}
