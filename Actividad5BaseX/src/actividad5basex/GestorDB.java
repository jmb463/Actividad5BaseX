/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad5basex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.basex.BaseXServer;
import org.basex.core.cmd.*;
import org.basex.server.ClientQuery;
import org.basex.server.ClientSession;

/**
 *
 * @author windeveloper
 */


public final class GestorDB {
    
    private static BaseXServer server;
    private static ClientSession session;
    private ArrayList<Dept> listaDpt = new ArrayList();
    private ArrayList<Emp> listaEmp = new ArrayList();
    
    public GestorDB(){
        try {
            //Crear servidor
            server = new BaseXServer();
            
            
            session = new ClientSession("localhost", 1984, "admin", "admin");
            
            // Crear base de datos
            System.out.println("\n* Creando la base de datos.");

            session.execute(new CreateDB("Empresa", "src/datos/empresa.xml"));
            
            recuperarDpt();
           
        } 
        catch (IOException ex) {
            System.out.println("No se pudo encontrar o crear la base de datos " + ex.getMessage());
        } 
    }
    
    public void cerrarDatabase(){
        try {
            //Parar el servidor
            session.close();
            server.stop();
            System.out.println(" \n La base de datos ha sido cerrada");
            
           
        } catch (IOException ex) {
            System.out.println("No se pudo cerrar la base de datos " + ex.getMessage());
        }
    }
    
    public Dept getDptSinEmp(String codigoDpt){
        Dept dept = null;
        try {       
            ClientQuery query = session.query("//dept/@codi='"+codigoDpt+"'");
            ClientQuery qNombre = session.query("//dept[@codi='"+codigoDpt+"']/nom/string()");
            ClientQuery qLocalidad = session.query("//dept[@codi='"+codigoDpt+"']/localitat/string()");
            String q1 = query.execute();
            String q2 = qNombre.execute();
            String q3 = qLocalidad.execute();
            
            
            if(q1.equalsIgnoreCase("true")){
                dept = new Dept(q1,q2,q3);
                
                System.out.println("---------Departamento "+codigoDpt+"----------");
                System.out.println("Nombre: " + dept.getNombreDpt());
                System.out.println("Localidad: " + dept.getLocalidad());
                System.out.print("");
            
                
            }
            else{
                System.out.println("El departamento no existe");
                return null;
            }
        } 
        catch (Exception ex) {
            System.out.println("No se pudo recuperar el departamento con código "+codigoDpt+"\n" + ex.getMessage());
        }
        return dept;
    }
    
    public Dept getDptConEmp(String codigoDpt) throws IOException{
        Dept dept = new Dept(codigoDpt,null,null);
        int contador = 0;
        ClientQuery query = session.query("//dept/@codi='"+codigoDpt+"'");
        String q1 = query.execute();
        getDptSinEmp(codigoDpt);
        String nombre, localidad;
        
    
        if(q1.equalsIgnoreCase("true")){   
            for(int i = 0; i<listaEmp.size();i++){
                String codigo = this.listaEmp.get(i).getCodigoDpt(); 
                if(codigo.equalsIgnoreCase(codigoDpt)){
                    nombre = dept.getNombreDpt();
                    localidad = dept.getLocalidad();
                    System.out.println("---------Empleado----------");
                    System.out.println("Código: " + this.listaEmp.get(i).getCodigoEmp());
                    System.out.println("Apellido: " + this.listaEmp.get(i).getApellido());
                    System.out.println("Codigo departamento: " + this.listaEmp.get(i).getCodigoDpt());
                    System.out.println("Fecha de alta: " + this.listaEmp.get(i).getFechaAlta());
                    System.out.println("Oficio: " + this.listaEmp.get(i).getOficio());
                    System.out.println("Salario: " + this.listaEmp.get(i).getSalario());
                    System.out.println("Comisión: " + this.listaEmp.get(i).getComision());
                    System.out.println("Codigo jefe: " + this.listaEmp.get(i).getJefe());
                    System.out.print("");
                    contador++;
                    
                    dept = new Dept(codigoDpt, nombre, localidad, listaEmp);
                    
                }
            }    
        }
        else{
            System.out.println("\n El departamento no existe");
            
        }
        
        if(contador==0){
            System.out.println("\n No hay ningún empleado en ese departamento");
        }
                    
        return dept;
    }
    
