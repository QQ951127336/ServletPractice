import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 95112 on 2018/3/28.
 */
public class HelloWorld extends HttpServlet {
    private String message;
    @Override
    public void init() throws ServletException{
        message = "Hello,my name is ZhouKeyu";
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>"+message+"</h1>");
    }
    @Override
    public void destroy(){

    }
}
