/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 *
 * @author Anjali Kumari
 */
public class TF_IDF_stastistic {

    /**
     * @param args the command line arguments
     */
    
     Word_IDF_TF WIT = new Word_IDF_TF();
     HashMap<Integer , HashMap<String,Integer>> hmOfTF = null;
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        
        
        TF_IDF_stastistic stas = new TF_IDF_stastistic();
        stas.print();
    }
    
    public void print() throws IOException, FileNotFoundException, ClassNotFoundException
    {
        
            Object obj = readBinaryFile("termFreqInDoc.txt");
            hmOfTF = (HashMap)obj;
            
    }
    
    
    public  Object readBinaryFile(String filename ) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        File f = new File (filename);
        FileInputStream fos = new FileInputStream(f);
        ObjectInputStream in = new  ObjectInputStream(fos); 
        Object obj = in.readObject();
        return obj;
    
    }
            
    }