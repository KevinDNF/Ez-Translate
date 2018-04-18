//KevinDNF
package translatorapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

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
		// If the path is empty
		if (files.length <= 0){
			HTML += " <img src='img/noFile.png'>";
			HTML += " <p style=\"text-align: center\">No documents to display.</p>";
		} else {
			for (int i = 0 ; i< files.length; i++){
					String sPath = "'"+ files[i].getPath()  +"'";
				    sPath = sPath.replace("\\", "/"); 
				// If its a directory and its not ".temp" - that has to be invisible
				if (files[i].isDirectory() && !files[i].getName().equals(".temp")){
					if (files[i].listFiles().length <= 0){
						HTML += " <button type=\"button\" class=\"button\" onclick=\"openFileExplorer("+ sPath+ ")\">";
						HTML += " <img src='img/emptyFolder.png'>";
						HTML += " <p>"+ files[i].getName() + "</p>";
						HTML += " </button>";		
					}else{
						HTML += " <button type=\"button\" class=\"button\" onclick=\"openFileExplorer("+ sPath+ ")\">";
						HTML += " <img src='img/folder.png'>";
						HTML += " <p>"+ files[i].getName() + "</p>";
						HTML += " </button>";	
					}
				// If its a file and it has a pdf format
				}else if (files[i].isFile()){
					String fName = files[i].getName();
					String fFormat = fName.substring(fName.lastIndexOf("."));
					if (fFormat.equals(".pdf")){ //Only PDF files
						HTML += " <button type=\"button\" class=\"button\" onclick=\"selectFile("+ sPath  + ")\">";
						HTML += " <img src='img/pdf.png'>";
						HTML += " <p>"+ fName.substring(0, fName.lastIndexOf(".")) + "</p>";
						HTML += " </button>";
					}
				}
			}
		}
		HTML += " </div>";
		HTML += " </div>";
	}
	
	private void HTMLTemplate(String path){
		
		HTML = "<div class='header'>";
		HTML += " <button type=\"button\" class=\"close\" onclick=\"prevFolder('/"+ path + "')\">";
		HTML += " back";
		HTML += " </button>";
		HTML += " <h2>File Explorer</h2>";
		HTML += " <button type='button' class='close' onclick='exitOverlay()'>";
		HTML += " close";
		HTML += " </button>";
		HTML += " </div>";
		HTML += " <div class='main'>";
		HTML += " <div class='box'>";
	
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
	
	public void receivePDF(String pdfString){
			byte[] pdfBin = new byte[819200001];
			//url safe?
			pdfBin = Base64.decodeBase64(pdfString);
	}
	
}
