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
	<script type="text/javascript" src="/resources/js/pages/tools/progress<c:if test="${activeProfile ne 'local'}">.min</c:if>.js"></script>
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
																<span class="badge badge-danger">시작일</span>
																<div class="form-group">
																	<textarea class="form-control" id="textarea" rows="3"></textarea>
																	<label for="textarea">여기에 입력하세요.</label>
																</div>
																<span class="badge badge-danger">기준일</span>
																<div class="form-group">
																	<textarea class="form-control" id="textarea" rows="3"></textarea>
																	<label for="textarea">여기에 입력하세요.</label>
																</div>
																<span class="badge badge-danger">종료일</span>
																<div class="form-group">
																	<textarea class="form-control" id="textarea" rows="3"></textarea>
																	<label for="textarea">여기에 입력하세요.</label>
																</div>
															</div>
															<div class="col-12 col-lg-6">
																<span class="badge badge-secondary">결과</span>
																<div class="form-group">
																	<div id="qrCode"></div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
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
