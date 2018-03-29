import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Created by 95112 on 2018/3/29.
 */
public class DisplayHeader extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out =response.getWriter();
        String title = "HTTP head";
        String doctype ="<!DOCTYPE html>\n";
        out.println(doctype+
        "<head><meta charset=\"utf-8\"><title>"+
        title+"</title></head>\n"+
        "<body><h1 align=\"center\">"+title+"</h1>"+"<table>"+
        "<tr><th>Header</th><th>value</th></tr>\n");

        Enumeration headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String paramName = (String)headerNames.nextElement();
            out.println("<tr><td>"+paramName+"</td>\n");
            String paramValue = request.getHeader(paramName);
            out.println("<td>"+paramValue+"</td></tr>");
        }
        out.print("</table></body></html>");
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        doGet(request,response);
    }
}
