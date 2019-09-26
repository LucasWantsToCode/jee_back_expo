/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Compte;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author darkshadow
 */
public class CompteImpl implements ICompte{

    private DB db = new DB();
    private long ok;
    @Override
    public long persist(Compte compte) {
         String sql = "INSERT INTO compte VALUES(?, ?, ?, ?, ?)";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(1, compte.getNumero());
            db.getPstm().setString(2, compte.getNom());
            db.getPstm().setString(3, compte.getPrenom());
            db.getPstm().setString(4, compte.getTel());
            db.getPstm().setDouble(5, compte.getSolde());
            
            ok = db.executeMaj();//Insertion dans la base de données
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return ok;
    }

    @Override
    public Double recherche(String numero) {
        Compte compte = null;
        String sql = "SELECT * FROM compte where numero = ?";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(1, numero);
            ResultSet rs = db.executeSelect();
            if(rs.next()) {
                compte = new Compte();
                compte.setNumero(rs.getString(1));
                compte.setNom(rs.getString(2));
                compte.setPrenom(rs.getString(3));
                compte.setTel(rs.getString(4));
                compte.setSolde(rs.getDouble(5));  
            }
            rs.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return compte.getSolde();
    }

    @Override
    public List<Compte> list() {
        List<Compte> comptes = new ArrayList<Compte>();
        String sql = "SELECT * FROM compte";
        try{
            db.initPrepar(sql);
            ResultSet rs = db.executeSelect();
            while(rs.next()) {
                Compte compte = new Compte();
                compte.setNumero(rs.getString(1));
                compte.setNom(rs.getString(2));
                compte.setPrenom(rs.getString(3));
               compte.setTel(rs.getString(4));
                compte.setSolde(rs.getDouble(5));
                
                comptes.add(compte);
            }
            rs.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return comptes;
    }
    
}
