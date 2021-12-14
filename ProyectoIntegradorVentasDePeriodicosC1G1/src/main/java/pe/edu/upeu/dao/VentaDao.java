package pe.edu.upeu.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import pe.edu.upeu.data.AppCrud;
import pe.edu.upeu.modelo.ClienteTO;
import pe.edu.upeu.modelo.ProductoTO;
import pe.edu.upeu.modelo.VentaDetalleTO;
import pe.edu.upeu.modelo.VentaTO;
import pe.edu.upeu.utils.LeerArchivo;
import pe.edu.upeu.utils.LeerTeclado;
import pe.edu.upeu.utils.UtilsX;

public class VentaDao extends AppCrud{
    LeerTeclado leerTecla=new LeerTeclado();
    UtilsX util=new UtilsX();

    Ansi color=new Ansi();

    final String TABLA_VENTAS="Ventas.txt";
    final String TABLA_VENTA_DETALLE="VentaDetalle.txt";
    final String TABLA_PRODUCTO="Producto.txt";
    final String TABLA_CLIENTE="Cliente.txt";

    LeerArchivo leerArch;

    ProductoTO prodTO;
    VentaTO venTO;
    VentaDetalleTO ventaDetalleTO;
    ClienteTO clienteTO;

    SimpleDateFormat formatofechahora = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    SimpleDateFormat formatofecha = new SimpleDateFormat("dd-MM-yyyy");


    public void registrarVenta() {
        double subtotalXX=0;
        double totalImporteXX=0;
        double descuentoXX=0;
 
        VentaTO ventTo=crearVenta();
        String opcion="SI";
        if(ventTo!=null){
            util.clearConsole();
            System.out.println("**********Agregar productos a carrito de ventas**********");
             do {
                 VentaDetalleTO dataVD=carritoVenta(ventTo);   
                 subtotalXX=subtotalXX+dataVD.getPrecioTotal();             
                 opcion=leerTecla.leer("", "Desea agregar un producto mas? SI/NO");
             } while (opcion.toUpperCase().equals("SI"));
 
             //Actualizar Tabla Ventas
             if(leerTecla.leer("SI", "Desea aplicar descuento? SI/NO").toUpperCase().equals("SI")){
                 //por implementar
 
             }else{
                 descuentoXX=0;
             }
             totalImporteXX=subtotalXX-descuentoXX;
             ventTo.setDescuento(descuentoXX);
             ventTo.setSubtotal(subtotalXX);
             ventTo.setTotalimporte(totalImporteXX);
 
             leerArch=new LeerArchivo(TABLA_VENTAS);
             editarRegistro(leerArch, 0, ventTo.getIdVenta(), ventTo);            
        }
     }
 
     public VentaTO crearVenta() {
         leerArch=new LeerArchivo(TABLA_VENTAS);
 
         venTO=new VentaTO();
         venTO.setIdVenta(generarId(leerArch, 0, "V", 1));
         String dnix=leerTecla.leer("", "Ingrese el Dni del Cliente");
         venTO.setDni(crearCliente(dnix));
         Date fecha=new Date();
         venTO.setFecha(formatofechahora.format(fecha));
         venTO.setSubtotal(0);
         venTO.setDescuento(0);
         venTO.setTotalimporte(0);
         
         leerArch=new LeerArchivo(TABLA_VENTAS);
         agregarContenido(leerArch, venTO);
 
         leerArch=new LeerArchivo(TABLA_VENTAS);
         if(buscarContenido(leerArch, 0, venTO.getIdVenta()).length==1){
             return venTO;
         }else{ 
             System.out.println("Intente nuevamente:");           
             return crearVenta();
         }               
     }
 
     public String crearCliente(String dni) {
         leerArch=new LeerArchivo(TABLA_CLIENTE);
         Object[][] dataCli=null;
         dataCli=buscarContenido(leerArch, 0, dni);
         System.out.println("VER:"+dataCli.length);
         if(dataCli==null || dataCli.length==0){
            if(dataCli.length==0){
                ClienteDao cDao=new ClienteDao();
                cDao.crearCliente(dni);                
            }
             return dni;

         }else{
             return dni;
         }       
     }
 
     public VentaDetalleTO carritoVenta(VentaTO venT) {
         mostrarProductos();
         ventaDetalleTO=new VentaDetalleTO();
         leerArch=new LeerArchivo(TABLA_VENTA_DETALLE);
 
         ventaDetalleTO.setIdDetVenta(generarId(leerArch, 0, "DV", 2));
         ventaDetalleTO.setIdVenta(venT.getIdVenta());
         ventaDetalleTO.setIdProd(leerTecla.leer("", "Ingrese Id del Producto a vender"));
         leerArch=new LeerArchivo(TABLA_PRODUCTO);
         Object[][] prodData=buscarContenido(leerArch, 0, ventaDetalleTO.getIdProd());
         if(prodData!=null){
             double precioX=Double.parseDouble(String.valueOf(prodData[0][3]))
             +Double.parseDouble(String.valueOf(prodData[0][4]));
         ventaDetalleTO.setPrecioUnit(precioX);
         }
         ventaDetalleTO.setCantidad(leerTecla.leer(0, "Ingrese la cantidad"));
         ventaDetalleTO.setPrecioTotal(ventaDetalleTO.getPrecioUnit()*ventaDetalleTO.getCantidad());        
         leerArch=new LeerArchivo(TABLA_VENTA_DETALLE);
         agregarContenido(leerArch, ventaDetalleTO);
 
         return ventaDetalleTO;
     }
 
