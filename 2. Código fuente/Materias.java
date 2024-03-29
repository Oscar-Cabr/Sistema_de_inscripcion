import java.util.List;
import java.util.LinkedList;

public class Materias{
  private int clave;
  private String nombre;
  private List<Grupos> grupos;

  public Materias(int clave, List<Grupos> grupos, String nombre){
    this.clave = clave;
    this.grupos = grupos;
    this.nombre = nombre;
    this.grupos = new LinkedList<Grupos>();
  }
  public Materias(String nombre){
    this.nombre = nombre;
    this.grupos = new LinkedList<Grupos>();
    generarClave();
  }

  public String getNombre(){
    return nombre;
  }
  public void setNombre(String nombre){
    this.nombre = nombre;
  }
  public int getClave(){
    return clave;
  }
  public void setClave(int clave){
    this.clave = clave;
  }
  public List<Grupos> getGrupos(){
    return grupos;
  }

  public void generarClave(){
    setClave((int)getNombre().charAt(0)*1000 + 100+Sistema.asignaturas_totales.size()+1);
  }
}