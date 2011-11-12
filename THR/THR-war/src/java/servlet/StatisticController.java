/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.PesanPaket;

/**
 *
 * @author Didik
 */
@WebServlet(name = "StatisticController", urlPatterns = {"/StatisticController"})
public class StatisticController extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if(request.getParameter("orderby") != null){
                    ArrayList<PesanPaket> ap = new ArrayList<PesanPaket>();
                    PesanPaket pp = new PesanPaket();
                    HttpSession session = request.getSession();
                    if (request.getParameter("orderby").equals("payment")) {
                        ap = pp.getPesanPaket("pay_status");                    
                    }else if (request.getParameter("orderby").equals("paket")) {
                        ap = pp.getPesanPaket("paket_name");
                    }else if (request.getParameter("orderby").equals("order")) {
                        ap = pp.getPesanPaket("order_date");
                    }else if (request.getParameter("orderby").equals("jumlah")) {
                        ap = pp.getPesanPaket("jumlah_paket");
                    }else if (request.getParameter("orderby").equals("item")) {
                        ArrayList<PesanPaket> api = new ArrayList<PesanPaket>();
                        PesanPaket pi = new PesanPaket();
                        api = pi.getPesanPaketbyItem("name");
                        if (api.isEmpty()) {
                            //response.sendRedirect("paketPerjalanan/daftarPaketPerjalanan.jsp?empty=1");
                        } else {
                            session.setAttribute("PesanItem", api);
                            session.setAttribute("sort", "1");
                            response.sendRedirect("statistic.jsp");
                        }
                    }else if (request.getParameter("orderby").equals("jitem")) {
                        ArrayList<PesanPaket> api = new ArrayList<PesanPaket>();
                        PesanPaket pi = new PesanPaket();
                        api = pi.getPesanPaketbyItem("jumlah");
                        if (api.isEmpty()) {
                            //response.sendRedirect("paketPerjalanan/daftarPaketPerjalanan.jsp?empty=1");
                        } else {
                            session.setAttribute("PesanItem", api);
                            session.setAttribute("sort", "1");
                            response.sendRedirect("statistic.jsp");
                        }
                    }
                    if (ap.isEmpty()) {
                            //response.sendRedirect("paketPerjalanan/daftarPaketPerjalanan.jsp?empty=1");
                    } else {
                        session.setAttribute("PesanPaket", ap);
                        session.setAttribute("sort", "1");
                        response.sendRedirect("statistic.jsp");
                    }
                    
            }
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


    public ArrayList<PesanPaket> showPesan(String s){
        PesanPaket pp = new PesanPaket();
        return pp.getPesanPaket(s);
    }
    
    public ArrayList<PesanPaket> showPesanItem(String s){
        PesanPaket pp = new PesanPaket();
        return pp.getPesanPaketbyItem(s);
    }

}
