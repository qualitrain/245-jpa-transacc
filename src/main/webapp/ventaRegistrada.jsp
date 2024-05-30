<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Venta</title>
</head>
<body>
	<h3>Venta registrada con el n&uacute;mero <span class="relevante">${venta.numVenta}</span> por un
		total de <span class="relevante">${importeVenta}</span></h3>
	<form action="./Controlador" method="get">
		<input type="hidden" name="vista" value="ventaRegistrada.jsp">
		<input type="submit" value="ok">
	</form>
</body>
</html>