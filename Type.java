
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class Type extends JPanel implements ActionListener, Runnable
{
    protected JFormattedTextField textField;

    protected JTextArea textArea;

    protected JTextArea wrongArea;

    protected JTextPane slotArea;

    private final static String newline = "\n";

    private WrongGuesses mw;

    private Slot slot;

    private static String word;

    private Set<Character> guesses;

    private Image img;

    private JLabel picLabel;


    /**
     * Constructor takes a word and generates a GUI around it
     * 
     * @param str
     *            word
     */
    public Type( String str )
    {
        super( new GridBagLayout() );

        textField = new JFormattedTextField();
        textField.addActionListener( this );

        textArea = new JTextArea( 5, 20 );
        textArea.setEditable( false );
        JScrollPane scrollPane = new JScrollPane( textArea );

        // Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;
        add( textField, c );

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add( scrollPane, c );
        word = str;

        // slot textarea
        slotArea = new JTextPane();
        slotArea.setEditable( false );
        add( slotArea, c );
        slot = new Slot( str, slotArea );

        // hangman panel
        img = new ImageIcon( "./images/hang00.png" ).getImage();
        picLabel = new JLabel( new ImageIcon( img ) );
        add( picLabel, c );

        // wrong guesses textarea
        wrongArea = new JTextArea( 5, 20 );
        wrongArea.setEditable( false );
        add( wrongArea, c );
        mw = new WrongGuesses( picLabel, wrongArea );

        guesses = new HashSet<Character>();

        StyledDocument doc = slotArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment( center, StyleConstants.ALIGN_CENTER );
        doc.setParagraphAttributes( 0, doc.getLength(), center, false );
    }


    /**
     * Event listener for entering guess
     */
    public void actionPerformed( ActionEvent evt )
    {
        String text = textField.getText();
        if ( text.length() > 1 || !Character.isLetter( text.charAt( 0 ) ) )
        {
            textArea.append( "\nNot a valid character" );
        }
        else
        {
            processGuess( text.charAt( 0 ) );
        }
        textField.selectAll();
        textArea.setCaretPosition( textArea.getDocument().getLength() );
    }


    /**
     * 
     * Processes guessed character to see if it is part of the word. Handles
     * game-over scenarios
     * 
     * @param c
     *            guessed character
     */
    public void processGuess( char c )
    {
        c = Character.toLowerCase( c );
        if ( !guesses.contains( c ) )
        {
            if ( word.indexOf( c ) != -1 )
            {
                textArea.append( "\nFound the letter " + c );
                if ( slot.rightGuess( c ) )
                {
                    textArea.append( "\nYou win!" );
                }
            }
            else
            {
                textArea.append( "\nDid not find the letter " + c );
                if ( mw.wrongGuess( c ) )
                {
                    slot.gameOver();
                    textArea.append( "\nYou lose!" );
                }
            }
            guesses.add( c );

        }
        else
        {
            textArea.append( "\nAlready guessed the letter " + c );
        }
    }


    /**
     * 
     * Gets word
     * 
     * @return word
     */
    public String getWord()
    {
        return word;
    }


    /**
     * Gets guesses
     * 
     * @return guesses
     */
    public Set<Character> getGuesses()
    {
        return guesses;
    }

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event dispatch thread.
     */
    private static void createAndShowGUI( String s )
    {
        JFrame frame = new JFrame( s );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        frame.add( new Type( word ) );

        frame.pack();
        frame.setVisible( true );
    }


    /**
     * 
     * Creates text field
     * 
     * @param args
     *            Not used
     */
    public static void main( String[] args )
    {
        javax.swing.SwingUtilities.invokeLater( new Runnable()
        {
            public void run()
            {
                createAndShowGUI( "Guess a letter" );
            }
        } );
    }


    /**
     * Creates text field
     */
    @Override
    public void run()
    {
        createAndShowGUI( "Guess a letter" );

    }
}
