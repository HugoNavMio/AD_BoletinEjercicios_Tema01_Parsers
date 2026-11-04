package com.hugonavarro.tema01.parsers.json;

public class Ubicacion {
    private final int pasillo;
    private final String estante;

    public Ubicacion(int pasillo, String estante) {
        this.pasillo = pasillo;
        this.estante = estante;
    }

    public int getPasillo() {
        return pasillo;
    }

    public String getEstante() {
        return estante;
    }

    @Override
    public String toString() {
        return "Ubicacion{" +
                "pasillo=" + pasillo +
                ", estante='" + estante + '\'' +
                '}';
    }
}
