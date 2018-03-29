<%--
  Created by IntelliJ IDEA.
  User: 95112
  Date: 2018/3/27
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <script type="text/javascript" src="jquery-1.10.2.js"></script>
</head>
<body>
<h1 id="show">Show Something</h1>
<button>click</button>
<script>
    $("button").click(function(){
        $("#show").load("text.txt",function(responseTxt,statusTxt,xhr){
            if(statusTxt=="success")
                alert("Yes!");
            else{
                alert("NO");
            }
        })
    });

</script>
</body>
</html>
