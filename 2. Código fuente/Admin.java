import java.util.Scanner;

public class Admin{
  public static void iniciar(){
    Scanner input = new Scanner(System.in);
    int option;

    System.out.println("\n\nBienvenido, Admin. ¿Qué es lo que quieres hacer?");
    System.out.println("1. Registrar alumno.");
    System.out.println("2. Registrar docente.");
    System.out.println("3. Registrar asignatura.");
    System.out.println("4. Abrir un grupo.");
    System.out.println("5. Mostrar grupos por clave de asignatura.");
    System.out.println("6. Mostrar grupos por nombre de asignatura.");
    System.out.println("7. Ver cada grupo de cada asignatura.");
    System.out.println("8. Ver lista de alumnos totales inscritos.");
    System.out.println("9. Ver lista de docentes totales.");
    System.out.print("10. Cerrar sesión.\n\t");
    option = input.nextInt();

    switch(option){
      case 1:
        Sistema.crearAlumno();
        iniciar();
        break;
      case 2:
        Sistema.crearDocente();
        iniciar();
        break;
      case 3:
        Sistema.crearAsignatura();
        iniciar();
        break;
      case 4:
        nuevoGrupo();
        iniciar();
        break;
      case 5:
        Sistema.mostrarGruposPorClave();
        iniciar();
        break;
      case 6:
        Sistema.mostrarGruposPorNombre();
        iniciar();
        break;
      case 7:
        Sistema.imprimirAsignaturas();
        iniciar();
        break;
      case 8:
        Sistema.imprimirAlumnos();
        iniciar();
        break;
      case 9:
        Sistema.imprimirDocentes();
        iniciar();
        break;
      case 10:
        System.out.println("Cerrando sesión...");
        break;
      default:
        System.out.println("Opción no válida.");
        iniciar();
        break;
    }
  }
  
  static void nuevoGrupo(){
    Scanner input = new Scanner(System.in);
    String RFC_docente;
    int clave_asignatura=0;
    
    System.out.println("\n\nABRIR UN NUEVO GRUPO");
    System.out.print("Ingrese la clave de la asignatura:\t");
    clave_asignatura = input.nextInt();
    input.nextLine();
    if(!Sistema.asignaturas.containsKey(clave_asignatura))
      System.out.println("Esa asignatura no está dada de alta en el sistema.");
    else{
      Sistema.imprimirGrupos(clave_asignatura);
      System.out.print("\nIngrese el RFC del docente:\t");
      RFC_docente = input.nextLine();
      if(!Sistema.clave_docentes.containsKey(RFC_docente))
        System.out.println("El docente no está dado de alta en el sistema.");
      else
        Sistema.abrirGrupo(RFC_docente, Sistema.asignaturas.get(clave_asignatura));
    }
  }
}