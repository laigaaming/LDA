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
	String score=request.getParameter("score");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>成绩</title>
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
<link href="css/productdetail.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
<!--webfont-->
<!-- <link href='//fonts.googleapis.com/css?family=Quicksand:300,400,700' rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800' rel='stylesheet' type='text/css'> -->
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
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
							<!-- <div id="loginBox">                
						  <form id="loginForm">
			                <fieldset id="body">
			                	<fieldset>
			                          <label for="email">Email Address</label>
			                          <input type="text" name="email" id="email">
			                    </fieldset>
			                    <fieldset>
			                            <label for="password">Password</label>
			                            <input type="password" name="password" id="password">
			                     </fieldset>
			                    <input type="submit" id="login" value="Sign in">
			                	<label for="checkbox"><input type="checkbox" id="checkbox"> <i>Remember me</i></label>
			            	</fieldset>
			                 <span><a href="#">Forgot your password?</a></span>
						   </form>
				        </div> -->
						</div>
					</li>
				</ul>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<div class="single">
		<div class="container">
			<div class="single_box1">
				<!-- <div class="col-sm-5 single_left">
				<img src="images/single.jpg" class="img-responsive" alt=""/>
			 </div> -->
				<div class="col-sm-7 col_6" style="width: auto;">
					<ul class="size">
						<h3>
							<strong>你的成绩</strong>
						</h3>
						<!-- <li><a href="#">S</a></li>
					<li><a href="#">M</a></li>
					<li><a href="#">L</a></li>
					<li><a href="#">XL</a></li>
					<li><a href="#">2XL</a></li>
					<li><a href="#">3XL</a></li> -->
					</ul>
					<!-- <li>相似度：60%</li> -->
					<li>得分：<%=score %></li>
				</div>
				<div class="clearfix"></div>
			</div>

			<div class="tags">
				<div class="col-sm-7 col_6" style="width: 100%;">
					<h4>请点击右上角退出，并关闭考试页面。</h4>
				</div>
				<div class="clearfix"></div>
				</ul>
			</div>
		</div>
	</div>
	<!-- <div class="grid_2">
		<div class="container"> 
			<div class="col-md-3 col_2">
				<h3>Stock Photo<br>Categories</h3>
			</div>
			<div class="col-md-9 col_5">
				<div class="col_1_of_5 span_1_of_5">
					<ul class="list1">
					    <li><a href="stock.html">Abstract</a></li>
			            <li><a href="stock.html">Animals/Wildlife</a></li>
			            <li><a href="stock.html">The Arts</a></li>
			            <li><a href="stock.html">Backgrounds/Textures</a></li>
			            <li><a href="stock.html">Beauty/Fashion</a></li>
			            <li><a href="stock.html">Buildings/Landmarks</a></li>
		            </ul>
				</div>
				<div class="col_1_of_5 span_1_of_5">
					<ul class="list1">
					    <li><a href="stock.html">Business/Finance</a></li>
			            <li><a href="stock.html">Celebrities</a></li>
			            <li><a href="stock.html">Editorial</a></li>
			            <li><a href="stock.html">Education</a></li>
			            <li><a href="stock.html">Food and Drink</a></li>
			            <li><a href="stock.html">Healthcare/Medical</a></li>
		            </ul>
				</div>
				<div class="col_1_of_5 span_1_of_5">
					<ul class="list1">
					    <li><a href="stock.html">Holidays</a></li>
			            <li><a href="stock.html">Illustrations/Clip-Art</a></li>
			            <li><a href="stock.html">Industrial</a></li>
			            <li><a href="stock.html">Interiors</a></li>
			            <li><a href="stock.html">Miscellaneous</a></li>
			            <li><a href="stock.html">Model Released Only</a></li>
		            </ul>
				</div>
				<div class="col_1_of_5 span_1_of_5">
					<ul class="list1">
					    <li><a href="stock.html">Nature</a></li>
			            <li><a href="stock.html">Objects</a></li>
			            <li><a href="stock.html">Parks/Outdoor</a></li>
			            <li><a href="stock.html">People</a></li>
			            <li><a href="stock.html">Religion</a></li>
			            <li><a href="stock.html">Science</a></li>
		            </ul>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"> </div>
		</div>
	</div> -->
	<div class="grid_3">
		<div class="container">
			<!-- <ul id="footer-links">
			<li><a href="#">Terms of Use</a></li>
			<li><a href="#">Royalty Free License</a></li>
			<li><a href="#">Extended License</a></li>
			<li><a href="#">Privacy</a></li>
			<li><a href="support.html">Support</a></li>
			<li><a href="about.html">About Us</a></li>
			<li><a href="faq.html">FAQ</a></li>
			<li><a href="#">Categories</a></li>
         </ul> -->
			<p>@ Copyright. All rights reserved</p>
		</div>
	</div>
</body>
</html>