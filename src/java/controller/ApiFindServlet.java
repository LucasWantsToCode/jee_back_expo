/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CompteImpl;
import dao.ICompte;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.Compte;
import java.io.StringWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author darkshadow
 */
@WebServlet(name = "ApiFindServlet", urlPatterns = {"/ApiFind"})
public class ApiFindServlet extends HttpServlet {

    ICompte compte_dao = new CompteImpl();
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
            out.println("<title>Servlet ApiFindServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ApiFindServlet at " + request.getContextPath() + "</h1>");
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
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         Double solde = null;
         String result = null;
         
         if(request.getParameter("numero") != null){
             
             String numero = request.getParameter("numero").toString();
             solde = compte_dao.recherche(numero);
             if(solde == null){
                 result = "NOK";
                 
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("response", result);
                

                jsonArray.add(jsonObject);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Methods", "GET, POST");

                StringWriter out = new StringWriter();
                jsonArray.writeJSONString(out);
                String jsonText = out.toString();

                response.getWriter().print(jsonText);
                
             }else{
                result = "OK";
                
                 
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("response", result);
                jsonObject.put("compte", solde);

                jsonArray.add(jsonObject);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Methods", "GET, POST");

                StringWriter out = new StringWriter();
                jsonArray.writeJSONString(out);
                String jsonText = out.toString();

                response.getWriter().print(jsonText);
             }
         }
         
        
        
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
