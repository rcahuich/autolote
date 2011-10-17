
<%@ page import="general.Costo" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'costo.label', default: 'Costo')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-costo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-costo" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="descripcion" title="${message(code: 'costo.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="costo" title="${message(code: 'costo.costo.label', default: 'Costo')}" />
					
						<th><g:message code="costo.auto.label" default="Auto" /></th>
					
						<g:sortableColumn property="fecha" title="${message(code: 'costo.fecha.label', default: 'Fecha')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${costoInstanceList}" status="i" var="costoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${costoInstance.id}">${fieldValue(bean: costoInstance, field: "descripcion")}</g:link></td>
					
						<td>${fieldValue(bean: costoInstance, field: "costo")}</td>
					
						<td>${fieldValue(bean: costoInstance, field: "auto")}</td>
					
						<td><g:formatDate date="${costoInstance.fecha}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${costoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
