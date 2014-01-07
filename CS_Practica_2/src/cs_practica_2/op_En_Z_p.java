
package cs_practica_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.math.BigInteger;
public class op_En_Z_p {
    static int primo=0;
    op_En_Z_p(int prim){
        primo=prim;
    }
    op_En_Z_p(){
        primo=0;
    }
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
     * Calcula la raiz potDe2-esima primitiva de la unidad en Zp (Cuando se tiene un primo pequeÃ±o, 
     * se pueden localizar las raÃ­ces primitivas por â€œensayo y errorâ€�,construyendo una tabla de potencias).
     * @param potDe2 numero equivalente a n en la raiz n-esima.
     * @param p modulo para reducir.
     * @return la raiz potDe2-esima primitiva de la unidad.
    */
   public static Integer raiz_n_esima_primitiva_en_Zp(Integer potDe2, Integer p){
        int raiz=0;

        boolean salte=false;
        for(int i=2;i<p && salte==false;i++){
            if(potencia_modulo_p(i, potDe2,p) == 1){
                raiz=i;
                salte=true;
            }
            if(salte==true){
                for(int j=1;j<potDe2 && salte==true;j++){
                    if(potencia_modulo_p(raiz, j,p) == 1){
                        salte=false;
                    }
                }
            }
        }
        if(salte==false)
            raiz=-1;
        return raiz;
    }
   
   
   /**
    * Localizar las raices primitivas por ensayo  error (mostrar tabla), pagina 94 TeoriaDeNumeros.pdf
    * @param p modulo.
    */
    public static void mostrar_raices_primitivas_en_Zp(Integer p){
        
        for(int base=1;base<p;base++){
            System.out.print("\n"+base+" |");
            for(int exp=1;exp<p;exp++){
                System.out.print(" "+((int)Math.pow(base, exp))%p);
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
        
        //AÃ±adir ceros al polinomio solucion
        for(int i=0;i<(polA.size()+polB.size())-1;i++)
             polC.add(0);
        
        for(int k=0;k<polB.size();k++)
            for(int i=0;i<polA.size();i++)
               polC.set(i+k, modulo_en_Zp(p, (Integer) (multiplica_modulo_Zp(polA.get(i),polB.get(k),p)+polC.get(i+k))%p));

        return polC;
    }
    public static int multiplica_modulo_Zp(int x, int y, int p){
        int result;
        if(y==0){
            result=0;
        }else{
            result=x;
            for(int i=1;i<y;i++){
                result=(result+x)%p;
                if(result<0){
                    result=result+x;
                }
            }
        }
        
        return result;
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
        
        //Inicializar A con ceros con un tamaÃ±o igual a 2^N 
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
            B=FFT_en_Zp(N-1, multiplica_modulo_Zp(omega, omega,p), polB, p);
            C=FFT_en_Zp(N-1, multiplica_modulo_Zp(omega, omega,p), polC, p);
            for(int i=0;i<((int)Math.pow(2, N-1));i++){
                A.set(i, modulo_en_Zp(p,(int) (((double)B.get(i)+( (double)multiplica_modulo_Zp((potencia_modulo_p(omega,i,p) ), C.get(i),p)))%p)));
                A.set(((int)Math.pow(2, N-1))+i, modulo_en_Zp(p,(int) (((double)B.get(i)-( (double)multiplica_modulo_Zp((potencia_modulo_p(omega,i,p) ), C.get(i),p)))%p)));
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
        //Llamar a 'FFT_en_Zp' pero con el inverso de omega
        polAux=FFT_en_Zp(N, invOmega, polC, p);
        //Multiplicar cada uno de los elementos del polinomio devuelto por 'FFT_en_Zp' por el inverso de 2^N y el nuemo polinomio sera la solucion
        Integer invPotDe2=inverso_en_Zp(p, ((int) Math.pow( 2, N)));
        for(int i=0;i<polAux.size();i++){
            polSol.add(i, modulo_en_Zp(p, (int)( multiplica_modulo_Zp( invPotDe2, polAux.get(i),p)%p)));
        }
        return polSol;
    }
    public static int potencia_modulo_p(Integer x, Integer y, Integer p){
        int result;
        int x_double=x;
        int p_double=p;
        
        if(y==0)
            result=1;
        else{
            result=x_double;
            for(int j=0;j<y-1;j++){
                result=multiplica_modulo_Zp(result,x_double,p_double);
                if(result<0)
                    result=result+p_double;
            }
        }        
        return result;        
    }
    
        
    /**
     * Multiplicacion rapida de polinomios mediante FFT.
     * El grado de cada polinomio (n y m) es el tamaÃ±o del polinomio menos uno, ya que el indice de cada ArrayList 
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
        
        //Calcular primer entero tal que m+n < 2^N (Â¡Â¡Â¡Mayor estricto!!!)
        Integer N=0;
        while(((m+n) -(int) Math.pow( 2, N))>0){
            N++;
        }
        if(((int) Math.pow( 2, N))==(n+m))
            N++;    
        //Calcular omega=raiz 2^N-esima primitiva de la unidad
        omega=raiz_n_esima_primitiva_en_Zp(((int) Math.pow( 2, N)), p);
        //Antes de llamar a 'FFT_en_Zp' hay que aÃ±adir ceros al final de polA y polB hasta que tengan un tamaÃ±o igual a 2^N
        for(int i=polA.size();i<((int) Math.pow( 2, N));i++)
            polA.add(i,0);
        for(int i=polB.size();i<((int) Math.pow( 2, N));i++)
            polB.add(i,0);

        A=FFT_en_Zp(N, omega, polA, p);
        B=FFT_en_Zp(N, omega, polB, p);

        for(int i=0;i<((int) Math.pow( 2, N));i++){
            C.add(i, modulo_en_Zp(p,multiplica_modulo_Zp(A.get(i),B.get(i),p)%p));
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
        boolean es_cero=false;
        for(int i=pol.size()-1;pol.get(i)==0;i--){
            pol.remove(i);
            if(pol.size()==0){
                pol.add(0);
                return;
            }
        }
   }
    
    
    /**
     * Muestra por pantalla el polinomio  empezando por el coeficiente menos significativo 
     * @param pol el polinomio que se va a mostrar por pantalla 
     */
    public static void mostrar_polinomio_en_Zp(ArrayList<Integer> pol){
        System.out.print("[");
        for(int i=0;i<pol.size();i++)
            System.out.print(pol.get(i)+" ");
       
        System.out.print("]\n");
   }
    
    
   /**
    * Lee de la entrada estÃ¡ndar un polinomio con el formato num1 num2 num3 ... empezando por el coeficiente menos significativo.
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
                Integer num=modulo_en_Zp(p,(( Integer.parseInt(dig))%p));
                pol.add(num);
            }
            else
                i++;
        }
        return pol;
   }
   public static boolean son_iguales(ArrayList<Integer> polA, ArrayList<Integer> polB){
       boolean iguales=true;
       if(polA.size()!=polB.size()){
           iguales=false;
       }else{
           for(int i=0;i<polA.size();i++){
               if((int) polA.get(i)!= (int) polB.get(i)){
                   iguales=false;
               }
           }
       }              
       return iguales;
   }
   public static boolean existe_raiz_nesima_sobre_zp(int grado, int p){
       boolean existe=true;
       
        Integer N=0;
        while((grado -(int) Math.pow( 2, N))>0){
            N++;
        }
        if(((int) Math.pow( 2, N))==grado)
            N++;    
        int omega;
        omega=raiz_n_esima_primitiva_en_Zp(((int) Math.pow( 2, N)), p);
        if(omega==-1)
            existe=false;
       
       return existe;
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
        int salir=1;
        while(salir==1){
            ArrayList<Integer> polA=new ArrayList<Integer>();
            ArrayList<Integer> polB=new ArrayList<Integer>();
 
            Random rnd = new Random();
            int p=1;
            int intro_datos=0;
            if(primo==0){
                System.out.print("\nINTRODUCE UN NUMERO PRIMO IMPAR p:\n");
                p=leer_numero();
            }else{
                p=primo;
            }
            while(intro_datos!=1 && intro_datos!=2){
                System.out.print("\nDesea introducir los polinomios:  \n    1. A mano \n    2. Aleatoriamente\n");
                intro_datos=leer_numero();
                if(intro_datos!=1 && intro_datos!=2){
                    System.out.print("\nError: diga un numero entre 1 y 2.");
                }
            }
            if(intro_datos==1){
                System.out.print("\n    OJO: Los coeficientes se introducen de menor a mayor.");
                System.out.print("\n         Los coeficientes nulos se introduciran como 0.");                 
                System.out.print("\nINTRODUCE EL PRIMER POLINOMIO DE NUMEROS EN Z"+p+":\n");
                polA=leer_polinomio_en_Zp(p);
                System.out.print("\nINTRODUCE EL SEGUNDO POLINOMIO DE NUMEROS EN Z"+p+":\n");
                polB=leer_polinomio_en_Zp(p); 

            }else if(intro_datos==2){
                System.out.print("\nCuantos digitos quiere que tenga el primer polinomio:\n");
                int num_dig=leer_numero();        
                System.out.print("\nCuantos digitos quiere que tenga el segundo polinomio:\n");
                int num_dig2=leer_numero();
                System.out.print("\nDiga una semilla para generar el numero aleatorio:\n");
                int semilla=leer_numero();         
                rnd.setSeed(semilla);
                for(int i=0;i<num_dig;i++){
                    polA.add(i,modulo_en_Zp(p, (rnd.nextInt())%p));
                }
                for(int i=0;i<num_dig2;i++){
                    polB.add(i,modulo_en_Zp(p, (rnd.nextInt())%p));
                }               
            }
            System.out.print("\nPrimer polinomio generado aleatoriamente:  ");
            for(int i=polA.size()-1;i>=0;i--){
                if(i!=0)
                    System.out.print(polA.get(i)+"x^"+i+" + ");
                else
                    System.out.print(polA.get(i));
            }
            System.out.print("\nSegundo polinomio generado aleatoriamente: ");
            for(int i=polB.size()-1;i>=0;i--){
                if(i!=0)
                    System.out.print(polB.get(i)+"x^"+i+" + ");
                else
                    System.out.print(polB.get(i));
            }

            int mult_usada=0;
            while(mult_usada!=1 && mult_usada!=2 && mult_usada!=3){
                System.out.print("\n\nDiga que multiplicacion quiere usar: \n   1.Escuela\n   2.Multiplicacion mediante FFT\n   3.Las dos anteriores para comparar los tiempos\n");
                mult_usada=leer_numero();      
                if(mult_usada!=1 && mult_usada!=2 && mult_usada!=3){
                    System.out.print("\nError: diga un numero entre 1 y 3.");
                }
            }
            if((mult_usada==2 || mult_usada==3) && existe_raiz_nesima_sobre_zp((polA.size()+polB.size()-2),p)==false){
                System.out.print("\nSobre Z"+p+" no se pueden multiplicar mediante la FFT, dos polinomios cuyos grados sumen "+(polA.size()+polB.size()-2)+".");
            }else{
                if(mult_usada==1){               
                    ArrayList<Integer> polSol=new ArrayList<Integer>();                 
                    long time1=System.currentTimeMillis();
                    polSol=multiplicacion_escuela_en_Zp(polA, polB, p);
                    long time2=System.currentTimeMillis();
                    if(polSol.get(polSol.size()-1) == 0)
                          quitar_ceros_en_Zp(polSol);                    
                    System.out.print("\nResultado en Z"+p+" de multiplicacion ESCUELA:\n    ");        
                    for(int i=polSol.size()-1;i>=0;i--){
                        if(i!=0)
                            System.out.print(polSol.get(i)+"x^"+i+" + ");
                        else
                            System.out.print(polSol.get(i));
                    } 
                    long tiempo=time2-time1;
                    System.out.print("\n    Tiempo empleado en la multiplicacion escuela:   "+tiempo+" ms");
                }else if(mult_usada==2){
                    ArrayList<Integer> polSol2=new ArrayList<Integer>();  
                    long tiempo;
                    long time1=System.currentTimeMillis();
                    polSol2=multiplicacion_FFT_en_Zp(polA, polB, polA.size()-1, polB.size()-1, p);
                    long time2=System.currentTimeMillis();
                     tiempo=time2-time1;
                    
                    if(polSol2.get(polSol2.size()-1) == 0)
                        quitar_ceros_en_Zp(polSol2); 
                    System.out.print("\nResultado en Z"+p+" de multiplicacion mediante FFT:\n   ");        
                    for(int i=polSol2.size()-1;i>=0;i--){
                        if(i!=0)
                            System.out.print(polSol2.get(i)+"x^"+i+" + ");
                        else
                            System.out.print(polSol2.get(i));
                    } 
                    System.out.print("\n    Tiempo empleado en la multiplicacion mediante FFT:   "+tiempo+" ms");
                }else if(mult_usada==3){
                    ArrayList<Integer> polSol=new ArrayList<Integer>();                 
                    long time1=System.currentTimeMillis();
                    polSol=multiplicacion_escuela_en_Zp(polA, polB, p);
                    long time2=System.currentTimeMillis();
                    if(polSol.get(polSol.size()-1) == 0)
                          quitar_ceros_en_Zp(polSol);                    
                    System.out.print("\nResultado en Z"+p+" de multiplicacion ESCUELA:\n    ");        
                    for(int i=polSol.size()-1;i>=0;i--){
                        if(i!=0)
                            System.out.print(polSol.get(i)+"x^"+i+" + ");
                        else
                            System.out.print(polSol.get(i));
                    } 
                    long tiempo=time2-time1;
                    System.out.print("\n    Tiempo empleado en la multiplicacion escuela:   "+tiempo+" ms");

                    ArrayList<Integer> polSol2=new ArrayList<Integer>();  
                    tiempo=0;

                    if(polA.get(polA.size()-1) == 0)
                          quitar_ceros_en_Zp(polA);       
                    if(polB.get(polB.size()-1) == 0)
                          quitar_ceros_en_Zp(polB);   
                    
                     time1=System.currentTimeMillis();
                    polSol2=multiplicacion_FFT_en_Zp(polA, polB, polA.size()-1, polB.size()-1, p);
                     time2=System.currentTimeMillis();
                     tiempo=time2-time1;
                    
                    if(polSol2.get(polSol2.size()-1) == 0)
                        quitar_ceros_en_Zp(polSol2); 
                    System.out.print("\nResultado en Z"+p+" de multiplicacion mediante FFT:\n   ");        
                    for(int i=polSol2.size()-1;i>=0;i--){
                        if(i!=0)
                            System.out.print(polSol2.get(i)+"x^"+i+" + ");
                        else
                            System.out.print(polSol2.get(i));
                    } 
                    System.out.print("\n    Tiempo empleado en la multiplicacion mediante FFT:   "+tiempo+" ms");
                    if(son_iguales(polSol, polSol2)==true){
                        System.out.print("\nSon iguales :) !!!!!!!!!!!!!!");
                    }else
                        System.out.print("\nMALLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
                    
                }                
            }
            salir=0;
            while(salir!=1 && salir!=2){
                System.out.print("\n\n¿Desea hacer mas multiplicaciones de polinomios en Zp?\n   1. Si\n   2. No\n");
                salir=leer_numero();      
                if(salir!=1 && salir!=2){
                    System.out.print("\nError: diga un numero entre 1 y 2.");
                }
            }                     
        }
    }
}

    


