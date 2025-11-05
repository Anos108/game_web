package org.example.demo2;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BrickTest {
    @Test
    @DisplayName("Brick Orange should have 1 hp")
    void testBrickOrangeHealth(){
        Bricks.Brick brick = new Bricks.BrickOrange(100, 100, 64, 32);
        assertEquals(1, brick.getHealth(), "Brick health should be 1");
    }
    @Test
    @DisplayName("Brick Red should have 2 hp")
    void testBrickRedHealth(){
        Bricks.Brick brick = new Bricks.BrickRed(100, 100, 64, 32);
        assertEquals(2, brick.getHealth(), "Brick health should be 2");
    }
    @Test
    @DisplayName("Brick Green should have 4 hp")
    void testBrickGreenHealth(){
        Bricks.Brick brick = new Bricks.BrickGreen(100, 100, 64, 32);
        assertEquals(4, brick.getHealth(), "Brick health should be 4");
    }
    @Test
    @DisplayName("Brick Purple should have 6 hp")
    void testBrickPurpleHealth(){
        Bricks.Brick brick = new Bricks.BrickPurple(100, 100, 64, 32);
        assertEquals(6, brick.getHealth(), "Brick health should be 6");
    }
    @Test
    @DisplayName("Test brick destruction")
    void testBrickDestruction(){
        Bricks.Brick brickOrange = new Bricks.BrickOrange(100,100,64,32);
        brickOrange.healthDown();
        assertEquals(0, brickOrange.getHealth(), "Brick health should be 0");
    }
    @Test
    @DisplayName("Brick with many hp should not be destroyed after minus 1 hp")
    void testBrickHealthDecrease(){
        Bricks.Brick brickRed = new Bricks.BrickRed(100,100,64,32);
        brickRed.healthDown();
        assertEquals(1, brickRed.getHealth(), "Brick health should be 1");
        brickRed.healthDown();
        assertEquals(0, brickRed.getHealth(), "Brick health should be 0");
    }
    @Test
    @DisplayName("Brick should have multiple score")
    void testBrickScore(){
        Bricks.Brick brickOrange = new Bricks.BrickOrange(100,100,64,32);
        Bricks.Brick brickRed = new Bricks.BrickRed(100,100,64,32);
        Bricks.Brick brickGreen = new Bricks.BrickGreen(100,100,64,32);
        Bricks.Brick brickPurple = new Bricks.BrickPurple(100,100,64,32);
        assertEquals(100, brickOrange.getScore(), "Brick score should be 100");
        assertEquals(150, brickRed.getScore(), "Brick score should be 150");
        assertEquals(300, brickGreen.getScore(), "Brick score should be 300");
        assertEquals(600, brickPurple.getScore(), "Brick score should be 600");
    }
}
