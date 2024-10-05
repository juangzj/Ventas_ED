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
@WebServlet(name = "SvEditarArticulo", urlPatterns = {"/SvEditarArticulo"})
public class SvEditarArticulo extends HttpServlet {

    //Llamado al metodo construcopr para acceder a sus metodos
    GestionarVentas ventasCont = new GestionarVentas();
    // variable donde se guardará el id temporalmente
    int idArticulo;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idArticuloEditar = request.getParameter("idArticuloEditar");
        String nombreEditar = request.getParameter("nombreArticuloEditar");
        String descripionEditar = request.getParameter("descripcionArticuloEditar");
        String precioEditar = request.getParameter("precioArticuloEditar");
        String confirmacionEditar = request.getParameter("confirmarEdicion");

        System.out.println("Los valores de la edicion son: " + idArticuloEditar + nombreEditar + descripionEditar + precioEditar + confirmacionEditar);
        if (idArticuloEditar != null) {
            idArticulo = Integer.parseInt(idArticuloEditar);
        }

        if (confirmacionEditar != null) {
            if (nombreEditar != null && descripionEditar != null && precioEditar != null) {
                ventasCont.editarArticulo(idArticulo, nombreEditar, descripionEditar, Double.parseDouble(precioEditar));
            }
        }

        //redireccionamos a la pagina index
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
