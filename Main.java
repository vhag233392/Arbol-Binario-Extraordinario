
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {
        ArbolBinario arbol1 = new ArbolBinario();

        while (true) {

        try {
            
            arbol1.visualizarMenu();
            break;
        } catch (InputMismatchException e) {
            System.out.println("-->Ha Habido Un Error<--");
        }
                
            

        }

    }

}
