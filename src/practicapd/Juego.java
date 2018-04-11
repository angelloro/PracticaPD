package practicapd;

import utilidades.*;

/**
 * @author Ángel Loro y Ángel Sánchez
 *
 */
public class Juego {

    public static void main(String[] args) {
        PracticaPD d = new PracticaPD();
        boolean seguir = true;
        //programa para seleccionar lo que se desea hacer
        do {
            switch (leer.entero("Indique que desea realizar:\n1:Comprobar una jugada\n2:Jugar contra la maquina\n3:Fin programa\n")) {
                case 1:
                    comprobarJugada(d);
                    break;
                case 2:
                    jugadorPerfecto(d);
                    break;
                case 3:
                    seguir = false;
                    leer.pln("Hasta la proxima");
                    break;
                default:
                    leer.pln("Opcion invalida");
                    break;
            }

        } while (seguir);
    }

    public static void comprobarJugada(PracticaPD d) {
        int n = leer.entero("Indique el numero con el cual desea jugar\n");
        d.inicio(n);//Indicamos el numero con el que se desea jugar
        switch(leer.entero("Indique la opcion elegida:\n1:fordward\n2:backward\n")){
            case 1:
                break;
                
            case 2:
                d.inicio(n);
               if (d.getRaiz().getGanador().equals("G")){
                   leer.pln("Este numero puede ganar");
                   Numero siguiente =d.siguienteJugada();
                   leer.pln("Para ganar deje al contrario el numero: "+siguiente.getNumero());
               }else{
                   leer.pln("Este numero no puede ganar");
               }
               
            
        }
    }

    public static void jugadorPerfecto(PracticaPD d) {
        boolean fin = false,
        turnoJugador = false;
        String ganador = "";
        int n;
        n = leer.entero("Indique el numero con el cual desea jugar\n");
        d.inicio(n);//Indicamos el numero con el que se desea jugar
        Numero actual = d.getNActual();//Actualizamos el numero con el que se trabaja
        int eleccion = 0;
        leer.pln("El numero para comenzar a jugar es: " + n);
        //Se alternan los turnos del jugador y de la maquina mediante un bucle hasta que el juego termine
        while (fin == false) {
            if (turnoJugador) {
                leer.pln("Turno jugador: ");
                while (!actual.getAdyacentes().contains(eleccion)) {//El jugador selecciona un numero de los adyacentes al punto actual
                    eleccion = leer.entero("Introduzca un numero de esta lista: " + actual.getAdyacentes());
                }
                turnoJugador = false;
                if (eleccion == 1) {
                    fin = true;
                    ganador = "Jugador";
                }
            } else {//La maquina realiza su jugada buscando siempre la mejor
                leer.pln("Turno maquina: ");
                actual = d.siguienteJugada();
                eleccion = actual.getNumero();
                turnoJugador = true;
                if (eleccion == 1) {
                    fin = true;
                    ganador = "Maquina";
                }
            }
            actual = d.pedirActual(eleccion);
            d.setNActual(actual);
            leer.pln("" + actual.getNumero());
        }

        leer.pln("El ganador es: " + ganador);
    }
}
