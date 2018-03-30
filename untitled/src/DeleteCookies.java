import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 95112 on 2018/3/29.
 */
public class DeleteCookies extends HttpServlet {
    private static final long serialVersionUID=1L;
    public DeleteCookies(){
        super();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        Cookie[] cookies = null;
        cookies = request.getCookies();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title = "Delete Cookie";
        String docType = "<!DOCTYPE html>\n";
        out.println(docType+
            "<html><head><title>"+ title+"</title></head>"+
            "<body>");
        if (cookies != null){
            out.println("<h2>cookie`s name and value</h2>");
            for (Cookie cookie : cookies){
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                out.println("<p>"+cookie.getName()+" : "+cookie.getValue()+"</p><br>");
            }
        }else{
            out.print("<h2>No cookie</h2>");
        }
        out.println("</body></html>");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
