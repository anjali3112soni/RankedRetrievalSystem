/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Anjali Kumari
 */
public class Stastistic {
    
   public  HashMap<String,Integer> wordc = null;
    public Map<String,List<Integer>> invMap = null;
     public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException  {
     
          List<Integer> listFinal = null;
          File f_inv = new File("invertedBinary.txt");
          File file = new File("wordFrequencyBinary.txt");
          FileInputStream fis_inv = new FileInputStream(f_inv);
            ObjectInputStream s_inv = new ObjectInputStream(fis_inv);
            FileInputStream f = new FileInputStream(file);
            ObjectInputStream s = new ObjectInputStream(f);
            Stastistic oq = new Stastistic();
          oq.wordc = (HashMap) s.readObject();
          oq.invMap = (Map) s_inv.readObject();
          s.close();
            //oq.postListQyery("बात सरकार");
          Scanner scan = new Scanner(System.in);
          
        
        //System.out.println(oq.wordc);
       
        boolean exit = false;
        while(!exit)
        {   
            System.out.println("Option 1: wordFrequency"+"\n"+"Option 2: postingList"+"\n"+"Option 3: Exit");
            int option = scan.nextInt();
            
            if(option!=3)
            {
                System.out.println("Enter The Word");
        
                String word = scan.next();
            
                switch(option)
                {
                    case 1: oq.wordFrequency(word);
                            break;
                    case 2: listFinal = oq.postingList(word);
                            System.out.println("Posting List : "+listFinal);
                            break;
                    
                    default: System.out.println("No matched option");
                            break;


                }   
            }
            
            else{exit = true;}
        }
        //oq.wordFrequency("सरकार");
        //listFinal = oq.postingList("सरकार");
        //System.out.println("Posting list is: "+listFinal);
        
        
        
         
     }
    
    //------------------print word-frequency----------------------------------- 
     public void wordFrequency(String word)
     {
         
          int count = 0;
         if(wordc.containsKey(word))
         {
             count = wordc.get(word);
         
         }
         
         System.out.println(word+ " = "+ count);
     }
     //--------------------------print posting list of word-------------------------
     public List<Integer>  postingList(String word)
     {
         List<Integer> list = null;
         if(invMap.containsKey(word))
         {
             list = invMap.get(word);
         
         }
         Collections.sort(list);
         
         //System.out.println(word+ " = "+ list);
         System.out.println("SIZE IS :"+list.size());
         check(list);
         return list;
     }
     
     public void check(List<Integer> list)
     {
         if(list.contains(44))
         {
         System.out.println("True");
         }
         else{System.out.println("False");}
     
     }
     
     
     //-------------------------------Query--------------------------
//     public void postListQyery(String query)
//     {
//         List<List<Integer>> list = new ArrayList<List<Integer>>();
//         List<Integer> l = null;
//         String[] wQuery = query.split(" ");
//         for (int i=0;i<wQuery.length;i++)
//         {
//             l = postingList(wQuery[i]);
//             
//             
//             //System.out.println(l);
//             try{
//                 list.add(l);
//             System.out.println(l);
//             }
//             catch (Exception e)
//             {
//                System.out.println("IT IS NULl");
//             }
//         }
//         
//        commonDoc(list);
//     //System.out.println(list);
//     
//     }
//     
     //--------------------common document----------------------------
     
//     public void commonDoc(List<List<Integer>> lists )
//     {
//         int p1 = 0;
//         int p2 = 0;
//         System.out.println("Common doc are: ");
//         int sizeL1 = lists.get(0).size();
//         int sizeL2 = lists.get(1).size();
//         System.out.println(sizeL1 +" "+ sizeL2);
//         List<Integer> cl = null;
//         
//         while( p1<= (sizeL1-1) && p2<= (sizeL2-1) )
//             
//         {    
//                if(lists.get(0).get(sizeL1-1) < lists.get(1).get(0))
//                  {
//                         if(lists.get(0).get(p1) == lists.get(1).get(p2))
//                         {
//                           cl.add(lists.get(0).get(p1));
//                           p1++;
//                           p2++;
//                         }
//                     
//                         else
//                         {
//                             if(lists.get(0).get(p1)> lists.get(1).get(p2))
//                             {
//                             p2++;
//                             }
//                             else if(lists.get(0).get(p1)< lists.get(1).get(p2))
//                             {
//                             p1++;
//                             }
//                         
//                         }
//                    }
//         }
//         System.out.println("Common doc are: ");
//         System.out.println(cl);
//     }
     
     
}
