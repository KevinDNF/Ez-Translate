package jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class editDocument_jsp extends HttpJspBase {


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
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n<html>\r\n<head>\r\n<link rel=\"stylesheet\" href=\"js/lib/pdfjs/web/viewer.css\">\r\n<link rel=\"stylesheet\" href=\"css/styleEdit.css\">\r\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n<title>Insert title here</title>\r\n<script src=\"js/translate.js\"></script>\r\n</head>\r\n\t\r\n\t<button onclick=\"translateDocument()\">EDIT DOCUMENT</button>\t\r\n\t\r\n\t<iframe id=\"frame\" src=\"js/lib/pdfjs/web/viewer.jsp?file=%2FTranslatorApp/tempPDFs/temp.pdf\">\r\n\t\tOriginal Document goes here.\r\n\t</iframe>\r\n\r\n\t<div id=\"edited\">\r\n\t\tEdited Document will go here\t\r\n\t</div>\r\n\r\n</html>\r\n");
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