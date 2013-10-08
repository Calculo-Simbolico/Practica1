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
        System.out.print("\nNumero: ");
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
                    System.out.print("\nacarreo \n"+(temporal/Math.pow( 2, 16)));
            acarreo= new Double (temporal/Math.pow( 2, 16)).intValue();
                                System.out.print("\nacarreo \n"+acarreo);
        }
        return result;
    }   
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
        
        ArrayList<Integer> result_suma=new ArrayList<Integer>();
        result_suma=suma_escuela(vector_enteros, vector_enteros2);
        mostrar_numero(result_suma);
    }
}
