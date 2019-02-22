/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad5basex;

import java.util.ArrayList;

public class Dept {
    
    private String codigoDpt;
    private String nombreDpt;
    private String localidad;
    private ArrayList<Emp> listaEmp;
    
    public Dept(String codigoDpt, String nombreDpt, String localidad){
        this.codigoDpt = codigoDpt;
        this.nombreDpt = nombreDpt;
        this.localidad = localidad;
    }
    
    public Dept(String codigoDpt, String nombreDpt, String localidad, ArrayList<Emp> listaEmp){
        this.codigoDpt = codigoDpt;
        this.nombreDpt = nombreDpt;
        this.localidad = localidad;
        listaEmp = new ArrayList();
    }

    public ArrayList<Emp> getListaEmp() {
        return listaEmp;
    }

    public void setListaEmp(ArrayList<Emp> listaEmp) {
        this.listaEmp = listaEmp;
    }

    public String getCodigoDpt() {
        return codigoDpt;
    }

    public void setCodigoDpt(String codigoDpt) {
        this.codigoDpt = codigoDpt;
    }

    public String getNombreDpt() {
        return nombreDpt;
    }

    public void setNombreDpt(String nombreDpt) {
        this.nombreDpt = nombreDpt;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
      
}
