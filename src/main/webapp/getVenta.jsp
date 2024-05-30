 <%@ taglib prefix = "c" uri="jakarta.tags.core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Venta</title>
	<link rel="stylesheet" href="./css/IU.css">	
	<script type="text/javascript">
	function enrutarAregistroVenta(){
		var hdnAccion = document.getElementById("hdnAccion");
		hdnAccion.value = "registrarVta";
	}
	</script>
 </head>
<body>
	<div id="contenedor">
		<div id="titForma">
			<h3>Registro de Ventas</h3>
		</div>
		<form id="fmVenta" action="./Controlador" method="get">
			<div id="involucrados">
				<label for="selIdCte">Cliente:</label> 
				<select id="selIdCte"
					name="idCte">
					<c:forEach var="clienteI" items="${clientes}">
						<c:choose>
							<c:when test="${clienteI.numCte eq idCteSel}">
								<option value="${clienteI.numCte}" selected="selected">${clienteI.nombre}</option>
							</c:when>
							<c:otherwise>
								<option value="${clienteI.numCte}">${clienteI.nombre}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				<label for="selIdVendedor" class="labelSecundaria">Vendedor:</label> 
				<select id="selIdVendedor"
					name="idVendedor">
					<c:forEach var="vendedorI" items="${vendedores}">
						<c:choose>
							<c:when test="${vendedorI.numVendedor eq idVendedorSel}">
								<option value="${vendedorI.numVendedor}" selected="selected">${vendedorI.nombre}</option>
							</c:when>
							<c:otherwise>
								<option value="${vendedorI.numVendedor}">${vendedorI.nombre}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<p>
			
			<div id="carrito">
				<table>
					<thead>
						<tr>
							<th>Clave</th>
							<th>Descripci&oacute;n</th>
							<th>Cant</th>
							<th>Precio</th>
							<th>Total</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="detalleI" items="${carritoCompra}">
							<tr>
							<td>${detalleI.cveArticulo}</td>
							<td>${detalleI.descripcion}</td>
							<td class="cantidad">${detalleI.cantidad}</td>
							<td class="importe">${detalleI.precioFormateado}</td>
							<td class="importe total">${detalleI.importeItemFormateado}</td>
							</tr>
						</c:forEach>
							<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td class="importe total">${importeVenta}</td>
							</tr>
					</tbody>
				</table>
			</div>
			<p>
			
			<div id="selecArt">
				<label for="selIdArt">Art&iacute;culo:</label> 
				<select id="selIdArt"
					name="idArt">
					<c:forEach var="artI" items="${mapArticulos}">
						<option value="${artI.value}">${artI.key}</option>
					</c:forEach>
				</select> 
				<label for="idCantArt" class="labelSecundaria">Cantidad:</label>
				<input id="idCantArt"
					name="cantArt" type="number" value="1" maxlength="4"/>
				<p>
			</div>
			<input type="hidden" name="vista" value="getVenta.jsp"> 
			<input type="hidden" id="hdnAccion" name="accion" value="agregarArt"> 
			<input type="submit"
				value="Agregar" > 
			<button type="submit" onclick="enrutarAregistroVenta()">Registrar Venta</button>
		</form>
		<div id="seccmensajes">
			<span id="error">${mensaje}</span>
		</div>
	</div>
</body>
</html>