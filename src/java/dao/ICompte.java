/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import entities.Compte;
import java.util.List;

/**
 *
 * @author darkshadow
 */
public interface ICompte {
    public long persist(Compte compte);
    public Double recherche(String numero);
    public List<Compte>list();
    
}
