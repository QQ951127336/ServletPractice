import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

/**
 * Created by 95112 on 2018/3/29.
 */
public class ReadCookies extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public ReadCookies(){
        super();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        Cookie[] cookies = null;
        cookies = request.getCookies();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title ="Delete Cookie Example";
        String doctype = "<!DOCTYPE html>\n";
        out.println(doctype+
            "<html><head><title>"+title+"</title></head>"+
            "<body>");
        if (cookies != null){
            out.println("<h1>cookie values</h1>");
            for (Cookie cookie : cookies){
                if (cookie.getName().compareTo("name")==0){
                    cookie.setMaxAge(0);
                    response.addCookie((cookie));
                    out.print("it has been deleted that cookie : "+
                    cookie.getName() + "<br/>");
                }
                out.print("name :" +cookie.getName()+",");
                out.print("value : "+ URLDecoder.decode(cookie.getValue(),"utf-8")+"<br>");
            }
        }else{
            out.println("<h2> no cookie founds</h2>");
        }
        out.println("</body></html>");
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        doGet(request,response);
    }
}
