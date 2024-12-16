package org.example;

import java.util.List;

/**
 * Clase principal que prueba los métodos implementados en DataService.
 */
public class Main {
    public static void main(String[] args) {
        // Registrar usuarios
        DataService.registrarUsuario("correo1@example.com", "Juan Perez");
        DataService.registrarUsuario("correo2@example.com", "Ana Lopez");

        // Añadir comentarios
        DataService.añadirComentario("correo1@example.com", "Excelente contenido", 9);
        DataService.añadirComentario("correo2@example.com", "Muy informativo", 8);

        // Listar mejores comentarios
        List<Comentario> mejoresComentarios = DataService.listarMejoresComentarios(8);
        for (Comentario c : mejoresComentarios) {
            System.out.println("Comentario: " + c.getContenido() + ", Valoración: " + c.getValoracion());
        }

        // Eliminar un usuario
        DataService.eliminarUsuario("correo1@example.com");

        // Cerrar EntityManagerFactory
        DataService.close();
    }
}

