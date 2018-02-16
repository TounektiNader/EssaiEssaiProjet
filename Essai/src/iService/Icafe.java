package iService;
import Entity.EntiteCafe;
import java.util.List;

public interface Icafe 
{
    public void AddCafe(EntiteCafe p);
    public void DeleteCafe(EntiteCafe p);
    public void EditCafe(EntiteCafe p);
    public EntiteCafe FindCafe(String nom);
    public List<EntiteCafe> FindCafeVille(String ville);
    public List<EntiteCafe> ListCafes();
}