package leetCode;

public class Random {
	private static int M = 10000;   //��·��ͨ
	public static int[][] Random()
	{
		int[][] r=new int[60][60];
		for (int i = 0; i < 60; i++) 
		{
			for (int j = 0; j < 60; j++)
			{
				r[i][j]=(int)(Math.random()*100);
				if(i==j)
				{
					r[i][j]=0;
				}
				//System.out.print(r[i][j]+" ");
			}
			//System.out.print("\n");
		}
			for (int j = 0; j < 1600; j++)
			{
				r[(int)(Math.random()*6)][(int)(Math.random()*6)]=M;
				//System.out.print(r[i][j]+" ");
			}
			//System.out.print("\n");
		return r;
	}
	public static void main(String[] args) {
		long startTime=System.currentTimeMillis();   //��ȡ��ʼʱ�� 		 
		int [][] weight=Random();
		long endTime=System.currentTimeMillis();
		System.out.println("��������ʱ�䣺 "+(endTime-startTime)+"ms"); 
		
	}
}