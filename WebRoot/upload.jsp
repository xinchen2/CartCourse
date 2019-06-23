<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
     <form action="UploadHandleServlet" enctype="multipart/form-data" method="post">
         上传用户：<input type="text" name="username"> <br/>
         上传文件1：<input type="file" name="file1" id="file1"><br/>
         上传文件2：<input type="file" name="file2" id="file2"><br/>
         <input type="submit" value="上传">
     </form>
</body>
</html>