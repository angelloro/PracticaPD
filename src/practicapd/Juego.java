package practicapd;

import java.util.ArrayList;
import utilidades.*;

/**
 * @author Angel
 */
public class Juego {
    
    public static void main(String [] args){
        boolean fin=false;
        boolean turnoJugador=false;
        String ganador = "";
        PracticaPD d = new PracticaPD();  
        int n = 195;
        d.inicio(n);
        Numero actual=d.darNActual(); 
            int eleccion=0;
            leer.pln("El numero para comenzar a jugar es: "+n);
        while(fin==false){  
            if(turnoJugador){
                leer.pln("Turno jugador: ");
                while(!actual.getPrimos().contains(eleccion)){
                   eleccion= leer.entero("Introduzca un numero de esta lista: "+actual.getPrimos());
                }
                turnoJugador = false;
                if(eleccion == 1){
                    fin = true;
                    ganador = "Jugador";
                }
            }else{
                leer.pln("Turno maquina: ");
                //d.inicio(eleccion);
                actual = d.siguienteJugada();
                eleccion = actual.getNumero();
                turnoJugador = true;
                if(eleccion == 1){
                    fin = true;
                    ganador = "Maquina";
                }
            }
            actual=d.pedirActual(eleccion);
            d.setActual(actual);
            leer.pln(""+actual.getNumero());
        }
        leer.pln("El ganador es: "+ganador);
    }
}
