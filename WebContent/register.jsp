<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>注册</title>
<!-- for-mobile-apps -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Manufactory Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	
</script>
<!-- //for-mobile-apps -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/register.css" rel="stylesheet" type="text/css"
	media="all" />
<!-- js -->
<script src="js/jquery-1.11.1.min.js"></script>
<!-- //js -->
<!-- start-smoth-scrolling -->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1000);
		});
	});
</script>
<!-- start-smoth-scrolling -->

<!-- <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Montserrat+Alternates:400,700'
	rel='stylesheet' type='text/css'> -->
</head>
<body>
	<!-- banner -->
	<div class="banner">
		<div class="container">
			<div class="header">
				<div class="banner-left">
					<a href="index.html"><i class="glyphicon glyphicon-wrench"
						aria-hidden="true"><strong></i>考试系统</strong></a>
				</div>
				<!-- <div class="main-in">
					<section>
					<button id="showLeft" class="navig"></button>
					</section>
				</div> -->
				<div class="clearfix"></div>
				<!--- Navigation from Right To Left --->
				<link rel="stylesheet" type="text/css" href="css/component.css" />

				<!--- Navigation from Right To Left --->
				<div class="clearfix"></div>
			</div>
			<div class="banner-info">
				<div class="banner-info-left">
					<h1>注册</h1>
					<form method="post" action="RegisterForm">
						<input type="text" name="identity" placeholder="请输入学号"
							onfocus="this.value = '';" required=""> 
						请选择班级
						<select
							name="class">
							<option value="网络工程1201">网络工程1201</option>
							<option value="计算机1201">计算机1201</option>
							<option value="计算机1202">计算机1202</option>
							<option value="计算机1203">计算机1203</option>
						</select> <input type="text" name="name" placeholder="请输入姓名"
							onfocus="this.value = '';" required=""> <input id="pwd1"
							name="password" type="password" placeholder="请输入密码"
							onfocus="this.value = '';" required=""> <input id="pwd2"
							type="password" placeholder="请再次输入密码" onfocus="this.value = '';"
							required=""> <input type="submit" value="注册"
							onclick="checkPWD()"> <input type="reset" value="取消">
					</form>
				</div>
				<div class="banner-info-right">
					<h2 style="color: transparent;">Sed ut perspiciatis unde omnis
						iste natus error laudantium</h2>

				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!-- //banner -->

	<!-- footer -->
	<div class="footer">
		<div class="container">
			<div class="footer-left">
				<p>@ Copyright. All rights reserved</p>
			</div>
			<div class="footer-right"></div>
			<div class="clearfix"></div>
		</div>
	</div>
	<!-- //footer -->
	<!-- here stars scrolling icon -->
	<script type="text/javascript">
		$(document).ready(function() {
			/*
				var defaults = {
				containerID: 'toTop', // fading element id
				containerHoverID: 'toTopHover', // fading element hover id
				scrollSpeed: 1200,
				easingType: 'linear' 
				};
			 */

			$().UItoTop({
				easingType : 'easeOutQuart'
			});

		});

		function checkPWD() {
			// 			alert("123");
			var pwd1 = document.getElementById("pwd1").value;
			var pwd2 = document.getElementById("pwd2").value;
			if (pwd1 !== pwd2) {
				alert("前后输入密码不相同！请再次输入。");
				history.back();
			}
		}
	</script>
	<!-- //here ends scrolling icon -->
</body>
</html>