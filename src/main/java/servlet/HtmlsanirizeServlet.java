package servlet;



public final class HtmlsanirizeServlet {


public class sanirize (String word) {
	
	String newWord = word;
	newWord = newWord).replace("&", "&amp;");
	newWord = newWord.replace("\"", "&quot;");
	newWord = newWord.replace("<", "&lt;");
	newWord = newWord.replace(">", "&gt");
	newWord = newWord.replace("", "&nbsp");
	return newWord;
}
}