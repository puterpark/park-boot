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
	<script type="text/javascript" src="/resources/js/pages/tools/filesize<c:if test="${activeProfile ne 'local'}">.min</c:if>.js"></script>
	
	<link type="text/css" rel="stylesheet" href="/resources/css/pages/tools/filesize.css">
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
									<div class="tab-pane fade show active p-3" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab">
										<!-- WIDGET GROUP -->
										<div class="widget-group row no-gutters">
											<%@ include file="../common/adfit.jsp" %>
										</div>
										<div class="widget-group row no-gutters">
											<div class="col-12 p-3">
												<div class="widget widget5 card">
													<div class="widget-content p-4">
														<div class="row">
															<div class="col-12 col-lg-6">
																<div class="input-group input-group-sm mb-3">
																	<span class="badge badge-secondary">Byte</span>
																	<input type="text" id="byte" class="form-control" onkeydown="return number(event);" onkeyup="convert(0);">
																</div>
																
																<div class="input-group input-group-sm mb-3">
																	<span class="badge badge-info">KB</span>
																	<input type="text" id="kb" class="form-control" onkeydown="return number(event);" onkeyup="convert(1);">
																</div>
																
																<div class="input-group input-group-sm mb-3">
																	<span class="badge badge-warning">MB</span>
																	<input type="text" id="mb" class="form-control" onkeydown="return number(event);" onkeyup="convert(2);">
																</div>
																
																<div class="input-group input-group-sm mb-3">
																	<span class="badge badge-success">GB</span>
																	<input type="text" id="gb" class="form-control" onkeydown="return number(event);" onkeyup="convert(3);">
																</div>
																
																<div class="input-group input-group-sm mb-3">
																	<span class="badge badge-primary">TB</span>
																	<input type="text" id="tb" class="form-control" onkeydown="return number(event);" onkeyup="convert(4);">
																</div>
															</div>
															<div class="col-12 col-lg-6">
																<h4 style='color:red;'>
																	file size 변환
																</h4>
																<h5 style='color:red;'>
																	&gt; 입력 필드에 숫자를 입력하면 동적으로 변환
																</h5>
																<br/>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div id="resultDiv" class="col-12 p-3 hidden"></div>
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
