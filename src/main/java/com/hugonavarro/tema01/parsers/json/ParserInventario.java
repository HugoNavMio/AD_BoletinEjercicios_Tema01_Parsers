package com.hugonavarro.tema01.parsers.json;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ParserInventario {
    public Inventario parsearInventario(String rutaFichero) throws IOException {
        Inventario inventario = null;
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
            JSONObject jsonObjectInventario = new JSONObject(tokener);

            String almacen = jsonObjectInventario.getString("almacen");
            String actualizado = jsonObjectInventario.getString("actualizado");

            JSONArray jsonArrayProductos = jsonObjectInventario.getJSONArray("productos");
            Producto[] productos = new Producto[jsonArrayProductos.length()];

            for (int i = 0; i < jsonArrayProductos.length(); i++) {
                JSONObject jsonObjectProdcuto = jsonArrayProductos.getJSONObject(i);

                String id = jsonObjectProdcuto.getString("id");
                String nombre = jsonObjectProdcuto.getString("nombre");
                int stock = jsonObjectProdcuto.getInt("stock");
                double precio = jsonObjectProdcuto.getDouble("precio");

                JSONArray jsonArrayTags = jsonObjectProdcuto.getJSONArray("tags");
                String[] tags = new String[jsonArrayTags.length()];

                for (int j = 0; j < jsonArrayTags.length(); j++) {
                    tags[j] = jsonArrayTags.getString(j);
                }

                JSONObject jsonObjectUbicacion = jsonObjectProdcuto.getJSONObject("ubicacion");
                int pasillo = jsonObjectUbicacion.getInt("pasillo");
                String estante = jsonObjectUbicacion.getString("estante");

                Ubicacion ubicacion = new Ubicacion(pasillo, estante);
                productos[i] = new Producto(id, nombre, stock, precio, tags, ubicacion);
            }

            inventario = new Inventario(almacen, actualizado, productos);
        }

        return inventario;
    }
}
