<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fnc" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp" %>
</head>
<body class="layout layout-vertical layout-left-navigation layout-below-toolbar">
	<main>
		<div id="wrapper">
			<%@ include file="../common/menu.jsp" %>
			<div class="content-wrapper">
				<div class="content custom-scrollbar">
					<div id="project-dashboard" class="page-layout simple right-sidebar">
						<div class="page-content-wrapper custom-scrollbar">
							<%@ include file="../common/top.jsp" %>
							<!-- CONTENT -->
							<div class="page-content">
								<ul class="nav nav-tabs" id="myTab" role="tablist">
									<li class="nav-item">
										<a class="nav-link btn ${mode eq 'home' ? 'active' : ''}" id="home-tab" href="/">
											<i class="icon s-4 icon-home"></i>
											home
										</a>
									</li>
								</ul>
								<div class="tab-content">
									<div class="tab-pane fade show active p-2" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab">
										<!-- WIDGET GROUP -->
										<div class="widget-group row no-gutters adfit">
											<%@ include file="../common/adfit.jsp" %>
										</div>
										<div class="widget-group row no-gutters">
											<div class="col-12 p-1">
												<div>
													<span class="badge badge-danger">TODAY</span>
													${todayRedirectCount}
												</div>
												<c:forEach var="list" items="${shortenUrlTop5List}">
													<div>
														<span class="badge badge-danger">URI</span> : ${list.shortenUri} /
														<span class="badge badge-secondary">CNT</span> : ${list.redirectCount}
													</div>
												</c:forEach>
											</div>
										</div>
										<!-- / WIDGET GROUP -->
									</div>
								</div>
							<!-- / CONTENT -->
							</div>
						</div>
						<script type="text/javascript" src="/resources/js/apps/dashboard/project.js"></script>
					</div>
				</div>
			</div>
		</div>
	</main>
	<%@ include file="../common/footer.jsp" %>
</body>

</html>
