/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soa.grupo2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;

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
        //2.Crear la petición deseada (GET, POST, PUT, DELETE, etc)con la clase HTTPRequest.
        try {
            HttpRequest peticionGet = HttpRequest.newBuilder()
                    .uri(new URI(ApiUrl))
                    .GET()
                    .build();
        //3.Envíar la petición utilizando el cliente HTTPClient
        //creado.
        //4.Almacenar la respuesta de la petición.
        HttpResponse<String> respuestaGet = cliente.send(peticionGet, BodyHandlers.ofString());
        
        //5.Programar en base a la respuesta de la petición.
        if (respuestaGet.statusCode() == 200) {
            String jsonRespuesta = respuestaGet.body();
            //System.out.println(jsonRespuesta);
            
            Type tipoArray = new TypeToken<ArrayList<Estudiante>>(){}.getType();
            ArrayList<Estudiante> estudianteLista = gson.fromJson(jsonRespuesta, tipoArray);
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
            HttpRequest peticionPost = HttpRequest.newBuilder()
                    .uri(new URI(ApiUrl))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(BodyPublishers.ofString(parametros))
                    .build();

            HttpResponse<String> respuesta = cliente.send(peticionPost, BodyHandlers.ofString());

            // Retornar true si el servidor devuelve 200 OK
            return respuesta.statusCode() == 200;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean eliminarEstudiantes(String cedula) {
        String urlBorrar = construirConsulta("cedula=" + cedula);
        try {
            HttpRequest peticionDelete = HttpRequest.newBuilder()
                    .uri(new URI(urlBorrar))
                    .DELETE() // DELETE sin cuerpo
                    .build();

            HttpResponse<String> respuesta = cliente.send(peticionDelete, BodyHandlers.ofString());

            // Retornar true si el servidor devuelve 200 OK
            return respuesta.statusCode() == 200;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para construir la URL con parámetros
    public String construirConsulta(String parametros) {
        return ApiUrl + "?" + parametros;
    }
    
    public boolean editarEstudiantes(Estudiante estudiante) {
        String urlEditar = construirConsulta(construirParametros(estudiante));
        try {
            HttpRequest peticionPut = HttpRequest.newBuilder()
                    .uri(new URI(urlEditar))
                    .PUT(BodyPublishers.noBody()) // DELETE sin cuerpo
                    .build();

            HttpResponse<String> respuesta = cliente.send(peticionPut, BodyHandlers.ofString());

            // Retornar true si el servidor devuelve 200 OK
            return respuesta.statusCode() == 200;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    

    // Método para construir parámetros en formato URL-encoded
    public String construirParametros(Estudiante estudiante) {
        return "cedula=" + estudiante.getCEDULA()
                + "&nombre=" + estudiante.getNOMBRE()
                + "&apellido=" + estudiante.getAPELLIDO()
                + "&direccion=" + estudiante.getDIRECCION()
                + "&telefono=" + estudiante.getTELEFONO();
    }


    
}
