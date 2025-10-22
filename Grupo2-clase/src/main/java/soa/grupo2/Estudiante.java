package soa.grupo2;

import com.google.gson.annotations.SerializedName;

public class Estudiante {

    @SerializedName("CEDULA")
    private String CEDULA;

    @SerializedName("NOMBRE")
    private String NOMBRE;

    @SerializedName("APELLIDO")
    private String APELLIDO;

    @SerializedName("DIRECCION")
    private String DIRECCION;

    @SerializedName("TELEFONO")
    private String TELEFONO;

    public Estudiante(String CEDULA, String NOMBRE, String APELLIDO, String DIRECCION, String TELEFONO) {
        this.CEDULA = CEDULA;
        this.NOMBRE = NOMBRE;
        this.APELLIDO = APELLIDO;
        this.DIRECCION = DIRECCION;
        this.TELEFONO = TELEFONO;
    }

    public String getCEDULA() {
        return CEDULA;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getAPELLIDO() {
        return APELLIDO;
    }

    public void setAPELLIDO(String APELLIDO) {
        this.APELLIDO = APELLIDO;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public String getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }
}
