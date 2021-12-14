package pe.edu.upeu.modelo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PreCompraTO {
   public String  idPreCompra;
   public String idProv;
   public String fecha;
   public String idProd;
   public int cantidad;
   public double precio;
   public int cantDevol;
   public double totalPago;
   public int estado;
}
