package Services;

import DateStroge.MyConnection;
import Entity.EntiteStade;
import iService.Istade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StadeService implements Istade
{
    Connection connexion;

    public StadeService() throws SQLException 
    {
        connexion = MyConnection.getInstance().getConnexion();
    }
    @Override
    public void AddSatde(EntiteStade p) 
    {
        try 
        {
            String insert = "INSERT INTO stades (nomstade, fondationstade, capacitestade, photostade, equipestade, positionstade, idville)"
                    + " VALUES (?,?,?,?,?,?,?)";
            PreparedStatement st1 = connexion.prepareStatement(insert);
            st1.setString(1, p.getNom());
            st1.setString(2, p.getFondation());
            st1.setString(3, p.getCapacite());
            st1.setString(4, p.getPhoto());
            st1.setString(5, p.getEquipelocale());
            st1.setString(6, p.getPosition());
            st1.setInt(7, p.getStadeVille().getId());
            st1.executeUpdate();
            System.out.println("Stade "+p.getNom()+" ajouté !!!");
            
        } 
        catch (Exception e) 
        {
            System.err.println("SQLException: "+e.getMessage());
            System.err.println("Echec d'insertion du stade " +p.getNom());
        }
    }

    @Override
    public void DeleteStade(EntiteStade p) 
    {
        try 
        {
        String delete = "DELETE FROM stades WHERE nomstade= ?";
        PreparedStatement st2 = connexion.prepareStatement(delete);
        st2.setString(1,p.getNom());
        st2.executeUpdate();
        System.out.println("Stade "+p.getNom()+" supprimée !!!");
        
        }
        catch (Exception e)
        {
            System.err.println("Echec de la suppression du stade "+p.getNom());
        }
    }

    @Override
    public void EditStade(EntiteStade p) 
    {
        try
        {
        String update = "UPDATE stades SET positionstade = ? WHERE nomstade = ?";
        PreparedStatement statement2 = connexion.prepareStatement(update);
        statement2.setString(1, "Hamza");
        statement2.setString(2, p.getNom());
        statement2.executeUpdate();
        System.out.println("Stade "+p.getNom()+" modifié !!!");
        
        }
        catch (Exception e)
                {
                    System.err.println("Stade "+p.getNom()+" non modifié");
                }
    }

    @Override
    public EntiteStade FindStade(String nom) 
    {
        EntiteStade v = new EntiteStade();
        try
        {
        String select = "SELECT * FROM stades WHERE nomstade = '"+nom+"'";
        Statement statement1 = connexion.createStatement();
        ResultSet result = statement1.executeQuery(select);
       
        while (result.next()) 
        {
            v.setId(result.getInt("idstade"));
            v.setNom(result.getString("nomstade"));
            v.setFondation(result.getString("fondationstade"));
            v.setCapacite(result.getString("capacitestade"));
            v.setPhoto(result.getString("photostade"));
            v.setEquipelocale(result.getString("equipestade"));
            v.setPosition(result.getString("positionstade"));
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
    public List<EntiteStade> ListStades() 
    {
        List<EntiteStade> lv=new ArrayList<>();
        try 
        {
        String select = "SELECT * FROM stades";
        Statement statement1 = connexion.createStatement();
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            EntiteStade v = new EntiteStade();
            
             v.setId(result.getInt("idstade"));
            v.setNom(result.getString("nomstade"));
            v.setFondation(result.getString("fondationstade"));
            v.setCapacite(result.getString("capacitestade"));
            v.setPhoto(result.getString("photostade"));
            v.setEquipelocale(result.getString("equipestade"));
            v.setPosition(result.getString("positionstade"));
            
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
    public List<EntiteStade> FindStadeVille(String ville) 
    {
        List<EntiteStade> lv=new ArrayList<>();
        try 
        {
        String select = "SELECT * FROM stades c, villes v WHERE v.idville = c.idville and nomville ='"+ville+"'";
        Statement statement1 = connexion.createStatement();
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            EntiteStade v = new EntiteStade();
            
            v.setId(result.getInt("idstade"));
            v.setNom(result.getString("nomstade"));
            v.setFondation(result.getString("fondationstade"));
            v.setCapacite(result.getString("capacitestade"));
            v.setPosition(result.getString("positionstade"));
            v.setPhoto(result.getString("photostade"));
            v.setEquipelocale(result.getString("equipestade"));
            
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
    
}
