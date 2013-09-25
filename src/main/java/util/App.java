package util;


import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
    	Set<Integer> s = new HashSet<Integer>();
    	for(int i=0 ; i < 10 ;i++){
    		s.add(i);
    	}
    	System.out.println(s.contains(1));
    	for(int i : s){
    		System.out.println(i);
    	}
    	
    	System.out.println(s);
    }
}
