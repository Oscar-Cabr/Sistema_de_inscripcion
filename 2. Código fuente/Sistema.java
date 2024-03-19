import java.util.Scanner;
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.HashSet;

public class Sistema{
  static Hashtable<Integer, Materias> asignaturas = new Hashtable<Integer, Materias>(); //Acceder a cualquier asignatura (objeto), conociendo su clave.
  static Hashtable<String, Integer> clave_asignaturas = new Hashtable<String, Integer>(); //Buscar la clave de cualquier asignatura por su nombre.
  static HashSet<Materias> asignaturas_totales = new HashSet<Materias>(); //Imprimir fácilmente todas las asignaturas.

  static Hashtable<Integer, Alumnos> clave_alumnos = new Hashtable<Integer, Alumnos>(); //Almacenar todos los alumnos dados de alta en el sistema. Buscarlos por No. Cuenta.
  static HashSet<Alumnos> alumnos_totales = new HashSet<Alumnos>(); //Imprimir de manera fácil y ordenada (por clave) todas las asignaturas.

  static Hashtable<String, Profesores> clave_docentes = new Hashtable<String, Profesores>(); //Almacenar todos los profesores dados de alta en el sistema. Buscarlos por RFC.
  static HashSet<Profesores> docentes_totales = new HashSet<Profesores>(); //Almacenar todos los docentes dados de alta en el sistema.

  static final String CONTRASEÑA = "contra"; //Contraseña de acceso al sistema.

//MÉTODO DEL MENÚ PRINCIPAL

  public static void inicioSesion(){
      Scanner input = new Scanner(System.in);
      int option;

      System.out.print("\n\nBienvenido. Por favor, selecciona tu opción.\n");
      System.out.println("1. Alumno");
      System.out.println("2. Docente.");
      System.out.println("3. Administrador.");
      System.out.print("4. Terminar.\n\t");
      option = input.nextInt();
      switch(option){
          case 1:
              iniciarAlumno();
              inicioSesion();
              break;
          case 2:
              iniciarDocente();
              inicioSesion();
              break;
          case 3:
              iniciarAdmin();
              inicioSesion();
              break;
          case 4:
              System.out.println("Saliendo...");
              System.exit(0);
              break;
        default:
              System.out.println("Selección no válida.");
              inicioSesion();
              break;
      }
    
  }

  //MÉTODOS PARA INSTANCIAR UN ALUMNO Y PROCEDER CON SUS OPCIONES
  
  private static void iniciarAlumno(){
      Scanner input = new Scanner(System.in);
      int option;

      System.out.println("\n\nALUMNO");
      System.out.println("1. Ingresar.");
      System.out.println("2. Registrar nueva cuenta.");
      System.out.println("3. Olvidé mi contraseña.");
      System.out.print("4. Regresar.\n\t");
      option = input.nextInt();
      switch(option){
          case 1:
              ingresarAlumno();
              inicioSesion();
              break;
          case 2:
              crearAlumno();
              inicioSesion();
              break;
          case 3:
              olvidoDeContraseña(true);
              inicioSesion();
              break;
          case 4:
              System.out.println("Regresando al menú...");
              inicioSesion();
              break;
          default:
              System.out.println("Selección no válida.");
              iniciarAlumno();
              break;
      }
  }
  
  private static void ingresarAlumno(){
      Scanner input = new Scanner(System.in);

      System.out.print("\n\nIngresa tu usuario (número de cuenta):\t");
      Integer usuario = input.nextInt();
      while(!(usuario > 99999999 && usuario < 1000000000)){
        System.out.print("Valor inválido, ingrese de nuevo:\t");
        usuario = Integer.valueOf(input.nextInt());
      }
      if(clave_alumnos.containsKey(usuario)){
          System.out.print("Ingresa tu contraseña:\t");
          int clave = input.nextInt();
          if(clave == clave_alumnos.get(usuario).getContraseña())
              clave_alumnos.get(usuario).opciones();
          else{
              System.out.println("Contraseña incorrecta.");
              iniciarAlumno();
          }
      }
      else
          System.out.println("El usuario no existe.");
}

