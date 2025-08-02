package com.sample.qwords;

import com.sample.qwords.model.GameStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class GameStatusTest {

    @Test
    public void testGameStatusValues() {
        GameStatus[] expectedValues = {
            GameStatus.NOTSTARTED,
            GameStatus.INPROGRESS,
            GameStatus.SUCCESS,
            GameStatus.FAILED
        };
        
        GameStatus[] actualValues = GameStatus.values();
        Assertions.assertArrayEquals(expectedValues, actualValues);
    }

    @Test
    public void testGameStatusValueOf() {
        Assertions.assertEquals(GameStatus.NOTSTARTED, GameStatus.valueOf("NOTSTARTED"));
        Assertions.assertEquals(GameStatus.INPROGRESS, GameStatus.valueOf("INPROGRESS"));
        Assertions.assertEquals(GameStatus.SUCCESS, GameStatus.valueOf("SUCCESS"));
        Assertions.assertEquals(GameStatus.FAILED, GameStatus.valueOf("FAILED"));
    }

    @Test
    public void testGameStatusCount() {
        Assertions.assertEquals(4, GameStatus.values().length);
    }

    @Test
    public void testGameStatusToString() {
        Assertions.assertEquals("NOTSTARTED", GameStatus.NOTSTARTED.toString());
        Assertions.assertEquals("INPROGRESS", GameStatus.INPROGRESS.toString());
        Assertions.assertEquals("SUCCESS", GameStatus.SUCCESS.toString());
        Assertions.assertEquals("FAILED", GameStatus.FAILED.toString());
    }

    @Test
    public void testInvalidGameStatusValueOf() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            GameStatus.valueOf("INVALID_STATUS");
        });
    }
}