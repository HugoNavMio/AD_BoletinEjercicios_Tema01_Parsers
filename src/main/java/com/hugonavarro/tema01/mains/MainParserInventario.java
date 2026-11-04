package com.hugonavarro.tema01.mains;

import com.hugonavarro.tema01.parsers.json.Inventario;
import com.hugonavarro.tema01.parsers.json.ParserInventario;
import com.hugonavarro.tema01.parsers.json.Producto;

import java.io.IOException;
import java.util.Scanner;

public class MainParserInventario {
    static void main() {
        ParserInventario mainParserInventario = new ParserInventario();
        final String archivo = "inventario.json";

        try (Scanner sc = new Scanner(System.in)) {
            Inventario inventario = mainParserInventario.parsearInventario(archivo);

            IO.println("Almacén: " + inventario.getAlmacen());
            IO.println("Actualizado: " + inventario.getActualizado());

            IO.print("\nIntroduzca el ID o nombre del producto: ");
            String identificador = sc.nextLine();

            Producto producto = inventario.buscarProducto(identificador);

            if (producto != null) {
                IO.println("\nProducto encontrado:");
                IO.println("  ID: " + producto.getId());
                IO.println("  Nombre: " + producto.getNombre());

                IO.println("Ubicación:");
                IO.println("  Pasillo: " + producto.getUbicacion().getPasillo());
                IO.println("  Estante: " + producto.getUbicacion().getEstante());
            } else {
                IO.println("No se pudo encontrar ningún producto con el ID o nombre que puso.");
            }
        } catch (IOException ioe) {
            System.err.println("No se ha podido leer el archivo " + archivo);
        }
    }
}
