package com.hugonavarro.tema01.parsers.json;

import java.util.Arrays;

public class Producto {
    private final String id;
    private final String nombre;
    private final int stock;
    private final double precio;
    private final String[] tags;
    private final Ubicacion ubicacion;

    public Producto(String id, String nombre, int stock, double precio, String[] tags, Ubicacion ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.tags = tags;
        this.ubicacion = ubicacion;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getStock() {
        return stock;
    }

    public double getPrecio() {
        return precio;
    }

    public String[] getTags() {
        return tags;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Producto producto)) return false;

        return id.equals(producto.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", stock=" + stock +
                ", precio=" + precio +
                ", tags=" + Arrays.toString(tags) +
                ", ubicacion=" + ubicacion +
                '}';
    }
}
