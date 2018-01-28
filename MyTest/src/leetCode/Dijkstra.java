package leetCode;

public class Dijkstra {
	
	 public static int M = 10000;   //此路不通
	 public static void main(String[] args) {
		 long startTime=System.currentTimeMillis();   //获取开始时间
		 
	 int[][] weight1 = {//邻接矩阵 
	        {0,3,2,7,M}, 
	        {3,0,4,2,M}, 
	        {M,4,0,5,4}, 
	        {7,2,5,0,6},   
	        {M,M,4,6,0} 
	    }; 

	   /* int[][] weight2 = { 
	        {0,3,M,M,5,M,M}, 
	        {3,0,12,7,M,M,M}, 
	        {M,12,0,8,M,12,M}, 
	        {M,7,8,0,4,M,13}, 
	        {5,M,M,4,0,M,6} ,
	        {M,M,12,M,M,0,6},
	        {M,M,M,12,6,6,0}
	    };*/
	  	int [][] weight2=Random();	    
	    int start=0; 
	    int[] shortPath = dijkstra(weight1,start); 
	     
	    for(int i = 0;i < shortPath.length;i++) 
	       System.out.println("从"+start+"出发到"+i+"的最短距离为："+shortPath[i]); 
	    long endTime=System.currentTimeMillis();
		System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
	 }
	 
	 public static int[] dijkstra(int[][] weight, int start) {
		 //接受一个有向图的权重矩阵，和一个起点编号start（从0编号，顶点存在数组中） 
		 //返回一个int[] 数组，表示从start到它的最短路径长度 
	 int n = weight.length;      //顶点个数
	 int[] shortPath = new int[n];  //保存start到其他各点的最短路径
	 String[] path = new String[n];  //保存start到其他各点最短路径的字符串表示
	 for(int i=0;i<n;i++) 
	  path[i]=new String(start+"-->"+i); 
	 int[] visited = new int[n];   //标记当前该顶点的最短路径是否已经求出,1表示已求出 
	 
	 //初始化，第一个顶点已经求出
	 shortPath[start] = 0;
	 visited[start] = 1;
	 
	 for(int count = 1; count < n; count++) {   //要加入n-1个顶点
	  int k = -1;        //选出一个距离初始顶点start最近的未标记顶点 
	  int dmin = Integer.MAX_VALUE;
	  for(int i = 0; i < n; i++) {
	  if(visited[i] == 0 && weight[start][i] < dmin) {
	   dmin = weight[start][i];
	   k = i;
	  }
	  }
	  
	  //将新选出的顶点标记为已求出最短路径，且到start的最短路径就是dmin 
	  shortPath[k] = dmin;
	  visited[k] = 1;
	  
	  //以k为中间点，修正从start到未访问各点的距离 
	  for(int i = 0; i < n; i++) {
	  if(visited[i] == 0 && weight[start][k] + weight[k][i] < weight[start][i]) {
	   weight[start][i] = weight[start][k] + weight[k][i];
	   path[i] = path[k] + "-->" + i; 
	  }
	  }
	 }
	 for(int i = 0; i < n; i++) {
	  System.out.println("从"+start+"出发到"+i+"的最短路径为："+path[i]);
	 }
	 System.out.println("====================================="); 
	 return shortPath;
	 }
	 
	 public static int[][] Random()
		{
			int[][] r=new int[60][60];
			for (int i = 0; i < 60; i++) 
			{
				for (int j = i; j < 60; j++)
				{
					r[i][j]=(int)(Math.random()*100);
					r[j][i]=r[i][j];
					if(i==j)
					{
						r[i][j]=0;
					}
					//System.out.print(r[i][j]+" ");
				}
				//System.out.print("\n");
			}
				for (int t = 0; t < 3000; t++)
				{
					int i=(int)(Math.random()*6);
					int j=(int)(Math.random()*6);
					r[i][j]=M;
					//System.out.print(i+" lalla "+j);
				}
				//System.out.print("\n");
				for (int i = 0; i < 60; i++) 
				{
					for (int j = 0; j < 60; j++)
					{
						//System.out.print(r[i][j]+" ");
					}
					//System.out.print("\n");
				}
			return r;
		}
	}