  public static void crearAlumno(){
      Scanner input = new Scanner(System.in);
      String nombre_alumno;
      Integer numero_cuenta, contraseña_acceso;
    
      System.out.println("\n\nRegistrando una cuenta de alumno.");
      System.out.print("Ingrese el nombre:\t");
      nombre_alumno = input.nextLine();
      System.out.print("Ingrese el número de cuenta:\t");
      numero_cuenta = Integer.valueOf(input.nextInt());
      while(!(numero_cuenta > 99999999 && numero_cuenta < 1000000000)){
        System.out.print("Valor inválido, ingrese de nuevo:\t");
        numero_cuenta = Integer.valueOf(input.nextInt());
      }
      if(!clave_alumnos.containsKey(numero_cuenta)){
          contraseña_acceso = generarContraseña();
          System.out.print("La contraseña de acceso es la siguiente:\t"+contraseña_acceso);;
          Alumnos alumno = new Alumnos(numero_cuenta, nombre_alumno, contraseña_acceso);
          clave_alumnos.put(numero_cuenta, alumno);
          alumnos_totales.add(alumno);
      }
      else
          System.out.println("La cuenta ya está registrada.");
  }
  
  private static int generarContraseña(){ //Se genera la contraseña de la misma forma en alumnos como docentes.
      Scanner input = new Scanner(System.in);
      int D, M, A;
      
      System.out.println("\nIngrese la fecha de nacimiento (DD MM AAAA)");
      System.out.print("Dia:\t");
      D = input.nextInt();
      while(!(D > 0 && D < 32)){
        System.out.print("Valor inválido. Ingresa de nuevo:\t");
        D = input.nextInt();
      }

      System.out.print("Mes:\t");
      M = input.nextInt();
      while(!(M > 0 && M < 13)){
        System.out.print("Valor inválido. Ingresa de nuevo:\t");
        M = input.nextInt();
      }

      System.out.print("Año:\t");
      A =  input.nextInt();
      while(!(A > 0 && A < 2025)){
        System.out.print("Valor inválido. Ingresa de nuevo:\t");
        A = input.nextInt();
      }
      int contraseña = ((D + M + A) * (D + M + A) - (D + M + A));

      return contraseña;
  }
  
  private static boolean isValid(String val,int len){
      if(val.length() == len){
        return true;
      }
      return false;
  }
  
  private static void olvidoDeContraseña(boolean es_alumno){ //Método adaptado para funcionar con alumnos o docentes.
      Scanner input = new Scanner(System.in);

      if(es_alumno){
        System.out.print("\nIngresa tu usuario (número de cuenta) para recuperar tu contraseña:\t");
        Integer usuario = input.nextInt();
        while(!(usuario > 99999999 && usuario < 1000000000)){
          System.out.print("Valor inválido, ingrese de nuevo:\t");
          usuario = Integer.valueOf(input.nextInt());
        }
        if(clave_alumnos.containsKey(usuario)){
            System.out.println(clave_alumnos.get(usuario).getNombre()+", tu contraseña es: "+(clave_alumnos.get(usuario)).getContraseña()+". No la pierdas.");
            iniciarAlumno();
        }
        else{
            System.out.println("El usuario no existe.");
            iniciarAlumno();
        }
      }
      else{
        System.out.print("Ingresa tu usuario (RFC) para recuperar tu contraseña:\t");
        String usuario = input.nextLine();
        while(!isValid(usuario, 13)){
          System.out.print("Valor inválido, ingrese de nuevo:\t");
          usuario = input.nextLine();
        }
        if(clave_docentes.containsKey(usuario)){
            System.out.println(clave_docentes.get(usuario).getNombre()+", tu contraseña es: "+clave_docentes.get(usuario).getClave()+". No la pierdas.");
            iniciarDocente();
        }
        else{
            System.out.println("El usuario no existe.");
            iniciarDocente();
        }
      }
  }

  //MÉTODOS PARA INSTANCIAR UN DOCENTE Y PROCEDER CON SUS OPCIONES
  
