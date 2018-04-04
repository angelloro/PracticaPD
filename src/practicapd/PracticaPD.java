package practicapd;

import utilidades.*;
import java.util.ArrayList;

/**
 * @author Ángel Loro y Ángel Sánchez
 *
 */
public class PracticaPD {

    private static Grafo<Numero, Integer> mapa;

    public static void main(String[] args) {
        int num = 12;
        //ArrayList pa = primos(num);
        /*for(int i = 0 ; i<pa.size() ; i++){    
            leer.pln(""+pa.get(i));
            
        }*/
        ArrayList pad = primosDivisores(num, primos(num));
        /*for (int i = 0; i < pad.size(); i++) {
            leer.pln("" + pad.get(i));
                
        }*/
        Grafo<Numero,Integer> g;
        Numero raiz=new Numero(num,pad,null,0);        
        forward(raiz);
    }

    public static void forward(Numero raiz) {
        ArrayList<Numero> anchura = new ArrayList<Numero>();
        raiz.setCoste(0);
        anchura.add(raiz);
        
       
        for(int i = 0 ; i < raiz.getPrimos().size() ; i++){
            int a = (int)raiz.getPrimos().get(i);
            Numero n = new Numero(a,primosDivisores(a, primos(a)),raiz,0);
            mapa.nuevoVertice(n);
        }
        
        leer.pln(mapa.toString());
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
                if (mejor(estoy.getCoste() + mapa.peso(estoy, voy), voy.getCoste())) {
                    voy.setCoste(estoy.getCoste() + mapa.peso(estoy, voy));
                    voy.setPadre(estoy);
                }
            }
            c++;
        }
    }

    private static boolean mejor(int a, int b) {
        return a < b;
    }

    public static ArrayList primos(int num) {
        ArrayList primos = new ArrayList();

        primos.add(2);
        primos.add(3);
        primos.add(5);
        for (int i = 2; i <= num; i++) {
            if (i % 2 != 0 && i % 3 != 0 && i % 5 != 0) {
                primos.add(i);
            }
        }
        return primos;

    }

    public static ArrayList primosDivisores(int num, ArrayList primos) {
        ArrayList primosDivisores = new ArrayList();
        int cont = 1;
        for (int i = 0; i < primos.size(); i++) {
            cont = 1;
            while (num % (int) primos.get(i) == 0) {

                primosDivisores.add((int) Math.pow((int) primos.get(i), cont));
                num /= (int) primos.get(i);
                cont++;
            }
        }
        return primosDivisores;
    }
}
