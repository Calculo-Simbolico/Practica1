
package cs_practica_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class op_En_Z_41 {
       
//***************OPERACIONES PARA NUMEROS EN Z_41*******************
    
      
    /**
     * Calcula el inverso de un numero en Z_41 mediante el algoritmo extendido de Euclides.
     * @param num numero del cual se va a calcular su inverso.
     * @return el inverso del numero.
     */
    public static Integer inverso_en_Z_41(Integer num){
        Integer invs=0, x=0, y=0, x2=1, x1=0, y2=0, y1=1, q=0, r=0;
        Integer mod=41;
        if(num==0){
            invs=0;
        }
        else{
            while(num>0){
                q = (mod/num); 
                r = mod - q*num; 
                x = x2-q*x1; 
                y = y2 - q*y1; 
                mod = num; 
                num = r; 
                x2 = x1; 
                x1 = x; 
                y2 = y1;             
                y1 = y; 
            }
            invs= y2; 
        }
        if(invs<0)
            return (41+invs); //Reducir modulo 41 por si el resultado es negativo
        else 
            return invs;
    }  
    
    
    /**
     * Factoriza un numero en Z_41 como producto de primos.
     * @param num numero el cual se va a factorizar.
     * @return un ArrayList con todos los factores del numero.
     */
    public static ArrayList<Integer> factorizar_en_Z_41(Integer num){
        ArrayList<Integer> factores=new ArrayList<Integer>();
        ArrayList<Integer> primos=new ArrayList<Integer>();
        Integer q;
        primos.add(2);primos.add(3);primos.add(5);primos.add(7);primos.add(11);primos.add(13);primos.add(17);primos.add(19);primos.add(23);primos.add(29);primos.add(31);primos.add(37);  
        
        int i=0;
        q=num;
        while(q > 1){
            if(q%primos.get(i)==0){ 
                q=q/primos.get(i);
                factores.add(primos.get(i));
            }
            else
                i++;
        }
        return factores;
    }
    
    
    /**
     * Calcula la raiz n-esima primitiva de la unidad en Z_41.
     * Se elige el 6 ya que (6^40/2)%41=38  (6^40/5)%41=10 los dos resultados distintos de 1, siendo 2 y 5 los dos factores
     * primos en que se descompone 40=p-1.
     * @return la raiz n-esima primitiva de la unidad.
     */
    public static Integer raiz_n_esima_primitiva_en_Z_41(Integer potDe2){
        Integer raiz;
        Integer generador = 6;
        Integer p_1=40;
        
        raiz=modulo_en_Z_41((int)Math.pow(generador, (p_1/potDe2)));
        
        return raiz;  
    }
        
     /**
     * Calcula el modulo de un numero en Z_41.
     * @param num numero del cual se va a calcular el modulo.
     * @return el modulo del numero.
     */
    public static Integer modulo_en_Z_41(Integer num){
        Integer p=41;
        Integer modulo = 0;
        
        modulo=num%p;        
        if(modulo<0)
            return p+modulo;
        else 
            return modulo;
    }
    
    
    /**
     * Multiplicacion por el algoritmo escuela de dos polinomios de numeros en Z_41.                                              
     * @param polA polinomio que se va a multiplicar.
     * @param polB polinomio que se va a multiplicar.
     * @return polinomio de numeros en Z_41 con el resultado.
     */
    public static ArrayList<Integer> multiplicacion_escuela_en_Z_41(ArrayList<Integer> polA, ArrayList<Integer> polB){
        ArrayList<Integer> polC=new ArrayList<Integer>();
        
        //Añadir ceros al polinomio solucion
        for(int i=0;i<(polA.size()+polB.size())-1;i++)
             polC.add(0);
        
        for(int k=0;k<polB.size();k++)
            for(int i=0;i<polA.size();i++)
               polC.set(i+k, ((polA.get(i)*polB.get(k))+polC.get(i+k))% 41);

        return polC;
    }
    
    
     /**
     * Transformada rapida de Fourier
     * @param N es el exponemte de la menor potencia de 2 tal que este numero sea mayor que la suma del grado de los dos polinomios que se
     * multiplicaran en multiplicacion_FFT_en_Z_41, dicho metodo hara llamadas a este.
     * @param omega una raiz 2^N -esima primitiva de la unidad que se le pasara desde multiplicacion_FFT_en_Z_41 el las llamadas.
     * @param polA debe ser un polinomio de numeros de grado menor que N
     * @return polinomio de numeros con la solucion
     */
    public static ArrayList<Integer> FFT_en_Z_41(Integer N, Integer omega, ArrayList<Integer> polA){
        ArrayList<Integer> A=new ArrayList<Integer>();
        ArrayList<Integer> B=new ArrayList<Integer>();
        ArrayList<Integer> C=new ArrayList<Integer>();
        ArrayList<Integer> polB=new ArrayList<Integer>();
        ArrayList<Integer> polC=new ArrayList<Integer>();
        
        //Inicializar A con ceros con un tamaño igual a 2^N 
        for(int i=0;i<((int)Math.pow(2, N));i++){
            A.add(0);
        }
        
        if((int)Math.pow(2, N)==1){ 
            A.set(0, polA.get(0));
        }
        else{
            for(int i=0;i<polA.size();i++){
                //Componentes para polB, que son los componentes pares de polA
                if(i%2==0){
                    polB.add(polA.get(i));
                }
                //Componentes para polC, que son los componentes impares de polA
                else{
                    polC.add(polA.get(i));
                }
            }
            B=FFT_en_Z_41(N-1, modulo_en_Z_41((int)Math.pow(omega, 2)), polB);
            C=FFT_en_Z_41(N-1, modulo_en_Z_41((int)Math.pow(omega, 2)), polC);

            for(int i=0;i<((int)Math.pow(2, N-1));i++){
                A.set(i, modulo_en_Z_41(B.get(i)+((int)Math.pow(omega, i)* C.get(i))));
                A.set(((int)Math.pow(2, N-1))+i, modulo_en_Z_41(B.get(i)-((int)Math.pow(omega, i)* C.get(i))));
            }
        }
        return A;
    }
    
    
    /**
     * Transformada inversa de Fourier, hace una llamada a FFT_en_Z_41(Integer potDe2, Double omega, ArrayList<ArrayList<Double>> polA)
     * pero pasandole por parametro la inversa de omega.
     * @param N es el exponemte de la menor potencia de 2 tal que este numero sea mayor que la suma del grado de los dos polinomios que se
     * multiplicaran en multiplicacion_FFT_en_Z_41, dicho metodo hara llamadas a este.
     * @param omega una raiz 2^N -esima primitiva de la unidad que se le pasara desde multiplicacion_FFT_en_Z_41 el las llamadas.
     * @param polC debe ser un polinomio de grado menor que N.
     * @return polinomio de numeros en Z_41 con la solucion.
     */
    public static ArrayList<Integer> IFFT_en_Z_41(Integer N, Integer omega, ArrayList<Integer> polC){
        ArrayList<Integer> polSol=new ArrayList<Integer>();
        ArrayList<Integer> polAux=new ArrayList<Integer>();
        
        //Calcular el inverso de omega
        Integer invOmega=inverso_en_Z_41(omega);
        //Llamar a 'FFT_en__Z_41' pero con el inverso de omega
        polAux=FFT_en_Z_41(N, invOmega, polC);
        //Multiplicar cada uno de los elementos del polinomio devuelto por 'FFT_en_Z_41' por el inverso de 2^N y el nuevo polinomio sera la solucion
        Integer invPotDe2=inverso_en_Z_41(((int) Math.pow( 2, N)));
        for(int i=0;i<polAux.size();i++){
            polSol.add(i, modulo_en_Z_41(invPotDe2*polAux.get(i)));
        }
        return polSol;
    }
    
    
    /**
     * Multiplicacion rapida de polinomios mediante FFT.
     * El grado de cada polinomio (n y m) es el tamaño del polinomio menos uno, ya que el indice de cada ArrayList 
     * nos indica el exponente al que esta elevado el coeficiente que ocupa esa posicion.
     * Por tanto hay que tener en cuenta rellenar los coeficientes nulos del polinomio con ceros en el ArrayList.
     * @param polA polinomio de numeros en Z_41 que se va a multiplicar.
     * @param polB polinomio de numeros en Z_41 que se va a multiplicar.
     * @param m grado del polinomio polA.
     * @param n grado del polinomio polB.
     * @return polinomio de numeros en Z_41 solucion.
     */
    public static ArrayList<Integer> multiplicacion_FFT_en_Z_41(ArrayList<Integer> polA, ArrayList<Integer> polB, Integer m, Integer n){
        ArrayList<Integer> polSol=new ArrayList<Integer>();
        ArrayList<Integer> A=new ArrayList<Integer>();
        ArrayList<Integer> B=new ArrayList<Integer>();
        ArrayList<Integer> C=new ArrayList<Integer>();
        Integer omega=0;
        
        //Calcular primer entero tal que m+n < 2^N (¡¡¡Mayor estricto!!!)
        Integer N=0;
        while(((m+n) -(int) Math.pow( 2, N))>0){
            N++;
        }
        if(((int) Math.pow( 2, N))==(n+m))
            N++;

        //Calcular omega=raiz 2^N-esima primitiva de la unidad
        omega=raiz_n_esima_primitiva_en_Z_41(((int) Math.pow( 2, N)));    System.out.print("\nN="+N+" omega="+omega);
        
        //Antes de llamar a 'FFT_en__Z_41' hay que añadir ceros al final de polA y polB hasta que tengan un tamaño igual a 2^N
        for(int i=polA.size();i<((int) Math.pow( 2, N));i++)
            polA.add(i,0);
        for(int i=polB.size();i<((int) Math.pow( 2, N));i++)
            polB.add(i,0);
        
        A=FFT_en_Z_41(N, omega, polA);
        B=FFT_en_Z_41(N, omega, polB);

        for(int i=0;i<((int) Math.pow( 2, N));i++){
            C.add(i, modulo_en_Z_41(A.get(i)*B.get(i)));
        }       
        //Llamada a la transformada de Fourier Inversa.
        polSol=IFFT_en_Z_41(N, omega, C);

        return polSol;
    }
    
     /**
     * Elimina los ceros a la derecha de un polinomio
     * @param pol el polinomio que se va a modificar.
     */
    public static void quitar_ceros_en_Z_41(ArrayList<Integer> pol){
        if(pol.size()>0)
            for(int i=pol.size()-1;pol.get(i)==0;i--)
                pol.remove(i);
   }
       
   
    /**
     * Muestra por pantalla el polinomio  empezando por el coeficiente menos significativo 
     * @param pol el polinomio que se va a mostrar por pantalla 
     */
    public static void mostrar_polinomio_en_Z_41(ArrayList<Integer> pol){
        System.out.print("[");
        for(int i=0;i<pol.size()-1;i++)
            System.out.print(pol.get(i)+", ");
        System.out.print(pol.get(pol.size()-1));
        System.out.print("]");
   }
    
    
   /**
    * Lee de la entrada estándar un polinomio con el formato num1 num2 num3 ... empezando por el coeficiente menos significativo.
    * @return polinomio de numeros complejo
    */
   public static ArrayList<Integer> leer_polinomio_en_Z_41(){
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
                Integer num=((Integer.parseInt(dig))% 41);
                pol.add(num);
            }
            else
                i++;
        }
        return pol;
   }
       

    public static void main(String[] args){
     
        ArrayList<Integer> polSolEsc=new ArrayList<Integer>();
        ArrayList<Integer> polSolFFT=new ArrayList<Integer>();    
        Random rnd = new Random();
<<<<<<< HEAD
    	rnd.setSeed(333333);
        /*
=======
    	rnd.setSeed(20011974);
        /**
>>>>>>> 803eacbbe4f05b07dabb682922456e98f607191d
        System.out.print("\nINTRODUCE EL PRIMER POLINOMIO DE NUMEROS EN Z_41:\n");
        polA=leer_polinomio_en_Z_41();
        System.out.print("\nINTRODUCE EL SEGUNDO POLINOMIO DE NUMEROS EN Z_41:\n");
        polB=leer_polinomio_en_Z_41(); 
        
        if((polA.size()-1)+(polB.size()-1)>=8){
                System.out.print("\nNo existe en Z_41 una raiz de orden mayor a 8, la suma de los grados de los dos polinomios debe ser menor que 8.\n");
                return;
        }
        */
        for(int i=1;i<=5;i++){ 
            ArrayList<Integer> polA=new ArrayList<Integer>();
            ArrayList<Integer> polB=new ArrayList<Integer>();
            
            for(int j=0;j<i;j++){
                polA.add(j, modulo_en_Z_41(rnd.nextInt()));
<<<<<<< HEAD
                
                if(j!=4)
                polB.add(j, modulo_en_Z_41(rnd.nextInt()));
            }
            System.out.print("\n\npolA="+polA+"      Grado de polA="+(polA.size()-1));
            System.out.print("\npolB="+polB+"        Grado de polB="+(polB.size()-1));
=======
             
            for(int j=0;j<grad;j++)
                polB.add(j, modulo_en_Z_41(rnd.nextInt()));
            
            System.out.print("\n--> Grado de polA="+(polA.size()-1)+"  --> Grado de polB="+(polB.size()-1));
>>>>>>> 803eacbbe4f05b07dabb682922456e98f607191d

            polSolEsc=multiplicacion_escuela_en_Z_41(polA, polB);
            if(polSolEsc.get(polSolEsc.size()-1) == 0)
                  quitar_ceros_en_Z_41(polSolEsc);
            System.out.print("\nResultado en Z_41 de multiplicacion ESCUELA:\n");
            mostrar_polinomio_en_Z_41(polSolEsc);

            /*El grado de cada polinomio (n y m) es el tamaño del polinomio menos uno, ya que el indice de cada ArrayList 
             * nos indica el exponente al que esta elevado el coeficiente que ocupa esa posicion.
             * Por tanto hay que tener en cuenta rellenar los coeficientes nulos del polinomio con ceros en el ArrayList.
            */ 
            polSolFFT=multiplicacion_FFT_en_Z_41(polA, polB, polA.size()-1, polB.size()-1);
            if(polSolFFT.get(polSolFFT.size()-1) == 0)
                  quitar_ceros_en_Z_41(polSolFFT);
            System.out.print("\nResultado en Z_41 de multiplicacion rapida mediante FFT:\n");
            mostrar_polinomio_en_Z_41(polSolFFT); 
        }
    }
}

