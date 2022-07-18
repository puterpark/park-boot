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
	<script type="text/javascript" src="/resources/js/pages/tools/json.js"></script>
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
											<div class="col-12 p-3">
												<div class="widget widget5 card">
													<div class="widget-content p-4">
														<div class="row">
															<div class="col-12">
																<span class="badge badge-danger">입력</span>
																<div class="form-group" style="display: flex;">
																	<textarea class="form-control mousetrap" id="textarea" rows="15"></textarea>
																	<label for="textarea">여기에 입력하세요.</label>
																	
																	<button type="button" class="col-6 btn btn-secondary btn-lg" onclick="javascript:convert(0);">beautify</button>
																	<button type="button" class="col-6 btn btn-danger btn-lg" onclick="javascript:convert(1);">minify</button>
																</div>
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
