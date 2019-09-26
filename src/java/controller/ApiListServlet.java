/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CompteImpl;
import dao.ICompte;
import entities.Compte;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author darkshadow
 */
@WebServlet(name = "ApiListServlet", urlPatterns = {"/ApiList"})
public class ApiListServlet extends HttpServlet {

    ICompte compte_dao = new CompteImpl();
    List<Compte> list= null;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ApiListServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ApiListServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String result = null;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         list = compte_dao.list();
            JSONArray jsonArray = new JSONArray();
                
               
                for(Compte c: list){
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("res", "OK");
                    jsonObject.put("numero", c.getNumero());
                    jsonObject.put("nom", c.getNom());
                    jsonObject.put("prenom", c.getPrenom());
                    jsonObject.put("tel", c.getTel());
                    jsonObject.put("solde", c.getSolde());
                    jsonObject.put("solde", c.getSolde());

                    jsonArray.add(jsonObject);   
               }

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Methods", "GET, POST");

                StringWriter out = new StringWriter();
                jsonArray.writeJSONString(out);
                String jsonText = out.toString();

                response.getWriter().print(jsonText);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
