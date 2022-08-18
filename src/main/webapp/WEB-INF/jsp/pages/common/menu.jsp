<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fnc" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<aside id="aside" class="aside aside-left" data-fuse-bar="aside" data-fuse-bar-media-step="md" data-fuse-bar-position="left">
	<div class="aside-content-wrapper">
		<div class="aside-content bg-primary-700 text-auto">
			<div class="aside-toolbar">
				<div class="logo">
					<span class="logo-icon">P</span>
					<span class="logo-text">${title}</span>
				</div>
				<button id="toggle-fold-aside-button" type="button" class="btn btn-icon d-none d-lg-block" data-fuse-aside-toggle-fold>
					<i class="icon icon-backburger"></i>
				</button>
			</div>
			<ul class="nav flex-column custom-scrollbar" id="sidenav" data-children=".nav-item">
				<li class="subheader">
					<span id="requestIP">접속 IP : </span>
				</li>
				<li class="nav-item">
					<a class="nav-link ripple ${mode eq 'home' ? 'active' : ''}" href="/" data-url="/">
						<i class="icon s-4 icon-home"></i>
						<span>home</span>
					</a>
				</li>
				<li class="subheader">
					<span>nas</span>
				</li>
				<li class="nav-item">
					<a class="nav-link ripple " href="https://nas.puter.us" data-url="/">
						<i class="icon s-4 icon-package-variant-closed"></i>
						<span>sinoli</span>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link ripple " href="https://jen.puter.us" data-url="/">
						<i class="icon s-4 icon-package-variant"></i>
						<span>jenkins</span>
					</a>
				</li>
				<li class="subheader">
					<span>tools</span>
				</li>
				<c:forEach var="m" items="${menuList}">
					<li class="nav-item">
						<a class="nav-link ripple ${m.activeFlag == 1 ? 'active' : ''}" href="${m.uri}" data-url="/">
							<i class="icon s-4 ${m.icon}"></i>
							<span>${m.name}</span>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</aside>