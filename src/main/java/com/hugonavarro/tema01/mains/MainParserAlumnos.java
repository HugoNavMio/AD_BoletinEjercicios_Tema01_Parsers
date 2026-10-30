package com.hugonavarro.tema01.mains;

import com.hugonavarro.tema01.parsers.json.Alumno;
import com.hugonavarro.tema01.parsers.json.Alumnos;

import java.io.IOException;

public class MainParserAlumnos {
    static void main() {
        Alumnos mainParsearAlumnos = new Alumnos();
        final String archivo = "alumnos.json";

        try {
            Alumno[] alumnos = mainParsearAlumnos.parsearAlumnos(archivo);
            for (Alumno alumno : alumnos) {
                IO.println(alumno);
            }
        } catch (IOException ioe) {
            System.err.println("No se ha podido leer el archivo " + archivo);
        }
    }
}
