import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

/**
 * Created by 95112 on 2018/3/29.
 */
public class CookieDeal extends HttpServlet {
    private static final long seralVersionUID = 1L;
    public CookieDeal(){
        super();
    }
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        Cookie name = new Cookie("name",
                URLEncoder.encode(request.getParameter("name"),"UTF-8"));
        Cookie url = new Cookie("url", request.getParameter("url"));
        name.setMaxAge(60*60);
        url.setMaxAge(60*60);
        response.addCookie(name);
        response.addCookie(url);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title = "Setting Cookie";
        String doctype = "<!DOCTYPE html>\n";
        out.println(doctype+
                "<html>\n<head><title>"+title+"</title></head>"+
                "</body><h1>"+title+"</h1>"+
                "<ul>\n<li>Web name:"+request.getParameter("name")+
                "</li><li>Web URL:"+request.getParameter("url")+"</li></ul></body></html>");
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
