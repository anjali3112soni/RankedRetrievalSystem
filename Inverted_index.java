/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab1;

/**
 *
 * @author Anjali Kumari
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import java.util.Map;
import java.util.Scanner;

public class Inverted_index {

    /**
     * @param args the command line arguments
     */
    
        
       Map <String ,List<Integer>> map = new HashMap <String, List<Integer>>();
        //------------- Craeting indexing of words-----------------
       public void index ( String word,int fileId  )
       {
           if(map.containsKey(word))
           {
               
               List<Integer> oldlist= map.get(word) ;  //getting existing posting list
               if(!oldlist.contains(fileId))
               {
                   
                   oldlist.add(fileId);                      // adding new fileId to existing list
                   map.put(word, oldlist);
               
               }
              
               
               
           }
           else
           {
           List<Integer> newlist = new ArrayList<Integer>();         //creating new list and adding new fileid
           newlist.add(fileId);
           map.put(word, newlist);
           }
       
       }
       
       public void printIndex() throws IOException
       {
//           FileWriter out = new FileWriter("inverted_Index.txt"); 
//            for(Object key : map.keySet())
//            {
//             out.write(key+"  "+ map.get(key)+"\n");
//
//            }
//            out.close();
       //System.out.println(map);
        File fi = new File("invertedBinary.txt");
           FileOutputStream f = new FileOutputStream(fi);    // Storing index into memory
        ObjectOutputStream s = new ObjectOutputStream(f);
        s.writeObject(map);
       s.close();
       
       }
       
    
    
    
            }
    

