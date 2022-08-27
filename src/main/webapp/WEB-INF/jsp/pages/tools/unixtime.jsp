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
	<script type="text/javascript" src="/resources/js/mousetrap.min.js"></script>
	<script type="text/javascript" src="/resources/js/sweetalert.min.js"></script>
	<script type="text/javascript" src="/resources/js/pages/tools/unixtime<c:if test="${activeProfile ne 'local'}">.min</c:if>.js"></script>
	
	<link type="text/css" rel="stylesheet" href="/resources/css/pages/tools/unixtime.css">
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
								<%@ include file="../common/top_menu.jsp" %>
								<div class="tab-content">
									<div class="tab-pane fade show active p-2" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab">
										<!-- WIDGET GROUP -->
										<div class="widget-group row no-gutters adfit">
											<%@ include file="../common/adfit.jsp" %>
										</div>
										<div class="widget-group row no-gutters">
											<div class="col-12 p-1">
												<div class="widget widget5 card">
													<div class="widget-content p-4">
														<div class="row">
															<div class="col-12 col-lg-6">
																<div class="input-group input-group-sm mb-3">
																	<span class="badge badge-secondary">UNIX</span>
																	<input type="text" id="unix" class="form-control" onkeydown="return validate(event);" onkeyup="convert(0);">
																</div>
															
																<div class="input-group input-group-sm mb-3">
																	<span class="badge badge-warning">REAL</span>
																	<input type="datetime-local" class="form-control" value="" id="real" step="1" onkeydown="return validate(event);" onkeyup="convert(1);"/>
																</div>
															</div>
															<div class="col-12 col-lg-6">
																<div class="alert alert-success" role="alert">
																	<h4 class="alert-heading">UNIXTIME 변환</h4>
																	<p>&gt; 입력 필드에 숫자를 입력하면 동적으로 변환</p>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div id="resultDiv" class="col-12 p-1 hidden"></div>
										</div>
										<!-- / WIDGET GROUP -->
									</div>
								</div>
							</div>
							<!-- / CONTENT -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<%@ include file="../common/footer.jsp" %>
</body>

</html>
