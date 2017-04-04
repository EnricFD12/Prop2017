package Compartit;
/**
 * @author Enric F D
 */

public class Arbol_completo extends Algoritmo_clustering {
    	
  struct cluster{
  	double[] valors;
	Stack<Int> elementos = new Stack();  
  }	
    
  private void inicialitzar_cluster_vect(cluster[] c_v, double[][] Mat_conj_resp)     //tam == Mat_conj_resp.size()*2
    {
	cluster[] c_v = new cluster[Mat_conj_resp.size()*2];
        for(int i = 0; i < Mat_conj_resp.size(); i++){
		c_v[i].valors = Mat_conj_resp[i*Mat_conj_resp.size()];
		c_v[i].elementos.push(i);
	}
    }
	
  private void inicialitzar_hashmap(HashMap Path, int tam)     //tam == Mat_conj_resp.size()*2
    {
        for(int i = 0; i < tam; i++)
        {    
            if(i >= tam/2) Path.put(i,-2);
            else  Path.put(i,-1);
        }
    }
    
  protected  void genera_arbre_complet(cluster[] c_v, HashMap Path,  double[][] Mat_conj_resp, int num_clusters_def, bool complet, HashMap Path_NC)
    {
      int num_res = c_v.size(); 
      int cont_clusters = 0;

      if( num_clusters_def > 0) inicialitzar_hashmap(Path_NC, (num_res*2));
      inicialitzar_hashmap(Path, (num_res*2));
      
      inicialitzar_cluster_vect(c_v, Mat_conj_resp);   
	    
      int cl1 = -2;     int cl2 = -2;    

      double[][] Mat_distancies = new double[num_res][num_res]; //cada linia de mat es un cluster
      
      while (cl1!=-1 && cl2!=-1)
      {
        cl1 = -1;        cl2 = -1;          

        calcul_distancies(&c_v, &Mat_distancies, &Path,  cl1,  cl2); //es tenen en conte els elements que tenen valor -1 del hashmap
 
        Path.put(cl1, num_res+cont_clusters);
	Path.put(cl2, num_res+cont_clusters); 
	Path.put(num_res+cont_clusters, -1);   
	      
	c_v[num_res+cont_clusters].elementos.add(cl1);
	c_v[num_res+cont_clusters].elementos.add(cl2);
	calcula_centroide(&c_v[num_res+cont_clusters]);        
        
        cont_clusters++;

	If((num_res - cont_clusters) == num_clusters_def)
	{
		Path_NC = Path;
		if(not complet)
		{
		   cl1 = -1;
       		   cl2 = -1;     
		}
	}
      }  	    
   }
    
 protected  void clusters_x_nivel(int nivell, double[][] Mat_conj_resp, HashMap Path)  
 { 
   cluster[] c_v_n = new cluster[Mat_conj_resp.size()*2];
   int nivell_aux = nivell;
   int i = 0;
   int valor, valor_inicial;
   valor_inicial = i;
	 
   while(i < (Mat_conj_resp.size()*2)
   {
     while(nivell_aux > 0)
     {      
        valor = Path.get(i); 	     
        If(valor > -1)
	{
          nivell_aux--; 
          i = valor;
        }
	     
        Else{
	   c_v_n[i].elementos.push(valor_inicial);	
           valor_inicial++;
           i = valor_inicial;
	   nivell_aux = nivell;
         }       		           
     }    
   } 
	     
}
