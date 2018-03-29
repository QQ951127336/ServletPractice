/**
 * Created by 95112 on 2018/3/29.
 */
import sun.rmi.runtime.Log;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

public class Refresh extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        response.setIntHeader("Refresh",5);
        response.setContentType("text/html;charset=UTF-8");
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        PrintWriter out = response.getWriter();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = simpleDateFormat.format(date);
        String title = "Header Setting";
        String doctype = "<!DOCTYPE html>\n";
        Logger logger = Logger.getLogger("Refresh");
        logger.info(nowTime);
        out.println(doctype +
                "<html>\n" +
                "<head><title>" + title + "</title></head>" +
                "<body>\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<p>当前时间 : " + nowTime+"</p>");

    }
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        doGet(request,response);
    }
}
