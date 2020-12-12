import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// root node
		WebPage rootPage = new WebPage("http://soslab.nccu.edu.tw/Welcome.html", "Soslab");
		WebTree tree = new WebTree(rootPage);
		// build childnode
		tree.root.addChild(new WebNode(new WebPage("http://soslab.nccu.edu.tw/Publications.html", "Publication")));
		tree.root.addChild(new WebNode(new WebPage("http://soslab.nccu.edu.tw/Projects.html", "Projects")));
		tree.root.children.get(1).addChild(new WebNode(new WebPage("https://vlab.cs.ucsb.edu/stranger/", "Stranger")));
		tree.root.addChild(new WebNode(new WebPage("http://soslab.nccu.edu.tw/Members.html", "MEMBER")));
		tree.root.addChild(new WebNode(new WebPage("http://www3.nccu.edu.tw/~yuf/course.htm", "Course")));

		ArrayList<Keyword> keywords = new ArrayList<Keyword>();
		Keyword a1 = new Keyword("¥V¤Ñ", 2);
		Keyword a2 = new Keyword("·Å¬u", 5);
		Keyword a3 = new Keyword("¶º©±", 4);
		Keyword a4 = new Keyword("¥xÆW", 4);
		Keyword a5 = new Keyword("¬ü´º", 2);
		Keyword a6 = new Keyword("«×°²§ø", 4);
		Keyword a7 = new Keyword("SPA", 3);
		Keyword a8 = new Keyword("´ö«Î", 4);
		Keyword a9 = new Keyword("ªd¼ß·Å¬u", 4.5);
		Keyword a10 = new Keyword("¤ôÀø", 3);
		Keyword a11 = new Keyword("ªw´ö", 5);
		keywords.add(a1);
		keywords.add(a2);
		keywords.add(a3);
		keywords.add(a4);
		keywords.add(a5);
		keywords.add(a6);
		keywords.add(a7);
		keywords.add(a8);
		keywords.add(a9);
		keywords.add(a10);
		keywords.add(a11);

		tree.setPostOrderScore(keywords);
		tree.eularPrintTree();
	}

}

/*
 * 
 * Input: 2 Fang 0.5 Yu 0.6 Output: (Fang Yu,131.10000000000002
 * (Publication,110.5) (Project,12.1 (Stranger,0.0) ) (Biography,2.8 (Vlab,0.0)
 * ) (Course,2.3) )
 */