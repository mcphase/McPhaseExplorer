/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mcphase.base;

import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides static helper functions to obtain matchers for regular
 * expressions and read key-value pairs from text files.
 * @author Till Hoffmann
 */
public class VariableHelper {

    private static final Hashtable<String, Matcher> matcherCache = new Hashtable<>();

    /**
     * Creates a matcher for the specified pattern and text.
     * @param pattern the pattern to be used
     * @param text the text to apply the pattern to
     * @return a matcher for the specified pattern and text
     */
    public static Matcher getMatcher(String pattern, String text) {
        //Return a reset matcher if one exists
        if (matcherCache.containsKey(pattern)) {
            return matcherCache.get(pattern).reset(text);
        }
        //Otherwise create a new pattern
        Pattern p = Pattern.compile(pattern, Pattern.MULTILINE);
        //Create a matcher
        Matcher matcher = p.matcher(text);
        //Add the matcher
        matcherCache.put(pattern, matcher);
        //Return the matcher
        return matcher;
    }

    /**
     * Creates a matcher for a key-value pair type variable.
     * @param variable the name of the variable
     * @param text the text to apply the pattern to
     * @return a matcher for the key-value pair type variable
     */
    public static Matcher getVariableMatcher(String variable, String text) {
        String v[]=variable.split(":");String pattern;
        if(v.length==1)
        { //Create a pattern for the variable  case "variable"
        //pattern = String.format("(?<!\\b%s\\s*=\\s*[^\\s]*)(^)(?:\\#!|[^\\#\\n])*?((\\b%s\\s*=\\s*([^\\s]*)))",v[0], v[0]);
        pattern = String.format("^(?:\\#!|[^\\#\\n])*?((\\b%s\\s*=\\s*([^\\s]*)))", v[0]);
      //  String pattern = String.format("(\\b%s\\s*+=\\s*+([^\\s]*+))", v[0]);
               
        } else
            { if(v[0].length()==0) // case ":var:var:variable"
             {//pattern = String.format("(?!\\b%s\\s*=\\s*[^\\s]*)(^)(?:\\#!|[^\\#\\n])*?((\\b%s\\s*=\\s*([^\\s]*)))",v[v.length-1],v[v.length-1]);
              pattern = String.format("^(?:\\#!|[^\\#\\n])*?((\\b%s\\s*=\\s*([^\\s]*)))",v[v.length-1]);
                        
             } else 
             {//case "var:var1:var2:var1", e.g.  hklline=h1=0 k1=1 l1=0 to hN=1 kN=1 lN=0 Nstp=21
             //pattern = String.format("(?!\\b%s\\s*=.*\\b%s\\s*=\\s*[^\\s]*)(^)(?:\\#!|[^\\#\\n])*?(\\b%s\\s*=.*(\\b%s\\s*=\\s*([^\\s]*)))",  v[0],v[v.length-1],  v[0],v[v.length-1]);
             pattern = String.format("^(?:\\#!|[^\\#\\n])*?(\\b%s\\s*=.*(\\b%s\\s*=\\s*([^\\s]*)))",v[0],v[v.length-1]);
             } 
            }
       //Get a matcher
        return getMatcher(pattern, text);
        
    }
    /**
     * Creates a matcher for a key-value pair type variable in a comment line.
     * @param variable the name of the variable
     * @param text the text to apply the pattern to
     * @return a matcher for the key-value pair type variable
     */
    public static Matcher getCommentedVariableMatcher(String variable, String text) {
        String v[]=variable.split(":");String pattern ;
        if(v.length==1)
        { //Create a pattern for the variable
           // pattern = String.format("(!\\b%s\\s*=\\s*[^\\s]*)*(\\#)[^\\n]*(\\b%s\\s*=\\s*([^\\s]*))",
             //  v[0],v[0]);
                pattern = String.format("\\#.*(\\b(%s\\s*=\\s*([^\\s]*)))",v[0]);
        }
        else
             { if(v[0].length()==0) // case ":var:var:variable"
             {pattern = String.format("\\#.*(\\b(%s\\s*=\\s*([^\\s]*)))", v[v.length-1]);
             //pattern = String.format("(!\\b%s\\s*=\\s*[^\\s]*)*(\\#)[^\\n]*(\\b(%s\\s*=\\s*([^\\s]*)))",  v[v.length-1], v[v.length-1]);
             } else 
             {//case "var:var1:var2:var1", e.g.  hklline=h1=0 k1=1 l1=0 to hN=1 kN=1 lN=0 Nstp=21
             //pattern = String.format("(!\\b%s\\s*=.*\\b%s\\s*=\\s*[^\\s]*)*(\\#)[^\\n]*(\\b%s\\s*=.*(\\b%s\\s*=\\s*([^\\s]*)))",v[0],v[v.length-1],v[0],v[v.length-1]);
             pattern = String.format("\\#.*(\\b%s\\s*=.*(\\b%s\\s*=\\s*([^\\s]*)))",v[0],v[v.length-1]);
             } 
            }
        //Get a matcher
        return getMatcher(pattern, text);
        
    }
    
