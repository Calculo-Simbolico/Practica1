package cs_practica_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class op_En_Complejos {

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
     * @param complejoA numero complejo que se va a multiplicar.
     * @param escalar escalar que se va a multiplicar.
     * @return numero complejo solucion.
     */
    public static ArrayList<Double> prodPorEsc_en_C(Double escalar, ArrayList<Double> complejoA){
        ArrayList<Double> complejoC=new ArrayList<Double>();
             
        complejoC.add(0,complejoA.get(0)*escalar);
        complejoC.add(1,complejoA.get(1)*escalar);
        
        return complejoC;
    }
    
    
    /**
     * Calcula la potencia de un numero complejo 
     * @param complejoA numero complejo que sera la base de la potencia.
     * @param exp exponente de la potencia.
     * @return numero complejo solucion.
     */
   public static ArrayList<Double> potencia_en_C(ArrayList<Double> complejoA, Integer exp){
        ArrayList<Double> complejoSol=new ArrayList<Double>();
        ArrayList<Double> complejoAux=new ArrayList<Double>();
        ArrayList<Double> complejoUno=new ArrayList<Double>(); 
        complejoUno.add(0, 1.0);
        complejoUno.add(1, 0.0);
        
        if(exp==0)
            return complejoUno;
        else if(exp==1)
            return complejoA;
        else{
            complejoAux=complejoA;
            for(int i=1;i<exp;i++){
                complejoSol=mult_en_C(complejoA, complejoAux);
                complejoAux=complejoSol;
            }
        return complejoSol;
        }
    }
    
     
    /**
     * Calcula el inverso de un numero num haciendo 1/num 
     * @param num numero del cual se va a calcular el inverso
     * @return el inverso del numero
     */
    public static Double inverso(Integer num){ 
        Double inverso = 0.0;
        inverso=(double)1/num;
        
        return inverso;
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
        for(int i=0;i<(polA.size()+polB.size())-1;i++){
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
     * Transformada rapida de Fourier
     * @param potDe2 es la menor potencia de 2 tal que este numero sea mayor que la suma del grado de los dos polinomios que se
     * multiplicaran en multiplicacion_FFT_en_C, dicho metodo hara llamadas a este.
     * @param omega una raiz 2^N -esima primitiva de la unidad que se le pasara desde multiplicacion_FFT_en_C el las llamadas.
     * @param polA debe ser un polinomio de numeros complejos de grado menor que N
     * @return polinomio de numeros complejos con la solucion
     */
    public static ArrayList<ArrayList<Double>> FFT_en_C(Integer potDe2, ArrayList<Double> omega, ArrayList<ArrayList<Double>> polA){
        ArrayList<ArrayList<Double>> A=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> B=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> C=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> polB=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> polC=new ArrayList<ArrayList<Double>>();
        ArrayList<Double>complejo=new ArrayList<>();
        ArrayList<Double>cmpljCero=new ArrayList<>();
        cmpljCero.add(0, 0.0);
        cmpljCero.add(1, 0.0);
                                                                                                               
        //Inicializar A con ceros con un tamaño igual a 2^N 
        for(int i=0;i<(potDe2);i++){
            A.add(cmpljCero);
        }
        if(potDe2==1){ 
            complejo.add(0, polA.get(0).get(0));
            complejo.add(1, polA.get(0).get(1));
            A.set(0, complejo);
        }
        else{
            for(int i=0;i<polA.size();i++){
                //Componentes para polB, que son los componentes pares de polA
                if(i%2==0){
                    ArrayList<Double>complejo1=new ArrayList<>();
                    complejo1.add(0, polA.get(i).get(0));
                    complejo1.add(1, polA.get(i).get(1));
                    polB.add(complejo1);
                }
                //Componentes para polC, que son los componentes impares de polA
                else{
                    ArrayList<Double>complejo2=new ArrayList<>();
                    complejo2.add(0, polA.get(i).get(0));
                    complejo2.add(1, polA.get(i).get(1));
                    polC.add(complejo2);
                }
            }                                                                                                                                                                           
            B=FFT_en_C(potDe2/2, potencia_en_C(omega, 2), polB);                                                                                                         
            C=FFT_en_C(potDe2/2, potencia_en_C(omega, 2), polC);
                                                                                                                   
            for(int i=0;i<(potDe2/2);i++){
                complejo.add(0, sum_en_C(B.get(i), mult_en_C(potencia_en_C(omega, i), C.get(i))).get(0));
                complejo.add(1, sum_en_C(B.get(i), mult_en_C(potencia_en_C(omega, i), C.get(i))).get(1));
                A.set(i, complejo);
                complejo.add(0, rest_en_C(B.get(i), mult_en_C(potencia_en_C(omega, i), C.get(i))).get(0));
                complejo.add(1, rest_en_C(B.get(i), mult_en_C(potencia_en_C(omega, i), C.get(i))).get(1));
                A.set((potDe2/2)+i, complejo);
            }
        }                                                                                                           
        return A;
    }
    
    
    /**
     * Transformada inversa de Fourier, hace una llamada a FFT_en_C(Integer potDe2, Double omega, ArrayList<ArrayList<Double>> polA)
     * pero pasandole por parametro la inversa de omega calculada segun la formula de coseno y seno.
     * @param potDe2 es la menor potencia de 2 tal que este numero sea mayor que la suma del grado de los dos polinomios que se
     * multiplicaran en multiplicacion_FFT_en_C, dicho metodo hara llamadas a este.
     * @param omega una raiz 2^N -esima primitiva de la unidad que se le pasara desde multiplicacion_FFT_en_C el las llamadas.
     * @param polC debe ser un polinomio de numeros complejos de grado menor que N
     * @return polinomio de numeros complejos con la solucion
     */
    public static ArrayList<ArrayList<Double>> IFFT_en_C(Integer potDe2, ArrayList<Double> omega, ArrayList<ArrayList<Double>> polC){
        ArrayList<ArrayList<Double>> polSol=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> polAux=new ArrayList<ArrayList<Double>>();
        ArrayList<Double>invOmega=new ArrayList<>();
        ArrayList<Double>complejo=new ArrayList<>();
        
        //Calcular el inverso de omega, se hace cambiando el signo de la parte imaginaria
        invOmega.add(0, Math.cos((2*Math.PI)/potDe2));
        invOmega.add(1, -1*(Math.sin((2*Math.PI)/potDe2)));                                                                                                     
        //Llamar a 'FFT_en_C' pero con el inverso de omega
        polAux=FFT_en_C(potDe2, invOmega, polC);      
        //Multiplicar cada uno de los elementos del polinomio devuelto por 'FFT_en_C' por el inverso de 2^N y el nuemo polinomio sera la solucion
        Double invPotDe2=inverso(potDe2);       
        for(int i=0;i<polAux.size();i++){
            complejo.add(0, polAux.get(i).get(0));
            complejo.add(1, polAux.get(i).get(1));
            polSol.add(i, prodPorEsc_en_C(invPotDe2, complejo));
        }
        return polSol;
    }
    
    
    /**
     * Multiplicacion rapida de polinomios mediante FFT.
     * El grado de cada polinomio (n y m) es el tamaño del polinomio menos uno, ya que el indice de cada ArrayList 
     * nos indica el exponente al que esta elevado el coeficiente que ocupa esa posicion.
     * Por tanto hay que tener en cuenta rellenar los coeficientes nulos del polinomio con ceros en el ArrayList.
     * @param polA polinomio de numeros complejos que se va a multiplicar.
     * @param polB polinomio de numeros complejos que se va a multiplicar.
     * @param m grado del polinomio polA.
     * @param n grado del polinomio polB.
     * @return polinomio de numeros complejos solucion.
     */
    public static ArrayList<ArrayList<Double>> multiplicacion_FFT_en_C(ArrayList<ArrayList<Double>> polA, ArrayList<ArrayList<Double>> polB, Integer m, Integer n){
        ArrayList<ArrayList<Double>> polSol=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> A=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> B=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> C=new ArrayList<ArrayList<Double>>();
        ArrayList<Double>omega=new ArrayList<>();
        ArrayList<Double>cmpljCero=new ArrayList<>();
        cmpljCero.add(0, 0.0);
        cmpljCero.add(1, 0.0);
        
        //Calcular primer entero tal que m+n < 2^N
        Integer N=0;
        while((m+n -(int) Math.pow( 2, N))>0){
            N++;
        }
        if(((int) Math.pow( 2, N))==(n+m))
            N++;     System.out.print("\nN="+N);
                                                                                                                     
        //Calcular omega=raiz 2^N-esima primitiva de la unidad (PI=180)
        omega.add(0, Math.cos((2*Math.PI)/Math.pow(2, N)));
        omega.add(1, Math.sin((2*Math.PI)/Math.pow(2, N)));     System.out.print("(\nomega=("+omega.get(0)+", "+omega.get(1)+")");
                                                                                                                    
        //Antes de llamar a 'FFT_en_C' hay que añadir ceros al final de polA y polB hasta que tengan un tamaño igual a 2^N
        for(int i=polA.size();i<(int) Math.pow( 2, N);i++)
            polA.add(i,cmpljCero);
        for(int i=polB.size();i<(int) Math.pow( 2, N);i++)
            polB.add(i,cmpljCero);
                                                                                                                    
        A=FFT_en_C((int) Math.pow( 2, N), omega, polA);                                                                                                                                                                                                                                 
        B=FFT_en_C((int) Math.pow( 2, N), omega, polB);
                                                                                                                    
        for(int i=0;i<((int) Math.pow( 2, N));i++){
            C.add(i, mult_en_C(A.get(i), B.get(i)));
        }
        //Llamada a la transformada de Fourier Inversa.
        polSol=IFFT_en_C((int)Math.pow( 2, N), omega, C);

        return polSol;
    }
    
     /**
     * Elimina los ceros a la derecha de un polinomio.
     * @param pol el polinomio que se va a modificar.
     */
    public static void quitar_ceros_en_C(ArrayList<ArrayList<Double>> pol){
        for(int i=pol.size()-1;pol.get(i).get(0)==0 && pol.get(i).get(1)==0;i--)
            pol.remove(i);
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
   
   
   /**
    * Metodo usado para las entradas desde ficheros de texto 
    * @return un objeto BufferedReader el cual sera leido desde el metodo que llame a este o null si el fichero esta vacio
    * @throws IOException 
    */
    public static ArrayList<ArrayList<ArrayList<Double>>> leer_polinomio_en_C_fichero() throws IOException { 
        ArrayList<ArrayList<ArrayList<Double>>> listPolLeidos=new ArrayList<ArrayList<ArrayList<Double>>>();
        ArrayList<ArrayList<Double>> polLeido1=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> polLeido2=new ArrayList<ArrayList<Double>>();
        FileReader fr = new FileReader("C:\\Users\\Asus\\Documents\\GitHub\\Practica1\\CS_Practica_2\\src\\cs_practica_2\\entrada.txt");
        BufferedReader entrada = new BufferedReader(fr);
        String polinomio = null;
        String input=new String();
 
        input=entrada.readLine();
        polinomio=input;
        int i=0;

        while(polinomio.charAt(i)!='*' && i<polinomio.length()){ 
                if(polinomio.charAt(i)=='('){
                    ArrayList<Double>complejo=new ArrayList<>();
                    i++;
                    String real="";
                    while(polinomio.charAt(i)!=',' && i<polinomio.length()){
                       real+=polinomio.charAt(i);
                       i++;
                    }
                    Double valor=Double.parseDouble(real);
                    complejo.add(0,valor);

                    i++;
                    String imag="";
                    while(polinomio.charAt(i)!=')' && i<polinomio.length()){
                       imag+=polinomio.charAt(i);
                       i++;
                    }
                    valor=Double.parseDouble(imag);
                    complejo.add(1,valor);

                    polLeido1.add(complejo);
                }
                else
                    i++;
            }
            listPolLeidos.add(polLeido1);
            
            while(i<polinomio.length()){ 
            if(polinomio.charAt(i)=='('){
                    ArrayList<Double>complejo=new ArrayList<>();
                    i++;
                    String real="";
                    while(polinomio.charAt(i)!=',' && i<polinomio.length()){
                       real+=polinomio.charAt(i);
                       i++;
                    }
                    Double valor=Double.parseDouble(real);
                    complejo.add(0,valor);

                    i++;
                    String imag="";
                    while(polinomio.charAt(i)!=')' && i<polinomio.length()){
                       imag+=polinomio.charAt(i);
                       i++;
                    }
                    valor=Double.parseDouble(imag);
                    complejo.add(1,valor);

                    polLeido2.add(complejo);
                }
                else
                    i++;
            }
            listPolLeidos.add(polLeido2);
        
        fr.close();
        return listPolLeidos;
    }   
    

    public static void main(String[] args) throws IOException {
        
        ArrayList<ArrayList<Double>> polA=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> polB=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> polSolEsc=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> polSolFFT=new ArrayList<ArrayList<Double>>();  
        
        //System.out.print("\ncos(PI/2) = "+Math.cos((Math.PI)/2.0));
        //System.out.print("\ncos(PI/2) = "+Math.cos((Math.PI)));
        
        System.out.print("\nINTRODUCE EL PRIMER POLINOMIO DE COMPLEJOS:\n");
        polA=leer_polinomio_en_C();
  
        System.out.print("\nINTRODUCE EL SEGUNDO POLINOMIO DE COMPLEJOS:\n");
        polB=leer_polinomio_en_C(); 
        
        System.out.print("\nGrado de polA="+(polA.size()-1));
        System.out.print("\nGrado de polB="+(polB.size()-1));
       
        polSolEsc=multiplicacion_escuela_en_C(polA, polB);
        System.out.print("\nResultado en C de multiplicacion ESCUELA:\n");
        mostrar_polinomio_en_C(polSolEsc);
        
        //El grado de cada polinomio (n y m) es el tamaño del polinomio menos uno, ya que el indice de cada ArrayList 
        //nos indica el exponente al que esta elevado el coeficiente que ocupa esa posicion.
        //Por tanto hay que tener en cuenta rellenar los coeficientes nulos del polinomio con ceros en el ArrayList.
        polSolFFT=multiplicacion_FFT_en_C(polA, polB, polA.size()-1, polB.size()-1);
        System.out.print("\nResultado en C de multiplicacion rapida mediante FFT:\n");
        mostrar_polinomio_en_C(polSolFFT);
    }
}


