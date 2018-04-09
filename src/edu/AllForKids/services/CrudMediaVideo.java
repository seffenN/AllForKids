/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.services;

import edu.AllForKids.entities.MediaVideo;

import edu.AllForKids.utils.MyConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author khaoula
 */
public class CrudMediaVideo {
      public MyConnexion dc = MyConnexion.getInstance().getInstance();
    public Connection c = dc.getConnection();
    
    public void AjouterVideo(MediaVideo video) throws SQLException
    {
         String sql = "INSERT INTO mediavideo (type,titre,identif,theme,categorieAge,description) VALUES (?,?, ?,?,?,?)";

PreparedStatement statement = c.prepareStatement(sql);
statement.setString(1, video.getType());
statement.setString(2, video.getTitre());
statement.setString(3, video.getIdentif());
statement.setString(4, video.getTheme());
statement.setString(5, video.getCategorieAge());
statement.setString(6, video.getDescription());

 
int rowsInserted = statement.executeUpdate();
if (rowsInserted > 0) {
    System.out.println("A new user was inserted successfully!");
}
    }
     public void updateLesson(MediaVideo media,int id)
    {
        try {
   
            String sql = "UPDATE mediavideo SET type=?,titre=?, identif=?,theme=?,categorieAge=?,Description=? where id=?";
            
            PreparedStatement statement = c.prepareStatement(sql);
statement.setString(1, media.getType());
statement.setString(2, media.getTitre());
statement.setString(3, media.getIdentif());
statement.setString(4, media.getTheme());
statement.setString(5, media.getCategorieAge());
statement.setString(6, media.getDescription());
statement.setInt(7, id);

            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing video was updated successfully!");
            }       } catch (SQLException ex) {
            System.err.println("fauxxx");
        }
    
}
     
     public void DeleteVideo(int id)
    {
        try {
           
            String sql = "update  mediavideo set flag=0 WHERE id=?";
            
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, id);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A video was deleted successfully!");
            }       } catch (SQLException ex) {
            System.err.println("FAUXX");
        }
    }
    
     public ArrayList<MediaVideo> getAllVideo()
     {
         ArrayList<MediaVideo> videos=new ArrayList<>();
          try {
 
        String sql = "SELECT * FROM mediavideo where flag=1";
        
        Statement statement = c.createStatement();
        ResultSet result = statement.executeQuery(sql);
        
     
        
        while (result.next()){
            MediaVideo media=new MediaVideo();
            media.setId(result.getInt("id"));
                media.setTitre(result.getString("titre"));
         media.setIdentif(result.getString("identif"));
             media.setType(result.getString("type"));
                media.setTheme(result.getString("theme"));
             media.setCategorieAge(result.getString("categorieage"));
              media.setDescription(result.getString("description"));
              videos.add(media);
            
          
        }       } catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }
          return videos;
     }
     
    
     public MediaVideo getVideo(int id) throws SQLException
     {
      
       
 
        String sql = "SELECT * FROM mediavideo where flag=1 and id=?";
        
        PreparedStatement statement = c.prepareStatement(sql);
        statement.setInt(1, id);
       ResultSet result = statement.executeQuery();
 MediaVideo media=new MediaVideo();
 
       if(result.next())
           
            media.setId(result.getInt("id"));
                media.setTitre(result.getString("titre"));
         media.setIdentif(result.getString("identif"));
             media.setType(result.getString("type"));
                media.setTheme(result.getString("theme"));
             media.setCategorieAge(result.getString("categorieage"));
              media.setDescription(result.getString("description"));
           
            
          
       
          return media;
     }
     
                        public ArrayList<MediaVideo> FilterQuizTheme(ArrayList<String> theme,ArrayList<String> cat) throws SQLException {
        ArrayList<MediaVideo> newlist=new ArrayList<>();
        String sql ="SELECT * FROM mediavideo where flag=1";
       int i=1;
for(String th:theme)
{     if(i==1)
{
         sql = sql+" and (theme like ?";
         i++;
         System.out.println(sql);
}
else
{
    sql = sql+" or theme like ?";
    i++;
     System.out.println(sql);
}
}
int k=1;
if(i>1) sql+=")";
for(String th:cat)
{     if(k==1)
{
         sql = sql+" and (categorieage like ?";
         k++;
         System.out.println(sql);
}
else
{
    sql = sql+" or categorieage like ?";
    k++;
     System.out.println(sql);
}
}
if(k>1)
sql+=" )";

        PreparedStatement statement = c.prepareStatement(sql);
        int j=0;
        for(String s:theme)
        { j++;
            statement.setString(j, s);
            System.out.println(s);
        }
        int l=j;
          for(String s:cat)
        { l++;
            statement.setString(l, s);
            System.out.println(s);
        }
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            MediaVideo quiz = new MediaVideo();

            quiz.setId(result.getInt("id"));
            quiz.setCategorieAge(result.getString("categorieage"));
            quiz.setDescription(result.getString("description"));
            quiz.setTitre(result.getString("titre"));
            quiz.setTheme(result.getString("theme"));
          quiz.setIdentif(result.getString("identif"));
          newlist.add(quiz);
          
        }

        
    return newlist;
                    }
     
}
