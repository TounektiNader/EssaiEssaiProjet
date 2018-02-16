package Entity;

public class EntiteHotel 
{
    int Id;
    String Nom;
    String Details;
    String Position;
    String Photo;
    EntiteVille HotelVille;

    public EntiteHotel(String Nom, String Details, String Position, String Photo, EntiteVille HotelVille) {
        this.Nom = Nom;
        this.Details = Details;
        this.Position = Position;
        this.Photo = Photo;
        this.HotelVille = HotelVille;
    }

    public EntiteHotel() 
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

    public EntiteVille getHotelVille() {
        return HotelVille;
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

    public void setHotelVille(EntiteVille HotelVille) {
        this.HotelVille = HotelVille;
    }

    @Override
    public String toString() {
        return "EntiteHotel{" + "Id=" + Id + ", Nom=" + Nom + ", Details=" + Details + ", Position=" + Position + ", Photo=" + Photo + ", HotelVille=" + HotelVille + '}';
    }
}