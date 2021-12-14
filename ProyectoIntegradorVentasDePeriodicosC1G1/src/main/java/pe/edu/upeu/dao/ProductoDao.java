package pe.edu.upeu.dao;

import pe.edu.upeu.data.AppCrud;
import pe.edu.upeu.modelo.ProductoTO;
import pe.edu.upeu.utils.LeerArchivo;
import pe.edu.upeu.utils.LeerTeclado;
import pe.edu.upeu.utils.UtilsX;

public class ProductoDao extends AppCrud {

    LeerTeclado leerTecla=new LeerTeclado();
    UtilsX util=new UtilsX();
    final String TABLA_PRODUCTO="Producto.txt";

    LeerArchivo leerArch;
    ProductoTO prodTO;

    String tipoProd="Periodico\nRevista\n";



    public Object[][] crearProducto(){
        util.clearConsole();
        leerArch=new LeerArchivo(TABLA_PRODUCTO);    
        prodTO=new ProductoTO();
        prodTO.setIdProd(generarId(leerArch, 0, "P", 1));
        prodTO.setNombre(leerTecla.leer("", "Ingrese nombre del Producto"));
        prodTO.setPrecio(leerTecla.leer(0.0, "Ingrese precio base del Producto"));
        prodTO.setUnidadMed(leerTecla.leer("", "Ingrese unidad de medidad"));
        prodTO.setTipo(leerTecla.leer("", "Ingrese el tipo ("+tipoProd+")"));
        prodTO.setUtilidad(leerTecla.leer(0.0, "Ingrese la utilidad Ejem: 0.02"));
        prodTO.setStock(leerTecla.leer(0, "Ingrese el Stock"));
        leerArch=new LeerArchivo(TABLA_PRODUCTO);           
        return agregarContenido(leerArch, prodTO); 
    }

    public void reportarProductos() {
        util.clearConsole();
        leerArch=new LeerArchivo(TABLA_PRODUCTO);
       Object[][] data= listarContenido(leerArch);
       util.pintarLine('H', 26);
       util.pintarTextHeadBody('H', 3, "ID,Nombre,Precio,Stock");
       System.out.println("");        
       util.pintarLine('H', 26);
       String dataPrint="";
       for (int i = 0; i < data.length; i++) {            
            dataPrint=data[i][0]+","+data[i][1]+","+data[i][3]+","+data[i][5];
            util.pintarTextHeadBody('B', 3, dataPrint);   
       }
       util.pintarLine('H', 26);
       System.out.println();
    }

    public void reportarProductos(Object[][] data) {
        util.pintarLine('H', 26);
        util.pintarTextHeadBody('H', 3, "ID,Nombre,Precio,Stock");
        System.out.println("");        
        util.pintarLine('H', 26);
        String dataPrint="";
        for (int i = 0; i < data.length; i++) {            
             dataPrint=data[i][0]+","+data[i][1]+","+data[i][3]+","+data[i][5];
             util.pintarTextHeadBody('B', 3, dataPrint);   
        }
        util.pintarLine('H', 26);
        System.out.println();
     }    

    public void updateProducto(){
        util.clearConsole();
        reportarProductos();
        String dataId=leerTecla.leer("","Ingrese el Id del Producto");
        prodTO=new ProductoTO();
        prodTO.setPrecio(leerTecla.leer(0.0,"Ingrese el nuevo precio"));
        prodTO.setUtilidad(leerTecla.leer(0.0,"Ingrese la nueva utilidad"));
        leerArch=new LeerArchivo(TABLA_PRODUCTO);
        Object[][] data= editarRegistro(leerArch,0, dataId, prodTO);
        util.clearConsole();
        reportarProductos(data);


    }

    public void deleteProducto(){
        util.clearConsole();
        reportarProductos();
        String dataId=leerTecla.leer("","Ingrese el Id del Producto");
        leerArch=new LeerArchivo(TABLA_PRODUCTO);
        Object[][] data= eliminarRegistros(leerArch, 0, dataId);
        util.clearConsole();
        reportarProductos(data);


    }




    
}
