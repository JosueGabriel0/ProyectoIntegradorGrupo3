package pe.edu.upeu.app;
import pe.edu.upeu.modelo.ClienteTO;
/**
 * Hello world!
 *
 */
public class App {

    public static ClienteTO[] cliente;

    public static void ejemploCleinteVector(){
        System.out.println( "Hello Josue!" );

        cliente = new ClienteTO[3];
        
        cliente[0]=new ClienteTO("43631917", "David Mamani", "951782520");
        cliente[1]=new ClienteTO("43631918", "Juan Mamani", "951782515");
        cliente[2]=new ClienteTO("43631919", "Stive Mendoza", "951712520");
        System.out.println("DNI\t\tNombre\t\tCelular");   
        for(ClienteTO cliente : cliente){
            System.out.println(cliente.getDni()+"\t"+cliente.getNombre()+"\t\t"+cliente.getCelular());
        }
    }





    public static void main( String[] args ){
       MenuPrincipal mp=new MenuPrincipal();
       mp.mainLogin();
        
    }
}
