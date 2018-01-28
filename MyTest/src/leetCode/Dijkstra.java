package leetCode;

public class Dijkstra {
	
	 public static int M = 10000;   //��·��ͨ
	 public static void main(String[] args) {
		 long startTime=System.currentTimeMillis();   //��ȡ��ʼʱ��
		 
	 int[][] weight1 = {//�ڽӾ��� 
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
	       System.out.println("��"+start+"������"+i+"����̾���Ϊ��"+shortPath[i]); 
	    long endTime=System.currentTimeMillis();
		System.out.println("��������ʱ�䣺 "+(endTime-startTime)+"ms");
	 }
	 
	 public static int[] dijkstra(int[][] weight, int start) {
		 //����һ������ͼ��Ȩ�ؾ��󣬺�һ�������start����0��ţ�������������У� 
		 //����һ��int[] ���飬��ʾ��start���������·������ 
	 int n = weight.length;      //�������
	 int[] shortPath = new int[n];  //����start��������������·��
	 String[] path = new String[n];  //����start�������������·�����ַ�����ʾ
	 for(int i=0;i<n;i++) 
	  path[i]=new String(start+"-->"+i); 
	 int[] visited = new int[n];   //��ǵ�ǰ�ö�������·���Ƿ��Ѿ����,1��ʾ����� 
	 
	 //��ʼ������һ�������Ѿ����
	 shortPath[start] = 0;
	 visited[start] = 1;
	 
	 for(int count = 1; count < n; count++) {   //Ҫ����n-1������
	  int k = -1;        //ѡ��һ�������ʼ����start�����δ��Ƕ��� 
	  int dmin = Integer.MAX_VALUE;
	  for(int i = 0; i < n; i++) {
	  if(visited[i] == 0 && weight[start][i] < dmin) {
	   dmin = weight[start][i];
	   k = i;
	  }
	  }
	  
	  //����ѡ���Ķ�����Ϊ��������·�����ҵ�start�����·������dmin 
	  shortPath[k] = dmin;
	  visited[k] = 1;
	  
	  //��kΪ�м�㣬������start��δ���ʸ���ľ��� 
	  for(int i = 0; i < n; i++) {
	  if(visited[i] == 0 && weight[start][k] + weight[k][i] < weight[start][i]) {
	   weight[start][i] = weight[start][k] + weight[k][i];
	   path[i] = path[k] + "-->" + i; 
	  }
	  }
	 }
	 for(int i = 0; i < n; i++) {
	  System.out.println("��"+start+"������"+i+"�����·��Ϊ��"+path[i]);
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
