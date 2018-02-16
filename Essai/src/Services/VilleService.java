package Services;

import DateStroge.MyConnection;
import Entity.EntiteVille;
import iService.Iville;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VilleService implements Iville
{

    Connection connexion;

    public VilleService() throws SQLException 
    {
        connexion = MyConnection.getInstance().getConnexion();
    }

    @Override
    public void AddVille(EntiteVille p) 
    {
        try 
        {
            String insert = "INSERT INTO villes (nomville, fondationville, populationville, timezone, photoville, equipelocale, logoville, logoequipe, coordonnees)"
                    + " VALUES (?,?,?,?,?,?,?,?,?);";
            PreparedStatement st1 = connexion.prepareStatement(insert);
            st1.setString(1, p.getNom());
            st1.setString(2, p.getFondation());
            st1.setString(3, p.getPopulation());
            st1.setString(4, p.getTimezone());
            st1.setString(5, p.getPhotoville());
            st1.setString(6, p.getEquipelocale());
            st1.setString(7, p.getLogoville());
            st1.setString(8, p.getLogoequipe());
            st1.setString(9, p.getCoordonnees());
            st1.executeUpdate();
            System.out.println("Ville "+p.getNom()+" ajoutée !!!");
            
        } 
        catch (Exception e) 
        {
            System.err.println("SQLException: "+e.getMessage());
            System.err.println("Echec d'insertion de la ville " +p.getNom());
        }
    }

    @Override
    public void DeleteVille(EntiteVille p) 
    {
        try 
        {
        String delete = "DELETE FROM villes WHERE nomville= ?";
        PreparedStatement st2 = connexion.prepareStatement(delete);
        st2.setString(1,p.getNom());
        st2.executeUpdate();
        System.out.println("Ville "+p.getNom()+" supprimée !!!");
        
        }
        catch (Exception e)
        {
            System.err.println("Echec de la suppression de la ville "+p.getNom());
        }
    }

    @Override
    public void EditVille(EntiteVille p) 
    {
        try
        {
        String update = "UPDATE villes SET coordonnees = ? WHERE nomville = ?";
        PreparedStatement statement2 = connexion.prepareStatement(update);
        statement2.setString(1, "Hamza");
        statement2.setString(2, p.getNom());
        statement2.executeUpdate();
        System.out.println("Ville "+p.getNom()+" modifiée !!!");
        
        }
        catch (Exception e)
                {
                    System.err.println("Ville "+p.getNom()+" non modifiée");
                }
    }

    @Override
    public EntiteVille FindVille(String nom) 
    {
        EntiteVille v = new EntiteVille();
        try
        {
        String select = "SELECT * FROM villes WHERE nomville = '"+nom+"'";
        Statement statement1 = connexion.createStatement();
        ResultSet result = statement1.executeQuery(select);
       
        while (result.next()) 
        {
            v.setIdville(result.getInt("idville"));
            v.setNom(result.getString("nomville"));
            v.setFondation(result.getString("fondationville"));
            v.setPopulation(result.getString("populationville"));
            v.setTimezone(result.getString("timezone"));
            v.setEquipelocale(result.getString("equipelocale"));
            v.setCoordonnees(result.getString("coordonnees"));
            v.setLogoville(result.getString("logoville"));
            v.setLogoequipe(result.getString("logoequipe"));
            v.setPhotoville(result.getString("photoville"));
            }
        }
        catch (SQLException e)
                {
                    System.err.println("SQLException: "+e.getMessage());
                    System.err.println("SQLSTATE: "+e.getSQLState());
                    System.err.println("VnedorError: "+e.getErrorCode());
                }
        return v;
    }

    @Override
    public List<EntiteVille> listVilles() 
    {
                List<EntiteVille> lv=new ArrayList<>();
        try 
        {
        String select = "SELECT idville, nomville, fondationville, populationville, timezone, equipelocale, coordonnees, logoville, logoequipe, photoville FROM villes";
        Statement statement1 = connexion.createStatement();
        
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            EntiteVille v = new EntiteVille();
            
            v.setIdville(result.getInt("idville"));
            v.setNom(result.getString("nomville"));
            v.setFondation(result.getString("fondationville"));
            v.setPopulation(result.getString("populationville"));
            v.setTimezone(result.getString("timezone"));
            v.setEquipelocale(result.getString("equipelocale"));
            v.setCoordonnees(result.getString("coordonnees"));
            v.setLogoville(result.getString("logoville"));
            v.setLogoequipe(result.getString("logoequipe"));
            v.setPhotoville(result.getString("photoville"));
            
            lv.add(v);

        } 
    }   
        catch (SQLException e)
                {
                    System.err.println("SQLException: "+e.getMessage());
                    System.err.println("SQLSTATE: "+e.getSQLState());
                    System.err.println("VnedorError: "+e.getErrorCode());
                }
        return lv;
}

    @Override
    public List<String> ReturnNames() 
    {
        List<String> lv = new ArrayList<>();
        try 
        {
        String select = "SELECT nomville FROM villes";
        Statement statement1 = connexion.createStatement();
      
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            lv.add(result.getString("nomville"));
        }
}
        catch (SQLException e)
                {
                    System.err.println("SQLException: "+e.getMessage());
                    System.err.println("SQLSTATE: "+e.getSQLState());
                    System.err.println("VnedorError: "+e.getErrorCode());
                }
        return lv;
}

    @Override
    public int ReturnId(String nom) 
    {
        int res=0;
        try 
        {
        String select = "SELECT idville FROM villes WHERE nomville = '"+nom+"'";
        Statement statement1 = connexion.createStatement();
      
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            res = result.getInt("idville");
        }
}
        catch (SQLException e)
                {
                    System.err.println("SQLException: "+e.getMessage());
                    System.err.println("SQLSTATE: "+e.getSQLState());
                    System.err.println("VnedorError: "+e.getErrorCode());
                }
        return res;
    }
}
