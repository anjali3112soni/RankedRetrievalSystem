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
public class Stemmer {
    
    public String stem(String buffer, int len) {
    // 5
    if ((len > 6) && (buffer.endsWith( "ाएंगी")
        || buffer.endsWith("ाएंगे")
        || buffer.endsWith("ाऊंगी")
        || buffer.endsWith("ाऊंगा")
        || buffer.endsWith("ाइयाँ")
        || buffer.endsWith("ाइयों")
        || buffer.endsWith("ाइयां")
      ))
      return buffer.substring(0, len-5);
    
    
    // 4
    if ((len > 5) && (buffer.endsWith("ाएगी")
        || buffer.endsWith("ाएगा")
        || buffer.endsWith("ाओगी")
        || buffer.endsWith("ाओगे")
        || buffer.endsWith("एंगी")
        || buffer.endsWith("ेंगी")
        || buffer.endsWith("एंगे")
        || buffer.endsWith("ेंगे")
        || buffer.endsWith("ूंगी")
        || buffer.endsWith("ूंगा")
        || buffer.endsWith("ातीं")
        || buffer.endsWith("नाओं")
        || buffer.endsWith("नाएं")
        || buffer.endsWith("ताओं")
        || buffer.endsWith("ताएं")
        || buffer.endsWith("ियाँ")
        || buffer.endsWith("ियों")
        || buffer.endsWith("ियां")
        ))
      return buffer.substring(0, len-4);
    
    // 3
    if ((len > 4) && (buffer.endsWith("ाकर")
        || buffer.endsWith( "ाइए")
        || buffer.endsWith("ाईं")
        || buffer.endsWith("ाया")
        || buffer.endsWith("ेगी")
        || buffer.endsWith("ेगा")
        || buffer.endsWith("ोगी")
        || buffer.endsWith("ोगे")
        || buffer.endsWith("ाने")
        || buffer.endsWith("ाना")
        || buffer.endsWith("ाते")
        || buffer.endsWith("ाती")
        || buffer.endsWith("ाता")
        || buffer.endsWith("तीं")
        || buffer.endsWith("ाओं")
        || buffer.endsWith("ाएं")
        || buffer.endsWith ("ुओं")
        || buffer.endsWith("ुएं")
        || buffer.endsWith("ुआं")
        ))
      return buffer.substring(0, len-3);
    
    // 2
    if ((len > 3) && (buffer.endsWith("कर")
        || buffer.endsWith("ाओ")
        || buffer.endsWith("िए")
        || buffer.endsWith("ाई")
        || buffer.endsWith("ाए")
        || buffer.endsWith("ने")
        || buffer.endsWith("नी")
        || buffer.endsWith("ना")
        || buffer.endsWith("ीं")
        || buffer.endsWith("ती")
        || buffer.endsWith("ती")
        || buffer.endsWith("ता")
        || buffer.endsWith("ाँ")
        || buffer.endsWith("ां")
        || buffer.endsWith("ों")
        || buffer.endsWith("ें")
        ))
      return buffer.substring(0, len-2);
    
    // 1
    if ((len > 2) && (buffer.endsWith( "ो")
        || buffer.endsWith( "े")
        || buffer.endsWith("ू")
        || buffer.endsWith( "ु")
        || buffer.endsWith("ी")
        || buffer.endsWith("ि")
        || buffer.endsWith( "ा")
       ))
      return buffer.substring(0, len-1);
    return buffer.substring(0, len);
  }
    
}
