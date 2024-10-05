package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.GestionarVentas;

/**
 *
 * @author Usuario 1
 */
@WebServlet(name = "SvAniadirArticulo", urlPatterns = {"/SvAniadirArticulo"})
public class SvAniadirArticulo extends HttpServlet {

    //LLamado al metodo constructor para accceder a sus metodos
    GestionarVentas ventasCont = new GestionarVentas();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Obtenemos los valores ingresador por el usauario
        String nombre = request.getParameter("nombreArticulo");
        String descripcion = request.getParameter("descripcionArticulo");
        String precio = request.getParameter("precioArticulo");

        //Agregamos el nuevo articulo comprobado antes si llegan de forma correcta
        if (nombre != null && descripcion != null && precio != null) {
            ventasCont.agregarArticulo(nombre, descripcion, Double.parseDouble(precio));
        }
        //redireccionamos a la pagina index
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
