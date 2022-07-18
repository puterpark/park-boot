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
								<%@ include file="../common/top_menu.jsp" %>
								<div class="tab-content">
									<div class="tab-pane fade show active p-3" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab">
										<!-- WIDGET GROUP -->
										<div class="widget-group row no-gutters">
											<!-- WIDGET 5 -->
											<div class="col-12 p-3">
												<c:choose>
													<c:when test="${fundaListSize < 1}">
														<div id="error-404" class="d-flex flex-column align-items-center justify-content-center">
															<div class="content">
																<div class="error-code display-1 text-center">NO DATA</div>
																<div class="message h4 text-center text-muted">데이터가 없습니다.</div>
																<a class="back-link d-block text-center text-primary" href="/" style="padding-top: 10px;">Go back</a>
															</div>
														</div>
													</c:when>
													<c:otherwise>
														<div class="widget widget5 card">
															<div class="widget-header px-4 row no-gutters align-items-center justify-content-between">
																<div class="col">
																	<span class="h6">Funda Status</span>
																</div>
															</div>
															<div class="widget-content p-4">
																<div class="row">
																	<div class="col-12 col-lg-6">
																		<div id="widget5-main-chart">
																			<svg></svg>
																		</div>
																	</div>
																	<div class="col-12 col-lg-6">
																		<div id="widget5-supporting-charts" class="row">
																			<div class="col-12 p-2">
																				<div id="widget5-total-chart">
																					<div class="h6 text-muted">Total</div>
																					<div class="h2 count">${currentTotal}원</div>
																					<div class="chart-wrapper">
																						<svg></svg>
																					</div>
																				</div>
																			</div>
																			<div class="col-12 p-2">
																				<div id="widget5-interest-chart">
																					<div class="h6 text-muted">Interest</div>
																					<div class="h2 count">${currentInterest}원</div>
																					<div class="chart-wrapper">
																						<svg></svg>
																					</div>
																				</div>
																			</div>
																			<div class="col-12 p-2">
																				<div id="widget5-yield-chart">
																					<div class="h6 text-muted">Yield</div>
																					<div class="h2 count">${currentYield}%</div>
																					<div class="chart-wrapper">
																						<svg></svg>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</c:otherwise>
												</c:choose>
											</div>
											<!-- / WIDGET 5 -->
										</div>
										<!-- / WIDGET GROUP -->
									</div>
								</div>
							</div>
							<!-- / CONTENT -->
						</div>
					</div>
					<input type="hidden" id="interest" value='${interest}'/>
					<input type="hidden" id="principal" value='${principal}' />
					<input type="hidden" id="yield" value='${yield}' />
					<input type="hidden" id="total" value='${total}' />
					<c:if test="${fundaListSize > 0}">
						<script type="text/javascript" src="/resources/js/pages/funda/main.js"></script>
					</c:if>
				</div>
			</div>
		</div>
	</main>
	<%@ include file="../common/footer.jsp" %>
</body>

</html>
