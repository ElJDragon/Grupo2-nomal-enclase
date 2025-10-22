/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soa.grupo2;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 *
 * @author ramir
 */
public class ClienteEstudiantes {

    private final Gson gson = new Gson();
    //1.Crear un cliente HTTPClient.
    private final HttpClient cliente = HttpClient.newHttpClient();
    private final String ApiUrl = "http://localhost/SOA/api.php";

    public ArrayList<Estudiante> obtenerEstudiantes() {
        System.out.println("Obteniendo estudiantes desde la API...");
        try {
            HttpRequest peticionGet = HttpRequest.newBuilder()
                    .uri(new URI(ApiUrl))
                    .GET()
                    .build();
        HttpResponse<String> respuestaGet = cliente.send(peticionGet, BodyHandlers.ofString());
        
        if (respuestaGet.statusCode() == 200) {
            String jsonRespuesta = respuestaGet.body();
            System.out.println("Respuesta API (GET): " + jsonRespuesta);
            
            Type tipoArray = new TypeToken<ArrayList<Estudiante>>(){}.getType();
            ArrayList<Estudiante> estudianteLista = gson.fromJson(jsonRespuesta, tipoArray);
            System.out.println("Estudiantes encontrados: " + (estudianteLista != null ? estudianteLista.size() : "null"));
            return estudianteLista;
            
        } 
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
        return null;
    }
    
    public boolean registrarestudiantes(Estudiante estudiante) {
        String parametros = construirParametros(estudiante);
        try {
            System.out.println("Enviando POST con parámetros: " + parametros);
            
            // Ensure parameters are properly built
            if (parametros.isEmpty()) {
                System.out.println("Error: Parámetros vacíos");
                return false;
            }
            
            HttpRequest peticionPost = HttpRequest.newBuilder()
                    .uri(new URI(ApiUrl))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(BodyPublishers.ofString(parametros))
                    .build();

            HttpResponse<String> respuesta = cliente.send(peticionPost, BodyHandlers.ofString());
            
            System.out.println("Respuesta del servidor (POST): " + respuesta.body());
            System.out.println("Código de estado: " + respuesta.statusCode());

            // Retornar true si el servidor devuelve 200 OK y respuesta no está vacía
            boolean exitoso = respuesta.statusCode() == 200 && !respuesta.body().isEmpty();
            if (!exitoso) {
                System.out.println("Error: La operación no fue exitosa. Código: " + respuesta.statusCode());
            }
            return exitoso;

        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean eliminarEstudiante(String cedula){
        String urlBorrar = construirConsulta("cedula="+cedula);
        try{
            //Construir peticion DELETE
            HttpRequest peticionDelete = HttpRequest.newBuilder()
                    .uri(new URI(urlBorrar))
                    .DELETE()
                    .build();
            //Enviar la peticion con el cliente
            HttpResponse<String> respuesta = cliente.send(peticionDelete, BodyHandlers.ofString());
            if (respuesta.statusCode()==200) {
                return true;
            }
        } catch(Exception e){
            System.out.println(e);
            
        }
        return false;
    }
    public boolean editarEstudiante(Estudiante estudiante){
        // La API espera los datos como parámetros de URL, no en el cuerpo
        String urlEditar = ApiUrl + "?cedula=" + estudiante.getCEDULA() +
                "&nombre=" + estudiante.getNOMBRE() +
                "&apellido=" + estudiante.getAPELLIDO() +
                "&direccion=" + estudiante.getDIRECCION() +
                "&telefono=" + estudiante.getTELEFONO();
        
        try{
            System.out.println("Enviando PUT a: " + urlEditar);
            
            // Construir peticion PUT
            HttpRequest peticionPut = HttpRequest.newBuilder()
                    .uri(new URI(urlEditar))
                    .PUT(BodyPublishers.noBody())
                    .build();
                    
            // Enviar la peticion con el cliente
            HttpResponse<String> respuesta = cliente.send(peticionPut, BodyHandlers.ofString());
            System.out.println("Respuesta del servidor (update): " + respuesta.body());
            System.out.println("Código de estado: " + respuesta.statusCode());
            
            if (respuesta.statusCode()==200) {
                return true;
            }
        } catch(Exception e){
            System.out.println("Error al actualizar: " + e);
            e.printStackTrace();
        }
        return false;
    }
    public String construirConsulta(String parametros){
        return ApiUrl +"?"+parametros;
    }
    public String construirParametros(Estudiante estudiante){
        try {
            // Codificar los valores para URL
            String cedula = java.net.URLEncoder.encode(estudiante.getCEDULA(), "UTF-8");
            String nombre = java.net.URLEncoder.encode(estudiante.getNOMBRE(), "UTF-8");
            String apellido = java.net.URLEncoder.encode(estudiante.getAPELLIDO(), "UTF-8");
            String direccion = java.net.URLEncoder.encode(estudiante.getDIRECCION(), "UTF-8");
            String telefono = java.net.URLEncoder.encode(estudiante.getTELEFONO(), "UTF-8");
            
            return "cedula="+cedula
                    + "&nombre="+nombre
                    + "&apellido="+apellido
                    + "&direccion="+direccion
                    + "&telefono="+telefono;
        } catch (Exception e) {
            System.out.println("Error al codificar parámetros: " + e.getMessage());
            return "";
        }
    }
    
}
