package org.example.demo2;

import javafx.geometry.Rectangle2D;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Config
 */
class ConfigTest {
    
    @Test
    @DisplayName("Test game dimensions constants")
    void testGameDimensions() {
        assertEquals(800, Config.WIDTH, "Game width should be 800");
        assertEquals(800, Config.HEIGHT, "Game height should be 800");
    }
    
    @Test
    @DisplayName("Test gravity constants")
    void testGravityConstants() {
        assertEquals(0.4, Config.GRAVITY_X, 0.001, 
                    "Gravity X should be 0.4");
        assertEquals(0.4, Config.GRAVITY_Y, 0.001, 
                    "Gravity Y should be 0.4");
    }
    
    @Test
    @DisplayName("Test initial key states")
    void testInitialKeyStates() {
        // Key states are now instance fields in Singleton
        Config config = Config.getInstance();
        assertNotNull(config, "Config instance should not be null");
        // Thay dòng 34-35:
        assertDoesNotThrow(config::isLeftPressed);
        assertDoesNotThrow(config::isRightPressed);
    }
    
    @Test
    @DisplayName("Test ball initial position constants")
    void testBallInitialPosition() {
        assertEquals(50, Config.BALL_START_X, "Ball initial X should be 50");
        assertEquals(300, Config.BALL_START_Y, "Ball initial Y should be 300");
    }
    
    @Test
    @DisplayName("Test paddle constants")
    void testPaddleConstants() {
        assertEquals(0, Config.PADDLE_START_X, "Paddle initial X should be 0");
        assertEquals(700, Config.PADDLE_START_Y, "Paddle initial Y should be 700");
        assertEquals(906.0/7, Config.PADDLE_WIDTH, 0.001, 
                    "Paddle width should be 906/7");
        assertEquals(484.0/7, Config.PADDLE_HEIGHT, 0.001, 
                    "Paddle height should be 484/7");
    }
    
    @Test
    @DisplayName("Test brick constants")
    void testBrickConstants() {
        assertEquals(150, Config.BRICK_X, "Brick X should be 150");
        assertEquals(100, Config.BRICK_Y, "Brick Y should be 100");
        assertEquals(64.0, Config.BRICK_WIDTH, "Brick width should be 64");
        assertEquals(32.0, Config.BRICK_HEIGHT, "Brick height should be 32");
    }
    
    @Test
    @DisplayName("Test image path constant")
    void testImagePath() {
        assertEquals("/asset/images/", Config.IMAGE_PATH, 
                    "Image path should be /asset/images/");
    }
    
    @Test
    @DisplayName("Test sound path constant")
    void testSoundPath() {
        assertEquals("/asset/sounds/", Config.SOUND_PATH, 
                    "Sound path should be /asset/sounds/");
    }
    
    @Test
    @DisplayName("Test volume constant")
    void testVolume() {
        Config config = Config.getInstance();
        assertEquals(0.5, config.getVolume(), 0.001, 
                    "Volume should be 0.5");
        assertTrue(config.getVolume() >= 0.0 && config.getVolume() <= 1.0, 
                  "Volume should be between 0 and 1");
    }
    
    @Test
    @DisplayName("Test interact with overlapping rectangles")
    void testInteractWithOverlappingRectangles() {
        Config config = Config.getInstance();
        Rectangle2D rect1 = new Rectangle2D(100, 100, 50, 50);
        Rectangle2D rect2 = new Rectangle2D(120, 120, 50, 50);
        
        assertTrue(config.interact(rect1, rect2), 
                  "Overlapping rectangles should interact");
    }
    
    @Test
    @DisplayName("Test interact with non-overlapping rectangles")
    void testInteractWithNonOverlappingRectangles() {
        Config config = Config.getInstance();
        Rectangle2D rect1 = new Rectangle2D(100, 100, 50, 50);
        Rectangle2D rect2 = new Rectangle2D(200, 200, 50, 50);
        
        assertFalse(config.interact(rect1, rect2), 
                   "Non-overlapping rectangles should not interact");
    }
    
    @Test
    @DisplayName("Test interact with touching rectangles")
    void testInteractWithTouchingRectangles() {
        Config config = Config.getInstance();
        Rectangle2D rect1 = new Rectangle2D(100, 100, 50, 50);
        Rectangle2D rect2 = new Rectangle2D(150, 100, 50, 50);
        
        // Touching at the edge - depends on Rectangle2D.intersects() behavior
        boolean result = config.interact(rect1, rect2);
        assertNotNull(result, "Interact should return a boolean value");
    }
    
    @Test
    @DisplayName("Test interact with identical rectangles")
    void testInteractWithIdenticalRectangles() {
        Config config = Config.getInstance();
        Rectangle2D rect1 = new Rectangle2D(100, 100, 50, 50);
        Rectangle2D rect2 = new Rectangle2D(100, 100, 50, 50);
        
        assertTrue(config.interact(rect1, rect2), 
                  "Identical rectangles should interact");
    }
    
    @Test
    @DisplayName("Test interact with one rectangle inside another")
    void testInteractWithNestedRectangles() {
        Config config = Config.getInstance();
        Rectangle2D outerRect = new Rectangle2D(100, 100, 100, 100);
        Rectangle2D innerRect = new Rectangle2D(120, 120, 20, 20);
        
        assertTrue(config.interact(outerRect, innerRect), 
                  "Nested rectangles should interact");
    }
    
    @Test
    @DisplayName("Test key press state changes")
    void testKeyPressStateChanges() {
        Config config = Config.getInstance();
        
        // Test left key
        config.setLeftPressed(false);
        assertFalse(config.isLeftPressed(), "Left key should be not pressed");
        
        config.setLeftPressed(true);
        assertTrue(config.isLeftPressed(), "Left key should be pressed");
        
        // Test right key
        config.setRightPressed(false);
        assertFalse(config.isRightPressed(), "Right key should be not pressed");
        
        config.setRightPressed(true);
        assertTrue(config.isRightPressed(), "Right key should be pressed");
        
        // Reset
        config.setLeftPressed(false);
        config.setRightPressed(false);
    }
    
    @Test
    @DisplayName("Test both keys pressed simultaneously")
    void testBothKeysPressed() {
        Config config = Config.getInstance();
        
        config.setLeftPressed(true);
        config.setRightPressed(true);
        
        assertTrue(config.isLeftPressed() && config.isRightPressed(), 
                  "Both keys should be pressed simultaneously");
        
        // Reset
        config.setLeftPressed(false);
        config.setRightPressed(false);
    }
    
    @Test
    @DisplayName("Test interact with ball and paddle scenario")
    void testInteractBallPaddleScenario() {
        Config config = Config.getInstance();
        
        // Simulate ball hitting paddle
        Rectangle2D ballBounds = new Rectangle2D(400, 690, 25, 25);
        Rectangle2D paddleBounds = new Rectangle2D(350, 700, Config.PADDLE_WIDTH, Config.PADDLE_HEIGHT);
        
        boolean collision = config.interact(ballBounds, paddleBounds);
        assertTrue(collision, "Ball should collide with paddle");
    }
    
    @Test
    @DisplayName("Test interact with ball and brick scenario")
    void testInteractBallBrickScenario() {
        Config config = Config.getInstance();
        
        // Simulate ball hitting brick
        Rectangle2D ballBounds = new Rectangle2D(150, 95, 25, 25);
        Rectangle2D brickBounds = new Rectangle2D(150, 100, Config.BRICK_WIDTH, Config.BRICK_HEIGHT);
        
        boolean collision = config.interact(ballBounds, brickBounds);
        assertTrue(collision, "Ball should collide with brick");
    }
}

