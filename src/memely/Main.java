/* Copyright (c) 2017-2020 MIT 6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package memely;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Console interface to the expression system.
 * 
 * <p>PS3 instructions: you are free to change this user interface class.
 */
public class Main {
    
    /**
     * Read expression and command inputs from the console and output results.
     * An empty input terminates the program.
     * @param args unused
     * @throws IOException if there is an error reading the input
     */
    public static void main(String[] args) throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Optional<Expression> currentExpression = Optional.empty();

        while (true) {
            System.out.print("> ");
            final String inputLine = in.readLine();
            
            if (inputLine.isEmpty()) {
                continue;
            }

            try {
                if (inputLine.equals(SIZE_COMMAND)) {
                    System.out.println(Commands.size(currentExpression.get()));
                    
                } else if (inputLine.equals(IMAGE_COMMAND)) {
                    final Expression expr = currentExpression.get();
                    showInWindow(expr.toString(), Commands.image(expr));
                    // ... but don't change currentExpression
                    
                } else if (inputLine.equals(EXIT_COMMAND)) {
                    System.exit(0); // exits the program
                    
                } else {
                    final Expression result = Expression.parse(inputLine);
                    currentExpression = Optional.of(result);
                    System.out.println(result);
                }
                
            } catch (NoSuchElementException nse) {
                // currentExpression was empty
                System.out.println("must enter an expression before using this command");
            } catch (RuntimeException re) {
                System.out.println(re.getClass().getName() + ": " + re.getMessage());
            }
        }
    }
    
    private static final String SIZE_COMMAND = "!size";
    private static final String IMAGE_COMMAND = "!image";
    private static final String EXIT_COMMAND = "!quit";

    /**
     * Display an image in a new window exactly sized to fit the image.
     * @param windowTitle title for window's titlebar
     * @param image image to display
     */
    public static void showInWindow(final String windowTitle, final BufferedImage image) {
        JFrame frame = new JFrame(windowTitle);
        frame.getContentPane().setBackground(Color.GRAY);
        frame.add(new JLabel(new ImageIcon(image)));
        frame.pack();
        frame.setVisible(true);
    }

}
