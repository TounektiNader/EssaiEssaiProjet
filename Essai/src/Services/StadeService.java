package Services;

import DateStroge.MyConnection;
import Entity.EntiteStade;
import Entity.Partie;
import Entity.User;
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
        String update = "UPDATE stades SET nomstade = ?, fondationstade = ?, capacitestade = ?, photostade = ?, equipestade = ?, positionstade = ?, idville = ? WHERE idstade = ?";
        PreparedStatement statement2 = connexion.prepareStatement(update);
        statement2.setString(1, p.getNom());
        statement2.setString(2, p.getFondation());
        statement2.setString(3, p.getCapacite());
        statement2.setString(4, p.getPhoto());
        statement2.setString(5, p.getEquipelocale());
        statement2.setString(6, p.getPosition());
        statement2.setInt(7, p.getStadeVille().getId());
        statement2.setInt(8, p.getId());
        System.out.println(p.getId());
        statement2.executeUpdate();
        System.out.println("Stade "+p.getNom()+" modifié !!!");
        
        }
        catch (SQLException ex)
                {
                    System.err.println(ex.getMessage());
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
    
    public List<String> Metier(String u)
    {
        List<String> l = new ArrayList<>();
        String n1 = null;
        String n2 = null;
        String d = null;
        String h = null;
        try 
        {
            String select = "SELECT p1.NomEquipe, p2.NomEquipe, c.datePartie, c.heurePartie "
                    + "FROM partie c JOIN equipe p1 ON p1.idEquipe = c.home JOIN equipe p2 on p2.idEquipe = c.away "
                    + "WHERE p1.NomEquipe ='"+u+"' OR p2.NomEquipe = '"+u+"' ORDER by c.datePartie ASC;";
            Statement statement1 = connexion.createStatement();
            ResultSet result = statement1.executeQuery(select);
            
            while (result.next()) 
        {
            n1 = result.getString("p1.NomEquipe");
            n2 = result.getString("p2.NomEquipe");
            d = result.getString("c.datePartie");
            h = result.getString("c.heurePartie");
            l.add(n1);
            l.add(n2);
            l.add(d);
            l.add(h);

        } 
        }
        catch (SQLException e)
                {
                    System.err.println("SQLException: "+e.getMessage());
                    System.err.println("SQLSTATE: "+e.getSQLState());
                    System.err.println("VnedorError: "+e.getErrorCode());
                }
        return l;
    }

    @Override
    public String FindStadeVille(int id) 
    {
        String lv = "";
        try 
        {
        String select = "SELECT positionstade FROM stades s, villes v WHERE v.idville = s.idville and v.idville ='"+id+"'";
        Statement statement1 = connexion.createStatement();
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            lv = result.getString("positionstade");
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
    public int Nmbr() 
    {
        int res=0;
        try 
        {
        String select = "SELECT count(idstade) FROM stades;";
        Statement statement1 = connexion.createStatement();
      
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            res = result.getInt("count(idstade)");
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
