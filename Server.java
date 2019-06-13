import java.util.Hashtable;
import java.io.*;
import java.net.*;

public class Server
{
    ServerSocket server = null;
    Socket client = null;
    BufferedReader inputReader = null;
    PrintWriter outputWriter = null;
    Hashtable<String, Tree> forest;

    Server() 
    { 
        int executionProgress = 0;
        String treeType="";
        String actionType;
        Object value=null;
        String valueString;
        forest = new Hashtable<>();
        forest.put(TreeType.Int, new Tree<Integer>());
        forest.put(TreeType.Double, new Tree<Double>());
        forest.put(TreeType.String, new Tree<String>());


        try 
        {
            server = new ServerSocket(4444);
            executionProgress++;

            client = server.accept();
            inputReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outputWriter = new PrintWriter(client.getOutputStream(), true);
            executionProgress++;

            while(true)
            {
                treeType = inputReader.readLine();
                actionType = inputReader.readLine();
                valueString = inputReader.readLine();

                if(actionType.compareTo(ActionType.Draw) != 0)
                {
                    try
                    {
                        switch(treeType)
                        {
                            case TreeType.Int:
                                value = Integer.parseInt(valueString);
                            break;
        
                            case TreeType.Double:
                                value = Double.parseDouble(valueString);
                            break;
    
                            default:
                                value = valueString;
                            break;
                        }
                    }
                    catch(NumberFormatException ex)
                    {
                        outputWriter.println("Inserted value must be a numeric value");
                        continue;
                    }
                }
                
                
                switch(actionType)
                {
                    case ActionType.Search:
                        if(forest.get(treeType).search(value))
                        {
                            outputWriter.println("Item " + valueString + " is present in the tree");
                        }
                        else
                        {
                            outputWriter.println("Item " + valueString + " is not present in the tree");
                        }
                    break;
        
                    case ActionType.Insert:
                        if(forest.get(treeType).insert(value))
                        {
                            outputWriter.println("Item " + valueString + " has been inserted into the tree");
                        }
                        else
                        {
                            outputWriter.println("Insertion failure. Item " + valueString + " already exists in the tree");
                        }
                    break;
        
                    case ActionType.Delete:
                        if(forest.get(treeType).delete(value))
                        {
                            outputWriter.println("Item " + valueString + " has been deleted from the tree");
                        }
                        else
                        {
                            outputWriter.println("Daletion failure. Item " + valueString + " doesn't exists in the tree");
                        }
                    break;
        
                    default://draw
                        outputWriter.println(forest.get(treeType).draw());
                    break;
                }
            }
        } 
        catch (IOException e) 
        {
            switch(executionProgress)
            {
                case 0:
                System.out.println("Could not listen on port 4444"); 
                System.exit(-1);
                break;

                case 1:
                System.out.println("Accept failed: 4444"); 
                System.exit(-1);
                break;

                case 2:
                System.out.println("Read failed"); 
                System.exit(-1);
                break;
            }
        }
    }

    protected void closeConnection() 
    {
        try 
        {
            inputReader.close();
            outputWriter.close();
            client.close();
            server.close();
        } 
        catch (IOException e) 
        {
            System.out.println("Could not close."); 
            System.exit(-1);
        }
    }
}
