import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by 95112 on 2018/3/29.
 */
public class UploadTest extends HttpServlet {
    private static final long serialVersionUID =1L;
    private static final String UPLOAD_DIRECTORY ="upload";

    private static final int MEMORY_THRESHOLD = 1024*1024*3;
    private static final int MAX_FILE_SIZE = 1024*1024*40;
    private static final int MAX_REQUEST_SIZE = 1024*1024*50;

    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        if (!ServletFileUpload.isMultipartContent(request)){
            PrintWriter writer = response.getWriter();
            writer.println("Error: form must contain enctype=multipart/fom-data");
            writer.flush();
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        upload.setHeaderEncoding("UTF-8");
        String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists())
            uploadDir.mkdir();

        try{
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0){
                for(FileItem item : formItems){
                    if (!item.isFormField()){
                        String fileName = new File(item.getName()).getName();
                        String filePath = upload + File.separator + fileName;
                        File storeFile =new File(filePath);
                        System.out.println(filePath);
                        item.write(storeFile);
                        request.setAttribute("message","successful");
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getServletContext().getRequestDispatcher("/message.jsp").forward(
                request, response);
    }
}
