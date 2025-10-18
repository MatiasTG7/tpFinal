
package Vistass;

import Modeloo.Conexion;
import Modeloo.Entidad;
import Persistencia.EntidadData;
import java.util.Scanner;
import org.mariadb.jdbc.Connection;

public class VistaConsola {

    public static void main(String[] args) {
        
        Connection con = (Connection) Conexion.getConexion();
        
        if (con != null) {
            System.out.println("Conexion establecida con la base de datos.");
            EntidadData ent= new EntidadData(con);
            Scanner sc = new Scanner(System.in);

            System.out.print("Ingrese DNI: ");
            int dni = sc.nextInt();
            sc.nextLine(); 

            System.out.print("Ingrese nombre: ");
            String nombre = sc.nextLine();
            
            System.out.println("Ingrese su apellido:");
            String apellido= sc.nextLine();
            
            System.out.println("Ingrese numero de telefono:");
            int telefono = sc.nextInt();
            sc.nextLine();
            
            System.out.println("Ingrese su edad:");
            int edad = sc.nextInt();
            sc.nextLine();
            
            System.out.println("Usted tiene afecciones?");
            boolean afecciones= sc.nextBoolean();
            
            System.out.print("¿Está activo? (true/false): ");
            boolean estado = sc.nextBoolean();
            Entidad cliente1=new Entidad();
            ent.guardarCliente(cliente1);
            //-----------------------------------IMPORTANTEISHON------------------------------------------------
            //ESTO HAY QUE VERLO POR QUE EN LOS ATRIBUTOS DE ENTIDAD TIENE APELLIDO PERO EN LA BASE DE DATOS NO.
            //--------------------------------------------------------------------------------------------------
        }else{
            System.out.println("No se pudo establecer la conexion con la BD");
        }
    }
}
