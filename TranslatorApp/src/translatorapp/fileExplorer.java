//KevinDNF
package translatorapp;


import java.io.IOException;
import java.io.InputStreamReader;

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
		for (int i = 0 ; i< files.length; i++){
				String sPath = "'"+ files[i].getPath()  +"'";
			    sPath = sPath.replace("\\", "/"); 
			if (files[i].isDirectory()){
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
					
			}else if (files[i].isFile()){
				String fName = files[i].getName();
				String fFormat = fName.substring(fName.lastIndexOf(".")+1);
				if (fFormat.equals("pdf")){ //Only PDF files
					HTML += " <button type=\"button\" class=\"button\" onclick=\"selectFile("+ sPath  + ")\">";
					HTML += " <img src='img/pdf.png'>";
					HTML += " <p>"+ fName + "</p>";
					HTML += " <p>"+ fFormat + "</p>";
					HTML += " </button>";
				}
			}
		}
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
	
	}
	
	public String getHTML(){
		return HTML;
	}
	
	public String getFile(String path){
		//from USB 0
		StorageFile file = sm.getStorage(StorageType.USB_MEMORY)[0].getStorageFile(path);

		byte[] buffer = new byte[819200];
		String stringData = "";
		try {
			StorageFileInputStream is = file.getInputStream();
			
			/*BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			/StringBuilder out = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null){
				out.append(line);
			}
			reader.close();
			is.close();
			data = out.toString();
			 */	
			InputStreamReader reader = new InputStreamReader(is);
			is.read(buffer);
			is.close();
			StringBuilder str = new StringBuilder();
			str.append("data:application/pdf;base64,");
			String converted = Base64.encodeBase64URLSafeString(buffer).toString();
			str.append(converted);
			stringData = str.toString();
			//stringData = str.append(Base64.encode(buffer)).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block check sample prog. 3 catches there
			e.printStackTrace();
		}
		return stringData;	
	}
	
	
}
