//KevinDNF
package translatorapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.osgi.framework.BundleContext;

import jp.co.kyoceramita.ksf.*;
import jp.co.kyoceramita.util.*;
import jp.co.kyoceramita.app.*;
import jp.co.kyoceramita.box.BoxException;
import jp.co.kyoceramita.box.RemovableMemory;
import jp.co.kyoceramita.storage.*;

public class fileExplorer {
	
	private StorageManager sm;
	private String selectedFileName = "";
	private String HTML;
	
	public fileExplorer(BundleContext context){
		KSFUtility ksf = KSFUtility.getInstance();
		AppContext appCtx = ksf.getApplicationContext(context);
		
		sm = StorageManager.getInstance(appCtx);
		
		//RAMDISK and cf_memory give errors. This could be due to the simulated enviroment.
	}
	
	public void reload(String path){
		System.out.println("Reloading...");
		
		HTMLTemplate(path);
		scanFileSystem(path);
		
	}
	
	public void createTempFolder(){
		StorageFile file = null;
		Storage device = sm.getStorage(StorageType.USB_MEMORY)[0];
			if (device.isAvailable()){
				System.out.println("SCANNING : " + device);
				file = device.getStorageFile("/.temp");
				file.mkdir();
				System.out.println("File.getPath():  " + file.getPath());
			}else{
				System.out.println("Device : " + device + " is unavailable.");
			}
	}
	
