package cs_practica_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.math.BigInteger;

public class op_En_Z_257 {
       
//***************OPERACIONES PARA NUMEROS EN Z_257******************

    /**
     * Calcula el inverso de un numero en Z_257 mediante el algoritmo extendido de Euclides.
     * @param num numero del cual se va a calcular su inverso.
     * @return el inverso del numero.
    */
    public static Integer inverso_en_Z_257(Integer num){
        Integer invs=0, x=0, y=0, x2=1, x1=0, y2=0, y1=1, q=0, r=0;
        Integer mod=257;
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
            return (257+invs); //Reducir modulo 257 por si el resultado es negativo
        else 
            return invs;
    }  
    
    
     /**
     * Factoriza un numero en Z_257 como producto de primos.
     * @param num numero el cual se va a factorizar.
     * @return un ArrayList con todos los factores del numero.
     */
    public static ArrayList<Integer> factorizar_en_Z_257(){
        ArrayList<Integer> factores=new ArrayList<Integer>();
        ArrayList<Integer> primos=new ArrayList<Integer>();
        Integer q;
        primos.add(2);primos.add(3);primos.add(5);primos.add(7);primos.add(11);primos.add(13);primos.add(17);primos.add(19);primos.add(23);primos.add(29);primos.add(31);primos.add(37);  
        
        int i=0;
        q=256;
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
     * Calcula la raiz n-esima primitiva de la unidad en Z_257.
     * @return la raiz n-esima primitiva de la unidad.
     */
    public static Integer raiz_n_esima_primitiva_en_Z_257(Integer N){
        Integer raiz=0;
<<<<<<< HEAD

        boolean salte=false;
        for(int i=2;i<257 && salte==false;i++){
            if(Math.pow(i, Math.pow(2,N))%257 == 1){
                raiz=i;
                salte=true;
            }
        }
=======
        
        if(N==1)
            raiz=256;
        else if(N==2)
            raiz=16;
        else if(N==3)
            raiz=4;
        else if(N==4)
            raiz=2;     
        else if(N==5)
            System.out.print("No existe en Z_257 ninguna raiz de orden="+N+".\n");
        else if(N==6)
            System.out.print("No existe en Z_257 ninguna raiz de orden="+N+".\n");
        else if(N==7)
            System.out.print("No existe en Z_257 ninguna raiz de orden="+N+".\n");
        else if(N==8)
            System.out.print("No existe en Z_257 ninguna raiz de orden="+N+".\n");
>>>>>>> 7fe3f0d1ea26c892395c245b0c67bb420429468f
        
        return raiz;  
    }
    
    
     /**
     * Calcula el modulo de un numero en Z_257.
     * @param num numero del cual se va a calcular el modulo.
     * @return el modulo del numero.
     */
    public static Integer modulo_en_Z_257(Integer num){
        Integer p=257;
        Integer modulo = 0;
        
        modulo=num%p;
        
        if(modulo<0)
            return p+modulo;
        else 
            return modulo;
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
               polC.set(i+k, modulo_en_Z_257((polA.get(i)*polB.get(k))+polC.get(i+k)));

        return polC;
    }
    
    
      /**
     * Transformada rapida de Fourier
     * @param N es el exponemte de la menor potencia de 2 tal que este numero sea mayor que la suma del grado de los dos polinomios que se
     * multiplicaran en multiplicacion_FFT_en_Z_257, dicho metodo hara llamadas a este.
     * @param omega una raiz 2^N -esima primitiva de la unidad que se le pasara desde multiplicacion_FFT_en_Z_257 el las llamadas.
     * @param polA debe ser un polinomio de numeros de grado menor que N
     * @return polinomio de numeros con la solucion
     */
    public static ArrayList<Integer> FFT_en_Z_257(Integer N, Integer omega, ArrayList<Integer> polA){
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
            B=FFT_en_Z_257(N-1, (int)(Math.pow(omega, 2)%257), polB);
            C=FFT_en_Z_257(N-1, (int)(Math.pow(omega, 2)%257), polC);

            for(int i=0;i<((int)Math.pow(2, N-1));i++){
                A.set(i, (int)(B.get(i)+( (Math.pow(omega, i)* C.get(i))%257))%257);
                A.set(((int)Math.pow(2, N-1))+i, (int)(B.get(i)-( (Math.pow(omega, i)* C.get(i))%257))%257);
            }
        }
        return A;
    }
    
    
    /**
     * Transformada inversa de Fourier, hace una llamada a FFT_en_Z_257(Integer N, Double omega, ArrayList<ArrayList<Double>> polA)
     * pero pasandole por parametro la inversa de omega.
     * @param N es el exponemte de la menor potencia de 2 tal que este numero sea mayor que la suma del grado de los dos polinomios que se
     * multiplicaran en multiplicacion_FFT_en_Z_257, dicho metodo hara llamadas a este.
     * @param omega una raiz 2^N -esima primitiva de la unidad que se le pasara desde multiplicacion_FFT_en_Z_257 el las llamadas.
     * @param polC debe ser un polinomio de grado menor que N.
     * @return polinomio de numeros en Z_257 con la solucion.
     */
    public static ArrayList<Integer> IFFT_en_Z_257(Integer N, Integer omega, ArrayList<Integer> polC){
        ArrayList<Integer> polSol=new ArrayList<Integer>();
        ArrayList<Integer> polAux=new ArrayList<Integer>();
        
        //Calcular el inverso de omega
        Integer invOmega=inverso_en_Z_257(omega);
        System.out.print("  --> invOmega="+invOmega);
        //Llamar a 'FFT_en_Z_257' pero con el inverso de omega
        polAux=FFT_en_Z_257(N, invOmega, polC);
        //Multiplicar cada uno de los elementos del polinomio devuelto por 'FFT_en_Z_257' por el inverso de 2^N y el nuevo polinomio sera la solucion
        Integer invPotDe2=inverso_en_Z_257(((int) Math.pow( 2, N)));
        for(int i=0;i<polAux.size();i++){
            polSol.add(i, modulo_en_Z_257(invPotDe2*polAux.get(i)));
        }
        return polSol;
    }
    
    
    /**
     * Multiplicacion rapida de polinomios mediante FFT.
     * El grado de cada polinomio (n y m) es el tamaño del polinomio menos uno, ya que el indice de cada ArrayList 
     * nos indica el exponente al que esta elevado el coeficiente que ocupa esa posicion.
     * Por tanto hay que tener en cuenta rellenar los coeficientes nulos del polinomio con ceros en el ArrayList.
     * @param polA polinomio de numeros en Z_257 que se va a multiplicar.
     * @param polB polinomio de numeros en Z_257 que se va a multiplicar.
     * @param m grado del polinomio polA.
     * @param n grado del polinomio polB.
     * @return polinomio de numeros en Z_257 solucion.
     */
    public static ArrayList<Integer> multiplicacion_FFT_en_Z_257(ArrayList<Integer> polA, ArrayList<Integer> polB, Integer m, Integer n){
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
<<<<<<< HEAD
        omega=raiz_n_esima_primitiva_en_Z_257(N);
        
=======
        omega=raiz_n_esima_primitiva_en_Z_257(N);    
        System.out.print("--> N="+N+"  --> omega="+omega);
>>>>>>> 7fe3f0d1ea26c892395c245b0c67bb420429468f
        //Antes de llamar a 'FFT_en__Z_257' hay que añadir ceros al final de polA y polB hasta que tengan un tamaño igual a 2^N
        for(int i=polA.size();i<((int) Math.pow( 2, N));i++)
            polA.add(i,0);
        for(int i=polB.size();i<((int) Math.pow( 2, N));i++)
            polB.add(i,0);
        

        A=FFT_en_Z_257(N, omega, polA);
        B=FFT_en_Z_257(N, omega, polB);
            
        for(int i=0;i<((int) Math.pow( 2, N));i++){
            C.add(i, modulo_en_Z_257(A.get(i)*B.get(i)));
        }       
        //Llamada a la transformada de Fourier Inversa.
        polSol=IFFT_en_Z_257(N, omega, C);

        return polSol;
    }
    
    
     /**
     * Elimina los ceros a la derecha de un polinomio
     * @param pol el polinomio que se va a modificar.
     */
    public static void quitar_ceros_en_Z_257(ArrayList<Integer> pol){
        for(int i=pol.size()-1;pol.get(i)==0;i--)
            pol.remove(i);
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
        Random rnd = new Random();
<<<<<<< HEAD
        rnd.setSeed(9999999);
        /*
=======
    	rnd.setSeed(20011974);
        /**
>>>>>>> 7fe3f0d1ea26c892395c245b0c67bb420429468f
        System.out.print("\nINTRODUCE EL PRIMER POLINOMIO DE NUMEROS EN Z_257:\n");
        polA=leer_polinomio_en_Z_257();
        
        System.out.print("\nINTRODUCE EL SEGUNDO POLINOMIO DE NUMEROS EN Z_257:\n");
        polB=leer_polinomio_en_Z_257(); 
        
        if((polA.size()-1)+(polB.size()-1)>=8){
                System.out.print("\nNo existe en Z_257 una raiz de orden mayor a 8, la suma de los grados de los dos polinomios debe ser menor que 8.\n");
                return;
        }
<<<<<<< HEAD
        */
        for(int i=1;i<=5;i++){ 
=======
        **/
<<<<<<< HEAD
        for(int grad=1;grad<=8;grad++){ 
=======
        for(int grad=1;grad<=128;grad++){ 
>>>>>>> 7fe3f0d1ea26c892395c245b0c67bb420429468f
>>>>>>> e43506a6d4953366b5f495ca85ada0bfd3207baf
            ArrayList<Integer> polA=new ArrayList<Integer>();
            ArrayList<Integer> polB=new ArrayList<Integer>();
            ArrayList<Integer> polSolEsc=new ArrayList<Integer>();
            ArrayList<Integer> polSolFFT=new ArrayList<Integer>();
            
            for(int j=0;j<grad+1;j++)
                polA.add(j, modulo_en_Z_257(rnd.nextInt()));
             
            for(int j=0;j<grad;j++)
                polB.add(j, modulo_en_Z_257(rnd.nextInt()));
            
            System.out.print("\n--> Grado de polA="+(polA.size()-1)+"  --> Grado de polB="+(polB.size()-1));

            polSolEsc=multiplicacion_escuela_en_Z_257(polA, polB);
            if(polSolEsc.get(polSolEsc.size()-1) == 0)
                  quitar_ceros_en_Z_257(polSolEsc);
<<<<<<< HEAD
            System.out.print("\nResultado en Z_257 de multiplicacion ESCUELA:\n");
=======
            System.out.print("\n--> Resultado en Z_257 de multiplicacion ESCUELA:\n");
>>>>>>> 7fe3f0d1ea26c892395c245b0c67bb420429468f
            mostrar_polinomio_en_Z_257(polSolEsc);

            /*El grado de cada polinomio (n y m) es el tamaño del polinomio menos uno, ya que el indice de cada ArrayList 
             * nos indica el exponente al que esta elevado el coeficiente que ocupa esa posicion.
             * Por tanto hay que tener en cuenta rellenar los coeficientes nulos del polinomio con ceros en el ArrayList.
            */ 
            polSolFFT=multiplicacion_FFT_en_Z_257(polA, polB, polA.size()-1, polB.size()-1);
            if(polSolFFT.get(polSolFFT.size()-1) == 0)
                  quitar_ceros_en_Z_257(polSolFFT);
<<<<<<< HEAD
            System.out.print("\nResultado en Z_257 de multiplicacion rapida mediante FFT:\n");
=======
            System.out.print("\n--> Resultado en Z_257 de multiplicacion rapida mediante FFT:\n");
>>>>>>> 7fe3f0d1ea26c892395c245b0c67bb420429468f
            mostrar_polinomio_en_Z_257(polSolFFT); 
            System.out.print("\n");
        }
    }
}

