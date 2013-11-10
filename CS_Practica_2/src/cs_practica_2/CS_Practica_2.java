
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
    public static ArrayList<Double> mult_en_C(ArrayList<Double> complejoA, ArrayList<Double> complejoB){
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
    public static ArrayList<Double> sum_en_C(ArrayList<Double> complejoA, ArrayList<Double> complejoB){
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
    public static ArrayList<Double> rest_en_C(ArrayList<Double> complejoA, ArrayList<Double> complejoB){
        ArrayList<Double> complejoC=new ArrayList<Double>();
             
        complejoC.add(0,complejoA.get(0)-complejoB.get(0));
        complejoC.add(1,complejoA.get(1)-complejoB.get(1));
        
        return complejoC;
    }
    
    /**
     * Producto de complejo por un escalar--> (a, b)*esc = (a*esc, b*esc) 
     * @param complejoA numero complejo que se va a restar.
     * @param complejoB numero complejo que se va a restar.
     * @return numero complejo solucion.
     */
    public static ArrayList<Double> prodPorEsc_en_C(Integer escalar, ArrayList<Double> complejoA){
        ArrayList<Double> complejoC=new ArrayList<Double>();
             
        complejoC.add(0,complejoA.get(0)*escalar);
        complejoC.add(1,complejoA.get(1)*escalar);
        
        return complejoC;
    }
    
    
    /**
     * Multiplicacion por el algoritmo escuela de dos polinomios de numeros complejos en una variable. Ejemplo: 
     * polA --> (3,1)(-2,0)(10,0)   
     * polB --> (-12,3)(9,2)
     * ((3,1i) + (-2,0i)x + (10,0i)x^2) * ((-12,3i) + (9,2i)x) = (-39,-3i) + (25,15i)x + (24,-6i)x + (-18,-4i)x^2 + (-120,30i)x^2 + (90,20i)x^3 = (-39,-3i) + (49,9i)x + (-138,26i)x^2 + (90,20i)x^3
     * polC --> (-39,-3)(49,9)(-138,26)(90,20)                                               
     * @param polA polinomio de numeros complejos que se va a multiplicar, la posicion 0 del ArrayList anidado es la parte real 
     * y la posicion 1 es la parte imaginaria. 
     * @param polB polinomio de numeros complejos que se va a multiplicar, la posicion 0 del ArrayList anidado es la parte real 
     * y la posicion 1 es la parte imaginaria.
     * @return polinomio de numeros complejos con el resultado.
     */
    public static ArrayList<ArrayList<Double>> multiplicacion_escuela_en_C(ArrayList<ArrayList<Double>> polA, ArrayList<ArrayList<Double>> polB){
        ArrayList<ArrayList<Double>> polC=new ArrayList<ArrayList<Double>>();
        
        //Añadir ceros al polinomio solucion
        for(int i=0;i<polA.size()+polB.size()-1;i++){
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
               ArrayList<Double> multC = mult_en_C(complejoA, complejoB);
               polC.set(i+k, sum_en_C(multC, complejoC));
            }
        }
        return polC;
    }
	
    
    /**
     * 
     * @param N es la menor potencia de 2 tal que este numero sea mayor que la suma del grado de los dos polinomios que se
     * multiplicaran en multiplicacion_FFT_en_C, dicho metodo hara llamadas a este.
     * @param omega una raiz 2^N -esima primitiva de la unidad que se le pasara desde multiplicacion_FFT_en_C el las llamadas.
     * @param polA debe ser un polinomio de numeros complejos de grado menor que N
     * @return polinomio de numeros complejos con la solucion
     */
    public static ArrayList<ArrayList<Double>> FFT_en_C(Integer N, Integer omega, ArrayList<ArrayList<Double>> polA){
        ArrayList<ArrayList<Double>> A=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> B=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> C=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> polB=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> polC=new ArrayList<ArrayList<Double>>();
        ArrayList<Double>complejo=new ArrayList<>();
        
        if(N==1){
            complejo.add(0, polA.get(0).get(0));
            complejo.add(1, polA.get(0).get(1));
            A.add(0, complejo);
        }
        else{
            for(int i=0;i<(N/2)-1;i++){
                //Componentes para polB, que son los componentes pares de polA
                if(i%2==0){
                    complejo.add(0, polA.get(i).get(0));
                    complejo.add(1, polA.get(i).get(1));
                    polB.add(complejo);
                }
                //Componentes para polC, que son los componentes impares de polA
                else{
                    complejo.add(0, polA.get(i).get(0));
                    complejo.add(1, polA.get(i).get(1));
                    polC.add(complejo);
                }
            }
            
            A=FFT_en_C(N/2, (int)Math.pow(omega, 2), polB);
            B=FFT_en_C(N/2, (int)Math.pow(omega, 2), polC);
            
            for(int i=0;i<(N/2)-1;i++){
                complejo.add(0, sum_en_C(B.get(i), prodPorEsc_en_C((int)Math.pow(omega, i), C.get(i))).get(0));
                complejo.add(1, sum_en_C(B.get(i), prodPorEsc_en_C((int)Math.pow(omega, i), C.get(i))).get(1));
                A.add(i, complejo);
                complejo.add(0, rest_en_C(B.get(i), prodPorEsc_en_C((int)Math.pow(omega, i), C.get(i))).get(0));
                complejo.add(1, rest_en_C(B.get(i), prodPorEsc_en_C((int)Math.pow(omega, i), C.get(i))).get(1));
                A.add((N/2)+i, complejo);
            }
        }
        return A;
    }
    
    
    /**
     * 
     * @param N
     * @param omega
     * @param polA
     * @return 
     */
    public static ArrayList<ArrayList<Double>> IFFT_en_C(Integer N, Integer omega, ArrayList<ArrayList<Double>> polA){
        ArrayList<ArrayList<Double>> A=new ArrayList<ArrayList<Double>>();
        
        return A;
    }
    
    
    /**
     * 
     * @param polA
     * @param polB
     * @param m
     * @param n
     * @return 
     */
    public static ArrayList<ArrayList<Double>> multiplicacion_FFT_en_C(ArrayList<Integer> polA, ArrayList<Integer> polB, Integer m, Integer n){
        ArrayList<ArrayList<Double>> polC=new ArrayList<ArrayList<Double>>();
        
        return polC;
    }
    
    
    /**
     * Muestra por pantalla el polinomio de complejos empezando por el coeficiente menos significativo y mostrando entre
     * parentesis la parte real (a la izquierda) y la parte imaginaria (a la derecha).
     * @param pol el polinomio de complejos que se va a mostrar por pantalla 
     */
    public static void mostrar_polinomio_en_C(ArrayList<ArrayList<Double>> pol){
        for(int i=0;i<pol.size();i++)
            System.out.print("("+pol.get(i).get(0)+", "+pol.get(i).get(1)+") ");
        System.out.print("\n");
   }
    
    
   /**
    * Lee de la entrada estándar un polinomio de numeros complejos con el formato (real1,imag1)(real2,imag2)(real3,imag3)......
    * empezando por el coeficiente menos significativo.
    * @return polinomio de numeros complejo
    */
   public static ArrayList<ArrayList<Double>> leer_polinomio_en_C(){
                ArrayList<ArrayList<Double>> pol=new ArrayList<ArrayList<Double>>();
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
        int i=0;
        while(i<numStr.length()){       
            if(numStr.charAt(i)=='('){
                ArrayList<Double>complejo=new ArrayList<>();
                i++;
                String real="";
                while(numStr.charAt(i)!=',' && i<numStr.length()){
                   real+=numStr.charAt(i);
                   i++;
                }
                Double valor=Double.parseDouble(real);
                complejo.add(0,valor);
                
                i++;
                String imag="";
                while(numStr.charAt(i)!=')' && i<numStr.length()){
                   imag+=numStr.charAt(i);
                   i++;
                }
                valor=Double.parseDouble(imag);
                complejo.add(1,valor);
                
                pol.add(complejo);
            }
            else
                i++;
        }
        return pol;
   }
   
   
   //***************OPERACIONES PARA NUMEROS EN Z_41******************
   
   
   
   
   
   
   //***************OPERACIONES PARA NUMEROS EN Z_257******************
   
   
   
   
   
   //***************OPERACIONES PARA NUMEROS EN Z_p********************
   
    
   
   
    public static void main(String[] args) {
        
        ArrayList<ArrayList<Double>> polA=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> polB=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> polC=new ArrayList<ArrayList<Double>>();
         

        System.out.print("\nINTRODUCE EL PRIMER POLINOMIO EN C\n");
        polA=leer_polinomio_en_C();
        System.out.print("\nINTRODUCE EL SEGUNDO POLINOMIO EN C\n");
        polB=leer_polinomio_en_C();            
       
        polC=multiplicacion_escuela_en_C(polA, polB);
        mostrar_polinomio_en_C(polC);
    }
}
