import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;

public class Profesores{
  private String Nombre;
  private String Usuario;
  private int Clave; //Contraseña de acceso al sistema.
  private List<List<Integer>> gruposImpartidos; //Lista de listas. Cada sublista representa una materia distinta cuyo índice cero es la clave de la materia y los elementos subsiguientes el número de grupo impartido en dicha asignatura.

  public Profesores(String Usuario, String Nombre, int Clave){
      this.Usuario = Usuario;  
      this.Nombre = Nombre;
      this.Clave = Clave;
      this.gruposImpartidos = new LinkedList<List<Integer>>();
  }

  public String getNombre(){
    return Nombre;
  }
  public void setNombre(String Nombre){
    this.Nombre = Nombre;
  }
  public int getClave(){
    return Clave;
  }
  public void setClave(int Clave){
    this.Clave = Clave;
  }
  public List<List<Integer>> getGruposImpartidos(){
    return gruposImpartidos;
  }
  public String getUsuario(){
    return Usuario;
  }
  public void setUsuario(String Usuario){
    this.Usuario = Usuario;
  }

  public void opciones(){
    Scanner input = new Scanner(System.in);
    int option;
    
    do{
      System.out.println("\n\nBienvenido, "+getNombre()+". ¿Qué es lo que quieres hacer?");
      System.out.println("1. Mostrar grupos que impartes.");
      System.out.println("2. Mostrar grupos por clave de asignatura.");
      System.out.println("3. Mostrar grupos por nombre de asignatura.");
      System.out.println("4. Ver cada grupo de cada asignatura.");
      System.out.println("5. Solicitar dar grupo de una asignatura.");
      System.out.println("6. Ver lista de alumnos.");
      System.out.print("7. Cerrar sesión.\n\t");
      option = input.nextInt();

      switch(option){
        case 1:
          mostrarGruposImpartidos();
          break;
        case 2:
          Sistema.mostrarGruposPorClave();
          break;
        case 3:
          Sistema.mostrarGruposPorNombre();
          break;
        case 4:
          Sistema.imprimirAsignaturas();
          break;
        case 5:
          solicitarImpartir();
          break;
        case 6:
          Sistema.imprimirAlumnos();
          break;
        case 7:
          System.out.println("Cerrando sesión...");
          break;
        default:
          System.out.println("Opción no válida.");
          break;
      }
    }while(option != 7);
  }
  
  private void mostrarGruposImpartidos(){
    if(getGruposImpartidos().isEmpty())
      System.out.println("No tienes grupos asignados.");
    else{
      System.out.print("\n\nGrupos impartidos:");
      for(List<Integer> asignaturas : getGruposImpartidos())
      for(int i = 0; i < asignaturas.size(); i++){
          if(!(i==0))
            System.out.print(asignaturas.get(i)+" ");
          else
            System.out.print("\n\tAsignatura: "+Sistema.asignaturas.get(asignaturas.get(i)).getNombre()+"\tGrupos: ");
      }
    }
  }
  
  private void solicitarImpartir(){
    Scanner input = new Scanner(System.in);
    int clave=0; 
    System.out.print("\n\nIngresa la clave de la asignatura que quieres impartir: ");
    clave = input.nextInt(); 
    if(!Sistema.asignaturas.containsKey(clave)){
      System.out.println("La asginatura no está dada de alta en el sistema.");
      return;
    }
    if(Sistema.aprobarSolicitud(getUsuario())){
      Sistema.abrirGrupo(getUsuario(), Sistema.asignaturas.get(clave));
      System.out.println("Se te ha asignado un grupo.");
    }
    else
      System.out.println("No se te concedió la apertura de grupo.");
  }
}