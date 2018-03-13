/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author IRONSIDE
 */
public class Vaccin {
    
    int id;
    String nomVaccin;
    String age;
    String maladies;
    String description;
    
    public Vaccin() {
    }

    public Vaccin(int id, String nomVaccin, String age, String maladies, String description) {
        this.id = id;
        this.nomVaccin = nomVaccin;
        this.age =age ;
        this.maladies = maladies;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomVaccin() {
        return nomVaccin;
    }

    public void setNomVaccin(String nomVaccin) {
        this.nomVaccin = nomVaccin;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMaladies() {
        return maladies;
    }

    public void setMaladies(String maladies) {
        this.maladies = maladies;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
@Override
    public String toString() {
        return "Vaccin{" + "nom=" + nomVaccin + ", age=" + age + ", vaccin contre=" + maladies + ", description=" + description +  '}';
    }    
    
    
}
