import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import sun.misc.BASE64Encoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by 95112 on 2018/3/30.
 */
public class YigoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final int MEMORY_THRESHOLD = 1024*1024*3;
    private static final int MAX_FILE_SIZE = 1024*1024*40;
    private static final int MAX_REQUEST_SIZE = 1024*1024*50;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        if (!ServletFileUpload.isMultipartContent(request)){
            PrintWriter out = response.getWriter();
            out.println("It is not multipart file.");
            out.flush();
            out.close();
            return;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        fileUpload.setSizeMax(MAX_REQUEST_SIZE);
        fileUpload.setFileSizeMax(MAX_FILE_SIZE);
        fileUpload.setHeaderEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try{
            List<FileItem> formItems = fileUpload.parseRequest(request);
            if (formItems != null && formItems.size() > 0){
                for (FileItem item:formItems){
                    if (!item.isFormField()){
                        byte[] data = getImgBinary(item.getInputStream());
                        String picCode = getImgBase64(data);
                        out.write(picCode);
                    }
                }
            }
        } catch (FileUploadException e) {
            throw new FileUploadIOException(e.getMessage());
        }finally {
            out.close();
        }
    }
    private byte[] getImgBinary(InputStream inputStream) throws IOException{
        InputStream picInputStream = inputStream;
        if (picInputStream == null)
            return null;
        byte[] data = null;
        try{
            data = new byte[picInputStream.available()];
            picInputStream.read(data);
            picInputStream.close();
        } catch (IOException e) {
            throw e;
        }
        return data;
    }
    private String getImgBase64(byte[] data){
        BASE64Encoder encoder = new BASE64Encoder();
        String picCode = encoder.encode(data);
        return picCode;
    }
    class FileUploadIOException extends IOException{
        public FileUploadIOException(String message){super("没有得到上传文件 : "+message);}
    }
}
