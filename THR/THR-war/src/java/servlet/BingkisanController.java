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
            String harga = request.getParameter("harga");
            String name = request.getParameter("name");
            String operator = request.getParameter("operator");
            String desc = request.getParameter("desc");
            HttpSession session = request.getSession();
            if(harga.equals("") || operator.equals("")){
                response.sendRedirect("paketBingkisan/daftarPaketBingkisan.jsp");            
            }else{
                ArrayList<PaketBingkisan> p; 
                PaketBingkisan pj = new PaketBingkisan();
                p = pj.getSearchResult(harga, operator, name, desc);
                if(p.isEmpty()){
                    out.println("Search Return no Result!");
                }else{
                    session.setAttribute("PaketBingkisan", p);
                    session.setAttribute("filter", "1");
                    response.sendRedirect("paketBingkisan/daftarPaketBingkisan.jsp");
//                    
//                    out.println("<html>");
//                    out.println("<head>");
//                    out.println("<title>Search Result</title>");  
//                    out.println("</head>");
//                    out.println("<body>");
//                    out.println("<h1>Daftar Paket Bingkisan</h1>");
//                    
//                    for(int i=0; i<p.size(); ++i){
//                        out.println("<a href=paketBingkisan/detailPaketBingkisan.jsp?id="+p.get(i).getIdp()+">"+p.get(i).getPaket_name()+"</a>");
//                        out.println("<br/>");
//                        out.println(p.get(i).getDescription());
//                        out.println("<br/>");
//                        out.println(p.get(i).getPrice());
//                        out.println("<br/>");
//                        out.println("<br/>");
//                    }                    
//                    out.println("</body>");
//                    out.println("</html>");
                    
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

    public ArrayList<PaketBingkisan> showPaket(){
        PaketBingkisan p = new PaketBingkisan();
        return p.getPaket();
    }
    public ArrayList<ItemBingkisan> showDetail(String id){
        ItemBingkisan ij = new ItemBingkisan();
        return ij.getItem(id);
    }
    
}
