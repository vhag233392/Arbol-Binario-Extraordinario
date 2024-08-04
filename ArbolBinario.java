import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ArbolBinario {

    Archivo archivo = new Archivo();
    ArrayList<Participante> participantes = new ArrayList<>();
    ArrayList<Participante> participantesNoAsistentes = new ArrayList<>();
    ArrayList<Participante> participantesAsistentes = new ArrayList<>();

    int folio;
    String nombre;
    int opc;

    Scanner entrada = new Scanner(System.in);
    Scanner informScanner = new Scanner(System.in);

    String MARCA_AZUL = "\u001B[44m";
    String FIN = "\u001B[0m";
    String MARCA_VERDE = "\u001B[42m";
    String MARCA_ROJO = "\u001B[41m";

    public void mostrarParticipante(int x) {
        System.out.println("-----------------------");
        System.out.println("Nombre: " + participantes.get(x).getNombre());
        System.out.println("Folio: " + participantes.get(x).getFolio());
        if (participantes.get(x).getAsistencia() == true) {
            MENSAJE_VERDE("--- El Participante ASISTIO ---");
        } else {
            MENSAJE_ROJO("--- El Participante NO ASISTIO ---");
        }
        System.out.println("-----------------------");
    }

    public void MENSAJE_AZUL(String Mensaje) {
        System.out.println(MARCA_AZUL + Mensaje + FIN);
    }

    public void MENSAJE_AZUL(int Mensaje) {
        System.out.println(MARCA_AZUL + Mensaje + FIN);
    }

    public void MENSAJE_ROJO(String Mensaje) {
        System.out.println(MARCA_ROJO + Mensaje + FIN);
    }

    public void MENSAJE_VERDE(String Mensaje) {
        System.out.println(MARCA_VERDE + Mensaje + FIN);
    }

    public void visualizarMenu() {
        while (true) {
            try {
                int opc;
                Scanner opcion = new Scanner(System.in);

                System.out.println("[0.- Buscar Participante por Folio]\n[1.- Agregar Asistentes]\n[2.- Tomar Asistencia]\n[3.- Visualizar Participantes que Asistieron]\n[4.- Visualizar Participantes que NO Asistieron]\n[5.- Salir]");
                opc = opcion.nextInt();

                switch (opc) {
                    case 0:
                        busquedaDeParticipanteFolio();
                        break;
                    case 1:
                        AgregarParticipante();
                        break;
                    case 2:
                        ponerAsistencia();
                        break;
                    case 3:
                        visualizarAsistentes();
                        break;
                    case 4:
                        visualizarNoAsistentes();
                        break;
                    case 5:
                        salir();
                        return;  // Salir del menú
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número.");
                entrada.nextLine();  // Limpiar el buffer
            }
        }
    }

    public void AgregarParticipante() {
        MENSAJE_AZUL("Nombre Del Participante");
        nombre = informScanner.nextLine();

        while (true) {
            while (true) {
                try {
                    System.out.println("Ingrese el Folio del Participante (tres Dígitos)");
                    folio = entrada.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    MENSAJE_ROJO("----- ERROR AL INGRESAR -----");
                    entrada.nextLine();
                }
            }

            String cadena1 = String.valueOf(folio);

            if (cadena1.length() == 3) {
                boolean folioBoolean = false;
                for (Participante participante : participantes) {
                    if (participante.getFolio() == folio) {
                        folioBoolean = true;
                        break;
                    }
                }
                if (!folioBoolean) {
                    MENSAJE_VERDE("Folio Válido");
                    break;
                } else {
                    MENSAJE_ROJO("El folio ya está en uso. Intente con otro folio.");
                }
            } else {
                MENSAJE_ROJO("Folio Inválido. Inténtalo de nuevo.");
            }
        }

        participantes.add(new Participante(false, folio, nombre));
    }

    public void ponerAsistencia() {
        int opc;
        Scanner opcion = new Scanner(System.in);

        while (true) {
            for (int i = 0; i < participantes.size(); i++) {
                MENSAJE_AZUL(participantes.get(i).getNombre() + " Asistió al evento? \n[1.- Sí]\n[2.- No]\n[Cualquier Número (Acabar)]");
                opc = opcion.nextInt();

                if (opc == 1) {
                    MENSAJE_VERDE("ASISTIÓ");
                    participantes.get(i).setAsistencia(true);
                    participantesAsistentes.add(participantes.get(i));
                } else if (opc == 2) {
                    MENSAJE_ROJO("NO ASISTIÓ");
                    participantes.get(i).setAsistencia(false);
                    participantesNoAsistentes.add(participantes.get(i));
                } else {
                    break;
                }
            }
            break;
        }
    }

    public void visualizarNoAsistentes() {
        if (participantesNoAsistentes.isEmpty()) {
            System.out.println("No hay participantes que asistieron para crear el árbol.");
            return;
        }
        Nodo raizNoAsistentes = new Nodo(participantesNoAsistentes.get(0));
        for (int idx = 1; idx < participantesNoAsistentes.size(); idx++) {
            agregarParticipanteAlArbol(raizNoAsistentes, new Nodo(participantesNoAsistentes.get(idx)));
        }

        Recorrido g = new Recorrido();
        System.out.println("Inorden (Participantes que no asistieron):");
        g.inorden(raizNoAsistentes);

        archivo.escribirArchivo("NoAsistentes.txt", raizNoAsistentes.getDato());
    }

    public void visualizarAsistentes() {
        if (participantesAsistentes.isEmpty()) {
            System.out.println("No hay participantes que asistieron para crear el árbol.");
            return;
        }

        Nodo raiz = new Nodo(participantesAsistentes.get(0));
        for (int idx = 1; idx < participantesAsistentes.size(); idx++) {
            agregarParticipanteAlArbol(raiz, new Nodo(participantesAsistentes.get(idx)));
        }

        Recorrido r = new Recorrido();
        System.out.println("Inorden (Participantes que asistieron):");
        r.inorden(raiz);

        archivo.escribirArchivo("Asistentes.txt", raiz.getDato());
    }

    public void busquedaDeParticipanteFolio() {
        try {
            System.out.println("Ingresa El Folio Que deseas Buscar: ");
            int folioBuscado = entrada.nextInt();
            System.out.println("Ingresa 0 para empezar desde el primer participante o Elige el índice de algún otro participante: ");
            int folioOrigen = entrada.nextInt();

            if (folioOrigen < 0 || folioOrigen >= participantes.size()) {
                System.out.println("Índice de participante no válido.");
                return;
            }

            if (participantes.isEmpty()) {
                System.out.println("No hay participantes para buscar.");
                return;
            }

            Nodo raiz = new Nodo(participantes.get(folioOrigen));
            Nodo nodo = busqueda(raiz, folioBuscado);

            if (nodo != null) {
                System.out.println("Folio Encontrado como: ");
                System.out.println("Nombre: " + nodo.getDato().getNombre());
                System.out.println("Folio: " + nodo.getDato().getFolio());
                System.out.println("Asistencia: " + nodo.getDato().getAsistencia());
            } else {
                System.out.println("Folio No Encontrado");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida. Por favor, introduce un número.");
            entrada.nextLine();  
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Índice fuera de los límites.");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }
    }

    private void agregarParticipanteAlArbol(Nodo raiz, Nodo nuevoNodo) {
        if (nuevoNodo.getDato().getFolio() < raiz.getDato().getFolio()) {
            if (raiz.getIzq() == null) {
                raiz.setIzq(nuevoNodo);
            } else {
                agregarParticipanteAlArbol(raiz.getIzq(), nuevoNodo);
            }
        } else {
            if (raiz.getDer() == null) {
                raiz.setDer(nuevoNodo);
            } else {
                agregarParticipanteAlArbol(raiz.getDer(), nuevoNodo);
            }
        }
    }

    public Nodo busqueda(Nodo actual, int valorBuscado) {
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

    private void salir() {
        System.out.println("Has Salido");
    }
}
