/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculosimbolico;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.lang.Math;
import java.math.BigInteger;
/**
 *
 * @author Francisco
 */
public class CalculoSimbolico {

    
    /**
     * Convierte el ArrayList de entrada que es un numero en Hexadecimal a un número en base 16^2 de la siguiente forma:
     * 1.Recorre el ArrayList de entrada  y convierte cada 4 cifras de Hexadecimal en un dígito de nuestro numero en base
     * 2^16, cada posicion contendra un numero 'num_i'  menor que 16^4=65536 el cual multiplica a la base elevada a la 
     * posicion que ocupa, o sea: num_0*65536^0 + num_1*65536^1 + num_2*65536^2.....num_list.size*65536^list.size.
     * 2.Devuelve un ArrayList cuyas posiciones contienen cada uno de los dígitos del número en base 2^16.
     * @param num cadena de entrada que contiene el numero en Hexadecimal que se introduce
     * @return numeroInt ArrayList con el numero en base 2^16, en cada una de las posiciones hay un dígito del número.
    */
    public static ArrayList<Integer> cambiaBase(ArrayList<Character> num){
        int digito=0;
        ArrayList<Integer> numeroInt=new ArrayList<Integer>();
        for(int i=0;i<num.size();i=i+4){
            digito=0;
            for(int j=0;j<4 && i+j<num.size();j++){
                int aux;
                
                if( num.get(i+j) == 'A' || num.get(i+j) == 'a'){
                    aux=10;
                }else if( num.get(i+j) == 'B' || num.get(i+j) == 'b'){
                    aux=11;
                }else if( num.get(i+j) == 'C' || num.get(i+j) == 'c'){
                    aux=12;
                }else if( num.get(i+j) == 'D' || num.get(i+j) == 'd'){
                    aux=13;
                }else if( num.get(i+j) == 'E' || num.get(i+j) == 'e'){
                    aux=14;
                }else if( num.get(i+j) == 'F' || num.get(i+j) == 'f'){
                    aux=15;
                }else{
                    aux=Integer.parseInt(num.get(i+j).toString());
                }
                digito=digito+aux*(int) Math.pow( 16, j);
            }
            numeroInt.add(digito);
        }
        
        return numeroInt;
   }
    
   
   /**
     * Lee de un fichero de entrada o de la entrada estándar un número en hexadecimal el cual después de transformarse a base 
     * 2^16 será usado para las operaciones correspondientes.
   */
   public static ArrayList<Character> leer_numero_hexadecimal(){
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
        ArrayList<Character> numChar=new ArrayList<Character>();
        for(int i=0;i<numStr.length();i++){       
            numChar.add(numStr.charAt(numStr.length()-1-i));
        }
        return numChar;
   }
   
   
   /**
    * Imprime el numero obtenido del ArrayList que se le pasa por parametro empezando por el final.
    * @param vector_enteros ArrayList el cual se  va a mostrar por pantalla.
   */
   public static void mostrar_numero(ArrayList<Integer> vector_enteros){
        for(int i=0;i<vector_enteros.size();i++)
            System.out.print(" "+vector_enteros.get(vector_enteros.size()-1-i));
   }
   
   
   /**
     * Suma los dos números en base 2^16 pasados por parámetro mediante el algoritmo suma_escuela siguiendo los siguientes pasos:
     * 1.Se comprueba el número de dígitos de ambos números y se completa con ceros a la izquierda el de menor tamaño hasta que
     * este alcance un número de digitos igual al otro número. Si los dos números son iguales no se hace nada.
     * 2.Ir recorriendo los dos números desde el digito menos significativo hasta el mas significativo e ir sumando ambos dígitos
     * introduciendo en el ArrayList 'result' el digito con el resultado de hacer modulo base 2^16 de la suma, añadiendo además
     * el acarreo, el cual se obtiene del cociente de dividir la suma de ambos digitos entre la base 2^16.
     * @param num1 ArrayList con el primer número en base 2^16 que se va a sumar mediante suma_escuela.
     * @param num2 ArrayList con el segundo número en base 2^16 que se va a sumar mediante suma_escuela.
     * @return result ArrayList con el resultado de sumar los dos número en base 2^16 mediante suma_escuela. 
   */
   public static ArrayList<Integer> suma_escuela(ArrayList<Integer> num1, ArrayList<Integer> num2){
        ArrayList<Integer> result=new ArrayList<Integer>();
        int num_dig;   
        
        if(num1.size()>num2.size()){
            num_dig=num1.size();
            //anado ceros a num2 para que tenga el mismo numero de dig que num1
            for(int i=num2.size();i<num1.size();i++)
                num2.add(0);
        }else if(num2.size()>num1.size()){
            num_dig=num2.size();
            //anado ceros a num2 para que tenga el mismo numero de dig que num1
            for(int i=num1.size();i<num2.size();i++)
            num1.add(0);
        }else{
            num_dig=num2.size();
        }
        
        int acarreo=0, temporal=0;
        for(int i=0;i<num_dig;i++){
            temporal=num1.get(i)+num2.get(i)+acarreo;
            int dig;
            dig=temporal% (int) Math.pow( 2, 16);
            result.add(dig);
            acarreo= new Double (temporal/Math.pow( 2, 16)).intValue();
        }
        return result;
   } 
   
   
   /**
     * Multiplica los dos números que se pasan como parámetro por medio del algoritmo mult_escuela siguiendo los siguientes pasos:
     * 1.Se añaden ceros en un número de posiciones de 'result' igual al tamaños del número que se va a multiplcar.
     * 2.Se va recorriendo el primer y segundo número multiplicando todos los factores de num1 por cada uno de los factores de
     * num2 y almacenando en 'result' el resultado de esta multiplicación haciendo modulo con la base 2^16 y sumando también el 
     * acarreo.
     * 3.El acarreo se calcula como el cociente del resultado de la multiplicación entre la base 2^16.
     * @param num1 ArrayList con el primer número en base 2^16 que se va a multiplicar mediante mult_escuela.
     * @param num2 ArrayList con el segundo número en base 2^16 que se va a multiplicar mediante mult_escuela.
     * @return result ArrayList con el resultado de multiplicar  los dos número en base 2^16 mediante mult_escuela.
   */
   public static ArrayList<Integer> mult_escuela(ArrayList<Integer> num1, ArrayList<Integer> num2){
        ArrayList<Integer> result=new ArrayList<Integer>();

        for(int i=0;i<num2.size();i++)
            result.add(0);
        
        long temporal=0;
        int acarreo=0;
        for(int i=0;i<num1.size();i++){
            acarreo=0;
            for(int k=0;k<num2.size();k++){
                temporal=getUnsignedInt(num1.get(i)*num2.get(k))+result.get(i+k)+acarreo;
                result.set(i+k, (int) (temporal%(int) Math.pow( 2, 16)));
                acarreo=new Double (temporal/Math.pow( 2, 16)).intValue();
            }
            result.add( acarreo);
        }        
        
        return result;
   }
   
   
   /**
    * Elimina los ceros de la izquierda que se han introducido durante las operaciones como digitos nulos.
    * @param num1 ArrayList el cual se va a modificar quitandole los ceros.
    * @return num1 el ArrayList una vez eliminados los ceros 
    */
   public static ArrayList<Integer> elimina_ceros_izq(ArrayList<Integer> num1){
       int i=num1.size()-1;
       while(num1.get(i)==0 && num1.size()!=1){
           num1.remove(i);
           i--;
       }
       return num1;
   }
   
   
   /**
     * Resta los dos números en base 2^16 pasados por parámetro mediante el algoritmo resta_escuela siguiendo los siguientes pasos:
     * 1.Se comprueba el número de dígitos de ambos números y se completa con ceros a la izquierda el de menor tamaño hasta que
     * este alcance un número de digitos igual al otro número.
     * 2.Ir recorriendo los dos números desde el digito menos significativo hasta el mas significativo e ir restando ambos
     * introduciendo en el ArrayList 'result' un digito con el resultado de hacer modulo base 2^16 de la resta de ambos digitos.
     * 3.El acarreo, el cual será siempre igual a 1 se sumará al siguiente digito del sustraendo antes de realizar la resta de
     * ambos dígitos.
     * @param num1 ArrayList con el primer número en base 2^16 que se va a restar mediante resta_escuela (minuendo). 
     * @param num2 ArrayList con el segundo número en base 2^16 que se va a restar mediante resta_escuela (sustraendo). 
     * @return result ArrayList con el resultado de restar los dos número en base 2^16 mediante resta_escuela (diferencia). 
   */
   public static ArrayList<Integer> resta_escuela(ArrayList<Integer> num1, ArrayList<Integer> num2){        
        ArrayList<Integer> result=new ArrayList<>();
        int acarreo = 0, temporal=0;
        int num_dig=0;   
        int num_mayor=0;
        
        if(num1.size()>num2.size()){
            num_dig=num1.size();
            num_mayor=1;
            for(int i=num2.size();i<num1.size();i++)
                num2.add(0);
        }else if(num2.size()>num1.size()){
            num_dig=num2.size();
            num_mayor=2;
            for(int i=num1.size();i<num2.size();i++)
                num1.add(0);
        }else{
            num_dig=num2.size();
            int i=num_dig-1;
            while(num_mayor==0 && i>-1){
                if(num1.get(i)>num2.get(i)){
                    num_mayor=1;
                }else if(num2.get(i)>num1.get(i)){
                    num_mayor=2;
                }
                i--;
            }
            num_dig=num2.size();
        }
        boolean signo=true;
        if(num_mayor==2){  //cambio los numeros de orden
            signo=false;
            ArrayList<Integer> aux=new ArrayList<Integer>();
            aux=num1;
            num1=num2;
            num2=aux;
        }
        ArrayList<Integer> aux3=new ArrayList<>();
        for(int i=0;i<num2.size();i++)
            aux3.add(num2.get(i));
        for(int i=0;i<num_dig;i++){
                acarreo=0;
                temporal=num1.get(i) - num2.get(i);
                if(temporal<0){
                    temporal=(int) Math.pow( 2, 16)+temporal;
                    acarreo=1;
                }
                result.add((temporal % (int) Math.pow( 2, 16)));
                if(i+1!=num_dig){
                    num2.set(i+1, num2.get(i+1)+acarreo);  
                }
        }
        result=elimina_ceros_izq(result);
        if(signo==false){
            result.set(result.size()-1, result.get(result.size()-1)*(-1));
        }   
        num2.clear();
        for(int i=0;i<aux3.size();i++)
            num2.add(aux3.get(i));

        return result;
    }
   
   
    public static long getUnsignedInt(int x) {
        return x & 0x00000000ffffffffL;
    } 
    
    
    /**
     * Hace una llamada a mult_karatsuba para obtener el resultado de multiplicar los dos números en base 2^16 pasados 
     * por parámetro, además antes de esto obtiene el valor de m. Para todo ello se siguen los siguientes pasos:
     * 1.Se calcula cual de los dos números tiene un número mayor de dígitos.
     * 2.Se calcula m siendo su valor el logaritmo en base 2 del numero de digitos de la cifra que se va a multiplicar
     * que sea mayor o un número mayor a esta.
     * 3.Completar los dos numeros con ceros hasta llegar al tamaño de m.
     * 4.Hacer una llamada a mult_karatsuba con los dos números ya completados con ceros y el valor de m obtenido.
     * @param num1 ArrayList con el primer número en base 2^16 que se va a multiplicar mediante mult_karatsuba pasandose
     * como parámetro desde esta función a mult_karatsuba en la cual se realizará la multiplicación.
     * @param num2 ArrayList con el segundo número en base 2^16 que se va a multiplicar mediante mult_karatsuba pasandose
     * como parámetro desde esta función a mult_karatsuba en la cual se realizará la multiplicación. 
     * @return result ArrayList con el resultado de multiplicar  los dos número en base 2^16 mediante mult_karatsuba. 
    */
    public static ArrayList<Integer> calcula_m_karatsuba(ArrayList<Integer> num1, ArrayList<Integer> num2){
        int num_dig=0, num_mayor;
        ArrayList<Integer> vector_enteros=new ArrayList<>();
        ArrayList<Integer> result=new ArrayList<>();        
        if(num1.size()>=num2.size()){
            num_dig=num1.size();
            num_mayor=1;
        }else if(num2.size()>num1.size()){
            num_dig=num2.size();
            num_mayor=2;
        }
        
        int m=0;
        while(num_dig-(int) Math.pow( 2, m)>0){
            m++;
        }
        for(int i=num1.size();i< Math.pow( 2, m);i++){
            num1.add(0);
        }
        for(int i=num2.size();i< Math.pow( 2, m);i++){
            num2.add(0);
        }        
        result=mult_karatsuba(num1, num2, m);
        elimina_ceros_izq(result);
        return result;
    }
    
    
    /**
     * Multiplica los dos números que se pasan como parámetro por medio del algoritmo mult_karatsuba siguiendo los siguientes pasos:
     * 1.El algoritmo es un algoritmo recursivo por tanto tiene un caso base para cuando m es cero en el cual se almacena en el
     * resultado el digito y el acarreo de multiplicar los dos  digitos menos significativos de a y de b.
     * 2.Si no se ha entrado en el caso base se almacenan en a0 y a1 las partes menos significativa y mas significativa 
     * respectivamente de a haciendo los mismo para b0 y b1 para b.
     * 3.Se calculan t1, t2 y t3 haciendo tres llamadas recursivas a la función con sus correspondientes parámetros.
     * 4.Se calcula el resultado final el cual se devuelve recursivamente a las llamadas anteriores.
     * @param a ArrayList con el primer número en base 2^16 que se va a multiplicar mediante mult_karatsuba.
     * @param b ArrayList con el segundo número en base 2^16 que se va a multiplicar mediante mult_karatsuba.
     * @param m valor que va a servir para reducir hasta un caso base de cuando m sea cero.
     * @return aux2 ArrayList con el resultado de multiplicar  los dos número en base 2^16 mediante mult_karatsuba.
    */
    public static ArrayList<Integer> mult_karatsuba(ArrayList<Integer> a, ArrayList<Integer> b, int m){

        Integer acarreo=0;
        ArrayList<Integer> a0=new ArrayList<>();
        ArrayList<Integer> a1=new ArrayList<>();
        ArrayList<Integer> b0=new ArrayList<>();
        ArrayList<Integer> b1=new ArrayList<>();
        ArrayList<Integer> t1=new ArrayList<>();
        ArrayList<Integer> t2=new ArrayList<>();
        ArrayList<Integer> t3=new ArrayList<>();
        ArrayList<Integer> result=new ArrayList<>();
   
        long temporal;
        if(m==0){
            temporal=getUnsignedInt(a.get(0)*b.get(0));
            result.add((int) (temporal%(int) Math.pow( 2, 16)));
            acarreo=new Double (temporal/Math.pow( 2, 16)).intValue();
            result.add( acarreo);
            
            return result;
        }else{
            digitos_mas_sinificativos(a1, a);
            digitos_menos_sinificativos(a0, a);
            digitos_mas_sinificativos(b1, b);
            digitos_menos_sinificativos(b0, b);
            
            t1 = mult_karatsuba(a1, b1, m-1);
            
            boolean signo1=true, signo2=true;
            ArrayList<Integer> resta1=new ArrayList<>();
            ArrayList<Integer> resta2=new ArrayList<>();
            resta1=resta_escuela(a1, a0);     
            resta2=resta_escuela(b0, b1);
            if(resta1.get(resta1.size()-1)<0){
                signo1=false;
                resta1.set(resta1.size()-1,resta1.get(resta1.size()-1)*(-1));
            }
            if(resta2.get(resta2.size()-1)<0){
                signo2=false;
                resta2.set(resta2.size()-1,resta2.get(resta2.size()-1)*(-1));
            }        
            for(int i=resta1.size();i<Math.pow( 2, m-1);i++){//añado ceros a la izq por si en la resta se pierden ( 15 - 10 =5 MAL, BIEN=05)
                resta1.add(0);
            }
            for(int i=resta2.size();i<Math.pow( 2, m-1);i++){//añado ceros a la izq por si en la resta se pierden ( 15 - 10 =5 MAL, BIEN=05)
                resta2.add(0);
            }

            t2 = mult_karatsuba(resta1, resta2, m-1);
            t3 = mult_karatsuba(a0, b0, m-1);          
           
            ArrayList<Integer> aux=new ArrayList<>();
            acarreo=0;
            temporal=0;
            if(signo1==signo2){//si t2 sale positivo lo sumo
                for(int i=0;i<Math.pow( 2, m);i++){
                    temporal=t1.get(i)+t2.get(i)+t3.get(i)+acarreo;
                    int dig;
                    dig=(int) temporal% (int) Math.pow( 2, 16);
                    aux.add(dig);
                    acarreo= new Double (temporal/Math.pow( 2, 16)).intValue();
                }
                aux.add( acarreo);
            }else{//si t2 sale negativo aux=t1+t3-t2
                
                ArrayList<Integer> aux3=new ArrayList<>();
                for(int i=0;i<Math.pow( 2, m);i++){
                    temporal=t1.get(i)+t3.get(i)+acarreo;
                    int dig;
                    dig=(int) temporal% (int) Math.pow( 2, 16);
                    aux3.add(dig);
                    acarreo= new Double (temporal/Math.pow( 2, 16)).intValue();
                }
                aux3.add( acarreo);
   
                aux3=elimina_ceros_izq(aux3);
                t2=elimina_ceros_izq(t2); 
                aux=resta_escuela(aux3, t2);       
            }

            for(int i=0;i< Math.pow( 2, m-1);i++){
                aux.add(0, 0);
            }
            for(int i=0;i< Math.pow( 2, m);i++){
                t1.add(0, 0);
            }     
            for(int i=aux.size();i<t1.size();i++){
                aux.add(0);
            }
            for(int i=t3.size();i<t1.size();i++){
                t3.add(0);
            }
            ArrayList<Integer> aux2=new ArrayList<>();
            acarreo=0;
            temporal=0;

            for(int i=0;i<t1.size();i++){
                temporal=t1.get(i)+aux.get(i)+t3.get(i)+acarreo;
                int dig;
                dig=(int) temporal% (int) Math.pow( 2, 16);
                aux2.add(dig);
                acarreo= new Double (temporal/Math.pow( 2, 16)).intValue();
            }
            aux2.add( acarreo);

            return aux2;
        }
   }
    
    
    /**
     * Copia en el ArrayList 'parte' los digitos de la parte menos significativa del ArrayList 'num', es decir
     * desde la posición 0 hasta la mitad del tamaño que tenga 'num' menos 1 .
     * @param parte ArrayList con la parte menos significativa del número. 
     * @param num ArrayList con el número que se va a dividir.
   */
    public static void digitos_menos_sinificativos(ArrayList<Integer> parte, ArrayList<Integer> num){           
       for(int i=0;i<num.size()/2;i++){
           parte.add(num.get(i));
       }
   }
    
   
   /**
     * Copia en el ArrayList 'parte' los digitos de la parte mas significativa del ArrayList 'num', es decir
     * desde  la mitad del tamaño que tenga 'num' hasta la última posición.
     * @param parte ArrayList con la parte mas significativa del número. 
     * @param num ArrayList con el número que se va a dividir.
   */
   public static void digitos_mas_sinificativos(ArrayList<Integer> parte, ArrayList<Integer> num){         
       for(int i=num.size()/2;i<num.size();i++){
           parte.add(num.get(i));
       }
   }
   
