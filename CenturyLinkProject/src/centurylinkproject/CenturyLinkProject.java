//The purpose of this program is to "Write an APi endpoint that accepts a GitHub
//ID and returns Follower GitHub ID's (up to 5 Followers total) associated with 
//the passed in GitHub ID. Retrieve data up to 3 levels deep, repeating the
//process of retrieving Followers (up to 5 Followers total) for each Follower
//found. Data should be returned in JSON format."
//
//TL:DR Takes in ID, spits out follower, and calls itself for each follower, 3
//levels of followers per instance each in John:Jacob,Jingle,Heimer,Shmit format
//"Checked into" GitHub with a readme that explains how to run and test it

/*
 * @author Jared Couture
 */

package centurylinkproject;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class CenturyLinkProject
{   
    public static void getFollowers(int id, int level) throws Exception
    {
        int[] followers = new int[5];
        String inputLine;
        String tempString = "";
        
        URL github = new URL("http://api.github.com/users/" + id + "/followers");
        URLConnection con = github.openConnection();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        while((inputLine = in.readLine()) != null)
        {
            tempString = tempString + "\n" + inputLine;
        }
        followers = extractor(tempString); // Gets just the ID ints we want
        
        // Catch error of less than 5 total availible followers?
        
        in.close(); // Close input stream
            
        for(int i = 0; i < 5; i++)
        {
            System.out.println(id + ":" + followers[i]); // Prints out each ID:Follower
        }
        
        if(level < 3) // If we aren't already at the third itterence of reccurence
        {
            level++; // record going a level deeper
            getFollowers(followers[0], level); // actually go deeper
        }
    }
    
    public static int[] extractor(String str)
    {   
        int[] folls = new int[5];
        String[] splitStringArray = str.split("\"id\": "); // Makes the second, third, etc values in the array the id
        folls[0] = Integer.parseInt(splitStringArray[1]); // gets the first int in the array, which should just be the id number
        folls[1] = Integer.parseInt(splitStringArray[2]); // gets the first int, which should be the second follower id
        folls[2] = Integer.parseInt(splitStringArray[3]);
        folls[3] = Integer.parseInt(splitStringArray[4]);
        folls[4] = Integer.parseInt(splitStringArray[5]);
        
        return folls;
    }
    
    public static void main(String[] args) throws Exception
    {
        int ID; // The ID number to be used
        int lev = 0; // Just starting the program, level of data is zero
        Scanner scan = new Scanner(System.in); // Allows user input
        
        System.out.println("Please give the ID number of a GitHub user. Please only give numbers.");
        ID = scan.nextInt(); // Gets the starting ID
        System.out.println("Format will be ID:Followers");
        
        getFollowers(ID, lev);
    }
    
}
