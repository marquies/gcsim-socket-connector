import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by Patrick Steinert on 03.04.22.
 */
public class SocketClient {
    public static void main(String[] args) {
        String server = "cudaknecht.fritz.box";
        int port = 4711;

        System.out.println( "Loading contents of URL: " + server + " Port: " + port);

        try
        {
            // Connect to the server
            Socket socket = new Socket( server, port );

            // Create input and output streams to read from and write to the server
            PrintStream out = new PrintStream( socket.getOutputStream() );
            BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );

            // Follow the HTTP protocol of GET <path> HTTP/1.0 followed by an empty line
            out.print( "Query by Example: post_100.wapo.json" );
            //out.println();

            // Read data from the server until we finish reading the document
            String line = in.readLine();
            while( line != null )
            {
                System.out.println( line );
                line = in.readLine();
            }

            // Close our streams
            in.close();
            out.close();
            socket.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
