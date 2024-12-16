package org.example;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un usuario en la plataforma.
 * Cada usuario tiene un correo electrónico como identificador único y puede tener varios comentarios.
 */
@Entity
public class Usuario {

    /**
     * Correo electrónico del usuario (clave primaria).
     */
    @Id
    private String correo;

    /**
     * Nombre completo del usuario.
     */
    private String nombre;

    /**
     * Lista de comentarios realizados por el usuario (relación 1:N).
     * La relación está mapeada en el atributo "usuario" de la clase Comentario.
     */
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Comentario> comentarios = new ArrayList<>();

    /**
     * Constructor parametrizado para crear un usuario.
     *
     * @param correo Correo electrónico del usuario.
     * @param nombre Nombre completo del usuario.
     */
    public Usuario(String correo, String nombre) {
        this.correo = correo;
        this.nombre = nombre;
    }

    /**
     * Constructor por defecto necesario para JPA.
     */
    public Usuario() {}

    // Getters y Setters

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return Correo electrónico.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Obtiene el nombre completo del usuario.
     *
     * @return Nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la lista de comentarios realizados por el usuario.
     *
     * @return Lista de comentarios.
     */
    public List<Comentario> getComentarios() {
        return comentarios;
    }

    /**
     * Añade un comentario a la lista de comentarios del usuario.
     *
     * @param comentario Comentario a añadir.
     */
    public void añadirComentario(Comentario comentario) {
        comentario.setUsuario(this);
        this.comentarios.add(comentario);
    }
}


