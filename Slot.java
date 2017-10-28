import javax.swing.JTextArea;
import javax.swing.JTextPane;


public class Slot
{
    private String word;

    private char[] letters;

    private JTextPane slotArea;


    /**
     * Constructor
     * 
     * @param s
     *            word to check against
     * @param area
     *            text area for slots
     */
    public Slot( String s, JTextPane area )
    {
        word = s;
        letters = new char[s.length()];
        for ( int i = 0; i < s.length(); i++ )
        {
            letters[i] = '_';
        }
        slotArea = area;
        slotArea.setText( formatLetters() );
    }


    /**
     * 
     * Updates slots if character is guessed correctly. Checks if game is won
     * yet.
     * 
     * @param c
     *            guessed character
     * @return true if game is won
     */
    public Boolean rightGuess( char c )
    {
        for ( int i = 0; i < word.length(); i++ )
        {
            if ( word.charAt( i ) == c )
            {
                letters[i] = c;
            }
        }
        display();

        if ( new String( letters ).equals( word ) )
        {
            gameOver();
            return true;
        }
        return false;
    }


    /**
     * 
     * Formats letters array to string.
     * 
     * @return formatted string
     */
    public String formatLetters()
    {
        String s = new String( "" );
        for ( int i = 0; i < letters.length; i++ )
        {
            s += letters[i] + " ";
        }
        return s;

    }


    /**
     * 
     * Returns word.
     * 
     * @return word
     */
    public String getWord()
    {
        return word;
    }


    /**
     * 
     * Returns letters.
     * 
     * @return letters
     */
    public char[] getLetters()
    {
        return letters;
    }


    /**
     * 
     * Updates slot display.
     */
    public void display()
    {
        slotArea.setText( formatLetters() );
    }


    /**
     * 
     * Triggers game over display
     */
    public void gameOver()
    {
        slotArea.setText( word );
    }
}