    public void insertDpt(String codigoDpt, String nombreDpt, String localidad){
        //Insertar departamento sin empleados
        try {       
            ClientQuery qCodigo = session.query("//dept/@codi='"+codigoDpt+"'");
            String q1 = qCodigo.execute(); 
                  
            if(q1.equalsIgnoreCase("false")){
                ClientQuery insert = session.query("insert node <dept codi='"+codigoDpt+"'>"
                        + "<nom>"+nombreDpt+"</nom><localitat>"+localidad+"</localitat>"
                                + "</dept>after //dept[@codi='d40']");
                insert.execute();
                
                Dept dpt = new Dept(codigoDpt,nombreDpt,localidad);
                this.listaDpt.add(dpt);
                System.out.println("Insertado el departamento con codigo "+ codigoDpt);
            }
            else{
                System.out.println("Ya existe el departamento");
            }
            
        } 
        catch (IOException ex) {
            System.out.println("No se pudo realizar con éxito el insert \n" + ex.getMessage());
        }
       
        
    }
    
    public void insertDpt(String codigoDpt, String nombreDpt, String localidad, Emp empleados[]){
        //Insertar departamento con empleados
        insertDpt(codigoDpt,nombreDpt,localidad);
        
        for(int i = 0; i<empleados.length;i++){
            try {
                ClientQuery query = session.query("//dept/@codi='"+codigoDpt+"'");
                ClientQuery idEmp = session.query("//emp/@codi='"+this.listaEmp.get(i).getCodigoEmp()+"'");
                String comprobacion = query.execute();
                String comprobacion2 = idEmp.execute();
                String qCodigo = this.listaEmp.get(i).getCodigoEmp();
                String qApellido = this.listaEmp.get(i).getApellido();
                String qFechaAlta = this.listaEmp.get(i).getFechaAlta();
                String qOficio = this.listaEmp.get(i).getOficio();
                String qSalario = String.valueOf(this.listaEmp.get(i).getSalario());
                String qComision =  String.valueOf(this.listaEmp.get(i).getComision());
                String qJefe = this.listaEmp.get(i).getJefe();
                
                if(comprobacion.equalsIgnoreCase("true")){
                    if(codigoDpt.equalsIgnoreCase(this.listaEmp.get(i).getCodigoDpt())){
                        if(qJefe.equalsIgnoreCase("null")){
                            ClientQuery insert = session.query("insert node <emp codi='"+qCodigo+"' dept='"+codigoDpt+"'><cognom>"+qApellido+"</cognom><ofici>"+qOficio+"</ofici>"
                                + "<dataAlta>"+qFechaAlta+"</dataAlta><salari>"+qSalario+"</salari><comissio>"+qComision+"</comissio></emp> after //emp[@codi='e7934']");
                            insert.execute();
                                
                        }
                        
                        else{ 
                            
                            ClientQuery insert = session.query("insert node <emp codi='"+qCodigo+"' dept='"+codigoDpt+"' cap='"+qJefe+"'><cognom>"+qApellido+"</cognom><ofici>"+qOficio+"</ofici>"       
                                + "<dataAlta>"+qFechaAlta+"</dataAlta><salari>"+qSalario+"</salari><comissio>"+qComision+"</comissio></emp> after //emp[@codi='e7934']");
                            insert.execute();
                        }
                            
                        System.out.println("Se realizó con éxito el insert del departamento "+codigoDpt+" con empleados");
                    }
                }  
            } catch (IOException ex) {
                System.out.println("No se pudo realizar con éxito el insert con empleados" + ex.getMessage());
            }
        }
        
    }
    
