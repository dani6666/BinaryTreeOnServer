import java.io.*;
import java.net.*;

public class ServerClient
{
    Socket socket = null;
    PrintWriter outputWriter = null;
    BufferedReader inputReader = null;

    ServerClient()
    {
        try 
        {
            socket = new Socket("localhost", 4444);
            outputWriter = new PrintWriter(socket.getOutputStream(), true);
            inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch(UnknownHostException e) 
        {
           System.out.println("Unknown host: localhost"); System.exit(1);
        }
        catch(IOException e) 
        {
           System.out.println("No I/O"); System.exit(1);
        }
    }

    public String sendOnServer(String treeType, String actionType, String valueString)
    {
        outputWriter.println(treeType);
        outputWriter.println(actionType);
        outputWriter.println(valueString);

        try
        {
            return inputReader.readLine();
        }
        catch(IOException ex)
        {
            return "No output";
        }
    }
}