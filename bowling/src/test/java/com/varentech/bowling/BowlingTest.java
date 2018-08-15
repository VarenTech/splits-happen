/*
 * Created 2018-08-15 in response to https://github.com/VarenTech/splits-happen
 */
package com.varentech.bowling;

import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

/**
 *
 * @author dcbyron
 */
public class BowlingTest {

    private final Map<String, String> testCases = new HashMap<>();
    private final Map<String, String> expectedResults = new HashMap<>();
    
    public BowlingTest() {
        testCases.put("case1", "XXXXXXXXXXXX");
        testCases.put("case2", "9-9-9-9-9-9-9-9-9-9-");
        testCases.put("case3", "5/5/5/5/5/5/5/5/5/5/5");
        testCases.put("case4", "X7/9-X-88/-6XXX81");
        expectedResults.put("case1", "300");
        expectedResults.put("case2", "90");
        expectedResults.put("case3", "150");
        expectedResults.put("case4", "167");
    }

    /**
     * Test of playGame method, of class Bowling.
     */
    @org.junit.Test
    public void testPlayGame() {
        for (String s : testCases.keySet()) {
            String[] args = { testCases.get(s) };
            Bowling instance = new Bowling();
            String result = instance.playGame(args);
            System.out.println("playGame for " + s + ": " + result);
            assertEquals(expectedResults.get(s), result);
        }
    }
    
}
