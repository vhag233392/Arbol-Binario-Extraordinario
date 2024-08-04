public class Nodo {
    private Participante dato;
    private Nodo izq;
    private Nodo der;
    
    public Nodo(Participante dato) {
        this.dato = dato;
    }

    public Participante getDato() {
        return dato;
    }

    public Nodo getIzq() {
        return izq;
    }

    public Nodo getDer() {
        return der;
    }

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public void setDer(Nodo der) {
        this.der = der;
    }
}
