/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DateStroge.MyConnection;
import Entity.Promo;
import iService.IPromo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 21650
 */
public class PromoService implements IPromo {
    Connection connexion;
    public String generate(int length)
{
	    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; // Tu supprimes les lettres dont tu ne veux pas
	    String pass = "";
	    for(int x=0;x<length;x++)
	    {
	       int i = (int)Math.floor(Math.random() * 62); // Si tu supprimes des lettres tu diminues ce nb
	       pass += chars.charAt(i);
	    }
	    System.out.println(pass);
	    return pass;
}
    public PromoService() {
        connexion = MyConnection.getInstance().getConnexion();
    }

    @Override
    public void ajouterPromo() {
        try {
            Promo x=new Promo();
            Calendar now = Calendar.getInstance();
    now.add(Calendar.MINUTE, 30);
           
    Date teenMinutesFromNow = now.getTime();
            x.setExpiraton(teenMinutesFromNow);
            x.setCoupon(generate(4));
            x.setPromotion((int) (Math.random() * ( 50 - 1 )));
            System.out.println(teenMinutesFromNow.toString());
            SimpleDateFormat dt1 = new SimpleDateFormat("yyyyy/MM/dd kk:mm:ss");
            System.out.println(dt1.format(teenMinutesFromNow));
            String query="insert into promo (coupon,promotion,expiration)values('"+x.getCoupon()+"','"+x.getPromotion()+"','"+dt1.format(teenMinutesFromNow)+"')";
            Statement stm = connexion.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            
        }
    }

    @Override
    public void DeletePromo(String coupon) {
        try {
            String query = "DELETE FROM promo WHERE coupon='" + coupon + "'";
            Statement stm = connexion.createStatement();
            stm.executeUpdate(query);
           
        } catch (SQLException ex) {
            
        }
    }

    @Override
    public Promo Recherche(String coupon) {
        Promo x=new Promo("",0);
        try {
             String query = "Select * FROM promo WHERE coupon='" + coupon + "'";
            Statement stm = connexion.createStatement();
            ResultSet resultat = stm.executeQuery(query);
            while (resultat.next()) {
                x.setCoupon( resultat.getString("coupon"));
                x.setPromotion(resultat.getInt("promotion"));
                x.setExpiraton(resultat.getDate("expiration"));
           
        }} catch (SQLException ex) {
            
        }
        return x;
    }

    @Override
    public List<Promo> Afficher() {
        List<Promo> list = new ArrayList<Promo>();
        try {
            
            String sql = "SELECT coupon,promotion,expiration FROM promo order by expiration desc;";
            Statement stm = connexion.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Promo promo= new Promo();
                
                promo.setCoupon(rs.getString("coupon"));
                promo.setPromotion(rs.getInt("promotion"));
                promo.setExpiraton(rs.getDate("expiration"));
                
                list.add(promo);
                
            }   } catch (SQLException ex) {
            Logger.getLogger(PromoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
