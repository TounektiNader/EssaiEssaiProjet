package Services;

import DateStroge.MyConnection;
import Entity.EntiteHotel;
import iService.Ihotel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HotelService implements Ihotel
{

    Connection connexion;

    public HotelService() throws SQLException 
    {
        connexion = MyConnection.getInstance().getConnexion();
    }

    @Override
    public void AddHotel(EntiteHotel p) 
    {
        try 
        {
            String insert = "INSERT INTO hotels (nomhotel, detailshotel, positionhotel, photohotel, idville)"
                    + " VALUES (?,?,?,?,?)";
            PreparedStatement st1 = connexion.prepareStatement(insert);
            st1.setString(1, p.getNom());
            st1.setString(2, p.getDetails());
            st1.setString(3, p.getPosition());
            st1.setString(4, p.getPhoto());
            st1.setInt(5, p.getHotelVille().getId());
            st1.executeUpdate();
            System.out.println("Hôtel "+p.getNom()+" ajouté !!!");
            
        } 
        catch (Exception e) 
        {
            System.err.println("SQLException: "+e.getMessage());
            System.err.println("Echec d'insertion de l'hôtel " +p.getNom());
        }
    }

    @Override
    public void DeleteHotel(EntiteHotel p) 
    {
        try 
        {
        String delete = "DELETE FROM hotels WHERE nomhotel= ?";
        PreparedStatement st2 = connexion.prepareStatement(delete);
        st2.setString(1,p.getNom());
        st2.executeUpdate();
        System.out.println("Hôtel "+p.getNom()+" supprimée !!!");
        
        }
        catch (Exception e)
        {
            System.err.println("Echec de la suppression de l'hôtel "+p.getNom());
        }
    }

    @Override
    public void EditHotel(EntiteHotel p) 
    {
        try
        {
        String update = "UPDATE hotels SET nomhotel = ?, detailshotel = ?, positionhotel = ?, photohotel = ?, idville = ? WHERE idhotel = ?";
        PreparedStatement statement2 = connexion.prepareStatement(update);
        statement2.setString(1, p.getNom());
        statement2.setString(2, p.getDetails());
        statement2.setString(3, p.getPosition());
        statement2.setString(4, p.getPhoto());
        statement2.setInt(5, p.getHotelVille().getId());
        statement2.setInt(6, p.getId());
        System.out.println(p.getId());
        statement2.executeUpdate();
        System.out.println("Hôtel "+p.getNom()+" modifié !!!");
        
        }
        catch (SQLException e)
        {
            System.err.println("SQLException: "+e.getMessage());
        }
    }

    @Override
    public EntiteHotel FindHotel(String nom) 
    {
        EntiteHotel v = new EntiteHotel();
        try
        {
        String select = "SELECT * FROM hotels WHERE nomhotel = '"+nom+"'";
        Statement statement1 = connexion.createStatement();
        ResultSet result = statement1.executeQuery(select);
       
        while (result.next()) 
        {
            v.setId(result.getInt("idhotel"));
            v.setNom(result.getString("nomhotel"));
            v.setDetails(result.getString("detailshotel"));
            v.setPosition(result.getString("positionhotel"));
            v.setPhoto(result.getString("photohotel"));
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
    public List<EntiteHotel> ListHotels() 
    {
        List<EntiteHotel> lv=new ArrayList<>();
        try 
        {
        String select = "SELECT * FROM hotels";
        Statement statement1 = connexion.createStatement();
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            EntiteHotel v = new EntiteHotel();
            
            v.setId(result.getInt("idhotel"));
            v.setNom(result.getString("nomhotel"));
            v.setDetails(result.getString("detailshotel"));
            v.setPosition(result.getString("positionhotel"));
            v.setPhoto(result.getString("photohotel"));
            
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
    public List<EntiteHotel> FindHotelVille(String ville) 
    {
        List<EntiteHotel> lv=new ArrayList<>();
        try 
        {
        String select = "SELECT * FROM hotels c, villes v WHERE v.idville = c.idville and nomville ='"+ville+"'";
        Statement statement1 = connexion.createStatement();
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            EntiteHotel v = new EntiteHotel();
            
            v.setId(result.getInt("idhotel"));
            v.setNom(result.getString("nomhotel"));
            v.setDetails(result.getString("detailshotel"));
            v.setPosition(result.getString("positionhotel"));
            v.setPhoto(result.getString("photohotel"));
            
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
        String select = "SELECT count(idhotel) FROM hotels;";
        Statement statement1 = connexion.createStatement();
      
        ResultSet result = statement1.executeQuery(select);
        
        while (result.next()) 
        {
            res = result.getInt("count(idhotel)");
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
