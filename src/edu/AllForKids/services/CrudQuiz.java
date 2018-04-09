/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.services;

import edu.AllForKids.entities.Quiz;
import edu.AllForKids.entities.Question;
import edu.AllForKids.entities.Reponse;
import edu.AllForKids.entities.Score;
import edu.AllForKids.utils.MyConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khaoula
 */
public class CrudQuiz {

    public MyConnexion dc = MyConnexion.getInstance().getInstance();
    public Connection c = dc.getConnection();
    

    public void AjouerQuiz(Quiz quiz, Map<Question, ArrayList<Reponse>> map) throws SQLException {

        String sql = "INSERT INTO quiz (nom_quiz, theme,categorie_age,time,description,image,total) VALUES (?,?,?,?,?,?,?)";

        PreparedStatement statement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, quiz.getNom_quiz());
        statement.setString(2, quiz.getTheme());
        statement.setString(3, quiz.getCategorie_age());
        statement.setInt(4, quiz.getTime());
        statement.setString(5, quiz.getDescription());
        statement.setString(6, quiz.getImage());
        statement.setInt(7, quiz.getTotal());

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int auto_id = rs.getInt(1);
            for (Map.Entry<Question, ArrayList<Reponse>> e : map.entrySet()) {
                String sql2 = "INSERT INTO question (id_quiz_id, libelle) VALUES (?,?)";

                PreparedStatement statement2 = c.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
                statement2.setInt(1, auto_id);
                statement2.setString(2, e.getKey().getLibelle());

                int rowsInsertedQuest = statement2.executeUpdate();
                if (rowsInsertedQuest > 0) {
                    for (Reponse r : e.getValue()) {
                        ResultSet rsques = statement2.getGeneratedKeys();
                        rsques.next();
                        int auto_id_que = rsques.getInt(1);
                        String sql3 = "INSERT INTO reponse (id_quest_id, libelle,verif,point) VALUES (?,?,?,?)";

                        PreparedStatement statement3 = c.prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS);
                        statement3.setInt(1, auto_id_que);
                        statement3.setString(2, r.getLibelle());
                        statement3.setInt(3, r.getVerif());
                        statement3.setInt(4, r.getPoint());

                        int rowsInsertedRep = statement3.executeUpdate();
                        System.out.println(rowsInsertedRep);

                    }
                }
            }

        }
    }

    public void ModifierQuiz(Quiz quiz, Map<Question, ArrayList<Reponse>> map, int id) throws SQLException {

        //nom_quiz, theme,categorie_age,time,description,image,total
        String sql = "UPDATE quiz SET nom_quiz=?, theme=?,categorie_age=?,time=?,description=?,image=?,total=? where id=?";

        PreparedStatement statement;

        statement = c.prepareStatement(sql);

        statement.setString(1, quiz.getNom_quiz());
        statement.setString(2, quiz.getTheme());
        statement.setString(3, quiz.getCategorie_age());
        statement.setInt(4, quiz.getTime());
        statement.setString(5, quiz.getDescription());
        statement.setString(6, quiz.getImage());
        statement.setInt(7, quiz.getTotal());
        statement.setInt(8, id);

        String sqldelete = "DELETE FROM question WHERE id_quiz_id=?";

        PreparedStatement statementdel = c.prepareStatement(sqldelete);
        statementdel.setInt(1, id);
        int rowsInserted = statement.executeUpdate();

        int auto_id = id;
        for (Map.Entry<Question, ArrayList<Reponse>> e : map.entrySet()) {
            String sql2 = "INSERT INTO question (id_quiz_id, libelle) VALUES (?,?)";

            PreparedStatement statement2 = c.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
            statement2.setInt(1, auto_id);
            statement2.setString(2, e.getKey().getLibelle());

            int rowsInsertedQuest = statement2.executeUpdate();
            if (rowsInsertedQuest > 0) {
                for (Reponse r : e.getValue()) {
                    ResultSet rsques = statement2.getGeneratedKeys();
                    rsques.next();
                    int auto_id_que = rsques.getInt(1);
                    String sql3 = "INSERT INTO reponse (id_quest_id, libelle,verif,point) VALUES (?,?,?,?)";

                    PreparedStatement statement3 = c.prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS);
                    statement3.setInt(1, auto_id_que);
                    statement3.setString(2, r.getLibelle());
                    statement3.setInt(3, r.getVerif());
                    statement3.setInt(4, r.getPoint());

                    int rowsInsertedRep = statement3.executeUpdate();
                    System.out.println(rowsInsertedRep);

                }
            }

        }
    }

    public HashMap<Quiz, HashMap<Question, ArrayList<Reponse>>> getAllQuiz(int id) throws SQLException {
        HashMap<Quiz, HashMap<Question, ArrayList<Reponse>>> map = new HashMap<>();

        String sql = "SELECT * FROM quiz where id=" + id;

        Statement statement = c.createStatement();
       
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            Quiz quiz = new Quiz();

            quiz.setId(result.getInt("id"));
            quiz.setCategorie_age(result.getString("categorie_age"));
            quiz.setDescription(result.getString("description"));
            quiz.setNom_quiz(result.getString("nom_quiz"));
            quiz.setTheme(result.getString("theme"));
            quiz.setImage(result.getString("image"));
            quiz.setTime(result.getInt("time"));
            quiz.setTotal(result.getInt("total"));

            String sqlquest = "SELECT * FROM question where id_quiz_id=" + result.getInt("id");

            Statement statementquest = c.createStatement();
            ResultSet resultquest = statementquest.executeQuery(sqlquest);
            HashMap<Question, ArrayList<Reponse>> maping = new HashMap<>();
            while (resultquest.next()) {
                Question question = new Question();
                question.setId(resultquest.getInt("id"));
                question.setId_quiz_id(resultquest.getInt("id_quiz_id"));
                question.setLibelle(resultquest.getString("libelle"));
                String sqlrep = "SELECT * FROM reponse where id_quest_id=" + resultquest.getInt("id");

                Statement statementrep = c.createStatement();
                ResultSet resultrep = statementrep.executeQuery(sqlrep);
                ArrayList<Reponse> myList = new ArrayList<Reponse>();
                while (resultrep.next()) {
                    Reponse reponse = new Reponse();
                    reponse.setId(resultrep.getInt("id"));
                    reponse.setLibelle(resultrep.getString("libelle"));
                    reponse.setLibelle(resultrep.getString("libelle"));
                    reponse.setPoint(resultrep.getInt("point"));
                    reponse.setVerif(resultrep.getInt("verif"));
                    myList.add(reponse);

                }
                maping.put(question, myList);

            }
            map.put(quiz, maping);
        }

        return map;
    }
      public ArrayList<Quiz> ListeQuiz() throws SQLException {
        ArrayList<Quiz> newlist=new ArrayList<>();

        String sql = "SELECT * FROM quiz where flag=1";

        Statement statement = c.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            Quiz quiz = new Quiz();

            quiz.setId(result.getInt("id"));
            quiz.setCategorie_age(result.getString("categorie_age"));
            quiz.setDescription(result.getString("description"));
            quiz.setNom_quiz(result.getString("nom_quiz"));
            quiz.setTheme(result.getString("theme"));
            quiz.setImage(result.getString("image"));
            quiz.setTime(result.getInt("time"));
            quiz.setTotal(result.getInt("total"));

          newlist.add(quiz);
          
        }

        return newlist;
    }
            public ArrayList<Quiz> ListeQuizbytheme(String theme) throws SQLException {
        ArrayList<Quiz> newlist=new ArrayList<>();

        String sql = "SELECT * FROM quiz where flag=1 and theme=?";

        PreparedStatement statement = c.prepareStatement(sql);
        statement.setString(1, theme);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            Quiz quiz = new Quiz();

            quiz.setId(result.getInt("id"));
            quiz.setCategorie_age(result.getString("categorie_age"));
            quiz.setDescription(result.getString("description"));
            quiz.setNom_quiz(result.getString("nom_quiz"));
            quiz.setTheme(result.getString("theme"));
            quiz.setImage(result.getString("image"));
            quiz.setTime(result.getInt("time"));
            quiz.setTotal(result.getInt("total"));

          newlist.add(quiz);
          
        }

        return newlist;
    }
                    public ArrayList<Quiz> FilterQuizTheme(ArrayList<String> theme,ArrayList<String> cat) throws SQLException {
        ArrayList<Quiz> newlist=new ArrayList<>();
        String sql ="SELECT * FROM quiz where flag=1";
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
         sql = sql+" and (categorie_age like ?";
         k++;
         System.out.println(sql);
}
else
{
    sql = sql+" or categorie_age like ?";
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
            Quiz quiz = new Quiz();

            quiz.setId(result.getInt("id"));
            quiz.setCategorie_age(result.getString("categorie_age"));
            quiz.setDescription(result.getString("description"));
            quiz.setNom_quiz(result.getString("nom_quiz"));
            quiz.setTheme(result.getString("theme"));
            quiz.setImage(result.getString("image"));
            quiz.setTime(result.getInt("time"));
            quiz.setTotal(result.getInt("total"));

          newlist.add(quiz);
          
        }

        
    return newlist;
                    }
            
                  public ArrayList<Quiz> ListeQuizbythemeenfant(String theme,int id) throws SQLException {
        ArrayList<Quiz> newlist=new ArrayList<>();

        String sql ="select c.id,c.nom_quiz,c.theme,c.categorie_age,c.time,c.image,c.total,c.description from quiz c,enfant enfant\n" +
"where ((SELECT DATEDIFF( date(now()), (DATE(enfant.DateNaissance)) ))>=substr(categorie_age,1,INSTR(categorie_age,'-')-1)*360) AND\n" +
"((SELECT DATEDIFF( date(now()), (DATE(enfant.DateNaissance)) ))>=substr(categorie_age,INSTR(categorie_age,'-')+1)*360)\n" +
"\n" +
"and enfant.id=? and c.theme like ? and c.flag=1";

        PreparedStatement statement = c.prepareStatement(sql);
        statement.setString(1, theme);
        statement.setInt(2, id);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            Quiz quiz = new Quiz();

            quiz.setId(result.getInt("id"));
            quiz.setCategorie_age(result.getString("categorie_age"));
            quiz.setDescription(result.getString("description"));
            quiz.setNom_quiz(result.getString("nom_quiz"));
            quiz.setTheme(result.getString("theme"));
            quiz.setImage(result.getString("image"));
            quiz.setTime(result.getInt("time"));
            quiz.setTotal(result.getInt("total"));

          newlist.add(quiz);
          
        }

        return newlist;
    }
      
      public void SupprimerQuiz(int id) 
      {
          try {
           
            String sql = "update quiz set flag=? WHERE id=?";
            
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, 0);
              statement.setInt(2, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A lesson was deleted successfully!");
            }       } catch (SQLException ex) {
            System.err.println("FAUXX");
        }
        }
      
      
          public void CalculScore(Score score) throws SQLException {
        String sql = "INSERT INTO score (score,id_quiz_id,id_enfant) VALUES (?, ?,?)";

        PreparedStatement statement = c.prepareStatement(sql);
        statement.setDouble(1, score.getScore());
        statement.setInt(2, score.getId_quiz_id());
        statement.setInt(3, score.getId_enfant());
        
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new score was inserted successfully!");
        }
    }
              public double StatScore1(String theme,int mois,int annee,int id_enfant) throws SQLException
    {
       
        
       String sql = "select sum(score) from score where extract(year from datesaisie)=? and extract(month from datesaisie)=?  and id_enfant=? and id_quiz_id in(SELECT id from quiz where theme like ?)";
       String sql2="select SUM(total) from quiz where theme like ? and id in(select id_quiz_id FROM score WHERE extract(year from datesaisie)=? and extract(month from datesaisie)=?  and id_enfant=?)";
       PreparedStatement ps = c.prepareStatement(sql);
       PreparedStatement ps2 = c.prepareStatement(sql2);
        ps.setInt(1, annee);
        ps.setInt(2, mois);
        ps.setInt(3, id_enfant);
        ps.setString(4, theme);
        ResultSet rs = ps.executeQuery();
        
      
       
        ps2.setString(1, theme);
           ps2.setInt(2, annee);
        ps2.setInt(3, mois);
        ps2.setInt(4, id_enfant);
        ResultSet rs2 = ps2.executeQuery();
        double res1=0;
         while(rs.next()) 
         {
             res1=rs.getDouble(1);
         }
          double res2=0;
         while(rs2.next()) 
         {
             res2=rs2.getDouble(1);
         }
       
       return (res1/res2)*10;
    }
   
               public double getPourcentagePlayed(String theme) throws SQLException
               {
                   String sql = "select count(*) from score where id_quiz_id in (select id from quiz where theme like ?)";
       String sql2="select * from score";
       PreparedStatement ps = c.prepareStatement(sql);
       PreparedStatement ps2 = c.prepareStatement(sql2);
     
        ps.setString(1, theme);
        ResultSet rs = ps.executeQuery();
       
        ResultSet rs2 = ps2.executeQuery();
        double res1=0;
         while(rs.next()) 
         {
             res1=rs.getDouble(1);
         }
          double res2=0;
         while(rs2.next()) 
         {
             res2=rs2.getDouble(1);
         }
       
       return (res1/res2)*10;
               }
               
                         public double getPourcentageScored(String theme) throws SQLException
               {
                   String sql = "select sum(score) from score where id_quiz_id in (select id from quiz where theme like ?)";
       String sql2="select sum(score) from score";
       PreparedStatement ps = c.prepareStatement(sql);
       PreparedStatement ps2 = c.prepareStatement(sql2);
     
        ps.setString(1, theme);
        ResultSet rs = ps.executeQuery();
       
        ResultSet rs2 = ps2.executeQuery();
        double res1=0;
         while(rs.next()) 
         {
             res1=rs.getDouble(1);
         }
          double res2=0;
         while(rs2.next()) 
         {
             res2=rs2.getDouble(1);
         }
       
       return (res1/res2)*10;
               }

}
          
      

