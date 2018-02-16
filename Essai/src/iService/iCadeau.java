package iService;

import Entity.Cadeau;
import java.util.List;
import javafx.collections.ObservableList;

public interface iCadeau {

  public ObservableList<Cadeau> listCadeau() ;

    public List<Cadeau> listCadeau(String categorie);

    public List<Cadeau> listCadeau(int jeton);

    public void ajoutCadeau(String categorie, String type, int jeton, String image);

    public void supprimer(int idCadeau);

    public void modifierCadeau(int idCadeau, String categorie, String type, int jeton, String image);

}
