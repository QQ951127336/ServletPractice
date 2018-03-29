/**
 * Created by 95112 on 2018/3/23.
 */
import sun.misc.BASE64Encoder;
import java.io.*;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet{
    private Logger logger = Logger.getLogger("Picture Upload in wangEditor");
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InputStream fileSource = request.getInputStream();
        //临时文件，用于除去Textfield前三行请求体信息
        String tempFileName = "C:\\Users\\95112\\Documents\\tempFile\\tempFile";
        File tempFile = new File(tempFileName);
        if (!tempFile.exists())
            tempFile.createNewFile();
        FileOutputStream outputStream = new FileOutputStream(tempFile);
        byte b[] = new byte[1024];
        int n;
        while ((n = fileSource.read(b)) != -1) {
            outputStream.write(b, 0, n);
        }
        //关闭输出流、输入流
        outputStream.close();
        fileSource.close();
        //获取上传文件的名称
        RandomAccessFile randomFile = new RandomAccessFile(tempFile, "r");
//      l = new String(l.getBytes("8859_1"),"gbk");
        String str2 = randomFile.readLine();
        //编码转换
        str2 = new String(str2.getBytes("8859_1"), "utf-8");
        String str = randomFile.readLine();
        str = new String(str.getBytes("8859_1"), "utf-8");
        logger.info(str);
        int beginIndex = str.lastIndexOf("=") + 2;
        int endIndex = str.lastIndexOf("\"");
        String filename = str.substring(beginIndex, endIndex);
        String[] tempString = null;
        if (filename.contains("\\")){
             tempString = filename.split("\\\\");
             filename = tempString[tempString.length-1];
        }
        logger.info("filename:" + filename);

        //重新定位文件指针到文件头
        randomFile.seek(0);
        long startPosition = 0;
        int i = 1;
        //获取文件内容 开始位置
        while ((n = randomFile.readByte()) != -1 && i <= 4) {
            if (n == '\n') {
                startPosition = randomFile.getFilePointer();
                i++;
            }
        }
        startPosition = randomFile.getFilePointer() - 1;
        //获取文件内容 结束位置
        randomFile.seek(randomFile.length());
        long endPosition = randomFile.getFilePointer();
        int j = 1;
        while (endPosition >= 0 && j <= 2) {
            endPosition--;
            randomFile.seek(endPosition);
            if (randomFile.readByte() == '\n') {
                j++;
            }
        }
        endPosition = endPosition - 1;
        //--------------------保存base64文件，后期接入数据库删除----------------
        //设置保存上传文件的路径
        //路径可以自行设置
        String realPath = "C:\\Users\\95112\\Documents\\tempFile\\savedFile";
        File fileupload = new File(realPath);
        logger.info(realPath);
        if (!fileupload.exists()) {
            fileupload.mkdir();
        }
        File saveFile = new File(realPath, filename);
        RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile, "rw");
        //从临时文件当中读取文件内容（根据起止位置获取）
        randomFile.seek(startPosition);
        while (startPosition < endPosition) {
            randomAccessFile.write(randomFile.readByte());
            startPosition = randomFile.getFilePointer();
        }
        //关闭输入输出流、删除临时文件
        randomAccessFile.close();
        //----------------------------------------------------------------------
        randomFile.close();
        tempFile.delete();
        response.getWriter().println("<head></head><body><img id='aim' src='"+getImageStr(realPath+"\\"+filename,realPath+"\\"+filename+".code")+"'/></body>");
    }
    private String getImageStr(String imgFile,String outCodeFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        File out = new File(outCodeFile);
        String code = encoder.encode(data);
        String[] temp = imgFile.split("\\.");
        if (temp.length>2) {
            code = "data:image/" + temp[1] + ";base64," + code;
        }else{
            code = "data:image/" +  "jpeg;base64," + code;
        }
        try {
            if (!out.exists()) {
                out.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(out));
            writer.write(code);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code ;

    }
}