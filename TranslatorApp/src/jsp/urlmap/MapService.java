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
            http.registerServlet("/TranslatorApp/Home.jsp",new jsp.Home_jsp(), null, ctx);
        } catch (Exception e) {
        }
    }
    public void removeUrl(BundleContext context){
        try {
            HttpService http;
            ServiceReference ref = context.getServiceReference("org.osgi.service.http.HttpService");
            http = (HttpService)context.getService(ref);
            http.unregister("/TranslatorApp");
            http.unregister("/TranslatorApp/Home.jsp");
        } catch (Exception e) {
        }
    }
}
