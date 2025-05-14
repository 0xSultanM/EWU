package Logic.Login;

import java.io.*;
/*File writer is used to write in files directly*/
import java.io.BufferedReader;
/*Buffered reader cannot read directly from files but it can read whole blocks of characters at a time*/
import java.io.FileReader; 
/*File reader can read directly from files but it only reads one character at a time*/
import java.io.IOException;

public class Account_Create
{
    public static boolean createAccount(String username, String password) 
    {   

        try         
        {
            /*Makes sure username and password isnt just blank*/
            if (username.isEmpty() || password.isEmpty()) 
            {
                System.out.println("Username or password cannot be blank.");
                return false; /*Conditions not met*/
            }
            

            boolean Dupe = false;
            /*boolean variable*/

            /*Opens file to read*/
            BufferedReader checker = new BufferedReader(new FileReader ("users.txt"));
            String line;

            /*Reads each line of the file till no lines remain(null)*/
            while((line = checker.readLine()) != null)
            {
                String[] parts = line.split(",");
                /*Split line into username and password*/

                /*Used to check if duplicate of username or password or both exists*/
                if(parts.length == 2 && parts[0].equals(username) && parts[1].equals(password))
                {
                    Dupe = true;
                    /*Confirms duplicate exists*/
                    break;
                }
            }
            checker.close();
            /*Prevent resource leaks*/

            /*If duplicate was confirmed sends error message*/
            if(Dupe)
            {
                System.out.println("This username and password already exists, please try again.");
                return false;/*Conditions not met*/
            }
            else
            {
                FileWriter fileInput = new FileWriter("users.txt",true);
                fileInput.write(username+","+password+"\n");
                fileInput.close();
                System.out.println("User Registered Successfull");
                return true;/*Conditions met*/
            }
            /*Inputs username and password into .txt file with comma between them*/
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred: " + e.getMessage());
            /*Outputs error text if any input output errors if possible*/
            return false;/*Conditions not met*/
        }    
        
    }
}
