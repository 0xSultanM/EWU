package Logic.Login;

import java.io.BufferedReader;
/*Buffered reader cannot read directly from files but it can read whole blocks of characters at a time */
import java.io.FileReader; 
/*File reader can read directly from files but it only reads one character at a time*/
import java.io.IOException;
/*Is used here to help process when an account being signed into does not exist in files*/
import java.util.ArrayList;
/*Array.*/



public class CheckFromFiles
{
    public static boolean loginAccount(String username, String password) 
    {
        ArrayList<String[]> Users = loadUsers("users.txt");
        /*Method loadUsers is used to read a file called "users.txt".*/

        boolean found = false;

        for(String[] pair: Users)
        {
            if(pair[0].equals(username) && pair[1].equals(password))
            {
                found = true;
                break;
            }
        }
        /*Advanced for loop in essence, looks through each element in the users list.*/
        /*pair[0] is username & pair[1] is password.*/

        return found;
        /*Returns true if account found, false otherwise*/
    }

    static ArrayList<String[]> loadUsers(String filename)
    {
        /*Loads users from a file and returns a list of [username, password] pairs*/
        ArrayList<String[]> list = new ArrayList<>();
        /*Empty list used to store each username-password pair (identical to multidimensional array)*/

        /*Opens file to read*/
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) 
        {
            String line;

            /*Reads each line of the file till no lines remain(null)*/
            while((line = reader.readLine()) != null)
            {
                String[] parts = line.split(",",2);
                /*Splits line at first comma(,) into 2 parts(2) such as "losing,Mymind" becomes ["losing","Mymind"]*/

                if (parts.length == 2) 
                {
                    list.add(new String[] { parts[0].trim(), parts[1] });
                }   
                /*Ensures exactly two parts are found, trims username and adds to list*/
            }
        }
        catch (IOException e) 
        {
           System.out.println("Error: " + e.getMessage());
        }
        /*Cool tech, will show the actual error message*/

        return list;
    }
}
