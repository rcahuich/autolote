
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
                                
                                <sec:ifAllGranted roles="ROLE_VENDEDOR">
                                    <li><g:link controller="auto"><g:message code="inventario.label" default="Autos en Venta" /></g:link></li>
                                    <li><g:link class="list" controller="auto" action="buscaAuto"><g:message code="inventario.label" default="Autos" /></g:link></li>
                                    <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                                </sec:ifAllGranted>
			</ul>
		</div>
		<div id="show-auto" class="content scaffold-show" role="main">
                  <center>
			<h1>¡Ahora ya eres dueño de este Automovil!</h1>
                        
                        <h2>¡Felicidades!</h2>
                  </center>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list auto">
                          
                                <li class="fieldcontain">
                                        <span aria-labelledby="costos-label">
                                          <center>
                                          <td colspan="2"><img style="width: 415px; height: 230px;" src="${createLink(action:'imagen',id:autoInstance?.id)}" /></td>
                                          </center>
                                        </span>
                                </li>
			
			</ol>
		</div>
	</body>
</html>
