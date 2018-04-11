package translatorapp;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import jp.co.kyoceramita.app.AppContext;

public class JobServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private BundleContext bc;
	
	public JobServlet(BundleContext ctx){
		super();
		bc = ctx;
	}
	
	//just in case web.xml changes  my mapService
	//there is probably a better way of doing this...
	public JobServlet() {
		if (bc == null){
			bc = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{

		fileExplorer fe = new fileExplorer(bc);

	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		
	}
}
