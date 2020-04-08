import java.util.ArrayList;

public class Client {
  private String name;
  private String address;
  private String telephone;

  private ArrayList<Project> projects;

  public void setName(String name){
    this.name = name;
  }

  public String getName(){
    return name;
  }

  public void setAddress(String address){
    this.address = address;
  }

  public String getAddress(){
    return address;
  }

  public void setTelephone(String telephone){
    this.telephone = telephone;
  }

  public String getTelephone(){
    return telephone;
  }

  public void setProjects(ArrayList<Project> projects){
    this.projects = projects;
  }

  public ArrayList<Project> getProjects(){
    return projects;
  }
}
