package practicapd;

import utilidades.*;
import java.util.ArrayList;


/**
 * @author Ángel Loro y Ángel Sánchez
 **/
public class PracticaPD {

    public static void main(String[] args) {
        int num = 111;
        ArrayList pa = primos(num);
        /*for(int i = 0 ; i<pa.size() ; i++){    
            leer.pln(""+pa.get(i));
            
        }*/
        ArrayList pad = primosDivisores(num, pa);
        for(int i = 0 ; i<pad.size() ; i++){    
            leer.pln(""+pad.get(i));
            
        }
    }
    
    public static ArrayList primos(int num){
        ArrayList primos= new ArrayList();
 
        primos.add(2);
        primos.add(3);
        primos.add(5);
        for(int i = 2 ; i <= num ; i++){
            if(i%2!=0 && i%3!=0 && i%5!=0){
                primos.add(i);
            }
        }
        return primos;
        
    }
    
    public static ArrayList primosDivisores(int num, ArrayList primos){
        ArrayList primosDivisores = new ArrayList();
        int cont = 1;
        for(int i = 0 ; i < primos.size() ; i++){
            cont = 1;
            while(num%(int)primos.get(i) == 0){
                
                primosDivisores.add((int)Math.pow((int)primos.get(i), cont));
                num /= (int)primos.get(i);
                cont++;
            }
        }
        return primosDivisores;
    }
}
