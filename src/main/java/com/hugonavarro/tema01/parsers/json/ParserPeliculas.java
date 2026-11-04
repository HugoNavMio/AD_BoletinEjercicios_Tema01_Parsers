package com.hugonavarro.tema01.parsers.json;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ParserPeliculas {
    public Peliculas parsearPeliculas(String rutaFichero) throws IOException {
        Pelicula[] peliculas = null;
        StringBuilder sb = new StringBuilder();

        try (
                InputStream is = getClass().getResourceAsStream("/" + rutaFichero);
                BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
        ) {
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            JSONTokener tokener = new JSONTokener(sb.toString());
            JSONObject jsonObjectPeliculas = new JSONObject(tokener);

            JSONArray jsonArrayPeliculas = jsonObjectPeliculas.getJSONArray("peliculas");
            peliculas = new Pelicula[jsonArrayPeliculas.length()];
            for (int i = 0; i < jsonArrayPeliculas.length(); i++) {
                JSONObject jsonObjectPelicula = jsonArrayPeliculas.getJSONObject(i);

                String id = jsonObjectPelicula.getString("id");
                String titulo = jsonObjectPelicula.getString("titulo");
                String director = jsonObjectPelicula.getString("director");
                int estreno = jsonObjectPelicula.getInt("estreno");
                int duracionMin = jsonObjectPelicula.getInt("duracionMin");

                JSONArray jsonArrayGeneros = jsonObjectPelicula.getJSONArray("generos");
                String[] generos = new String[jsonArrayGeneros.length()];
                for (int j = 0; j < jsonArrayGeneros.length(); j++) {
                    generos[j] = jsonArrayGeneros.getString(j);
                }

                JSONObject jsonObjectPuntuaciones = jsonObjectPelicula.getJSONObject("puntuaciones");

                double imdb = jsonObjectPuntuaciones.getDouble("imdb");
                int rt = jsonObjectPuntuaciones.getInt("rt");

                Puntuaciones puntuaciones = new Puntuaciones(imdb, rt);

                peliculas[i] = new Pelicula(id, titulo, director, estreno, duracionMin, generos, puntuaciones);
            }
        }

        return new Peliculas(peliculas);
    }
}
