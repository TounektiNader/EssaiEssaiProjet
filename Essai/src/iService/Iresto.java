package iService;
import Entity.EntiteResto;
import java.util.List;

public interface Iresto 
{
    public void AddResto(EntiteResto p);
    public void DeleteResto(EntiteResto p);
    public void EditResto(EntiteResto p);
    public EntiteResto FindResto(String nom);
    public List<EntiteResto> FindRestoVille(String ville);
    public List<EntiteResto> ListRestos();
}