package practicapd;

import utilidades.*;
//import java.util.ArrayList;
import java.util.*;

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
    
    public Numero inicioForward(int num) {
        raiz = new Numero(num);
        divisoresRaiz = raiz.sacarF(raiz.getNumero());
        Numero primero = new Numero(1);
                
        return forward(primero, raiz);            
    }

    //Algoritmo forward
    public Numero forward(Numero ultimo, Numero raiz) {                        
        ArrayList<Numero> anchura = new ArrayList<>();                        
        anchura.add(ultimo);                
        ultimo.setGanador("P");
        int c = 0;
        Numero estoy = ultimo;
        while (estoy.getNumero() != raiz.getNumero()) {
            estoy = anchura.get(c);

            ArrayList<Numero> suc = sucesoresForward(estoy);
            for (int k = 0; k < suc.size(); k++) {
                Numero voy = suc.get(k);                                
                if (!ganador(estoy.getGanador())) {
                    voy.setGanador("G");
                    estoy.setPadre(voy);                   
                }else{
                    voy.setGanador("P");
                    estoy.setPadre(voy); 
                }
                if (!contains(anchura, voy)) {
                    anchura.add(voy);
                }                
            }
            c++;            
        }
        
        raiz.setGanador(estoy.getGanador());
        
        for(int i = 0 ; i < anchura.size() ; i++){
            try{
                if(raiz.getNumero() == anchura.get(i).getPadre().getNumero() && anchura.get(i).getGanador().equals("P")){
                estoy = anchura.get(i);
            }
            }catch(Exception e){
                //Este fallo es provocado por el null del 1
            }
            
        }
        return estoy;
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
    
    public boolean contains(ArrayList<Numero> anchura, Numero num){
        boolean contains = false;
        for(int i = 0 ; i < anchura.size() ; i++){
            if(anchura.get(i).getNumero() == num.getNumero()){
                contains = true;
                if(anchura.get(i).getGanador().equals("P") && num.getGanador().equals("G")){
                    anchura.get(i).setGanador("G");
                }                
            }
        }
        return contains;
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

        int numeroMultiplicacion = 0;
        int pot = 1;
        for(int i = 0 ; i<aux.size() ; i++){
            if (numeroMultiplicacion==aux.get(i)){
                pot++;
            }else{
                pot=1; 
            }
            int potencia =(int) Math.pow(aux.get(i), pot);
            sucesoresForward.add(new Numero(num.getNumero()* potencia));
            numeroMultiplicacion=aux.get(i);
        }
        return sucesoresForward;
    }
    
    public Numero getRaiz() {
        return raiz;
    }
}
