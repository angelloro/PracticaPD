/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicapd;

import java.util.ArrayList;

/**
 *
 * @author Angel
 */
public class PracticaPD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    public ArrayList primos(int num){
        ArrayList primos= new ArrayList();
        for (int i=0;i<=num;i++){
            if (num%i==0){
                primos.add(i);
            }
        }
        
        
        return primos;
        
    }
    
}
