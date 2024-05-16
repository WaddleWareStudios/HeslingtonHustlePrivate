package com.main.utils;

import com.badlogic.gdx.graphics.Texture;
import com.main.Main;

public class Energy
{
    private Integer counter;
    private float energyBarY, energyBarX, energyBarWidth, energyBarHeight;
    private Texture energyTexture;

    public Energy(Main game)
    {
        counter = 10;
        onResize(game);
        energyTexture = new Texture(getTextureName());
    }
    public void onResize(Main game)
    {
        final float scaleX = game.scaleFactorX, scaleY = game.scaleFactorY, screenWidth = game.screenWidth, screenHeight = game.screenHeight;
        energyBarWidth = 200 * scaleX;
        energyBarHeight = 64 * scaleY;
        energyBarY = screenHeight - energyBarHeight - 10 * scaleY;
        energyBarX = 30 * scaleX + 64 * scaleX;
    }
    private String getTextureName()
    {
        return "energy/energy_" + counter.toString() + ".png";
    }
    public void draw(Main game)
    {
        game.batch.draw(energyTexture, energyBarX, energyBarY, energyBarWidth, energyBarHeight);
    }
    public Texture getEnergyTexture()
    {
        return energyTexture;
    }
    private void updateTexture()
    {
        energyTexture.dispose();
        energyTexture = new Texture(getTextureName());
    }
    public void increaseEnergy(int amount)
    {
        counter = Math.min(10, counter + amount);
        updateTexture();
    }
    public void decreaseEnergy(int amount)
    {
        counter = Math.max(0, counter - amount);
        updateTexture();
    }
    public Integer getCounter()
    {
        return counter;
    }
    public void disposeTexture()
    {
        energyTexture.dispose();
    }
}
