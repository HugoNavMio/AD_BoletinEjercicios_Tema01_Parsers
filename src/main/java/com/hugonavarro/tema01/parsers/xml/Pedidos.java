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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedidos {
    public Pedido[] parsearPedidos(String rutaFichero) throws IOException, ParserConfigurationException, SAXException {
        Pedido[] pedidos = null;

        try (FileInputStream fis = new FileInputStream(rutaFichero)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(fis);
            dom.getDocumentElement().normalize();

            NodeList items = dom.getElementsByTagName("pedido");
            pedidos = new Pedido[items.getLength()];

            for (int i = 0; i < items.getLength(); i++) {
                Element pedidoEtiqueta = (Element) items.item(i);

                String id = pedidoEtiqueta.getAttribute("id");
                String nombreCliente = pedidoEtiqueta.getElementsByTagName("nombre").item(0).getTextContent();
                String fechaCliente = pedidoEtiqueta.getElementsByTagName("fecha").item(0).getTextContent();
                LocalDate fecha = LocalDate.parse(fechaCliente);

                NodeList itemsXML = pedidoEtiqueta.getElementsByTagName("item");
                List<Item> listaItems = new ArrayList<>();

                for (int j = 0; j < itemsXML.getLength(); j++) {
                    Element itemEtiqueta = (Element) itemsXML.item(j);

                    String sku = itemEtiqueta.getAttribute("sku");
                    String descripcion = itemEtiqueta.getElementsByTagName("descripcion").item(0).getTextContent();
                    int cantidad = Integer.parseInt(itemEtiqueta.getElementsByTagName("cantidad").item(0).getTextContent());
                    Element precioElemento = (Element) itemEtiqueta.getElementsByTagName("precioUnitario").item(0);
                    BigDecimal precioUnitario = new BigDecimal(precioElemento.getTextContent());
                    listaItems.add(new Item(sku, descripcion, cantidad, precioUnitario));
                }

                Element totalElemento = (Element) pedidoEtiqueta.getElementsByTagName("total").item(0);
                String moneda = totalElemento.getAttribute("moneda");
                BigDecimal total = new BigDecimal(totalElemento.getTextContent());
                pedidos[i] = new Pedido(id, nombreCliente, fecha, listaItems, moneda, total);
            }
        }

        return pedidos;
    }
}
