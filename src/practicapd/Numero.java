package practicapd;

import java.util.*;

/**
 * @author Ángel Loro y Ángel Sánchez
 **/

public class Numero {
    int numero;
    ArrayList<Integer> adyacentes;
    Numero padre;    
    String ganador;
    
    public Numero(){
        
    }
    
    public Numero(int numero, Numero padre){
        this.numero = numero;
        this.adyacentes = null;
        this.padre = padre;        
        this.ganador = "X";
    }

    public int getNumero() {
        return numero;
    }

    public ArrayList<Integer> getAdyacentes() {
        return adyacentes;
    }

    public Numero getPadre() {
        return padre;
    }

    public String getGanador() {
        return ganador;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setAdyacentes(ArrayList primos) {
        this.adyacentes = primos;
    }

    public void setPadre(Numero padre) {
        this.padre = padre;
    }
    
    public void setGanador(String ganador) {
        this.ganador = ganador;
    }
    
    private ArrayList<Integer> sacarPrimos(int num) { //Saca los primos
        ArrayList<Integer> primos = new ArrayList<>(); //Lista con los primos menores que num
        int cont;
        for (int i = 1; i <= num; i++) {
            cont = 0;
            for (int j = 1; j <= i; j++) { //Comprobamos si 'i' es primo
                if (i % j == 0) {
                    cont++;
                }
            }
            if (cont == 2) { //Si solo es divisible por 1 y el propio numero es primo 
                primos.add(i);
            }
        }
        return primos;
    }

    public ArrayList<Integer> sacarDivisores(int num) { //Saca los divisores
        ArrayList<Integer> primos = sacarPrimos(num); //Lista con los primos menores que num
        ArrayList<Integer> divisores = new ArrayList<>(); //Lista para guardar los divisores
        int pot;
        
        for (int i = 0; i < primos.size(); i++) { 
            pot = 1;
            while (num % primos.get(i) == 0) { 
                divisores.add((int) Math.pow(primos.get(i), pot)); 
                num /= primos.get(i);
                pot++;
            }
        }
        return divisores;
    }
    
    public ArrayList<Integer> sacarSucesores(int num) { //Saca los sucesores
        ArrayList<Integer> divisores = sacarDivisores(num); //Lista con los divisores de num
        ArrayList<Integer> sucesores = new ArrayList<>(); //Lista para guardar los sucesores
        for (int i = 0; i < divisores.size(); i++) {
            sucesores.add(num / divisores.get(i));
        }        
        return sucesores;
    }
    
    @Override
    public String toString(){
        return "El numero: " + numero + "es " + ganador;
    }
}
