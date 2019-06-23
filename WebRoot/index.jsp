<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, 
        initial-scale=1, maximum-scale=1, user-scalable=no">
		<title></title>
		<link href="css/reset.css" type="text/css"  rel="stylesheet"/>
		<link href="css/style.css" type="text/css"  rel="stylesheet"/>
		<style type="text/css">
			.count form input:first-child{
				border-radius: 3px 3px 0 0;
			}
			.count form input:nth-child(2){
				border-radius: 0 0 3px 3px;
			}
			.count form input:nth-child(3){
				margin-top:20px;
				background: white;
				color: black;
				border-radius: 3px;
				width:50%;
			}
			#checkCodeDiv{
				flaot:left;
			}
		</style>
	</head>
	<body>
		<div id="code" style="display: none;">${code}</div>
		<div id="wrapper">
			<header><img src="images/1a.png" /></header>
			<article class="count">
				<form action="LoginServlet" method="post">
					<input type="text" name="exampleInputEmail1" placeholder="请输入帐号" />
					<input type="password" name="exampleInputPassword1" placeholder="请输入密码" />
					<div id="checkCodeDiv">
						<input type="text" class="form-control" id="inputPassword3" name="checkCode" placeholder="请输入验证码">
					<!--  	<img id="checkCodeImg" src="ValidateCodeServlet"/>
						<img id="checkCodeImg" src="CreateCode"/>  -->
						<img id="checkCodeImg" src="CheckCodeServlet"/>
					</div>
					<input type="submit"  value="登录"/>
				</form>
				<div>
					<span>
						<a href="register.html">立即注册！</a>
					</span>
					<span>
						<a href="#">忘记密码？</a>
					</span>
				</div>
			</article>
			<footer>
				<ul>
					<li><a href="#"><img src="images/2.png"/></a></li>
					<li><a href="#"><img src="images/3.png"/></a></li>
					<li><a href="#"><img src="images/4.png"/></a></li>
				</ul>
			</footer>
		</div>
	</body>
	
	<script type="text/javascript" src="./js/jquery-3.3.1.js"></script>
	<script type="text/javascript">
		code = $("#code").html();
		console.log(code);
		if(code==1)
			alert("用户名不能为空");
		else if(code==2)
			alert("邮箱不能为空");
		else if(code==3)
			alert("用户名或密码错误");
		else if(code==4)
			alert("验证码错误");
	</script>
	
</html>
