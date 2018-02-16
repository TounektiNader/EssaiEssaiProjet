package iService;

import Entity.EntiteStade;
import java.util.List;

public interface Istade 
{
    public void AddSatde(EntiteStade p);
    public void DeleteStade(EntiteStade p);
    public void EditStade(EntiteStade p);
    public EntiteStade FindStade(String nom);
    public List<EntiteStade> FindStadeVille(String ville);
    public List<EntiteStade> ListStades();
}
