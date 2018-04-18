package translatorapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import jp.co.kyoceramita.app.AppContext;
import jp.co.kyoceramita.storage.StorageFile;
import jp.co.kyoceramita.storage.StorageType;
import jp.co.kyoceramita.user.attribute.DomainName;

public class MenuServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private BundleContext bc;
	private fileExplorer fe;

	public MenuServlet(BundleContext ctx){
		super();
		bc = ctx;
	}
	
	//just in case web.xml changes  my mapService
	//there is probably a better way of doing this...
	public MenuServlet() {
		if (bc == null){
			bc = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		}
		fe = new fileExplorer(bc);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
	
        String param = req.getParameter("Action") ;
        if (param != null){ //Switch case would be better but.. java1.6
        	System.out.println("--------------------");
			System.out.println("PARAM : " + param);
			if (param.equals("FileExplorer")){
				System.out.println("FILE EXPLORER");
				//TODO add listener on pug/unplug to avoid reloading(optimization)
				
			    resp.setContentType("application/text");
			    resp.setCharacterEncoding("UTF-8");
			    String path = req.getParameter("Path");

			    fe.reload(path);
			    resp.getWriter().write(fe.getHTML());	
			    
			}else if (param.equals("SelectedFile")){
				String selectedFilePath = req.getParameter("Path");
				System.out.println("-------------------");
				System.out.println("Selected File: ");
				System.out.println(selectedFilePath);
				System.out.println("-------------------");
				
				fe.setSelectedFileName(selectedFilePath.substring(selectedFilePath.lastIndexOf("/")+1, selectedFilePath.length()-4));
				//byte[] binFile = fe.getFile(selectedFile);
				String strData = fe.getFileData(selectedFilePath);
				if (strData.length() > 1){
					System.out.println("File content Loaded");
					System.out.println("Sending data back");
					//don't print content or it will crash lul
					//Loading files take a while to load
					//TODO add loading animation
						
					resp.setContentType("application/text");
					//OutputStream output = resp.getOutputStream();
					//output.write(binFile);
					//output.close();
					resp.getWriter().write(strData);	
				}
								
        	}else if(param.equals("Scan")){
        	
				System.out.println("SCANNING");
			
			}else if(param.equals("Print")){
				System.out.println("PRINTING");
				
				PrinterJob pj = new PrinterJob(bc);

				pj.createJobAttributeSet();
				pj.createJob();
				System.out.println(pj.start() + " says PrinterJob.start()");
			}else if(param.equals("SaveToUSB")){
				fe.copyFromTempToTranslatedDocuments();
			}
			
			
		}else{
			//Assume its a first load or reload.
			System.out.println("-------------------");
			System.out.println("New client");
			System.out.println("-------------------");
			ServletContext sc = getServletContext();
			sc.getRequestDispatcher("/TranslatorApp/MainMenu.jsp").forward(req, resp);
		}
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		doGet(req,resp);
	}
}
