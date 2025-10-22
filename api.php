<?php

require_once 'cruds.php';

$opc = $_SERVER['REQUEST_METHOD'];
switch ($opc) {
    case 'GET':
        CRUD::selectEstudiantes();
        break;       
    case 'PUT':
        CRUD::updateEstudiantes();
        break;
    case 'POST':
        CRUD::insertEstudiantes();
        break;
    case 'DELETE':
        CRUD::deleteEstudiantes();
        break;
    
}
?>