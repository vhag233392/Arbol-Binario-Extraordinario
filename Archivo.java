import java.io.*;

public class Archivo {

    public void escribirArchivo(String nombreArchivo, Participante raiz){
        try (FileWriter archivo = new FileWriter(nombreArchivo)) {
            PrintWriter salida = new PrintWriter(archivo);
            imprimirDatos(salida, new Nodo(raiz));
            salida.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("archivo modificado");
    }

    public void imprimirDatos(PrintWriter archivo, Nodo nodo){
        if (nodo != null) {
            imprimirDatos(archivo, nodo.getIzq());
            archivo.println("Numero de folio: "+ nodo.getDato().getFolio() + " \nNombre: " + nodo.getDato().getNombre() + "\nAsistencia: " + nodo.getDato().getAsistencia() );
            imprimirDatos(archivo, nodo.getDer());
        }
    }
}
