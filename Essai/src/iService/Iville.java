package iService;
import Entity.EntiteVille;
import java.util.List;

public interface Iville 
{
    public void AddVille(EntiteVille p);
    public void DeleteVille(EntiteVille p);
    public void EditVille(EntiteVille p);
    public EntiteVille FindVille(String nom);
    public List<EntiteVille> listVilles();
    public List<String> ReturnNames();
    public int ReturnId(String nom);
    public int Nmbr();
}