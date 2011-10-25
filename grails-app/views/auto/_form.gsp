<%@ page import="general.Auto" %>



<div class="fieldcontain ${hasErrors(bean: autoInstance, field: 'marca', 'error')} ">
	<label for="marca">
		<g:message code="auto.marca.label" default="Marca" />
		
	</label>
	<g:select name="marca" from="${autoInstance.constraints.marca.inList}" value="${autoInstance?.marca}" valueMessagePrefix="auto.marca" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: autoInstance, field: 'modelo', 'error')} ">
	<label for="modelo">
		<g:message code="auto.modelo.label" default="Modelo" />
		
	</label>
	<g:textField name="modelo" value="${autoInstance?.modelo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: autoInstance, field: 'fechaDeModelo', 'error')} ">
	<label for="fechaDeModelo">
		<g:message code="auto.fechaDeModelo.label" default="Fecha De Modelo" />
		
	</label>
	<g:textField name="fechaDeModelo" value="${autoInstance?.fechaDeModelo}"/>
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

<div class="fieldcontain ${hasErrors(bean: autoInstance, field: 'venta', 'error')} required">
	<label for="venta">
		<g:message code="auto.venta.label" default="Venta" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="venta" required="" value="${fieldValue(bean: autoInstance, field: 'venta')}"/>
</div>

