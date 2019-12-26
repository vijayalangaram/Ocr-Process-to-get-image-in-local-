package com.example.demo;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@SpringBootApplication
public class JavalocaldoocrApplication 
{	
	public static void main(String[] args) throws IOException  
	{	
	SpringApplication.run(JavalocaldoocrApplication.class, args);
	
		Mongo mongodb = new MongoClient("localhost", 27017);				
		DB db = mongodb.getDB("test-db");	
		DBCollection collection = db.getCollection("user");
		
		File image = new File("mongodb://localhost:27017//test-db//user");
//		File image = new File("C:\\Users\\Administrator\\Desktop\\eurotext.png");
		
						
//		byte[] imageInByte= null ;
		
		byte[] imageInByte;
		
	    BufferedImage originalImage = ImageIO.read(new File("mongodb://localhost:27017//test-db//user"));
	    
//	    BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\eurotext.png"));
	   
	    // convert BufferedImage to byte array
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(originalImage, "png", baos);
		baos.flush();
		imageInByte = baos.toByteArray();
		baos.close();
		
		//convert byte array back to BufferedImage		
	    
		InputStream in = new ByteArrayInputStream(imageInByte);
	    
	    
		BufferedImage bImageFromConvert = ImageIO.read(in);
		
		ImageIO.write(bImageFromConvert, "png", new File("mongodb://localhost:27017//test-db//user"));
		
		
				
		Tesseract tessInst = new Tesseract();     
		tessInst.setDatapath("C:\\Program Files (x86)\\Tesseract-OCR\\tessdata");    
		try {
		        String result= tessInst.doOCR(originalImage);
		         System.out.println(result);
		  } catch (TesseractException a) {
			          System.err.println(a.getMessage());	
		   }}}


//final String datapath =  "C:\\Program Files (x86)\\Tesseract-OCR\\tessdata";	    		
//tessInst.setDatapath (new File("datapath").getPath());
//private static void listAllImages(DB db) {
//    GridFS gfsPhoto = new GridFS(db, "test-db");
//    DBCursor cursor = gfsPhoto.getFileList();
//    while (cursor.hasNext()) {   	
//        System.out.println(cursor.next());
//    }

//mongodb://localhost:27017//test-db//user  
//C:\\Users\\Administrator\\Desktop\\eurotext.png
