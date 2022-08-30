<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fnc" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<title>${title}</title>
	<%@ include file="../pages/common/favicon.jsp" %>
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700italic,700,900,900italic" rel="stylesheet">
	<!-- Main CSS -->
	<link type="text/css" rel="stylesheet" href="/resources/css/main.css">
</head>
<body class="layout layout-vertical layout-left-navigation layout-below-toolbar">
	<main>
		<div id="wrapper">
			<div class="content-wrapper">
				<div class="content custom-scrollbar">
					<div id="error-404" class="d-flex flex-column align-items-center justify-content-center">
						<div class="content">
							<div class="error-code display-1 text-center">404</div>
							<div class="message h4 text-center text-muted">페이지를 찾을 수 없음</div>
							<a class="back-link d-block text-center text-primary" href="/" style="padding-top: 10px;">Go back</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
</body>

</html>
