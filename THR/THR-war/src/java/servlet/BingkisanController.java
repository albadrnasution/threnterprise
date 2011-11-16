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
import model.IPBingkisan;
import model.ItemBingkisan;
import model.PaketBingkisan;

/**
 *
 * @author Didik
 */
@WebServlet(name = "BingkisanController", urlPatterns = {"/BingkisanController"})
public class BingkisanController extends HttpServlet {

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
                    if (request.getParameter("tipe").equals("item")) {
                        if (request.getParameter("aksi") != null) {
                            if (request.getParameter("aksi").equals("create")) {
                                response.sendRedirect("paketBingkisan/menyusunItem.jsp?item=bingkisan&option=create");
                            }
                        }else{
                        response.sendRedirect("paketBingkisan/mengelolaItem.jsp?manageitem=bingkisan");
                        }
                    }
                    if (request.getParameter("tipe").equals("paket")) {
                        response.sendRedirect("paketBingkisan/menyusunPBPage.jsp?aksi=susun");
                    }
                } else if (request.getParameter("mode").equals("edit")) {
                    if (request.getParameter("tipe").equals("item")) {
                        response.sendRedirect("paketBingkisan/menyusunItem.jsp?item=bingkisan&option=edit&id=" + request.getParameter("id"));
                    }
                    if (request.getParameter("tipe").equals("paket")) {
                        response.sendRedirect("paketBingkisan/menyusunPBPage.jsp?aksi=edit&id=" + request.getParameter("id"));
                    }
                } else if (request.getParameter("mode").equals("delete")) {
                    if (request.getParameter("tipe").equals("paket")) {
                        PaketBingkisan p = new PaketBingkisan();
                        p.deleteP(request.getParameter("id"));
                        if (getItem(request.getParameter("id")).isEmpty()) {
                            response.sendRedirect("paketBingkisan/mengelolaPaket.jsp?success=1");
                        } else {
                            response.sendRedirect("paketBingkisan/mengelolaPaket.jsp?success=0");
                        }
                    }
                    if (request.getParameter("tipe").equals("item")) {
                        ItemBingkisan ib = new ItemBingkisan();
                        String res = ib.deleteItem(request.getParameter("id"));
                        if (res != null) {
                            response.sendRedirect("paketBingkisan/mengelolaItem.jsp?success=1&manageitem=bingkisan");
                        } else {
                            response.sendRedirect("paketBingkisan/mengelolaItem.jsp?success=0&manageitem=bingkisan");
                        }
                    }
                } else if (request.getParameter("mode").equals("cari")) {
                    String harga = request.getParameter("harga");
                    String name = request.getParameter("name");
                    String operator = request.getParameter("operator");
                    String desc = request.getParameter("desc");
                    HttpSession session = request.getSession();
                    if (harga.equals("") && name.equals("") && desc.equals("")) {
                        response.sendRedirect("paketBingkisan/daftarPaketBingkisan.jsp");
                    } else {
                        ArrayList<PaketBingkisan> p;
                        PaketBingkisan pj = new PaketBingkisan();
                        p = pj.getSearchResult(harga, operator, name, desc);
                        if (p.isEmpty()) {
                            response.sendRedirect("paketBingkisan/daftarPaketBingkisan.jsp?empty=1");
                        } else {
                            session.setAttribute("PaketBingkisan", p);
                            session.setAttribute("filter", "1");
                            response.sendRedirect("paketBingkisan/daftarPaketBingkisan.jsp");
                        }
                    }
                }
            } else if (request.getParameter("act") != null) {
                if (request.getParameter("act").equals("addPaket")) {
                    String n = request.getParameter("s_nama_paket");
                    String d = request.getParameter("s_desc");
                    String h = request.getParameter("s_harga");
                    String[] it = request.getParameterValues("s_item");
                    String[] ni = request.getParameterValues("nitem");

                    if (n.equals("") || d.equals("") || h.equals("")) {
                        response.sendRedirect("paketBingkisan/mengelolaPaket.jsp?success=0");
                    } else if (!n.equals("") && !d.equals("") && !h.equals("") && it != null) {
                        PaketBingkisan pb = new PaketBingkisan();
                        pb.setPaket(n, d, h);

                        int i = pb.lastID();
                        IPBingkisan ipb = new IPBingkisan();
                        if (it != null && it.length != 0) {
                            for (int x = 0; x < it.length; x++) {
                                ipb.setIPBingkisan(it[x], i, ni[x]);
                            }
                        }
                        response.sendRedirect("paketBingkisan/mengelolaPaket.jsp?success=2");
                    } else {
                        response.sendRedirect("paketBingkisan/mengelolaPaket.jsp?success=0");
                    }
                } else if (request.getParameter("act").equals("editPaket")) {
                    String idp = request.getParameter("idp");
                    String n = request.getParameter("s_nama_paket");
                    String d = request.getParameter("s_desc");
                    String h = request.getParameter("s_harga");
                    String[] it = request.getParameterValues("s_item");
                    String[] ni = request.getParameterValues("nitem");

                    if (n.equals("") || d.equals("") || h.equals("")) {
                        response.sendRedirect("paketBingkisan/mengelolaPaket.jsp?success=0");
                    } else if (!n.equals("") && !d.equals("") && !h.equals("") && it != null && ni != null) {
                        String res = null;
                        PaketBingkisan pb = new PaketBingkisan();
                        res = pb.updatePaket(idp, n, d, h);

                        int i = Integer.parseInt(idp);
                        IPBingkisan ipb = new IPBingkisan();
                        if (it != null && it.length != 0) {
                            ipb.deleteIPB(idp);
                            for (int x = 0; x < it.length; x++) {
                                ipb.setIPBingkisan(it[x], i, ni[x]);
                            }
                        }
                        response.sendRedirect("paketBingkisan/mengelolaPaket.jsp?success=2");
                    } else {
                        response.sendRedirect("paketBingkisan/mengelolaPaket.jsp?success=0");
                    }
                }else if(request.getParameter("act").equals("addItem")){
                    String idp = request.getParameter("idi");
                    String n = request.getParameter("nama_item");
                    String d = request.getParameter("desc");
                    String h = request.getParameter("harga");
                    String idt = request.getParameter("idtempe");

                    if (n.equals("") || d.equals("") || h.equals("") || idt.equals("")) {
                        response.sendRedirect("paketBingkisan/mengelolaItem.jsp?success=0&manageitem=bingkisan");
                    } else if (!n.equals("") && !d.equals("") && !h.equals("") && !idt.equals("")) {
                        String res = null;
                        ItemBingkisan ib = new ItemBingkisan();
                        ib.setItem(n, d, h, idt);                        
                        response.sendRedirect("paketBingkisan/mengelolaItem.jsp?success=2&manageitem=bingkisan");
                    } else {
                        response.sendRedirect("paketBingkisan/mengelolaItem.jsp?success=0&manageitem=bingkisan");
                    }
                }else if(request.getParameter("act").equals("editItem")){
                    String idi = request.getParameter("idi");
                    String n = request.getParameter("nama_item");
                    String d = request.getParameter("desc");
                    String h = request.getParameter("harga");
                    String idt = request.getParameter("idtempe");

                    if (idi.equals("") || n.equals("") || d.equals("") || h.equals("") || idt.equals("")) {
                        response.sendRedirect("paketBingkisan/mengelolaItem.jsp?success=0&manageitem=bingkisan");
                    } else if (!idi.equals("") && !n.equals("") && !d.equals("") && !h.equals("") && !idt.equals("")) {
                        String res = null;
                        ItemBingkisan ib = new ItemBingkisan();
                        ib.updateItem(idi, n, d, h, idt);                        
                        response.sendRedirect("paketBingkisan/mengelolaItem.jsp?success=2&manageitem=bingkisan");
                    } else {
                        response.sendRedirect("paketBingkisan/mengelolaItem.jsp?success=0&manageitem=bingkisan");
                    }
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

    public ArrayList<PaketBingkisan> showPaket() {
        PaketBingkisan p = new PaketBingkisan();
        return p.getPaket();
    }

    public ArrayList<PaketBingkisan> showPaket(String id) {
        PaketBingkisan p = new PaketBingkisan();
        return p.getPaket(id);
    }

    public ArrayList<ItemBingkisan> showDetail(String id) {
        ItemBingkisan ij = new ItemBingkisan();
        return ij.getItem(id);
    }

    public ArrayList<ItemBingkisan> getItem() {
        ItemBingkisan ij = new ItemBingkisan();
        return ij.getItem();
    }

    public ArrayList<ItemBingkisan> getItem(String id) {
        ItemBingkisan ij = new ItemBingkisan();
        return ij.getItem(id);
    }

    public PaketBingkisan deletePaket(String id) {
        PaketBingkisan p = new PaketBingkisan();
        p.deleteP(id);
        return p;
    }

    public IPBingkisan getIPB(String idi, String idp) {
        IPBingkisan ij = new IPBingkisan();
        return ij.getIPB(idi, idp);
    }
}
