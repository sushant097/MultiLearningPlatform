package com.project.spring.config;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckExecutionTEmp {
	public String encodeDiscussionId(String toEncrypt) {

	    String tempEn = toEncrypt + "";
	    String encryptText ="";
	    for(int i=0;i<tempEn.length();i++) {
	        int a = (int)tempEn.charAt(i);
	        a+=148113; // your Secret Key
	        encryptText +=(char)a;
	    }
	    return encryptText;
	}

	public String decodeDiscussionId(String encryptText) {

	    String decodeText = "";
	    for(int i=0;i<encryptText.length();i++) {
	        int a= (int)encryptText.charAt(i);
	        a -= 148113;
	        decodeText +=(char)a;
	    }

	    return decodeText;	
	}

	public static void main(String[] args) {
		Date date = new Date();
		Format formatter = new SimpleDateFormat("yyyy-MMM-dd hh:mm:ss a");//yyyyMMddHHmmssa
		String s = formatter.format(date);
		System.out.println("Datetime: "+s);
		
		CheckExecutionTEmp obj = new CheckExecutionTEmp();
		String enDisId = obj.encodeDiscussionId("Hello");
		
		String decodeDisId = obj.decodeDiscussionId(enDisId);
		
		System.out.println("Encrypt  "+enDisId);
		System.out.println("Decrypt "+decodeDisId);
	}

}
