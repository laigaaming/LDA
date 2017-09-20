<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登录</title>
<!-- for-mobile-apps -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords"
	content="Manufactory Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	
</script>
<!-- //for-mobile-apps -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/signin.css" rel="stylesheet" type="text/css" media="all" />
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
				<!-- <link rel="stylesheet" type="text/css" href="css/component.css" />
						<script type="text/javascript">
				
						  var _gaq = _gaq || [];
						  _gaq.push(['_setAccount', 'UA-7243260-2']);
						  _gaq.push(['_trackPageview']);
				
						  (function() {
							var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
							ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
							var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
						  })();
				
						</script>
						<div class="cbp-spmenu-push">
							<nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left" id="cbp-spmenu-s1">
								<h3>Menu</h3>
								<a href="#home" class="scroll">Home</a>
								<a href="#services" class="scroll">Services</a>
								<a href="#about" class="scroll">About Us</a>
								<a href="#news" class="scroll">News & Events</a>
								<a href="#contact" class="scroll">Contact</a>
							</nav>
					</div>
						<script src="js/classie.js"></script>
						<script>
							var menuLeft = document.getElementById( 'cbp-spmenu-s1' ),				
								showLeft = document.getElementById( 'showLeft' ),				
								body = document.body;

							showLeft.onclick = function() {
								classie.toggle( this, 'active' );
								classie.toggle( menuLeft, 'cbp-spmenu-open' );
								disableOther( 'showLeft' );
							};
							function disableOther( button ) {
								if( button !== 'showLeft' ) {
									classie.toggle( showLeft, 'disabled' );
								}
							}
						</script> -->
				<!--- Navigation from Right To Left --->
				<div class="clearfix"></div>
			</div>
			<div class="banner-info">
				<div class="banner-info-left">
					<h1>
						<strong>登录</strong>
					</h1>
					<form method="post" action="SigninForm">

						<input type="text" name="identity" placeholder="账号"
							onfocus="this.value = '';" required=""> <input
							type="password" name="password" placeholder="密码"
							onfocus="this.value = '';" required=""> 登录身份： <input
							type="radio" name="type" value="student" required="">学生</input> <input
							type="radio" name="type" value="teacher" required="">管理员</input> <a
							href="register.jsp" style="float: right; color: blue;">注册</a> <input
							type="submit" value="登录"> <input type="reset" value="取消">
					</form>
				</div>
				<div class="banner-info-right">
					<h2 style="color: transparent;">Sed ut perspiciatis unde omnis
						iste natus error laudantium</h2>
					<p></p>
					<!-- <ul>
						<li><a href="#" class="facebook"> </a></li>
						<li><a href="#" class="twitter"> </a></li>
						<li><a href="#" class="p"> </a></li>
						<li><a href="#" class="be"> </a></li>
					</ul> -->
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
			<!-- <div class="footer-right">
			<ul>
				<li><a href="#" class="f"> </a></li>
				<li><a href="#" class="f1"> </a></li>
				<li><a href="#" class="f2"> </a></li>
				<li><a href="#" class="f3"> </a></li>
			</ul>
		</div> -->
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
	</script>
	<!-- //here ends scrolling icon -->
</body>
</html>