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
import model.PesanBingkisan;
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
            ArrayList<PesanPaket> ap = new ArrayList<PesanPaket>();
            PesanPaket pp = new PesanPaket();
            ArrayList<PesanPaket> api = new ArrayList<PesanPaket>();
            PesanPaket pi = new PesanPaket();
            ArrayList<PesanBingkisan> ab = new ArrayList<PesanBingkisan>();
            PesanBingkisan pb = new PesanBingkisan();
            ArrayList<PesanBingkisan> abi = new ArrayList<PesanBingkisan>();
            PesanBingkisan pbi = new PesanBingkisan();
            HttpSession session = request.getSession();
            if(request.getParameter("mode") != null){
                if(request.getParameter("mode").equals("stats")){
                    String tipe = request.getParameter("ptipe");
                    String opsi = request.getParameter("pitem");
                    String s = request.getParameter("start");
                    String e = request.getParameter("end");
                    if(s.equals(null) || e.equals(null)){
                        s = "";
                        e = "";
                    }
                    out.print(tipe);
                    if(tipe.equals("Paket Bingkisan")){
                        if(opsi.equals("Item")){
                            abi = pbi.getPesanBingkisanbyItem("",s,e);
                            if (abi.isEmpty()) {
                                response.sendRedirect("statistic.jsp?empty=1");
                            } else {
                                session.setAttribute("PesanItemBingkisan", abi);
                                session.setAttribute("sortitemb", "1");
                                session.setAttribute("showtable", "1");
                                response.sendRedirect("statistic.jsp");
                            }
                        }else if(opsi.equals("Paket")){
                            ab = pb.getPesanBingkisan("",s,e);
                            if (ab.isEmpty()) {
                                response.sendRedirect("statistic.jsp?empty=1");
                        } else {
                            session.setAttribute("PesanBingkisan", ab);
                            session.setAttribute("sortb", "1");
                            session.setAttribute("showtable", "2");
                            response.sendRedirect("statistic.jsp");
                        }
                        }
                    }else if(tipe.equals("Paket Perjalanan")){
                        if(opsi.equals("Item")){
                            api = pi.getPesanPaketbyItem("",s,e);
                            if (api.isEmpty()) {
                                response.sendRedirect("statistic.jsp?empty=1");
                            } else {
                                session.setAttribute("PesanItem", api);
                                session.setAttribute("sortitem", "1");
                                session.setAttribute("showtable", "3");
                                response.sendRedirect("statistic.jsp");
                            }
                        }else if(opsi.equals("Paket")){
                            ap = pp.getPesanPaket("",s,e);
                            if (ap.isEmpty()) {
                                response.sendRedirect("statistic.jsp?empty=1");
                        } else {
                            session.setAttribute("PesanPaket", ap);
                            session.setAttribute("sort", "1");
                            session.setAttribute("showtable", "4");
                            response.sendRedirect("statistic.jsp");
                        }
                        }
                    }else{
                        session.setAttribute("showtable", "4");
                        response.sendRedirect("statistic.jsp");
                    }
                }
            }
            
            
            
            
            if(request.getParameter("type") != null){
                if(request.getParameter("type").equals("perjalanan")){
                    if(request.getParameter("orderby") != null){      
                        if (request.getParameter("orderby").equals("payment")) {
                            ap = pp.getPesanPaket("pay_status","","");
                        }else if (request.getParameter("orderby").equals("paket")) {
                            ap = pp.getPesanPaket("paket_name","","");
                        }else if (request.getParameter("orderby").equals("order")) {
                            ap = pp.getPesanPaket("order_date","","");
                        }else if (request.getParameter("orderby").equals("jumlah")) {
                            ap = pp.getPesanPaket("jumlah_paket","","");
                        }else if (request.getParameter("orderby").equals("harga")) {
                            ap = pp.getPesanPaket("price","","");
                        }else if (request.getParameter("orderby").equals("item")) {
                            api = pi.getPesanPaketbyItem("name","","");
                            if (api.isEmpty()) {
                                //response.sendRedirect("statistic.jsp?empty=1");
                            } else {
                                session.setAttribute("PesanItem", api);
                                session.setAttribute("sortitem", "1");
                                session.setAttribute("showtable", "3");
                                response.sendRedirect("statistic.jsp");
                            }
                        }else if (request.getParameter("orderby").equals("jitem")) {
                            api = pi.getPesanPaketbyItem("jumlah","","");
                            if (api.isEmpty()) {
                                //response.sendRedirect("statistic.jsp?empty=1");
                            } else {
                                session.setAttribute("PesanItem", api);
                                session.setAttribute("sortitem", "1");
                                session.setAttribute("showtable", "3");
                                response.sendRedirect("statistic.jsp");
                            }
                        }
                        if (ap.isEmpty()) {
                                //response.sendRedirect("statistic.jsp?empty=1");
                        } else {
                            session.setAttribute("PesanPaket", ap);
                            session.setAttribute("sort", "1");
                            session.setAttribute("showtable", "4");
                            response.sendRedirect("statistic.jsp");
                        }
                    }
                }else if(request.getParameter("type").equals("bingkisan")){
                    if(request.getParameter("orderby") != null){
                        if (request.getParameter("orderby").equals("payment")) {
                            ab = pb.getPesanBingkisan("pay_status","","");
                        }else if (request.getParameter("orderby").equals("paket")) {
                            ab = pb.getPesanBingkisan("paket_name","","");
                        }else if (request.getParameter("orderby").equals("order")) {
                            ab = pb.getPesanBingkisan("order_date","","");
                        }else if (request.getParameter("orderby").equals("jumlah")) {
                            ab = pb.getPesanBingkisan("jumlah_paket","","");
                        }else if (request.getParameter("orderby").equals("harga")) {
                            ab = pb.getPesanBingkisan("price","","");
                        }else if (request.getParameter("orderby").equals("item")) {
                            abi = pbi.getPesanBingkisanbyItem("name","","");
                            if (abi.isEmpty()) {
                                //response.sendRedirect("statistic.jsp?empty=1");
                            } else {
                                session.setAttribute("PesanItemBingkisan", abi);
                                session.setAttribute("sortitemb", "1");
                                session.setAttribute("showtable", "1");
                                response.sendRedirect("statistic.jsp");
                            }
                        }else if (request.getParameter("orderby").equals("jitem")) {
                            abi = pbi.getPesanBingkisanbyItem("jumlah","","");
                            if (abi.isEmpty()) {
                                //response.sendRedirect("statistic.jsp?empty=1");
                            } else {
                                session.setAttribute("PesanItemBingkisan", abi);
                                session.setAttribute("sortitemb", "1");
                                session.setAttribute("showtable", "1");
                                response.sendRedirect("statistic.jsp");
                            }
                        }else if (request.getParameter("orderby").equals("hitem")) {
                            abi = pbi.getPesanBingkisanbyItem("harga","","");
                            if (abi.isEmpty()) {
                                //response.sendRedirect("statistic.jsp?empty=1");
                            } else {
                                session.setAttribute("PesanItemBingkisan", abi);
                                session.setAttribute("sortitemb", "1");
                                session.setAttribute("showtable", "1");
                                response.sendRedirect("statistic.jsp");
                            }
                        }
                        if (ab.isEmpty()) {
                                //response.sendRedirect("statistic.jsp?empty=1");
                        } else {
                            session.setAttribute("PesanBingkisan", ab);
                            session.setAttribute("sortb", "1");
                            session.setAttribute("showtable", "2");
                            response.sendRedirect("statistic.jsp");
                        }
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


    public ArrayList<PesanPaket> showPesanP(String s){
        PesanPaket pp = new PesanPaket();
        return pp.getPesanPaket(s,"","");
    }
    
    public ArrayList<PesanPaket> showPesanItemP(String s){
        PesanPaket pp = new PesanPaket();
        return pp.getPesanPaketbyItem(s,"","");
    }

    public ArrayList<PesanBingkisan> showPesanB(String s){
        PesanBingkisan pb = new PesanBingkisan();
        return pb.getPesanBingkisan(s,"","");
    }
    
    public ArrayList<PesanBingkisan> showPesanItemB(String s){
        PesanBingkisan pp = new PesanBingkisan();
        return pp.getPesanBingkisanbyItem(s,"","");
    }
}