   /**
    * Muestra por pantalla los digitos de un número de izquierda a derecha.
    * @param vector_enteros ArrayList que se va a mostrar.
   */
   public static void mostrar_numero_de_izq_dcha(ArrayList<Integer> vector_enteros){
        for(int i=0;i<vector_enteros.size();i++)
            System.out.print(" "+vector_enteros.get(i));
   } 
   

   /**
    * Lee de un fichero los números primos que serán usados en las operaciones correspondientes.
    * @param num1 ArrayList usado en los cálculos.
    * @param num1 ArrayList usado en los cálculos.
    * @return vector_primos ArrayList con los primos obtenidos,
    */
   public static ArrayList<Integer> guarda_vector_numeros_primos(ArrayList<Integer> num1, ArrayList<Integer> num2){
       ArrayList<Integer> vector_primos_aux=new ArrayList<>();
       
      FileReader entrada=null;
      StringBuffer str=new StringBuffer();

      try  {
            entrada=new FileReader("primos.txt");
            int c;
            while((c=entrada.read())!=-1){
                str.append((char)c);                
            }
       }catch (IOException ex) {
            System.out.println("\nNo se ha encontrado el archivo de texto que contiene los datos del problema.");
       }
       
       for(int i=str.length()-5;i>=0;i=i-7){       
           String num_primo=new String();
           num_primo=str.substring(i, i+5);
          
           vector_primos_aux.add(Integer.parseInt(num_primo));
       }
       
       int tam_vector_primos=0;
       tam_vector_primos=new Double (16*(num1.size()+num2.size())/15).intValue()+1;
       ArrayList<Integer> vector_primos=new ArrayList<>();       
       for(int i=0;i<tam_vector_primos;i++){
           vector_primos.add(vector_primos_aux.get(i));
       }
       
       return vector_primos;
   }
   
   
   /**
    * Pasa cada uno de los números de 'num' a su correspondiente valor haciendo módulo con un número primo el cual hace de base.
    * @param num ArrayList con los números con los cuales se va a hacer módulos con los correspondientes números primos.
    * @param primos ArrayList con los números que se van a usar como bases.
    * @return num_modular ArrayList 
    */
   public static ArrayList<Integer> cambio_base_modular(ArrayList<Integer> num, ArrayList<Integer> primos){
        ArrayList<Integer> num_modular=new ArrayList<>();
        
        for(int i=0;i<primos.size();i++){          
            long aux=0;
            double dig=0;
            for(int j=num.size()-1;j>=0;j--){
                double acarreo=0;
                acarreo=dig;                
                //aux=( getUnsignedInt( (int) getUnsignedInt(num.get(j)* (int) Math.pow(65536,j)) + (int) aux))%primos.get(i);         
                dig=Math.pow(65536,j)%primos.get(i);   
                dig= getUnsignedInt(num.get(j)* (int) dig)%primos.get(i);
                dig=(dig+acarreo)%primos.get(i);
            }
            num_modular.add((int) dig);
        }
        
        return num_modular;
   }
   
   
   /**
     * Multiplica los dos números que se pasan como parámetro haciendo modulo con su correspondiente número primo.
     * @param num1 ArrayList con el primer número que se va a multiplicar .
     * @param num2 ArrayList con el segundo número que se va a multiplicar.
     * @param primos ArrayList con los números primos los cuales de van a usar como bases para hacer el módulo.
     * @return result ArrayList con el resultado de multiplicar  los dos número en base 2^16 módulo con su correspondiente 
     * número primo.
   */
   public static ArrayList<Integer> multiplicacion_modular(ArrayList<Integer> num1, ArrayList<Integer> num2, ArrayList<Integer> primos){
        ArrayList<Integer> result=new ArrayList<>();
        
        for(int i=0;i<primos.size();i++){
            long aux;
            aux=(getUnsignedInt(num1.get(i)* num2.get(i)))%primos.get(i);
            
            result.add((int) aux);
        }        
        
        return result;
   }
   
   
   /**
    * Calcula una matriz con los Cij´s correspondientes a los primos relativos que se pasan como parámetro.
    * @param primos_relativos ArrayList con los números primos que se van a usar en los cálculos.
    * @returnc c <ArrayList<Integer>> o sea una matriz con los correspondientes Cij´s calculados. 
    */
   public static ArrayList<ArrayList<Integer>> calcular_c_mult_modular( ArrayList<Integer> primos_relativos){
       ArrayList<ArrayList<Integer>> c=new ArrayList<>();
       
       for(int i=0;i<=primos_relativos.size()-2;i++){
           c.add(new ArrayList<Integer>());
           for(int k=0;k<=i;k++)
                c.get(i).add(0);
           for(int j=i+1;j<=primos_relativos.size()-1;j++){
               //busco cij :  cij*mi%mj=1
               boolean valido=false;
               for(int k=0;k<=primos_relativos.get(j) && valido==false;k++){
                   if((getUnsignedInt(k*primos_relativos.get(i))%primos_relativos.get(j))==1){
                       valido=true;
                       c.get(i).add(k);
                   }
               }
           }
       }
       return c;
   }
   
   
   
