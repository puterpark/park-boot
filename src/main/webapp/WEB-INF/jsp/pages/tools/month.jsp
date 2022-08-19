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
	<script type="text/javascript" src="/resources/js/pages/tools/month<c:if test="${activeProfile ne 'local'}">.min</c:if>.js"></script>
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
																<div class="form-group">
																	<textarea class="form-control mousetrap" id="textarea" rows="15"></textarea>
																	<label for="textarea">여기에 입력하세요.</label>
																	<button type="button" class="btn btn-danger btn-lg btn-block" onclick="javascript:convert();">convert</button>
																</div>
															</div>
															<div class="col-12 col-lg-6">
																<h4>
																	예시와 같이 작성 후,<br/>
																	convert 버튼 클릭 or Ctrl + Enter<br/>
																	중복된 상세사항은 삭제 및 계산된 공수를 상세사항 뒤에 덧붙입니다.<br/>
																	<font style='color:red;'>(구분자는 " / ")</font>
																</h4>
																> 추가요구사항 개발 / 1<br/>
																> apache & tomcat 연동 테스트 / 0.875<br/>
																> 산출물 작성 / 0.125<br/>
																> 추가요구사항 개발 및 반영 / 0.5<br/>
																> apache 설치 및 연동 / 0.5<br/>
																> 산출물 작성 / 0.9375<br/>
																> 주소록 이관 분석 / 0.5<br/>
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
