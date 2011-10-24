<%@ page import="general.Costo" %>



<div class="fieldcontain ${hasErrors(bean: costoInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="costo.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${costoInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: costoInstance, field: 'costo', 'error')} required">
	<label for="costo">
		<g:message code="costo.costo.label" default="Costo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="costo" required="" value="${fieldValue(bean: costoInstance, field: 'costo')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: costoInstance, field: 'fecha', 'error')} required">
	<label for="fecha">
		<g:message code="costo.fecha.label" default="Fecha" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fecha" precision="day"  value="${costoInstance?.fecha}"  />
</div>