    public void recuperarDpt(){
        try {
            for(int i = 1; i<5;i++){
                String numeros = "d"+i+"0";
                ClientQuery qNombre = session.query("//dept[@codi='"+numeros+"']/nom/string()");
                ClientQuery qLocalidad = session.query("//dept[@codi='"+numeros+"']/localitat/string()");
                String q2 = qNombre.execute();
                String q3 = qLocalidad.execute();
                Dept dpt = new Dept(numeros,q2,q3);
                this.listaDpt.add(dpt);
                System.out.println("Recuperado el departamento con codigo "+ numeros);
            }
        } 
        catch (IOException ex) {
            System.out.println("No se pudo rellenar el array con los valores iniciales \n " + ex.getMessage());
        }
    }
    
    public ArrayList<Emp> recuperarEmp(Emp empleados[]){
        try{
            for(int i = 0; i<empleados.length;i++){
                Emp emp = empleados[i];
                listaEmp.add(emp);
            }
            System.out.println("Los empleados han sido recuperados");
            
        }
        catch(Exception ex){
            System.out.println("No se pudo recuperar el array de empleados" + ex.getMessage());
        }
        return null;
        
    }
    
    public void deleteDpt(String codigoDpt){
        try {
            ClientQuery query = session.query("delete node //dept[@codi='"+codigoDpt+"']");
            System.out.println(query.execute());
            System.out.println("Borrado el departamento " + codigoDpt);
            
            Dept dpt = new Dept(codigoDpt,null,null);
            this.listaDpt.remove(dpt);
        } catch (IOException ex) {
            System.out.println("No se pudo realizar con éxito el delete \n" + ex.getMessage());
        }
    }
    
    public void deleteDpt(String codigoDpt, Emp empleados[]){
        String qCodigo,qApellido,qFechaAlta,qOficio,qJefe;
        int qSalario,qComision;
        try{
            Scanner entrada = new Scanner(System.in);
            System.out.println("Por favor seleccione si 'eliminar' o 'reubicar' los empleados");
            String decision = entrada.next();
            if(decision.equalsIgnoreCase("eliminar")){
                for(int i = 0; i<empleados.length; i++){
                    qCodigo = this.listaEmp.get(i).getCodigoEmp();
                    qApellido = this.listaEmp.get(i).getApellido();
                    qFechaAlta = this.listaEmp.get(i).getFechaAlta();
                    qOficio = this.listaEmp.get(i).getOficio();
                    qSalario = this.listaEmp.get(i).getSalario();
                    qComision =  this.listaEmp.get(i).getComision();
                    qJefe = this.listaEmp.get(i).getJefe();
                    if(codigoDpt.equalsIgnoreCase(this.listaEmp.get(i).getCodigoDpt())){
                        
                        ClientQuery delete = session.query("delete node //emp[@codi='"+this.listaEmp.get(i).getCodigoEmp()+"']");
                        delete.execute();
                        System.out.println("Eliminado los trabajadores con apellido: " + this.listaEmp.get(i).getApellido());
                        
                        Emp emp = new Emp(qCodigo, qApellido, qOficio, qFechaAlta, qSalario, qComision, codigoDpt, qJefe);
                        this.listaEmp.remove(emp);
                        
                        
                    }
                }
                deleteDpt(codigoDpt);
            }
            else if(decision.equalsIgnoreCase("reubicar")){
                System.out.println("Escoja el codigo del departamento que quiere asignar a los trabajadores");
                decision = entrada.next();
                ClientQuery busqueda = session.query("//dept/@codi='"+decision+"'");
                String comprobacion = busqueda.execute();
                if(comprobacion.equalsIgnoreCase("true")){
                    for(int i = 0; i<empleados.length;i++){
                        qCodigo = this.listaEmp.get(i).getCodigoEmp();
                        qApellido = this.listaEmp.get(i).getApellido();
                        qFechaAlta = this.listaEmp.get(i).getFechaAlta();
                        qOficio = this.listaEmp.get(i).getOficio();
                        String Salario = String.valueOf(this.listaEmp.get(i).getSalario());
                        String Comision =  String.valueOf(this.listaEmp.get(i).getComision());
                        qJefe = this.listaEmp.get(i).getJefe();
                        if(codigoDpt.equalsIgnoreCase(this.listaEmp.get(i).getCodigoDpt())){
                            ClientQuery reubicar = session.query("replace node //emp[@codi='"+this.listaEmp.get(i).getCodigoEmp()+"'] with <emp codi='"+qCodigo+"' dept='"+decision+"' cap='"+qJefe+"'><cognom>"+qApellido+"</cognom><ofici>"+qOficio+"</ofici>"
                                    + "<dataAlta>"+qFechaAlta+"</dataAlta><salari>"+Salario+"</salari><comissio>"+Comision+"</comissio></emp>");
                            reubicar.execute();
                            this.listaEmp.get(i).setCodigoDpt(decision);
                            System.out.println(this.listaEmp.get(i).getCodigoEmp());
                        
                            System.out.println("Los empleados han sido reubicados al departamento: "+decision);
                        }
                    }
                    deleteDpt(codigoDpt);
                }
                else{
                    System.out.println("El departamento al cual quiere asignar los trabajadores no existe");
                }
            }
            else{
                System.out.println("Por favor seleccione eliminar o reubicar");
            }
        } 
        catch (IOException ex) {
            System.out.println("El delete no se pudo realizar correctamente" + ex.getMessage());
        }            
    }
    
