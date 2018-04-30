package practicapd;

import utilidades.*;
import java.util.ArrayList;

/**
 * @author Ángel Loro y Ángel Sánchez
 **/

public class PracticaPD {

    private Numero raiz;
    private ArrayList<Integer> divisoresRaiz;
    
    public void inicioBackward(int num) {
        raiz = new Numero(num);
        
        backward(raiz, raiz);                
    }
    
    public void inicioForward(int num) {
        raiz = new Numero(num);
        divisoresRaiz = raiz.sacarF(raiz.getNumero());
        Numero primero = new Numero(1);
                
        forward(primero, raiz);            
    }

    //Algoritmo forward
    public void forward(Numero ultimo, Numero raiz) {        
        leer.pln(""+raiz.sacarF(12));
        leer.pln(""+sucesoresForward(ultimo));
        /*ArrayList<Numero> anchura = new ArrayList<>();                        
        anchura.add(ultimo);                
        ultimo.setGanador("P");
        int c = 0;
        while (raiz.getGanador().equals("X")) {
            Numero estoy = anchura.get(c);
            ArrayList<Numero> inc = sucesoresForward(estoy);
            for (int k = 0; k < inc.size(); k++) {
                Numero voy = inc.get(k);
                if (!anchura.contains(voy)) {
                    anchura.add(voy);
                }
                if (!ganador(estoy.getGanador())) {
                    voy.setGanador("G");
                    
                }else{
                    voy.setGanador("P");
                }
            }
            c++;
            leer.pln(""+raiz.getGanador());
        }*/
    }

    //Algoritmo backward
    public void backward(Numero estoy, Numero raiz) {
        if(raiz.getGanador().equals("G")){
            //No hace nada porque ya ha encontrado el camino ganador
        }else if (estoy.getGanador().equals("X")) {
            ArrayList<Numero> ady = estoy.getAdyacentes();
            if (ady.isEmpty()) {
                estoy.setGanador("P");
            } else {
                for (int k = 0; k < ady.size(); k++) {
                    Numero voy = ady.get(k);
                    backward(voy, raiz);
                    if (!ganador(voy.getGanador())) {
                        estoy.setPadre(voy);
                        estoy.setGanador("G");                        
                    }
                    if (!ganador(estoy.getGanador())) {
                        estoy.setGanador("P");
                    }
                }
            }
        }
    }
    
    //Metodo auxiliar
    public boolean ganador(String a){
        return a.equals("G");
    }

    public Numero siguienteJugada() { //Para ver a que numero va a elegir un adyacente que sea perdedor
        ArrayList<Numero> adyacentesRaiz = raiz.getAdyacentes();
        Numero siguiente = null;
        boolean flag = false;
        for (int i = 0; i < adyacentesRaiz.size(); i++) {
            if (adyacentesRaiz.get(i).getGanador().equals("P")) {
                siguiente = adyacentesRaiz.get(i);
                flag = true;
            }
        }
        for (int i = 0; i < adyacentesRaiz.size(); i++) {
            if ( flag == false) {
                if(adyacentesRaiz.get(i).getGanador().equals("G") || adyacentesRaiz.get(i).getGanador().equals("X") )
                siguiente = adyacentesRaiz.get(i);
            }
        }
        return siguiente;
    }
    
    public Numero getRaiz() {
        return raiz;
    }
    
    public ArrayList<Numero> sucesoresForward(Numero num){
        ArrayList<Numero> sucesoresForward = new ArrayList<>();
        ArrayList<Integer> aux = new ArrayList<>();
        ArrayList<Integer> aux1 = num.sacarF(num.getNumero());                
        
        for(int i = 0 ; i < divisoresRaiz.size() ; i++){
            if(!aux1.contains(divisoresRaiz.get(i))){
                aux.add(divisoresRaiz.get(i));
            }else{
                aux1.remove(divisoresRaiz.get(i));
            }
        }        
        leer.pln(""+aux);
        for(int i = 0 ; i<aux.size() ; i++){
            
            sucesoresForward.add(new Numero(num.getNumero()*aux.get(i)));
        }
        return sucesoresForward;
    }
}
