
package cs_practica_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class op_En_Z_p {

//***************OPERACIONES PARA NUMEROS EN Z_p********************
    
    /**
     * Calcula el inverso de un numero en Zp con p un numero primo impar mediante el algoritmo extendido de Euclides.
     * @param num numero del cual se va a calcular su inverso.
     * @param p modulo para calcular el inverso.
     * @return el inverso del numero.
     */
    public static Integer inverso_en_Zp(Integer p, Integer num){
        Integer invs=0, x=0, y=0, x2=1, x1=0, y2=0, y1=1, q=0, r=0;
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
     * Calcula la raiz n-esima primitiva de la unidad en Zp con p un numero primo impar.
     * @return la raiz n-esima primitiva de la unidad.
     */
    public static Integer raiz_n_esima_primitiva(){
        Integer raiz = 0;
        
        return raiz;  
    }
  
    
    /**
     * Multiplicacion por el algoritmo escuela de dos polinomios de numeros en Zp con p un numero primo impar.                                              
     * @param polA polinomio que se va a multiplicar.
     * @param polB polinomio que se va a multiplicar.
     * @param p numero primo impar.
     * @return polinomio de numeros en Zp con el resultado.
     */
    public static ArrayList<Integer> multiplicacion_escuela_en_Zp(ArrayList<Integer> polA, ArrayList<Integer> polB, Integer p){
        ArrayList<Integer> polC=new ArrayList<Integer>();
        
        //Añadir ceros al polinomio solucion
        for(int i=0;i<(polA.size()+polB.size())-1;i++)
             polC.add(0);
        
        for(int k=0;k<polB.size();k++)
            for(int i=0;i<polA.size();i++)
               polC.set(i+k, ((polA.get(i)*polB.get(k))+polC.get(i+k))% p);

        return polC;
    }
    
    
    /**
     * Transformada rapida de Fourier
     * @param potDe2 es la menor potencia de 2 tal que este numero sea mayor que la suma del grado de los dos polinomios que se
     * multiplicaran en multiplicacion_FFT_en_C, dicho metodo hara llamadas a este.
     * @param omega una raiz 2^N -esima primitiva de la unidad que se le pasara desde multiplicacion_FFT_en_C el las llamadas.
     * @param polA debe ser un polinomio de numeros de grado menor que N
     * @param p numero primo impar.
     * @return polinomio de numeros con la solucion
     */
    public static ArrayList<Integer> FFT_en_Zp(Integer potDe2, Integer omega, ArrayList<Integer> polA, Integer p){
        ArrayList<Integer> A=new ArrayList<Integer>();
        ArrayList<Integer> B=new ArrayList<Integer>();
        ArrayList<Integer> C=new ArrayList<Integer>();
        ArrayList<Integer> polB=new ArrayList<Integer>();
        ArrayList<Integer> polC=new ArrayList<Integer>();
        
        if(potDe2==1){ 
            A.add(0, polA.get(0));
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

            B=FFT_en_Zp(potDe2/2, ((int)Math.pow(omega, 2))%p, polB, p);
            C=FFT_en_Zp(potDe2/2, ((int)Math.pow(omega, 2))%p, polC, p);

            for(int i=0;i<(potDe2/2);i++){
                A.add(i, (B.get(i)+((int)Math.pow(omega, i)* C.get(i)))% p);
                A.add((potDe2)+i, (B.get(i)-((int)Math.pow(omega, i)* C.get(i)))% p);
            }
        }
        return A;
    }
    
    
    /**
     * Transformada inversa de Fourier, hace una llamada a FFT_en_Zp(Integer potDe2, Double omega, ArrayList<ArrayList<Double>> polA, Integer p)
     * pero pasandole por parametro la inversa de omega.
     * @param potDe2 es la menor potencia de 2 tal que este numero sea mayor que la suma del grado de los dos polinomios que se
     * multiplicaran en multiplicacion_FFT_en_Zp, dicho metodo hara llamadas a este.
     * @param omega una raiz 2^N -esima primitiva de la unidad que se le pasara desde multiplicacion_FFT_en_Zp el las llamadas.
     * @param polC debe ser un polinomio de grado menor que N.
     * @param p numero primo impar.
     * @return polinomio de numeros en Zp con la solucion.
     */
    public static ArrayList<Integer> IFFT_en_Zp(Integer potDe2, Integer omega, ArrayList<Integer> polC, Integer p){
        ArrayList<Integer> polSol=new ArrayList<Integer>();
        ArrayList<Integer> polAux=new ArrayList<Integer>();
        
        //Calcular el inverso de omega
        Integer invOmega=inverso_en_Zp(p, omega);

        //Llamar a 'FFT_en_C' pero con el inverso de omega

        polAux=FFT_en_Zp(potDe2, invOmega, polC, p);
        
        //Multiplicar cada uno de los elementos del polinomio devuelto por 'FFT_en_Zp' por el inverso de 2^N y el nuemo polinomio sera la solucion
        Integer invPotDe2=inverso_en_Zp(p, potDe2);

        for(int i=0;i<polAux.size();i++){
            polSol.set(i, (invPotDe2*polAux.get(i))% p);
        }
        return polSol;
    }
    
    
    /**
     * Multiplicacion rapida de polinomios mediante FFT.
     * El grado de cada polinomio (n y m) es el tamaño del polinomio menos uno, ya que el indice de cada ArrayList 
     * nos indica el exponente al que esta elevado el coeficiente que ocupa esa posicion.
     * Por tanto hay que tener en cuenta rellenar los coeficientes nulos del polinomio con ceros en el ArrayList.
     * @param polA polinomio de numeros en Zp que se va a multiplicar.
     * @param polB polinomio de numeros en Zp que se va a multiplicar.
     * @param m grado del polinomio polA.
     * @param n grado del polinomio polB.
     * @param p numero primo impar.
     * @return polinomio de numeros en Zp solucion.
     */
    public static ArrayList<Integer> multiplicacion_FFT_en_Zp(ArrayList<Integer> polA, ArrayList<Integer> polB, Integer m, Integer n, Integer p){
        ArrayList<Integer> polSol=new ArrayList<Integer>();
        ArrayList<Integer> A=new ArrayList<Integer>();
        ArrayList<Integer> B=new ArrayList<Integer>();
        ArrayList<Integer> C=new ArrayList<Integer>();
        Integer omega=0;
        
        //Calcular primer entero tal que m+n < 2^N
        Integer N=0;
        while((m+n -(int) Math.pow( 2, N))>0){
            N++;
        }
        //Calcular omega=raiz 2^N-esima primitiva de la unidad
        omega=raiz_n_esima_primitiva();//SIN HACER
        
        //Antes de llamar a 'FFT_en_C' hay que añadir ceros al final de polA y polB hasta que tengan un tamaño igual a 2^N
        for(int i=polA.size();i<(int) Math.pow( 2, N);i++)
            polA.add(i,0);
        for(int i=polB.size();i<(int) Math.pow( 2, N);i++)
            polB.add(i,0);
        
        A=FFT_en_Zp((int) Math.pow( 2, N), omega, polA, p);
        B=FFT_en_Zp((int) Math.pow( 2, N), omega, polB, p);

        for(int i=0;i<((int) Math.pow( 2, N));i++){
            C.add(i, (A.get(i)*B.get(i))% p);
        }       
        //Llamada a la transformada de Fourier Inversa.
        polSol=IFFT_en_Zp((int)Math.pow( 2, N), omega, C, p);

        return polSol;
    }
    
    
    /**
     * Muestra por pantalla el polinomio  empezando por el coeficiente menos significativo 
     * @param pol el polinomio que se va a mostrar por pantalla 
     */
    public static void mostrar_polinomio_en_Zp(ArrayList<Integer> pol){
        System.out.print("[");
        for(int i=0;i<pol.size()-1;i++)
            System.out.print(pol.get(i)+", ");
        System.out.print(pol.get(pol.size()-1));
        System.out.print("]\n");
   }
    
    
   /**
    * Lee de la entrada estándar un polinomio con el formato num1 num2 num3 ... empezando por el coeficiente menos significativo.
    * @param p numero primo impar.
    * @return polinomio de numeros complejo
    */
   public static ArrayList<Integer> leer_polinomio_en_Zp(Integer p){
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
                Integer num=((Integer.parseInt(dig))% p);
                pol.add(num);
            }
            else
                i++;
        }
        return pol;
   }
       
   
    public static void main(String[] args) throws IOException {
       
        BufferedReader lectura = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> polA=new ArrayList<Integer>();
        ArrayList<Integer> polB=new ArrayList<Integer>();
        ArrayList<Integer> polSolEsc=new ArrayList<Integer>();
        ArrayList<Integer> polSolFFT=new ArrayList<Integer>();
        
        System.out.print("\nINTRODUCE UN NUMERO PRIMO IMPAR p:\n");
        Integer p = Integer.parseInt(lectura.readLine());
        System.out.print("\nINTRODUCE EL PRIMER POLINOMIO DE NUMEROS EN Zp:\n");
        polA=leer_polinomio_en_Zp(p);
        System.out.print("\nINTRODUCE EL SEGUNDO POLINOMIO DE NUMEROS EN Zp:\n");
        polB=leer_polinomio_en_Zp(p); 
       
        polSolEsc=multiplicacion_escuela_en_Zp(polA, polB, p);
        System.out.print("\nResultado en Zp de multiplicacion ESCUELA:\n");
        mostrar_polinomio_en_Zp(polSolEsc);
        
        /**El grado de cada polinomio (n y m) es el tamaño del polinomio menos uno, ya que el indice de cada ArrayList 
         * nos indica el exponente al que esta elevado el coeficiente que ocupa esa posicion.
         * Por tanto hay que tener en cuenta rellenar los coeficientes nulos del polinomio con ceros en el ArrayList.
         */ 
        polSolFFT=multiplicacion_FFT_en_Zp(polA, polB, polA.size()-1, polB.size()-1, p);
        System.out.print("\nResultado en Zp de multiplicacion rapida mediante FFT:\n");
        mostrar_polinomio_en_Zp(polSolFFT);
    }
}

    


