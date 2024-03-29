public class Sistema_de_inscripcion{
  public static void main(String[] args){

  // COMENTAR A PARTIR DE LA SIGUIENTE LÍNEA PARA EJECUTAR EL PROGRAMA SIN ELEMENTOS INICIALES

    Alumnos alumno1 = new Alumnos(320304435, "Cabrera Rojas Oscar", 4058210);
    Sistema.clave_alumnos.put(320304435, alumno1);
    Sistema.alumnos_totales.add(alumno1);
    Alumnos alumno2 = new Alumnos(320287055, "Ocampo Luna Andrea Itzel", 4094552);
    Sistema.clave_alumnos.put(320287055, alumno2);
    Sistema.alumnos_totales.add(alumno2);
    Alumnos alumno3 = new Alumnos(123456789, "Alumno Genérico Cualquiera", 6);
    Sistema.clave_alumnos.put(123456789, alumno3);
    Sistema.alumnos_totales.add(alumno3);
    Alumnos alumno4 = new Alumnos(423043606, "Mejia Albavera Mariela", 4067461);
    Sistema.clave_alumnos.put(423043606, alumno4);
    Sistema.alumnos_totales.add(alumno4);
    Alumnos alumno5 = new Alumnos(304516012, "Espejel Ornelas Irvin Giovanni", 4034402);
    Sistema.clave_alumnos.put(304516012, alumno5);
    Sistema.alumnos_totales.add(alumno5);
    Alumnos alumno6 = new Alumnos(364017415, "López Katt Edmundo", 4056361);
    Sistema.clave_alumnos.put(364017415, alumno6);
    Sistema.alumnos_totales.add(alumno6);
    Alumnos alumno7 = new Alumnos(303791324, "Ramírez del Prado Monte Rosa Evaristo", 4050146);
    Sistema.clave_alumnos.put(303791324, alumno7);
    Sistema.alumnos_totales.add(alumno7);

    Profesores profesor1 = new Profesores("LOHJ041001MH2", "Jhonathan Israel López Hernández", 4067984);
    Sistema.clave_docentes.put("LOHJ041001MH2", profesor1);
    Sistema.docentes_totales.add(profesor1);
    Profesores profesor2 = new Profesores("ABCD123456EF7", "Profesor Genérico Cualquiera", 6);
    Sistema.clave_docentes.put("ABCD123456EF7", profesor2);
    Sistema.docentes_totales.add(profesor2);
    Profesores profesor3 = new Profesores("CAMG798434BE8", "Gabriela Carpio Mendoza", 40376969);
    Sistema.clave_docentes.put("CAMG798434BE8", profesor3);
    Sistema.docentes_totales.add(profesor3);
    Profesores profesor4 = new Profesores("CLZR468911HJ8", "Clemencia Zapata Reyna", 40401237);
    Sistema.clave_docentes.put("CLZR468911HJ8", profesor4);
    Sistema.docentes_totales.add(profesor4);
    Profesores profesor5 = new Profesores("ALCE450189LA3", "Alcalá Correa Elliette", 40375869);
    Sistema.clave_docentes.put("ALCE450189LA3", profesor5);
    Sistema.docentes_totales.add(profesor5);

    Materias asignatura1 = new Materias("Ecuaciones Diferenciales");
    Sistema.asignaturas_totales.add(asignatura1);
    Sistema.asignaturas.put(asignatura1.getClave(), asignatura1);
    Sistema.clave_asignaturas.put(asignatura1.getNombre(), asignatura1.getClave());
    Materias asignatura2 = new Materias("Termodinamica");
    Sistema.asignaturas_totales.add(asignatura2);
    Sistema.asignaturas.put(asignatura2.getClave(), asignatura2);
    Sistema.clave_asignaturas.put(asignatura2.getNombre(), asignatura2.getClave());
    Materias asignatura3 = new Materias("Programacion Orientada a Objetos");
    Sistema.asignaturas_totales.add(asignatura3);
    Sistema.asignaturas.put(asignatura3.getClave(), asignatura3);
    Sistema.clave_asignaturas.put(asignatura3.getNombre(), asignatura3.getClave());
    Materias asignatura4 = new Materias("Estadistica");
    Sistema.asignaturas_totales.add(asignatura4);
    Sistema.asignaturas.put(asignatura4.getClave(), asignatura4);
    Sistema.clave_asignaturas.put(asignatura4.getNombre(), asignatura4.getClave());

    Sistema.abrirGrupo(profesor1.getUsuario(), Sistema.asignaturas.get(asignatura1.getClave()));
    Sistema.abrirGrupo(profesor4.getUsuario(), Sistema.asignaturas.get(asignatura1.getClave()));
    Sistema.abrirGrupo(profesor2.getUsuario(), Sistema.asignaturas.get(asignatura1.getClave()));
    Sistema.abrirGrupo(profesor5.getUsuario(), Sistema.asignaturas.get(asignatura1.getClave()));
    Sistema.abrirGrupo(profesor5.getUsuario(), Sistema.asignaturas.get(asignatura2.getClave()));
    Sistema.abrirGrupo(profesor5.getUsuario(), Sistema.asignaturas.get(asignatura2.getClave()));
    Sistema.abrirGrupo(profesor3.getUsuario(), Sistema.asignaturas.get(asignatura2.getClave()));
    Sistema.abrirGrupo(profesor3.getUsuario(), Sistema.asignaturas.get(asignatura2.getClave()));
    Sistema.abrirGrupo(profesor3.getUsuario(), Sistema.asignaturas.get(asignatura2.getClave()));
    Sistema.abrirGrupo(profesor1.getUsuario(), Sistema.asignaturas.get(asignatura3.getClave()));
    Sistema.abrirGrupo(profesor2.getUsuario(), Sistema.asignaturas.get(asignatura3.getClave()));
    Sistema.abrirGrupo(profesor3.getUsuario(), Sistema.asignaturas.get(asignatura3.getClave()));
    Sistema.abrirGrupo(profesor4.getUsuario(), Sistema.asignaturas.get(asignatura3.getClave()));
    Sistema.abrirGrupo(profesor5.getUsuario(), Sistema.asignaturas.get(asignatura3.getClave()));

    Sistema.altaGrupo(alumno1, asignatura1, 1, false);
    Sistema.altaGrupo(alumno1, asignatura2, 5, false);
    Sistema.altaGrupo(alumno2, asignatura3, 3, false);
    Sistema.altaGrupo(alumno2, asignatura2, 2, false);
    Sistema.altaGrupo(alumno4, asignatura3, 4, false);
    Sistema.altaGrupo(alumno5, asignatura1, 1, false);
    Sistema.altaGrupo(alumno5, asignatura2, 5, false);
    Sistema.altaGrupo(alumno5, asignatura3, 4, false);
    Sistema.altaGrupo(alumno6, asignatura1, 1, false);
    Sistema.altaGrupo(alumno6, asignatura2, 3, false);
    Sistema.altaGrupo(alumno6, asignatura3, 5, false);
    Sistema.altaGrupo(alumno7, asignatura1, 4, false);

  // COMENTAR HASTA LA ANTERIOR LÍNEA PARA EJECUTAR EL PROGRAMA SIN ELEMENTOS INICIALES


    Sistema.inicioSesion();
  }
}