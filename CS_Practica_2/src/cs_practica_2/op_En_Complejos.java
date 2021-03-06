package cs_practica_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

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
        ArrayList<Double>cmpljCero=new ArrayList<>();
        cmpljCero.add(0, 0.0);
        cmpljCero.add(1, 0.0);
        
        //Añadir ceros al polinomio solucion
        for(int i=0;i<(polA.size()+polB.size())-1;i++){
            polC.add(cmpljCero);
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
     * @param N es el exponente de la menor potencia de 2 tal que este numero sea mayor que la suma del grado de los dos polinomios que se
     * multiplicaran en multiplicacion_FFT_en_C, dicho metodo hara llamadas a este.
     * @param omega una raiz 2^N -esima primitiva de la unidad que se le pasara desde multiplicacion_FFT_en_C el las llamadas.
     * @param polA debe ser un polinomio de numeros complejos de grado menor que N
     * @return polinomio de numeros complejos con la solucion
     */
    public static ArrayList<ArrayList<Double>> FFT_en_C(Integer N, ArrayList<Double> omega, ArrayList<ArrayList<Double>> polA){
        ArrayList<ArrayList<Double>> A=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> B=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> C=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> polB=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> polC=new ArrayList<ArrayList<Double>>();
        ArrayList<Double>cmpljCero=new ArrayList<>();
        cmpljCero.add(0, 0.0);
        cmpljCero.add(1, 0.0);
                                                                                                               
        //Inicializar A con ceros con un tamaño igual a 2^N 
        for(int i=0;i<((int)Math.pow(2, N));i++){
            A.add(cmpljCero);
        }
        if(((int)Math.pow(2, N))==1){ 
            ArrayList<Double>complejo1=new ArrayList<>();
            complejo1.add(0, polA.get(0).get(0));
            complejo1.add(1, polA.get(0).get(1));
            A.set(0, complejo1);
        }
        else{
            for(int i=0;i<polA.size();i++){
                //Componentes para polB, que son los componentes pares de polA
                if(i%2==0){
                    ArrayList<Double>complejo2=new ArrayList<>();
                    complejo2.add(0, polA.get(i).get(0));
                    complejo2.add(1, polA.get(i).get(1));
                    polB.add(complejo2);
                }
                //Componentes para polC, que son los componentes impares de polA
                else{
                    ArrayList<Double>complejo3=new ArrayList<>();
                    complejo3.add(0, polA.get(i).get(0));
                    complejo3.add(1, polA.get(i).get(1));
                    polC.add(complejo3);
                }
            }                                                                    
            
            B=FFT_en_C(N-1, potencia_en_C(omega, 2), polB);                                                                                                         
            C=FFT_en_C(N-1, potencia_en_C(omega, 2), polC);  
            for(int i=0;i<((int)Math.pow(2, N-1));i++){
                ArrayList<Double>complejo4=new ArrayList<>();
                ArrayList<Double>complejo5=new ArrayList<>();
                complejo4.add(0, sum_en_C(B.get(i), mult_en_C(potencia_en_C(omega, i), C.get(i))).get(0));
                complejo4.add(1, sum_en_C(B.get(i), mult_en_C(potencia_en_C(omega, i), C.get(i))).get(1));
                A.set(i, complejo4);
                
                complejo5.add(0, rest_en_C(B.get(i), mult_en_C(potencia_en_C(omega, i), C.get(i))).get(0));
                complejo5.add(1, rest_en_C(B.get(i), mult_en_C(potencia_en_C(omega, i), C.get(i))).get(1));
                A.set(((int)Math.pow(2, N-1))+i, complejo5);
            }
        }          
        return A;
    }
    
    
    /**
     * Transformada inversa de Fourier, hace una llamada a FFT_en_C(Integer potDe2, Double omega, ArrayList<ArrayList<Double>> polA)
     * pero pasandole por parametro la inversa de omega calculada segun la formula de coseno y seno.
     * @param N es el exponente de la menor potencia de 2 tal que este numero sea mayor que la suma del grado de los dos polinomios que se
     * multiplicaran en multiplicacion_FFT_en_C, dicho metodo hara llamadas a este.
     * @param omega una raiz 2^N -esima primitiva de la unidad que se le pasara desde multiplicacion_FFT_en_C el las llamadas.
     * @param polC debe ser un polinomio de numeros complejos de grado menor que N
     * @return polinomio de numeros complejos con la solucion
     */
    public static ArrayList<ArrayList<Double>> IFFT_en_C(Integer N, ArrayList<Double> omega, ArrayList<ArrayList<Double>> polC){
        ArrayList<ArrayList<Double>> polSol=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> polAux=new ArrayList<ArrayList<Double>>();
        ArrayList<Double>invOmega=new ArrayList<>();
 
        //Calcular el inverso de omega, se hace cambiando el signo de la parte imaginaria
        invOmega.add(0, Math.cos((2*Math.PI)/Math.pow( 2, N)));
        invOmega.add(1, -1*(Math.sin((2*Math.PI)/Math.pow( 2, N))));  
        //Llamar a 'FFT_en_C' pero con el inverso de omega
        polAux=FFT_en_C(N, invOmega, polC);      
        //Multiplicar cada uno de los elementos del polinomio devuelto por 'FFT_en_C' por el inverso de 2^N y el nuemo polinomio sera la solucion
        Double invPotDe2=inverso(((int) Math.pow( 2, N)));  
        for(int i=0;i<polAux.size();i++){
            ArrayList<Double>complejo=new ArrayList<>();
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
            N++;
        
        //Calcular omega=raiz 2^N-esima primitiva de la unidad (PI=180)
        omega.add(0, Math.cos((2*Math.PI)/Math.pow(2, N)));
        omega.add(1, Math.sin((2*Math.PI)/Math.pow(2, N)));                                                                                                  
        //Antes de llamar a 'FFT_en_C' hay que añadir ceros al final de polA y polB hasta que tengan un tamaño igual a 2^N
        for(int i=polA.size();i<(int) Math.pow( 2, N);i++)
            polA.add(i,cmpljCero);
        for(int i=polB.size();i<(int) Math.pow( 2, N);i++)
            polB.add(i,cmpljCero);
                                                                                                                    
        A=FFT_en_C(N, omega, polA);                                                                                                                                                                                                                                 
        B=FFT_en_C(N, omega, polB);
                                                                                                                    
        for(int i=0;i<((int) Math.pow( 2, N));i++){
            C.add(i, mult_en_C(A.get(i), B.get(i)));
        }
        //Llamada a la transformada de Fourier Inversa.
        polSol=IFFT_en_C(N, omega, C);

        return polSol;
    }
    
     /**
     * Elimina los ceros a la derecha de un polinomio.
     * @param pol el polinomio que se va a modificar.
     */
    public static void quitar_ceros_en_C(ArrayList<ArrayList<Double>> pol){
        for(int i=pol.size()-1;pol.get(i).get(0).intValue()==0 && pol.get(i).get(1).intValue()==0 && i>0;i--)
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
   public static void mostrar_polinomio(ArrayList<ArrayList<Double>> polA){
        for(int i=polA.size()-1;i>=0;i--){
            if(i!=0){
                //System.out.print("("+polA.get(i).get(0)+"+"+polA.get(i).get(1) +"i)x^"+i+" + ");
                System.out.print("(");
                if(polA.get(i).get(0).intValue()+0.5 < polA.get(i).get(0) && polA.get(i).get(0)>0)
                    System.out.print(polA.get(i).get(0).intValue()+1);
                else if(polA.get(i).get(0).intValue()-0.5 > polA.get(i).get(0) && polA.get(i).get(0)<0)
                    System.out.print(polA.get(i).get(0).intValue()-1);
                else
                    System.out.print(polA.get(i).get(0).intValue());
                System.out.print("+");
                if(polA.get(i).get(1).intValue()+0.5 < polA.get(i).get(1) && polA.get(i).get(1)>0)
                    System.out.print(polA.get(i).get(1).intValue()+1);
                else if(polA.get(i).get(1).intValue()-0.5 > polA.get(i).get(1) && polA.get(i).get(1)<0)
                    System.out.print(polA.get(i).get(1).intValue()-1);
                else
                    System.out.print(polA.get(i).get(1).intValue());           
                System.out.print("i)x^"+i+" + ");
            }else{
                //System.out.print("("+polA.get(i).get(0).intValue()+"+"+polA.get(i).get(1).intValue() +"i)");
                System.out.print("(");
                if(polA.get(i).get(0).intValue()+0.5 < polA.get(i).get(0) && polA.get(i).get(0)>0)
                    System.out.print(polA.get(i).get(0).intValue()+1);
                else if(polA.get(i).get(0).intValue()-0.5 > polA.get(i).get(0) && polA.get(i).get(0)<0)
                    System.out.print(polA.get(i).get(0).intValue()-1);
                else
                    System.out.print(polA.get(i).get(0).intValue());
                System.out.print("+");
                if(polA.get(i).get(1).intValue()+0.5 < polA.get(i).get(1) && polA.get(i).get(1)>0)
                    System.out.print(polA.get(i).get(1).intValue()+1);
                else if(polA.get(i).get(1).intValue()-0.5 > polA.get(i).get(1) && polA.get(i).get(1)<0)
                    System.out.print(polA.get(i).get(1).intValue()-1);
                else
                    System.out.print(polA.get(i).get(1).intValue());      
                System.out.print("i)");
            }
        }
       
   }
   public static boolean son_iguales(ArrayList<ArrayList<Double>> polA, ArrayList<ArrayList<Double>> polB){
       boolean iguales=true;
       if(polA.size()!=polB.size()){
           iguales=false;
       }else{
           for(int i=0;i<polA.size();i++){//|| (int) polA.get(i).get(1)!= (int) polB.get(i).get(1)
               if( polA.get(i).get(0).intValue()!= polB.get(i).get(0).intValue() ||  polA.get(i).get(1).intValue()!= polB.get(i).get(1).intValue()){
                   iguales=false;
               }
           }
       }              
       return iguales;
   }
   public static int leer_numero(){
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader (isr); 
                String input=new String();
                int num;
        try{
            input = br.readLine();
        }catch(java.io.IOException e){
            System.out.print("\nExcepcion ocurrida "+e.getMessage());     
        }
        num=Integer.parseInt(input);
        return num;
   }     

    public static void main(String[] args) throws IOException {
        //Random rnd = new Random();
    	//rnd.setSeed(20011974);
        /**
        System.out.print("\nINTRODUCE EL PRIMER POLINOMIO DE COMPLEJOS:\n");
        polA=leer_polinomio_en_C();
        
        System.out.print("\nINTRODUCE EL SEGUNDO POLINOMIO DE COMPLEJOS:\n");
        polB=leer_polinomio_en_C(); 
        **//*
        for(int grad=1;grad<=101;grad++){ 
            ArrayList<ArrayList<Double>> polA=new ArrayList<ArrayList<Double>>();
            ArrayList<ArrayList<Double>> polB=new ArrayList<ArrayList<Double>>();
            ArrayList<ArrayList<Double>> polSolEsc_Doub=new ArrayList<ArrayList<Double>>();
            ArrayList<ArrayList<Integer>> polSolEsc_Int=new ArrayList<ArrayList<Integer>>();
            ArrayList<ArrayList<Double>> polSolFFT_Doub=new ArrayList<ArrayList<Double>>();
            ArrayList<ArrayList<Integer>> polSolFFT_Int=new ArrayList<ArrayList<Integer>>();
            
            for(int j=0;j<=grad;j++){
                ArrayList<Double>complejo1=new ArrayList<>();
                complejo1.add(0, rnd.nextDouble()*9);
                complejo1.add(1, rnd.nextDouble()*87);
                polA.add(j, complejo1);
            }  
            for(int j=0;j<grad;j++){
                ArrayList<Double>complejo2=new ArrayList<>();
                complejo2.add(0, rnd.nextDouble()*654);
                complejo2.add(1, rnd.nextDouble()*3210);
                polB.add(j, complejo2);
            }
            System.out.print("\n--> Grado de polA="+(polA.size()-1)+"  --> Grado de polB="+(polB.size()-1));

            polSolEsc_Doub=multiplicacion_escuela_en_C(polA, polB);
            //Hacer casting a entero 
            for(int i=0;i<polSolEsc_Doub.size();i++){
                ArrayList<Integer> aux=new ArrayList<Integer>();
                aux.add(0, polSolEsc_Doub.get(i).get(0).intValue());
                aux.add(1, polSolEsc_Doub.get(i).get(1).intValue());
                polSolEsc_Int.add(i, aux);
            }
            System.out.print("\n--> Resultado en C de multiplicacion ESCUELA:\n"+polSolEsc_Int);
            /*
            El grado de cada polinomio (n y m) es el tamaño del polinomio menos uno, ya que el indice de cada ArrayList 
            nos indica el exponente al que esta elevado el coeficiente que ocupa esa posicion.
            Por tanto hay que tener en cuenta rellenar los coeficientes nulos del polinomio con ceros en el ArrayList.
            *//*
            polSolFFT_Doub=multiplicacion_FFT_en_C(polA, polB, polA.size()-1, polB.size()-1);
            //Hacer casting a entero 
            for(int i=0;i<polSolEsc_Doub.size();i++){
                ArrayList<Integer> aux=new ArrayList<Integer>();
                aux.add(0, polSolFFT_Doub.get(i).get(0).intValue());
                aux.add(1, polSolFFT_Doub.get(i).get(1).intValue());
                polSolFFT_Int.add(i, aux);
            }
            System.out.print("\n--> Resultado en C de multiplicacion rapida mediante FFT:\n"+polSolFFT_Int+"\n");
        }
        */
        int salir=1;
        while(salir==1){
            ArrayList<ArrayList<Double>> polA=new ArrayList<ArrayList<Double>>();
            ArrayList<ArrayList<Double>> polB=new ArrayList<ArrayList<Double>>();
 
            Random rnd = new Random();
            int intro_datos=0;
            while(intro_datos!=1 && intro_datos!=2){
                System.out.print("\nDesea introducir los polinomios:  \n    1. A mano \n    2. Aleatoriamente\n");
                //intro_datos=leer_numero();
                intro_datos=2;
                if(intro_datos!=1 && intro_datos!=2){
                    System.out.print("\nError: diga un numero entre 1 y 2.");
                }
            }
            if(intro_datos==1){
                System.out.print("\n    OJO: Los coeficientes se introducen de menor a mayor.");
                System.out.print("\n         Los coeficientes nulos se introduciran como 0.");        
                System.out.print("\n         Los coeficientes se introducen como (x,y) siendo x la parte real e y la parte imaginaria."); 
                System.out.print("\n         Ejemplo: (3,4) (8,2) es (3+4i)+(8+2i)x.");                     
                System.out.print("\nINTRODUCE EL PRIMER POLINOMIO DE NUMEROS EN C:\n");
                polA=leer_polinomio_en_C();
                System.out.print("\nINTRODUCE EL SEGUNDO POLINOMIO DE NUMEROS EN C:\n");
                polB=leer_polinomio_en_C(); 

            }else if(intro_datos==2){
                System.out.print("\nCuantos digitos quiere que tenga el primer polinomio:\n");
                int num_dig=leer_numero(); 
                num_dig++;
                System.out.print("\nCuantos digitos quiere que tenga el segundo polinomio:\n");
                int num_dig2=leer_numero();
                num_dig2++;
                System.out.print("\nDiga una semilla para generar el numero aleatorio:\n");
                int semilla=1;         
                rnd.setSeed(semilla);
                for(int i=0;i<num_dig;i++){
                    ArrayList<Double>complejo1=new ArrayList<>();
                    Double aux1;
                    aux1=rnd.nextDouble()*1000;
                    complejo1.add(0, (double)aux1.intValue());
                    aux1=rnd.nextDouble()*1000;                    
                    complejo1.add(1, (double)aux1.intValue());
                    polA.add(i, complejo1);
                }
                for(int i=0;i<num_dig2;i++){
                    ArrayList<Double>complejo1=new ArrayList<>();
                    Double aux1;
                    aux1=rnd.nextDouble()*1000;
                    complejo1.add(0, (double)aux1.intValue());
                    aux1=rnd.nextDouble()*1000;                    
                    complejo1.add(1, (double)aux1.intValue());
                    polB.add(i, complejo1);
                }               
            }
            System.out.print("\nPrimer polinomio generado aleatoriamente:  ");
            mostrar_polinomio(polA);
            System.out.print("\nSegundo polinomio generado aleatoriamente: ");
            mostrar_polinomio(polB);
            int mult_usada=3;
            while(mult_usada!=1 && mult_usada!=2 && mult_usada!=3){
                System.out.print("\n\nDiga que multiplicacion quiere usar: \n   1.Escuela\n   2.Multiplicacion mediante FFT\n   3.Las dos anteriores para comparar los tiempos\n");
                mult_usada=leer_numero();      
                if(mult_usada!=1 && mult_usada!=2 && mult_usada!=3){
                    System.out.print("\nError: diga un numero entre 1 y 3.");
                }
            }
                if(mult_usada==1){               
                    ArrayList<ArrayList<Double>> polSol=new ArrayList<ArrayList<Double>>();                 
                    long time1=System.currentTimeMillis();
                    polSol=multiplicacion_escuela_en_C(polA, polB);
                    long time2=System.currentTimeMillis();
                    if(polSol.get(polSol.size()-1).get(0).intValue() == 0 && polSol.get(polSol.size()-1).get(1).intValue() == 0)
                          quitar_ceros_en_C(polSol);                    
                    System.out.print("\nResultado en C de multiplicacion ESCUELA:\n    ");        
                    mostrar_polinomio(polSol);
                    long tiempo=time2-time1;
                    System.out.print("\n    Tiempo empleado en la multiplicacion escuela:   "+tiempo+" ms");
                }else if(mult_usada==2){
                    ArrayList<ArrayList<Double>> polSol2=new ArrayList<ArrayList<Double>>();       
                    long tiempo;
                    long time1=System.currentTimeMillis();
                    polSol2=multiplicacion_FFT_en_C(polA, polB, polA.size()-1, polB.size()-1);
                    long time2=System.currentTimeMillis();
                     tiempo=time2-time1;
                    
                    if(polSol2.get(polSol2.size()-1).get(0).intValue() == 0 && polSol2.get(polSol2.size()-1).get(1).intValue() == 0)
                          quitar_ceros_en_C(polSol2);  
                    System.out.print("\nResultado en C de multiplicacion mediante FFT:\n   ");        
                    mostrar_polinomio(polSol2);
                    System.out.print("\n    Tiempo empleado en la multiplicacion mediante FFT:   "+tiempo+" ms");
                }else if(mult_usada==3){
                    ArrayList<ArrayList<Double>> polSol=new ArrayList<ArrayList<Double>>();  
                    
                    int repEsc=10;
                    //Bucle (cambiar repeticiones)
                    long time1=System.currentTimeMillis();   
                    for(int p=0;p<repEsc;p++)
                        polSol=multiplicacion_escuela_en_C(polA, polB);
                    long time2=System.currentTimeMillis();
                    
                    if(polSol.get(polSol.size()-1).get(0).intValue() == 0 && polSol.get(polSol.size()-1).get(1).intValue() == 0)
                          quitar_ceros_en_C(polSol);                    
                    System.out.print("\nResultado en C de multiplicacion ESCUELA:\n    ");        
                    mostrar_polinomio(polSol);
                    
                    //Tiempo (cambiar repeticiones)
                    long tiempo=(time2-time1)/repEsc;
                    System.out.print("\n    Tiempo empleado en la multiplicacion escuela:   "+tiempo+" ms");
                    
                    ArrayList<ArrayList<Double>> polSol2=new ArrayList<ArrayList<Double>>(); 
                    
                    int repFFT=10;
                    //Bucle (cambiar repeticiones)
                    time1=System.currentTimeMillis();
                    for(int p=0;p<repFFT;p++)
                        polSol2=multiplicacion_FFT_en_C(polA, polB, polA.size()-1, polB.size()-1);
                    time2=System.currentTimeMillis();
                    //Tiempo (cambiar repeticiones)
                    tiempo=(time2-time1)/repFFT;
                            
                    
                    if(polSol2.get(polSol2.size()-1).get(0).intValue() == 0 && polSol2.get(polSol2.size()-1).get(1).intValue() == 0)
                          quitar_ceros_en_C(polSol2);  
                    System.out.print("\nResultado en C de multiplicacion mediante FFT:\n   ");        
                    mostrar_polinomio(polSol2);
                    System.out.print("\n    Tiempo empleado en la multiplicacion mediante FFT:   "+tiempo+" ms");                    
                }                
            
            salir=0;
            while(salir!=1 && salir!=2){
                System.out.print("\n\n¿Desea hacer mas multiplicaciones de polinomios en C?\n   1. Si\n   2. No\n");
                salir=2;      
                if(salir!=1 && salir!=2){
                    System.out.print("\nError: diga un numero entre 1 y 2.");
                }
            }                     
        }      
    }
}