    public static Matcher getApproxVariableMatcher(String variable, String text) {
        String v[]=variable.split(":");String pattern ;
        if(v.length==1)
        { //Create a pattern for the variable
        pattern = String.format("\\#.*(\\b%s.*=\\s*([^\\s]*))", v[0]);
        //pattern = String.format("(!\\b%s.*=\\s*[^\\s]*)*(\\#)[^\\n]*(\\b%s.*=\\s*([^\\s]*))?m", v[0], v[0]);
        }
        else
             { if(v[0].length()==0) // case ":var:var:variable"
             {pattern = String.format("\\#.*(\\b(%s.*=\\s*([^\\s]*)))", v[v.length-1]);
              //pattern = String.format("(!\\b%s.*=\\s*[^\\s]*)*(\\#)[^\\n]*(\\b(%s.*=\\s*([^\\s]*)))?m", v[v.length-1], v[v.length-1]);
             } else 
             {//case "var:var1:var2:var1", e.g.  hklline=h1=0 k1=1 l1=0 to hN=1 kN=1 lN=0 Nstp=21
             pattern = String.format("\\#.*(\\b%s.*=.*(\\b%s.*=\\s*([^\\s]*)))",v[0],v[v.length-1]);
             //pattern = String.format("(!\\b%s.*=.*\\b%s.*=\\s*[^\\s]*)*(\\#)[^\\n]*(\\b%s.*=.*(\\b%s.*=\\s*([^\\s]*)))?m",v[0],v[v.length-1],v[0],v[v.length-1]);
             } 
            }
        //Get a matcher
        return getMatcher(pattern, text);
        
    }

   
    
     public static Matcher getTextMatcher(String variable, String text) {
        String v[]=variable.split(":");
        if(v.length==1)
        { //Create a pattern for the variable
        String pattern = String.format("^.*%s",v[0]);
        //Get a matcher
        return getMatcher(pattern, text);}
        else
        {
        String pattern = String.format("^.*%s.*%s",v[0],v[1]);
        //e.g.  hklline=h1=0 k1=1 l1=0 to hN=1 kN=1 lN=0 Nstp=21
        //Get a matcher
        return getMatcher(pattern, text);
        }
        
    }
     
      /**
     * Returns the value of a key-value pair type variable or <code>null</code>,
     * if the variable can not be found.
     * @param variable the name of the variable
     * @param text the text to apply the pattern to
     * @return the value of the key-value pair type variable or <code>null</code>,
     * if the variable can not be found.
     */
    public static String getVariable(String variable, String text) {
        //Get a matcher for the current text
        Matcher m = getVariableMatcher(variable, text);
        //Match the text
        if (m.find()) {
        Matcher cm = getCommentedVariableMatcher(variable, text);
        if(cm.find())
         {if(cm.start()<m.start())
         {return null;}
         }
            return m.group(3);
        }else
        //Get the variable value
        //System.out.printf("%s\n",m.group(2));
        {return null;}
    }
    
    public static Matcher VariableFind(String variable, String text) {
        //Get a matcher for the current text
        Matcher m = getVariableMatcher(variable, text);
        //Match the text
        if (m.find()) {
        Matcher cm = getCommentedVariableMatcher(variable, text);
        if(cm.find())
         {if(cm.start()<m.start())
         {return null;}
         }
            return m;
        }else
        //Get the variable value
        //System.out.printf("%s\n",m.group(2));
        {return null;}
    }
}
