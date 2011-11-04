
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
                                
                                
			</ul>
		</div>
		<div id="list-auto" class="content scaffold-list" role="main">
			<h1>Autos en Venta</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
                        
                        <div class="search">
                          <g:form method="post">
                            <div>
                              <table>
                                <tbody>
                                  <tr>
                                    <td style="vertical-align:middle;width:50px;">
                                      <label for="filtro"><g:message code="entrada.buscar" default="Buscar"/>:</label>
                                    </td>
                                    <td style="width:50px; vertical-align: middle;">
                                      <g:select id="filtro" name="filtro" from="${['Acura', 'Aston Martin', 'Audi', 'Bentley', 'BMW', 'Bugatti', 'Buick', 'Cadillac', 'Chevrolet', 'Chrysler', 'Dodge', 'Ferrari', 'FIAT', 'Fisker', 'Ford', 'GMC', 'Honda', 'Hyundai', 'Infiniti', 'Jaguar', 'Jeep', 'KIA', 'Lamborghini', 'Land Rover', 'Lexus', 'Lincoln', 'Lotus', 'Maserati', 'Maybach', 'Mazda', 'McLaren', 'Mercedez Benz', 'Mercury', 'MINI', 'Mitsubishi', 'Nissan', 'Porsche', 'Rolls-Royce', 'Saab', 'Scion', 'Smart', 'Spyker', 'Subaru', 'Suzuki', 'Tesla', 'Toyota', 'Volkswagen', 'Volvo']}" value="${params.filtro}" />
                                    </td>
                                    <td>
                                      <g:actionSubmit action="encuentraAuto" value="Buscar" class="save" style="vertical-align: middle;" />
                                    </td>
                                  </tr>
                                </tbody>
                              </table>
                            </div>
                          </g:form>
                  </div>
                        
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="marca" title="${message(code: 'auto.marca.label', default: 'Marca')}" />
					
						<g:sortableColumn property="modelo" title="${message(code: 'auto.modelo.label', default: 'Modelo')}" />
                                                <g:sortableColumn property="color" title="${message(code: 'auto.color.label', default: 'Color')}" />
						<g:sortableColumn property="compra" title="${message(code: 'auto.compra.label', default: 'Precio')}" />

					
                                                <g:sortableColumn property="modelo" title="${message(code: 'auto.modelo.label', default: 'Imagen')}" />
					
                                                <g:sortableColumn property="modelo" title="${message(code: 'auto.comprar.label', default: '   ')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${autoInstanceList}" status="i" var="autoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: autoInstance, field: "marca")}</td>
						<td>${fieldValue(bean: autoInstance, field: "modelo")}</td>
					
						<td>${fieldValue(bean: autoInstance, field: "color")}</td>
                                                <td>${fieldValue(bean: autoInstance, field: "venta")}</td>
					
					
					
                                                <td><img style="width: 180px; height: 120px;" src="${createLink(action:'imagen',id:autoInstance?.id)}" /></td>
                                                
                                                <td><g:link action="verMas" id="${autoInstance.id}">Ver más</g:link></td>
					
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
