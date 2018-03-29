import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;


public class HelloForm extends HttpServlet {
    private Logger logger = Logger.getLogger("HelloForm");
    private static final long serialVersionUID =1L;
    public HelloForm(){
        super();
    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title = "Get test";
        String name = new String(request.getParameter("name").getBytes("8859_1"),"utf-8");
        logger.info(name);
        String docType = "<!DOCTYPE html> \n";
        out.println(docType+
        "<html>\n" +
        "<head><meta lang='en'/><title>"+title+"</title></head>\n" +
        "<body bgcolor=\"#f0f0f0\">\n+" +
        "<h1 align=\"center\">"+title+"</h1>\n"+
        "<ul>\n"+
        "<li><b>站点名</b>"+
        name+"\n"+
        "<li><b>website</b>"+
        request.getParameter("url")+"\n"+
        "</ul>\n"+
        "</body></html>");
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        doGet(request,response);
    }
}
