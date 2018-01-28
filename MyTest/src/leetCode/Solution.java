package leetCode;

public class Solution {
	public static String longestCommonPrefix(String[] strs) {
        if(strs==null)
        	return " ";      
       int j=0;
       int w=minlen(strs);
       StringBuilder res=new StringBuilder();
            while(j<w)
            {                
                  for(int i=0;i<strs.length;i++)
                  {
                	  if(strs[i].charAt(j)!=strs[0].charAt(j))
                          return res.toString();             
                  }
                  res.append(strs[0].charAt(j));
                  j++;
            }
            return res.toString();
}
	private static int minlen(String[] strs) {
        int min = Integer.MAX_VALUE;
        for(int i=0; i<strs.length;i++)
            min = Math.min(min,strs[i].length());
        return min;
    }
	public static void main(String args[]) {
        String [] strs = {"abcd","abcdfe","abcdff"};
        String c=longestCommonPrefix(strs);
        System.out.println( c );
   }
}
