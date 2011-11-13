/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ItemBingkisan;
import model.ItemJalan;
import model.PaketBingkisan;
import model.PaketJalan;

/**
 *
 * @author Albadr
 */
@WebServlet(name = "PaketMobile", urlPatterns = {"/paketmobile"})
public class PaketMobile extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("mobile") != null) {
                if (request.getParameter("filter") != null) {
                    if (request.getParameter("request").equals("bingkisan")) {
                        String mark = request.getParameter("mark");
                        String price = request.getParameter("price");
                        String desc = request.getParameter("desc");
                        String name = request.getParameter("name");
                        PaketBingkisan pb = new PaketBingkisan();
                        out.print(pb.getFilterAsJSON(mark, price, name, desc));
                    } else if (request.getParameter("request").equals("jalan")) {
                        String mark = request.getParameter("mark");
                        String price = request.getParameter("price");
                        String name = request.getParameter("name");
                        String origin = request.getParameter("origin");
                        String dest = request.getParameter("dest");
                        PaketJalan pb = new PaketJalan();
                        out.print(pb.getSearchInJSON(mark, price, name, origin, dest));
                    } else {
                        out.println("Invalid request.");
                    }
                } else {
                    if (request.getParameter("request").equals("jalan")) {
                        PaketJalan pj = new PaketJalan();
                        out.print(pj.getPaket_asJSON());
                    } else if (request.getParameter("request").equals("bingkisan")) {
                        PaketBingkisan pb = new PaketBingkisan();
                        out.print(pb.getPaket_asJSON());
                    } else if (request.getParameter("request").equals("ijalan") && request.getParameter("idp") != null) {
                        String idp = request.getParameter("idp");
                        ItemJalan ij = new ItemJalan();
                        out.print(ij.getItems_asJSON(idp));
                    } else if (request.getParameter("request").equals("ibingkisan") && request.getParameter("idp") != null) {
                        String idp = request.getParameter("idp");
                        ItemBingkisan ib = new ItemBingkisan();
                        out.print(ib.getItems_asJSON(idp));
                    } else {
                        out.println("Invalid request.");
                    }
                }
            } else {
                out.println("Invalid request.");
            };
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PaketMobile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PaketMobile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
