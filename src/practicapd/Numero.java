package practicapd;

import java.util.*;

/**
 * @author Ángel Loro y Ángel Sánchez
 **/

public class Numero {
    int numero;
    ArrayList<Integer> adyacentes;       
    String ganador;
    
    public Numero(){
        
    }
    
    public Numero(int numero){
        this.numero = numero;
        this.adyacentes = sacarSucesores();               
        this.ganador = "X";
    }

    public int getNumero() {
        return numero;
    }

    public ArrayList<Integer> getAdyacentes() {
        return adyacentes;
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
   
    public void setGanador(String ganador) {
        this.ganador = ganador;
    }
    
    private ArrayList<Integer> sacarPrimos() { //Saca los primos
        ArrayList<Integer> primos = new ArrayList<>(); //Lista con los primos menores que num
        int cont;
        for (int i = 1; i <= numero; i++) {
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

    private ArrayList<Integer> sacarDivisores(int num) { //Saca los divisores
        ArrayList<Integer> primos = sacarPrimos(); //Lista con los primos menores que num
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
    
    public ArrayList<Integer> sacarSucesores() { //Saca los sucesores
        ArrayList<Integer> divisores = sacarDivisores(numero); //Lista con los divisores de num
        ArrayList<Integer> sucesores = new ArrayList<>(); //Lista para guardar los sucesores
        for (int i = 0; i < divisores.size(); i++) {
            sucesores.add(numero / divisores.get(i));
        }        
        return sucesores;
    }
    
    @Override
    public String toString(){
        return "El numero: " + numero + " es " + ganador;
    }
}
