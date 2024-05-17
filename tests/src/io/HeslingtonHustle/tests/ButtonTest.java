package io.HeslingtonHustle.tests;

import static org.junit.Assert.*;

import com.main.utils.Button;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Button class.
 */
public class ButtonTest {

    private Button button;

    /**
     * Initialises a Button with (x, y) coords, width, and height before the tests
     */
    @Before
    public void initialise() {
        button = new Button();
        button.init(10, 20, 100, 50);
    }

    /**
     * Tests whether the button's properties are set properly after initialisation.
     */
    @Test
    public void testButtonInitialisation() {
        assertEquals(10, button.x(), 0.0);
        assertEquals(20, button.y(), 0.0);
        assertEquals(100, button.width(), 0.0);
        assertEquals(50, button.height(), 0.0);
    }

    /**
     * Tests if the isClicked method returns True for clicks inside the boundaries of the button.
     */
    @Test
    public void testClickInBounds() {
        assertTrue(button.isClicked(15, 30));
        assertTrue(button.isClicked(50, 40));
        assertTrue(button.isClicked(100, 65));
    }

    /**
     * Tests if the isClicked method returns False for clicks outside the boundaries of the button.
     */
    @Test
    public void testClickOutBounds() {
        assertFalse(button.isClicked(5, 30)); // Left of button
        assertFalse(button.isClicked(115, 30)); // Right of button
        assertFalse(button.isClicked(15, 10)); // Above the button
        assertFalse(button.isClicked(15, 90)); // Below the button
    }

    /**
     * Tests isClicked method with negative coords.
     */
    @Test
    public void testIsClickedNegativeCoords() {
        assertFalse(button.isClicked(-5, -10));
        assertFalse(button.isClicked(-15, 30));
    }

    /**
     * Tests if the isClicked method returns True for clicks on the buttons boundaries
     */
    @Test
    public void testClickOnBounds() {
        assertTrue(button.isClicked(10, 20)); // Top left corner
        assertTrue(button.isClicked(110, 20)); // Top right corner
        assertTrue(button.isClicked(10, 70)); // Bottom left corner
        assertTrue(button.isClicked(110, 70)); // Bottom right corner
    }

    /**
     * Tests initialising a Button with zero width.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testZeroWidth() {
        button.init(10, 20, 0, 50);
    }

    /**
     * Tests initialising a Button with zero height.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testZeroHeight() {
        button.init(10, 20, 100, 0);
    }

    /**
     * Tests initialising a Button with negative coords.
     */
    @Test
    public void testNegativeCoords() {
        button.init(-10, -20, 100, 50);
        assertEquals(-10, button.x(), 0.0);
        assertEquals(-20, button.y(), 0.0);
        assertTrue(button.isClicked(-5, -15)); // Click within bounds
        assertFalse(button.isClicked(-15, -25)); // Click outside bounds
    }

    /**
     * Tests handling of large numbers for initialisation and clicks.
     */
    @Test
    public void testLargeNumbers() {
        button.init(100000, 200000, 100000, 50000);
        assertTrue(button.isClicked(150000, 225000)); // Click within bounds
        assertFalse(button.isClicked(50000, 150000)); // Click outside bounds
    }
}
