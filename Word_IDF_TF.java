/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab1;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 import java.lang.Math;


/**
 *
 * @author Anjali Kumari
 */
public class Word_IDF_TF {

    /**
     * @param args the command line arguments
     */
    
    HashMap<Integer , HashMap<String,Integer>> TF = new HashMap<Integer , HashMap<String,Integer>>();
    
    Map <String ,List<Integer>> invertedIndex = new HashMap <String, List<Integer>>();
   
    List<Integer> list = null;
    int total_document = 50691;
   
    
    
     public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException
     {
         Word_IDF_TF wordTermFreq = new Word_IDF_TF();
         double idf =wordTermFreq.inverseDocFrequecy("दामाद",wordTermFreq.total_document);
         System.out.println("IDF is "+ idf);
         wordTermFreq.weightOfTwoTermInDoc("बल दामाद");
         
     }
    // fequency of word in the doc.
    public void termFreqInDoc(String word , int fileId)
    {
        HashMap<String,Integer> value = new HashMap<String,Integer>();
        
        if(TF.containsKey(fileId))
        {
            value = TF.get(fileId);
            if(value.containsKey(word))
            {
                int freq = value.get(word);
                freq++;
                value.put(word, freq);
                TF.put(fileId, value);
            }
            else
            {
                int freq = 1;
                value.put(word, freq);
                TF.put(fileId, value);
            }
            System.out.println("IN in TF");
        }
        
        else{
        value.put(word, 1);
        TF.put(fileId, value);
        System.out.println("not in TF");
        }
    }
    
    // give total list of word and its corresponding frequency in a document
    public void search(int fileId)
    {
        HashMap<String,Integer> value = new HashMap<String,Integer>();
        if(TF.containsKey(fileId))
        {
            
            value = TF.get(fileId);
        }
    System.out.println(value);
    
    }
    
    
    public void weightOfTermInDoc (String word) throws IOException, FileNotFoundException, ClassNotFoundException
    {   
        HashMap<Integer , HashMap<String,Integer>> TF = new HashMap<Integer , HashMap<String,Integer>>();
        HashMap<String , Integer> hm = new HashMap<String,Integer>();
        HashMap<Integer, Double> tf_idf_Weight= new HashMap<Integer,Double>();
        Object obj = readBinaryFile("termFreqInDoc.txt");
        
        TF = (HashMap<Integer , HashMap<String,Integer>>) obj;
        double idf =inverseDocFrequecy(word ,3);
        for (Object key: TF.keySet())
        {
            
            hm = TF.get(key);
            if(hm.containsKey(word))
            {
                int freq = hm.get(word);
                
                double weight = freq *idf;
                tf_idf_Weight.put((Integer)key, weight);
            }
        }
        
        
        System.out.println(tf_idf_Weight);
    
    }
    
    
    public void weightOfTwoTermInDoc (String word) throws IOException, FileNotFoundException, ClassNotFoundException
    {   
        HashMap<Integer , HashMap<String,Integer>> TF = new HashMap<Integer , HashMap<String,Integer>>();
        HashMap<String , Integer> hm = new HashMap<String,Integer>();
        HashMap<Integer, Double> tf_idf_Weight= new HashMap<Integer,Double>();
        Object obj = readBinaryFile("termFreqInDoc.txt");
        String[] terms = word.split(" " );
        System.out.println(terms[0]+terms[1]);
        TF = (HashMap<Integer , HashMap<String,Integer>>) obj;
        
        for (Object key : TF.keySet())
        {
            hm = TF.get(key);
            double weight =0;
            for(int i =0;i<terms.length;i++)
            {
                    double idf =inverseDocFrequecy(terms[i] ,total_document);
                    if(hm.containsKey(terms[i].trim()))
                    {
                        int freq = hm.get(terms[i].trim());

                        weight = weight+ freq *idf;
                        
                    }
            }
            tf_idf_Weight.put((Integer)key, weight);
        }
        
        
        System.out.println(tf_idf_Weight);
    
    }
    
    // Inverse Doc Frequency
    public double inverseDocFrequecy(String word ,float totalDoc) throws IOException, FileNotFoundException, ClassNotFoundException
    {
        Object obj = readBinaryFile("invertedBinary.txt");
        invertedIndex = (Map)obj;
        list = invertedIndex.get(word.trim());
       // System.out.println(list.size());
        double idf= totalDoc/list.size() ;
        return Math.log(idf);
    }
    
    
    
    
    // Read binary file
    public  Object readBinaryFile(String filename ) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        File f = new File (filename);
        FileInputStream fos = new FileInputStream(f);
        ObjectInputStream in = new  ObjectInputStream(fos); 
        Object obj = in.readObject();
        return obj;
    
    }
}
