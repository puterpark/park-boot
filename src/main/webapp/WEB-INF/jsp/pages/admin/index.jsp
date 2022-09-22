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
	<link type="text/css" rel="stylesheet" href="/resources/css/pages/admin/admin.css">
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
												<div class="widget card">
													<div class="widget-content p-4">
														<div class="row">
															<div class="col-12 col-lg-3">
																<div class="widget card">
																	<div class="widget-header pl-4 pr-2 row no-gutters align-items-center justify-content-between">
																		<div class="col">
																			<span class="h6">TODAY</span>
																		</div>
																	</div>
																	<div class="widget-content pt-2 pb-10 d-flex flex-column align-items-center justify-content-center">
																		<div class="title text-secondary">${todayRedirectCount}</div>
																		<div class="sub-title h6 text-muted">REDIRECT COUNT</div>
																	</div>
																</div>
															</div>
															<div class="col-12 col-lg-9">
																<div class="widget shortenUrlWidget">
																	<div class="widget-header px-4 row no-gutters align-items-center justify-content-between">
																		<div class="col">
																			<span class="h6">[TOP 5] Shorten URL</span>
																		</div>
																		<div>
																			<button type="button" class="shortenUrlWidget-option-change-btn btn btn-link" data-interval="07D">
																				7 Days
																			</button>
																			<button type="button" class="shortenUrlWidget-option-change-btn btn btn-link" data-interval="30D">
																				30 Days
																			</button>
																		</div>
																	</div>
																	<div class="widget-content p-1">
																		<div id="shorten-url-widget-main-chart">
																			<svg></svg>
																		</div>
																	</div>
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
							<!-- / CONTENT -->
							</div>
						</div>
						<input type="hidden" id="top5day7" value='${top5day7}' />
						<input type="hidden" id="top5day30" value='${top5day30}' />
						<script type="text/javascript" src="/resources/js/pages/admin/admin${useMin}.js"></script>
					</div>
				</div>
			</div>
		</div>
	</main>
	<%@ include file="../common/footer.jsp" %>
</body>

</html>
