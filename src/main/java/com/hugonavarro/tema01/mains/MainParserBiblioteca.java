package com.hugonavarro.tema01.mains;

import com.hugonavarro.tema01.parsers.xml.Biblioteca;
import com.hugonavarro.tema01.parsers.xml.Libro;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

public class MainParserBiblioteca {
    static void main() {
        Biblioteca mainParsearBiblioteca = new Biblioteca();
        final String archivo = "/home/usuario/IdeaProjects/SegundoTrimestre/AD_BoletinEjercicios_Tema01_Parsers/Archivos_XML_JSON/biblioteca.xml";

        try {
            Libro[] libros = mainParsearBiblioteca.parsearBiblioteca(archivo);

            IO.println("TÍTULOS DE LOS LIBROS:");
            for (Libro libro : libros) {
                IO.println("- " + libro.getTitulo());
            }

            IO.println("\nNÚMERO DE LIBROS POR GÉNERO:");
            Map<String, Integer> contadorGeneros = mainParsearBiblioteca.contarLibrosPorGenero(libros);

            for (Map.Entry<String, Integer> entrada : contadorGeneros.entrySet()) {
                IO.println("- " + entrada.getKey() + ": " + entrada.getValue());
            }
        } catch (IOException ioe) {
            System.err.println("Error: no se ha podido leer el archivo " + archivo);
        } catch (ParserConfigurationException pce) {
            System.err.println("Error al interpretar el fichero de empleados.");
        } catch (SAXException saxe) {
            System.err.println("Error al construir el DOM jerárquico de empleados.");
        }
    }
}