  private static void iniciarDocente(){
    Scanner input = new Scanner(System.in);
    int option;

    System.out.println("\n\nDOCENTE");
    System.out.println("1. Ingresar.");
    System.out.println("2. Registrar nueva cuenta.");
    System.out.println("3. Olvidé mi contraseña.");
    System.out.print("4. Regresar.\n\t");
    option = input.nextInt();
    switch(option){
        case 1:
            ingresarDocente();
            inicioSesion();
            break;
        case 2:
            crearDocente();
            inicioSesion();
            break;
        case 3:
            olvidoDeContraseña(false);
            inicioSesion();
            break;
        case 4:
            System.out.println("Regresando al menú...");
            inicioSesion();
            break;
        default:
            System.out.println("Selección no válida.");
            iniciarDocente();
            break;
    }
  }
  
  private static void ingresarDocente(){
    Scanner input = new Scanner(System.in);

    System.out.print("\nIngrese el usuario (RFC):\t");
    String usuario = input.nextLine();
    while(!isValid(usuario, 13)){
      System.out.print("Valor inválido, ingrese de nuevo:\t");
      usuario = input.nextLine();
    }
    if(clave_docentes.containsKey(usuario)){
        System.out.print("Ingrese la contraseña:\t");
        int clave = input.nextInt();
        if(clave == clave_docentes.get(usuario).getClave())
            clave_docentes.get(usuario).opciones();
        else{
            System.out.println("Contraseña incorrecta.");
            iniciarDocente();
        }
    }
    else
        System.out.println("El usuario no existe.");
  }

  public static void crearDocente(){
    Scanner input = new Scanner(System.in);
    String nombre_docente, cuenta_rfc="";
    Integer contraseña_acceso;

    System.out.println("\n\nRegistrando una cuenta de docente.");
    System.out.print("Ingrese el nombre:\t");
    nombre_docente = input.nextLine();
    while (nombre_docente == null) {
      System.out.println("Ingresa al menos un caracter");
      nombre_docente = input.nextLine();
    }
    System.out.print("Ingrese el usuario (RFC a 13 caracteres):\t");
    cuenta_rfc = input.nextLine();
    while(!isValidRFC(cuenta_rfc)){
      System.out.print("RFC inválido, ingrese nuevamente:\t");
      cuenta_rfc = input.nextLine();
    }
    if(!clave_docentes.containsKey(cuenta_rfc)){
        contraseña_acceso = generarContraseña();
        System.out.print("La contraseña de acceso es la siguiente:\t"+contraseña_acceso);;
        Profesores profesor = new Profesores(cuenta_rfc, nombre_docente, contraseña_acceso);
        clave_docentes.put(cuenta_rfc, profesor);
        docentes_totales.add(profesor);
    }
    else
        System.out.println("La cuenta ya está registrada.");

  
  }

  public static boolean isValidRFC(String RFC){
    return RFC.length() == 13;
  }

  //MÉTODO PARA INICIAR COMO ADMINISTRADOR Y PROCEDER CON SUS ACCIONES
  
  public static void iniciarAdmin(){
      Scanner input = new Scanner(System.in);
      String contraseña;
    
      System.out.println("\n\nADMINISTRADOR");
      System.out.print("Por favor, ingresa la contraseña para ingresar como administrador.\t");
      contraseña = input.nextLine();
      if(contraseña.equals(CONTRASEÑA)){
          Admin.iniciar();
          inicioSesion();
      }
      else{
          System.out.println("Contraseña incorrecta.");
          inicioSesion();
      }
  }

  //REQUESTS DE CUALQUIERA DE LOS TRES A INFORMACIÓN GENERAL EN EL SISTEMA
  
  public static void imprimirAlumnos(){
    if(alumnos_totales.isEmpty())
      System.out.println("No hay alumnos inscritos.");
    else{
      System.out.println("\nTodos los alumnos inscritos en el sistema son:\n");
      for(Alumnos alumno: alumnos_totales)
          System.out.print("Nombre: "+alumno.getNombre()+"\tNúmero de cuenta: "+alumno.getNcuenta()+"\n");
    }
  }
  
