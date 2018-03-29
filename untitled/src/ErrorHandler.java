import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 95112 on 2018/3/29.
 */
public class ErrorHandler extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        Throwable throwable =(Throwable)request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String)request.getAttribute("javax.servlet.error.servlet_name");
        if(servletName == null) servletName = "UnKnown";
        String servletURI = (String)request.getAttribute("javax.servlet.error.servlet_uri");
        if (servletURI == null) servletName = "UnKnown";
        response.setContentType("text.html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title = "Error/Exception";
        String doctype = "<!DOCTYPE html>\n";
        out.println(doctype+"\n"+
                "<html><head><title>"+title+"</title></head>"+
                "<body><h1>title : "+title+"</h1><");
        if (throwable == null && statusCode == null){
            out.println("<h2>error messages lost</h2>");
            out.println("please back to  <a href=\""+response.encodeURL("http://localhost:8080/")+"\">home page</a>");
        }else if(statusCode !=null){
            out.println("error code : "+statusCode);
        }else{
            out.println("<h2>error message</h2>");
            out.println("Servlet Name: "+servletName+"<br>"+
                        "Servlet Uri" + servletURI+"<br>"+
                        "Servlet Exception type: "+throwable.getClass().getName()+"<br>"+
                        "Exception message: "+throwable.getMessage());
        }
        out.print("</body></html>");
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        doGet(request,response);
    }
}
