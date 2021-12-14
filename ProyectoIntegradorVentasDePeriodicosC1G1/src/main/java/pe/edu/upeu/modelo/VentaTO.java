package pe.edu.upeu.modelo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VentaTO {
  public String idVenta;
  public String dni;
  public String fecha;
  public double subtotal;
  public double descuento;
  public double totalimporte;
    
}