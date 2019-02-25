/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad5basex;

import java.io.IOException;

public class PruebaGestorDB {
    public static void main(String[] args) {
        try {
            
            // Insertar empleados
            
            Emp empleado1 = new Emp("e7369", "Martin", "Programador web", "12-12-2012" , 1200, 0, "d50", "e7525");
            Emp empleado2 = new Emp("e7499", "Lliteras", "Director", "1-1-2011" , 2200, 18000, "d30", "null");
            Emp empleado3 = new Emp("e7521", "Bruno", "Analista", "2-2-2002" , 15200, 0, "d50", "e53677");
            Emp empleado4 = new Emp("e7566", "Lopez", "Camarero", "12-12-2012" , 1200, 0, "d20", "e5236");
            Emp empleado5 = new Emp("e7654", "Jimenez", "Director", "1-1-2011" , 2200, 18000, "d20", "null");
            Emp empleado6 = new Emp("e7698", "Morell", "Cocinero", "2-2-2002" , 15200, 0, "d40", "e1256");
            Emp empleado7 = new Emp("e7782", "Maldonado", "Administrador de sistemas", "2-2-2000", 1400,12,"d10","null");
            Emp empleados[] = {empleado1,empleado2,empleado3, empleado4, empleado5, empleado6, empleado7};
            
            // Crear un nuevo departamento
            Dept newDept = new Dept("d60", "Informatica", "Palma de Mallorca");
            
            //Abrir base de datos
            GestorDB gdb = new GestorDB();
            
            //Metiendo todos los datos del XML en el ArrayList
            gdb.recuperarEmp(empleados);
            
           /* System.out.println("-----------------INSERT SIN EMPLEADOS-------------------");
            gdb.insertDpt("d50", "Informatica", "Murcia");
            gdb.getDptSinEmp("d50");
                       
            System.out.println("-----------------DELETE SIN EMPLEADOS-------------------");
            gdb.deleteDpt("d50");
            gdb.getDptConEmp("d50");*/
                        
            System.out.println("-----------------INSERT CON EMPLEADOS-------------------");
            gdb.insertDpt("d50", "Informatica", "Bilbao", empleados);
            gdb.getDptConEmp("d50");
            
            System.out.println("-----------------DELETE CON EMPLEADOS-------------------");
            gdb.getDptConEmp("d20");
            gdb.deleteDpt("d50", empleados);
            gdb.getDptConEmp("d50");
            
            /*System.out.println("-----------------REPLACE-------------------");
            gdb.getDptConEmp("d40");
            gdb.replaceDpt(newDept,empleados);
            gdb.getDptConEmp("d60");*/

            gdb.cerrarDatabase();
                         
        } catch (IOException ex) {
            System.out.println("Error al ejecutar... " + ex.getMessage());
        }
    }
}
