import java.util.*;

public class Graph {
	private int g_N,g_size;
	public Vector<Vector<Double>> array=new Vector<Vector<Double>>();
	public Vector<Vector<Double>> constraint = new Vector<Vector<Double>>();
	public Vector<Vector> array_path=new Vector<Vector>();
	public Vector<Stack> kpaths = new Vector<Stack>();
	public int start;
	public int end;
	public static final double POSITIVE_INFINITY = 1.0 / 0.0;
	
	public Vector<Vector<Double>> GraphInitial (double[][] a){
		Vector<Vector<Double>> array=new Vector<Vector<Double>>() ;
		for(int i=0;i<a.length;i++){
			  Vector vec=new Vector();
			  for(int j=0;j<a.length;j++){
					  vec.addElement(a[i][j]);
			  }
			  array.addElement(vec);
			}
		g_N=a.length;
		g_size=a.length;
		return array;
	}
	
	public double Dijkstra(Stack<Integer> path, int start, int end){
		if(end == start || start >= g_size) 
			return -1;
		double[] dist = new double[g_size];
		int[] paths = new int[g_size];
		double min = dijkstra(paths,dist,start,end);
	    if(min < 0) { 
	    	return -1;
	    }

		// parse the shortest path
		if(end > g_size-1) 
			end = g_size - 1;
		int i = end;	
		while(i>=0)
		{		
			path.push(i);
			i=paths[i];			
		}		
		if(array.get(start).get(path.peek())>=0.0)
			path.addElement(start);	
		else 
			{ return -1;}
		return min;
	}
	//return the shortest path distance
	public double dijkstra(int[] paths,double[] dist, int start, int end){
		int[] used = new int [g_N];
		int i;
		for(i=0;i<g_N;i++)
		{
			paths[i]=-1 ;	
			used[i] = 0;
			dist[i] = (array.get(start).get(i)< 0.0) ? POSITIVE_INFINITY : array.get(start).get(i); 
		}	

		used[start] = 1;		           // label the start node
		
		int count = 0;	
		while(count++ < g_N)
		{		
			int min_node = -1;
			double min_dist = POSITIVE_INFINITY;

			/* Select */
			for(i=0;i<g_N;i++)
			{
				if(used[i] > 0){continue;}   // ignore the used node
				
				if(dist[i] < min_dist)
				{
					min_dist = dist[i];
					min_node = i;
				}
			}
			if(min_dist == POSITIVE_INFINITY){break;}

			/* Record shortest path from the current node to start node */
			if(min_node >= 0)
			{			
				used[min_node]  = 1;
				dist[min_node]  = min_dist;			
			}
			if(min_node >= (int)g_N-1){break;}

			/* Adjust */
			for(i=0;i<g_N;i++)
			{
				if( used[i] > 0){continue;}  // ignore the used node

				double w = array.get(min_node).get(i) < 0.0 ? POSITIVE_INFINITY : array.get(min_node).get(i);
				if( min_dist + w < dist[i] )
				{
					dist[i] = min_dist + w;
					paths[i]= min_node;
				}
			}       
		}	
		double mdist = dist[g_N-1];
		if( end != start && end < g_N)
			{mdist = dist[end];}	
		return mdist == POSITIVE_INFINITY ? -1 : mdist;
	}
	
