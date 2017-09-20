<%@page import="bll.StudentBLL"
		import="java.util.*"
%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<% 
	long identity=Long.parseLong(request.getParameter("identity"));
	StudentBLL studentBLL=new StudentBLL();
	Map sInfo=studentBLL.getStudentInfo(identity);
	String name=sInfo.get("name").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>首页</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Photo-Hub Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	
</script>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Custom Theme files -->
<link href="css/productlist.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
<!--webfont-->
<!-- <link href='//fonts.useso.com/css?family=Quicksand:300,400,700' rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800' rel='stylesheet' type='text/css'> -->
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script src="js/easyResponsiveTabs.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#horizontalTab').easyResponsiveTabs({
			type : 'default', //Types: default, vertical, accordion           
			width : 'auto', //auto or any width like 600px
			fit : true
		// 100% fit in a container
		});
	});
</script>
<script src="js/menu_jquery.js"></script>
</head>
<body>
	<div class="header">
		<div class="container">
			<div class="logo">
				<h1>
					<a href="index.jsp">考试系统</a>
				</h1>
			</div>
			<div class="top_right">
				<ul>
					<li><a href="signin.jsp">退出</a></li>|
					<li class="login">
						<div id="loginContainer">
							<a href="#" id="loginButton"><span><%=name %></span></a>
							<div id="loginBox">
								<form id="loginForm">
									<fieldset id="body">
										<fieldset>
											<label for="email">Email Address</label> <input type="text"
												name="email" id="email">
										</fieldset>
										<fieldset>
											<label for="password">Password</label> <input type="password"
												name="password" id="password">
										</fieldset>
										<input type="submit" id="login" value="Sign in"> <label
											for="checkbox"><input type="checkbox" id="checkbox">
											<i>Remember me</i></label>
									</fieldset>
									<span><a href="#">Forgot your password?</a></span>
								</form>
							</div>
						</div>
					</li>
				</ul>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>

	<div class="col-md-10 sap_tabs" style="width:100%;">
		<center>
			<ul style="font-size: 20px;">
				<strong>请选择科目</strong>
			</ul>
		</center>
		<div id="horizontalTab"
			style="display: block; width: 100%; margin:0px;">
			<ul class="resp-tabs-list">

				<li class="resp-tab-item" aria-controls="tab_item-0" role="tab"><span>语文</span></li>
				<li class="resp-tab-item" aria-controls="tab_item-1" role="tab"><span>数学</span></li>
				<li class="resp-tab-item" aria-controls="tab_item-2" role="tab"><span>英语</span></li>
				<li class="resp-tab-item" aria-controls="tab_item-3" role="tab"><span>政治</span></li>
				<li class="resp-tab-item" aria-controls="tab_item-4" role="tab"><span>物理</span></li>
				<li class="resp-tab-item" aria-controls="tab_item-5" role="tab"><span>化学</span></li>
				<li class="resp-tab-item" aria-controls="tab_item-6" role="tab"><span>生物</span></li>
				<div class="clearfix"></div>
			</ul>
			<div class="resp-tabs-container">
				<div class="tab-1 resp-tab-content" aria-labelledby="tab_item-0">
					<ul class="tab_img">
						
						<li style="margin-left:30%;"><a href="#">
								<div class="tab_desc">
									<center>
										<p style="font-size: 20px;">主观题</p>
									</center>

								</div>
						</a></li>
						
						<li class="last"><a href="#">
								<div class="tab_desc">
									<center>
										<p style="font-size: 20px;">客观题</p>
									</center>

								</div>
						</a></li>
						
						<div class="clearfix"></div>
						
					</ul>
				</div>
				<div class="tab-1 resp-tab-content" aria-labelledby="tab_item-1">
					<ul class="tab_img">
						<li style="margin-left:30%;"><a href="#">
								<div class="tab_desc">
									<center>
										<p style="font-size: 20px;">主观题</p>
									</center>

								</div>
						</a></li>
						
						<li class="last"><a href="#">
								<div class="tab_desc">
									<center>
										<p style="font-size: 20px;">客观题</p>
									</center>

								</div>
						</a></li>

						<div class="clearfix"></div>
					</ul>
				</div>
				<div class="tab-1 resp-tab-content" aria-labelledby="tab_item-2">
					<ul class="tab_img">
						<li style="margin-left:30%;"><a href="#">
								<div class="tab_desc">
									<center>
										<p style="font-size: 20px;">主观题</p>
									</center>

								</div>
						</a></li>
						
						<li class="last"><a href="#">
								<div class="tab_desc">
									<center>
										<p style="font-size: 20px;">客观题</p>
									</center>

								</div>
						</a></li>

						<div class="clearfix"></div>
					</ul>
				</div>
				<div class="tab-1 resp-tab-content" aria-labelledby="tab_item-3">
					<ul class="tab_img">
						<li style="margin-left:30%;"><a href="exam.jsp?identity=<%=identity %>">
								<div class="tab_desc">
									<center>
										<p style="font-size: 20px;">主观题</p>
									</center>

								</div>
						</a></li>
						
						<li class="last"><a href="#">
								<div class="tab_desc">
									<center>
										<p style="font-size: 20px;">客观题</p>
									</center>

								</div>
						</a></li>

						<div class="clearfix"></div>
					</ul>
				</div>
				<div class="tab-1 resp-tab-content" aria-labelledby="tab_item-4">
					<ul class="tab_img">
						<li style="margin-left:30%;"><a href="#">
								<div class="tab_desc">
									<center>
										<p style="font-size: 20px;">主观题</p>
									</center>

								</div>
						</a></li>
						
						<li class="last"><a href="#">
								<div class="tab_desc">
									<center>
										<p style="font-size: 20px;">客观题</p>
									</center>

								</div>
						</a></li>

						<div class="clearfix"></div>
					</ul>
				</div>
				<div class="tab-1 resp-tab-content" aria-labelledby="tab_item-5">
					<ul class="tab_img">
						<li style="margin-left:30%;"><a href="#">
								<div class="tab_desc">
									<center>
										<p style="font-size: 20px;">主观题</p>
									</center>

								</div>
						</a></li>
						
						<li class="last"><a href="#">
								<div class="tab_desc">
									<center>
										<p style="font-size: 20px;">客观题</p>
									</center>

								</div>
						</a></li>

						<div class="clearfix"></div>
					</ul>
				</div>
				<div class="tab-1 resp-tab-content" aria-labelledby="tab_item-6">
					<ul class="tab_img">
						<li style="margin-left:30%;"><a href="#">
								<div class="tab_desc">
									<center>
										<p style="font-size: 20px;">主观题</p>
									</center>

								</div>
						</a></li>
						
						<li class="last"><a href="#">
								<div class="tab_desc">
									<center>
										<p style="font-size: 20px;">客观题</p>
									</center>

								</div>
						</a></li>

						<div class="clearfix"></div>
					</ul>
				</div>
			</div>
		</div>
		<!-- <ul class="pagination">
				<li><a href="#" aria-label="Previous"> <span
						aria-hidden="true">«</span>
				</a></li>
				<li><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#" aria-label="Next"> <span aria-hidden="true">»</span>
				</a></li>
			</ul> -->
	</div>
	<div class="clearfix"></div>
	</div>

	<div class="grid_3">
		<div class="container">

			<p>Copyright © 2015 . All Rights Reserved</p>
		</div>
	</div>
</body>
</html>