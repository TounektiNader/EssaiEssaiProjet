package iService;
import Entity.EntiteHotel;
import java.util.List;

public interface Ihotel 
{
    public void AddHotel(EntiteHotel p);
    public void DeleteHotel(EntiteHotel p);
    public void EditHotel(EntiteHotel p);
    public EntiteHotel FindHotel(String nom);
    public List<EntiteHotel> FindHotelVille(String ville);
    public List<EntiteHotel> ListHotels();
    public int Nmbr();

}