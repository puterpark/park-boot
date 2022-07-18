<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fnc" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- HEADER -->
<div class="page-header bg-secondary text-auto d-flex flex-column justify-content-between px-6 pt-4 pb-0">
	<div class="row no-gutters align-items-start justify-content-between flex-nowrap">
		<button type="button" class="toggle-aside-button btn btn-icon d-block d-lg-none" data-fuse-bar-toggle="aside">
			<i class="icon icon-menu"></i>
		</button>
		<div>
			<span class="h2"></span>
		</div>
	</div>
	<div class="row no-gutters align-items-center project-selection">
		<div class="selected-project h6 px-4 py-2">${title}</div>
	</div>
</div>
<!-- / HEADER -->