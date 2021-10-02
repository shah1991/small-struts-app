package net.slide.util;

import java.io.UnsupportedEncodingException;

public class Test {

	public static void main(String[] args) {
		double total=4;
		double yes=2;
		double no=1;
		double per=(yes/(total-no))*100;
		
		System.out.println(per);
		
		String url="TaskPostMain_entry.do?portfolioId=1";
		System.out.println(url.matches("TaskPostMain_entry.do"));
		System.out.println(url.contains("sdfdsfdft"));
		System.out.println(url.replace("TaskPost", ""));
		
		
		 String s = "Happy &amp; Sad";
		    System.out.println(s);
		    
		   

		    try {
		        // Change to "Happy & Sad". DOESN'T WORK!
		        s = java.net.URLDecoder.decode(s, "UTF-8");
		        System.out.println(s);
		    } catch (UnsupportedEncodingException ex) {

		    }
		    
		    
		    
		    

	}
}
