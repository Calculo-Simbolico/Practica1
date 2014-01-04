
package cs_practica_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

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
        Integer mod=p;
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
            return (p+invs); //Reducir modulo p por si el resultado es negativo
        else 
            return invs;
    }  
   
   /**
     * Calcula la raiz potDe2-esima primitiva de la unidad en Zp (Cuando se tiene un primo pequeño, 
     * se pueden localizar las raíces primitivas por “ensayo y error”,construyendo una tabla de potencias).
     * @param potDe2 numero equivalente a n en la raiz n-esima.
     * @param p modulo para reducir.
     * @return la raiz potDe2-esima primitiva de la unidad si existe la raiz o devuelve -1 si no existe la raiz.
    */
   public static Integer raiz_n_esima_primitiva_en_Zp(Integer potDe2, Integer p){
<<<<<<< HEAD
    /*    boolean noRepetido=true;
=======
        ArrayList<Integer> ArrayPot=new ArrayList<Integer>(); 
        HashSet<Integer> potencias=new HashSet<Integer>();
        boolean noRepetido=true;
>>>>>>> 803eacbbe4f05b07dabb682922456e98f607191d
        boolean noRaiz=true;
        Integer raiz=0;
        int j=1;
        int i=1;
        
        for(i=1;i<p && noRaiz;i++){
            potencias=new HashSet<Integer>();
            ArrayPot=new ArrayList<Integer>();
            noRepetido=true;
            for(j=1;j<=potDe2 && noRepetido;j++){
                //Se usa un HashSet para que no haya ninguna potencia repetida
                noRepetido=potencias.add(modulo_en_Zp(p, (int)Math.pow(i, j)));
                //Para visuaizar resultados
                ArrayPot.add(modulo_en_Zp(p, (int)Math.pow(i, j)));
            }
            //Si ninguna potencia se ha repetido y ademas la potencia n-esima de i es igual a 1 se sale del bucle
            if(noRepetido && modulo_en_Zp(p, ((int)Math.pow(i, (j-1))))==1){
                noRaiz=false;
            }
            //Para visuaizar resultados
            //System.out.print(i+" --> "+ArrayPot+"\n");      
        }
        if(noRaiz){
            System.out.print("\n¡¡¡No existe en Z_"+p+" una raiz de orden "+potDe2+"!!!.\n");
                return -1;
        }
        else{
            //Para visuaizar resultados
            System.out.print((i-1)+" --> "+ArrayPot+"\n"); 
            raiz = i-1;
            return raiz;
        }
<<<<<<< HEAD
        raiz = i-1;*/
        
        Integer raiz=0;

        boolean salte=false;
        for(int i=2;i<p && salte==false;i++){
            if(Math.pow(i, potDe2)%p == 1){
                raiz=i;
                salte=true;
            }
        }
        
        return raiz;
=======
>>>>>>> 803eacbbe4f05b07dabb682922456e98f607191d
    }
   
   
   /**
    * Localizar las raices primitivas por ensayo  error (mostrar tabla), pagina 94 TeoriaDeNumeros.pdf
    * @param p modulo.
    */
    public static void mostrar_raices_primitivas_en_Zp(Integer p){
        
        for(int base=1;base<p;base++){
            System.out.print("\n"+base+" |");
            for(int exp=1;exp<p;exp++){
                System.out.print(" "+modulo_en_Zp(p, (int)Math.pow(base, exp)));
            }
       }
    }
    
    
    /**
     * Calcula el modulo de un numero en Zp con p un numero primo impar.
     * @param p modulo.
     * @param num numero del cual se va a calcular el modulo.
     * @return el modulo del numero.
     */
    public static Integer modulo_en_Zp(Integer p, Integer num){
        Integer modulo = 0;
        
        modulo=num%p;
        
        if(modulo<0)
            return p+modulo;
        else 
            return modulo;
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
               polC.set(i+k, modulo_en_Zp(p, (polA.get(i)*polB.get(k))+polC.get(i+k)));

        return polC;
    }
    
    
    /**
     * Transformada rapida de Fourier
     * @param N es el exponente de la menor potencia de 2 tal que este numero sea mayor que la suma del grado de los dos polinomios que se
     * multiplicaran en multiplicacion_FFT_en_Zp, dicho metodo hara llamadas a este.
     * @param omega una raiz 2^N -esima primitiva de la unidad que se le pasara desde multiplicacion_FFT_en_Zp el las llamadas.
     * @param polA debe ser un polinomio de numeros de grado menor que N
     * @param p numero primo impar.
     * @return polinomio de numeros con la solucion
     */
    public static ArrayList<Integer> FFT_en_Zp(Integer N, Integer omega, ArrayList<Integer> polA, Integer p){
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
            B=FFT_en_Zp(N-1, modulo_en_Zp(p, (int)Math.pow(omega, 2)), polB, p);
            C=FFT_en_Zp(N-1, modulo_en_Zp(p, (int)Math.pow(omega, 2)), polC, p);

            for(int i=0;i<((int)Math.pow(2, N-1));i++){
                A.set(i, (int)(B.get(i)+( (Math.pow(omega, i)* C.get(i))%p))%p);
                A.set(((int)Math.pow(2, N-1))+i, (int)(B.get(i)-( (Math.pow(omega, i)* C.get(i))%p))%p);
                /*
                A.set(i, modulo_en_Zp(p, B.get(i)+((int)Math.pow(omega, i)* C.get(i))));
                A.set(((int)Math.pow(2, N-1))+i, modulo_en_Zp(p, B.get(i)-((int)Math.pow(omega, i)* C.get(i))));*/
            }
        }
        return A;
    }
    
    
    /**
     * Transformada inversa de Fourier, hace una llamada a FFT_en_Zp(Integer potDe2, Double omega, ArrayList<ArrayList<Double>> polA, Integer p)
     * pero pasandole por parametro la inversa de omega.
     * @param N es el exponente de la menor potencia de 2 tal que este numero sea mayor que la suma del grado de los dos polinomios que se
     * multiplicaran en multiplicacion_FFT_en_Zp, dicho metodo hara llamadas a este.
     * @param omega una raiz 2^N -esima primitiva de la unidad que se le pasara desde multiplicacion_FFT_en_Zp el las llamadas.
     * @param polC debe ser un polinomio de grado menor que N.
     * @param p numero primo impar.
     * @return polinomio de numeros en Zp con la solucion.
     */
    public static ArrayList<Integer> IFFT_en_Zp(Integer N, Integer omega, ArrayList<Integer> polC, Integer p){
        ArrayList<Integer> polSol=new ArrayList<Integer>();
        ArrayList<Integer> polAux=new ArrayList<Integer>();
        
        //Calcular el inverso de omega
        Integer invOmega=inverso_en_Zp(p, omega);
        System.out.print("  --> invOmega="+invOmega);
        //Llamar a 'FFT_en_Zp' pero con el inverso de omega
        polAux=FFT_en_Zp(N, invOmega, polC, p);
        //Multiplicar cada uno de los elementos del polinomio devuelto por 'FFT_en_Zp' por el inverso de 2^N y el nuemo polinomio sera la solucion
        Integer invPotDe2=inverso_en_Zp(p, ((int) Math.pow( 2, N)));
        for(int i=0;i<polAux.size();i++){
            polSol.add(i, modulo_en_Zp(p, invPotDe2*polAux.get(i)));
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
        
        //Calcular primer entero tal que m+n < 2^N (¡¡¡Mayor estricto!!!)
        Integer N=0;
        while(((m+n) -(int) Math.pow( 2, N))>0){
            N++;
        }
        if(((int) Math.pow( 2, N))==(n+m))
            N++;    

        //Calcular omega=raiz 2^N-esima primitiva de la unidad
        omega=raiz_n_esima_primitiva_en_Zp(((int) Math.pow( 2, N)), p);
        //Salir si no existe la raiz o sea omega=-1
        if(omega==-1)
            return polSol;
        System.out.print("\n--> N="+N+"  --> omega="+omega);
        //Antes de llamar a 'FFT_en_Zp' hay que añadir ceros al final de polA y polB hasta que tengan un tamaño igual a 2^N
        for(int i=polA.size();i<((int) Math.pow( 2, N));i++)
            polA.add(i,0);
        for(int i=polB.size();i<((int) Math.pow( 2, N));i++)
            polB.add(i,0);

        A=FFT_en_Zp(N, omega, polA, p);
        B=FFT_en_Zp(N, omega, polB, p);

        for(int i=0;i<((int) Math.pow( 2, N));i++){
            C.add(i, modulo_en_Zp(p, A.get(i)*B.get(i)));
        }       
        //Llamada a la transformada de Fourier Inversa.
        polSol=IFFT_en_Zp(N, omega, C, p);
        
        return polSol;
    }
    
    
    /**
     * Elimina los ceros a la derecha de un polinomio
     * @param pol el polinomio que se va a modificar.
     */
    public static void quitar_ceros_en_Zp(ArrayList<Integer> pol){
        for(int i=pol.size()-1;pol.get(i)==0;i--)
            pol.remove(i);
   }
    
    
    /**
     * Muestra por pantalla el polinomio  empezando por el coeficiente menos significativo 
     * @param pol el polinomio que se va a mostrar por pantalla 
     */
    public static void mostrar_polinomio_en_Zp(ArrayList<Integer> pol){
        System.out.print("[");
        for(int i=0;i<pol.size();i++)
            System.out.print(pol.get(i)+" ");
       
        System.out.print("]\n\n");
   }
    
    
   /**
    * Lee de la entrada estándar un polinomio con el formato num1 num2 num3 ... empezando por el coeficiente menos significativo.
    * @param p numero primo impar.
    * @return polinomio de numeros complejo
    */
   public static ArrayList<Integer> leer_polinomio_en_Zp(Integer p) throws IOException{
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
                Integer num=(modulo_en_Zp(p, Integer.parseInt(dig)));
                pol.add(num);
            }
            else
                i++;
        }
        return pol;
   }
       
   
    public static void main(String[] args) throws IOException {
        Random rnd = new Random();
    	rnd.setSeed(20011974);
        
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
        
        System.out.print("\nGrado de polA="+(polA.size()-1));
        System.out.print("\nGrado de polB="+(polB.size()-1));
       
        polSolEsc=multiplicacion_escuela_en_Zp(polA, polB, p);
        if(polSolEsc.get(polSolEsc.size()-1) == 0)
                  quitar_ceros_en_Zp(polSolEsc);
        System.out.print("\nResultado en Zp de multiplicacion ESCUELA:\n");
        mostrar_polinomio_en_Zp(polSolEsc);
        
        /*El grado de cada polinomio (n y m) es el tamaño del polinomio menos uno, ya que el indice de cada ArrayList 
         * nos indica el exponente al que esta elevado el coeficiente que ocupa esa posicion.
         * Por tanto hay que tener en cuenta rellenar los coeficientes nulos del polinomio con ceros en el ArrayList.
        */ 
        polSolFFT=multiplicacion_FFT_en_Zp(polA, polB, polA.size()-1, polB.size()-1, p);
        if(!polSolFFT.isEmpty() && polSolFFT.get(polSolFFT.size()-1) == 0)
                  quitar_ceros_en_Zp(polSolFFT);
        System.out.print("\nResultado en Zp de multiplicacion rapida mediante FFT:\n");
        mostrar_polinomio_en_Zp(polSolFFT);
        
        lectura.close();
    }
}

    


