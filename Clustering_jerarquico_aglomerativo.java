package Compartit;
/**
 * @author Enric F D
 */

public class Arbol_completo extends Algoritmo_clustering {
    	
  struct cluster{
  	double[] valors;
	Stack<Int> elementos = new Stack();  
  }	
    
  private void inicialitzar_cluster_vect(cluster[] c_v, double[][] Mat_conj_resp)  //Inicializamos el vector de clusters assignando cada
    {									//elemento, enquesta respondida a 1 cluster cons sus valores i 1 elemento
	cluster[] c_v = new cluster[Mat_conj_resp.size()*2];	 //en la pila el ordinal de este elemento 
	   c_v.valors = new double[Mat_conj_resp[0].size()]
        for(int i = 0; i < Mat_conj_resp.size(); i++){			// el tamaño del vector es 2 veces el num de elementos de t_conj_resp
		c_v[i].valors = Mat_conj_resp[i*Mat_conj_resp.size()];
		c_v[i].elementos.push(i);
	}
    }
	
  private void inicialitzar_hashmap(HashMap Path, int tam)     //tam == Mat_conj_resp.size()*2
    {
        for(int i = 0; i < tam; i++)				//inicializamos elementos de Path (cardinal, -2)
        {    
            if(i >= tam/2) Path.put(i,-2);
            else  Path.put(i,-1);
        }
    }
    
  protected  void genera_arbre_complet(cluster[] c_v, HashMap Path,  double[][] Mat_conj_resp, int num_clusters_def, bool complet, HashMap Path_NC)
    {
      int num_res = c_v.size(); 
      int cont_clusters = 0;		//contabilizador de los nuevos clusters que se crean, sin contar los de inicio

      if( num_clusters_def > 0) inicialitzar_hashmap(Path_NC, (num_res*2)); //usarem un Path auxiliar en el caso de definir un numero 
	    									//de clusters de salida
      inicialitzar_hashmap(Path, (num_res*2));    
      inicialitzar_cluster_vect(c_v, Mat_conj_resp);   
	    
      int cl1 = -2;     int cl2 = -2;    

      double[][] Mat_distancies = new double[num_res][num_res]; //cada linia/columna de mat es un cluster donde el elemento  Mat[i][j]
      						// es la distancia entre clusters , dist entre cluster "i" i el cluster "j"
      while (cl1!=-1 && cl2!=-1)	//mientras se puedan agrupar clusters do-->			
      {
        cl1 = -1;     cl2 = -1;          

        calcul_distancies(&c_v, &Mat_distancies, &Path,  cl1,  cl2); //es tenen en conte els elements que tenen valor -1 del hashmap
 							//ya sean clusters individuales o agrupaciones, cl1 i cl2 son los ordinales de 
        Path.put(cl1, num_res+cont_clusters);		//los clusters en question que tienen la menor distancia posible.
	Path.put(cl2, num_res+cont_clusters); 		//assignamos la nueva ruta a los clusters que obtenemos de la funcion anterior
	Path.put(num_res+cont_clusters, -1);   		//creando un nuevo cluster operativo, i assignandole -1 para tenerlo en cuenta
	      						//en los posteriores calculos de distancias
	      
	c_v[num_res+cont_clusters].elementos.add(cl1);	//actualizamos la informacion en c_v, del elemento que estamos tratando,
	c_v[num_res+cont_clusters].elementos.add(cl2);   //añadimos los elementos que conforman el cluster a la pila de esta (el cardinal)
	calcula_centroide(&c_v[num_res+cont_clusters]);    //recalculamos  el vector valors aplicando las formulas de centroide, "tipo    
        						//media calculada" segun los elementos del cluster (que tenemos en la pila)
        cont_clusters++;				//inc el numero de clusters operativos que tenemos (no generados x default)

	If((num_res - cont_clusters) == num_clusters_def) //si el numero de clusters (total) coincide con num_clusters_def que representa
	{					//el numero de clusters que queremos,	
		Path_NC = Path;			//Guardamos el Path actual en una variable auxiliar		
		if(not complet)	{		
		   cl1 = -1;			//forzamos la salida del bucle principal
       		   cl2 = -1;     		//podriamos ecoger si terminar el arbol i ademas guardar la inf del nivel que definimos
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
	 
   while(i < (Mat_conj_resp.size()*2)  //mientras queden elementos de Path do wwhile
   {
     while(nivell_aux > 0)		//mientras el cluster que tratamos sea de un nivel del arbol <= que el nivel que se nos da do-->
     {      
        valor = Path.get(i); 	
        				//si valor > -1 significa que hay una ruta, donde el elemento se agrupa en niveles mas altos
	If(valor > -1)	{				//seguimos mientras se cumpla la condicion de no superar el nivel que se nos da
          nivell_aux--; 		//para cada paso en la ruta -- el nivel que nos dan definido,
          i = valor;			//avanzamos en la ruta i volvemos a evaluar el nivel
        }     
        Else{				//si es -1 es una ruta ya cerrada, assignamos el elemento al cluster que cierra ruta
	   c_v_n[i].elementos.push(valor_inicial);	
           valor_inicial++;		//seguimos con el siguiente elemento 
           i = valor_inicial;
	   nivell_aux = nivell;		//reseteamos la variable del nivel
         }       		           
      } 	   
    } 	     
 }
	 
 }
