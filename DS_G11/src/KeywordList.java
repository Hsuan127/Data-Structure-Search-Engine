import java.util.ArrayList;

public class KeywordList {
	private ArrayList<Keyword> lst;
	
	public KeywordList(){
		this.lst = new ArrayList<Keyword>();
    }
	
	public void add(Keyword keyword){
		lst.add(keyword);
		System.out.println("Done");
    }
	
	public void find(String s){
		int maxValue = -1;
		int maxIndex = -1;
		for(int i=0; i<lst.size(); i++){
			int lcs = findLCS(lst.get(i).name, s);
			//System.out.println(lcs);
			if(lcs > maxValue){
				maxValue = lcs;
				maxIndex = i;
			}
		}
		System.out.println(lst.get(maxIndex).toString());
	}
	
	public int findLCS(String x, String y){
		//1. fill this method
		
		/*if(x.equals(y))
			return y.length();
		else {
			for(int i=x.length();i>=0;i--) {
				for(int j=y.length();j>=0;j--) {
					if(x.subSequence(i, ))
				}
			}
		}*/
		int n1=x.length();
		int n2=y.length();
		int[][] l = new int[n1+1][n2+1];
		for (int i=0; i<=n1; i++) 
			l[i][0] = 0;
		for (int j=0; j<=n2; j++) 
			l[0][j] = 0;
		for (int i=1;i<=n1;i++) {
			for (int j=1;j<=n2;j++) {
				if(x.charAt(i-1)==y.charAt(j-1)) {
					l[i][j]=l[i-1][j-1]+1;
				}else {
					if(l[i-1][j]<=l[i][j-1])
						l[i][j]=l[i][j-1];
					else
						l[i][j]=l[i-1][j];
				}
			}
		}return l[n1][n2];
	}
	
	private void printMatrix(int[][] matrix){
		for(int i=0; i<matrix.length; i++){
			for(int j=0; j<matrix[0].length; j++){
				System.out.print(matrix[i][j] + " ");
				if(j==matrix[0].length-1)System.out.print("\n");
			}
		}
	}
}