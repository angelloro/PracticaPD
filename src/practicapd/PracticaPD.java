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
    
    public void inicio(int num) {
        raiz = new Numero(num, null, null);
        ArrayList<Integer> pa = raiz.sacarDivisores(num);
        raiz.setAdyacentes(raiz.sacarSucesores(num));
        crearGrafo(num);
        
        
        backward(raiz);
        numeroActual=raiz;
        //siguienteJugada(adyacentesRaiz);
    }

    public Numero getRaiz() {
        return raiz;
    }
    public Numero getNActual(){
        return numeroActual;
    }

    public void crearGrafo(int num) {
        ArrayList<Numero> vertices = sacarVertices(num);
        vertices.add(0, raiz);

        mapa = new Grafo(vertices.size(), true);
        mapa = editarGrafo(mapa, vertices);
    }
    
    //Algoritmo forward y metodos auxiliares
    public void forward(Numero raiz) {
        /*ArrayList<Numero> anchura = new ArrayList<>();        
        anchura.add(raiz);
        ArrayList<Numero> numeros = mapa.vertices();
        int c = 0;
        while (c < numeros.size()) {
            Numero estoy = anchura.get(c);
            ArrayList<Numero> ady = mapa.adyacentes(estoy);
            for (int k = 0; k < ady.size(); k++) {
                Numero voy = ady.get(k);
                if (!anchura.contains(voy)) {
                    anchura.add(voy);
                }
                if (!ganador(voy)) {
                    if (voy.getNumero() != 1) {
                        voy.setPadre(estoy);
                        leer.pln("" + voy.getNumero());
                        leer.pln("" + voy.getPadre().getNumero());
                    }

                }
            }
            c++;
        }*/
    }

    /*private boolean ganador(Numero V) {
        boolean ganador = false;
        ArrayList<Integer> ADY = V.getAdyacentes();
        for (int i = 0; i < ADY.size(); i++) {
            if (ADY.get(i) == 1) {
                ganador = true;
            }

        }
        return ganador;
    }*/

    //Algoritmo backward y metodos auxiliares
    public void backward(Numero estoy) {
        if (estoy.getGanador().equals("X")) {
            ArrayList<Numero> ady = mapa.adyacentes(estoy);
            if (ady.isEmpty()) {
                estoy.setGanador("P");
            } else {
                for (int k = 0; k < ady.size(); k++) {
                    Numero voy = ady.get(k);
                    backward(voy);
                    if (!ganador(voy.getGanador())) {
                        estoy.setGanador("G");
                        estoy.setPadre(voy);
                    }
                    if (!ganador(estoy.getGanador())) {
                        estoy.setGanador("P");
                    }
                }
            }
        }
    }

    public boolean ganador(String a){
        return a.equals("G");
    }


    //Cosas del grafo
    public ArrayList<Numero> sacarVertices(int num) {
        Numero a = new Numero();        
        ArrayList<Integer> nVer = a.sacarSucesores(num);
        ArrayList<Integer> aux = new ArrayList<>();
        ArrayList<Numero> nas = new ArrayList<>();
        for (int i = 0; i < nVer.size(); i++) {
            aux = a.sacarSucesores(nVer.get(i));
            Numero n = new Numero(nVer.get(i), aux, null);
            nas.add(n);
            for (int j = 0; j < aux.size(); j++) {
                if (!nVer.contains(aux.get(j))) {
                    nVer.add(aux.get(j));
                }
            }
        }
        nVer.add(0, num);

        return nas;
    }

    public Grafo editarGrafo(Grafo mapa, ArrayList<Numero> vertices) {
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

    public Numero siguienteJugada() {
        //Para ver a que numero va, elegir un adyacente que sea perdedor
        ArrayList<Numero> adyacentesRaiz = mapa.adyacentes(numeroActual);
        Numero siguiente = null;
        boolean flan = false;
        for (int i = 0; i < adyacentesRaiz.size(); i++) {
            if (adyacentesRaiz.get(i).getGanador().equals("P")) {
                siguiente = adyacentesRaiz.get(i);
                flan = true;
            }
        }
        for (int i = 0; i < adyacentesRaiz.size(); i++) {
            if (adyacentesRaiz.get(i).getGanador().equals("G") && flan == false) {
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
    public void setActual(Numero actual){
        numeroActual=actual;
    }
}
