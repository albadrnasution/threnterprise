/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;
import model.DateFormater;
import model.ItemBingkisan;
import model.ItemJalan;
import model.PaketBingkisan;
import model.PaketJalan;
import model.PesanBingkisan;
import model.PesanKirimBingkisan;
import model.PesanPaket;

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
            throws ServletException, IOException, SQLException, ParseException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("mobile") != null) {
                if (request.getParameter("filter") != null) {
                    if (request.getParameter("request").equals("bingkisan")) {

                        String mark = request.getParameter("mark");
                        if (mark.equals("L")) {
                            mark = "<";
                        } else if (mark.equals("G")) {
                            mark = ">";
                        } else {
                            mark = "=";
                        }
                        String price = request.getParameter("price");
                        String desc = request.getParameter("desc");
                        String name = request.getParameter("name");
                        PaketBingkisan pb = new PaketBingkisan();
                        out.print(pb.getFilterAsJSON(mark, price, name, desc));

                    } else if (request.getParameter("request").equals("jalan")) {
                        String mark = request.getParameter("mark");
                        if (mark.equals("L")) {
                            mark = "<";
                        } else if (mark.equals("G")) {
                            mark = ">";
                        } else {
                            mark = "=";
                        }
                        String price = request.getParameter("price");
                        String name = request.getParameter("name");
                        String origin = "";
                        String dest = "";
                        if (request.getParameter("origin") != null) {
                            origin = request.getParameter("origin");
                        }
                        if (request.getParameter("dest") != null) {
                            dest = request.getParameter("dest");
                        }
                        PaketJalan pb = new PaketJalan();
                        out.print(pb.getSearchInJSON(mark, price, name, origin, dest));
                    } else {
                        out.println("Invalid request.");
                    }
                } else {
                    if (request.getParameter("request").equals("jalan")) {
                        if (request.getParameter("act") != null) {
                            String user = request.getParameter("un");
                            String pass = request.getParameter("pw");
                            String idp = request.getParameter("idp");
                            String n = request.getParameter("n");
                            Customer cu = new Customer();
                            int idc = 0;
                            if (!user.equals("") && !pass.equals("")) {
                                Customer cust = new Customer();
                                ArrayList<Customer> listcust = cust.getallCustomer();
                                boolean found = false;
                                for (int i = 0; i < listcust.size() && !found; ++i) {
                                    if (listcust.get(i).getPassword().equals(pass) && listcust.get(i).getEmail().equals(user)) {
                                        found = true;
                                        idc = listcust.get(i).getIdc();
                                        cu = listcust.get(i);
                                    }
                                }
                                if (idc != 0) {
                                    PesanPaket paket = new PesanPaket();
                                    PaketJalan pj = new PaketJalan().getPaketbyid(idp);
                                    paket.setIdc(cu.getIdc());
                                    paket.setIdp(pj.getIdp());
                                    paket.setJumlah_paket(Integer.parseInt(n));
                                    String res = paket.addPesanPaket();
                                    System.out.println("9999999");
                                    out.print("1");
                                } else {
                                    System.out.println("adadadad");
                                    out.print("0");
                                }
                            } else {
                                System.out.println("856789876");
                                out.print("0");
                            }
                        } else {
                            PaketJalan pj = new PaketJalan();
                            out.print(pj.getPaket_asJSON());
                        }
                    } else if (request.getParameter("request").equals("bingkisan")) {
                        if (request.getParameter("act") != null) {
                            String user = request.getParameter("un");
                            String pass = request.getParameter("pw");
                            String idp = request.getParameter("idp");
                            String n = request.getParameter("n");
                            String dest = request.getParameter("dest");
                            String due_date = request.getParameter("day") + "/" + request.getParameter("month") + "/" + request.getParameter("year");
                            Customer cu = new Customer();
                            int idc = 0;
                            if (!user.equals("") && !pass.equals("")) {
                                Customer cust = new Customer();
                                ArrayList<Customer> listcust = cust.getallCustomer();
                                boolean found = false;
                                for (int i = 0; i < listcust.size() && !found; ++i) {
                                    if (listcust.get(i).getPassword().equals(pass) && listcust.get(i).getEmail().equals(user)) {
                                        found = true;
                                        idc = listcust.get(i).getIdc();
                                        cu = listcust.get(i);
                                    }
                                }
                                if (idc != 0) {
                                    PesanBingkisan bingkisan = new PesanBingkisan();
                                    PesanKirimBingkisan kirim = new PesanKirimBingkisan();
                                    PaketBingkisan pbi = new PaketBingkisan().getPaketbyid(idp);
                                    bingkisan.setIdp(pbi.getIdp());
                                    bingkisan.setIdc(cu.getIdc());
                                    bingkisan.setJumlah_paket(Integer.parseInt(n));
                                    bingkisan.setDue_date2(DateFormater.formatDateToDBFormat(due_date));
                                    String res = bingkisan.addPesanBingkisan();
                                    kirim.setIdc(cu.getIdc());
                                    kirim.setIdp(pbi.getIdp());
                                    kirim.setBanyak_paket(Integer.parseInt(n));
                                    kirim.setAlamat(dest);
                                    kirim.setHarga(bingkisan.getJumlah_paket() * pbi.getPrice());
                                    res = kirim.addPesanKirimBingkisan();
                                    out.print("1");
                                } else {
                                    System.out.println("masukkkkkkkkkk");
                                    out.print("0");
                                }
                            } else {
                                System.out.println("kkkkkkkeluar");
                                out.print("0");
                            }
                        } else {
                            PaketBingkisan pb = new PaketBingkisan();
                            out.print(pb.getPaket_asJSON());
                        }
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
            try {
                processRequest(request, response);
            } catch (ParseException ex) {
                Logger.getLogger(PaketMobile.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            try {
                processRequest(request, response);
            } catch (ParseException ex) {
                Logger.getLogger(PaketMobile.class.getName()).log(Level.SEVERE, null, ex);
            }
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
