package com.hugonavarro.tema01.mains;

import com.hugonavarro.tema01.parsers.xml.Item;
import com.hugonavarro.tema01.parsers.xml.Pedido;
import com.hugonavarro.tema01.parsers.xml.Pedidos;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class MainParserPedidos {
    static void main() {
        Pedidos mainParsearPedidos = new Pedidos();
        final String archivo = "/home/usuario/IdeaProjects/SegundoTrimestre/AD_BoletinEjercicios_Tema01_Parsers/Archivos_XML_JSON/pedidos.xml";

        try (Scanner sc = new Scanner(System.in)) {
            Pedido[] pedidos = mainParsearPedidos.parsearPedidos(archivo);

            IO.print("Introduzca el identificador del pedido que quieras consultar: ");
            String idBuscado = sc.nextLine().trim();

            Pedido pedidoSeleccionado = null;

            for (Pedido pedido : pedidos) {
                if (pedido.getId().equalsIgnoreCase(idBuscado)) {
                    pedidoSeleccionado = pedido;
                    break;
                }
            }

            if (pedidoSeleccionado == null) {
                IO.println("No se pudo encontrar ningún pedido con el identificador: " + idBuscado);
            }

            assert pedidoSeleccionado != null;
            IO.println("\nPEDIDO: " + pedidoSeleccionado.getId());
            IO.println("Cliente: " + pedidoSeleccionado.getNombreCliente());
            IO.println("Fecha: " + pedidoSeleccionado.getFecha());

            IO.println("\nÍTEMS DEL PEDIDO:");

            for (Item item : pedidoSeleccionado.getItems()) {
                IO.println("- " + item.getDescripcion());
                IO.println("- SKU: " + item.getSku());
                IO.println("- Cantidad: " + item.getCantidad());
                IO.println("- Precio unitario: " + item.getPrecioUnitario().toPlainString() + " " + pedidoSeleccionado.getMoneda());
                IO.println("- Subtotal: " + item.getSubtotal().toPlainString() + " " + pedidoSeleccionado.getMoneda());
            }

            BigDecimal totalCalculado = pedidoSeleccionado.calcularTotal();
            BigDecimal totalXML = pedidoSeleccionado.getTotal();

            IO.println("\nVALIDACIÓN DEL TOTAL:");
            IO.println("Total recalculado: " + totalCalculado.toPlainString() + " " + pedidoSeleccionado.getMoneda());
            IO.println("Total indicado en XML: " + totalXML.toPlainString() + " " + pedidoSeleccionado.getMoneda());

            if (totalCalculado.compareTo(totalXML) == 0) {
                IO.println("Resultado: CORRECTO. Los dos totales coinciden.");
            } else {
                IO.println("Resultado: INCORRECTO. Los totales no coinciden.");
                IO.println("Diferencia: " + totalCalculado.subtract(totalXML).toPlainString() + " " + pedidoSeleccionado.getMoneda());
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
