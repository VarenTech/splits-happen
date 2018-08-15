/*
 * Created 2018-08-15 in response to https://github.com/VarenTech/splits-happen
 */
package com.varentech.bowling.view;

import java.io.Console;

/**
 * In theory, we could make view content actively or passively available to various presentation
 * technologies here. In practice, it's all about the simple printf.
 * 
 * @author dcbyron
 */
public class DisplayRouter {
 
    public static void writeToConsole(String score) {
        Console console = System.console();
        if (console != null) {
            console.printf("Score: %s", score);
        } else {
            System.out.printf("Score: %s", score);
        }        
    }
    
}
