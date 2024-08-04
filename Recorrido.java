public class Recorrido {
    public void inorden(Nodo nodo) {
        if (nodo != null) {
            inorden(nodo.getIzq());
            System.out.println("Folio --> " + nodo.getDato().getFolio() + " Nombre --> " + nodo.getDato().getNombre() + ", Asistencia --> " + nodo.getDato().getAsistencia());
            inorden(nodo.getDer());
        }
    }

    public Nodo busqueda(Nodo actual, int valorBuscado){
        if (actual == null) {
            return null;
        }
        if (valorBuscado == actual.getDato().getFolio()) {
            return actual;
        }
        if (valorBuscado < actual.getDato().getFolio()) {
            return busqueda(actual.getIzq(), valorBuscado);
        } else {
            return busqueda(actual.getDer(), valorBuscado);
        }
    }
}
