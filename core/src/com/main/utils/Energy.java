package com.main.utils;

/**
 * Represents the energy system for the game, where energy levels are managed.
 * The energy level has a max cap of 10 and a minimum of 0.
 */
public class Energy {

    private Integer counter; // Current energy level

    /**
     * Constructs a new Energy object with energy initially set to 10.
     */
    public Energy() {
        counter = 10;
    }

    /**
     * Increases energy level by a specified amount, ensuring that it does not exceed the max of 10.
     *
     * @param amount the amount to increase the energy by; should be non-negative
     */
    public void increaseEnergy(int amount) {
        counter = Math.min(10, counter + amount);
    }

    /**
     * Decreases energy level by a specified amount, ensuring that it does not decrease below 0.
     *
     * @param amount the amount to decrease the energy by; should be non-negative
     */
    public void decreaseEnergy(int amount) {
        counter = Math.max(0, counter - amount);
    }

    /**
     * Retrieves current energy level.
     *
     * @return the current energy level as an Integer
     */
    public Integer getEnergy() {
        return counter;
    }

    /**
     * Generates a texture name based on the current energy level. This texture name is used
     * for graphical representation in the game interface.
     *
     * @return a string representing the file path to the texture for the current energy level
     */
    public String getTextureName() {
        return "energy/energy_" + getEnergy() + ".png";
    }
}
