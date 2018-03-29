<%--
  Created by IntelliJ IDEA.
  User: 95112
  Date: 2018/3/26
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="jquery-1.10.2.js" type="text/javascript"></script>
    <script src="ajaxfileupload.js" type="text/javascript"></script>
    <title>Title</title>
</head>
<body>
<p><input type="file" id="file1" name="file" /></p>
<input type="button" value="上传" />
<p><img id="img1" alt="上传成功啦" src="" /></p>
<script type="text/javascript">
    $(function () {
        $(":button").click(function () {
            ajaxFileUpload();
        })
    })

    function ajaxFileUpload() {
        var response = $.ajaxFileUpload
        (
            {
                url: '/UploadServlet', //用于文件上传的服务器端请求地址
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: 'file1', //文件上传域的ID//返回值类型 一般设置为json
                async:false,
                success: function (data, status)  //服务器成功响应处理函数
                {
                    alert(data);
//                    $("#img1").attr("src", data.imgurl);
                    if (typeof (data.error) != 'undefined') {
                        if (data.error != '') {
                            alert(data.error);
                        } else {
                            alert(data.msg);
                        }
                    }
                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                    alert(e);
                }
            }
        );
        alert(response.responseText);
        return false;
    }
</script>
</body>
</html>
