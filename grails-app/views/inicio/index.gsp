<!doctype html>
<html>
	<head>
                <meta name="layout" content="main"/>
                <title>Bienvenido a Auto-Lote</title>
                <!-- jQuery -->
                <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js"></script>

                <!-- Anything Slider optional plugins -->
                <script src="js/jquery.easing.1.2.js"></script>

                <!-- Anything Slider -->
                 <link href="css/anythingslider.css" rel="stylesheet">
                 <script src="js/jquery.anythingslider.min.js"></script>

                <!-- Anything Slider optional FX extension -->
                 <script src="js/jquery.anythingslider.fx.min.js"></script>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="page-body" role="main">
                <div class="nav" role="navigation">
                    <ul>
                      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                      <li><g:link controller="auto"><g:message code="inventario.label" default="Autos en Venta" /></g:link></li>
                      
                      <sec:ifAllGranted roles="ROLE_VENDEDOR">
                          <li><g:link class="list" controller="auto" action="buscaAuto"><g:message code="inventario.label" default="Autos" /></g:link></li>
                        <li><g:link class="create" controller="usuario" action="create"><g:message code="inventario.label" default="Crear un Comprador" /></g:link></li>
                      </sec:ifAllGranted>
                                       
                      <sec:ifAllGranted roles="ROLE_ADMIN">
                        <li><g:link class="list" controller="usuario"><g:message code="inventario.label" default="Admin" /></g:link></li> 
                      </sec:ifAllGranted>
                                         
                    </ul>
                </div>
                <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
                </g:if>
                <div class="content" style="padding:10px 50px;">
                  <center><h1>Bienvenido al Sitio de Compra y Venta de Autos</h1></center>

                    
                    
                    <ul id="slider2">
                    <li class="panel1">
                     <div class="textSlide">
                      <img style="width: 415px; height: 270px;" src="images/mazda.jpg" class="expand">
                      <h3>Mazda</h3>
                      <h4>Detalles</h4>
                      <ul>
                       <li>Modelo 2010</li>
                       <li>Precio $210,050</li>
                       <li>Color Rojo Vivo</li>
                      </ul>
                     </div>
                    </li>
                    
                    <li class="panel1">
                     <div class="textSlide">
                      <img style="width: 415px; height: 270px;" src="images/ford.jpg" class="expand">
                       <h3>Ford - Focus</h3>
                      <h4>Detalles</h4>
                      <ul>
                       <li>Modelo 2011</li>
                       <li>Precio $420,050</li>
                       <li>Color Plata</li>
                      </ul>
                     </div>
                    </li>
                    
                    <li class="panel1">
                     <div class="textSlide">
                      <img style="width: 415px; height: 270px;" src="images/audi.jpg" class="expand">
                      <h3>Audi - S3</h3>
                      <h4>Detalles</h4>
                      <ul>
                       <li>Modelo 2012</li>
                       <li>Precio 345,070</li>
                       <li>Color Rojo</li>
                      </ul>
                     </div>
                    </li>
                    
                   </ul>
                    
                </div>
		</div>
<script type='text/javascript'>
	$(function(){
         $('#slider2') // Demo 2 code, using FX full control
          .anythingSlider({
           resizeContents      : false,
           navigationFormatter : function(i, panel){
            return ['Mazda', 'Ford', 'Audi', 'Quote #2', 'Image #2', 'Test'][i - 1];
           }
          })
          .anythingSliderFx({
           // base FX definitions can be mixed and matched in here too.
           '.fade' : [ 'fade' ],

           // for more precise control, use the "inFx" and "outFx" definitions
           // inFx = the animation that occurs when you slide "in" to a panel
           inFx : {
            '.textSlide h3'  : { opacity: 1, top  : 0, duration: 400, easing : 'easeOutBounce' },
            '.textSlide li'  : { opacity: 1, left : 0, duration: 400 },
            '.textSlide img' : { opacity: 1, duration: 400 },
            '.quoteSlide'    : { top : 0, duration: 400, easing : 'easeOutElastic' },
            '.expand'        : { width: '100%', top: '0%', left: '0%', duration: 400, easing : 'easeOutBounce' }
           },
           // out = the animation that occurs when you slide "out" of a panel
           // (it also occurs before the "in" animation)
           outFx : {
            '.textSlide h3'      : { opacity: 0, top  : '-100px', duration: 350 },
            '.textSlide li:odd'  : { opacity: 0, left : '-200px', duration: 350 },
            '.textSlide li:even' : { opacity: 0, left : '200px',  duration: 350 },
            '.textSlide img'     : { opacity: 0, duration: 350 },
            '.quoteSlide:first'  : { top : '-500px', duration: 350 },
            '.quoteSlide:last'   : { top : '500px', duration: 350 },
            '.expand'            : { width: '10%', top: '50%', left: '50%', duration: 350 }
           }
          });
});
</script>
	</body>
        
</html>
