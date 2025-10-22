<?php
include_once "conn.php";

class CRUD
{

    public static function select()
    {
        $connect = new Conexion();
        $conectat = $connect->connect();
        //se crea la consulta
        $sqlSelect = "SELECT * FROM estudiantes";
        //preparar la consulta
        //el prepare es para evitar inyecciones SQL porque
        //si se pone directamente en el execute puede ser peligroso 
        $result = $conectat->prepare($sqlSelect);
        // el execute ejecuta la consulta
        $result->execute();
        //fetchAll obtiene todos los registros
        // PDO::FETCH_ASSOC obtiene solo los nombres de las columnas
        // ejemplo: array("CEDULA"=>"123456","NOMBRE"=>"Juan")
        $data = $result->fetchAll(PDO::FETCH_ASSOC);

        echo json_encode($data);
    }

    public static function insert()
    {
        $connect = new Conexion();
        $conectat = $connect->connect();

        // se obtienen los datos enviados por POST esto se puede
        // cambiar por los datos que se quieran enviar
        // en este caso se envían los datos del estudiante
        $cedula = $_POST['CEDULA'];
        $nombre = $_POST['NOMBRE'];
        $apellido = $_POST['APELLIDO'];
        $direccion = $_POST['DIRECCION'];
        $telefono = $_POST['TELEFONO'];

        
        // se crea la consulta
        $sqlInsert = "INSERT INTO estudiantes (CEDULA, NOMBRE, APELLIDO, DIRECCION, TELEFONO) VALUES ('$cedula' ,'$nombre','$apellido', '$direccion', '$telefono')";
        $resultado = $conectat->prepare($sqlInsert);
        $resultado->execute();
        $data = "Insertado";
        echo json_encode($data);
    }

    public static function update()
    {
        $connect = new Conexion();
        $conectat = $connect->connect();

        // se obtienen los datos enviados por PUT
        // OBTENEMOS LA cEDULA POR LA URL
        // EL Get es para obtener los datos de la URL
        // ejemplo: api.php?cedula=123456         
        $cedula = $_GET['CEDULA'];
        $nombre = $_GET['NOMBRE'];
        $apellido = $_GET['APELLIDO'];
        $direccion = $_GET['DIRECCION'];
        $telefono = $_GET['TELEFONO'];
        // se crea la consulta
        $sqlUpdate = "UPDATE estudiantes SET NOMBRE='$nombre', APELLIDO='$apellido', DIRECCION='$direccion', TELEFONO='$telefono' WHERE CEDULA='$cedula'";
        $resultado = $conectat->prepare($sqlUpdate);
        $resultado->execute();
        $data = "Actualizado";
        echo json_encode($data);
    }

    public static function delete()
    {
        $connect = new Conexion();
        $conectat = $connect->connect();

        // se obtienen los datos enviados por DELETE
        // OBTENEMOS LA CEDULA POR LA URL
        // EL Get es para obtener los datos de la URL
        // ejemplo: api.php?CEDULA=123456
        if (isset($_GET['CEDULA'])) {
            $cedula = $_GET['CEDULA'];
        } else {
            http_response_code(400);
            echo json_encode(["error" => "Falta el parámetro 'CEDULA'"]);
            return;
        }
        // se crea la consulta
        $sqlDelete = "DELETE FROM estudiantes WHERE CEDULA='$cedula'";
        $resultado = $conectat->prepare($sqlDelete);
        $resultado->execute();
        $data = "Eliminado";
        echo json_encode($data);
    }
}
