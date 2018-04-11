package jsp.urlmap;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpContext;
import org.osgi.service.http.HttpService;

public class MapService {
    public void addUrl(BundleContext context) {
        try {
            HttpService http;
            ServiceReference ref = context.getServiceReference("org.osgi.service.http.HttpService");
            http = (HttpService)context.getService(ref);
            HttpContext ctx = http.createDefaultHttpContext();
            http.registerResources("/TranslatorApp","WebContents", ctx);
            http.registerServlet("/TranslatorApp/JobServlet",new translatorapp.JobServlet(), null, ctx);
            http.registerServlet("/TranslatorApp/editDocument.jsp",new jsp.editDocument_jsp(), null, ctx);
            http.registerServlet("/TranslatorApp/Home.jsp",new jsp.Home_jsp(), null, ctx);
            http.registerServlet("/TranslatorApp/js/lib/pdfjs/web/viewer.jsp",new jsp.js.lib.pdfjs.web.viewer_jsp(), null, ctx);
        } catch (Exception e) {
        }
    }
    public void removeUrl(BundleContext context){
        try {
            HttpService http;
            ServiceReference ref = context.getServiceReference("org.osgi.service.http.HttpService");
            http = (HttpService)context.getService(ref);
            http.unregister("/TranslatorApp");
            http.unregister("/TranslatorApp/JobServlet");
            http.unregister("/TranslatorApp/editDocument.jsp");
            http.unregister("/TranslatorApp/Home.jsp");
            http.unregister("/TranslatorApp/js/lib/pdfjs/web/viewer.jsp");
        } catch (Exception e) {
        }
    }
}
