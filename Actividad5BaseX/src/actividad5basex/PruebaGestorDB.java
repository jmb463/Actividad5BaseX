/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad5basex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.*;
import org.basex.server.ClientQuery;
import org.basex.server.ClientSession;


public class PruebaGestorDB {
    public static void main(String[] args) {
        try {
            // Database context.
            Context context = new Context();
            //ClientSession session = new ClientSession("localhost", 1984, "admin", "admin");
            Emp empleado1 = new Emp("e8754", "Martin", "Programador web", "12-12-2012" , 1200, 0, "d50", "e7525");
            Emp empleado2 = new Emp("e8734", "Lliteras", "Director", "1-1-2011" , 2200, 18000, "d50", "null");
            Emp empleado3 = new Emp("e8724", "Bruno", "Analista", "2-2-2002" , 15200, 0, "d50", "e53677");
            Emp empleado4 = new Emp("e8764", "Lopez", "Camarero", "12-12-2012" , 1200, 0, "d20", "e5236");
            Emp empleado5 = new Emp("e8774", "Jimenez", "Director", "1-1-2011" , 2200, 18000, "d20", "null");
            Emp empleado6 = new Emp("e8784", "Morell", "Cocinero", "2-2-2002" , 15200, 0, "d20", "e1256");
            Emp empleado7 = new Emp("e8714", "Maldonado", "Administrador de sistemas", "2-2-2000", 1400,12,"d60","null");
            Emp empleados[] = {empleado1,empleado2,empleado3, empleado4, empleado5, empleado6, empleado7};
            Dept newDept = new Dept("d60", "Informatica", "Palma de Mallorca");
            
  
            
            // Create a client session with host name, port, user name and password
            System.out.println("\n* Create a client session.");
            
            GestorDB gdb = new GestorDB();
            /*gdb.recuperarDpt();
            //gdb.insertDpt("d50", "Informatica", "Murcia"); //Si encuentra otro objeto con el mismo codigo sobreescribira el objeto anterior
            gdb.getDptSinEmp("d40");*/
            gdb.recuperarEmp(empleados);
            gdb.insertDpt("d50", "Informatica", "Bilbao", empleados);
            gdb.getDptConEmp("d50");
            gdb.getDptConEmp("d40");
            gdb.deleteDpt("d50", empleados);
            //gdb.replaceDpt(newDept,empleados);
            gdb.getDptConEmp("d40");
            
            
            
            //gdb.insertDpt("d60","Informatica","Barcelona");
            //gdb.deleteDpt("d50");
            //gdb.getDptSinEmp();
            //gdb.deleteDpt("d60");
            gdb.cerrarDatabase();
            
            
            /*
            //Enseñar la información de la base de datos
            System.out.print(new InfoDB().execute(context));
            //Enseñar todas las bases de datos
            System.out.print(new List().execute(context) + "\n");*/
            
            
      
        } catch (Exception ex) {
            System.out.println("Error al ejecutar... " + ex.getMessage());
        }
    }
}
