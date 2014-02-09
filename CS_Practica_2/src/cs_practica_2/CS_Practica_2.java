/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs_practica_2;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Francisco
 */

public class CS_Practica_2 {
    
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
   
   public static void main(String[] args){
       int salir=1;
       while(salir==1){
            int intro_datos=0;
            while(intro_datos!=1 && intro_datos!=2 && intro_datos!=3 && intro_datos!=4){
                System.out.print("\nDesea multiplicar polinomios sobre:  \n    1. Numeros Complejos \n    2. Z_41\n    3. Z_257\n    4. Zp\n");
                //intro_datos=leer_numero();
                intro_datos=3;
                if(intro_datos!=1 && intro_datos!=2 && intro_datos!=3 && intro_datos!=4){
                    System.out.print("\nError: diga un numero entre 1 y 4.");
                }
            }
            if(intro_datos==1){
                op_En_Complejos multiplicacion_complejos=new op_En_Complejos();  
                try{
                    multiplicacion_complejos.main(null);               
                }catch(java.io.IOException e){
                    System.out.print("\nExcepcion ocurrida "+e.getMessage());     
                }
            }else if(intro_datos==2){
                op_En_Z_p multiplicacion_z_p=new op_En_Z_p(41);             
                try{
                    multiplicacion_z_p.main(null);
                }catch(java.io.IOException e){
                    System.out.print("\nExcepcion ocurrida "+e.getMessage());     
                }  
            }else if(intro_datos==3){
                op_En_Z_p multiplicacion_z_p=new op_En_Z_p(257);             
                try{
                    multiplicacion_z_p.main(null);
                }catch(java.io.IOException e){
                    System.out.print("\nExcepcion ocurrida "+e.getMessage());     
                }                
            }else if(intro_datos==4){
                op_En_Z_p multiplicacion_z_p=new op_En_Z_p();             
                try{
                    multiplicacion_z_p.main(null);
                }catch(java.io.IOException e){
                    System.out.print("\nExcepcion ocurrida "+e.getMessage());     
                }
            }
            
           
            salir=0;
            while(salir!=1 && salir!=2){
                System.out.print("\n\n¿Desea hacer mas multiplicaciones de polinomios?\n   1. Si\n   2. No\n");
                salir=2;      
                if(salir!=1 && salir!=2){
                    System.out.print("\nError: diga un numero entre 1 y 2.");
                }
            } 
       }
          
   }
}
