import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.Base64;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        Base64.Encoder encoder = Base64.getEncoder();
        String code = getImageStr("C:\\Users\\95112\\Documents\\tempFile\\savedFile\\9121A8C8CD457FB4395CB21921933423.jpg");

//        try {
//            FileInputStream in = new FileInputStream("C:\\Users\\95112\\Documents\\tempFile\\savedFile\\9121A8C8CD457FB4395CB21921933423.jpg");
//            byte[] buffer = new byte[1024];
//            int count ;
//            while ((count = in.read(buffer)) != -1) {
//                code = encoder.encodeToString(buffer);
//            }
//            in.close();
//            System.out.println("Successful!");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println(code);


    }
    public static String getImageStr(String imgFile) {
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
        File out = new File("C:\\Users\\95112\\Documents\\tempFile\\savedFile\\saveCode.txt");
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
