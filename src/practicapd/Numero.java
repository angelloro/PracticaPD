/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicapd;
import java.util.*;

/**
 *
 * @author Angel
 */
public class Numero {
    int numero;
    ArrayList primos;
    Numero padre;
    int coste;
    public Numero(int numero,ArrayList primos,Numero padre,int coste){
        this.numero=numero;
        this.primos=primos;
        this.padre=padre;
        this.coste=coste;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }

    public int getCoste() {
        return coste;
    }

    public int getNumero() {
        return numero;
    }

    public ArrayList getPrimos() {
        return primos;
    }

    public Numero getPadre() {
        return padre;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setPrimos(ArrayList primos) {
        this.primos = primos;
    }

    public void setPadre(Numero padre) {
        this.padre = padre;
    }
    
}
