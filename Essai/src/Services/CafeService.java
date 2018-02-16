package Services;

import DateStroge.MyConnection;
import Entity.EntiteCafe;
import iService.Icafe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CafeService implements Icafe
{

    Connection connexion;

    public CafeService() throws SQLException 
    {
        connexion = MyConnection.getInstance().getConnexion();
    }

    @Override
    public void AddCafe(EntiteCafe p) 
    {
        try 
        {
            String insert = "INSERT INTO cafes (nomcafe, detailscafe, positioncafe, photocafe, idville)"
                    + " VALUES (?,?,?,?,?)";
            PreparedStatement st1 = connexion.prepareStatement(insert);
            st1.setString(1, p.getNom());
            st1.setString(2, p.getDetails());
            st1.setString(3, p.getPosition());
            st1.setString(4, p.getPhoto());
            System.out.println(p.getCafeVille().getId());
            //p.getCafeVille().setIdville(1);
            st1.setInt(5, p.getCafeVille().getId());
            st1.executeUpdate();
            System.out.println("Café "+p.getNom()+" ajouté !!!");
            
        } 
        catch (Exception e) 
        {
            System.err.println("SQLException: "+e.getMessage());
            System.err.println("Echec d'insertion du café " +p.getNom());
        }
    }

    @Override
    public void DeleteCafe(EntiteCafe p) 
    {
        try 
        {
        String delete = "DELETE FROM cafes WHERE nomcafe= ?";
        PreparedStatement st2 = connexion.prepareStatement(delete);
        st2.setString(1,p.getNom());
        st2.executeUpdate();
        System.out.println("Café "+p.getNom()+" supprimée !!!");
        
        }
        catch (Exception e)
        {
            System.err.println("Echec de la suppression du café "+p.getNom());
        }
    }

    @Override
    public void EditCafe(EntiteCafe p) 
    {
        try
        {
        String update = "UPDATE cafes SET positioncafe = ? WHERE nomcafe = ?";
        PreparedStatement statement2 = connexion.prepareStatement(update);
        statement2.setString(1, "Hamza");
        statement2.setString(2, p.getNom());
        statement2.executeUpdate();
        System.out.println("Café "+p.getNom()+" modifié !!!");
        
        }
        catch (Exception e)
                {
                    System.err.println("Café "+p.getNom()+" non modifié");
                }
    }

    @Override
    public EntiteCafe FindCafe(String nom) 
    {
        EntiteCafe v = new EntiteCafe();
        try
        {
        String select = "SELECT * FROM cafes WHERE nomcafe = '"+nom+"'";
        Statement statement1 = connexion.createStatement();
        ResultSet result = statement1.executeQuery(select);
       
        while (result.next()) 
        {
            v.setId(result.getInt("idcafe"));
            v.setNom(result.getString("nomcafe"));
            v.setDetails(result.getString("detailscafe"));
            v.setPosition(result.getString("positioncafe"));
            v.setPhoto(result.getString("photocafe"));
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
    public List<EntiteCafe> ListCafes() 
    {
        List<EntiteCafe> lv=new ArrayList<>();
        try 
        {
        String select = "SELECT * FROM cafes";
        Statement statement1 = connexion.createStatement();
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            EntiteCafe v = new EntiteCafe();
            
            v.setId(result.getInt("idcafe"));
            v.setNom(result.getString("nomcafe"));
            v.setDetails(result.getString("detailscafe"));
            v.setPosition(result.getString("positioncafe"));
            v.setPhoto(result.getString("photocafe"));
            
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
    public List<EntiteCafe> FindCafeVille(String ville) 
    {
        List<EntiteCafe> lv=new ArrayList<>();
        try 
        {
        String select = "SELECT * FROM cafes c, villes v WHERE v.idville = c.idville and nomville ='"+ville+"'";
        Statement statement1 = connexion.createStatement();
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            EntiteCafe v = new EntiteCafe();
            
            v.setId(result.getInt("idcafe"));
            v.setNom(result.getString("nomcafe"));
            v.setDetails(result.getString("detailscafe"));
            v.setPosition(result.getString("positioncafe"));
            v.setPhoto(result.getString("photocafe"));
            
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
