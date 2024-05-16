package io.HeslingtonHustle.tests;

import com.main.utils.Energy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the Energy class
 */
public class EnergyTest {

    private Energy energy;

    @Before
    public void initialise() {
        energy = new Energy();
    }

    @Test
    public void decreaseEnergyTest() {
        assertEquals((Integer) 10, energy.getEnergy());
        energy.decreaseEnergy(4);
        assertEquals((Integer) 6, energy.getEnergy());
    }

    @Test
    public void increaseEnergyTest() {
        energy.decreaseEnergy(4);
        energy.increaseEnergy(2);
        assertEquals((Integer) 8, energy.getEnergy());
        energy.increaseEnergy(2);
        assertEquals((Integer) 10, energy.getEnergy());
    }

    @Test
    public void updateTextureNameTest() {
        assertEquals("energy/energy_10.png", energy.getTextureName());
        energy.decreaseEnergy(4);
        assertEquals("energy/energy_6.png", energy.getTextureName());
        energy.increaseEnergy(2);
        assertEquals("energy/energy_8.png", energy.getTextureName());
    }
}
