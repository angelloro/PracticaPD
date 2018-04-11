package practicapd;

import java.util.ArrayList;
import utilidades.*;

/**
 * @author Ángel Loro y Ángel Sánchez
 **/

public class Juego {
    
    public static void main(String [] args){
        PracticaPD d = new PracticaPD();
        boolean fin=false, turnoJugador=false;
        String ganador = "";
          
        int n = 12;
        d.inicio(n);
        Numero actual=d.getNActual(); 
        int eleccion=0;
        leer.pln("El numero para comenzar a jugar es: "+n);
        
        while(fin==false){  
            if(turnoJugador){
                leer.pln("Turno jugador: ");
                while(!actual.getAdyacentes().contains(eleccion)){
                   eleccion= leer.entero("Introduzca un numero de esta lista: "+actual.getAdyacentes());
                }
                turnoJugador = false;
                if(eleccion == 1){
                    fin = true;
                    ganador = "Jugador";
                }
            }else{
                leer.pln("Turno maquina: ");                
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
