import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Servlet implementation class TestProject
 */
@WebServlet("/TestProject")
public class TestProject extends HttpServlet {
 private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestProject() {
        super();
        // TODO Auto-generated constructor stub
    }

 /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  // TODO Auto-generated method stub
  response.setCharacterEncoding("UTF-8");
  request.setCharacterEncoding("UTF-8");
  response.setContentType("text/html");
  if(request.getParameter("keyword")== null) {
   String requestUri = request.getRequestURI();
   request.setAttribute("requestUri", requestUri);
   request.getRequestDispatcher("Search.jsp").forward(request, response);
   return;
  }
  KeywordList keywords = new KeywordList();
  HashMap<String,String> query=new GoogleQuery(request.getParameter("keyword")).query();
  ArrayList<WebNode> nodeList=new ArrayList<WebNode>();
  HashMap<String,String> subpages=new HashMap<String,String>();
  try {
   for(String key:query.keySet())
   {
    String map="";
    WebPage rootPage = new WebPage(query.get(key), key);
    WebTree tree = new WebTree(rootPage);
    subpages=getSubpage(query.get(key));
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
   String[][] results = new String[nodeList.size()][2];
   request.setAttribute("query", results);
   for(int i=0;i<nodeList.size();i++)
   {
    String name=nodeList.get(i).webPage.name;
    String url=nodeList.get(i).webPage.url;
    results[i][0] = name;
       results[i][1] = url;
   }
//   for(int i=0;i<nodeList.size();i++)
//   {
//    System .out.println("( "+nodeList.get(i).webPage.name+" , "+nodeList.get(i).webPage.url+" , "+nodeList.get(i).nodeScore+" )");
//   }
//   System.out.println(ranking.output());
  }
  catch(IOException e)
  {
   e.printStackTrace();
  }
//  GoogleQuery google = new GoogleQuery(request.getParameter("keyword"));
//  HashMap<String, String> query = google.query();
//  
//  String[][] s = new String[query.size()][2];
//  request.setAttribute("query", s);
//  int num = 0;
//  for(Entry<String, String> entry : query.entrySet()) {
//      String key = entry.getKey();
//      String value = entry.getValue();
//      s[num][0] = key;
//      s[num][1] = value;
//      num++;
//  }
  request.getRequestDispatcher("googleitem.jsp")
   .forward(request, response); 
  
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
            }
           }
            return subpages;
        } 
     catch (IOException ex) 
     {
//         return "IOException";
         return null;
        }
    }
 /**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  // TODO Auto-generated method stub
  doGet(request, response);
 }

}