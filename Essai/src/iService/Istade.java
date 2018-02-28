package iService;

import Entity.EntiteStade;
import java.util.List;

public interface Istade 
{
    public void AddSatde(EntiteStade p);
    public void DeleteStade(EntiteStade p);
    public void EditStade(EntiteStade p);
    public EntiteStade FindStade(String nom);
    public String FindStadeVille(int id);
    public List<EntiteStade> ListStades();
    public int Nmbr();

}
