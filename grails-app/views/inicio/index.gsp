<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Bienvenido a Auto-Lote</title>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="page-body" role="main">
                <div class="nav" role="navigation">
                    <ul>
                      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                      <li><g:link controller="auto"><g:message code="inventario.label" default="Autos en Venta" /></g:link></li>
                      
                      <sec:ifAllGranted roles="ROLE_ADMIN">
                        <li><g:link class="list" controller="auto" action="buscaAuto"><g:message code="inventario.label" default="Mis Autos" /></g:link></li> 
                        <li><g:link class="list" controller="usuario"><g:message code="inventario.label" default="Admin" /></g:link></li> 
                      </sec:ifAllGranted>
                    
                      <sec:ifAllGranted roles="ROLE_COMPRADOR">
                        <li><g:link class="list" controller="auto" action="buscaAuto"><g:message code="inventario.label" default="Mis Autos" /></g:link></li> 
                      </sec:ifAllGranted>
                                         
                    </ul>
                </div>
                <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
                </g:if>
                <div class="content" style="padding:10px 50px;">
                    <h1>Auto-Lote</h1>

                    Bienvenido al Sitio de Compra y Venta de Autos



                </div>
		</div>
	</body>
</html>
