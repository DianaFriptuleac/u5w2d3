package dianafriptuleac.u5w2d3.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {

        super("Elemento con id: " + id + " non trovato!");
    }
}
