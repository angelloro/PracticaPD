package practicapd;

import utilidades.*;
import java.util.ArrayList;

/**
 * @author Ángel Loro y Ángel Sánchez
 **/

public class PracticaPD {

    private Grafo<Numero, Integer> mapa;
    private Numero raiz;
    private Numero numeroActual;
    
    public void inicioBackward(int num) {
        raiz = new Numero(num);
        
        backward(raiz, raiz);
        numeroActual=raiz;        
    }
    
    public void inicioForward(int num) {
        raiz = new Numero(num);
        Numero primero = new Numero(1);
                
        forward(primero);
        numeroActual=raiz;        
    }

    //Algoritmo forward
    public void forward(Numero ultimo) {
        ArrayList<Numero> anchura = new ArrayList<>();        
        anchura.add(ultimo);
        ultimo.setGanador("P");
        ArrayList<Numero> numeros = mapa.vertices();
        int c = 0;
        while (c < numeros.size()) {
            Numero estoy = anchura.get(c);
            ArrayList<Numero> inc = mapa.incidentes(estoy);
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
        }
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
        ArrayList<Numero> adyacentesRaiz = numeroActual.getAdyacentes();
        leer.pln(""+adyacentesRaiz);
        Numero siguiente = null;
        boolean flag = false;
        for (int i = 0; i < adyacentesRaiz.size(); i++) {
            if (adyacentesRaiz.get(i).getGanador().equals("P")) {
                siguiente = adyacentesRaiz.get(i);
                leer.pln(""+siguiente.getNumero());
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
    
    public Numero pedirActual(int num){
        Numero actual=null;
        ArrayList<Numero> vertex=mapa.vertices();
        for (int i=0;i<vertex.size();i++){
            if(vertex.get(i).getNumero()==num){
               actual=vertex.get(i);
            }
        }
        
        return actual;
    }
    
    public Numero getRaiz() {
        return raiz;
    }
    
    public Numero getNActual(){
        return numeroActual;
    }

    public void setNActual(Numero actual){
        numeroActual=actual;
    }
}
