/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iService;

import Entity.Promo;
import Entity.User;

/**
 *
 * @author 21650
 */
public interface IPromo {
    public void ajouterPromo();
    public void DeletePromo(String coupon);
    public Promo Recherche(String coupon);
}
