package Compartit;
/**
 * @author Enric F D
 */

public class Arbol_completo extends Algoritmo_clustering {
    
    //pre: vector valid i el seu tamany > 0
    //post: retorna un vector inicialitzat amb totes les posicions a -1
    
  private void inicialitzar(HashMap Path, int tam)     //tam == Mat_conj_resp.size()*2
    {
        for(int i = 0; i < tam; i++)
        {    
            if(i >= tam/2) Path.put(i,-2);
            else  Path.put(i,-1);
        }
    }
    
  protected  void genera_arbre_complet(double[][] Mat_conj_resp, HashMap Path)
    {
      int num_res = Mat_conj_resp.size(); 
      int cont_clusters = 0;
      
      inicialitzar(Path, (num_res*2));
      
      int cl1 = -2;
      int cl2 = -2;    
      double[][] Mat_distancies = new double[num_res*2][num_res]; //cada linia de mat es un cluster
      
      while (cl1!=-1 && cl2!=-1) {
        
        calcul_distancies(Mat_conj_resp, Mat_distancies, Path,  cl1,  cl2); //es tenen en conte els elements que tenen valor -1 del hashmap
 
        Path.put(cl1, num_res+cont_clusters);
        Path.put(cl2, num_res+cont_clusters);
        Path.put(num_res+cont_clusters, -1);
        
        cont_clusters++;
        cl1 = -1;
        cl2 = -1;  
      }      
    }
    
 protected  void clusters_x_nivel(int nivell, double[][] Mat_conj_resp, HashMap Path)  {
    
     Stack<Int> s = new Stack();
     s[] clusters = new s[Mat_conj_resp.size()]
     int nivell_aux = nivell;
     int i = 0;
     int valor, valor_inicial;
     valor_inicial = i;
         
  while(i < (Mat_conj_resp.size()*2){
  
     while(nivell_aux > 0){  
     
        valor = Path.get(i);        
        If(valor > -1){
          nivell_aux--; 
          i = valor;
        }
        Else{
           valor_inicial++;
           i = valor_inicial;
        }
        
        If(valor <= -1 && nivell_aux == 0){
            clusters[i].push(valor_inicial);
           valor_inicial++;
           i = valor_inicial;
           nivell_aux = nivell;
        }
        
        Else If(valor <= -1 && nivell_aux != 0){
          nivell_aux = nivell; 
          i = valor_inicial;
        }
     } 
   }
   
   
}
