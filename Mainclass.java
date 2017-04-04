package Classes; */condensat de rutes includes propis i de llibreries utilitzades*/
import java.util.Scanner;
/**
 * @author Enric
 */
public class Mainclass 
{   
    public static void menu() 
    {
        System.out.println("Escollir opció:");
        System.out.println("\t 01) Crear Enquesta");
        System.out.println("\t 02) Modificar Enquesta");
        System.out.println("\t 03) Eliminar Enquesta");
        System.out.println("\t 04) Consultar Enquesta");
        System.out.println("\t 05) Guardar Enquesta");
        System.out.println("\t 06) Llistat Enquesta");      
    }
    
    public static void main(String[] args) 
    {
        System.out.println("Opciones disponibles para Enquestas");
        Scanner scan = new Scanner(System.in);
        boolean out = false;
        menu();   
        while(!out){
            try{
                String op = scan.nextLine();              
                switch (op) {                               
                   case "01":             
                        System.out.println("Crear Enquesta(String nom, "resta atributs")");
                        //creadora
                        System.out.println("Enquesta creada!");
                        break;
                        
                   case "02":             
                        System.out.println("Modifica Enquesta: introduce la"ID Pimary Key" de la enquesta que quiere modificar ");
                        //Modificacions
                        System.out.println("Enquesta Modificada!");
                        break;
                }
            }
       }
    }   
    
 }
