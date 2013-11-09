
package cs_practica_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CS_Practica_2 {
    
    //***************OPERACIONES PARA NUMEROS COMPLEJOS********************
    
    /**
     * Multiplicación complejos --> (a, b)*(c, d) = (ac - bd, ad + bc) 
     * @param complejoA numero complejo que se va a multiplicar.
     * @param complejoB numero complejo que se va a multiplicar.
     * @return numero complejo solucion.
     */
    public static ArrayList<Double> mult_C(ArrayList<Double> complejoA, ArrayList<Double> complejoB){
        ArrayList<Double> complejoC=new ArrayList<Double>();
             
        complejoC.add(0, (complejoA.get(0)*complejoB.get(0)) - (complejoA.get(1)*complejoB.get(1)));
        complejoC.add(1, (complejoA.get(0)*complejoB.get(1)) + (complejoA.get(1)*complejoB.get(0)));
        
        return complejoC;
    }
    
    
    /**
     * Suma complejos --> (a, b)+(c, d) = (a+c, b+d)  
     * @param complejoA numero complejo que se va a sumar.
     * @param complejoB numero complejo que se va a sumar.
     * @return numero complejo solucion.
     */
    public static ArrayList<Double> sum_C(ArrayList<Double> complejoA, ArrayList<Double> complejoB){
        ArrayList<Double> complejoC=new ArrayList<Double>();
             
        complejoC.add(0,complejoA.get(0)+complejoB.get(0));
        complejoC.add(1,complejoA.get(1)+complejoB.get(1));
        
        return complejoC;
    }
    
    
    /**
     * Resta complejos --> (a, b)-(c, d) = (a-c, b-d) 
     * @param complejoA numero complejo que se va a restar.
     * @param complejoB numero complejo que se va a restar.
     * @return numero complejo solucion.
     */
    public static ArrayList<Double> rest_C(ArrayList<Double> complejoA, ArrayList<Double> complejoB){
        ArrayList<Double> complejoC=new ArrayList<Double>();
             
        complejoC.add(0,complejoA.get(0)-complejoB.get(0));
        complejoC.add(1,complejoA.get(1)-complejoB.get(1));
        
        return complejoC;
    }
    
    
    /**
     * Multiplicacion por el algoritmo escuela de dos polinomios de numeros complejos en una variable.  
     * @param polA polinomio de numeros complejos que se va a multiplicar, la posicion 0 del ArrayList anidado es la parte real 
     * y la posicion 1 es la parte imaginaria. 
     * @param polB polinomio de numeros complejos que se va a multiplicar, la posicion 0 del ArrayList anidado es la parte real 
     * y la posicion 1 es la parte imaginaria.
     * @return polinomio de numeros complejos con el resultado.
     */
    public static ArrayList<ArrayList<Double>> multiplicacion_escuela_en_C(ArrayList<ArrayList<Double>> polA, ArrayList<ArrayList<Double>> polB){
        ArrayList<ArrayList<Double>> polC=new ArrayList<ArrayList<Double>>();
        
        //Añadir ceros al polinomio solucion
        for(int i=0;i<polA.size();i++){
            ArrayList<Double>complejo=new ArrayList<>();
            complejo.add(0, 0.0);
            complejo.add(1, 0.0);
            polC.add(complejo);
        }
        
        for(int k=0;k<polB.size();k++){
            for(int i=0;i<polA.size();i++){
               ArrayList<Double> complejoA =  polA.get(i);
               ArrayList<Double> complejoB =  polB.get(k);
               ArrayList<Double> complejoC = polC.get(i+k);
               ArrayList<Double> multC = mult_C(complejoA, complejoB);
               polC.add(i+k, sum_C(multC, complejoC));
            }
        }
        return polC;
    }
    

    public static ArrayList<ArrayList<Double>> FFT_en_C(Integer N, Integer omega, ArrayList<ArrayList<Double>> polA){
        ArrayList<ArrayList<Double>> A=new ArrayList<ArrayList<Double>>();
        
        return A;
    }
    
    
    public static ArrayList<ArrayList<Double>> IFFT_en_C(Integer N, Integer omega, ArrayList<ArrayList<Double>> polA){
        ArrayList<ArrayList<Double>> A=new ArrayList<ArrayList<Double>>();
        
        return A;
    }
    
    
    public static ArrayList<ArrayList<Double>> multiplicacion_FFT_en_C(ArrayList<Integer> polA, ArrayList<Integer> polB, Integer m, Integer n){
        ArrayList<ArrayList<Double>> polC=new ArrayList<ArrayList<Double>>();
        
        return polC;
    }
    
    
    public static void mostrar_polinomio(ArrayList<Integer> pol){
        for(int i=0;i<pol.size();i++)
            System.out.print(pol.get(i)+"x^"+i+" + ");
   }
    
    
   /**
    * Lee de un fichero de entrada o de la entrada estándar un polinomio de numeros complejos
    * @return polinomio de numeros complejo
    */
   public static ArrayList<Integer> leer_polinomio(){
               InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader (isr); 
                String input=new String();
                String numStr;
        try{
            input = br.readLine();
        }catch(java.io.IOException e){
            System.out.print("\nExcepcion ocurrida "+e.getMessage());     
        }
        numStr=input;
        Character numChar;
        ArrayList<Integer> pol=new ArrayList<Integer>();
        for(int i=0;i<numStr.length();i++){       
            numChar=numStr.charAt(i);
            pol.add(Integer.parseInt(numChar.toString()));
        }
        return pol;
   }
   
    
    public static void main(String[] args) {
        
        ArrayList<Integer> polA=new ArrayList<>();
        ArrayList<Integer> polB=new ArrayList<>();
        ArrayList<Integer> polC=new ArrayList<>();
         

        System.out.print("\nINTRODUCE EL PRIMER POLINOMIO\n");
        polA=leer_polinomio();
        System.out.print("\nINTRODUCE EL SEGUNDO POLINOMIO\n");
        polB=leer_polinomio();            
       
        //mostrar_polinomio(polC);
    }
}
