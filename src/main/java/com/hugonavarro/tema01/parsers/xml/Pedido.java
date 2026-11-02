package com.hugonavarro.tema01.parsers.xml;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Pedido {
    private final String id;
    private final String nombreCliente;
    private final LocalDate fecha;
    private final List<Item> items;
    private final String moneda;
    private final BigDecimal total;

    public Pedido(String id, String nombreCliente, LocalDate fecha, List<Item> items, String moneda, BigDecimal total) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.fecha = fecha;
        this.items = items;
        this.moneda = moneda;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getMoneda() {
        return moneda;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal calcularTotal() {
        BigDecimal totalCalculado = BigDecimal.ZERO;

        for (Item item : items) {
            BigDecimal subtotal = item.getPrecioUnitario().multiply(BigDecimal.valueOf(item.getCantidad()));
            totalCalculado = totalCalculado.add(subtotal);
        }

        return totalCalculado;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Pedido pedido)) return false;

        return id.equals(pedido.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id='" + id + '\'' +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", fecha=" + fecha +
                ", items=" + items +
                ", moneda='" + moneda + '\'' +
                ", total=" + total +
                '}';
    }
}
