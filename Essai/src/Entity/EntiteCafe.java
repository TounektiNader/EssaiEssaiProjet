package Entity;

public class EntiteCafe 
{
    int Id;
    String Nom;
    String Details;
    String Position;
    String Photo;
    EntiteVille CafeVille;

    public EntiteCafe(String Nom, String Details, String Position, String Photo, EntiteVille CafeVille) {
        this.Nom = Nom;
        this.Details = Details;
        this.Position = Position;
        this.Photo = Photo;
        this.CafeVille = CafeVille;
    }

    public EntiteCafe() 
    {
        
    }

    public int getId() {
        return Id;
    }

    public String getNom() {
        return Nom;
    }

    public String getDetails() {
        return Details;
    }

    public String getPosition() {
        return Position;
    }

    public String getPhoto() {
        return Photo;
    }

    public EntiteVille getCafeVille() {
        return CafeVille;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setDetails(String Details) {
        this.Details = Details;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

    public void setCafeVille(EntiteVille CafeVille) {
        this.CafeVille = CafeVille;
    }

    @Override
    public String toString() {
        return "EntiteCafe{" + "Id=" + Id + ", Nom=" + Nom + ", Details=" + Details + ", Position=" + Position + ", Photo=" + Photo + ", CafeVille=" + CafeVille + '}';
    }

}