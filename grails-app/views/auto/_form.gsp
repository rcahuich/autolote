<%@ page import="general.Auto" %>



<div class="fieldcontain ${hasErrors(bean: autoInstance, field: 'marca', 'error')} ">
	<label for="marca">
		<g:message code="auto.marca.label" default="Marca" />
		
	</label>
	<g:select name="marca" from="${autoInstance.constraints.marca.inList}" value="${autoInstance?.marca}" valueMessagePrefix="auto.marca" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: autoInstance, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="auto.status.label" default="Status" />
		
	</label>
	<g:select name="status" from="${autoInstance.constraints.status.inList}" value="${autoInstance?.status}" valueMessagePrefix="auto.status" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: autoInstance, field: 'color', 'error')} ">
	<label for="color">
		<g:message code="auto.color.label" default="Color" />
		
	</label>
	<g:textField name="color" value="${autoInstance?.color}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: autoInstance, field: 'compra', 'error')} required">
	<label for="compra">
		<g:message code="auto.compra.label" default="Compra" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="compra" required="" value="${fieldValue(bean: autoInstance, field: 'compra')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: autoInstance, field: 'costos', 'error')} ">
	<label for="costos">
		<g:message code="auto.costos.label" default="Costos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${autoInstance?.costos?}" var="c">
    <li><g:link controller="costo" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="costo" action="create" params="['auto.id': autoInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'costo.label', default: 'Costo')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: autoInstance, field: 'fechaDeModelo', 'error')} ">
	<label for="fechaDeModelo">
		<g:message code="auto.fechaDeModelo.label" default="Fecha De Modelo" />
		
	</label>
	<g:textField name="fechaDeModelo" value="${autoInstance?.fechaDeModelo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: autoInstance, field: 'imagenes', 'error')} ">
	<label for="imagenes">
		<g:message code="auto.imagenes.label" default="Imagenes" />
		
	</label>
	<g:select name="imagenes" from="${general.Imagen.list()}" multiple="multiple" optionKey="id" size="5" value="${autoInstance?.imagenes*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: autoInstance, field: 'modelo', 'error')} ">
	<label for="modelo">
		<g:message code="auto.modelo.label" default="Modelo" />
		
	</label>
	<g:textField name="modelo" value="${autoInstance?.modelo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: autoInstance, field: 'venta', 'error')} required">
	<label for="venta">
		<g:message code="auto.venta.label" default="Venta" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="venta" required="" value="${fieldValue(bean: autoInstance, field: 'venta')}"/>
</div>

