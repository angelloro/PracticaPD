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
        ArrayList<Numero> vertices = sacarVertices(raiz);                   
 
        crearGrafo(raiz, vertices); //Creamos el grafo
        
        backward(raiz, raiz);
        numeroActual=raiz;        
    }
    
    public void inicioForward(int num) {
        raiz = new Numero(num);
        ArrayList<Numero> vertices = sacarVertices(raiz);                
 
        crearGrafo(raiz, vertices); //Creamos el grafo
                
        forward(vertices.get(0));
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
            ArrayList<Numero> ady = mapa.adyacentes(estoy);
            if (ady.isEmpty()) {
                estoy.setGanador("P");
            } else {
                for (int k = 0; k < ady.size(); k++) {
                    Numero voy = ady.get(k);
                    backward(voy, raiz);
                    if (!ganador(voy.getGanador())) {
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

    
    public ArrayList<Numero> sacarVertices(Numero num) {               
        ArrayList<Numero> vertices = new ArrayList<>(); //Lista donde vamos a guardar los vertices
        ArrayList<Integer> nVer = num.sacarSucesores(); //Lista de los sucesores que vamos a convertir en objeto Numero
        ArrayList<Integer> aux = new ArrayList<>(); //Lista auxiliar donde vamos a guardar los sucesores de cada vertice
        
        vertices.add(0,num);
        for (int i = 0; i < nVer.size(); i++) {
            
            Numero n = new Numero(nVer.get(i));
            aux = n.sacarSucesores();
                   
            vertices.add(0,n);
            for (int j = 0; j < aux.size(); j++) { //Añadimos los que no esten en la lista 
                if (!nVer.contains(aux.get(j))) {
                    nVer.add(aux.get(j));
                }
            }
        }        
        return vertices;
    }
    
    public void crearGrafo(Numero num, ArrayList<Numero> vertices) {
        mapa = new Grafo(vertices.size(), true);
        mapa = editarGrafo(vertices);
    }
    
    public Grafo editarGrafo(ArrayList<Numero> vertices) {
        for (int i = 0; i < vertices.size(); i++) {
            Numero a = vertices.get(i);
            mapa.nuevoVertice(a);
        }
       
        ArrayList<Numero> V = mapa.vertices();
        for (int x = 0; x < V.size(); x++) {
            Numero O = V.get(x);
            Numero X = null;
            ArrayList<Integer> Prim = O.getAdyacentes();
            for (int y = 0; y < Prim.size(); y++) {
                for (int z = 0; z < V.size(); z++) {
                    Numero N1 = V.get(z);
                    if (N1.getNumero() == Prim.get(y)) {
                        X = N1;
                    }
                }

                mapa.nuevoArco(O, X, 0);
            }

        }
        return mapa;
    }

    public Numero siguienteJugada() { //Para ver a que numero va a elegir un adyacente que sea perdedor
        ArrayList<Numero> adyacentesRaiz = mapa.adyacentes(numeroActual);
        Numero siguiente = null;
        boolean flag = false;
        for (int i = 0; i < adyacentesRaiz.size(); i++) {
            if (adyacentesRaiz.get(i).getGanador().equals("P")) {
                siguiente = adyacentesRaiz.get(i);
                flag = true;
            }
        }
        for (int i = 0; i < adyacentesRaiz.size(); i++) {
            if (adyacentesRaiz.get(i).getGanador().equals("G") && flag == false) {
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
