import java.io.*;
public class Archivo {



    public void escribirArchivo(String nombreArchivo, Participante raiz){
        try (FileWriter archivo = new FileWriter(nombreArchivo)) {
            PrintWriter salida = new PrintWriter(archivo);
            imprimirDatos(salida,raiz);
            salida.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    
            System.out.println("archivo modificado");
        
        
    
    };
    public void imprimirDatos(PrintWriter archivo, Participante raiz){


        
        if (raiz != null) {
            imprimirDatos(archivo, raiz.getIzq());
            archivo.println("Numero de folio: "+ raiz.getFolio() + " \nNombre: " + raiz.getNombre() + "\nAsistencia: " + raiz.getAsistencia() );
            imprimirDatos(archivo, raiz.getDer());
            
        }

    }
}
