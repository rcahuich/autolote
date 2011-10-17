
<%@ page import="general.Auto" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'auto.label', default: 'Auto')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
        
	<body>
		<a href="#list-auto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-auto" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="marca" title="${message(code: 'auto.marca.label', default: 'Marca')}" />
					
						<g:sortableColumn property="status" title="${message(code: 'auto.status.label', default: 'Status')}" />
					
						<g:sortableColumn property="color" title="${message(code: 'auto.color.label', default: 'Color')}" />
					
						<g:sortableColumn property="compra" title="${message(code: 'auto.compra.label', default: 'Compra')}" />
					
						<g:sortableColumn property="fechaDeModelo" title="${message(code: 'auto.fechaDeModelo.label', default: 'Fecha De Modelo')}" />
					
						<g:sortableColumn property="modelo" title="${message(code: 'auto.modelo.label', default: 'Modelo')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${autoInstanceList}" status="i" var="autoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${autoInstance.id}">${fieldValue(bean: autoInstance, field: "marca")}</g:link></td>
					
						<td>${fieldValue(bean: autoInstance, field: "status")}</td>
					
						<td>${fieldValue(bean: autoInstance, field: "color")}</td>
					
						<td>${fieldValue(bean: autoInstance, field: "compra")}</td>
					
						<td>${fieldValue(bean: autoInstance, field: "fechaDeModelo")}</td>
					
						<td>${fieldValue(bean: autoInstance, field: "modelo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${autoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
