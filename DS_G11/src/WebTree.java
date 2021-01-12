import java.io.IOException;
import java.util.ArrayList;

import javax.net.ssl.SSLHandshakeException;


public class WebTree {
 public WebNode root;
 
 public WebTree(WebPage rootPage){
  this.root = new WebNode(rootPage);
 }
 
 public void setPostOrderScore(KeywordList keywords) throws IOException{
  setPostOrderScore(root, keywords);
 }
 
 private void setPostOrderScore(WebNode startNode, KeywordList keywords) throws IOException
 {
  try {
   //4. compute the score of children nodes postorder
   for(WebNode child : startNode.children)
   {
    child.setNodeScore(keywords);
    
   }
   //5.setNode score of startNode æ²’å?å­© webnode è¨ˆç?—å?†æ•¸
   startNode.setNodeScore(keywords);
  }
  catch(IOException e)
  {
   startNode.webPage.setScore(keywords);
   for(WebNode child : startNode.children)
   {
    startNode.nodeScore+=child.nodeScore;//child.nodeScore
   }
//   System.out.println("IOException");
  }
 }
 
 public void eularPrintTree(){
  eularPrintTree(root);
 }
 //preorder
 private void eularPrintTree(WebNode startNode){
  int nodeDepth = startNode.getDepth();
  
  if(nodeDepth > 1) 
  { System.out.print("\n" + repeat("\t", nodeDepth-1));}
  //print "("
  System.out.print("(");
  //print "name","score"
  System.out.print(startNode.webPage.name+","+startNode.nodeScore);
  
  //7.print child preorder
  for(WebNode child : startNode.children)
  {
   eularPrintTree(child);
  }
  
  //print ")"
  System.out.print(")");
  
  /*for example
   (Publication,286.2)
  */
  if(startNode.isTheLastChild())
  {
   System.out.print("\n" + repeat("\t", nodeDepth-2));
  }
  
 }
 
 private String repeat(String str,int repeat){
  String retVal  = "";
  for(int i=0;i<repeat;i++){
   retVal+=str;
  }
  return retVal;
 }
}