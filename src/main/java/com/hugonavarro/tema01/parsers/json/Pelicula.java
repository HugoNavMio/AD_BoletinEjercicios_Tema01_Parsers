package com.hugonavarro.tema01.parsers.json;

import java.util.Arrays;

public class Pelicula {
    private final String id;
    private final String titulo;
    private final String director;
    private final int estreno;
    private final int duracionMin;
    private final String[] generos;
    private final Puntuaciones puntuaciones;

    public Pelicula(String id, String titulo, String director, int estreno, int duracionMin, String[] generos, Puntuaciones puntuaciones) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.estreno = estreno;
        this.duracionMin = duracionMin;
        this.generos = generos;
        this.puntuaciones = puntuaciones;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDirector() {
        return director;
    }

    public int getEstreno() {
        return estreno;
    }

    public int getDuracionMin() {
        return duracionMin;
    }

    public String[] getGeneros() {
        return generos;
    }

    public Puntuaciones getPuntuaciones() {
        return puntuaciones;
    }

    public double getPuntuacionMedia() {
        return puntuaciones.getPuntuacionMedia();
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Pelicula pelicula)) return false;

        return id.equals(pelicula.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", director='" + director + '\'' +
                ", estreno=" + estreno +
                ", duracionMin=" + duracionMin +
                ", generos=" + Arrays.toString(generos) +
                ", puntuaciones=" + puntuaciones +
                '}';
    }
}
