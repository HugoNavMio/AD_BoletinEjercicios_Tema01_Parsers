package com.hugonavarro.tema01.parsers.json;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Peliculas {
    private final Pelicula[] peliculas;

    public Peliculas(Pelicula[] peliculas) {
        this.peliculas = peliculas;
    }

    public Pelicula[] getPeliculas() {
        return peliculas;
    }

    public void ordenarPorMedia() {
        Arrays.sort(peliculas, (p1, p2) ->
                Double.compare(p2.getPuntuacionMedia(), p1.getPuntuacionMedia())
        );
    }

    public String[] getGenerosDistintos() {
        Set<String> generos = new TreeSet<>();

        for (Pelicula pelicula : peliculas) {
            generos.addAll(Arrays.asList(pelicula.getGeneros()));
        }

        return generos.toArray(new String[0]);
    }
}
