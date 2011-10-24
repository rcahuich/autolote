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
                    
                    </ul>
                </div>
                <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
                </g:if>
                <div class="content" style="padding:10px 50px;">
                  <center><h1>Lo sentimos</h1></center>

                    <p style="text-align: center">
                    Necesitas iniciar sesión o estar registrado para poder comprar
                    </p>


                </div>
		</div>
	</body>
</html>

