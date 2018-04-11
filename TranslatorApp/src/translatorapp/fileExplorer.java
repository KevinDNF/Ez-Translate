//KevinDNF
package translatorapp;


import org.osgi.framework.BundleContext;

import jp.co.kyoceramita.ksf.*;
import jp.co.kyoceramita.util.*;
import jp.co.kyoceramita.app.*;
import jp.co.kyoceramita.storage.*;

public class fileExplorer {
	
	private StorageManager sm;
	
	public fileExplorer(BundleContext context){
		KSFUtility ksf = KSFUtility.getInstance();
		AppContext appCtx = ksf.getApplicationContext(context);
		
		sm = StorageManager.getInstance(appCtx);

		scanFileSystem(StorageType.USB_MEMORY);
		scanFileSystem(StorageType.HDD);

		//RAMDISK and cf_memory give errors. This could be due to the simulated enviroment.
		//scanFileSystem(RAMDISK); 
		//scanFileSystem(CF_MEMORY);
	}
	
	public void scanFileSystem(StorageType storageType){
		
		Storage[] devices = sm.getStorage(storageType);
		
		for (int i = 0; i < devices.length; i++){
			if (devices[i].isAvailable()){
				System.out.println("SCANNING : " + devices[i]);
				StorageFile[] files = devices[i].getStorageFile("/").listFiles();
				printFiles(files);
			}else{
				System.out.println("Device : " + devices[i] + " is unavailable.");
			}
		}
	}
	//TODO generate array of file directory to be parsed into html or just directly HTML;
	public void printFiles(StorageFile[] files){
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
}
