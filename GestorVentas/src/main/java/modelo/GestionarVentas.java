package modelo;

import conexion.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Usuario 1
 */
public class GestionarVentas {

    //Metodo constructor vacio para acceder a los metodos de la clase
    public GestionarVentas() {
    }

    /**
     * Metodo para obtener todos los articulos existentes
     *
     * @return lista de articulos exitentes
     */
    public ArrayList<Articulo> mostrarArticulos() {
        ArrayList<Articulo> articulosExistentes = new ArrayList<>();
        String query = "SELECT id, nombre, descripcion, precio FROM articulos";

        Connection conexion = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conexion = Conectar.getConexion();
            statement = conexion.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                double precio = resultSet.getDouble("precio");

                Articulo articulo = new Articulo(id, nombre, descripcion, precio);
                articulosExistentes.add(articulo);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return articulosExistentes;
    }

    /**
     * Metodo Eliminar un articulo mediante el id
     *
     * @param idArticulo
     * @return
     */
    public boolean eliminarArticulo(int idArticulo) {
        String query = "DELETE FROM articulos WHERE id = ?"; // Consulta SQL para eliminar el artículo
        Connection conexion = null;
        PreparedStatement preparedStatement = null;

        try {
            // Obtener conexión a la base de datos
            conexion = Conectar.getConexion();

            // Preparar la sentencia SQL con el parámetro del id
            preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setInt(1, idArticulo); // Asignar el idArticulo al parámetro de la consulta

            // Ejecutar la sentencia SQL
            int filasAfectadas = preparedStatement.executeUpdate();

            // Verificar si se eliminó algún artículo
            if (filasAfectadas > 0) {
                System.out.println("El artículo con id " + idArticulo + " fue eliminado correctamente.");
                return true; // Retornar true si se eliminó exitosamente
            } else {
                System.out.println("No se encontró ningún artículo con el id " + idArticulo + ".");
                return false; // Retornar false si no se encontró ningún artículo con ese ID
            }

        } catch (SQLException e) {
            // Manejo de excepciones
            System.err.println("Error al intentar eliminar el artículo: " + e.getMessage());
            return false;
        } finally {
            // Cerrar recursos
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
    /**
     * Metodo para ingresar un nuevo articulo
     * @param nombre
     * @param descripcion
     * @param precio
     * @return 
     */
    public boolean agregarArticulo(String nombre, String descripcion, double precio) {
        String query = "INSERT INTO articulos (nombre, descripcion, precio) VALUES (?, ?, ?)";
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        boolean resultado = false;

        try {
            // Obtener conexión a la base de datos
            conexion = Conectar.getConexion();

            // Preparar la sentencia SQL con los parámetros correspondientes
            preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1, nombre); // Asignar el nombre al parámetro 1
            preparedStatement.setString(2, descripcion); // Asignar la descripción al parámetro 2
            preparedStatement.setDouble(3, precio); // Asignar el precio al parámetro 3

            // Ejecutar la inserción en la base de datos
            int filasAfectadas = preparedStatement.executeUpdate();

            // Verificar si la inserción fue exitosa
            if (filasAfectadas > 0) {
                resultado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Mostrar errores de SQL
        } finally {
            try {
                // Cerrar los recursos
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return resultado; // Retorna true si se agregó correctamente
    }

}
