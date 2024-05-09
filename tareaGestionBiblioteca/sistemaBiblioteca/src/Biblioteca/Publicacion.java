package Biblioteca;

public class Publicacion {
    //Atributos
    private String titulo;
    private int anioPublicacion;
    private String editorial;
    private String autor;
    private Idioma idioma;
    private int id;

    //Relaciones
    private Estanteria estanteria;
    private TipoPublicacion tipo;

    //Constructor
    public Publicacion(String titulo, int anioPublicacion, String editorial, String autor, Idioma idioma, int id, TipoPublicacion tipo){
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
        this.editorial = editorial;
        this.autor = autor;
        this.idioma = idioma;
        this.id = id;
        this.tipo = tipo;
    }
}
