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
import javax.servlet.http.HttpSession;
import model.Customer;
import model.DateFormater;
import model.PaketBingkisan;
import model.PaketJalan;
import model.PesanBingkisan;
import model.PesanKirimBingkisan;
import model.PesanPaket;
import model.Staff;

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
            throws ServletException, IOException, ParseException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Customer cu = new Customer();
            Staff st = new Staff();
            HttpSession session = request.getSession();
            if (session.getAttribute("user") == null) {
                out.write("\n");
                out.write("                    <div class=\"login-bucket bucket\" style=\"width:350px;height:130px\">\n");
                out.write("                        <form action=\"../login\" method=\"POST\" style=\"margin-top: 20px;\">\n");
                out.write("                            <table border=\"0\"  style=\"width: 80%;margin-left:10%\">\n");
                out.write("                                <tbody>\n");
                out.write("                                    <tr>\n");
                out.write("                                        <td colspan=\"2\">Have an account?</td>\n");
                out.write("                                    </tr>\n");
                out.write("                                    <tr>\n");
                out.write("                                        <td>Email </td>\n");
                out.write("                                        <td> <input type=\"text\" name=\"email\" value=\"\"  style=\"width: 100%;height: 24px\"/> </td>\n");
                out.write("                                    </tr>\n");
                out.write("                                    <tr>\n");
                out.write("                                        <td>Password </td>\n");
                out.write("                                        <td> <input type=\"password\" name=\"password\" value=\"\"  style=\"width: 100%;height: 24px\"/> </td>\n");
                out.write("                                    </tr>\n");
                out.write("                                    <tr><td></td>\n");
                out.write("                                        <td  style=\"text-align: right;height: auto\"> <input type=\"submit\" class=\"thrbutton\"value=\"Login\" name=\"login\" style=\"width: 120px; height: 40px\" /> </td>\n");
                out.write("                                    </tr>\n");
                out.write("                                    ");
                out.write("\n");
                out.write("\n");
                out.write("                                </tbody>\n");
                out.write("                            </table>\n");
                out.write("                        </form>\n");
                out.write("                    </div>\n");
                out.write("                    <div class=\"register-bucket bucket\">\n");
                out.write("                        <div class=\"paket-button\" title=\"Don't have an account?\">\n");
                out.write("                            <a href=\"");
                out.print(request.getContextPath());
                out.write("/Registerasi/registerasi.jsp\">Register</a>\n");
                out.write("                        </div>  \n");
                out.write("                    </div> \n");
                out.write("                    ");
            } else {
                if (session.getAttribute("jenisUser").equals("0")) {
                    cu = (Customer) session.getAttribute("user");
                } else
                {
                    st = (Staff) session.getAttribute("user");
                }
                if (request.getParameter("menu").equals("buybingkisan")) {
                    PaketBingkisan pbi = new PaketBingkisan();
                    PaketBingkisan pb = pbi.getPaketbyid(request.getParameter("idp"));
                    session.setAttribute("bingkisan", pb);
                    response.sendRedirect("paketBingkisan/pesanPaketBingkisan.jsp");
                } else if (request.getParameter("menu").equals("prosesbuybingkisan")) {
                    PesanBingkisan bingkisan = new PesanBingkisan();
                    PesanKirimBingkisan kirim = new PesanKirimBingkisan();
                    PaketBingkisan pbi = (PaketBingkisan) session.getAttribute("bingkisan");
                    bingkisan.setIdp(pbi.getIdp());
                    bingkisan.setIdc(cu.getIdc());
                    bingkisan.setJumlah_paket(Integer.parseInt(request.getParameter("jumlah")));
                    bingkisan.setDue_date2(DateFormater.formatDateToDBFormat(request.getParameter("due_date")));
                    String res = bingkisan.addPesanBingkisan();
                    kirim.setIdc(cu.getIdc());
                    kirim.setIdp(pbi.getIdp());
                    kirim.setBanyak_paket(Integer.parseInt(request.getParameter("jumlah")));
                    kirim.setAlamat(request.getParameter("alamat"));
                    kirim.setHarga(bingkisan.getJumlah_paket() * pbi.getPrice());
                    res = kirim.addPesanKirimBingkisan();
                    String redirectURL = "PesanController?menu=historipemesanan";
                    response.sendRedirect(redirectURL);
                } else if (request.getParameter("menu").equals("buyperjalanan")) {
                    PaketJalan pbi = new PaketJalan();
                    PaketJalan pb = pbi.getPaketbyid(request.getParameter("idp"));
                    session.setAttribute("perjalanan", pb);
                    response.sendRedirect("paketPerjalanan/pesanPaketPerjalanan.jsp");
                } else if (request.getParameter("menu").equals("prosesbuyperjalanan")) {
                    PesanPaket paket = new PesanPaket();
                    PaketJalan pj = (PaketJalan) session.getAttribute("perjalanan");
                    paket.setIdc(cu.getIdc());
                    paket.setIdp(pj.getIdp());
                    paket.setJumlah_paket(Integer.parseInt(request.getParameter("jumlah")));
                    String res = paket.addPesanPaket();
                    String redirectURL = "PesanController?menu=historipemesanan";
                    response.sendRedirect(redirectURL);
                } else if (request.getParameter("menu").equals("historipemesanan")) {
                    PesanPaket paket = new PesanPaket();
                    PesanBingkisan bingkisan = new PesanBingkisan();
                    PesanKirimBingkisan kirim = new PesanKirimBingkisan();
                    ArrayList<PesanPaket> pp = new ArrayList<PesanPaket>();
                    ArrayList<PesanBingkisan> pb = new ArrayList<PesanBingkisan>();
                    ArrayList<PesanKirimBingkisan> pk = new ArrayList<PesanKirimBingkisan>();
                    pp = paket.getPesanPaketbyIdc(Integer.toString(cu.getIdc()));
                    pb = bingkisan.getPesanBingkisanbyIdc(Integer.toString(cu.getIdc()));
                    pk = kirim.getPesanKirimBingkisanbyIdc(Integer.toString(cu.getIdc()));
                    session.setAttribute("getpaket", pp);
                    session.setAttribute("getbingkisan", pb);
                    session.setAttribute("getkirim", pk);
                    response.sendRedirect("paketBingkisan/historiPesan.jsp");
                }
                else if (request.getParameter("menu").equals("konfirmasipembayaran") && st != null)
                {
                    PesanPaket paket = new PesanPaket();
                    PesanBingkisan bingkisan = new PesanBingkisan();
                    PesanKirimBingkisan kirim = new PesanKirimBingkisan();
                    ArrayList<PesanPaket> pp = new ArrayList<PesanPaket>();
                    ArrayList<PesanBingkisan> pb = new ArrayList<PesanBingkisan>();
                    ArrayList<PesanKirimBingkisan> pk = new ArrayList<PesanKirimBingkisan>();
                    pp = paket.getAllPesanPaket();
                    pb = bingkisan.getAllPesanBingkisan();
                    pk = kirim.getAllPesanKirimBingkisan();
                    session.setAttribute("getpaket", pp);
                    session.setAttribute("getbingkisan", pb);
                    session.setAttribute("getkirim", pk);
                    response.sendRedirect("paketBingkisan/konfirmasiPembayaran.jsp");
                }
                else if (request.getParameter("menu").equals("konfirmpesan"))
                {
                    if(request.getParameter("jenispaket").equals("bingkisan"))
                    {
                        PesanBingkisan bingkisan = new PesanBingkisan();
                        bingkisan.confirmPay(request.getParameter("ido"),DateFormater.formatDateToDBFormat(request.getParameter("pay_date")),request.getParameter("no_rekening"),request.getParameter("uang_pembayaran"));
                        response.sendRedirect("PesanController?menu=historipemesanan");
                    }
                    else if(request.getParameter("jenispaket").equals("perjalanan"))
                    {
                        PesanPaket paket = new PesanPaket();
                        paket.confirmPay(request.getParameter("ido"),DateFormater.formatDateToDBFormat(request.getParameter("pay_date")),request.getParameter("no_rekening"));
                        response.sendRedirect("PesanController?menu=historipemesanan");
                    }
                }
                else if(request.getParameter("menu").equals("konfirmbayar"))
                {
                    PesanPaket paket = new PesanPaket();
                    PesanBingkisan bingkisan = new PesanBingkisan();
                    if(request.getParameter("paket").equals("perjalanan"))
                        paket.changePayStatus("konfirm", request.getParameter("ido"));
                    else
                    {
                        bingkisan.changePayStatus("konfirm", request.getParameter("ido"));
                        bingkisan.sendwebservice(request.getParameter("ido"));
                    }
                    response.sendRedirect(request.getContextPath()+"/PesanController?menu=konfirmasipembayaran");
                }
                else if(request.getParameter("menu").equals("cancelbayar"))
                {
                    PesanPaket paket = new PesanPaket();
                    PesanBingkisan bingkisan = new PesanBingkisan();
                    if(request.getParameter("paket").equals("perjalanan"))
                        paket.changePayStatus("cancel", request.getParameter("ido"));
                    else 
                        bingkisan.changePayStatus("cancel", request.getParameter("ido"));
                    response.sendRedirect(request.getContextPath()+"/PesanController?menu=konfirmasipembayaran");
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
        try {
            try {
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(PesanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParseException ex) {
            Logger.getLogger(PesanController.class.getName()).log(Level.SEVERE, null, ex);
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
            } catch (SQLException ex) {
                Logger.getLogger(PesanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParseException ex) {
            Logger.getLogger(PesanController.class.getName()).log(Level.SEVERE, null, ex);
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
