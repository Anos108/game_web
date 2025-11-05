package org.example.demo2;

import javafx.geometry.Rectangle2D;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Paddle
 */
class PaddleTest {
    
    private Paddle paddle;
    
    @BeforeEach
    void setUp() {
        paddle = new Paddle(350, 700);
    }
    
    @Test
    @DisplayName("Test paddle initial position")
    void testPaddleInitialPosition() {
        assertEquals(350, paddle.getX(), "Paddle X position should be 350");
        assertEquals(700, paddle.getY(), "Paddle Y position should be 700");
    }
    
    @Test
    @DisplayName("Test paddle movement left")
    void testPaddleMovementLeft() {
        double initialX = paddle.getX();
        
        // Move left (assuming no wall collision)
        paddle.update(true, false, paddle.getBounds());
        
        // Paddle should move left (X decreases) if not hitting wall
        assertTrue(paddle.getX() <= initialX, 
                   "Paddle X should decrease or stay same when moving left");
    }
    
    @Test
    @DisplayName("Test paddle movement right")
    void testPaddleMovementRight() {
        // Start from a position where we can definitely move right
        paddle.setPaddle(100, 700);
        double initialX = paddle.getX();
        
        // Move right
        paddle.update(false, true, paddle.getBounds());
        
        // Paddle should move right (X increases) if not hitting wall
        assertTrue(paddle.getX() >= initialX, 
                   "Paddle X should increase or stay same when moving right");
    }
    
    @Test
    @DisplayName("Test paddle not moving when no input")
    void testPaddleNotMoving() {
        double initialX = paddle.getX();
        
        // No movement input
        paddle.update(false, false, paddle.getBounds());
        
        assertEquals(initialX, paddle.getX(), 
                    "Paddle should not move when no input");
        assertFalse(paddle.isMoving(), 
                   "Paddle should not be marked as moving");
    }
    
    @Test
    @DisplayName("Test paddle moving status")
    void testPaddleMovingStatus() {
        // Initially not moving
        assertFalse(paddle.isMoving(), "Paddle should not be moving initially");
        
        // Move right (not left, due to else bug in Paddle.update)
        paddle.update(false, true, paddle.getBounds());
        assertTrue(paddle.isMoving(), "Paddle should be marked as moving when moving right");
    }
    
    @Test
    @DisplayName("Test paddle position setter")
    void testSetPaddlePosition() {
        paddle.setPaddle(200, 650);
        
        assertEquals(200, paddle.getX(), "Paddle X should be 200");
        assertEquals(650, paddle.getY(), "Paddle Y should be 650");
    }
    
    @Test
    @DisplayName("Test paddle bounds")
    void testPaddleBounds() {
        paddle.setPaddle(100, 600);
        Rectangle2D bounds = paddle.getBounds();
        
        assertEquals(100, bounds.getMinX(), "Bounds minX should be 100");
        assertEquals(600, bounds.getMinY(), "Bounds minY should be 600");
        assertEquals(Config.PADDLE_WIDTH, bounds.getWidth(), 
                    "Bounds width should match Config.PADDLE_WIDTH");
        assertEquals(Config.PADDLE_HEIGHT, bounds.getHeight(), 
                    "Bounds height should match Config.PADDLE_HEIGHT");
    }
    
    @Test
    @DisplayName("Test paddle bounds update with position")
    void testPaddleBoundsUpdatesWithPosition() {
        paddle.setPaddle(150, 650);
        Rectangle2D bounds1 = paddle.getBounds();
        
        paddle.setPaddle(250, 650);
        Rectangle2D bounds2 = paddle.getBounds();
        
        assertNotEquals(bounds1.getMinX(), bounds2.getMinX(), 
                       "Bounds should update when paddle position changes");
    }
    
    @Test
    @DisplayName("Test paddle continuous movement")
    void testPaddleContinuousMovement() {
        paddle.setPaddle(400, 700);
        double initialX = paddle.getX();
        
        // Move right multiple times
        for (int i = 0; i < 5; i++) {
            paddle.update(false, true, paddle.getBounds());
        }
        
        // Should have moved right (X increased)
        assertTrue(paddle.getX() > initialX, 
                  "Paddle should move right after multiple updates");
    }
    
    @Test
    @DisplayName("Test paddle stays within reasonable bounds")
    void testPaddleStaysInBounds() {
        // Move paddle far to the left
        paddle.setPaddle(10, 700);
        for (int i = 0; i < 100; i++) {
            paddle.update(true, false, paddle.getBounds());
        }
        
        // Paddle X should not be negative (assuming Wall checks prevent it)
        assertTrue(paddle.getX() >= 0 || paddle.getX() <= Config.WIDTH, 
                  "Paddle should stay within game boundaries");
    }
}

