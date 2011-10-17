
<%@ page import="general.Auto" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'auto.label', default: 'Auto')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-auto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-auto" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list auto">
			
				<g:if test="${autoInstance?.marca}">
				<li class="fieldcontain">
					<span id="marca-label" class="property-label"><g:message code="auto.marca.label" default="Marca" /></span>
					
						<span class="property-value" aria-labelledby="marca-label"><g:fieldValue bean="${autoInstance}" field="marca"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${autoInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="auto.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${autoInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${autoInstance?.color}">
				<li class="fieldcontain">
					<span id="color-label" class="property-label"><g:message code="auto.color.label" default="Color" /></span>
					
						<span class="property-value" aria-labelledby="color-label"><g:fieldValue bean="${autoInstance}" field="color"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${autoInstance?.compra}">
				<li class="fieldcontain">
					<span id="compra-label" class="property-label"><g:message code="auto.compra.label" default="Compra" /></span>
					
						<span class="property-value" aria-labelledby="compra-label"><g:fieldValue bean="${autoInstance}" field="compra"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${autoInstance?.costos}">
				<li class="fieldcontain">
					<span id="costos-label" class="property-label"><g:message code="auto.costos.label" default="Costos" /></span>
					
						<g:each in="${autoInstance.costos}" var="c">
						<span class="property-value" aria-labelledby="costos-label"><g:link controller="costo" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${autoInstance?.fechaDeModelo}">
				<li class="fieldcontain">
					<span id="fechaDeModelo-label" class="property-label"><g:message code="auto.fechaDeModelo.label" default="Fecha De Modelo" /></span>
					
						<span class="property-value" aria-labelledby="fechaDeModelo-label"><g:fieldValue bean="${autoInstance}" field="fechaDeModelo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${autoInstance?.imagenes}">
				<li class="fieldcontain">
					<span id="imagenes-label" class="property-label"><g:message code="auto.imagenes.label" default="Imagenes" /></span>
					
						<g:each in="${autoInstance.imagenes}" var="i">
						<span class="property-value" aria-labelledby="imagenes-label"><g:link controller="imagen" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${autoInstance?.modelo}">
				<li class="fieldcontain">
					<span id="modelo-label" class="property-label"><g:message code="auto.modelo.label" default="Modelo" /></span>
					
						<span class="property-value" aria-labelledby="modelo-label"><g:fieldValue bean="${autoInstance}" field="modelo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${autoInstance?.venta}">
				<li class="fieldcontain">
					<span id="venta-label" class="property-label"><g:message code="auto.venta.label" default="Venta" /></span>
					
						<span class="property-value" aria-labelledby="venta-label"><g:fieldValue bean="${autoInstance}" field="venta"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${autoInstance?.id}" />
					<g:link class="edit" action="edit" id="${autoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
