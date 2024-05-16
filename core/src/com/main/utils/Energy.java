package com.main.utils;

public class Energy {

    private Integer counter;

    public Energy() {
        counter = 10;
    }

    public void increaseEnergy(int amount) {
        counter = Math.min(10, counter + amount);
    }

    public void decreaseEnergy(int amount) {
        counter = Math.max(0, counter - amount);
    }

    public Integer getEnergy() {
        return counter;
    }

    public String getTextureName() {
        return "energy/energy_" + getEnergy() + ".png";
    }
}
