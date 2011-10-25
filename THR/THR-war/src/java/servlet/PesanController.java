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
import model.Customer;
import model.PaketBingkisan;
import model.PaketJalan;
import model.PesanBingkisan;
import model.PesanKirimBingkisan;
import model.PesanPaket;

/**
 *
 * @author hyouda
 */
@WebServlet(name = "PesanController", urlPatterns = {"/PesanController"})
public class PesanController extends HttpServlet {

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
            Customer cu = new Customer();
            HttpSession session = request.getSession();
            if (session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            } else {
                if(session.getAttribute("jenisUser").equals("0"))
                {
                    cu = (Customer)session.getAttribute("user");
                }
                if(request.getParameter("menu").equals("buybingkisan")) {
                    PaketBingkisan pbi = new PaketBingkisan();
                    PaketBingkisan pb = pbi.getPaketbyid(request.getParameter("idp"));
                    session.setAttribute("bingkisan", pb);
                    response.sendRedirect("paketBingkisan/pesanPaketBingkisan.jsp");
                }
                else if(request.getParameter("menu").equals("prosesbuybingkisan"))
                {
                    PesanBingkisan bingkisan =new PesanBingkisan();
                    PesanKirimBingkisan kirim = new PesanKirimBingkisan();
                    PaketBingkisan pbi = (PaketBingkisan)session.getAttribute("bingkisan");
                    bingkisan.setIdp(pbi.getIdp());
                    bingkisan.setIdc(cu.getIdc());
                    bingkisan.setJumlah_paket(Integer.parseInt(request.getParameter("jumlah")));
                    bingkisan.setDue_date2(request.getParameter("due_date"));
                    String res = bingkisan.addPesanBingkisan();
                    int max = bingkisan.lastPesanBingkisan();
                    kirim.setIdc(cu.getIdc());
                    kirim.setIdp(pbi.getIdp());
                    kirim.setAlamat(request.getParameter("alamat"));
                    kirim.setHarga(bingkisan.getJumlah_paket()*pbi.getPrice());
                    res = kirim.addPesanKirimBingkisan(Integer.toString(max));
                    //customer.addCustomer();'
                    
                    //out.print(res);
                    //String res = customer.addCustomer();
                    String redirectURL = "paketBingkisan/daftarPaketBingkisan.jsp";
                    response.sendRedirect(redirectURL);
                }
                else if(request.getParameter("menu").equals("buyperjalanan")) {
                    PaketJalan pbi = new PaketJalan();
                    PaketJalan pb = pbi.getPaketbyid(request.getParameter("idp"));
                    session.setAttribute("perjalanan", pb);
                    response.sendRedirect("paketPerjalanan/pesanPaketPerjalanan.jsp");
                }
                else if(request.getParameter("menu").equals("prosesbuyperjalanan"))
                {
                    PesanPaket paket =new PesanPaket();
                    PaketJalan pj = (PaketJalan)session.getAttribute("perjalanan");
                    paket.setIdc(cu.getIdc());
                    paket.setIdp(pj.getIdp());
                    paket.setJumlah_paket(Integer.parseInt(request.getParameter("jumlah")));
                    String res = paket.addPesanPaket();
                    //customer.addCustomer();'
                    
                    out.print(res);
                    //String res = customer.addCustomer();
                    String redirectURL = "paketBingkisan/daftarPaketPerjalanan.jsp";
                    response.sendRedirect(redirectURL);
                }
                else if(request.getParameter("menu").equals("historipemesanan"))
                {
                    PesanPaket paket =new PesanPaket();
                    PesanBingkisan bingkisan =new PesanBingkisan();
                    PesanKirimBingkisan kirim = new PesanKirimBingkisan();
                    ArrayList<PesanPaket> pp =new ArrayList<PesanPaket>();
                    ArrayList<PesanBingkisan> pb =new ArrayList<PesanBingkisan>();
                    ArrayList<PesanKirimBingkisan> pk = new ArrayList<PesanKirimBingkisan>();
                    pp = paket.getPesanPaketbyIdc(Integer.toString(cu.getIdc()));
                    pb = bingkisan.getPesanBingkisanbyIdc(Integer.toString(cu.getIdc()));
                    pk = kirim.getPesanKirimBingkisanbyIdc(Integer.toString(cu.getIdc()));
                    session.setAttribute("getpaket", pp);
                    session.setAttribute("getbingkisan", pb);
                    session.setAttribute("getkirim", pk);
                    String redirectURL = "paketBingkisan/historiPesan.jsp";
                    response.sendRedirect(redirectURL);
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
}
