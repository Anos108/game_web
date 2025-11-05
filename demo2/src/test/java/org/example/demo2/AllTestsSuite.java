package org.example.demo2;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * Test Suite to run all tests together
 */
@Suite
@SuiteDisplayName("Arkanoid Game - All Tests Suite")
@SelectClasses({
    BallTest.class,
    PaddleTest.class,
    GameplayManagerTest.class,
    ConfigTest.class,
    WallTest.class,
    BrickTest.class
})
public class AllTestsSuite {
    // This class remains empty, it is used only as a holder for the above annotations
}

