package Entity;

public class EntiteResto 
{
    int Id;
    String Nom;
    String Details;
    String Position;
    String Photo;
    EntiteVille RestoVille;

    public EntiteResto(String Nom, String Details, String Position, String Photo, EntiteVille RestoVille) {
        this.Nom = Nom;
        this.Details = Details;
        this.Position = Position;
        this.Photo = Photo;
        this.RestoVille = RestoVille;
    }

    public EntiteResto() 
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

    public EntiteVille getRestoVille() {
        return RestoVille;
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

    public void setRestoVille(EntiteVille RestoVille) {
        this.RestoVille = RestoVille;
    }

    @Override
    public String toString() {
        return "EntiteResto{" + "Id=" + Id + ", Nom=" + Nom + ", Details=" + Details + ", Position=" + Position + ", Photo=" + Photo + ", RestoVille=" + RestoVille + '}';
    }

}
