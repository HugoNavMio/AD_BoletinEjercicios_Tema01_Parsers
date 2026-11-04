package com.hugonavarro.tema01.mains;

import com.hugonavarro.tema01.parsers.json.ParserPeliculas;
import com.hugonavarro.tema01.parsers.json.Pelicula;
import com.hugonavarro.tema01.parsers.json.Peliculas;

import java.io.IOException;
import java.util.Locale;

public class MainParserPeliculas {
    static void main() {
        ParserPeliculas mainParserPeliculas = new ParserPeliculas();
        final String archivo = "peliculas.json";

        try {
            Peliculas peliculas = mainParserPeliculas.parsearPeliculas(archivo);
            peliculas.ordenarPorMedia();

            IO.println("Películas ordenadas por puntuación media:");

            for (Pelicula pelicula : peliculas.getPeliculas()) {
                IO.println("\nTítulo: " + pelicula.getTitulo());
                IO.println("Director: " + pelicula.getDirector());
                IO.println("Estreno: " + pelicula.getEstreno());

                System.out.printf(Locale.US, "Puntuación IMDb: %.1f%n", pelicula.getPuntuaciones().getImdb());

                IO.println("Puntuacion RT: " + pelicula.getPuntuaciones().getRt());

                System.out.printf(Locale.US, "Puntuación RT normalizada: %.1f%n", pelicula.getPuntuaciones().getRtNormalizada());

                System.out.printf(Locale.US, "Puntuación media: %.2f%n", pelicula.getPuntuacionMedia());
            }

            IO.println("\nGéneros distintos ordenados alfabéticamente:");

            String[] generos = peliculas.getGenerosDistintos();

            for (String genero : generos) {
                IO.println("- " + genero);
            }
        } catch (IOException ioe) {
            System.err.println("No se ha podido leer el archivo " + archivo);
        }
    }
}
