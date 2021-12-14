package pe.edu.upeu.modelo;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductoTO {
    public String idProd;
    public String nombre;
    public String tipo;
    public double precio;
    public double utilidad;
    public int stock;
    public String unidadMed;

    
}
