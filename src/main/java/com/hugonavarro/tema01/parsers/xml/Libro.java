package com.hugonavarro.tema01.parsers.xml;

import java.util.List;

public class Libro {
    private final String isbn;
    private final String titulo;
    private final List<String> generos;

    public Libro(String isbn, String titulo, List<String> generos) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.generos = generos;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<String> getGeneros() {
        return generos;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Libro libro)) return false;

        return isbn.equals(libro.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    @Override
    public String toString() {
        return "Libro{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", generos=" + generos +
                '}';
    }
}
