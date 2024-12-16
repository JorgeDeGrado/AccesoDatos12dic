package org.example;

import javax.persistence.*;
import java.util.List;

/**
 * Clase de servicio que proporciona métodos para gestionar usuarios y comentarios en la base de datos.
 */
public class DataService {

    /**
     * EntityManagerFactory para la gestión de la conexión con ObjectDB.
     */
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/mydb.odb");

    /**
     * Registra un nuevo usuario en la base de datos.
     *
     * @param correo Correo electrónico del usuario.
     * @param nombre Nombre completo del usuario.
     */
    public static void registrarUsuario(String correo, String nombre) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Usuario usuario = new Usuario(correo, nombre);
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Lista los comentarios cuya valoración es igual o superior a un valor mínimo.
     *
     * @param valorMin Valor mínimo para filtrar los comentarios.
     * @return Lista de comentarios que cumplen el criterio.
     */
    public static List<Comentario> listarMejoresComentarios(int valorMin) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Comentario> query = em.createQuery(
                "SELECT c FROM Comentario c WHERE c.valoracion >= :valorMin", Comentario.class);
        query.setParameter("valorMin", valorMin);
        List<Comentario> comentarios = query.getResultList();
        em.close();
        return comentarios;
    }

    /**
     * Añade un comentario a un usuario existente.
     *
     * @param correo    Correo del usuario que realiza el comentario.
     * @param contenido Contenido del comentario.
     * @param valoracion Valoración del comentario.
     */
    public static void añadirComentario(String correo, String contenido, int valoracion) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Usuario usuario = em.find(Usuario.class, correo);
        if (usuario != null) {
            Comentario comentario = new Comentario(contenido, valoracion);
            usuario.añadirComentario(comentario);
            em.persist(comentario);
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Elimina un usuario y todos sus comentarios asociados.
     *
     * @param correo Correo electrónico del usuario a eliminar.
     */
    public static void eliminarUsuario(String correo) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Usuario usuario = em.find(Usuario.class, correo);
        if (usuario != null) {
            em.remove(usuario);
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Cierra el EntityManagerFactory.
     * Debe llamarse al finalizar el programa.
     */
    public static void close() {
        emf.close();
    }
}

