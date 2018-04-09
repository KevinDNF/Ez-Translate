package jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class Home_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    javax.servlet.jsp.PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n<html>\r\n  <head>\r\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n    <link rel=\"stylesheet\" href=\"css/style.css\">\r\n    <title>Ez Translate</title>\r\n    <script src=\"/ksf/js/hypas-api.js\" language=\"JavaScript\"></script>\r\n    <script src=\"js/script.js\"></script>\r\n  </head>\r\n\r\n  <body onload=\"HyPAS.App.fullScreen(false);\">\r\n    <div id=\"mainBody\">\r\n\r\n        <div class=\"header\">\r\n            <button type=\"button\">\r\n                <img src=\"img/home.png\" alt=\"Go Home\">\r\n            </button>\r\n            <h1>Ez Translate</h1>\r\n        </div>\r\n\r\n        <div class=\"section\" id=\"mainUI\">\r\n            <form>\r\n                <div class=\"header\">\r\n                    <h2>Select a File</h2>\r\n                </div>\r\n                <div class=\"main\">\r\n                    <button type=\"button\">\r\n                        <img src=\"img/USB.png\" alt=\"USB\">\r\n                        <p>USB\r\n");
      out.write("                    </button>\r\n                    <button type=\"button\" onclick=\"pickCloudService()\">\r\n                        <img src=\"img/CloudDown.png\" alt=\"Cloud\">\r\n                        <p>Cloud\r\n                    </button>\r\n                    <button type=\"button\">\r\n                        <img src=\"img/Scan.png\" alt=\"Scan\">\r\n                        <p>Scan\r\n                    </button>\r\n                </div>\r\n            </form>\r\n\r\n            <form>\r\n                <div class=\"header\">\r\n                    <h2>Select Languages</h2>\r\n                </div>\r\n                <div class=\"main\">\r\n                    <p>From:</p>\r\n                    <button type=\"button\" class=\"longButton\">\r\n                       Spanish \r\n                    </button>\r\n\r\n                    <img src=\"img/arrow.png\" alt=\"arrow\">\r\n\r\n                    <p>To:</p>\r\n                    <button type=\"button\" class=\"longButton\">\r\n                       English \r\n                    </button>\r\n                </div>\r\n            </form>\r\n");
      out.write("\r\n            <iframe id=\"frame\" src=\"js/lib/pdfjs/web/viewer.jsp?file=%2FTranslatorApp/tempPDFs/temp.pdf\">\r\n               Something went wrong... \r\n            </iframe>\r\n            <form>\r\n                <div class=\"header\">\r\n                    <h2>Save To</h2>\r\n                </div>\r\n                <div class=\"main\">\r\n                    <button type=\"button\">\r\n                        <img src=\"img/USB.png\" alt=\"USB\">\r\n                        <p>USB\r\n                    </button>\r\n                    <button type=\"button\" onclick=\"pickCloudService()\">\r\n                        <img src=\"img/CloudUP.png\" alt=\"Cloud\">\r\n                        <p>Cloud\r\n                    </button>\r\n                    <a href=\"editDocument.jsp\">\r\n                     <button type=\"button\" onclick=\"\">\r\n                         <img src=\"img/Print.png\" alt=\"Print\">\r\n                         <p>Print\r\n                    </button>           \r\n                    </a>\r\n                </div>\r\n            </form>\r\n\r\n            <form>\r\n");
      out.write("                <div class=\"header\">\r\n                    <h2>Settings</h2>\r\n                </div>\r\n                <div class=\"main\">\r\n                    <button type=\"button\" class=\"longButton\">\r\n                       Lock Settings \r\n                    </button>\r\n                    <p>Accent Color:</p>\r\n                    <button type=\"button\" class=\"longButton\">\r\n                       Red \r\n                    </button>\r\n                    <button type=\"button\" class=\"longButton\">\r\n                       Set settings as default \r\n                    </button>           \r\n                </div>\r\n            </form>\r\n        </div>\r\n<!--              Overlay               -->\r\n        <div id=\"overlay\">\r\n            <form id=\"pickCloud\">\r\n                <div class=\"header\">\r\n                    <h2>Pick a cloud service</h2>\r\n                    <button type=\"button\" class=\"close\" onclick=\"exitOverlay()\">\r\n                        close\r\n                    </button>\r\n                </div>\r\n                <div class=\"main\">\r\n");
      out.write("                    <button type=\"button\" onclick=\"loginToCloud()\">\r\n                        <img src=\"img/gdrive.png\" alt=\"google drive\">\r\n                        <p>Google Drive</p>\r\n                    </button>\r\n                    <button type=\"button\" onclick=\"loginToCloud()\">\r\n                        <img src=\"img/mega.png\" alt=\"mega\">\r\n                        <p>Mega</p>\r\n                    </button>\r\n                    <button type=\"button\" onclick=\"loginToCloud()\">\r\n                        <img src=\"img/onedrive.png\" alt=\"one drive\">\r\n                        <p>One Drive</p>\r\n                    </button>\r\n                </div>\r\n            </form>\r\n\r\n            <form id=\"login\">\r\n                <div class=\"header\">\r\n                    <h2>Login</h2>\r\n                    <button type=\"button\" class=\"close\" onclick=\"exitOverlay()\">\r\n                        close\r\n                    </button>\r\n                </div>\r\n                <div class=\"main\">\r\n                    <input type=\"text\">\r\n                        Username\r\n");
      out.write("                    </input>\r\n                    <input type=\"text\">\r\n                        Passwords\r\n                    </input>\r\n                </div>\r\n            </form>\r\n        </div>\r\n    </div>\r\n</body>\r\n</html>");
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          } catch (Throwable t) {
      out = _jspx_out;
      if (out != null && out.getBufferSize() != 0)
        out.clearBuffer();
      if (pageContext != null) pageContext.handlePageException(t);
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(pageContext);
    }
  }
}
