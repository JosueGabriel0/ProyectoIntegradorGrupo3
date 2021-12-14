package pe.edu.upeu.app;

import pe.edu.upeu.dao.ProductoDao;
import pe.edu.upeu.dao.UsuarioDao;
import pe.edu.upeu.dao.VentaDao;
import pe.edu.upeu.utils.LeerTeclado;

public class MenuPrincipal {
    LeerTeclado lt=new LeerTeclado();   
    ProductoDao prodDao;
    UsuarioDao uDao;
    VentaDao ventDao;


    public void mainLogin(){
      uDao=new UsuarioDao();
      if (uDao.login()) {

        menuOpciones();

        
      }else{
        System.out.println("Intente Nuevamente!!");
        mainLogin();
      }

    }


    public void menuOpciones() {        
        int opcionesA=0;
        System.out.println("-----------------------Bienvenido al Sistema --------------------");
        String msg="\nEliga una Opcion"+ 
        "\n1=Crear Producto"+
        "\n2=Listar Producto"+
        "\n3=Editar Producto"+
        "\n4=Eliminar Producto"+
        "\n5=Crear Nuevo Usuario"+
        "\n6=Realizar una venta"+
        "\n7=Reportar Ventas"+
        "\n";        
        opcionesA=lt.leer(0, msg);  
        while(opcionesA!=0){
            switch(opcionesA) {

              case 1:{ 
                    prodDao=new ProductoDao();
                    prodDao.crearProducto();
                    } break;

            
              case 2: {
                  prodDao=new ProductoDao();
                  prodDao.reportarProductos();
              } break;  
              
              
              case 3: {
                prodDao=new ProductoDao();
                prodDao.updateProducto();
            } break; 
            

              case 4: {
                prodDao=new ProductoDao();
                prodDao.deleteProducto();
            } break;


            case 5: {
              uDao=new UsuarioDao();
              uDao.crearUsuario();
          } break;

            case 6: {
              ventDao=new VentaDao();
              ventDao.registrarVenta();
            } break;

            case 7: {
              ventDao=new VentaDao();
              ventDao.reporteVentasRangoFecha();
            } break;

              default: System.out.println("La opcion ingresada no existe!");
            }   
          System.out.println("----------------------------------------------------------------"); //15.511.210.043.330.985.984.000.000        
          opcionesA=lt.leer(0,msg);        
        }        
    }
}
