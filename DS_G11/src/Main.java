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
  keywords.add(new Keyword("冬天",2));
  keywords.add(new Keyword("溫泉",5));
  keywords.add(new Keyword("飯店",4));
  keywords.add(new Keyword("台灣",4));
  keywords.add(new Keyword("美景",2));
  keywords.add(new Keyword("度假村",2));
  keywords.add(new Keyword("SPA",4));
  keywords.add(new Keyword("湯屋",3));
  keywords.add(new Keyword("泥漿溫泉",4));
  keywords.add(new Keyword("水療",4.5));
  keywords.add(new Keyword("泡湯",3));
  HashMap<String,String> test=new GoogleQuery(a).query();
//  String subpages="";
  HashMap<String,String> subpages=new HashMap<String,String>();
  ArrayList<WebTree> treeList=new ArrayList<WebTree>();
  try {
  for(String key:test.keySet())
     {
   String map="";
//   WebPage rootPage = new WebPage(test.get(key), key);
//   WebTree tree = new WebTree(rootPage);
   subpages=getSubpage(test.get(key));
   if(subpages!=null)
   {
    for(String key2:subpages.keySet())
    { 
//         System.out.println(("subpages of "+key+":"+key2+","+subpages.get(key2)));
     map+="    ("+key2+","+subpages.get(key2)+")\n";
//     tree.root.addChild(new WebNode(new WebPage(subpages.get(key2),key2)));  
    }
    System.out.println("subpages of "+key+":\n"+map);
//    tree.setPostOrderScore(keywords);
//    tree.eularPrintTree();
//    System.out.println("subpages of "+key+":"+subpages);
    //System.out.println(key + " : " + test.get(key));
   }
     }
  }
        catch (NullPointerException e)
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
//              if(title=="")
//              {
//               title="a";
//               spLinks+="              ("+title+","+link.attr("abs:href")+")\n";
//               subpages.put(title,link.attr("abs:href"));
//              }
              //System.out.println("("+title+","+link.attr("abs:href")+")");
//              Document subpage=Jsoup.connect(link.attr("abs:href")).get();
//              title=subpage.select("a").getElementsByTag("title").text();
//              else
//              {
              
              spLinks+="              ("+title+","+link.attr("abs:href")+")\n";
              //subpages.put(title,link.attr("abs:href"));
              if(title=="")
              {
               subpages.put(i+"",link.attr("abs:href"));
               i++;
              }
              else
              {
               subpages.put(title,link.attr("abs:href"));
              }
//             }
            }
           }
            return subpages;
        } catch (IOException ex) {
//         return "IOException";
         return null;
        }
//     return "no subpage";
    }
 private static String fetchContent(String url) throws IOException
 {
  String retVal = "";
  URL u = new URL(url);
  URLConnection conn = u.openConnection();
  conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");
  InputStream in = conn.getInputStream();
  InputStreamReader inReader = new InputStreamReader(in,"utf-8");
  BufferedReader bufReader = new BufferedReader(inReader);
  String line = null;
  while((line=bufReader.readLine())!=null)
  {
   retVal += line;
  }
  return retVal;
 }
  
}