     public void mostrarProductos() {
         leerArch=new LeerArchivo(TABLA_PRODUCTO);
         Object[][] data=listarContenido(leerArch);
         for (int i = 0; i < data.length; i++) {
             System.out.print(data[i][0]+"="+data[i][1]+
             "(Precio:"+
             (
             Double.parseDouble(String.valueOf(data[i][3]))+
             Double.parseDouble(String.valueOf(data[i][4]))
             )
             +" / Stock: "+ data[i][5]+") |\t");
         }
         System.out.println("\n");
     }


     public void reporteVentasRangoFecha(){
            util.clearConsole();
            System.out.println("============================Registro Ventas============================");
            String fechaInit=leerTecla.leer("", "Ingrese F. Inicio (dd-MM-yyyy)");
            String fechaFinal=leerTecla.leer("", "Ingrese F. Final (dd-MM-yyyy)");
            leerArch=new LeerArchivo(TABLA_VENTAS);
            Object[][] dataV=listarContenido(leerArch);
            int contadorVRD=0;
            try {
                //Para saber que cantidad de registros coinciden con el rango de fecha
                for (int i = 0; i < dataV.length; i++) {
                    String[] fechaVector=String.valueOf(dataV[i][2]).split(" ");
                    Date fechaVentaX=formatofecha.parse(fechaVector[0]);
                    if(
                    (fechaVentaX.after(formatofecha.parse(fechaInit)) || fechaInit.equals(fechaVector[0])) && 
                    (fechaVentaX.before(formatofecha.parse(fechaFinal)) || fechaFinal.equals(fechaVector[0]))
                    ){
                        contadorVRD++;
                    }
                }

                //Pasar los datos a un Vetor de tipo VentaTO segun Rango Fechas
                VentaTO[] dataReal=new VentaTO[contadorVRD];
                int indiceVector=0;
                for (int i = 0; i < dataV.length; i++) {
                    String[] fechaVector=String.valueOf(dataV[i][2]).split(" ");
                    Date fechaVentaX=formatofecha.parse(fechaVector[0]);
                    if(
                    (fechaVentaX.after(formatofecha.parse(fechaInit)) || fechaInit.equals(fechaVector[0])) && 
                    (fechaVentaX.before(formatofecha.parse(fechaFinal)) || fechaFinal.equals(fechaVector[0]))
                    ){
                      VentaTO vTo=new VentaTO();
                      vTo.setIdVenta(dataV[i][0].toString());
                      vTo.setDni(dataV[i][1].toString());
                      vTo.setFecha(dataV[i][2].toString());
                      vTo.setDescuento(Double.parseDouble(String.valueOf(dataV[i][4])));
                      vTo.setSubtotal(Double.parseDouble(String.valueOf(dataV[i][3])));
                      vTo.setTotalimporte(Double.parseDouble(String.valueOf(dataV[i][5])));
                        dataReal[indiceVector]=vTo;
                        indiceVector++;
                    }
                }
                //Imprimir Ventas
                AnsiConsole.systemInstall();

                System.out.println(color.bgBrightYellow().fgBlack()
                .a("============================Reporte Ventas entre "+fechaInit+" Y "+fechaFinal+"============================").reset());
                util.pintarLine('H' , 40);
                util.pintarTextHeadBody('H', 3, "IdVenta, DNI cliente, Fecha, Sub. total, Descuento, Imp.Total");
                System.out.println("");
                double subtotalX=0, descuentoX=0, importeTX=0;
                util.pintarLine('H' , 40);
                for (VentaTO TOv : dataReal) {
                    String datax=TOv.getIdVenta()+","+TOv.getDni()+","+TOv.getFecha()+","+
                    TOv.getSubtotal()+","+TOv.getDescuento()+","+TOv.getTotalimporte();
                    subtotalX+=TOv.getSubtotal(); 
                    descuentoX+=TOv.getDescuento(); 
                    importeTX+=TOv.getTotalimporte();
                    util.pintarTextHeadBody('B', 3, datax);
                }
                //System.out.println("");
                color=new Ansi();
                util.pintarLine('H' , 40);
                System.out.println(color.render(

                "| @|red Sub. Total: |@ | @|blue S/."+subtotalX+" |@ | @|red Descuento: |@ @|blue S/."+descuentoX+
                "|@ | @|red Imp. Total: |@ @|blue S/."+importeTX+"|@")
                );
                util.pintarLine('H' , 40);
                

            } catch (Exception e) {
                System.err.println("Error al Reportar!!"+e.getMessage());
            }


     }      
}
