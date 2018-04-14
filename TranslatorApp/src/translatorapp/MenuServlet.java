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
			System.out.println("PARAM : " + param);
			if (param.equals("FileExplorer")){
				System.out.println("FILE EXPLORER");
				//TODO add listener on pug/unplug to avoid reloading(optimization)
				fe.reload();
				
			    resp.setContentType("application/text");
			    resp.setCharacterEncoding("UTF-8");

			    resp.getWriter().write(html());	
			    
			}else if(param.equals("Scan")){
				System.out.println("SCANNING");
			}
		}else{
			//Assume its a first load or reload.
			System.out.println("New client");
			ServletContext sc = getServletContext();
			sc.getRequestDispatcher("/TranslatorApp/MainMenu.jsp").forward(req, resp);
		}
	}
	

	private String html(){
		String html = "";
		html += "<div class='header'>";
		html += " <h2>File Explorer</h2>";
		html += " <button type='button' class='close' onclick='exitOverlay()'>";
		html += " close";
		html += " </button>";
		html += " </div>";
		html += " <div class='main'>";
		html += " <div class='colorblock' style='background-color:purple'></div>";
		html += " <div class='colorblock' style='background-color:purple'></div>";
		html += " <div class='colorblock' style='background-color:purple'></div>";
		html += " <div class='colorblock' style='background-color:purple'></div>";
		html += " <div class='colorblock' style='background-color:purple'></div>";
		html += " <div class='colorblock' style='background-color:purple'></div>";
		html += " </div>";
		return html;
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		doGet(req,resp);
	}
}