  public static void imprimirDocentes(){
    if(docentes_totales.isEmpty())
      System.out.println("No hay docentes dados de alta en el sistema.");
    else{
      System.out.println("\nTodos los docentes dados de alta en el sistema son:");
      for(Profesores docente : docentes_totales){
          System.out.print("\n\nNombre: "+docente.getNombre()+"\tUsuario (RFC):\t"+docente.getUsuario()+"\nImparte las materias:");
          for(List<Integer> grupo_impartido : docente.getGruposImpartidos())
            for(int i = 0; i < grupo_impartido.size(); i++){
              if(!(i==0))
                System.out.print(grupo_impartido.get(i)+" ");
              else
                System.out.print("\n\tAsignatura: "+asignaturas.get(grupo_impartido.get(i)).getNombre()+"\tGrupos: ");
            }
      }
    }
  }
  
  public static void imprimirAsignaturas(){
    if(asignaturas_totales.isEmpty())
      System.out.println("No hay asignaturas dadas de alta en el sistema.");
    else{
      System.out.println("\nTodas las asignaturas dadas de alta en el sistema son:");
      for(Materias asignatura: asignaturas_totales){
          System.out.print("\nASIGNATURA - "+asignatura.getNombre()+".\tCLAVE - "+asignatura.getClave()+".\n");
          for(Grupos grupo: asignatura.getGrupos())
              System.out.println("\tGrupo "+grupo.getNumeroDeGrupo()+".\tProfesor: "+grupo.getProfesor()+".\tAlumnos inscritos: "+grupo.getAlumnosInscritos()+".");
      }
    }
  }
  
  public static void imprimirGrupos(int clave){
    if(!asignaturas.containsKey(clave))
      System.out.println("La asignatura no está dada de alta en el sistema.");
    else{
      System.out.print("\nASIGNATURA - "+asignaturas.get(clave).getNombre()+".\tCLAVE - "+asignaturas.get(clave).getClave()+".\tGRUPOS:");
      if(!asignaturas.get(clave).getGrupos().isEmpty())
        for(Grupos grupo: asignaturas.get(clave).getGrupos())
            System.out.println("\n\tGrupo "+grupo.getNumeroDeGrupo()+".\tProfesor: "+grupo.getProfesor()+".\tAlumnos inscritos: "+grupo.getAlumnosInscritos()+".");
    }
  }
  
  public static void imprimirGrupos(String nombre_asignatura){
    if(asignaturas.containsKey(clave_asignaturas.get(nombre_asignatura))){
      System.out.print("\nASIGNATURA - "+asignaturas.get(clave_asignaturas.get(nombre_asignatura)).getNombre()+"\tCLAVE - "+asignaturas.get(clave_asignaturas.get(nombre_asignatura)).getClave()+"\tGRUPOS:");
      if(!asignaturas.get(clave_asignaturas.get(nombre_asignatura)).getGrupos().isEmpty())
        for(Grupos grupo: asignaturas.get(clave_asignaturas.get(nombre_asignatura)).getGrupos())
          System.out.print("\tGrupo: "+grupo.getNumeroDeGrupo()+".\tProfesor: "+grupo.getProfesor()+".\tAlumnos inscritos: "+grupo.getAlumnosInscritos()+".");
    }
    else
      System.out.println("La asignatura no está dada de alta en el sistema.");
  }
  
  public static void mostrarGruposPorClave(){
    Scanner input = new Scanner(System.in);
    int clave_asignatura;

    System.out.print("\nIngrese la clave de la asignatura de la que quieres ver los grupos: ");
    clave_asignatura = input.nextInt();
    imprimirGrupos(clave_asignatura);
  }
  
  public static void mostrarGruposPorNombre(){
    Scanner input = new Scanner(System.in);
    String nombre_asignatura;

    System.out.print("\nIngrese el nombre de la asignatura de la que quieres ver los grupos: ");
    nombre_asignatura = input.nextLine();
    if(clave_asignaturas.containsKey(nombre_asignatura))
      imprimirGrupos(clave_asignaturas.get(nombre_asignatura));
    else
      System.out.println("La asignatura no está dada de alta en el sistema.");
  }

  //MÉTODOS PARA MANEJOR DE GRUPOS Y ASIGNATURAS 

