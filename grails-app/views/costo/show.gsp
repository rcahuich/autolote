
<%@ page import="general.Costo" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'costo.label', default: 'Costo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-costo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-costo" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list costo">
			
				<g:if test="${costoInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="costo.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${costoInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${costoInstance?.costo}">
				<li class="fieldcontain">
					<span id="costo-label" class="property-label"><g:message code="costo.costo.label" default="Costo" /></span>
					
						<span class="property-value" aria-labelledby="costo-label"><g:fieldValue bean="${costoInstance}" field="costo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${costoInstance?.auto}">
				<li class="fieldcontain">
					<span id="auto-label" class="property-label"><g:message code="costo.auto.label" default="Auto" /></span>
					
						<span class="property-value" aria-labelledby="auto-label"><g:link controller="auto" action="show" id="${costoInstance?.auto?.id}">${costoInstance?.auto?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${costoInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="costo.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:formatDate date="${costoInstance?.fecha}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${costoInstance?.id}" />
					<g:link class="edit" action="edit" id="${costoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
