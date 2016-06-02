package firstclass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
//import java.util.Collections;
//import java.util.Scanner;
//import java.util.ArrayList;


public class anagram {
	public static void main(String[] args) throws IOException {
		InputStreamReader is = new InputStreamReader(System.in);       
        BufferedReader br = new BufferedReader(is);                    
        String str = br.readLine(); 
		char[] chars = str.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        System.out.println(sorted);
        
        
	}
}

