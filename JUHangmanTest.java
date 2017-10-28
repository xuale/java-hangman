import org.junit.*;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import junit.framework.JUnit4TestAdapter;


public class JUHangmanTest
{
    private String word = "abc";


    // --Test Slot
    /**
     * Checks if Slot constructor works
     */
    @Test
    public void slotConstructor()
    {
        Slot s = new Slot( word, new JTextPane() );
        assertEquals( "Slot1 should be " + word, s.getWord(), word );
    }


    /**
     * 
     * Checks if Slot can properly update letters after a correct guess is made
     */
    @Test
    public void slotRightGuess()
    {
        Slot s = new Slot( word, new JTextPane() );
        char[] c = { 'a', '_', '_' };
        s.rightGuess( 'a' );
        for ( int i = 0; i < 3; i++ )
        {
            assertEquals( "Character should be " + c[i], c[i], s.getLetters()[i] );
        }

    }


    /**
     * Checks if Slot can format the letters into a nice string
     */
    @Test
    public void slotFormatLetters()
    {
        String formatted = new String( "a b c " );
        Slot s = new Slot( word, new JTextPane() );
        s.rightGuess( 'a' );
        s.rightGuess( 'b' );
        s.rightGuess( 'c' );

        assertEquals( "Formatted should be " + formatted, formatted, s.formatLetters() );
    }


    // --Test Type
    /**
     * Checks if Type constructor properly saves word.
     * 
     */
    @Test
    public void typeConstructor()
    {
        Type t = new Type( word );
        assertEquals( "Word should be " + word, word, t.getWord() );
    }


    /**
     * Checks if Type can properly process a guess Handles non-letters,
     * uppercase, multiple characters, and duplicate guesses
     */
    @Test
    public void typeProcessGuess()
    {
        Type t = new Type( word );
        t.processGuess( 'A' );
        // multiple characters and non-letters handled by event listener, no
        // practical way to
        // unit test
        t.processGuess( 'b' );
        t.processGuess( 'b' );
        Set<Character> s = new HashSet<Character>();
        s.add( 'a' );
        s.add( 'b' );
        assertTrue( "Guesses incorrect", t.getGuesses().equals( s ) );

    }


    // --Test WrongGuesses
    /**
     * Checks if constructor works
     */
    @Test
    public void wrongGuessesConstructor()
    {
        WrongGuesses wg = new WrongGuesses( new JLabel(), new JTextArea() );
        assertTrue( "Constructor doesn't work", !wg.getWrongArea().equals( null ) );
    }


    /**
     * Checks if WrongGuesses properly processes the wrong guess
     */
    @Test
    public void wrongGuessesProcess()
    {
        WrongGuesses wg = new WrongGuesses( new JLabel(), new JTextArea() );
        wg.wrongGuess( 'd' );
        assertTrue( "wrongArea text should be " + wg.getWrongArea().getText(),
            wg.getWrongArea().getText().equals( new String( "    d" ) ) );
    }


    // --Test Man
    /**
     * manConstructor: constructs man and compares hang_state value
     */
    @Test
    public void manConstructor()
    {
        Man m = new Man( new JLabel() );
        assertTrue( "Constructor doesn't work", m.getHangState() == 0 );
    }


    /**
     * Checks if state is truly advanced and if file pathway is correctly
     * updated
     */
    @Test
    public void manAdvanceState()
    {
        Man m = new Man( new JLabel() );
        m.advanceState();
        assertTrue( "hang_state should be " + 1, m.getHangState() == 1 );
        String pathway = new String( "./images/hang01.png" );
        assertTrue( "pathway should be " + pathway, m.getFilePathway().equals( pathway ) );
    }

}