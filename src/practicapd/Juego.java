package practicapd;

import utilidades.*;

/**
 * @author Ángel Loro y Ángel Sánchez
 *
 */
public class Juego {

    public static void main(String[] args) {
       menu();
    }

    public static void comprobarJugada(PracticaPD d) {
        int n =comprobarNumero();
        switch(leer.entero("Indique la opcion elegida:\n1:fordward\n2:backward\n")){
            case 1:
                d.inicioForward(n);
                if (d.getRaiz().getGanador().equals("G")){
                   leer.pln("Este numero puede ganar");
                   Numero siguiente =d.siguienteJugada();
                   leer.pln("Para ganar deje al contrario el numero: "+siguiente.getNumero());
                }else{
                   leer.pln("Este numero no puede ganar");
                }
                break;                
            case 2:
                d.inicioBackward(n);
               if (d.getRaiz().getGanador().equals("G")){
                   leer.pln("Este numero puede ganar");
                   Numero siguiente =d.siguienteJugada();
                   leer.pln("Para ganar deje al contrario el numero: "+siguiente.getNumero());
               }else{
                   leer.pln("Este numero no puede ganar");
               }
               break;            
        }
    }
    
    public static void jugadorPerfecto(PracticaPD d) {
        boolean fin = false, turnoJugador = true;        
        int n = comprobarNumero(); //Pide el numero y comprueba que sea mayor que 0
        
        Numero actual = new Numero(n);//Actualizamos el numero con el que se trabaja
       
        leer.pln("El numero para comenzar a jugar es: " + n);        
        while (fin == false) { //Se alternan los turnos del jugador y de la maquina mediante un bucle hasta que el juego termine
            if (turnoJugador) {
                leer.pln("Turno jugador: ");
                
                //while (!actual.getAdyacentes().contains(a)) {//El jugador selecciona un numero de los adyacentes al punto actual
                    int eleccion = leer.entero("Introduzca un numero de esta lista: " + actual.getAdyacentes());                    
                //}
                actual = new Numero(eleccion);
                turnoJugador = false;
                
                leer.pln("" + actual.getNumero());
                if (actual.getNumero() == 1) {
                    fin = true;
                    leer.pln("HAS GANADO");
                }
                
            } else {//La maquina realiza su jugada buscando siempre la mejor
                leer.pln("Turno maquina: ");
                
                d.inicioBackward(actual.getNumero());
                actual = d.siguienteJugada();                
                turnoJugador = true;
                
                leer.pln("" + actual.getNumero());
                if (actual.getNumero() == 1) {
                    fin = true;
                    leer.pln("HAS PERDIDO. GAME OVER");
                }
            }                    
        }        
    }
    public static int comprobarNumero(){
        int n = 0;
        while(n <= 1)
                n=leer.entero("Indique el numero con el cual desea jugar\n");
        return n;
    }
    
    public static void menu(){
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
}
