<%--
  Created by IntelliJ IDEA.
  User: 95112
  Date: 2018/3/25
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="jquery-1.10.2.js"></script>
</head>
<body id="parent">
<input type="file" id="file1" /><br />
<input type="button" id="upload" value="上传" />
<p id="p1">123455</p>
<script type="text/javascript">
    var response ;
    $(function () {
        $("#upload").click(function () {
            var formData = new FormData();
            formData.append("myfile", document.getElementById("file1").files[0]);

            response = $.ajax({
                async:false,
                url: "/UploadServlet",
                type: "POST",
                data: formData,
                /**
                 *必须false才会自动加上正确的Content-Type
                 */
                contentType: false,
                /**
                 * 必须false才会避开jQuery对 formdata 的默认处理
                 * XMLHttpRequest会对 formdata 进行正确的处理
                 */
                processData: false,
                success: function (data) {
                    if (data.status == "true") {
                        alert("上传成功！");
                    }
                    if (data.status == "error") {
                        alert(data.msg);
                    }

                },
                error: function () {
                    alert("上传失败！");

                }
            });
            alert(response.responseText);
            document.getElementById("p1").innerHTML='<img id="img" src="'+response.responseText+'"/>'
        });
    });


</script>
</body>
</html>