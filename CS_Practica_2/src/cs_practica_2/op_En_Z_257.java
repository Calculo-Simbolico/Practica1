package cs_practica_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class op_En_Z_257 {
       
//***************OPERACIONES PARA NUMEROS EN Z_257******************

    /**
     * Calcula el inverso de un numero en Z_257 mediante el algoritmo extendido de Euclides.
     * @param num numero del cual se va a calcular su inverso.
     * @return el inverso del numero.
    */
    public static Integer inverso_en_Z_257(Integer num){
        Integer invs=0, x=0, y=0, x2=1, x1=0, y2=0, y1=1, q=0, r=0;
        Integer p=257;
        if(num==0){
            invs=0;
        }
        else{
            while(num>0){
                q = (p/num); 
                r = p - q*num; 
                x = x2-q*x1; 
                y = y2 - q*y1; 
                p = num; 
                num = r; 
                x2 = x1; 
                x1 = x; 
                y2 = y1;             
                y1 = y; 
            }
            invs=y2; 
      }
      return invs;
    }
    
    
    /**
     * Multiplicacion por el algoritmo escuela de dos polinomios de numeros en Z_257.                                              
     * @param polA polinomio que se va a multiplicar.
     * @param polB polinomio que se va a multiplicar.
     * @return polinomio de numeros en Z_257 con el resultado.
     */
    public static ArrayList<Integer> multiplicacion_escuela_en_Z_257(ArrayList<Integer> polA, ArrayList<Integer> polB){
        ArrayList<Integer> polC=new ArrayList<Integer>();
        
        //Añadir ceros al polinomio solucion
        for(int i=0;i<(polA.size()+polB.size())-1;i++)
             polC.add(0);
        
        for(int k=0;k<polB.size();k++)
            for(int i=0;i<polA.size();i++)
               polC.set(i+k, ((polA.get(i)*polB.get(k))+polC.get(i+k))% 257);

        return polC;
    }
       
   
    /**
     * Muestra por pantalla el polinomio  empezando por el coeficiente menos significativo 
     * @param pol el polinomio que se va a mostrar por pantalla 
     */
    public static void mostrar_polinomio_en_Z_257(ArrayList<Integer> pol){
        System.out.print("[");
        for(int i=0;i<pol.size()-1;i++)
            System.out.print(pol.get(i)+", ");
        System.out.print(pol.get(pol.size()-1));
        System.out.print("]\n");
   }
    
    
   /**
    * Lee de la entrada estándar un polinomio con el formato num1 num2 num3 ... empezando por el coeficiente menos significativo.
    * @return polinomio de numeros complejo
    */
   public static ArrayList<Integer> leer_polinomio_en_Z_257(){
                ArrayList<Integer> pol=new ArrayList<Integer>();
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
            if(numStr.charAt(i)!=' '){
                String dig="";
                while(i<numStr.length() &&  numStr.charAt(i)!=' '){
                   dig+=numStr.charAt(i);
                   i++;
                }
                Integer num=((Integer.parseInt(dig))% 257);
                pol.add(num);
            }
            else
                i++;
        }
        return pol;
   }
       
   
    public static void main(String[] args){
        
        BufferedReader lectura = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> polA=new ArrayList<Integer>();
        ArrayList<Integer> polB=new ArrayList<Integer>();
        ArrayList<Integer> polSolEsc=new ArrayList<Integer>();
        ArrayList<Integer> polSolFFT=new ArrayList<Integer>();
        
        System.out.print("\nINTRODUCE EL PRIMER POLINOMIO DE NUMEROS EN Z_257:\n");
        polA=leer_polinomio_en_Z_257();
        System.out.print("\nINTRODUCE EL SEGUNDO POLINOMIO DE NUMEROS EN Z_257:\n");
        polB=leer_polinomio_en_Z_257(); 
       
        polSolEsc=multiplicacion_escuela_en_Z_257(polA, polB);
        System.out.print("\nResultado en Z_257 de multiplicacion ESCUELA:\n");
        mostrar_polinomio_en_Z_257(polSolEsc);
        
        /**El grado de cada polinomio (n y m) es el tamaño del polinomio menos uno, ya que el indice de cada ArrayList 
         * nos indica el exponente al que esta elevado el coeficiente que ocupa esa posicion.
         * Por tanto hay que tener en cuenta rellenar los coeficientes nulos del polinomio con ceros en el ArrayList.
         */ 
        //polSolFFT=multiplicacion_FFT_en_Zp(polA, polB, polA.size()-1, polB.size()-1);
        System.out.print("\nResultado en Z_257 de multiplicacion rapida mediante FFT:\n");
        mostrar_polinomio_en_Z_257(polSolFFT); 
    }
}


