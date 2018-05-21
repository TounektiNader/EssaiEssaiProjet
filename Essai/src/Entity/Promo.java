/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author 21650
 */
public class Promo {
     private int idpromo;

    private String Coupon;
    private int promotion;
    private Date expiraton;

    public Promo() {
    }

    public Promo(int idpromo, String Coupon, int promotion, Date expiraton) {
        this.idpromo = idpromo;
        this.Coupon = Coupon;
        this.promotion = promotion;
        this.expiraton = expiraton;
    }

    public Promo(String Coupon, int promotion, Date expiraton) {
        this.Coupon = Coupon;
        this.promotion = promotion;
        this.expiraton = expiraton;
    }

    public Promo(String Coupon, int promotion) {
        this.Coupon = Coupon;
        this.promotion = promotion;
    }

    public int getIdpromo() {
        return idpromo;
    }

    public void setIdpromo(int idpromo) {
        this.idpromo = idpromo;
    }

    public String getCoupon() {
        return Coupon;
    }

    public void setCoupon(String Coupon) {
        this.Coupon = Coupon;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public Date getExpiraton() {
        return expiraton;
    }

    public void setExpiraton(Date expiraton) {
        this.expiraton = expiraton;
    }
    
}
