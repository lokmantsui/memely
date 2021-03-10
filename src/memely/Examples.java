/* Copyright (c) 2017-2020 MIT 6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package memely;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class Examples {

    /**
     * Display some image memes in windows.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            Main.showInWindow("one does not simply", oneDoesNotSimply());
            Main.showInWindow("tech support", techSupport());
            examinePixelsOfImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return an image containing the One Does Not Simply meme (with Boromir from Lord of the Rings)
     * @throws IOException if any of the files it depends on are missing or unable to be read as images
     */
    private static BufferedImage oneDoesNotSimply() throws IOException {
        final BufferedImage boromir = ImageIO.read(new File("img/boromir.jpg"));
        System.out.println("boromir.jpg " + boromir.getWidth() + "x" + boromir.getHeight());
        //showInWindow("boromir", boromir);

        final BufferedImage topCaption = convertStringToImage("ONE DOES NOT SIMPLY");
        System.out.println("top caption " + topCaption.getWidth() + "x" + topCaption.getHeight());
        //showInWindow("top caption", topCaption);
        
        final BufferedImage bottomCaption = convertStringToImage("WALK INTO MORDOR");
        System.out.println("bottom caption " + bottomCaption.getWidth() + "x" + topCaption.getHeight());
        //showInWindow("bottom caption", bottomCaption);

        final int upperLeftX = 0;
        final int upperLeftY = 0;
        final int outputImageWidth = 300;
        final int outputImageHeight = 200;
        final int outputCaptionHeight = outputImageHeight / 4;

        final BufferedImage output = new BufferedImage(outputImageWidth, outputImageHeight, BufferedImage.TYPE_4BYTE_ABGR);
        final Graphics graphics = output.getGraphics();
        
        graphics.drawImage(boromir, 
                           upperLeftX, upperLeftY,
                           outputImageWidth, outputImageHeight, 
                           NO_OBSERVER_NEEDED);
        graphics.drawImage(topCaption, 
                           upperLeftX, upperLeftY,
                           outputImageWidth, outputCaptionHeight, 
                           NO_OBSERVER_NEEDED);
        graphics.drawImage(bottomCaption,
                           upperLeftX, outputImageHeight - outputCaptionHeight,
                           outputImageWidth, outputCaptionHeight,
                           NO_OBSERVER_NEEDED);
        
        return output;
    }

    private static final ImageObserver NO_OBSERVER_NEEDED = null;
    
    /**
     * @return an image containing the What My Friends Think I Do meme, for Tech Support
     * @throws IOException if any of the files it depends on are missing or unable to be read as images
     */
    private static BufferedImage techSupport() throws IOException {
        final BufferedImage title = convertStringToImage("          TECH SUPPORT          ");
        
        final BufferedImage blackBackground = ImageIO.read(new File("img/black.png"));
        
        final BufferedImage image1 = ImageIO.read(new File("img/tech1.png"));        
        final BufferedImage caption1 = convertStringToImage("     What my friends think I do     ");
        
        final BufferedImage image2 = ImageIO.read(new File("img/tech2.png"));
        final BufferedImage caption2 = convertStringToImage("     What my mom thinks I do     ");
        
        final BufferedImage image3 = ImageIO.read(new File("img/tech3.png"));
        final BufferedImage caption3 = convertStringToImage("     What society thinks I do     ");
        
        final BufferedImage image4 = ImageIO.read(new File("img/tech4.png"));
        final BufferedImage caption4 = convertStringToImage("     What my boss thinks I do     ");
        
        final BufferedImage image5 = ImageIO.read(new File("img/tech5.png"));
        final BufferedImage caption5 = convertStringToImage("     What I think I do     ");
        
        final BufferedImage image6 = ImageIO.read(new File("img/tech6.png"));
        final BufferedImage caption6 = convertStringToImage("     What I actually do     ");
        
        final BufferedImage output = new BufferedImage(600, 450, BufferedImage.TYPE_4BYTE_ABGR);
        final Graphics graphics = output.getGraphics();

        final int titleHeight = 50;
        final int panelWidth = 200;
        final int panelHeight = 150;
        final int captionHeight = 25;
        final int blackBarBelowCaptionHeight = 25;
        final int rowHeight = panelHeight + captionHeight + blackBarBelowCaptionHeight;
        
        final int titleUpperLeftX = 0;
        final int column1UpperLeftX = 0;
        final int column2UpperLeftX = column1UpperLeftX + panelWidth;
        final int column3UpperLeftX = column2UpperLeftX + panelWidth;
        final int outputImageWidth = column3UpperLeftX + panelWidth;

        final int titleUpperLeftY = 0;
        final int row1UpperLeftY = titleUpperLeftY + titleHeight;
        final int row2UpperLeftY = row1UpperLeftY + rowHeight;
        final int outputImageHeight = row2UpperLeftY + rowHeight;
        

        graphics.drawImage(blackBackground, 
                           titleUpperLeftX, titleUpperLeftY, 
                           outputImageWidth, outputImageHeight, 
                           NO_OBSERVER_NEEDED);
        graphics.drawImage(title,
                           titleUpperLeftX, titleUpperLeftY,
                           outputImageWidth, titleHeight,
                           NO_OBSERVER_NEEDED);

        graphics.drawImage(image1,
                           column1UpperLeftX, row1UpperLeftY,
                           panelWidth, panelHeight,
                           NO_OBSERVER_NEEDED);
        graphics.drawImage(caption1,
                           column1UpperLeftX, row1UpperLeftY + panelHeight,
                           panelWidth, captionHeight,
                           NO_OBSERVER_NEEDED);

        graphics.drawImage(image2,
                           column2UpperLeftX, row1UpperLeftY,
                           panelWidth, panelHeight,
                           NO_OBSERVER_NEEDED);
        graphics.drawImage(caption2,
                           column2UpperLeftX, row1UpperLeftY + panelHeight,
                           panelWidth, captionHeight,
                           NO_OBSERVER_NEEDED);

        graphics.drawImage(image3,
                           column3UpperLeftX, row1UpperLeftY,
                           panelWidth, panelHeight,
                           NO_OBSERVER_NEEDED);
        graphics.drawImage(caption3,
                           column3UpperLeftX, row1UpperLeftY + panelHeight,
                           panelWidth, captionHeight,
                           NO_OBSERVER_NEEDED);

        graphics.drawImage(image4,
                           column1UpperLeftX, row2UpperLeftY,
                           panelWidth, panelHeight,
                           NO_OBSERVER_NEEDED);
        graphics.drawImage(caption4,
                           column1UpperLeftX, row2UpperLeftY + panelHeight,
                           panelWidth, captionHeight,
                           NO_OBSERVER_NEEDED);

        graphics.drawImage(image5,
                           column2UpperLeftX, row2UpperLeftY,
                           panelWidth, panelHeight,
                           NO_OBSERVER_NEEDED);
        graphics.drawImage(caption5,
                           column2UpperLeftX, row2UpperLeftY + panelHeight,
                           panelWidth, captionHeight,
                           NO_OBSERVER_NEEDED);

        graphics.drawImage(image6,
                           column3UpperLeftX, row2UpperLeftY,
                           panelWidth, panelHeight,
                           NO_OBSERVER_NEEDED);
        graphics.drawImage(caption6,
                           column3UpperLeftX, row2UpperLeftY + panelHeight,
                           panelWidth, captionHeight,
                           NO_OBSERVER_NEEDED);
                
        return output;
    }

    /**
     * @param string a string representing a single line of text (newlines in the string are ignored)
     * @return an image that renders the string as text using the default system font,
     *         cropped as tightly around the text as possible
     */
    public static BufferedImage convertStringToImage(final String string) {
        // make a tiny 1x1 image at first so that we can get a Graphics object, 
        // which we need to compute the width and height of the text
        BufferedImage output = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics = (Graphics2D) output.getGraphics();
        
        // use the system font, but make it 96-point bold
        final Font defaultFont = graphics.getFont();
        final int fontSizeInPoints = 96;
        final Font font = new Font(defaultFont.getFontName(), Font.BOLD, fontSizeInPoints);
        graphics.setFont(font);
        
        // get the bounding box of the string, rounding up the coordinates
        Rectangle2D rectangle = font.getStringBounds(string, graphics.getFontRenderContext());
        //System.out.println(caption + " has rectangle " + rectangle);
        final int width =   (int) Math.ceil(rectangle.getWidth());
        final int height =  (int) Math.ceil(rectangle.getHeight());
        final int xOffset = (int) Math.ceil(rectangle.getX());
        final int yOffset = (int) Math.ceil(rectangle.getY());
        
        // now that we know the text's width and height,
        // recreate the image big enough to fit
        output = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        graphics = (Graphics2D) output.getGraphics();
        
        // render the text in the font we chose, and white, and antialiased
        graphics.setFont(font);
        graphics.setColor(Color.WHITE);
        graphics.setRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
        
        // finally draw the string. drawString's x,y expects the left end of the text *baseline* (instead
        // of the upper left corner of its bounding box), but xOffset and yOffset give us that.
        graphics.drawString(string, -xOffset, -yOffset);
        return output;
    }
    
    private static void examinePixelsOfImage() throws IOException {
        final BufferedImage blackImage = ImageIO.read(new File("img/black.png"));
        final BufferedImage whiteImage = ImageIO.read(new File("img/white.png"));

        final int outputImageWidth = 10;
        final int outputImageHeight = 10;
        final int upperLeftX = 0;
        final int upperLeftY = 0;
        final int halfWidth = outputImageWidth/2;

        final BufferedImage output = new BufferedImage(outputImageWidth, outputImageHeight, BufferedImage.TYPE_4BYTE_ABGR);
        final Graphics graphics = output.getGraphics();
        
        graphics.drawImage(blackImage, 
                           upperLeftX, upperLeftY,
                           halfWidth, outputImageHeight, 
                           NO_OBSERVER_NEEDED);
        graphics.drawImage(whiteImage, 
                           halfWidth, upperLeftY,
                           outputImageWidth, outputImageHeight, 
                           NO_OBSERVER_NEEDED);

        final int abgrBlackPixel = 0xFF_00_00_00; // alpha=100%, blue=0%, green=0%, red=0%
        final int abgrWhitePixel = 0xFF_FF_FF_FF; // alpha=100%, blue=100%, green=100%, red=100%
        
        final int pixelFromLeftHalf  = output.getRGB(halfWidth/2, outputImageHeight/2);
        final int pixelFromRightHalf = output.getRGB(halfWidth + halfWidth/2, outputImageHeight/2);
        
        System.out.println("pixel from left (black) half:  " 
                            + String.format("%08X", pixelFromLeftHalf)
                            + "  is it black? " + (pixelFromLeftHalf == abgrBlackPixel));
        System.out.println("pixel from right (white) half: " 
                            + String.format("%08X", pixelFromRightHalf)
                            + "  is it white? " + (pixelFromRightHalf == abgrWhitePixel));
    }
}
