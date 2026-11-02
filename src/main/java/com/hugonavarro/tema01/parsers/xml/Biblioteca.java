package com.hugonavarro.tema01.parsers.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Biblioteca {
    public Libro[] parsearBiblioteca(String rutaFichero) throws IOException, ParserConfigurationException, SAXException {
        Libro[] libros = null;

        try (FileInputStream fis = new FileInputStream(rutaFichero)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(fis);
            dom.getDocumentElement().normalize();

            NodeList items = dom.getElementsByTagName("libro");
            libros = new Libro[items.getLength()];

            for (int i = 0; i <  items.getLength(); i++) {
                Element libroEtiqueta = (Element) items.item(i);

                String isbn = libroEtiqueta.getAttribute("isbn");
                String titulo = libroEtiqueta.getElementsByTagName("titulo").item(0).getTextContent();

                NodeList generosXML = libroEtiqueta.getElementsByTagName("genero");
                List<String> generos = new ArrayList<>();

                for (int j = 0; j < generosXML.getLength(); j++) {
                    generos.add(generosXML.item(j).getTextContent());
                }

                libros[i] = new Libro(isbn, titulo, generos);
            }
        }

        return libros;
    }

    public Map<String, Integer> contarLibrosPorGenero(Libro[] libros) {
        Map<String, Integer> contadorGeneros = new LinkedHashMap<>();

        for (Libro libro : libros) {
            for (String genero : libro.getGeneros()) {
                contadorGeneros.put(genero, contadorGeneros.getOrDefault(genero, 0) + 1);
            }
        }

        return contadorGeneros;
    }
}
