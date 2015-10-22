<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mapeo V4.5</title>
<!-- LIBRERIA JQUERY -->
	<script type="text/javascript" src="js/librerias/jquery-2.1.3.js"></script>
	<script type="text/javascript" src="js/librerias/angular.js"></script>
	<script type="text/javascript" src="js/librerias/ui-bootstrap/ui-bootstrap-tpls-0.13.0.js"></script>
<!-- LIBRERIA CORE -->  
    <script type="text/javascript" src="js/librerias/core/core-v2.js"></script>
<!-- COMPONENTES PAGINA WEB -->
	<script type="text/javascript" src="js/index.js"></script>
	<link type="text/css" href="css/estructura.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="js/librerias/ui-bootstrap/bootstrap.min.css" />
<!-- ICONO -->
	<link rel="apple-touch-icon" sizes="57x57" href="imagenes/icono/apple-icon-57x57.png">
	<link rel="apple-touch-icon" sizes="60x60" href="imagenes/icono/apple-icon-60x60.png">
	<link rel="apple-touch-icon" sizes="72x72" href="imagenes/icono/apple-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="76x76" href="imagenes/icono/apple-icon-76x76.png">
	<link rel="apple-touch-icon" sizes="114x114" href="imagenes/icono/apple-icon-114x114.png">
	<link rel="apple-touch-icon" sizes="120x120" href="imagenes/icono/apple-icon-120x120.png">
	<link rel="apple-touch-icon" sizes="144x144" href="imagenes/icono/apple-icon-144x144.png">
	<link rel="apple-touch-icon" sizes="152x152" href="imagenes/icono/apple-icon-152x152.png">
	<link rel="apple-touch-icon" sizes="180x180" href="imagenes/icono/apple-icon-180x180.png">
	<link rel="icon" type="image/png" sizes="192x192"  href="imagenes/icono/android-icon-192x192.png">
	<link rel="icon" type="image/png" sizes="32x32" href="imagenes/icono/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="96x96" href="imagenes/icono/favicon-96x96.png">
	<link rel="icon" type="image/png" sizes="16x16" href="imagenes/icono/favicon-16x16.png">
	<link rel="manifest" href="imagenes/icono/manifest.json">
	<meta name="msapplication-TileColor" content="#ffffff">
	<meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
	<meta name="theme-color" content="#ffffff">
</head>
<body class="POR_FONDO" ng-app="acceder" ng-controller="logeo">
<div class="fondoTop LOGEO_TOP"><img src="imagenes/logo.png" style="height: 35px;" /></div>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="120">&nbsp;</td>
  </tr>
  <tr>
    <td align="center">
		<table width="40%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td align="center" class="LOGEO_CENTRO_1">
				<div class="panel panel-default">
				  <div class="panel-heading"><img src="imagenes/logo.png" /></div>
				  <table class="table">
					<tbody>
					  <tr>
						<td><strong>Usuario : </strong></td>
						<td><input type="text" class="form-control" id="usuario" ng-model="frm.USU_W_COD_USUARIO"></td>
					  </tr>
					  <tr>
						<td><strong>Contraseña : </strong></td>
						<td><input type="password" class="form-control" id="clave" ng-model="frm.USU_W_CLAVE"></td>
					  </tr>
					  <tr>
						<td colspan="2" align="right"><a class="btn btn-primary" ng-click="conectar()" id="acceder"><i class="glyphicon glyphicon-off"></i> INICIAR SESIÓN</a></td>
					  </tr>
					</tbody>
				  </table>
				</div>
			  </td>
		  </tr>
		  <tr ng-show="mensaje.length>0">
			<td height="44" class="LOGEO_CENTRO_3"><alert type="danger">{{mensaje}}</alert></td>
		  </tr>
		</table>
	</td>
  </tr>
</table>
<div ng-include="'mapeo/web/piepagina.html'"></div>
</body>
</html>