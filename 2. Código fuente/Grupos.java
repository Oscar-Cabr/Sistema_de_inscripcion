import java.util.LinkedList;
import java.util.List;

public class Grupos{
  private int alumnos_inscritos;
  private List<Integer> alumnos;
  private int numero_de_grupo;
  private String profesor;
  
  public Grupos(int alumnos_inscritos, int numero_de_grupo, String profesor){
    this.alumnos_inscritos = alumnos_inscritos;
    this.numero_de_grupo = numero_de_grupo;
    this.profesor = profesor;
    this.alumnos = new LinkedList<Integer>();
  }

  public int getNumeroDeGrupo(){
    return numero_de_grupo;
  }
  public void setNumeroDeGrupo(int numero_de_grupo){
    this.numero_de_grupo = numero_de_grupo;
  }
  public int getAlumnosInscritos(){
    return alumnos_inscritos;
  }
  public void setAlumnosInscritos(int alumnos_inscritos){
    this.alumnos_inscritos = alumnos_inscritos;
  }
  public List<Integer> getAlumnos(){
    return alumnos;
  }
  public String getProfesor(){
    return profesor;
  }
  public void setProfesor(String profesor){
    this.profesor = profesor;
  }
}