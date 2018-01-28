package leetCode;

public class NetInfo{
	public static int M = 10000;
	public int[][] topoMetric; 
	public double [][] transRate;
	public double [][] queueTotal;
	public double [][] queueOccupy;
	public double [][] timeBusy;
	public double [][] timeTotal;
	public double [] sinr;
	public double [] snr;
	public double maxInterference;
	
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
	    int start=0; 
	    String[] Path = Dijsktra(weight1,start); 
	     
	    for(int i = 0;i < Path.length;i++) 
	       System.out.println("��"+start+"������"+i+"����̾���Ϊ��"+Path[i]); 
	    long endTime=System.currentTimeMillis();
		System.out.println("��������ʱ�䣺 "+(endTime-startTime)+"ms");
		
	 }
	
				
	public static String[] Dijsktra(int[][] weight,int start){
	     //����һ������ͼ��Ȩ�ؾ��󣬺�һ�������start����0��ţ�������������У�
	        //����һ��int[] ���飬��ʾ��start���������·������
	        int n = weight.length;        //�������
	        int[] shortPath = new int[n];    //��Ŵ�start��������������·��
	        String[] path=new String[n]; //��Ŵ�start��������������·�����ַ�����ʾ
	         for(int i=0;i<n;i++)
	             path[i]=new String(start+"-->"+i);
	        int[] visited = new int[n];   //��ǵ�ǰ�ö�������·���Ƿ��Ѿ����,1��ʾ�����
	        
	        //��ʼ������һ���������
	        shortPath[start] = 0;
	        visited[start] = 1;

	        for(int count = 1;count <= n - 1;count++)  //Ҫ����n-1������
	        {
	 
	            int k = -1;    //ѡ��һ�������ʼ����start�����δ��Ƕ���
	            int dmin = Integer.MAX_VALUE;
	            for(int i = 0;i < n;i++)
	            {
	                if(visited[i] == 0 && weight[start][i] < dmin)
	                {
	                    dmin = weight[start][i];
	                   
	                    k = i;
	                }  
	                    
	            }
	            System.out.println("k="+k);
	             
	            //����ѡ���Ķ�����Ϊ��������·�����ҵ�start�����·������dmin
	            shortPath[k] = dmin;

	            visited[k] = 1;
	  
	            //��kΪ�м�㣬������start��δ���ʸ���ľ���
	            for(int i = 0;i < n;i++)
	            {                 
	            	// System.out.println("k="+k);
	                if(visited[i] == 0 && weight[start][k] + weight[k][i] < weight[start][i]){
	                     weight[start][i] = weight[start][k] + weight[k][i];
	                   
	                     path[i]=path[k]+"-->"+i;
	                    
	                }
	                
	            }  
	     
	        }
//	         for(int i=0;i<n;i++)
//	           System.out.println("��"+start+"������"+i+"�����·��Ϊ��"+path[i]);  
//	         System.out.println("=====================================");
//	      
	        return path;
	    }
	
	public double[][] linkCostCal() {
		int N=topoMetric.length;
		double[][] linkCost=new double [N][N];
		double IR;
		double CU;
		double QU;
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				IR = Math.min(sinr[i]/snr[i], sinr[j]/snr[j]);
				CU = timeBusy[i][j]/timeTotal[i][j];
				QU = queueOccupy[i][j]/queueTotal[i][j];
				linkCost[i][j] = (CU+QU)/IR;
			}
		}
		
	return linkCost;				
	}
	
	
}