   /**
    * Calcula mediante el algortimo extendido de Euclides los inversos de los primos relativos que se le pasan por parámetro.
    * @param primos_relativos ArrayList con los números a los cuales se les va a calcular su inverso.
    * @return c <ArrayList<Integer>> o sea una matriz con los correspondientes inversos calculados.
    */
   public static ArrayList<ArrayList<Integer>> alg_euclides( ArrayList<Integer> primos_relativos){
       ArrayList<ArrayList<Integer>> c=new ArrayList<>();
       
       for(int i=0;i<=primos_relativos.size()-2;i++){
           c.add(new ArrayList<Integer>());
           for(int k=0;k<=i;k++)
                c.get(i).add(0);
           for(int j=i+1;j<primos_relativos.size();j++){
               ArrayList<Integer> a=new ArrayList<>();
               ArrayList<Integer> b=new ArrayList<>();
               ArrayList<Integer> q=new ArrayList<>();
               ArrayList<Integer> r=new ArrayList<>();
               ArrayList<Integer> u=new ArrayList<>();

               for(int k=0;k<2;k++){
                   a.add(0);
                   b.add(0);
                   q.add(0);
                   r.add(0);
               }
               u.add(1);
               u.add(0);
               a.add(primos_relativos.get(i));
               b.add(primos_relativos.get(j));
               boolean encontrado=false;
               for(int k=2;k<primos_relativos.get(j) && encontrado==false;k++){
                   int aux;
                   aux=new Double (a.get(k)/b.get(k)).intValue();
                   q.add( aux );
                   r.add( a.get(k)-b.get(k)*q.get(k) );
                   u.add( u.get(k-2)-q.get(k)*u.get(k-1));
                   if(r.get(k)==1){
                       encontrado=true;
                       if(u.get(k)<0){
                           u.set(k, u.get(k)+b.get(2));
                       }
                       c.get(i).add(u.get(k));
                   }
                   a.add(b.get(k));
                   b.add(r.get(k));
               }
           }
       }
       return c;
   } 
   
   
   /**
    * Muestra el número que se le pasa por parámetro en base decimal.
    * @param vector_enteros ArrayList con el número que se va a mostrar. 
    */
   public static void mostrar_numero_base_dec(ArrayList<Integer> vector_enteros){
        for(int i=0;i<vector_enteros.size();i++){
            System.out.print("+"+vector_enteros.get(vector_enteros.size()-1-i));
            for(int j=i;j<vector_enteros.size()-1;j++){
                System.out.print("*65536");
            }
        }
   }
   
   
   /**
    * Pasa un número de su base a la base normal que se está usando para las operaciones. 
    * @param x ArrayList con los Xi´s que se van a usar en las operaciones.
    * @param cij ArrayList<ArrayList<Integer>> o sea matriz con los Cij´s que se van a usar el las operaciones.
    * @param m ArrayList con los Mi´s que se van a usar en las operaciones.
    * @return solucion ArrayList con el resultado de pasar a base normal modular.
    */
   public static ArrayList<Integer> pasar_base_mod_a_normal(ArrayList<Integer> x, ArrayList<ArrayList<Integer>> cij, ArrayList<Integer> m){
       ArrayList<Integer> y=new ArrayList<>();
       
       long aux=0;
       
       aux=x.get(0)%m.get(0);
       y.add((int) aux);  
       for(int i=1;i<m.size();i++){       
           aux=x.get(i);                
           for(int j=0;j<i;j++){
               boolean resta_neg=false;
               if(aux<y.get(j)){
                   resta_neg=true;
               }
               if(resta_neg==true){
                    aux=getUnsignedInt( (int) ((aux-y.get(j))*cij.get(j).get(i))*(-1));
                   aux=aux%m.get(i);
                   aux=aux*(-1);
                   aux=aux+m.get(i);
               }else{
                   aux=getUnsignedInt( (int) (aux-y.get(j))*cij.get(j).get(i));
                   aux=aux%m.get(i);
               }
           }         
           y.add((int) aux);
       }       
       
       ArrayList<Integer> solucion=new ArrayList<>();
       solucion.add(y.get(0));
       for(int i=1;i<m.size();i++){
           ArrayList<Integer> mult_aux=new ArrayList<>();
           ArrayList<Integer> aux1=new ArrayList<>();
           ArrayList<Integer> aux2=new ArrayList<>();
           aux1.add(y.get(i));
           aux2.add(m.get(0));
           mult_aux=mult_escuela(aux1, aux2);
           for(int j=1;j<i;j++){
               ArrayList<Integer> aux3=new ArrayList<>();
               aux3.add(m.get(j));
               mult_aux=mult_escuela(mult_aux, aux3);
           }
           solucion=suma_escuela(solucion,mult_aux);
       }
       
       return solucion;
   }
   
