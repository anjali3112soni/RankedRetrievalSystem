/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 *
 * @author Anjali Kumari
 */
public class Stopword {
      
        
     ArrayList<String> aList = new ArrayList<String>();
    
    Stopword() throws FileNotFoundException, IOException
    {
       
         FileInputStream fis = new FileInputStream("Stopword//hindiStopWords.txt");  //reading stopword.txt
           InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isr);
           String line=null;
            while( (line = br.readLine()) != null)
            {
                aList.add(line);                                       //putting in arraylist
            
            }
           // System.out.println(aList);
    }
    
    
   
    
    public boolean checkStopWord(String word)
    {
        if(aList.contains(word))
        {
        return true;
        }
        else
        {
        return false;
        }
    
    }
    
}
