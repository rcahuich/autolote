<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<g:layoutHead/>
	</head>
	<body>
          
          
          <div id="grailsLogo" role="banner">
            <div id="logo">
                <a href="${createLink(uri: '/')}"><img src="${resource(dir: 'images', file: 'autoloteO.png')}" alt="Auto-Lote" style="width: 250px; height: 100px;"/></a>
            </div>
            <div class="encabezado">
                <sec:ifLoggedIn>
                  <a>Bienvenido <sec:username /></a>|
                  <a href="${createLink(controller:'logout')}"><g:message code="logout.header.message" /></a>
                </sec:ifLoggedIn>
              <sec:ifNotLoggedIn>
                  <g:link controller='login' action='auth'>iniciar sesión</g:link>|
                  <g:link controller='usuario' action='create'>registrarse</g:link>
              </sec:ifNotLoggedIn>
            </div>
        </div>
        
        <g:layoutBody/>
		<div class="footer" role="contentinfo">
                  <p style="text-align:right;">&copy; 2011 Auto-Lote</p>
                </div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<g:javascript library="application"/>
	</body>
</html>