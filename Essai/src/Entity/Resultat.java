package Entity;

public class Resultat {

    private int idResultat;  
    private int butHome;
    private int butAway;
    private Partie partie;
      
    private String nomEquipeHome ;
    private String nomEquipeAway;
    private int idPartie;
   
    public Resultat() {
    }

    public Resultat(int idResultat, int butHome, int butAway, Partie partie) {
        this.idResultat = idResultat;
        this.butHome = butHome;
        this.butAway = butAway;
        this.partie = partie;
    }

    public Resultat(int butHome, int butAway, Partie partie) {
        this.butHome = butHome;
        this.butAway = butAway;
        this.partie = partie;
    }

    public int getIdResultat() {
        return idResultat;
    }

    public void setIdResultat(int idResultat) {
        this.idResultat = idResultat;
    }

    public int getButHome() {
        return butHome;
    }

    public void setButHome(int butHome) {
        this.butHome = butHome;
    }

    public int getButAway() {
        return butAway;
    }

    public void setButAway(int butAway) {
        this.butAway = butAway;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }

    public String getNomEquipeHome() {
        return this.partie.getHome().getNomEquipe();
    }

    public void setNomEquipeHome() {
        this.nomEquipeHome =this.partie.getHome().getNomEquipe();
    }

    public String getNomEquipeAway() {
        return this.partie.getAway().getNomEquipe();
    }

    public void setNomEquipeAway() {
        this.nomEquipeAway = this.partie.getAway().getNomEquipe();
    }

    public int getIdPartie() {
        return this.partie.getIdMatch();
    }

    public void setIdPartie() {
        this.idPartie = this.partie.getIdMatch();
    }

   
    
    
    
    
}
