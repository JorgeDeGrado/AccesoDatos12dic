package org.example;

import javax.persistence.*;

/**
 * Representa un comentario realizado por un usuario.
 * Cada comentario está asociado a un usuario y tiene un contenido y una valoración.
 */
@Entity
public class Comentario {

    /**
     * Identificador único del comentario (clave primaria generada automáticamente).
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Contenido del comentario.
     */
    private String contenido;

    /**
     * Valoración asociada al comentario, debe ser un número entre 0 y 10.
     */
    private int valoracion;

    /**
     * Usuario al que pertenece el comentario (relación N:1).
     */
    @ManyToOne
    private Usuario usuario;

    /**
     * Constructor parametrizado para crear un comentario.
     *
     * @param contenido  Texto del comentario.
     * @param valoracion Valoración del comentario.
     */
    public Comentario(String contenido, int valoracion) {
        this.contenido = contenido;
        this.valoracion = valoracion;
    }

    /**
     * Constructor por defecto necesario para JPA.
     */
    public Comentario() {}

    // Getters y Setters

    /**
     * Obtiene el contenido del comentario.
     *
     * @return Contenido del comentario.
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Obtiene la valoración del comentario.
     *
     * @return Valoración (entre 0 y 10).
     */
    public int getValoracion() {
        return valoracion;
    }

    /**
     * Asigna un usuario al comentario.
     *
     * @param usuario Usuario que realizó el comentario.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

