import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Hangman
{
    private final static int dictionary_size = 19912;


    /**
     * 
     * Generates Type object. Picks random word from dictionary
     * 
     * @param args
     *            Not used
     * @throws FileNotFoundException
     *             Dictionary file not found
     * @throws IOException
     *             Input/output exception
     */
    public static void main( String[] args ) throws FileNotFoundException, IOException
    {
        String word = new String();
        int random_idx = (int)( Math.random() * dictionary_size );
        try (BufferedReader br = new BufferedReader( new FileReader( "dictionary.txt" ) ))
        {
            word = br.readLine();
            int i = 0;
            while ( i < random_idx )
            {
                word = br.readLine();
                i++;
            }
        }
        JFrame frame = new JFrame();
        Type type = new Type( word );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        frame.getContentPane().add( type );

        frame.setSize( 400, 550 );

        frame.setVisible( true );

    }
}