	@SuppressWarnings("rawtypes")
	int MSAforKSP(int k,Vector<Stack> kpaths,int start,int end)
	{
		/* Initialize */	
		int[] Path_tempt = new int[g_size];
		double[] Dist_tempt = new double[g_size];
		Vector<Integer> Path =new Vector<Integer>();
		Vector<Integer> Base =new Vector<Integer>();
		Vector<Integer> Prime = new Vector<Integer>();
		Vector<Double> Dist =new Vector<Double>(); 

		/* Find the shortest path firstly */	
		if( dijkstra(Path_tempt,Dist_tempt, start, end) < 0 ) return 0;
		Stack<Integer> path = new Stack<Integer>();      //可记录一条路径
		Stack<Integer> path2 = new Stack<Integer>();
		for(int i=0;i<g_size;i++)
		{ 
			Prime.addElement(-1); 
			Base.addElement(i); 
			Path.addElement(Path_tempt[i]);
			Dist.addElement(Dist_tempt[i]);
		} 
		int j = end;	
		while(j>=0)
		{		
			path.push(j); path2.push(j);j=Path_tempt[j];	//push -- stack 
		}
//		System.out.println("Dist = "+Dist);
//		System.out.println("Path = "+Path);
//		System.out.println("path = "+path);
//		System.out.println("Prime = "+Prime);
//		System.out.println("Base = "+Base);

		kpaths.addElement(path2); // store the shortest path  push_back vector
		
		/* Find the 2th - kth shortest paths */
		int ki = 1;
		while( ki < k )
		{
			System.out.println("---------------roll = "+ki);
			/* Find the first node with more than a single incoming arc */
			int nh = 0;
			while( path.size()>0 )  //path 向量不为空时，循环前记录的是第ki条最短路径1389
			{
				int node = path.peek(); path.pop();
				int nextnode = path.peek();
//				System.out.println("path = "+path);
				int count = 0;
				for( int i=0; i<g_size; i++ )
				{
					if( array.get(i).get(node) >= 0 && i != nextnode) count++;
					if( count > 1 ) 
					{
						break;
					}	
				}
				if( count > 1 ) { nh = node;break; } //nh：可扩展节点
			}

			if( nh==0 ){break; }// there is NOT an alternative path, exit!

			int ni = 0;
			/* Add the first prime node to graph */
			if( Prime.get(nh) < 0 ) 
			{
				int nh1;
				if(path.size()>0){
					nh1 = AddNode(nh,Path.get(nh),path.peek());
				}
				else{
					nh1 = AddNode(nh,Path.get(nh));
				}
				 //nh1=size++  of _array,估计是扩展节点，列编号
				
				/* compute the minimal distance from node 0 to nh1 */
				double min_dist = POSITIVE_INFINITY;
				int min_node = -1;
				for(int i=0;i<g_size-1;i++)
				{	
					double tmp = (array.get(i).get(nh1)< 0.0) ? POSITIVE_INFINITY : array.get(i).get(nh1);
					if( Dist.get(i) + tmp < min_dist )
					{
						min_dist = Dist.get(i) + tmp;
						min_node = i;
					}
				}	
				Dist.addElement(min_dist);
				Path.addElement(min_node);  //nh1到s的最短路径和连接节点。
				Prime.addElement(-1);  
				Prime.setElementAt(nh1, nh);  //记录nh的扩展节点nh1  Prime更新
				
				/* record the base node */
				int basei = nh;
				while(basei != Base.get(basei))
				{
					basei = Base.get(basei);
				}
				Base.addElement(basei); //记录nh1的原节点					

				if(path.size()>0)
				{ ni = path.peek(); path.pop(); }
			}
			/*  Get node ni, it must meet it's the first node following nh in path, but its prime node ni` is NOT in graph */
			else
			{
				while( path.size()>0 )
				{
					ni = path.peek(); path.pop();
					if(Prime.get(ni) < 0) break;				
				}
			}		
			/* Add the other prime nodes to graph */
			while(ni > 0)
			{
				// dele the arc -- (ni`,ni+1,)
				int ni1;
				if(path.size()>0){
					ni1 = AddNode(ni,Path.get(ni),path.peek());
				}
				else{
					ni1 = AddNode(ni,Path.get(ni));
				}
				if(Prime.get(Path.get(ni))>=0)		
				{
					array.get(Prime.get(Path.get(ni))).setElementAt(array.get(Path.get(ni)).get(ni), ni1);	
					// add the arc -- (ni-1`,ni`)
				}
						
				/* compute the minimal distance from node 0 to ni1 */
				double min_dist = POSITIVE_INFINITY;
				int min_node = -1;
				for(int i=0;i<g_size-1;i++)
				{
					double tmp = (array.get(i).get(ni1)< 0.0) ? POSITIVE_INFINITY : array.get(i).get(ni1);
					if( Dist.get(i) + tmp < min_dist )
					{
						min_dist = Dist.get(i) + tmp;
						min_node = i;
					}
				}
				
				Dist.addElement(min_dist);
				Path.addElement(min_node);
				Prime.addElement(-1);
				Prime.setElementAt(ni1, ni);;

				/* record the base node */
				int basei = ni;
				while(basei != Base.get(basei))
				{
					basei = Base.get(basei);		
				}
				Base.addElement(basei);

				if( path.size()==0 ) break;
				ni = path.peek(); path.pop();			
			}
			System.out.println("Dist = "+Dist);
			System.out.println("Path = "+Path);
			System.out.println("Prime = "+Prime);
			System.out.println("Base = "+Base);
			
			/* get the kth shortest path */			
			if( ni==0 ) ni = nh; // if nh is just the end node.
			Stack<Integer> temp = new Stack<Integer>();
			j = Prime.get(ni);
			while(j>=0)
			{		
				path.push(j); 
				temp.push(Base.get(j)); 
				j=Path.get(j);
			}
			if(temp.size()<2) break;  //路径只有一个节点，break
			kpaths.addElement(temp);  // store the kth shortest path
			ki++;;
		}	
		for(int t=0;t<kpaths.size();t++){
			kpaths.elementAt(t).addElement(start);
		}
		return ki;
	}
	private int AddNode(int ni,int preni, int nextni){
		
		Vector newRow = new Vector<Object>();
		for(int i=0;i<g_size;i++)
		{
			if(i != preni && i != nextni)    //如果i不等于nh节点最短路径的前一个点
				array.get(i).addElement(array.get(i).get(ni)); //在第i行后，加入i--》nh的权值
			else
				array.get(i).addElement(-1.0);

			newRow.addElement(-1.0);
		}
		newRow.addElement(-1.0);
		array.addElement(newRow); //加一行 -1
		return g_size++;		
	}
	private int AddNode(int ni,int preni){
		
		Vector newRow = new Vector<Object>();
		for(int i=0;i<g_size;i++)
		{
			if(i != preni)    //如果i不等于nh节点最短路径的前一个点
				array.get(i).addElement(array.get(i).get(ni)); //在第i行后，加入i--》nh的权值
			else
				array.get(i).addElement(-1.0);

			newRow.addElement(-1.0);
		}
		newRow.addElement(-1.0);
		array.addElement(newRow); //加一行 -1
		return g_size++;		
	}
	public void calcuQoS(double[][] bw,double[][] arriveRate, double[][] delay){
		for(int i=0;i<kpaths.size();i++){
			double B=POSITIVE_INFINITY,A=1,D=0;	
			int temp1 = (Integer) kpaths.get(i).peek();kpaths.get(i).pop();
			Vector arraytemp = new Vector();arraytemp.addElement(temp1);
			while(kpaths.get(i).size()>0){
				int temp2 = (Integer) kpaths.get(i).peek();kpaths.get(i).pop();
				B = Math.min(B , bw[temp1][temp2]);
				A = A * arriveRate[temp1][temp2];
				D = D + delay[temp1][temp2];
				temp1 = temp2;
				arraytemp.addElement(temp2);
			}
			Vector temp3 = new Vector();
			temp3.addElement(B);
			temp3.addElement(A);
			temp3.addElement(D);
			constraint.add(temp3);
			array_path.add(arraytemp);
		}
	}
	
	public Vector SelectPath(double[] qos){
		for(int i=0 ;i<array_path.size();i++){
			if(qos.length==1)
				if(constraint.get(i).get(0)>=qos[0]){
					return array_path.get(i);}
			if(qos.length==2)
					if(constraint.get(i).get(0)>=qos[0] && constraint.get(i).get(1)>=qos[1]){
							return array_path.get(i);}
			
			if(qos.length==3){
				if(constraint.get(i).get(0)>=qos[0] && constraint.get(i).get(1)>=qos[1] && constraint.get(i).get(2)<=qos[2]){
					return array_path.get(i);}
				}		
		}
		return null;
	}

}