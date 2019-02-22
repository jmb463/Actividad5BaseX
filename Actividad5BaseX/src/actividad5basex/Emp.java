/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad5basex;

public class Emp {
    private String codigoEmp;
    private String apellido;
    private String oficio;
    private String fechaAlta;
    private int salario;
    private int comision;
    private String codigoDpt;
    private String jefe;

    public Emp(String codigoEmp, String apellido, String oficio, String fechaAlta, int salario, int comision, String codigoDpt, String jefe) {
        this.codigoEmp = codigoEmp;
        this.apellido = apellido;
        this.oficio = oficio;
        this.fechaAlta = fechaAlta;
        this.salario = salario;
        this.comision = comision;
        this.codigoDpt = codigoDpt;
        this.jefe = jefe;
    }

    public String getCodigoDpt() {
        return codigoDpt;
    }

    public void setCodigoDpt(String codigoDpt) {
        this.codigoDpt = codigoDpt;
    }

    public String getCodigoEmp() {
        return codigoEmp;
    }
    
    public String getJefe() {
        return jefe;
    }

    public void setJefe(String jefe) {
        this.jefe = jefe;
    }

    public void setCodigoEmp(String codigoEmp) {
        this.codigoEmp = codigoEmp;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public int getComision() {
        return comision;
    }

    public void setComision(int comision) {
        this.comision = comision;
    }
    

}