   /**
    * Lee un número desde la entrada estándar
    * @return num número que se ha leido.
    */
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
    
    
    public static void main(String[] args) {
        double tiempo=0;
        for(int k=0;k<30;k++){
            int num_dig=1;
            int num_dig2=1;
            
            ArrayList<Integer> vector_enteros=new ArrayList<>();
            ArrayList<Integer> vector_enteros2=new ArrayList<>();
            ArrayList<Character> numChar=new ArrayList<>();
            ArrayList<Character> numChar2=new ArrayList<>();
                for(int i=0;i<num_dig;i++){
                    int dig= new Double(Math.random()*16).intValue();
                    char dig_char='x';
                    if(dig<10){
                        dig_char= (new Integer (dig).toString()).charAt(0);
                    }else if(dig==10){
                        dig_char='a';
                    }else if(dig==11){
                        dig_char='b';
                    }else if(dig==12){
                        dig_char='c';
                    }else if(dig==13){
                        dig_char='d';
                    }else if(dig==14){
                        dig_char='e';
                    }else if(dig==15){
                        dig_char='f';
                    }
                    numChar.add(dig_char);
                }
                for(int i=0;i<num_dig2;i++){
                    int dig= new Double(Math.random()*16).intValue();
                    char dig_char='x';
                    if(dig<10){
                        dig_char= (new Integer (dig).toString()).charAt(0);
                    }else if(dig==10){
                        dig_char='a';
                    }else if(dig==11){
                        dig_char='b';
                    }else if(dig==12){
                        dig_char='c';
                    }else if(dig==13){
                        dig_char='d';
                    }else if(dig==14){
                        dig_char='e';
                    }else if(dig==15){
                        dig_char='f';
                    }
                    numChar2.add(dig_char);
                }    
                vector_enteros=cambiaBase(numChar);
                vector_enteros2=cambiaBase(numChar2);
                
                ArrayList<Integer> result_mult1=new ArrayList<Integer>();                
                double time1=System.currentTimeMillis();
                for(int p=0;p<10000;p++)
                    result_mult1=mult_escuela(vector_enteros, vector_enteros2);
                double time2=System.currentTimeMillis();
                System.out.print("\nResultado de multiplicar los dos numeros ESCUELA:   ");        
                mostrar_numero(result_mult1);  
                tiempo=tiempo+((time2-time1)/10000);
                System.out.print("\nTiempo empleado en la multiplicacion escuela:   "+tiempo+" ms");
        }

        /*
        int salir=1;
        while(salir==1){
            ArrayList<Integer> vector_enteros=new ArrayList<>();
            ArrayList<Integer> vector_enteros2=new ArrayList<>();
            ArrayList<Character> numChar=new ArrayList<>();
            ArrayList<Character> numChar2=new ArrayList<>();
            
            int intro_datos=0;         
            while(intro_datos!=1 && intro_datos!=2){
                System.out.print("\nDesea introducir los numeros:  \n    1. A mano \n    2.Aleatoriamente\n");
                intro_datos=leer_numero();                
                if(intro_datos!=1 && intro_datos!=2){
                    System.out.print("\nError: diga un numero entre 1 y 2.");
                }
            }
            if(intro_datos==1){
                System.out.print("\nINTRODUCE UN NUMERO\n");
                numChar=leer_numero_hexadecimal();
                System.out.print("\nINTRODUCE OTRO NUMERO\n");
                numChar2=leer_numero_hexadecimal();            
            }else if(intro_datos==2){
                System.out.print("\nCuantos digitos quiere que tenga el primer numero:\n");
                int num_dig=leer_numero();        
                System.out.print("\nCuantos digitos quiere que tenga el segundo numero:\n");
                int num_dig2=leer_numero(); 
                for(int i=0;i<num_dig;i++){
                    int dig= new Double(Math.random()*16).intValue();
                    char dig_char='x';
                    if(dig<10){
                        dig_char= (new Integer (dig).toString()).charAt(0);
                    }else if(dig==10){
                        dig_char='a';
                    }else if(dig==11){
                        dig_char='b';
                    }else if(dig==12){
                        dig_char='c';
                    }else if(dig==13){
                        dig_char='d';
                    }else if(dig==14){
                        dig_char='e';
                    }else if(dig==15){
                        dig_char='f';
                    }
                    numChar.add(dig_char);
                }
                for(int i=0;i<num_dig2;i++){
                    int dig= new Double(Math.random()*16).intValue();
                    char dig_char='x';
                    if(dig<10){
                        dig_char= (new Integer (dig).toString()).charAt(0);
                    }else if(dig==10){
                        dig_char='a';
                    }else if(dig==11){
                        dig_char='b';
                    }else if(dig==12){
                        dig_char='c';
                    }else if(dig==13){
                        dig_char='d';
                    }else if(dig==14){
                        dig_char='e';
                    }else if(dig==15){
                        dig_char='f';
                    }
                    numChar2.add(dig_char);
                }               
                System.out.print("\nPrimer numero generado aleatoriamente: "+numChar.size());
                for(int i=numChar.size()-1;i>=0;i--)
                    System.out.print(numChar.get(i));
                System.out.print("\nSegundo numero generado aleatoriamente: ");
                for(int i=numChar2.size()-1;i>=0;i--)
                    System.out.print(numChar2.get(i));
            }
            vector_enteros=cambiaBase(numChar);
            vector_enteros2=cambiaBase(numChar2);
            System.out.print("\nNumero1 base (2^16):   ");      
            mostrar_numero(vector_enteros);
            System.out.print("\nNumero2 base (2^16):   ");      
            mostrar_numero(vector_enteros2);
            int mult_usada=0;
            while(mult_usada!=1 && mult_usada!=2 && mult_usada!=3 && mult_usada!=4){
                System.out.print("\nDiga que multiplicacion quiere usar: \n   1.Escuela\n   2.Karatsuba\n   3.Modular\n   4.Las tres anteriores para comparar los tiempos\n");
                mult_usada=leer_numero();      
                if(mult_usada!=1 && mult_usada!=2 && mult_usada!=3 && mult_usada!=4){
                    System.out.print("\nError: diga un numero entre 1 y 4.");
                }
            }
            if(mult_usada==1){
                ArrayList<Integer> result_mult1=new ArrayList<Integer>();                
                long time1=System.currentTimeMillis();
                result_mult1=mult_escuela(vector_enteros, vector_enteros2);
                long time2=System.currentTimeMillis();
                System.out.print("\nResultado de multiplicar los dos numeros ESCUELA:   ");        
                mostrar_numero(result_mult1);  
                long tiempo=time2-time1;
                System.out.print("\nTiempo empleado en la multiplicacion escuela:   "+tiempo+" ms");
            }else if(mult_usada==2){
                ArrayList<Integer> result_mult2=new ArrayList<Integer>();
                long time1=System.currentTimeMillis();
                result_mult2=calcula_m_karatsuba(vector_enteros, vector_enteros2);
                long time2=System.currentTimeMillis();
                System.out.print("\nResultado de multiplicar los dos numeros KARATSUBA: ");
                mostrar_numero(result_mult2);
                long tiempo=time2-time1;
                System.out.print("\nTiempo empleado en la multiplicacion Karatsuba:   "+tiempo+" ms");
            }else if(mult_usada==3){
                ArrayList<Integer> primos_relativos=new ArrayList<Integer>();
                primos_relativos=guarda_vector_numeros_primos(vector_enteros, vector_enteros2);

                ArrayList<Integer> num1_base_mod=new ArrayList<Integer>();
                ArrayList<Integer> num2_base_mod=new ArrayList<Integer>();
                ArrayList<Integer> result_modular=new ArrayList<Integer>();
                long time1=System.currentTimeMillis();
                num1_base_mod=cambio_base_modular(vector_enteros, primos_relativos);                
                num2_base_mod=cambio_base_modular(vector_enteros2, primos_relativos);
                result_modular=multiplicacion_modular(num1_base_mod, num2_base_mod, primos_relativos);                
                long time2=System.currentTimeMillis();
                long tiempo=time2-time1;

                ArrayList<ArrayList<Integer>> cij=new ArrayList<ArrayList<Integer>>();
                cij=alg_euclides(primos_relativos);
                ArrayList<Integer> result_mod=new ArrayList<Integer>();
                time1=System.currentTimeMillis();
                result_mod=pasar_base_mod_a_normal(result_modular, cij, primos_relativos);
                time2=System.currentTimeMillis();
                tiempo=tiempo+time2-time1;
                System.out.print("\nResultado de la multiplicacion modular   ");        
                mostrar_numero(result_mod);
                System.out.print("\nTiempo empleado en la multiplicacion modular:   "+tiempo+" ms");
            }else if(mult_usada==4){
                ArrayList<Integer> result_mult1=new ArrayList<Integer>();                
                long time1=System.currentTimeMillis();
                result_mult1=mult_escuela(vector_enteros, vector_enteros2);
                long time2=System.currentTimeMillis();
                System.out.print("\nResultado de multiplicar los dos numeros ESCUELA:   ");        
                mostrar_numero(result_mult1);  
                long tiempo=time2-time1;
                System.out.print("\nTiempo empleado en la multiplicacion escuela:   "+tiempo+" ms");
                
                ArrayList<Integer> result_mult2=new ArrayList<Integer>();
                time1=System.currentTimeMillis();
                result_mult2=calcula_m_karatsuba(vector_enteros, vector_enteros2);
                time2=System.currentTimeMillis();
                System.out.print("\nResultado de multiplicar los dos numeros KARATSUBA: ");
                mostrar_numero(result_mult2);
                tiempo=time2-time1;
                System.out.print("\nTiempo empleado en la multiplicacion Karatsuba:   "+tiempo+" ms");
                elimina_ceros_izq(vector_enteros);
                elimina_ceros_izq(vector_enteros2);
                ArrayList<Integer> primos_relativos=new ArrayList<Integer>();
                primos_relativos=guarda_vector_numeros_primos(vector_enteros, vector_enteros2);

                ArrayList<Integer> num1_base_mod=new ArrayList<Integer>();
                ArrayList<Integer> num2_base_mod=new ArrayList<Integer>();
                ArrayList<Integer> result_modular=new ArrayList<Integer>();
                time1=System.currentTimeMillis();
                num1_base_mod=cambio_base_modular(vector_enteros, primos_relativos);                
                num2_base_mod=cambio_base_modular(vector_enteros2, primos_relativos);
                result_modular=multiplicacion_modular(num1_base_mod, num2_base_mod, primos_relativos);         
                time2=System.currentTimeMillis();
                tiempo=time2-time1;

                ArrayList<ArrayList<Integer>> cij=new ArrayList<ArrayList<Integer>>();
                cij=alg_euclides(primos_relativos);
                ArrayList<Integer> result_mod=new ArrayList<Integer>();
                time1=System.currentTimeMillis();
                result_mod=pasar_base_mod_a_normal(result_modular, cij, primos_relativos);
                time2=System.currentTimeMillis();
                tiempo=tiempo+time2-time1;
                System.out.print("\nResultado de la multiplicacion modular   ");        
                mostrar_numero(result_mod);
                System.out.print("\nTiempo empleado en la multiplicacion modular:   "+tiempo+" ms");
            }
            salir=0;
            while(salir!=1 && salir!=2){
                System.out.print("\n¿Desea hacer mas multiplicaciones?\n   1. Si\n   2. No\n");
                salir=leer_numero();      
                if(salir!=1 && salir!=2){
                    System.out.print("\nError: diga un numero entre 1 y 2.");
                }
            }            
        }
 */
         
   } 
}
 

