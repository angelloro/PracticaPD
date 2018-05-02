package practicapd;

import java.util.*;

/**
 * @author Ángel Loro y Ángel Sánchez
 **/

public class Numero {
    private int numero;
    private ArrayList<Numero> adyacentes;       
    private String ganador;
    private Numero padre;
    
    
    public Numero(){
        
    }
    
    public Numero(int num){
        this.numero = num;
        this.adyacentes = sacarSucesores();               
        this.ganador = "X";
        this.padre = null;
    }

    public int getNumero() {
        return this.numero;
    }

    public ArrayList<Numero> getAdyacentes() {
        return this.adyacentes;
    }

    public String getGanador() {
        return this.ganador;
    }
    
    public Numero getPadre(){
        return this.padre;
    }
    
    public void setNumero(int newNumero) {
        this.numero = newNumero;
    }

    public void setAdyacentes(ArrayList newPrimos) {
        this.adyacentes = newPrimos;
    }
   
    public void setGanador(String newGanador) {
        this.ganador = newGanador;
    }
    
    public void setPadre(Numero newPadre){
        this.padre = newPadre;
    }
    
    public void addAdyacente(Numero newNumero){
        adyacentes.add(newNumero);
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

    public ArrayList<Integer> sacarDivisores(int num) { //Saca los divisores
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
    
    public ArrayList<Numero> sacarSucesores() { //Saca los sucesores
        ArrayList<Integer> divisores = sacarDivisores(numero); //Lista con los divisores de num
        ArrayList<Numero> sucesores = new ArrayList<>(); //Lista para guardar los sucesores
        for (int i = 0; i < divisores.size(); i++) {
            Numero nuevo = new Numero(numero / divisores.get(i));
            sucesores.add(nuevo);
        }        
        return sucesores;
    }
    
    public ArrayList<Integer> sacarF(int num) { //Saca los divisores
        ArrayList<Integer> primos = sacarPrimos(); //Lista con los primos menores que num
        ArrayList<Integer> divisores = new ArrayList<>(); //Lista para guardar los divisores        
        
        for (int i = 0; i < primos.size(); i++) { 
            
            while (num % primos.get(i) == 0) { 
                divisores.add(primos.get(i)); 
                num /= primos.get(i);                
            }
        }
        return divisores;
    }
    
    @Override
    public String toString(){
        return "El numero: " + numero + " es " + ganador;
    }
}
