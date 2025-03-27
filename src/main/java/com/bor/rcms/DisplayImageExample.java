package com.bor.rcms;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class DisplayImageExample {
	public static void main(String[] args) {
		  // Define the path to the image
		        String filePath = "C:\\Users\\Admin\\Pictures\\Screenshots\\morskie-oko-tatry.jpg"; // Local file path
		        try {
		            // Create a File object
		            File file = new File(filePath);

		            // Read the file into a byte array
		            FileInputStream fileInputStream = new FileInputStream(file);
		            byte[] fileBytes = new byte[(int) file.length()];
		            fileInputStream.read(fileBytes);
		            fileInputStream.close();

		            // Convert the byte array to a Base64 string
		            String base64Encoded = Base64.getEncoder().encodeToString(fileBytes);
		            
		            // Output the Base64 string
		            System.out.println("Base64 Encoded Image: " + base64Encoded);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
	}


