package org.example.demo2;

import javafx.geometry.Rectangle2D;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Wall
 */
class WallTest {
    
    @Test
    @DisplayName("Test top wall collision detection")
    void testTopWallCollision() {
        // Rectangle near top of screen
        Rectangle2D rect = new Rectangle2D(100, 5, 25, 25);
        
        int result = Wall.check_wall(rect);
        
        assertEquals(1, result, "Should detect top wall collision");
    }
    
    @Test
    @DisplayName("Test left wall collision detection")
    void testLeftWallCollision() {
        // Rectangle near left edge
        Rectangle2D rect = new Rectangle2D(5, 100, 25, 25);
        
        int result = Wall.check_wall(rect);
        
        assertEquals(2, result, "Should detect left wall collision");
    }
    
    @Test
    @DisplayName("Test right wall collision detection")
    void testRightWallCollision() {
        // Rectangle near right edge
        Rectangle2D rect = new Rectangle2D(795, 100, 25, 25);
        
        int result = Wall.check_wall(rect);
        
        assertEquals(3, result, "Should detect right wall collision");
    }
    
    @Test
    @DisplayName("Test bottom wall collision detection")
    void testBottomWallCollision() {
        // Rectangle at bottom of screen
        Rectangle2D rect = new Rectangle2D(400, 790, 25, 25);
        
        int result = Wall.check_wall(rect);
        
        assertEquals(4, result, "Should detect bottom wall collision");
    }
    
    @Test
    @DisplayName("Test no wall collision in center")
    void testNoWallCollisionInCenter() {
        // Rectangle in center of screen
        Rectangle2D rect = new Rectangle2D(400, 400, 25, 25);
        
        int result = Wall.check_wall(rect);
        
        assertEquals(0, result, "Should not detect any wall collision in center");
    }
    
    @Test
    @DisplayName("Test ball hitting top wall")
    void testBallHittingTopWall() {
        Ball ball = new Ball(400, 5);
        Rectangle2D ballBounds = ball.getBounds();
        
        int wallCollision = Wall.check_wall(ballBounds);
        
        assertEquals(1, wallCollision, "Ball should hit top wall");
    }
    
    @Test
    @DisplayName("Test paddle near left wall")
    void testPaddleNearLeftWall() {
        Paddle paddle = new Paddle(5, 700);
        Rectangle2D paddleBounds = paddle.getBounds();
        
        int wallCollision = Wall.check_wall(paddleBounds);
        
        assertEquals(2, wallCollision, "Paddle should be near left wall");
    }
    
    @Test
    @DisplayName("Test paddle near right wall")
    void testPaddleNearRightWall() {
        Paddle paddle = new Paddle(750, 700);
        Rectangle2D paddleBounds = paddle.getBounds();
        
        int wallCollision = Wall.check_wall(paddleBounds);
        
        assertEquals(3, wallCollision, "Paddle should be near right wall");
    }
    
    @Test
    @DisplayName("Test ball falling below screen")
    void testBallFallingBelowScreen() {
        Ball ball = new Ball(400, 800);
        Rectangle2D ballBounds = ball.getBounds();
        
        int wallCollision = Wall.check_wall(ballBounds);
        
        assertEquals(4, wallCollision, "Ball should be at bottom (game over scenario)");
    }
    
    @Test
    @DisplayName("Test rectangle at exact screen height")
    void testRectangleAtExactScreenHeight() {
        // Rectangle with maxY exactly at screen height
        Rectangle2D rect = new Rectangle2D(400, 775, 25, 25);
        
        int result = Wall.check_wall(rect);
        
        assertEquals(4, result, "Rectangle at screen height should trigger bottom collision");
    }
    
    @Test
    @DisplayName("Test very small rectangle collisions")
    void testVerySmallRectangleCollisions() {
        // Very small rectangle at top
        Rectangle2D smallRect = new Rectangle2D(400, 4, 5, 5);
        
        int result = Wall.check_wall(smallRect);
        
        assertEquals(1, result, "Small rectangle should still detect top wall");
    }
    
    @Test
    @DisplayName("Test corner collision priority - top left")
    void testTopLeftCornerCollision() {
        // Rectangle in top-left corner
        Rectangle2D rect = new Rectangle2D(5, 5, 10, 10);
        
        int result = Wall.check_wall(rect);
        
        // Should return 1 (top) as it's checked first
        assertEquals(1, result, "Top-left corner should prioritize top wall");
    }
    
    @Test
    @DisplayName("Test corner collision priority - top right")
    void testTopRightCornerCollision() {
        // Rectangle in top-right corner
        Rectangle2D rect = new Rectangle2D(795, 5, 10, 10);
        
        int result = Wall.check_wall(rect);
        
        // Should return 1 (top) as it's checked first
        assertEquals(1, result, "Top-right corner should prioritize top wall");
    }
    
    @Test
    @DisplayName("Test valid game area")
    void testValidGameArea() {
        // Test several points in valid game area
        Rectangle2D[] validRects = {
            new Rectangle2D(400, 400, 25, 25),  // Center
            new Rectangle2D(200, 300, 25, 25),  // Left-center
            new Rectangle2D(600, 500, 25, 25),  // Right-center
            new Rectangle2D(400, 200, 25, 25)   // Upper-center
        };
        
        for (Rectangle2D rect : validRects) {
            int result = Wall.check_wall(rect);
            assertEquals(0, result, 
                "Rectangle at " + rect.getMinX() + "," + rect.getMinY() + 
                " should not collide with any wall");
        }
    }
}

