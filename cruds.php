<?php
class CRUD {
static function selectEstudiantes(){
    require_once 'conexion.php';
$objetoConn = new Conexion();
$conectar = $objetoConn ->conectar();
$sqlSelect = "SELECT  * FROM estudiantes";
$resultado = $conectar->prepare($sqlSelect);
$resultado->execute();
$data=$resultado->fetchAll(PDO::FETCH_ASSOC);
echo json_encode($data);
}

static function deleteEstudiantes(){
    include_once 'conexion.php';
$objetoConn = new Conexion();
$conectar = $objetoConn ->conectar();
$cedula=$_GET['cedula'];
$sqlDelete = "DELETE FROM estudiantes WHERE CEDULA = ?";
$resultado = $conectar->prepare($sqlDelete);
$resultado->execute([$cedula]);
$data="Se elimino";
echo json_encode($data);

}
static function insertEstudiantes(){
    include_once 'conexion.php';
$objetoConn = new Conexion();
$conectar = $objetoConn->conectar();

// Log para depuración
error_log("POST data: " . print_r($_POST, true));

// Verifica si los parámetros existen
$cedula   = isset($_POST['cedula'])   ? $_POST['cedula']   : '';
$nombre   = isset($_POST['nombre'])   ? $_POST['nombre']   : '';
$apellido = isset($_POST['apellido']) ? $_POST['apellido'] : '';
$direccion= isset($_POST['direccion'])? $_POST['direccion']: '';
$telefono = isset($_POST['telefono']) ? $_POST['telefono'] : '';

// Valida que no estén vacíos
if ($cedula && $nombre && $apellido && $direccion && $telefono) {
    try {
        $sqlINSERT = "INSERT INTO estudiantes (CEDULA, NOMBRE, APELLIDO, DIRECCION, TELEFONO) VALUES (?, ?, ?, ?, ?)";
        $resultado = $conectar->prepare($sqlINSERT);
        $resultado->execute([$cedula, $nombre, $apellido, $direccion, $telefono]);
        error_log("Estudiante insertado con éxito: " . $cedula);
        $data = "Se Agregó";
        echo json_encode($data);
    } catch (PDOException $e) {
        error_log("Error al insertar estudiante: " . $e->getMessage());
        echo "Error: " . $e->getMessage();
    }
} else {
    error_log("Faltan datos para la inserción: cedula=$cedula, nombre=$nombre, apellido=$apellido, direccion=$direccion, telefono=$telefono");
    echo "Faltan datos";
}
}

static function updateEstudiantes(){
    include_once 'conexion.php';
$objetoConn = new Conexion();
$conectar = $objetoConn->conectar();

// Verifica si los parámetros existen
$cedula    = $_GET['cedula']    ?? '';
$nombre    = $_GET['nombre']    ?? '';
$apellido  = $_GET['apellido']  ?? '';
$direccion = $_GET['direccion'] ?? '';
$telefono  = $_GET['telefono']  ?? '';


// Valida que no estén vacíos
if ($cedula && $nombre && $apellido && $direccion && $telefono) {
    $sqlUPDATE = "UPDATE estudiantes SET NOMBRE=?, APELLIDO=?, DIRECCION=?, TELEFONO=? WHERE CEDULA=?";
    $resultado = $conectar->prepare($sqlUPDATE);
    $resultado->execute([$nombre, $apellido, $direccion, $telefono, $cedula]);
    $data = "Se actualizó";
    echo json_encode($data);
} else {
    echo "Faltan datos";
}
}

}
?>