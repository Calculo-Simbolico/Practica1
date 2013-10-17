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
                               System.out.print("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!resta_escuela num1");
                mostrar_numero(num1);
                 System.out.print("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!resta_escuela num2");
                mostrar_numero(num2);
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
                       System.out.print("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!resta_escuela num1");
                mostrar_numero(num1);
                 System.out.print("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!resta_escuela num2");
                mostrar_numero(num2);
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
                        System.out.print("\nEMPIEZA m="+m);
                        System.out.print("\n a");
                        mostrar_numero(a); 
                        System.out.print("\n b");
                        mostrar_numero(b);   
        long temporal;
        if(m==0){
            temporal=getUnsignedInt(a.get(0)*b.get(0));
            result.add((int) (temporal%(int) Math.pow( 2, 16)));
            acarreo=new Double (temporal/Math.pow( 2, 16)).intValue();
            result.add( acarreo);
            
                System.out.print("\n    m=0 result =");
                mostrar_numero(result);
            
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
            if(signo1==false){
                System.out.print("\n resta!!!!!!! negativa1 =");
                mostrar_numero(a1);
                System.out.print(" - ");
                mostrar_numero(a0);     
            }
            if(signo2==false){
                System.out.print("\n resta!!!!!!! negativa2 =");
                mostrar_numero(b0);
                System.out.print(" - ");
                mostrar_numero(b1);     
            } 
            t2 = mult_karatsuba(resta1, resta2, m-1);
            t3 = mult_karatsuba(a0, b0, m-1);          
           
            ArrayList<Integer> aux=new ArrayList<>();
            acarreo=0;
            temporal=0;
            if(m==3){
                System.out.print("\n signo1="+signo1+" signo2="+signo2);
            }
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
            
                System.out.print("\n                                                                                t1+t2+t3= ");
                mostrar_numero(t1);
                                System.out.print(" + ");
                mostrar_numero(t2);
                                                System.out.print(" + ");
                mostrar_numero(t3);
                                System.out.print("\n                                                                                                           ==");
                mostrar_numero(aux);
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

                System.out.print("\n t1");
                mostrar_numero(t1);
                                System.out.print("\n aux");
                mostrar_numero(aux);
                                System.out.print("\n t3");
                mostrar_numero(t3);
            for(int i=0;i<t1.size();i++){
                temporal=t1.get(i)+aux.get(i)+t3.get(i)+acarreo;
                int dig;
                dig=(int) temporal% (int) Math.pow( 2, 16);
                aux2.add(dig);
                acarreo= new Double (temporal/Math.pow( 2, 16)).intValue();
            }
            aux2.add( acarreo);

                        System.out.print("\n                                                            m="+m+"result=");
                        mostrar_numero(aux2); 
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
   
   
   public static void main(String[] args) {
        ArrayList<Integer> vector_enteros=new ArrayList<>();
        ArrayList<Integer> vector_enteros2=new ArrayList<>();
        ArrayList<Character> numChar=new ArrayList<>();
        ArrayList<Character> numChar2=new ArrayList<>();
        
        System.out.print("\nINTRODUCE UN NUMERO\n");
        //numChar=leer_numero_hexadecimal();
        System.out.print("\nINTRODUCE OTRO NUMERO\n");
        //numChar2=leer_numero_hexadecimal();
        for(int i=0;i<30;i++)
            numChar.add('F');
        for(int i=0;i<30;i++)
            numChar2.add('F');
        vector_enteros=cambiaBase(numChar);
        vector_enteros2=cambiaBase(numChar2);
        
        mostrar_numero(vector_enteros);
        mostrar_numero(vector_enteros2);
        

        
        ArrayList<Integer> result_mult2=new ArrayList<Integer>();
        ArrayList<Integer> result=new ArrayList<>();
        result_mult2=calcula_m_karatsuba(vector_enteros, vector_enteros2);
        System.out.print("\nResultado de multiplicar los dos numeros KARATSUBA: ");
        mostrar_numero(result_mult2);
        
        ArrayList<Integer> result_mult1=new ArrayList<Integer>();
        result_mult1=mult_escuela(vector_enteros, vector_enteros2);
        System.out.print("\nResultado de multiplicar los dos numeros ESCUELA: ");        
        mostrar_numero(result_mult1);          
   }
   
   
   
   
    /*
    public static void main(String[] args) {
        ArrayList<Integer> vector_enteros=new ArrayList<Integer>();
        ArrayList<Integer> vector_enteros2=new ArrayList<Integer>();
        ArrayList<Character> numChar=new ArrayList<Character>();
        ArrayList<Character> numChar2=new ArrayList<Character>();
        

        System.out.print("\nINTRODUCE UN NUMERO\n");
        numChar=leer_numero_hexadecimal();
        System.out.print("\nINTRODUCE OTRO NUMERO\n");
        numChar2=leer_numero_hexadecimal();

        vector_enteros=cambiaBase(numChar);
        vector_enteros2=cambiaBase(numChar2);
        
        mostrar_numero(vector_enteros);
        mostrar_numero(vector_enteros2);
        
        ArrayList<Integer> result_mult=new ArrayList<Integer>();
        result_mult=mult_escuela(vector_enteros, vector_enteros2);
        System.out.print("\nResultado de multiplicar los dos numeros:");        
        mostrar_numero(result_mult);  
        
      
        ArrayList<Integer> result_suma=new ArrayList<Integer>();
        result_suma=suma_escuela(vector_enteros, vector_enteros2);
        System.out.print("\nResultado de sumar los dos numeros:");        
        mostrar_numero(result_suma);
         
      
       
        ArrayList<Integer> result_resta=new ArrayList<Integer>();
        result_resta=resta_escuela(vector_enteros, vector_enteros2);
        System.out.print("\nResultado de restar los dos numeros:");        
        mostrar_numero(result_resta);  
       */

    
}
