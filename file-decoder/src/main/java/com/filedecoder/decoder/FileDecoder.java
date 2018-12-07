package com.filedecoder.decoder;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class FileDecoder {

	public String imageDecoder(String data,String targetFilePath,FileType fileType) throws Exception {
		return decode(data,targetFilePath,fileType);										
	}
	
	private String decode(String data,String targetFile,FileType fileType) throws Exception {
		String filepath;
		String fileName="Image_";
		String fileExt=".jpg";
		if(FileType.JPG.equals(fileType)) {
			fileExt=".jpg";
			fileName="Image_";
		}else if(FileType.PDF.equals(fileType)) {
			fileExt=".pdf";
			fileName="Pdf_";
		}else if(FileType.WORD.equals(fileType)) {
			fileExt=".docx";
			fileName="Document_";
		}else if(FileType.TEXT.equals(fileType)) {
			fileExt=".txt";
			fileName="PlainText_";
		}
		
		filepath=  System.getProperty("file.separator")+fileName+fileExt;
		targetFile=targetFile+filepath;
        byte[] decodedBytes = Base64.getDecoder().decode(data);
 
        writeByteArraysToFile(targetFile, decodedBytes);
        
        return targetFile;
    }
	private  void writeByteArraysToFile(String fileName, byte[] content) throws IOException {
		 
        File file = new File(fileName);
        BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
        writer.write(content);
        writer.flush();
        writer.close();
 
    }
}