    public void replaceDpt(Dept newDept, Emp empleados[]){
        try {
            //REPLACE node /n with <a/> (Ejemplo)
            Scanner entrada = new Scanner(System.in);
            String eleccion;
            String qCodigo = newDept.getCodigoDpt();
            String qLocalidad = newDept.getLocalidad();
            String qNombre = newDept.getNombreDpt();
            
            
            System.out.println("¿Que departamento quiere cambiar?, escriba el código por favor");
            eleccion = entrada.next();
            
            ClientQuery busqueda = session.query("//dept/@codi='"+eleccion+"'");
            String comprobacion = busqueda.execute();
            
            if(comprobacion.equalsIgnoreCase("true")){
                ClientQuery replace = session.query("replace node //dept[@codi='"+eleccion+"'] with <dept codi='"+qCodigo+"'><nom>"+qNombre+"</nom><localitat>"+qLocalidad+"</localitat></dept>");
                for(int i = 0; i<listaDpt.size();i++){
                    this.listaDpt.get(i).setCodigoDpt(newDept.getCodigoDpt());
                }
                replace.execute();
                System.out.println("Departamentos cambiados");
                    
                for(int i = 0; i<empleados.length;i++){
                    String qCodigoEmp = this.listaEmp.get(i).getCodigoEmp();
                    String qApellido = this.listaEmp.get(i).getApellido();
                    String qFechaAlta = this.listaEmp.get(i).getFechaAlta();
                    String qOficio = this.listaEmp.get(i).getOficio();
                    String qSalario = String.valueOf(this.listaEmp.get(i).getSalario());
                    String qComision =  String.valueOf(this.listaEmp.get(i).getComision());
                    String qJefe = this.listaEmp.get(i).getJefe();
                    if(eleccion.equalsIgnoreCase(this.listaEmp.get(i).getCodigoDpt())){
   
                        ClientQuery reubicar = session.query("replace node //emp[@codi='"+qCodigoEmp+"'] with <emp codi='"+qCodigoEmp+"' dept='"+qCodigo+"' cap='"+qJefe+"'><cognom>"+qApellido+"</cognom><ofici>"+qOficio+"</ofici>"
                                    + "<dataAlta>"+qFechaAlta+"</dataAlta><salari>"+qSalario+"</salari><comissio>"+qComision+"</comissio></emp>");
                        reubicar.execute();
                        this.listaEmp.get(i).setCodigoDpt(qCodigo);
                       
                        
                        System.out.println("Los empleados han sido reubicados al departamento: "+eleccion);
                    }
                }
            }
            else{
                System.out.println("Ese departamento no existe");
            }
        } 
        catch (IOException ex) {
            System.out.println("No se pudo cambiar el departamento" + ex.getMessage());
        }
    }
}
