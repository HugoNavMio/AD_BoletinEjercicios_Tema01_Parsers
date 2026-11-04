package com.hugonavarro.tema01.parsers.json;

public class Puntuaciones {
    private final double imdb;
    private final int rt;

    public Puntuaciones(double imdb, int rt) {
        this.imdb = imdb;
        this.rt = rt;
    }

    public double getImdb() {
        return imdb;
    }

    public int getRt() {
        return rt;
    }

    public double getRtNormalizada() {
        return rt / 10.0;
    }

    public double getPuntuacionMedia() {
        return (imdb + getRtNormalizada()) / 2;
    }

    @Override
    public String toString() {
        return "Puntuaciones{" +
                "imdb=" + imdb +
                ", rt=" + rt +
                ", rtNormalizada=" + getRtNormalizada() +
                ", media=" + getPuntuacionMedia() +
                '}';
    }
}
