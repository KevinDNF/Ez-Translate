package translatorapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import jp.co.kyoceramita.app.AppContext;

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
				String selectedFile = req.getParameter("Path");
				System.out.println("-------------------");
				System.out.println("Selected File: ");
				System.out.println(selectedFile);
				System.out.println("-------------------");
				
				if (fe.getFile(selectedFile).length() > 1){
					System.out.println("File content Loaded");
					//don't print content or it will crash lul
					//Loading files take a while to load
					//TODO add loading animation
				}
				//DO STUFF WITH FILE
				
        	}else if(param.equals("Scan")){
				System.out.println("SCANNING");
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