  public static void crearAsignatura(){
    Scanner input = new Scanner(System.in);
    String nombre_nueva_asignatura;
    
    System.out.println("\n\nREGISTRAR ASGIGNATURA");
    System.out.print("Ingrese el nombre de la asignatura:\t");
    nombre_nueva_asignatura = input.nextLine().trim();
    
    Materias asignatura = new Materias(nombre_nueva_asignatura);
    asignaturas_totales.add(asignatura);
    asignaturas.put(asignatura.getClave(), asignatura);
    clave_asignaturas.put(asignatura.getNombre(), asignatura.getClave());
    
    System.out.println("La asignatura "+asignatura.getNombre()+" ha sido registrada con éxito");
    System.out.print("La clave de la asignatura es:\t"+asignatura.getClave());
  }

  public static void abrirGrupo(String rfc_docente, Materias asignatura){
    Profesores profesor = clave_docentes.get(rfc_docente);
    Grupos grupo = new Grupos(0, asignatura.getGrupos().size()+1, profesor.getNombre());
    List<Integer> clave_grupo;
    boolean ya_imparte = false;
    int index = 0;

    asignatura.getGrupos().add(grupo);

    if(!profesor.getGruposImpartidos().isEmpty()){
      for(int i = 0; i < profesor.getGruposImpartidos().size(); i++){
        clave_grupo = profesor.getGruposImpartidos().get(i);
        if(clave_grupo.get(0) == asignatura.getClave()){
          ya_imparte = true;
          index = i;
        }
      }
      if(ya_imparte)
        profesor.getGruposImpartidos().get(index).add(grupo.getNumeroDeGrupo());
      else{
        List<Integer> nueva_clave_grupo = new LinkedList<Integer>();
        nueva_clave_grupo.add(asignatura.getClave());
        nueva_clave_grupo.add(grupo.getNumeroDeGrupo());
        profesor.getGruposImpartidos().add(nueva_clave_grupo);
      }
    }
    else{
      List<Integer> nueva_clave_grupo = new LinkedList<Integer>();
      nueva_clave_grupo.add(asignatura.getClave());
      nueva_clave_grupo.add(grupo.getNumeroDeGrupo());
      profesor.getGruposImpartidos().add(nueva_clave_grupo);
    }
  }

  public static void altaGrupo(Alumnos alumno, Materias asignatura, int grupo, boolean mostrar_mensaje){
    int[] array_grupo = {asignatura.getClave(), grupo};
    boolean se_puede_dar_alta = true;

    for(int[] asignaturas : alumno.getGruposInscritos())
      if(asignaturas[0] == asignatura.getClave()){
        System.out.println("Ya estás inscrito un grupo de la asignatura.");
        se_puede_dar_alta = false;
      }
    if(se_puede_dar_alta){
      asignatura.getGrupos().get(grupo-1).getAlumnos().add(alumno.getNcuenta());
      alumno.getGruposInscritos().add(array_grupo);
      asignatura.getGrupos().get(grupo-1).setAlumnosInscritos(asignatura.getGrupos().get(grupo-1).getAlumnosInscritos()+1);
      if(mostrar_mensaje)
        System.out.println("Te has inscrito satisfactoriamente.");
    }
  }

  public static void bajaGrupo(Alumnos alumno, Materias asignatura, int grupo, boolean mostrar_mensaje){
    List<Integer> alumnos;
    int index_alumno = 0;

    for(int i = 0; i < asignatura.getGrupos().get(grupo-1).getAlumnos().size() ; i++){
      alumnos = asignatura.getGrupos().get(grupo-1).getAlumnos();
      if(clave_alumnos.get(alumnos.get(i)).getNcuenta() == alumno.getNcuenta())
        index_alumno = i;
    }
    asignatura.getGrupos().get(grupo-1).getAlumnos().remove(index_alumno); //*
    int[] asignaturas;
    for(int i = 0; i < alumno.getGruposInscritos().size(); i++){
      asignaturas = alumno.getGruposInscritos().get(i);
      if(asignaturas[0] == asignatura.getClave()){
        alumno.getGruposInscritos().remove(i);
        if(mostrar_mensaje)
          System.out.println("Te has desinscrito satisfactoriamente.");
        break;
      }
    }
  }

  public static void cambiarGrupo(Alumnos alumno, Materias asignatura, int grupo_viejo, int grupo_nuevo){
    bajaGrupo(alumno, asignatura, grupo_viejo, false);
    altaGrupo(alumno, asignatura, grupo_nuevo, false);
  }

  public static boolean aprobarSolicitud(String RFC_profesor){
    return true;
  }
}