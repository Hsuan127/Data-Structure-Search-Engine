import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
 

 public static void main(String[] args) throws IOException {
  // TODO Auto-generated method stub
  //root node
  WebPage rootPage = new WebPage("https://www.funtime.com.tw/blog/funtime/top-10-hotspring-in-taiwan", "Funtime");  
  WebTree tree = new WebTree(rootPage);
  //build childnode
  tree.root.addChild(new WebNode(new WebPage("https://www.funtime.com.tw/localtour/beitou","Beitou")));
  tree.root.addChild(new WebNode(new WebPage("https://www.funtime.com.tw/domhotel/北投訂房-住宿-飯店-民宿-酒店","hotel")));
  //add below projects
  tree.root.addChild(new WebNode(new WebPage("https://www.funtime.com.tw/blog/funtime/隨便拍都好看！台北陽明山秘境景點top10", "yangming")));
  tree.root.addChild(new WebNode(new WebPage("https://www.funtime.com.tw/blog/funtime/ulay-attractions-recommend","ulay")));
  tree.root.children.get(3).addChild(new WebNode(new WebPage("https://www.funtime.com.tw/blog/funtime/wulai-spring", "wulaiHotel")));
  //read keyword: 2 Yu 1.2 Fang 1.8 
  ArrayList<Keyword> keywords = new ArrayList<Keyword>();
  Keyword key1=new Keyword("冬天",2);
  Keyword key2=new Keyword("溫泉",5);
  Keyword key3=new Keyword("飯店",4);
  Keyword key4=new Keyword("台灣",4);
  Keyword key5=new Keyword("美景",2);
  Keyword key6=new Keyword("度假村",2);
  Keyword key7=new Keyword("SPA",4);
  Keyword key8=new Keyword("湯屋",3);
  Keyword key9=new Keyword("泥漿溫泉",4);
  Keyword key10=new Keyword("水療",4.5);
  Keyword key11=new Keyword("泡湯",3);
  keywords.add(key1);
  keywords.add(key2);
  keywords.add(key3);
  keywords.add(key4);
  keywords.add(key5);
  keywords.add(key6);
  keywords.add(key7);
  keywords.add(key8);
  keywords.add(key9);
  keywords.add(key10);
  keywords.add(key11);
   /*for(int i =0;i<numOfKeywords;i++)
   {
    String name = scanner.next();//Yu
    double weight = scanner.nextDouble();//1.2
    Keyword k = new Keyword(name, weight);//store key
    keywords.add(k);
   }
   */
   
   tree.setPostOrderScore(keywords);
   tree.eularPrintTree();
  }
  
}
