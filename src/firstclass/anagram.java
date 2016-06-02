package firstclass;

import java.io.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;

public class anagram {
	
	public static void main(String[] args) throws IOException{ 
		//sort 16 characters in alphabetical order
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));                    
        String str = br.readLine(); 
		char[] chars = str.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        
        //辞書の中身をソート
        BufferedReader br2 =new BufferedReader(new FileReader("/usr/share/dict/words"));
        Hashtable<String, String> hashtable = new Hashtable<String, String>();
        for(;;){
            String s = br2.readLine();
            if(s == null) {
            	break;
            }
            if(s.length()<17){
             	char[] chars1 = s.toCharArray();
               	Arrays.sort(chars1);
               	String sw = new String(chars1);
                hashtable.put(s, sw);
             }
         }
         br.close();
        
         for(int i = 0; i < hashtable.size() ; i++) {
        	 //hashcodeがi番目のkeyを取り出す
        	 StringBuilder keys = hashtable.keyat(hashCode(i));
        	 int[] count = null;
        	 for (int j = 0; j < 16; j++) { 
        		 StringBuilder keyi = keys; 
        		 String match = null;
        		 String charj = sorted.substring(j);
        		 if (keyi.indexOf(charj) != -1) {
        			 int num = keyi.indexOf(charj);
        			 keyi.deleteCharAt(num);
        			 match = match + chars[j];
        			 count[i]++;
        		 } 
        	 }
        	
        	//countの配列の中で一番大きい値を持つものに対応するiの値を取得して、hashcodeがiであるvalueを取り出す
        	 Arrays.sort(count);
        	 int max = hashtable.size()-1;
         	 System.out.println(hashtable.element(max));
         }	   
	}
}

