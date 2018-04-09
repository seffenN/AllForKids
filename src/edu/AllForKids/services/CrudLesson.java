/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.services;

import edu.AllForKids.entities.Lesson;
import edu.AllForKids.entities.MediaVideo;
import edu.AllForKids.utils.MyConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author khaoula
 */
public class CrudLesson {

    public MyConnexion dc = MyConnexion.getInstance().getInstance();
    public Connection c = dc.getConnection();

    public void AjouterLesson(Lesson lesson) throws SQLException {
        String sql = "INSERT INTO lesson (nomLesson,brochure,theme,categorieAge) VALUES (?,?, ?,?)";

        PreparedStatement statement = c.prepareStatement(sql);
          statement.setString(1, lesson.getNomLesson());
        statement.setString(2, lesson.getBrochure());
        statement.setString(3, lesson.getTheme());
        statement.setString(4, lesson.getCategorieAge());
        
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new lesson was inserted successfully!");
        }
    }
    public void updateLesson(Lesson lesson,int id)
    {
        try {
   
            String sql = "UPDATE lesson SET nomLesson=?,brochure=?, categorieAge=?,theme=? where id=?";
            
            PreparedStatement statement = c.prepareStatement(sql);
    statement.setString(1, lesson.getNomLesson());        
statement.setString(2, lesson.getBrochure());
statement.setString(3, lesson.getCategorieAge());
statement.setString(4, lesson.getTheme());
statement.setInt(5, id);

            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing video was updated successfully!");
            }       } catch (SQLException ex) {
            System.err.println("fauxxx");
        }
    
}
 public void DeleteLesson(int id)
    {
        try {
           
            String sql = "update lesson set flag=0 WHERE id=?";
            
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, id);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A lesson was deleted successfully!");
            }       } catch (SQLException ex) {
            System.err.println("FAUXX");
        }
    }
     public ArrayList<Lesson> getAllLessons()
     {
         ArrayList<Lesson> lessons=new ArrayList<>();
          try {
 
        String sql = "SELECT * FROM lesson";
        
        Statement statement = c.createStatement();
        ResultSet result = statement.executeQuery(sql);
        
     
        
        while (result.next()){
            Lesson lesson=new Lesson();
            lesson.setId(result.getInt("id"));
             lesson.setNomLesson(result.getString("nomLesson"));
         lesson.setBrochure(result.getString("brochure"));
          
                lesson.setTheme(result.getString("theme"));
             lesson.setCategorieAge(result.getString("categorieage"));
            
              lessons.add(lesson);
            
          
        }       } catch (SQLException ex) {
                System.err.println("fauxxx");
        }
          return lessons;
     }
     public Lesson getLesson(int id) throws SQLException
     {
      
       
 
        String sql = "SELECT * FROM lesson where flag=1 and id=?";
        
        PreparedStatement statement = c.prepareStatement(sql);
        statement.setInt(1, id);
       ResultSet result = statement.executeQuery();
 Lesson lesson=new Lesson();
 
       if(result.next())
           
            lesson.setId(result.getInt("id"));
                lesson.setBrochure(result.getString("brochure"));
         lesson.setNomLesson(result.getString("nomLesson"));
             lesson.setTheme(result.getString("theme"));
                lesson.setCategorieAge(result.getString("categorieAge"));
             
           
            
          
       
          return lesson;
     }
     
                             public ArrayList<Lesson> FilterLessonTheme(ArrayList<String> theme,ArrayList<String> cat) throws SQLException {
        ArrayList<Lesson> newlist=new ArrayList<>();
        String sql ="SELECT * FROM lesson where flag=1";
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
            Lesson quiz = new Lesson();

            quiz.setId(result.getInt("id"));
            quiz.setCategorieAge(result.getString("categorieAge"));
          
            quiz.setNomLesson(result.getString("nomLesson"));
            quiz.setTheme(result.getString("theme"));
          quiz.setBrochure(result.getString("brochure"));
          newlist.add(quiz);
          
        }

        
    return newlist;
                    }
}
