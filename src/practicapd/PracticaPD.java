package practicapd;

import utilidades.*;
import java.util.ArrayList;

/**
 * @author Ángel Loro y Ángel Sánchez
 *
 */
public class PracticaPD {

    private static Grafo<Numero, Integer> mapa;
    private static Numero raiz;
    public static void main(String[] args) {
        int num = 24;
        ArrayList<Integer> pa = primosDivisores(num);
        ArrayList<Numero> vertices = sacarVertices(num);
        raiz = new Numero(num, division(num, pa), null, 0);
        vertices.add(0, raiz);

        mapa = new Grafo(vertices.size(), true);
        mapa = editarGrafo(mapa, vertices);

        leer.pln(mapa.toString());
        leer.pln("fordward");
        forward(raiz);
        
        leer.pln("backward");        
        backward(raiz);
        leer.pln(""+raiz.isGanador());
        //Para ver a que numero va, elegir un adyacente que sea perdedor
    }

    public static void forward(Numero raiz) { 
        ArrayList<Numero> anchura = new ArrayList<Numero>();
        raiz.setCoste(0);
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
                    if(voy.getNumero()!=1){
                    voy.setPadre(estoy);
                    leer.pln("" + voy.getNumero());
                    leer.pln(""+voy.getPadre().getNumero());}
                    
                }
            }
            c++;
        }
    }

    private static boolean ganador(Numero V) {
        boolean ganador=false;
        ArrayList <Integer> ADY=V.getPrimos();
        for (int i=0;i<ADY.size();i++){
            if (ADY.get(i)==1){
                    ganador=true;
            }
                    
        }
        return ganador;
    }

    public static void backward(Numero estoy) {//recorrido profundidad             
        if (estoy.isGanador().equals("X")) {
            ArrayList<Numero> ady = mapa.adyacentes(estoy);
            if (ady.isEmpty()) {              
                estoy.setGanador("P");
            } else {
                for (int k = 0; k < ady.size(); k++) {
                    Numero voy = ady.get(k);
                    backward(voy);                    
                    if (voy.isGanador().equals("P")) {                        
                        estoy.setGanador("G");                        
                        estoy.setPadre(voy);
                    }
                    if(!estoy.isGanador().equals("G")){
                       estoy.setGanador("P");
                    }
                }
            }
        }        
    }

    public static ArrayList<Integer> primosMenores(int num) {
        ArrayList<Integer> primos = new ArrayList();
        int aux;
        if (num >= 5) {
            primos.add(2);
            primos.add(3);
            primos.add(5);
        } else if (num >= 3) {
            primos.add(2);
            primos.add(3);
        } else if (num >= 2) {
            primos.add(2);
        }

        for (int i = 2; i <= num; i++) {
            if (i % 2 != 0 && i % 3 != 0 && i % 5 != 0) {
                primos.add(i);
            }
        }
        return primos;
    }

    public static ArrayList<Integer> primosDivisores(int num) {
        ArrayList<Integer> primos = primosMenores(num);
        ArrayList<Integer> aux = new ArrayList();
        int cont;

        for (int i = 0; i < primos.size(); i++) {
            cont = 1;
            while (num % primos.get(i) == 0) {
                aux.add((int) Math.pow(primos.get(i), cont));
                num /= primos.get(i);
                cont++;
            }
        }
        primos = aux;
        return primos;
    }

    public static ArrayList<Integer> division(int num, ArrayList<Integer> primos) {
        ArrayList<Integer> aux = new ArrayList<Integer>();
        for (int i = 0; i < primos.size(); i++) {
            aux.add(num / primos.get(i));
        }
        return aux;
    }

    public static ArrayList<Numero> sacarVertices(int num) {
        int numeroVer;
        ArrayList<Integer> nVer = division(num, primosDivisores(num));
        ArrayList<Integer> aux = new ArrayList<Integer>();
        ArrayList<Numero> nas = new ArrayList<Numero>();
        for (int i = 0; i < nVer.size(); i++) {
            aux = division(nVer.get(i), primosDivisores(nVer.get(i)));
            Numero n = new Numero(nVer.get(i), aux, null, 0);
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

    public static Grafo editarGrafo(Grafo mapa, ArrayList<Numero> vertices) {
        for (int i = 0; i < vertices.size(); i++) {
            Numero a = vertices.get(i);
            //Numero n = new Numero(a,primosDivisores(a),raiz,0);
            mapa.nuevoVertice(a);
        }
        ArrayList<Numero> V = mapa.vertices();
        for (int x = 0; x < V.size(); x++) {
            Numero O = V.get(x);
            Numero X = null;
            ArrayList<Integer> Prim = O.getPrimos();
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
}
