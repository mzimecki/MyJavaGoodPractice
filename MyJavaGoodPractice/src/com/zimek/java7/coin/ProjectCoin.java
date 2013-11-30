package com.zimek.java7.coin;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Java 7 project coin syntactic sugar add-ons.
 */
public class ProjectCoin {

	public static void main(String[] args) {
		
		//Strings in switch
		String week = "Wed";
		switch (week) {
		case "Mon":
			System.out.println("Monday");
			break;
		case "Tue":
			System.out.println("Tuesday");
			break;
		case "Wed":
			System.out.println("Wednesday");
			break;
		default:
			System.out.println("Other day or day unknown");
			break;
		}
		
		//numeric literals
		int bytes = 0b0101;
		int number = 100_000_000;
		
		//improved exception handling
		File f = new File("test");
		byte [] buf = new byte [1024];
		ByteArrayInputStream bai = new ByteArrayInputStream(buf);
		try {
			bai.wait(100);
			bai.close();
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		//try with resources - automatically closes stream
		try (OutputStream os = new FileOutputStream(f)) {
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Diamond syntax
		Map<Integer, Map<String, String>> myMap = new HashMap<>();
	}
}
