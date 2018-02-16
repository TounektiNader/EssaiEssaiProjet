package iService;

import Entity.EntiteStade;
import Entity.Equipe;
import Entity.Partie;
import Entity.Stade;
import java.sql.Date;
import javafx.collections.ObservableList;

public interface iPartie {

 public String getNomEquipe(int id);
  public int getidEquipe(String nom) ;
  public Equipe getEquipe(int idEquipe);
  public EntiteStade getStade(int idStade);
   public ObservableList<Partie> getPartie() ;
     public void ajoutPartie(String groupe, String date, String heure, String tour, String etiquette, int idStade, int home, int away);
     public void supprimer(int idPartie);
     public int geIdHome(int idPartie) ;
     public int geIdAway(int idPartie) ;
     public ObservableList<Partie> partiegroupe(String groupe);
     public ObservableList<Partie> partieEquipe(int id);
     public Partie DetailsPartie(int idPartie) ;
     public void updateEtatPartie(int idPartie);
     public int  getIdStade(String nomStade);
  
 }
