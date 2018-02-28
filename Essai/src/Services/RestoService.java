package Services;

import DateStroge.MyConnection;
import Entity.EntiteResto;
import iService.Iresto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RestoService implements Iresto
{

    Connection connexion;

    public RestoService() throws SQLException 
    {
        connexion = MyConnection.getInstance().getConnexion();
    }

    @Override
    public void AddResto(EntiteResto p) 
    {
        try 
        {
            String insert = "INSERT INTO restos (nomresto, detailsresto, positionresto, photoresto, idville)"
                    + " VALUES (?,?,?,?,?)";
            PreparedStatement st1 = connexion.prepareStatement(insert);
            st1.setString(1, p.getNom());
            st1.setString(2, p.getDetails());
            st1.setString(3, p.getPosition());
            st1.setString(4, p.getPhoto());
            st1.setInt(5, p.getRestoVille().getId());
            st1.executeUpdate();
            System.out.println("Resto "+p.getNom()+" ajouté !!!");
            
        } 
        catch (Exception e) 
        {
            System.err.println("SQLException: "+e.getMessage());
            System.err.println("Echec d'insertion du resto " +p.getNom());
        }
    }

    @Override
    public void DeleteResto(EntiteResto p) 
    {
        try 
        {
        String delete = "DELETE FROM restos WHERE nomresto= ?";
        PreparedStatement st2 = connexion.prepareStatement(delete);
        st2.setString(1,p.getNom());
        st2.executeUpdate();
        System.out.println("Resto "+p.getNom()+" supprimée !!!");
        
        }
        catch (Exception e)
        {
            System.err.println("Echec de la suppression du resto "+p.getNom());
        }
    }

    @Override
    public void EditResto(EntiteResto p) 
    {
        try
        {
        String update = "UPDATE restos SET nomresto = ?, detailsresto = ?, positionresto = ?, photoresto = ?, idville = ? WHERE idresto = ?";
        PreparedStatement statement2 = connexion.prepareStatement(update);
        statement2.setString(1, p.getNom());
        statement2.setString(2, p.getDetails());
        statement2.setString(3, p.getPosition());
        statement2.setString(4, p.getPhoto());
        statement2.setInt(5, p.getRestoVille().getId());
        statement2.setInt(6, p.getId());
        System.out.println(p.getId());
        statement2.executeUpdate();
        System.out.println("Resto "+p.getNom()+" modifié !!!");
        
        }
        catch (Exception e)
                {
                    System.err.println("Resto "+p.getNom()+" non modifié");
                }
    }

    @Override
    public EntiteResto FindResto(String nom) 
    {
        EntiteResto v = new EntiteResto();
        try
        {
        String select = "SELECT * FROM restos WHERE nomresto = '"+nom+"'";
        Statement statement1 = connexion.createStatement();
        ResultSet result = statement1.executeQuery(select);
       
        while (result.next()) 
        {
            v.setId(result.getInt("idresto"));
            v.setNom(result.getString("nomresto"));
            v.setDetails(result.getString("detailsresto"));
            v.setPosition(result.getString("positionresto"));
            v.setPhoto(result.getString("photoresto"));
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
    public List<EntiteResto> ListRestos() 
    {
        List<EntiteResto> lv=new ArrayList<>();
        try 
        {
        String select = "SELECT * FROM restos";
        Statement statement1 = connexion.createStatement();
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            EntiteResto v = new EntiteResto();
            
            v.setId(result.getInt("idresto"));
            v.setNom(result.getString("nomresto"));
            v.setDetails(result.getString("detailsresto"));
            v.setPosition(result.getString("positionresto"));
            v.setPhoto(result.getString("photoresto"));
            System.out.println(v.toString());
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
    public List<EntiteResto> FindRestoVille(String ville) 
    {
        List<EntiteResto> lv=new ArrayList<>();
        try 
        {
        String select = "SELECT * FROM restos c, villes v WHERE v.idville = c.idville and nomville ='"+ville+"'";
        Statement statement1 = connexion.createStatement();
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            EntiteResto v = new EntiteResto();
            
            v.setId(result.getInt("idresto"));
            v.setNom(result.getString("nomresto"));
            v.setDetails(result.getString("detailsresto"));
            v.setPosition(result.getString("positionresto"));
            v.setPhoto(result.getString("photoresto"));
            
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
    public int Nmbr() 
    {
        int res=0;
        try 
        {
        String select = "SELECT count(idresto) FROM restos;";
        Statement statement1 = connexion.createStatement();
      
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            res = result.getInt("count(idresto)");
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
