<?php

class Conexion
{

    public function connect()
    {
        $servername = "localhost:3306";
        $username = "root";
        $password = "";
        $dbname = "soa";

        try {

            $connect = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
        } catch (Exception $e) {
            die("Fallo : " . $e->getMessage());
        }

        return $connect;
    }
}
