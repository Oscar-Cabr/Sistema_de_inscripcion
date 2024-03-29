import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

public class Alumnos{
  private int Ncuenta;
  private List<int[]> gruposInscritos;
  private int contraseña;
  private String nombre;

  public Alumnos(int Ncuenta, String nombre, int contraseña){
      this.Ncuenta = Ncuenta;
      this.nombre = nombre;
      this.contraseña = contraseña;
      this.gruposInscritos = new LinkedList<int[]>();
  }

  public int getNcuenta(){
      return Ncuenta;
  }
  public void setNcuenta(int Ncuenta){
      this.Ncuenta = Ncuenta;
  }
  public List<int[]> getGruposInscritos(){
      return gruposInscritos;
  }
  public int getContraseña(){
      return contraseña;
  }
  public void setContraseña(int contraseña){
      this.contraseña = contraseña;
  }
  public String getNombre(){
      return nombre;
  }
  public void setNombre(String nombre){
      this.nombre = nombre;
  }

  public void opciones(){
    Scanner input = new Scanner(System.in);
    int option;
    do{
      System.out.println("\n\nBienvenido, "+getNombre()+". ¿Qué es lo que quieres hacer?");
      System.out.println("1. Ver materias inscritas.");
      System.out.println("2. Mostrar grupos por clave de asignatura.");
      System.out.println("3. Mostrar grupos por nombre de asignatura.");
      System.out.println("4. Ver cada grupo de cada asignatura.");
      System.out.println("5. Dar de alta un grupo. (Inscribirse)");
      System.out.println("6. Dar de baja un grupo.");
      System.out.println("7. Cambiarse grupo de una asignatura.");
      System.out.print("8. Cerrar sesión.\n\t");
      option = input.nextInt();

      switch(option){
        case 1:
          imprimirMateriasInscritas();
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
          inscribir();
          break;
        case 6:
          desinscribir();
          break;
        case 7:
          cambiarGrupo();
          break;
        case 8:
          System.out.println("Cerrando sesión...");
          break;
        default:
          System.out.println("Opción no válida.");
          break;
      }
    }while(option != 8);
  }
  
  private void imprimirMateriasInscritas(){
    if(getGruposInscritos().isEmpty())
      System.out.println("No tienes grupos inscritos.");
    else{
      System.out.println("\n\nMaterias inscritas: ");
      for(int[] materia : getGruposInscritos())
        System.out.print("Asignatura: "+Sistema.asignaturas.get(materia[0]).getNombre()+"\tGrupo: "+materia[1]+"\n");
    }
  }

  private void inscribir(){
    Scanner input = new Scanner(System.in);
    int clave_asignatura=0, grupo=0;

    System.out.print("Ingresa la clave de la asignatura a la que quieres inscribir:\t");
    clave_asignatura = input.nextInt();
    if(!Sistema.asignaturas.containsKey(clave_asignatura)){
      System.out.println("La asignatura no está dada de alta en el sistema.");
      return;
    }
    else{
      if(Sistema.asignaturas.get(clave_asignatura).getGrupos().isEmpty()){
        System.out.println("No hay grupos disponibles para esta asignatura.");
        return;
      }
      else{
        System.out.print("Ingresa el número del grupo al que te quieres inscribir:\t");
        grupo = input.nextInt();
        while(!(grupo > 0 && grupo <= Sistema.asignaturas.get(clave_asignatura).getGrupos().size())){
          System.out.print("Grupo inexistente, ingrese nuevamente:\t");
          grupo = input.nextInt();
        }
        Sistema.altaGrupo(this, Sistema.asignaturas.get(clave_asignatura), grupo, true);
      }
  }
}

  private void desinscribir(){
    Scanner input = new Scanner(System.in);
    int clave_asignatura=0, grupo=0;
    boolean esta_inscrito = false;

    System.out.print("Ingresa la clave de la asignatura que quieres dar de baja:\t");
    clave_asignatura = input.nextInt();
    if(!Sistema.asignaturas.containsKey(clave_asignatura)){
      System.out.println("La asignatura no está dada de alta en el sistema.");
      return;
    }
    else{
      for(int[] materia : getGruposInscritos())
          if(materia[0] == clave_asignatura)
            esta_inscrito = true;
      if(!esta_inscrito){
        System.out.println("No estás inscrito a esa asignatura.");
        return;
      }
      else{
        for(int[] materia : getGruposInscritos())
          if(materia[0] == clave_asignatura)
            grupo = materia[1];
        Sistema.bajaGrupo(this, Sistema.asignaturas.get(clave_asignatura), grupo, true);
      }
    }
  }

  private void cambiarGrupo(){
    Scanner input = new Scanner(System.in);
    int grupo_a_cambio=0, clave_asignatura=0, grupo_viejo = 0;
    boolean esta_inscrito = false;
    
    System.out.print("\nIngresa la asignatura que quieres cambiar:\t");
    clave_asignatura = input.nextInt();
    if(!Sistema.asignaturas.containsKey(clave_asignatura)){
      System.out.println("La asignatura no está dada de alta en el sistema.");
      return;
    }
    else{
      for(int[] materia : getGruposInscritos())
          if(materia[0] == clave_asignatura)
            esta_inscrito = true;
      if(!esta_inscrito){
        System.out.println("No estás inscrito a esa asignatura.");
        return;
      }
      else{
        System.out.print("Ingresa el número del grupo al que te quieres cambiar:\t");;
        grupo_a_cambio = input.nextInt();
        if(!(grupo_a_cambio > 0 && grupo_a_cambio <= Sistema.asignaturas.get(clave_asignatura).getGrupos().size()))
          System.out.println("El grupo no existe.");
        else{
          for(int[] materia : getGruposInscritos())
            if(materia[0] == clave_asignatura)
              grupo_viejo = materia[1];
          Sistema.cambiarGrupo(this, Sistema.asignaturas.get(clave_asignatura), grupo_viejo, grupo_a_cambio);
          System.out.println("Has cambiado de grupo satisfactoriamente.");
        }
      }
    }
  }
}