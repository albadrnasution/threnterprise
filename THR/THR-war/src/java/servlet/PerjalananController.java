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
import model.*;

/**
 *
 * @author Didik
 */
@WebServlet(name = "PerjalananController", urlPatterns = {"/PerjalananController"})
public class PerjalananController extends HttpServlet {

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
            if (request.getParameter("mode") != null) {
                if (request.getParameter("mode").equals("susun")) {
                        response.sendRedirect("paketPerjalanan/menyusunPPPage.jsp?aksi=susun");
                } else if(request.getParameter("mode").equals("edit")){
                    response.sendRedirect("paketPerjalanan/menyusunPPPage.jsp?aksi=edit&id=" + request.getParameter("id"));
                }else if (request.getParameter("action").equals("delete")) {
                        PaketJalan p = new PaketJalan();
                        String x = p.deleteP(request.getParameter("id"));
                        response.sendRedirect("paketPerjalanan/mengelolaPaket.jsp?success=1");
                }else if (request.getParameter("mode").equals("cari")) {
                    String harga = request.getParameter("harga");
                    String name = request.getParameter("name");
                    String operator = request.getParameter("operator");
                    String desc = request.getParameter("desc");
                    HttpSession session = request.getSession();
                    if (harga.equals("") || operator.equals("")) {
                        response.sendRedirect("paketPerjalanan/daftarPaketPerjalanan.jsp");
                    } else {
                        ArrayList<PaketJalan> p;
                        PaketJalan pj = new PaketJalan();
                        p = pj.getSearchResult(harga, operator, name, desc);
                        if (p.isEmpty()) {
                            response.sendRedirect("paketPerjalanan/daftarPaketPerjalanan.jsp?empty=1");
                        } else {
                            session.setAttribute("PaketJalan", p);
                            session.setAttribute("filter", "1");
                            response.sendRedirect("paketPerjalanan/daftarPaketPerjalanan.jsp");

                        }
                    }
                }                
            }else if(request.getParameter("act").equals("addPaket")){
                    String n = request.getParameter("s_nama_paket");
                    String d = request.getParameter("s_desc");
                    String h = request.getParameter("s_harga");
                    String[] it = request.getParameterValues("s_item");
                    String na = request.getParameter("s_nadult");
                    String nc = request.getParameter("s_nchild");
                    String t = request.getParameter("s_time");
                    if (n.equals("") && d.equals("") && h.equals("") && na.equals("") && nc.equals("") && t.equals("")) {
                        response.sendRedirect("paketPerjalanan/menyusunPPPage.jsp?success=0");
                    } else {
                        PaketJalan pb = new PaketJalan();
                        pb.setPaket(n, d, h, na, nc, t);

                        int i = pb.lastID();
                        IPJalan ipb = new IPJalan();
                        if (it != null && it.length != 0) {
                            for (int x = 0; x < it.length; x++) {
                                ipb.setIPJalan(it[x], i);
                            }
                        }
                        response.sendRedirect("paketPerjalanan/menyusunPPPage.jsp?success=1");
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

    public ArrayList<PaketJalan> showPaket() {
        PaketJalan p = new PaketJalan();
        return p.getPaket();
    }

    public ArrayList<PaketJalan> showPaket(String id) {
        PaketJalan p = new PaketJalan();
        return p.getPaket(id);
    }
    
    public ArrayList<ItemJalan> showDetail(String id) {
        ItemJalan ij = new ItemJalan();
        return ij.getItem(id);
    }

    public ArrayList<ItemJalan> getItem() {
        ItemJalan ij = new ItemJalan();
        return ij.getItem();
    }
    
    public ArrayList<ItemJalan> getItem(String id) {
        ItemJalan ij = new ItemJalan();
        return ij.getItem(id);
    }
}
