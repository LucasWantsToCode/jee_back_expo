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
@WebServlet(name = "ApiServlet", urlPatterns = {"/Api"})
public class ApiServlet extends HttpServlet {

    ICompte compte_dao = new CompteImpl();
        long ok =0;
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
            out.println("<title>Servlet ApiServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ApiServlet at " + request.getContextPath() + "</h1>");
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
        String result = null;
        
        
        if(request.getParameter("numero") != null && request.getParameter("nomClient") != null && request.getParameter("prenomClient") 
                != null && request.getParameter("telClient") != null && request.getParameter("solde") != null) {
            
            String numero = request.getParameter("numero").toString();
            String nom = request.getParameter("nomClient").toString();
            String prenom = request.getParameter("prenomClient").toString();
            String tel = request.getParameter("telClient").toString();
            Double solde = Double.parseDouble(request.getParameter("solde").toString());
            
            Compte compte = new Compte();
            compte.setNumero(numero);
            compte.setNom(nom);
            compte.setPrenom(prenom);
            compte.setTel(tel);
            compte.setSolde(solde);
            
            ok = compte_dao.persist(compte);
            
            if(ok == 0){
                result = "NOK";
            }else{
                result = "OK";
            }
        }else{
            result = "NOK";
        }
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
