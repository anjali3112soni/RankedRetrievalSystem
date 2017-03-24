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
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
//import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Anjali Kumari
 */
public class Word_counting {

    /**
     * @param args the command line arguments
     */
    HashMap<String, Integer> hm ;
    File folder ;
    File[] listOfFiles ;
    Word_counting()
    {
     hm = new HashMap<String, Integer>();
     folder = new File("it309/");       // Reading Folder
        listOfFiles = folder.listFiles();  // storing all file into file array
    
    }
    
    
        
        
    
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        
        // TODO code application logic here
        //File file = new File("hindi-1.txt");
        
        
        Word_counting wordCount = new Word_counting();
        File_index fileindex = new File_index();
        Stopword stopword =new Stopword();
        Stemmer stemming = new Stemmer();
        Stastistic stastic = new Stastistic();
        Inverted_index invIndex = new Inverted_index();
        Word_IDF_TF word_Tf_In_Doc = new Word_IDF_TF();
        Object obj;
        String text = null;
        
        StringBuilder record  = null;
        
        StringBuilder lineInFile  = null;
        
        for (File file :wordCount.listOfFiles) {
            if (file.isFile()) {
                fileindex.FileIndex(file.getName());  // giving ID to files by calling method from File_index class

            }

        }
        //System.out.println(fileindex.hmIndex);
        for (Object key : fileindex.hmIndex.keySet()) {
                File f = new File("it309//"+fileindex.hmIndex.get(key)); //reading file one at a time

                BufferedReader br = new BufferedReader(new FileReader(f));
                record = new StringBuilder(128);
                lineInFile = new StringBuilder(128);
                    while((text = br.readLine())!=null)
                        {
                            if(text.trim().length()>0)
                            {
                                String clean = text.replaceAll( "&([^;&]+(?!(?:\\w|;)))", "&amp;$1" ); 
                                record.append(clean);               // storing content in buffer reader


                            }
                        }

                try (ByteArrayInputStream bais = new ByteArrayInputStream(record.toString().getBytes())) {



                            org.w3c.dom.Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);
                            XPath xPath = XPathFactory.newInstance().newXPath();
                            XPathExpression exp = xPath.compile("story/title"); //Reading content in between title tag
                            Node title = (Node) exp.evaluate(doc, XPathConstants.NODE);

                            exp = xPath.compile("story/content"); // reading content in between content tag
                            Node content = (Node) exp.evaluate(doc, XPathConstants.NODE);

                             lineInFile.append(title.getTextContent());
                             lineInFile.append(content.getTextContent());
//                            System.out.println(
//                                    title.getTextContent() );
//                            System.out.println(content.getTextContent());
                             //System.out.println(lineInFile);
                             //System.out.println(key+" "+fileindex.hmIndex.get(key));
                
            } catch (ParserConfigurationException | XPathExpressionException | SAXException ex) {
                            ex.printStackTrace();
                        }
       


            
                String[] words = lineInFile.toString().split(" "); //Storing each word into array
                int length = words.length;
                for (int i = 0; i < length; i++) {
                    String valid_word = wordCount.validWord(words[i]); // removing special character
                    if(!stopword.checkStopWord(valid_word))
                    {
                        word_Tf_In_Doc.termFreqInDoc(valid_word,Integer.parseInt(key.toString()) );
                        if (!valid_word.isEmpty()) {
                            valid_word=stemming.stem(valid_word,valid_word.length() );  // Stemmimg

                            invIndex.index(valid_word, (int) key);   // creating index for the word

                            if (wordCount.hm.containsKey(valid_word)) {

                                int value = wordCount.hm.get(valid_word);          //Creting frequency of each valid word
                                value++;
                                wordCount.hm.put(valid_word, value);

                            } else {
                                wordCount.hm.put(valid_word, 1);
                            }

                        }
                }

                }

            
        }
        //String w = null;

      
       wordCount.createBinaryFile("wordFrequencyBinary.txt", wordCount.hm);
       wordCount.createBinaryFile("termFreqInDoc.txt", word_Tf_In_Doc.TF);

        wordCount.printFile(wordCount.hm);
        invIndex.printIndex();
        
        word_Tf_In_Doc.search(1);
        System.out.println("Anjali");
        word_Tf_In_Doc.search(8);
        
       //System.out.println( word_Tf_In_Doc.TF);
        
        
      
       // System.out.println(wordCount.hm);
        
    }
//-------------------To remove specialword like(:,.{})*)etc from words---------------------
    public String validWord(String word) {
        Pattern special = Pattern.compile("[।!@#$%&*,.॥()_+:=|<>?{}~-]");
        word = word.trim();
        Matcher hasSpecial = special.matcher(word);
        

            while(hasSpecial.find())
            {
               String s= hasSpecial.group();
               
                word =word.replaceAll("\\"+s, "");
                
            }
            return word;
   
        
        }
//
//       
        

    
//---------------------------To create text file of word-frequency---------------------------
    public void printFile(HashMap<String, Integer> hm) throws IOException {
        FileWriter out = new FileWriter("wordCount.txt");
        for (Object key : hm.keySet()) {
            out.write(key + "  " + hm.get(key) + "\n");

        }
        out.close();
    }

    public void createBinaryFile(String filename , Object obj ) throws FileNotFoundException, IOException
    {
        File f = new File (filename);
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream out = new  ObjectOutputStream(fos); 
        out.writeObject(obj);
        
    
    }

}