/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
@WebServlet(name = "SvEliminarArticulo", urlPatterns = {"/SvEliminarArticulo"})
public class SvEliminarArticulo extends HttpServlet {
    
    //Llamado al metodo constrcutor para acceder a sus metodos
    GestionarVentas ventasCont = new GestionarVentas();
    //Variblae donde se guardará el id temporalmente
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

        String idArticuloEliminar = request.getParameter("idArticuloEliminar");
        String confirmacionEliminacion = request.getParameter("confirmacionEliminacion");
        
        if(idArticuloEliminar != null ){
            idArticulo = Integer.parseInt(idArticuloEliminar);
        }
        if(confirmacionEliminacion != null ){
            ventasCont.eliminarArticulo(idArticulo);
        }


        // Redirigir pagina
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
