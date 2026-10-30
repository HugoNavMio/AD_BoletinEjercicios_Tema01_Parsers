package com.hugonavarro.tema01.parsers.json;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

public class Alumnos {
    public Alumno[] parsearAlumnos(String rutaFichero) throws IOException {
        Alumno[] alumnos = null;
        StringBuilder sb = new StringBuilder();
        try (InputStream is = getClass().getResourceAsStream("/" + rutaFichero);
             BufferedReader br = new BufferedReader(new InputStreamReader(is,
                     StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            JSONTokener tokener = new JSONTokener(sb.toString());
            JSONArray jsonArray = new JSONArray(tokener);
            alumnos = new Alumno[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectAlumno = jsonArray.getJSONObject(i);
                String id = jsonObjectAlumno.getString("id");
                String nombre = jsonObjectAlumno.getString("nombre");
                boolean matriculado = jsonObjectAlumno.getBoolean("matriculado");
                String fechaNacimientoStr = jsonObjectAlumno.getString("fechaNacimiento");
                LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
                JSONArray jsonArrayNotas = jsonObjectAlumno.getJSONArray("notas");
                Nota[] notas = new Nota[jsonArrayNotas.length()];
                for (int j = 0; j < jsonArrayNotas.length(); j++) {
                    JSONObject jsonObjectNota = jsonArrayNotas.getJSONObject(j);
                    String asignatura = jsonObjectNota.getString("asignatura");
                    double nota = jsonObjectNota.getDouble("nota");
                    notas[j] = new Nota(asignatura, nota);
                }
                alumnos[i] = new Alumno(id, nombre, matriculado, fechaNacimiento, notas);
            }
        }
        return alumnos;
    }
}