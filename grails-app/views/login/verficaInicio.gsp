<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Bienvenido a Auto-Lote</title>
                <style type='text/css' media='screen'>
	#login {
		margin: 15px 0px;
		padding: 0px;
		text-align: center;
                font-family: calibri;
                font-size: 13px;
	}

	#login .inner {
		width: 270px;
		padding-bottom: 6px;
		margin: 0px auto;
		text-align: left;
		border: 1px solid #aab;
		background-color: #f0f0fa;
		-moz-box-shadow: 2px 2px 2px #eee;
		-webkit-box-shadow: 2px 2px 2px #eee;
		-khtml-box-shadow: 2px 2px 2px #eee;
		box-shadow: 2px 2px 2px #eee;
	}

	#login .inner .fheader {
		padding: 18px 26px 14px 26px;
		background-color: #f7f7ff;
		margin: 0px 0 14px 0;
		color: #2e3741;
		font-size: 18px;
	}

	#login .inner .cssform p {
		clear: left;
		margin: 0;
		padding: 4px 0 3px 0;
		padding-left: 105px;
		margin-bottom: 20px;
		height: 1%;
	}

	#login .inner .cssform input[type='text'] {
		width: 120px;
	}

	#login .inner .cssform label {
		float: left;
		text-align: right;
		margin-left: -105px;
		width: 110px;
		padding-top: 3px;
		padding-right: 10px;
	}

	#login #remember_me_holder {
		padding-left: 120px;
	}

	#login #submit {
		margin-left: 15px;
	}

	#login #remember_me_holder label {
		float: none;
		margin-left: 0;
		text-align: left;
		width: 200px
	}

	#login .inner .login_message {
		padding: 6px 25px 20px 25px;
		color: #c33;
	}

	#login .inner .text_ {
		width: 120px;
	}

	#login .inner .chk {
		height: 12px;
	}
	</style>
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
                    Necesitas estar registrado o iniciar sesión para poder comprar
                    </p>
                    <br>
                    <br>
                  <div style="float: left; text-align: center">
                      <center><h1>Registrate</h1></center>
                      
                                <div id="create-usuario" class="content scaffold-create" role="main">
                                  <g:if test="${flash.message}">
                                  <div class="message" role="status">${flash.message}</div>
                                  </g:if>
                                  <g:hasErrors bean="${usuarioInstance}">

                                  <ul class="errors" role="alert">
                                          <g:eachError bean="${usuarioInstance}" var="error">
                                          <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                                          </g:eachError>
                                  </ul>
                                  </g:hasErrors>

                                  <g:form url="[controller:'usuario', action:'save']" >
                                          <fieldset class="form">
                                                  <g:render template="/usuario/form"/>
                                          </fieldset>
                                          <fieldset class="buttons">
                                                  <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                                          </fieldset>
                                  </g:form>
                                </div>
                  </div>
                  <div style="float: right; text-align: center">
                      
                    <center><h1>Inicia sesión</h1></center>
                      <div id='login'>
                                  <div class='inner'>
                                      <div class='fheader' style="text-align: center; font-size: 15px;" >iniciar sesión</div>

                                      <g:if test='${flash.message}'>
                                              <div class='login_message'>${flash.message}</div>
                                      </g:if>

                                      <form action='/autolote/j_spring_security_check' method='POST' id='loginForm' class='cssform' autocomplete='off'>
                                              <p>
                                                      <label for='username'><g:message code="springSecurity.login.username.label"/>:</label>

                                              </p>
                                                      <input style="margin: 0px 0px 0px 40px; width: 170px;" type='text' class='text_' name='j_username' id='username'/>

                                              <p>
                                                      <label for='password'><g:message code="springSecurity.login.password.label"/>:</label>
                                              </p>
                                                      <input style="margin: 0px 0px 0px 40px; width: 170px;" type='password' class='text_' name='j_password' id='password'/>
                                              <p>

                                              </p>
                                              <p>
                                                      <input type='submit' id="submit" value='${message(code: "springSecurity.login.button")}'/>
                                              </p>
                                      </form>
                              </div>
                      </div>
                      
                  </div>
                          
                </div>
                  
                  
                  
                  
		</div>
<script type='text/javascript'>
	<!--
	(function() {
		document.forms['loginForm'].elements['j_username'].focus();
	})();
	// -->
</script>
	</body>
</html>

