
public class Recorrido {


    
    public void inorden(Participante participante) {
        if (participante != null) {
            inorden(participante.getIzq());
            System.out.println("Folio --> " + participante.getFolio() + " Nombre --> " + participante.getNombre() + ", Asistencia --> " + participante.getAsistencia());
            inorden(participante.getDer());
        }
    }


    
    public Participante busqueda(Participante actual, int valorBuscado){
        
        if(actual == null){
            return null;
        }
        if(valorBuscado == actual.getFolio()){
            return actual;
        }
        if (valorBuscado < actual.getFolio()) {
            return busqueda(actual.getIzq(), valorBuscado);
        } else {
            return busqueda(actual.getDer(), valorBuscado);
        }
        
    }

}