	public void copyFromTempToTranslatedDocuments(){
		StorageFile[] files = null;
		StorageFile file = null;
		Storage[] devices = sm.getStorage(StorageType.USB_MEMORY);
		for (int i = 0; i < devices.length; i++){
			if (devices[i].isAvailable()){
				System.out.println("SCANNING : " + devices[i]);
				files = devices[i].getStorageFile("/").listFiles();
				file = devices[i].getStorageFile("/Translated Documents");
				System.out.println("File.getPath():  " + file.getPath());
			}else{
				System.out.println("Device : " + devices[i] + " is unavailable.");
			}
		}
		
		if (files.length <= 0){
			file.mkdir();
			System.out.println("New folder created1");
		} else {
			boolean folderIsThere = false;
			for (int i = 0 ; i< files.length; i++){
				// If its a directory
				if (files[i].isDirectory() && files[i].getName().equals("Translated Documents")){
					folderIsThere = true;
					break;
				}
			}
			if (!folderIsThere){
				file.mkdir();
				System.out.println("New folder created2");
			}
			System.out.println("Selected File: " + selectedFileName);
			StorageFile srcFile = devices[0].getStorageFile("/.temp/.temppdf.pdf");
			StorageFile destFile = devices[0].getStorageFile("/Translated Documents/" + selectedFileName + "-translated.pdf");
			InputStream is = null;
		    OutputStream os = null;
		    try {
		        try {
					is = srcFile.getInputStream();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        try {
					os = destFile.getOutputStream();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        byte[] buffer = new byte[1024];
		        int length;
		        try {
					while ((length = is.read(buffer)) > 0) {
					    os.write(buffer, 0, length);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    } finally {
		        try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		}
		
	}
	
	public void scanFileSystem(String path){
		
		Storage[] devices = sm.getStorage(StorageType.USB_MEMORY);
		
		//TODO simplify to only USB
		for (int i = 0; i < devices.length; i++){
			if (devices[i].isAvailable()){
				System.out.println("SCANNING : " + devices[i]);
				StorageFile[] files = devices[i].getStorageFile(path).listFiles();
				
				appendHTML(files);
				//printFiles(files);
			}else{
				System.out.println("Device : " + devices[i] + " is unavailable.");
			}
		}
		if (devices.length == 0){
			System.out.println("No devices found");
		}
	}
	
	//for testing purposes
	private void printFiles(StorageFile[] files){
		for (int i =0; i<files.length;i++){
			if (files[i].isDirectory()){
				System.out.println("----------------------------------------------");
				System.out.println("Found Folder  :");
				System.out.println(files[i].getName());
				System.out.println("----------------------------------------------");
				printFiles(files[i].listFiles());
			}
			System.out.println("----------------------------------------------");
			System.out.println("Found File at :");
			System.out.println(files[i].getParent());
			System.out.println("Name : ");
			System.out.println(files[i].getName());
			System.out.println("----------------------------------------------");
		}
	}
	
	private void appendHTML(StorageFile[] files){
		String fileName = "";
		// If the path is empty
		if (files.length <= 0){
			HTML += " <img src='img/noFile.png'>";
			HTML += " <p>No documents to display.</p>";
		} else {
			for (int i = 0 ; i< files.length; i++){
					String sPath = "'"+ files[i].getPath()  +"'";
				    sPath = sPath.replace("\\", "/"); 
				// If its a directory and its not ".temp" - that has to be invisible
				if (files[i].isDirectory() && !files[i].getName().equals(".temp")){
					// If the name is too long, its shortened
					if (files[i].getName().length() > 10){
						fileName = files[i].getName().substring(0, 9) + "...";
					} else fileName = files[i].getName();
					if (files[i].listFiles().length <= 0){
						HTML += " <button type=\"button\" class=\"button\" onclick=\"openFileExplorer("+ sPath+ ")\">";
						HTML += " <img src='img/emptyFolder.png'>";
						HTML += " <p>"+ fileName + "</p>";
						HTML += " </button>";		
					}else{
						HTML += " <button type=\"button\" class=\"button\" onclick=\"openFileExplorer("+ sPath+ ")\">";
						HTML += " <img src='img/folder.png'>";
						HTML += " <p>"+ fileName + "</p>";
						HTML += " </button>";	
					}
				// If its a file and it has a pdf format
				}else if (files[i].isFile()){
					// If the name is too long, its shortened
					if (files[i].getName().length() > 14){
						fileName = files[i].getName().substring(0, 9) + "...";
					} else fileName = files[i].getName();
					// Get the format
					String fName = files[i].getName();
					String fFormat = fName.substring(fName.lastIndexOf("."));
					if (fFormat.equals(".pdf")){ //Only PDF files
						HTML += " <button type=\"button\" class=\"button\" onclick=\"selectFile("+ sPath  + ")\">";
						HTML += " <img src='img/pdf.png'>";
						HTML += " <p>"+ fileName + "</p>";
						HTML += " </button>";
					}
				}
			}
		}
		HTML += " </div>";
	}
	
	private void HTMLTemplate(String path){
		
		HTML = "<div class='header'>";
		HTML += " <button type=\"button\" class=\"back\" onclick=\"prevFolder('/"+ path + "')\">";
		HTML += " </button>";
		HTML += " <h2>File Explorer</h2>";
		HTML += " <button type='button' class='close' onclick='exitOverlay()'>";
		HTML += " </button>";
		HTML += " </div>";
		HTML += " <div class='main'>";	
	}
	
	public String getHTML(){
		return HTML;
	}
	
	public String getFileData(String path){
		//from USB 0
		StorageFile file = sm.getStorage(StorageType.USB_MEMORY)[0].getStorageFile(path);

		byte[] buffer = new byte[81920001];
		String stringData = "";
		try {
			StorageFileInputStream reader = file.getInputStream();
			
			reader.read(buffer);//TODO break big pdf files to multiple buffers
			reader.close();

			StringBuilder str = new StringBuilder();
			str.append("data:application/pdf;base64,");
			String converted = Base64.encodeBase64URLSafeString(buffer).toString();
			str.append(converted);
			stringData = str.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block check sample prog. 3 catches there
			e.printStackTrace();
		}
		return stringData;	
	}
	
	public File getFileToSave(String path){
		//from USB 0
		StorageFile file = sm.getStorage(StorageType.USB_MEMORY)[0].getStorageFile(path);

		return (File) file;	
	}
	
	public void setSelectedFileName(String name){
		selectedFileName = name;
	}
	public void receivePDF(String pdfString){
			byte[] pdfBin = new byte[819200001];
			//url safe?
			pdfBin = Base64.decodeBase64(pdfString);
	}
	
}
