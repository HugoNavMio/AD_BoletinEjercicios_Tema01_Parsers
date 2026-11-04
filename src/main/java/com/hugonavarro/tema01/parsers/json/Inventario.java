package com.hugonavarro.tema01.parsers.json;

public class Inventario {
    private final String almacen;
    private final String actualizado;
    private final Producto[] productos;

    public Inventario(String almacen, String actualizado, Producto[] producto) {
        this.almacen = almacen;
        this.actualizado = actualizado;
        this.productos = producto;
    }

    public String getAlmacen() {
        return almacen;
    }

    public String getActualizado() {
        return actualizado;
    }

    public Producto[] getProductos() {
        return productos;
    }

    public Producto buscarProducto(String identificador) {
        for (Producto producto : productos) {
            if (producto.getId().equalsIgnoreCase(identificador) || producto.getNombre().equalsIgnoreCase(identificador)) {
                return producto;
            }
        }
        return null;
    }
}
