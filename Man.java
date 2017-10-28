import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Man extends JPanel
{
    private int hang_state;

    private static Image man_display;

    private JLabel picLabel;

    private String filePathway;


    /**
     * Constructor
     * 
     * @param label
     *            the image pane for the man
     */
    public Man( JLabel label )
    {
        hang_state = 0;
        filePathway = new String( "./images/hang00.png" );
        man_display = new ImageIcon( filePathway ).getImage();
        Image newimg = man_display.getScaledInstance( 120, 120, java.awt.Image.SCALE_SMOOTH );
        picLabel = label;
        picLabel.setIcon( new ImageIcon( newimg ) );
    }


    /**
     * 
     * Paints current man image. If game is over, trigger game over animation.
     * 
     * @return true if game is over
     */
    public Boolean drawMan()
    {
        Image newimg = man_display.getScaledInstance( 120, 120, java.awt.Image.SCALE_SMOOTH );
        picLabel.setIcon( new ImageIcon( newimg ) );
        if ( hang_state > 5 )
        {
            Image gameOver = new ImageIcon( "./images/gameover.gif" ).getImage();
            newimg = gameOver.getScaledInstance( 120, 120, java.awt.Image.SCALE_DEFAULT );
            picLabel.setIcon( new ImageIcon( newimg ) );
            return true;
        }
        return false;
    }


    /**
     * 
     * Advances the state of the hanging man by loading the next image file.
     * 
     * @return true if game is over
     */
    public Boolean advanceState()
    {
        hang_state++;
        filePathway = new String( "./images/hang0" + hang_state + ".png" );
        man_display = new ImageIcon( filePathway ).getImage();
        return drawMan();
    }


    /**
     * 
     * Gets hang_state
     * 
     * @return hang_state
     */
    public int getHangState()
    {
        return hang_state;
    }


    /**
     * Gets file pathway
     * 
     * @return filePathway
     */
    public String getFilePathway()
    {
        return filePathway;
    }

}