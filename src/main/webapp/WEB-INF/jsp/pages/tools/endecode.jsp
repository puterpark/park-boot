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
	<script type="text/javascript" src="/resources/js/pages/tools/endecode.js"></script>
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
																<div class="form-group">
																	<textarea class="form-control" id="original" rows="3" onkeyup="convert();" onkeydown="convert();"></textarea>
																	<label for="original">여기에 입력하세요.</label>
																</div>
															</div>
															<div class="col-12 col-lg-6">
																<span class="badge badge-secondary">Base64 Encode</span>
																<div class="form-group">
																	<textarea class="form-control" id="base64Encode" rows="3" onclick="javascript:copy(this);"></textarea>
																</div>
															</div>
															<div class="col-12 col-lg-6">
																<span class="badge badge-secondary">Base64 Decode</span>
																<div class="form-group">
																	<textarea class="form-control" id="base64Decode" rows="3" onclick="javascript:copy(this);"></textarea>
																</div>
															</div>
															<div class="col-12 col-lg-6">
																<span class="badge badge-info">URL Encode</span>
																<div class="form-group">
																	<textarea class="form-control" id="urlEncode" rows="3" onclick="javascript:copy(this);"></textarea>
																</div>
															</div>
															<div class="col-12 col-lg-6">
																<span class="badge badge-info">URL Decode</span>
																<div class="form-group">
																	<textarea class="form-control" id="urlDecode" rows="3" onclick="javascript:copy(this);"></textarea>
																</div>
															</div>
															<div class="col-12 col-lg-6">
																<span class="badge badge-warning">Unicode Encode</span>
																<div class="form-group">
																	<textarea class="form-control" id="unicodeEncode" rows="3" onclick="javascript:copy(this);"></textarea>
																</div>
															</div>
															<div class="col-12 col-lg-6">
																<span class="badge badge-warning">Unicode Decode</span>
																<div class="form-group">
																	<textarea class="form-control" id="unicodeDecode" rows="3" onclick="javascript:copy(this);"></textarea>
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
