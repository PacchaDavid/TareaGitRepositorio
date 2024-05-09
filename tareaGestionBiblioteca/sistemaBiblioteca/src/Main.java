
import Biblioteca.*;

import static Biblioteca.Idioma.ESPANOL;
import static Biblioteca.Idioma.INGLES;
import static Biblioteca.TipoPublicacion.LIBRO;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca("Biblioteca Nacional", 3);
        SistemaGestion sistema = new SistemaGestion(biblioteca, 1);
        biblioteca.getSeccionList()[0] = new Seccion("Seccion 1", 3);
        biblioteca.getSeccionList()[1] = new Seccion("Seccion 2", 3);
        biblioteca.getSeccionList()[2] = new Seccion("Seccion 3", 3);
        biblioteca.getSeccionList()[0].agregarEstanteria(new Estanteria(1, 3));
        biblioteca.getSeccionList()[0].agregarEstanteria(new Estanteria(2, 3));
        biblioteca.getSeccionList()[0].agregarEstanteria(new Estanteria(3, 3));
        biblioteca.getSeccionList()[0].getEstanteriaList()[0].agregarPublicacion(new Publicacion("Libro 1", 2020, "Editorial 1", "Autor 1", ESPANOL, 1, LIBRO));
        biblioteca.getSeccionList()[0].getEstanteriaList()[0].agregarPublicacion(new Publicacion("Libro 2", 2020, "Editorial 2", "Autor 2", INGLES, 2, LIBRO));
        biblioteca.getSeccionList()[0].getEstanteriaList()[0].agregarPublicacion(new Publicacion("Libro 3", 2020, "Editorial 3", "Autor 3", ESPANOL, 3, LIBRO));
    }
}