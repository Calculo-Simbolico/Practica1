/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculosimbolico;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.lang.Math;
/**
 *
 * @author Francisco
 */
public class CalculoSimbolico {

    
    public static ArrayList<Integer> cambiaBase(ArrayList<Character> num){
        int digito=0;
        ArrayList<Integer> numeroInt=new ArrayList<Integer>();
        for(int i=0;i<num.size();i=i+4){
            digito=0;
            for(int j=0;j<4 && i+j<num.size();j++){
                int aux;
                
                if( num.get(i+j) == 'A'){
                    aux=10;
                }else if( num.get(i+j) == 'B'){
                    aux=11;
                }else if( num.get(i+j) == 'C'){
                    aux=12;
                }else if( num.get(i+j) == 'D'){
                    aux=13;
                }else if( num.get(i+j) == 'E'){
                    aux=14;
                }else if( num.get(i+j) == 'F'){
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
   
   
   public static void mostrar_numero(ArrayList<Integer> vector_enteros){
        for(int i=0;i<vector_enteros.size();i++)
            System.out.print(" "+vector_enteros.get(vector_enteros.size()-1-i));
   }
   
   
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
   
   
   public static ArrayList<Integer> elimina_ceros_izq(ArrayList<Integer> num1){
       int i=num1.size()-1;
       while(num1.get(i)==0 && num1.size()!=1){
           num1.remove(i);
           i--;
       }
       return num1;
   }
   
   
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
        
        return result;
    }
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
    
    
    
    public static void digitos_menos_sinificativos(ArrayList<Integer> parte, ArrayList<Integer> num){           
       for(int i=0;i<num.size()/2;i++){
           parte.add(num.get(i));
       }
   }
    
    
   public static void digitos_mas_sinificativos(ArrayList<Integer> parte, ArrayList<Integer> num){         
       for(int i=num.size()/2;i<num.size();i++){
           parte.add(num.get(i));
       }
   }
   
   
   public static ArrayList<Integer> hacer_modulo(ArrayList<Integer> num){
       ArrayList<Integer> result=new ArrayList<>();
       Integer temporal, acarreo=0, digito;
       
       for(int i=0;i<num.size();i++){
            temporal=num.get(i) + acarreo;
            digito =  temporal % (int) Math.pow( 2, 16);
            acarreo= new Double (temporal/Math.pow( 2, 16)).intValue();
            result.add(digito);
       }
       return result;
       
   }
   
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
       tam_vector_primos=num1.size()+num2.size();
       ArrayList<Integer> vector_primos=new ArrayList<>();       
       for(int i=0;i<tam_vector_primos;i++){
           vector_primos.add(vector_primos_aux.get(i));
       }
       
       return vector_primos;
   }
   public static ArrayList<Integer> cambio_base_modular(ArrayList<Integer> num, ArrayList<Integer> primos){
        ArrayList<Integer> num_modular=new ArrayList<>();
        
        for(int i=0;i<primos.size();i++){
            
            long aux=0;
            for(int j=num.size()-1;j>=0;j--){
                aux=(getUnsignedInt(num.get(j)*(int) Math.pow(65536,j))+aux)%primos.get(i);
            }
            num_modular.add((int) aux);
        }
        
        return num_modular;
   }
   public static ArrayList<Integer> multiplicacion_modular(ArrayList<Integer> num1, ArrayList<Integer> num2, ArrayList<Integer> primos){
        ArrayList<Integer> result=new ArrayList<>();
        
        for(int i=0;i<primos.size();i++){
            long aux;
            aux=(getUnsignedInt(num1.get(i)* num2.get(i)))%primos.get(i);
            
            result.add((int) aux);
        }        
        
        return result;
   }
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
   public static ArrayList<Integer> pasar_base_mod_a_normal(ArrayList<Integer> x, ArrayList<ArrayList<Integer>> cij, ArrayList<Integer> m){
       ArrayList<Integer> y=new ArrayList<>();
       
       long aux=0;
       
       aux=x.get(0)%m.get(0);
       y.add((int) aux);  
       for(int i=1;i<m.size();i++){
           aux=x.get(i);                           
           for(int j=0;j<i;j++){
               aux=getUnsignedInt( (int) (aux-y.get(j))*cij.get(j).get(i))%m.get(i);
           }
           y.add((int) aux);
       }
       System.out.print("\n---------------------------------------------Salida y:   ");
       mostrar_numero(y);
       
       ArrayList<Integer> dig=new ArrayList<>();
       dig.add(y.get(0));
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
           dig=suma_escuela(dig,mult_aux);
           System.out.print("\nDig="+dig);
       }
       System.out.print("\n         Dig final=  ");
       mostrar_numero(dig);
       
       return dig;
   }
   public static void main(String[] args) {
        ArrayList<Integer> vector_enteros=new ArrayList<>();
        ArrayList<Integer> vector_enteros2=new ArrayList<>();
        ArrayList<Character> numChar=new ArrayList<>();
        ArrayList<Character> numChar2=new ArrayList<>();
        
        System.out.print("\nINTRODUCE UN NUMERO\n");
        //numChar=leer_numero_hexadecimal();
        System.out.print("\nINTRODUCE OTRO NUMERO\n");
        //numChar2=leer_numero_hexadecimal();
        
        numChar.add('1');
        numChar.add('0');
        numChar.add('0');
        numChar.add('0');
        numChar.add('0');
        numChar.add('1');
        numChar2.add('1');
        numChar2.add('2');
        numChar2.add('2');
        numChar2.add('2');
        numChar2.add('2');
        /*
        for(int i=0;i<8;i++)
            numChar.add('F');
        for(int i=0;i<14;i++)
            numChar2.add('F');*/
        vector_enteros=cambiaBase(numChar);
        vector_enteros2=cambiaBase(numChar2);
        
        System.out.print("\nNum1:   ");      
        mostrar_numero(vector_enteros);
        System.out.print("\nNum2:   ");      
        mostrar_numero(vector_enteros2);
        
                ArrayList<Integer> primos_relativos=new ArrayList<Integer>();
        primos_relativos=guarda_vector_numeros_primos(vector_enteros, vector_enteros2);
        System.out.print("\nPrimos:   ");        
        mostrar_numero(primos_relativos);       

                 ArrayList<Integer> num1_base_mod=new ArrayList<Integer>();
        num1_base_mod=cambio_base_modular(vector_enteros, primos_relativos);
        System.out.print("\num1_base_mod:   ");        
        mostrar_numero(num1_base_mod); 
                 ArrayList<Integer> num2_base_mod=new ArrayList<Integer>();
        num2_base_mod=cambio_base_modular(vector_enteros2, primos_relativos);
        System.out.print("\num2_base_mod:   ");        
        mostrar_numero(num2_base_mod); 
        
                  ArrayList<Integer> result_modular=new ArrayList<Integer>();
        result_modular=multiplicacion_modular(num1_base_mod, num2_base_mod, primos_relativos);
        System.out.print("\nresult_modular:   ");        
        mostrar_numero(result_modular);        
        
                          ArrayList<ArrayList<Integer>> cij=new ArrayList<ArrayList<Integer>>();
        cij=calcular_c_mult_modular(primos_relativos);
        System.out.print("\ncij:   ");       
        for(int i=0;i<cij.size();i++){
            System.out.print("\n "); 
                   mostrar_numero(cij.get(i));   
        }

        
                           ArrayList<Integer> result_mod=new ArrayList<Integer>();
        result_mod=pasar_base_mod_a_normal(result_modular, cij, primos_relativos);
        System.out.print("\nresult_modular:                                     ");  
        mostrar_numero(result_mod);
        
                ArrayList<Integer> result_mult1=new ArrayList<Integer>();
        result_mult1=mult_escuela(vector_enteros, vector_enteros2);
                elimina_ceros_izq(result_mult1);
        System.out.print("\nResultado de multiplicar los dos numeros ESCUELA:   ");        
        mostrar_numero(result_mult1);  
   /*   
        ArrayList<Integer> result_mult2=new ArrayList<Integer>();
        ArrayList<Integer> result=new ArrayList<>();
        result_mult2=calcula_m_karatsuba(vector_enteros, vector_enteros2);
        System.out.print("\nResultado de multiplicar los dos numeros KARATSUBA: ");
                elimina_ceros_izq(result_mult2);
        mostrar_numero(result_mult2);
        
 

        boolean iguales=true;
        for(int i=0;i<result_mult2.size();i++){
            if(result_mult2.get(i).intValue()!=result_mult1.get(i).intValue()){
                iguales=false;
            }
        }
        if(result_mult1.size()!=result_mult2.size())
            iguales=false;
        
        if(iguales==false){
            System.out.print("\nMalllllllllllll-----------------");
        }else
            System.out.print("\nBien !!!!!!!");
         * */
         
   }
        
   
   
}