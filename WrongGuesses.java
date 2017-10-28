import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JTextArea;


public class WrongGuesses
{

    private Man man;

    private final int MAX_LENGTH = 20;

    private JTextArea wrongArea;


    /**
     * Constructor
     * 
     * @param label
     *            the image pane for the man
     * @param area
     *            the text area for the wrong guesses
     */
    public WrongGuesses( JLabel label, JTextArea area )
    {
        man = new Man( label );
        wrongArea = area;
    }


    /**
     * 
     * Updates that guess was wrong. Advances state of hanging man.
     * 
     * @param c
     *            guessed character
     * @return t/f if all guesses are depleted
     */
    public Boolean wrongGuess( char c )
    {

        wrongArea.append( "    " + c );
        return man.advanceState();
    }


    /**
     * Gets text area
     * @return wrongArea
     */
    public JTextArea getWrongArea()
    {
        return wrongArea;
    }

}