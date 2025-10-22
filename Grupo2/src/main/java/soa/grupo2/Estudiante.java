package soa.grupo2;

import com.google.gson.annotations.SerializedName;

public class Estudiante {

    @SerializedName("cedula")
    private String CEDULA;

    @SerializedName("nombre")
    private String NOMBRE;

    @SerializedName("apellido")
    private String APELLIDO;

    @SerializedName("direccion")
    private String DIRECCION;

    @SerializedName("telefono")
    private String TELEFONO;

    // Constructor
    public Estudiante(String CEDULA, String NOMBRE, String APELLIDO, String DIRECCION, String TELEFONO) {
        this.CEDULA = CEDULA;
        this.NOMBRE = NOMBRE;
        this.APELLIDO = APELLIDO;
        this.DIRECCION = DIRECCION;
        this.TELEFONO = TELEFONO;
    }

    // Getters y Setters
    public String getCEDULA() { return CEDULA; }

    public String getNOMBRE() { return NOMBRE; }
    public void setNOMBRE(String NOMBRE) { this.NOMBRE = NOMBRE; }

    public String getAPELLIDO() { return APELLIDO; }
    public void setAPELLIDO(String APELLIDO) { this.APELLIDO = APELLIDO; }

    public String getDIRECCION() { return DIRECCION; }
    public void setDIRECCION(String DIRECCION) { this.DIRECCION = DIRECCION; }

    public String getTELEFONO() { return TELEFONO; }
    public void setTELEFONO(String TELEFONO) { this.TELEFONO = TELEFONO; }
}
