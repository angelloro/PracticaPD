package utilidades;

import java.util.Random;

/**
 *
 * @author Juan.Giralt
 */
public class MatricesOperaciones{
	

    public static String mostrar(int [] A){
        String s="";
        for(int n=0;n<A.length;n++) s=s+A[n]+" ";
        return s;
    }


    public static String mostrar(int[][] A){
        String s="";
        for(int fil=0;fil<A.length;fil++) s=s+mostrar(A[fil])+"\n";
        return s;
    }
    
    public static void mostrarMatriz(int[][] b) {
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                System.out.print("" + b[i][j]+" ");
            }
            System.out.println();
        }
    }
    
}//MatricesOperaciones
