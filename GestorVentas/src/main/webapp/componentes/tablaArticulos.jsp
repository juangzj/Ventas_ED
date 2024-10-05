<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Articulo"%>
<%@page import="modelo.GestionarVentas"%>

<div class="container mt-4">
    <!-- Botón para agregar un artículo -->  
    <div class="text-center mb-3">
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModalAgregarArticulo">Agregar Artículo</button>
    </div>
</div>

<!-- Tabla de Artículos -->
<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-10"> <!-- Cambiado a col-md-10 -->
            <div class="card card-body">
                <table class="table table-bordered mt-3">  
                    <thead>  
                        <tr>
                            <th>ID</th> 
                            <th>Nombre</th> 
                            <th>Descripción</th>
                            <th>Precio</th>
                            <th>Acciones</th>
                        </tr>
                    </thead> 
                    <tbody>
                        <%
                            GestionarVentas ventasCont = new GestionarVentas();
                            ArrayList<Articulo> articulosExistentes = ventasCont.mostrarArticulos();

                            if (articulosExistentes != null) {
                                for (Articulo articulo : articulosExistentes) {
                        %>
                        <tr> 
                            <td><%=articulo.getId()%></td>  
                            <td><%=articulo.getNombre()%></td>
                            <td><%=articulo.getDescripcion()%></td>
                            <td><%=articulo.getPrecio()%></td>
                            <td>
                                <!-- Importamos el botón para ver la información de un artículo -->
                                <%@include file="verArticuloBoton.jsp" %>
                                <!-- Botón para abrir el modal de edición -->
                                <button type="button" class="btn btn-secondary editarArticulo-btn" data-bs-toggle="modal" data-bs-target="#exampleModalEditar" data-id="<%= articulo.getId() %>"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                        <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                        <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/>
                                    </svg></button>

                                <!-- Botón para eliminar un artículo -->
                                <button type="button" class="btn btn-danger eliminarArticulo-btn" data-bs-toggle="modal" data-bs-target="#exampleModal" data-id="<%=articulo.getId()%>">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3" viewBox="0 0 16 16">
                                        <path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5M11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47M8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5"/>
                                    </svg>
                                </button>
                            </td>
                        </tr>
                        <%    }
                        } else {
                        %>
                        <tr> 
                            <td colspan="5" class="text-center">No hay datos que mostrar</td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>  
                </table>       
            </div>
        </div>
    </div>
</div>

<!--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->                   
<!-- Modal Para Eliminar un artículo -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Eliminar</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>¿Estás seguro de que deseas ELIMINAR el artículo con ID: <span id="articuloIdEliminar"></span>?</p>
            </div>
            <form action="SvEliminarArticulo" method="POST">
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary" name="confirmacionEliminacion" value="confirmacionEliminar">Eliminar</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------> 
<!-- Modal para agregar artículo -->
<div class="modal fade" id="exampleModalAgregarArticulo" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Agregar Artículo</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- Formulario para agregar un artículo -->
                <form action="SvAniadirArticulo" method="POST"> 
                    <div class="mb-3">
                        <label for="nombreArticulo" class="form-label">Nombre</label>
                        <input type="text" class="form-control"  name="nombreArticulo" required>
                    </div>
                    <div class="mb-3">
                        <label for="descripcionArticulo" class="form-label">Descripción</label>
                        <textarea class="form-control"  name="descripcionArticulo" required></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="precioArticulo" class="form-label">Precio</label>
                        <input type="number" class="form-control"  name="precioArticulo" step="0.01" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Agregar</button>
                </form>
            </div>
        </div>
    </div>
</div>
<!---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------> 
<!-- Modal para editar un artículo -->

<div class="modal fade" id="exampleModalEditar" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Editar</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="SvEditarArticulo" method="POST"> 
                <div class="modal-body">
                    <p>¿Estás seguro de que deseas EDITAR el archivo con ID: <span id="ArticuloIdEditar"></span>?</p>
                    <!-- Formulario para editar un artículo -->

                    <div class="mb-3">
                        <label for="nombreArticulo" class="form-label">Nombre</label>
                        <input type="text" class="form-control"  name="nombreArticuloEditar" required>
                    </div>
                    <div class="mb-3">
                        <label for="descripcionArticulo" class="form-label">Descripción</label>
                        <textarea class="form-control"  name="descripcionArticuloEditar" required></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="precioArticulo" class="form-label">Precio</label>
                        <input type="number" class="form-control"  name="precioArticuloEditar" step="0.01" required>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    <button type="submit" class="btn btn-primary" name="confirmarEdicion" value="editar">Editar</button>
                </div>
            </form>
        </div>
    </div>
</div>





<!---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------> 

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------> 
<!-- Script para obtener el id de la valoracion que se va a elimnar y despues enviarla por ajaxx al servlet -->
<script>
    // Captur clic y mandar el id de el articlo que se va a eliminar 
    $('.eliminarArticulo-btn').on('click', function () {
        // Obtener el ID del articulo
        const idArticuloEliminar = $(this).data('id');
        // Mostrar el ID en el modal de eliminación
        $('#articuloIdEliminar').text(idArticuloEliminar);

        // Envío de ID al servlet a través de AJAX (método POST)
        $.ajax({
            url: 'SvEliminarArticulo', // Url donde se enviara los datos(en este caso el id)
            method: 'POST', // Método de solicitud por donde llegara la información al servlet
            data: {idArticuloEliminar: idArticuloEliminar}, // Datos a enviar (en este caso, el ID)
            success: function (response) {
                // Manejar la respuesta del servidor si es necesario
            },
            error: function (xhr, status, error) {
                console.error('Error al enviar la solicitud:', error);
            }
        });
    });
</script>
<!---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------> 
<!-- Script para obtener el id del ariticulo que se va a editar y despues enviarla por ajaxx al servlet -->
<script>
    // Captur clic y mandar el id del  articulo que se va a editar 
    $('.editarArticulo-btn').on('click', function () {
        // Obtener el ID del articulo
        const idArticuloEditar = $(this).data('id');
        // Mostrar el ID en el modal de la edicion
        $('#ArticuloIdEditar').text(idArticuloEditar);

        // Envío de ID al servlet a través de AJAX (método POST)
        $.ajax({
            url: 'SvEditarArticulo', // Url donde se enviara los datos(en este caso el id)
            method: 'POST', // Método de solicitud por donde llegara la información al servlet
            data: {idArticuloEditar: idArticuloEditar}, // Datos a enviar (en este caso, el ID)
            success: function (response) {
                // Manejar la respuesta del servidor si es necesario
            },
            error: function (xhr, status, error) {
                console.error('Error al enviar la solicitud:', error);
            }
        });
    });
</script>