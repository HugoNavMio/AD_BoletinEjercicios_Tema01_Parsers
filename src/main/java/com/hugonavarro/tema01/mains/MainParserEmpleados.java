package com.hugonavarro.tema01.mains;

import com.hugonavarro.tema01.parsers.xml.Empleado;
import com.hugonavarro.tema01.parsers.xml.Empleados;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class MainParserEmpleados {
    static void main() {
        Empleados mainParsearEmpleados = new Empleados();
        final String archivo = "/home/usuario/IdeaProjects/SegundoTrimestre/AD_BoletinEjercicios_Tema01_Parsers/Archivos_XML_JSON/empleados.xml";

        try {
            Empleado[] empleados = mainParsearEmpleados.parsearEmpleados(archivo);
            for (Empleado empleado : empleados) {
                IO.println(empleado);
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
