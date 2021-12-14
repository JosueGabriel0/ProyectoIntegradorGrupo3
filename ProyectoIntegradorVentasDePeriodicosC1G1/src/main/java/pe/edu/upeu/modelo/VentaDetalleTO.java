package pe.edu.upeu.modelo;

import lombok.Data;

@Data
public class VentaDetalleTO {
    public String idDetVenta;
    public String idVenta;
    public String idProd;
    public int cantidad;
    public double precioUnit;
    public double precioTotal;
}