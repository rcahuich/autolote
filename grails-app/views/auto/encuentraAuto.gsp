<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'auto.label', default: 'Auto')}" />
		<title>Encuentra tu Auto Nuevo</title>
    </head>
    
    <body>
      <a href="#list-auto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
              <div class="nav" role="navigation">
  
      <ul>
        	<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      
                <sec:ifAllGranted roles="ROLE_COMPRADOR">
                        <li><g:link controller="auto"><g:message code="inventario.label" default="Autos en Venta" /></g:link></li>
                </sec:ifAllGranted>
        </ul>
                    
                </div>
        <div id="list-auto" class="content scaffold-list" role="main">
            <h1><g:message code="auto.encuentraAuto"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
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
            
            <div class="list">
                <table id="autoInstanceList">
                    <thead>
                        <tr>                            
                            <g:sortableColumn property="marca" title="${message(code: 'auto.marca.label', default: 'Marca')}" />
					

                            <g:sortableColumn property="color" title="${message(code: 'auto.color.label', default: 'Color')}" />

                            <g:sortableColumn property="compra" title="${message(code: 'auto.compra.label', default: 'Precio')}" />

                            <g:sortableColumn property="fechaDeModelo" title="${message(code: 'auto.fechaDeModelo.label', default: 'Fecha De Modelo')}" />

                            <g:sortableColumn property="modelo" title="${message(code: 'auto.modelo.label', default: 'Modelo')}" />
                            
                            <g:sortableColumn property="modelo" title="${message(code: 'auto.modelo.label', default: 'Imagen')}" />
                            
                            <g:sortableColumn property="modelo" title="${message(code: 'auto.modelo.label', default: '   ')}" />
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${autoInstanceList}" status="i" var="autoInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                                                     
                            <td>${fieldValue(bean: autoInstance, field: "marca")}</td>
					

                            <td>${fieldValue(bean: autoInstance, field: "color")}</td>

                            <td>${fieldValue(bean: autoInstance, field: "venta")}</td>

                            <td>${fieldValue(bean: autoInstance, field: "fechaDeModelo")}</td>

                            <td>${fieldValue(bean: autoInstance, field: "modelo")}</td>
                            
                            <td><img style="width: 115px; height: 130px;" src="${createLink(action:'imagen',id:autoInstance?.id)}" /></td>
                            
                            <td><g:link action="verMas" id="${autoInstance.id}">Ver más</g:link></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
