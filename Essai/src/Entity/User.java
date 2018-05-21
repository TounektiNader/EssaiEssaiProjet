/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;

/**
 *
 * @author Nader
 */
public class User implements Serializable {
     private String username;
    private String nom;
    private String prenom;
    private String mdp;
    private String role;
    private String mail;
    private String status; 
    private int jeton ; 
    private String nationalite;

    public void setNum(String num) {
        this.num = num;
    }
    private String num;

    public User(String username, String nom, String prenom, String mdp, String role, String mail, String status, int jeton, String nationalite, String num) {
        super();
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.role = role;
        this.mail = mail;
        this.status = status;
        this.jeton = jeton;
        this.nationalite = nationalite;
        this.num = num;
    }

    public String getNum() {
        
        return num;
    }
    
    public String getNationalite() {
        return nationalite;
    }
    

    public User(){ super();}

    public User(String username, String nom, String prenom, String mdp, String role, String mail, String status, int jeton,String nationalite) {
        super();       
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.role = role;
        this.mail = mail;
        this.status = status;
        this.jeton = jeton;
        this.nationalite=nationalite;
    }
    public User(String username)
    {
         super();
        this.username=username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public int getJeton() {
        return jeton;
    }

    public void setJeton(int jeton) {
        this.jeton = jeton;
    }
    
    
}
