package org.example.demo2;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for GameplayManager
 */
class GameplayManagerTest {
    
    private GameplayManager gameplayManager;
    
    @BeforeEach
    void setUp() {
        // Create instance for non-static methods
        gameplayManager = new GameplayManager();
        
        // Reset game state before each test
        GameplayManager.resetState();
        GameplayManager.resetLife();
        gameplayManager.resetScore();
    }
    
    @AfterEach
    void tearDown() {
        // Clean up after each test
        GameplayManager.resetState();
    }
    
    @Test
    @DisplayName("Test initial life count")
    void testInitialLifeCount() {
        GameplayManager.resetLife();
        assertEquals(3, GameplayManager.getLife(), 
                    "Initial life count should be 3");
    }
    
    @Test
    @DisplayName("Test life decrease")
    void testLifeDecrease() {
        GameplayManager.resetLife();
        GameplayManager.setCheckLife(true);
        int initialLife = GameplayManager.getLife();
        
        GameplayManager.equalLife();
        
        assertEquals(initialLife - 1, GameplayManager.getLife(), 
                    "Life should decrease by 1");
    }
    
    @Test
    @DisplayName("Test life decrease only when checkLife is true")
    void testLifeDecreaseOnlyWhenCheckLifeIsTrue() {
        GameplayManager.resetLife();
        GameplayManager.setCheckLife(false);
        int initialLife = GameplayManager.getLife();
        
        GameplayManager.equalLife();
        
        assertEquals(initialLife, GameplayManager.getLife(), 
                    "Life should not decrease when checkLife is false");
    }
    
    @Test
    @DisplayName("Test life increase")
    void testLifeIncrease() {
        GameplayManager.resetLife();
        int initialLife = GameplayManager.getLife();
        
        GameplayManager.plusLife();
        
        assertEquals(initialLife + 1, GameplayManager.getLife(), 
                    "Life should increase by 1");
    }

    @Test
    @DisplayName("Test life max")
    void testLifeMax() {
        GameplayManager.resetLife();
        GameplayManager.plusLife();
        GameplayManager.plusLife();
        GameplayManager.plusLife();
        GameplayManager.plusLife();

        assertEquals(5, GameplayManager.getLife(),
                "Life should increase by 5");
    }
    
    @Test
    @DisplayName("Test reset life")
    void testResetLife() {
        GameplayManager.resetLife();
        GameplayManager.equalLife();
        GameplayManager.equalLife();
        
        GameplayManager.resetLife();
        
        assertEquals(3, GameplayManager.getLife(), 
                    "Life should be reset to 3");
    }
    
    @Test
    @DisplayName("Test initial score")
    void testInitialScore() {
        assertEquals(0, GameplayManager.getScore(), 
                    "Initial score should be 0");
    }
    
    @Test
    @DisplayName("Test score increment")
    void testScoreIncrement() {
        int initialScore = GameplayManager.getScore();
        
        gameplayManager.plusScore(10);
        
        assertEquals(initialScore + 10, GameplayManager.getScore(), 
                    "Score should increase by 10");
    }
    
    @Test
    @DisplayName("Test score increment multiple times")
    void testScoreIncrementMultipleTimes() {
        gameplayManager.plusScore(5);
        gameplayManager.plusScore(10);
        gameplayManager.plusScore(15);
        
        assertEquals(30, GameplayManager.getScore(), 
                    "Score should be sum of all increments");
    }
    
    @Test
    @DisplayName("Test reset score")
    void testResetScore() {
        gameplayManager.plusScore(100);
        
        gameplayManager.resetScore();
        
        assertEquals(0, GameplayManager.getScore(), 
                    "Score should be reset to 0");
    }
    
    @Test
    @DisplayName("Test checkLife getter and setter")
    void testCheckLifeGetterSetter() {
        GameplayManager.setCheckLife(false);
        assertFalse(GameplayManager.isCheckLife(), 
                   "checkLife should be false");
        
        GameplayManager.setCheckLife(true);
        assertTrue(GameplayManager.isCheckLife(), 
                  "checkLife should be true");
    }
    
    @Test
    @DisplayName("Test checkLife toggle")
    void testCheckLifeToggle() {
        GameplayManager.setCheckLife(false);
        boolean initial = GameplayManager.isCheckLife();
        
        GameplayManager.setCheckLife();
        
        assertEquals(!initial, GameplayManager.isCheckLife(), 
                    "checkLife should be toggled");
    }
    
    @Test
    @DisplayName("Test checkLife is set to false after equalLife")
    void testCheckLifeSetToFalseAfterEqualLife() {
        GameplayManager.resetLife();
        GameplayManager.setCheckLife(true);
        
        GameplayManager.equalLife();
        
        assertFalse(GameplayManager.isCheckLife(), 
                   "checkLife should be false after equalLife");
    }
    
    @Test
    @DisplayName("Test reset state")
    void testResetState() {
        GameplayManager.resetLife();
        gameplayManager.plusScore(100);
        GameplayManager.setCheckLife(true);
        
        GameplayManager.resetState();
        
        assertEquals(0, GameplayManager.getLife(), 
                    "Life should be 0 after resetState");
        assertEquals(0, GameplayManager.getScore(), 
                    "Score should be 0 after resetState");
        assertFalse(GameplayManager.isCheckLife(), 
                   "checkLife should be false after resetState");
    }
    
    @Test
    @DisplayName("Test score accumulation scenario")
    void testScoreAccumulationScenario() {
        // Simulate destroying different colored bricks
        gameplayManager.plusScore(5);   // Red brick
        gameplayManager.plusScore(10);  // Orange brick
        gameplayManager.plusScore(15);  // Green brick
        gameplayManager.plusScore(20);  // Purple brick
        
        assertEquals(50, GameplayManager.getScore(), 
                    "Score should accumulate correctly");
    }
    
    @Test
    @DisplayName("Test game over scenario")
    void testGameOverScenario() {
        GameplayManager.resetLife();
        GameplayManager.setCheckLife(true);
        
        // Lose all lives
        GameplayManager.equalLife();
        GameplayManager.setCheckLife(true);
        GameplayManager.equalLife();
        GameplayManager.setCheckLife(true);
        GameplayManager.equalLife();
        
        assertEquals(0, GameplayManager.getLife(), 
                    "Life should be 0 (game over)");
    }
    
    @Test
    @DisplayName("Test life power-up scenario")
    void testLifePowerUpScenario() {
        GameplayManager.resetLife();
        int initialLife = GameplayManager.getLife();
        
        // Collect life power-up
        GameplayManager.plusLife();
        
        assertEquals(initialLife + 1, GameplayManager.getLife(), 
                    "Life should increase after power-up");
    }
}

