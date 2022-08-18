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
									<div class="tab-pane fade show active p-3" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab">
										<!-- WIDGET GROUP -->
										<div class="widget-group row no-gutters">
											<div class="col-6 col-sm-6 col-xl-3 p-3">
												<div class="widget widget1 card">
													<div class="widget-content pt-2 pb-8 d-flex flex-column align-items-center justify-content-center">
														<div class="sub-title h6 text-muted">&nbsp;</div>
														<button type="button" class="btn btn-outline-secondary" onclick="goMenu('https://nas.puter.us')">
															<i class="icon s-4 btn-outline-secondary icon-package-variant-closed"></i>
															sinoli
														</button>
													</div>
												</div>
											</div>
											<div class="col-6 col-sm-6 col-xl-3 p-3">
												<div class="widget widget1 card">
													<div class="widget-content pt-2 pb-8 d-flex flex-column align-items-center justify-content-center">
														<div class="sub-title h6 text-muted">&nbsp;</div>
														<button type="button" class="btn btn-outline-secondary" onclick="goMenu('https://jen.puter.us')">
															<i class="icon s-4 btn-outline-secondary icon-package-variant"></i>
															jenkins
														</button>
													</div>
												</div>
											</div>
											<c:forEach var="m" items="${menuList}">
												<div class="col-6 col-sm-6 col-xl-3 p-3">
													<div class="widget widget1 card">
														<div class="widget-content pt-2 pb-8 d-flex flex-column align-items-center justify-content-center">
															<div class="sub-title h6 text-muted">&nbsp;</div>
															<button type="button" class="btn btn-outline-secondary" onclick="goMenu('${m.uri}')">
																<i class="icon s-4 btn-outline-secondary ${m.icon}"></i>
																${m.name}
															</button>
														</div>
													</div>
												</div>
											</c:forEach>
										</div>
										<!-- / WIDGET GROUP -->
									</div>
									<div class="col-12 p-3" style="text-align: center;">
										<%@ include file="../common/adfit.jsp" %>
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
