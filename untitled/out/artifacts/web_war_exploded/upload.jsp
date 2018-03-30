<%--
  Created by IntelliJ IDEA.
  User: 95112
  Date: 2018/3/23
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>文件上传</title>
    <script type="text/javascript" src="jquery-1.10.2.js"></script>
    <style type="text/css">
         .img
        {
            width:200px;
            height:200px;

        }
    </style>
</head>
<body>
<h1>文件上传</h1>
<form method="post" action="/UploadServlet" target="send" enctype="multipart/form-data">
    选择一个文件:
    <input type="file" onchange="" name="uploadFile" />
    <br/><br/>
    <input type="submit"  value="上传" />
</form>
<iframe name="send" id="aimIframe" style="width:200px;height:200px"></iframe>

<div id="PicWindow"></div>
<script type="text/javascript">
    $("#aimIframe").load(function(){
        var data = $(this).contents().find('#aimImg').html();
        if (data!="undefine"){
            $("body").append(data);
        }
    })
</script>
</body>
</html>
