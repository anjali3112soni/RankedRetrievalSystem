/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab1;

import java.util.HashMap;

/**
 *
 * @author Anjali Kumari
 */
public class File_index {
    int index =1;
    HashMap<Integer,String> hmIndex = new HashMap<Integer, String>();
    public void FileIndex(String  nameOfFile)
    {      
    hmIndex.put(index, nameOfFile);
    index++;
    
    
    }
    
}
