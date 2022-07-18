<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fnc" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
	<title>${title}</title>
	<meta charset="UTF-8">
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

	<link rel="shortcut icon" type="image/x-icon" href="/resources/favicon.ico" />

	<link href="https://fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700italic,700,900,900italic" rel="stylesheet">

	<!-- STYLESHEETS -->
	<style type="text/css">
		[fuse-cloak],
		.fuse-cloak {
			display: none !important;
		}
	</style>
	<!-- Icons.css -->
	<link type="text/css" rel="stylesheet" href="/resources/icons/fuse-icon-font/style.css">
	<!-- Animate.css -->
	<link type="text/css" rel="stylesheet" href="/resources/vendor/animate.css/animate.min.css">
	<!-- PNotify -->
	<link type="text/css" rel="stylesheet" href="/resources/vendor/pnotify/pnotify.custom.min.css">
	<!-- Nvd3 - D3 Charts -->
	<link type="text/css" rel="stylesheet" href="/resources/vendor/nvd3/build/nv.d3.min.css" />
	<!-- Perfect Scrollbar -->
	<link type="text/css" rel="stylesheet" href="/resources/vendor/perfect-scrollbar/css/perfect-scrollbar.min.css" />
	<!-- Fuse Html -->
	<link type="text/css" rel="stylesheet" href="/resources/vendor/fuse-html/fuse-html.min.css" />
	<!-- Main CSS -->
	<link type="text/css" rel="stylesheet" href="/resources/css/main.css">
	<!-- Custom CSS -->
	<link type="text/css" rel="stylesheet" href="/resources/css/custom.css">
	<!-- / STYLESHEETS -->

	<!-- JAVASCRIPT -->
	<!-- jQuery -->
	<script type="text/javascript" src="/resources/vendor/jquery/dist/jquery.min.js"></script>
	<!-- Mobile Detect -->
	<script type="text/javascript" src="/resources/vendor/mobile-detect/mobile-detect.min.js"></script>
	<!-- Perfect Scrollbar -->
	<script type="text/javascript" src="/resources/vendor/perfect-scrollbar/js/perfect-scrollbar.jquery.min.js"></script>
	<!-- Popper.js -->
	<script type="text/javascript" src="/resources/vendor/popper.js/index.js"></script>
	<!-- Bootstrap -->
	<script type="text/javascript" src="/resources/vendor/bootstrap/bootstrap.min.js"></script>
	<!-- Nvd3 - D3 Charts -->
	<script type="text/javascript" src="/resources/vendor/d3/d3.min.js"></script>
	<script type="text/javascript" src="/resources/vendor/nvd3/build/nv.d3.min.js"></script>
	<!-- Data tables -->
	<script type="text/javascript" src="/resources/vendor/datatables.net/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="/resources/vendor/datatables-responsive/js/dataTables.responsive.js"></script>
	<!-- PNotify -->
	<script type="text/javascript" src="/resources/vendor/pnotify/pnotify.custom.min.js"></script>
	<!-- Fuse Html -->
	<script type="text/javascript" src="/resources/vendor/fuse-html/fuse-html.min.js"></script>
	<!-- Main JS -->
	<script type="text/javascript" src="/resources/js/main.js"></script>
	<!-- Common JS -->
	<script type="text/javascript" src="/resources/js/pages/common.js"></script>
	<!-- Loader JS -->
	<script type="text/javascript" src="/resources/js/loader.js"></script>
	<!-- / JAVASCRIPT -->

	<script>
		$(function() {
			$.ajax({
				url: "https://api.ipify.org?format=json"
			}).done(function(data) {
				$("#requestIP").append(data.ip);
			}).fail(function() {
				$("#requestIP").append('error');
			});
		});
	</script>
	
	<style>
		.hidden {
			display: none !important;
			visibility: hidden !important;
		}
	</style>
</head>
