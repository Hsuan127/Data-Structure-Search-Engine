import java.util.ArrayList;

public class KeywordList  {
 private ArrayList<Keyword> lst;
 
 public KeywordList(){
  this.lst = new ArrayList<Keyword>();
  lst.add(new Keyword("冬天",2));
  lst.add(new Keyword("溫泉",5));
  lst.add(new Keyword("飯店",4));
  lst.add(new Keyword("台灣",4));
  lst.add(new Keyword("美景",2));
  lst.add(new Keyword("度假村",2));
  lst.add(new Keyword("SPA",1));
  lst.add(new Keyword("湯屋",3));
  lst.add(new Keyword("泥漿溫泉",4));
  lst.add(new Keyword("水療",4));
  lst.add(new Keyword("泡湯",4.5));
  
    }
 public ArrayList<Keyword> getList()
 {
  return lst;
 }
 public void add(Keyword keyword){
  lst.add(keyword);
//  System.out.println("Done");
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
  int[][] L=new int[x.length()+1][y.length()+1];
  for(int i=0;i<=x.length();i++)
  {
   L[i][0]=0;
  }
  for(int j=0;j<=y.length();j++)
  {
   L[0][j]=0;
  }
  for(int i=1;i<=x.length();i++)
  {
   for(int j=1;j<=y.length();j++)
   {
       if(x.substring(i-1,i).equals(y.substring(j-1,j)))
    {
     L[i][j]=L[i-1][j-1]+1;
    }
    else
    {
     if(L[i-1][j]>L[i][j-1])
     {
      L[i][j]=L[i-1][j];
     }
     else
     {
      L[i][j]=L[i][j-1];
     }
    }
   }
  } 
  return L[x.length()][y.length()];
  
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
