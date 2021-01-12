import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
 
 public static void main(String[] args) throws IOException, NullPointerException {
  // TODO Auto-generated method stub
  String a="";
  Scanner in=new Scanner(System.in);
  System.out.println("User input keyword:"+a);
  a=in.next();
  KeywordList keywords = new KeywordList();
  for(int i=0;i<keywords.getList().size();i++)
  {
   System.out.println(keywords.getList().get(i).toString());
  }
  HashMap<String,String> test=new GoogleQuery(a).query();
  ArrayList<WebNode> nodeList=new ArrayList<WebNode>();
  HashMap<String,String> subpages=new HashMap<String,String>();
  try {
   for(String key:test.keySet())
   {
    String map="";
    WebPage rootPage = new WebPage(test.get(key), key);
    WebTree tree = new WebTree(rootPage);
    subpages=getSubpage(test.get(key));
    if(subpages!=null)
    {
     for(String key2:subpages.keySet())
     { 
      tree.root.addChild(new WebNode(new WebPage(subpages.get(key2),key2)));
      map+="    ("+key2+","+subpages.get(key2)+")\n";     
     }
//     System.out.println("subpages of "+key+":\n"+map);
    }
    tree.setPostOrderScore(keywords);
    nodeList.add(tree.root);
//    tree.eularPrintTree();
   }
   Ranking ranking=new Ranking(nodeList);
   ranking.sort();
   nodeList=ranking.getList();
   for(int i=0;i<nodeList.size();i++)
   {
    System .out.println("( "+nodeList.get(i).webPage.name+" , "+nodeList.get(i).webPage.url+" , "+nodeList.get(i).nodeScore+" )");
   }
//   System.out.println(ranking.output());
  }
        catch (NullPointerException e)
        {
         e.printStackTrace();
        }
  catch(IOException e)
  {
   e.printStackTrace();
  }
 }
    public static HashMap<String,String> getSubpage(String url) throws IOException
    {
     try {
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a");
            String title="";
            String spLinks="\n";
            HashMap<String,String> subpages=new  HashMap<String,String>();
            int i=0;
            for (Element link : links) {
             
             if(link.attr("abs:href").startsWith(url)&&!link.attr("abs:href").equals(url)&&!link.attr("abs:href").startsWith(url+"#")
               &&!link.attr("abs:href").startsWith(url+"/#")&&!link.attr("abs:href").equals(url+"/")&&!link.attr("abs:href").endsWith("jpg"))//link.attr("title")!=null&&link.attr("abs:href").contains(url+"&")||link.attr("abs:href").contains(url+"/")
             {
              title=link.attr("title");
              if(title=="")
              {
               if(!link.text().isEmpty())
               {
                title=link.text();
                subpages.put(title,link.attr("abs:href"));
                spLinks+="              ("+title+","+link.attr("abs:href")+")\n";
               }
              }
              else
              {
               subpages.put(title,link.attr("abs:href"));
               spLinks+="              ("+title+","+link.attr("abs:href")+")\n";
              }
//             }
            }
           }
            return subpages;
        } catch (IOException ex) {
//         return "IOException";
         return null;
        }
    }
  
}