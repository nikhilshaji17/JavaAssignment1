import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Stack;
import java.util.HashSet;



public class WebCrawler {

	//creating UrlFile that will contains anchor tags and content
	private static FileWriter UrlFile;
	//creating ParaFile that will contains para tags and para content
    private static FileWriter ParaFile;
    //stack for dfs crawler
    private static Stack<String> UrlPending = new Stack<>();
    //hashset to keep a check so that same url is not visited again and again
    private static HashSet<String> UrlVisited = new HashSet<>();
    //depth of the crawler set to 10
    private static int depth=50;

    public static void getUrls(String url,String newBase) {
    	try
    	{

    		Document doc = Jsoup.connect(url).ignoreHttpErrors(true).ignoreContentType(true).get();

    		//Anchor tag elements
    		Elements links = doc.getElementsByTag("a");


    		//Separating href from content
    		//and sending them in corresponding csv file
    		for (Element link : links) {
    			  String linkHref = link.attr("href");
    			  String linkText = link.text();


    			  //checking for static and relative urls
    			  if(linkHref.contains("#") == false && linkHref.contains("http")== false && linkHref.contains("https")== false)
    			  {

    				  String baseUrl = newBase;
    				  String completeUrl = baseUrl + linkHref;

    				  if(!UrlVisited.contains(completeUrl))
    				  {
    				  UrlPending.add(completeUrl);
    			      UrlVisited.add(completeUrl);
    				  try {
    						//adding it to the file
    			            String newElement = completeUrl + "," + linkText;
    			            UrlFile.write(newElement);
    			            String line="\n";
    			            UrlFile.write(line);

    			        }
    					catch (IOException e)
    					{
    						System.out.println("Error in writing in File");
    			            e.printStackTrace();
    			        }
    				  }

    			  }
    			  //if it is a complete url
    			  else if (linkHref.contains("http://pec.ac.in")== true || linkHref.contains("https://pec.ac.in")== true)
    			  {
    				  if(!UrlVisited.contains(linkHref))
    				  {
    				  UrlPending.add(linkHref);
    			      UrlVisited.add(linkHref);
    				  try {
    						//adding it to the file
    			            String newElement = linkHref + "," + linkText;
    			            UrlFile.write(newElement);
    			            String line="\n";
    			            UrlFile.write(line);
    			        }
    					catch (IOException e)
    					{
    						System.out.println("Error in writing in File");
    			            e.printStackTrace();
    			        }
    				  }

    			  }
    			}


    		doc = Jsoup.connect(url).ignoreHttpErrors(true).ignoreContentType(true).get();
    		Elements paras = doc.getElementsByTag("p");
    		//Separating content from p tag
    		//and sending them in corresponding csv file
    		for (Element paraElement : paras) {
    			  String content = paraElement.text();
    			  if(content.length()>0)
    			  {
    				  try {
    						//adding it to the file
    			            String newElement = "<p>" + "," + content;
    			            ParaFile.write(newElement);
    			            String line="\n";
    			            ParaFile.write(line);

    			        }
    					catch (IOException e)
    					{
    						System.out.println("Error in writing in File");
    			            e.printStackTrace();
    			        }
    				  }
    		}
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}


    }
	public static void main(String[] args) {
		try {
			//adding headers in file 1
			UrlFile = new FileWriter("Urls.csv");
            String header1 = "Anchor Tags , Text";
            UrlFile.write(header1);
            String line="\n";
            UrlFile.write(line);
            System.out.println("Url File Created");

            //adding headers in file 2
            ParaFile = new FileWriter("Paras.csv");
            String header2 = "Link Text,URL\n";
            ParaFile.write(header2);
            ParaFile.write(line);
            System.out.println("Para File Created");

        }
		catch (IOException e)
		{
			System.out.println("Error in Creating File");
            e.printStackTrace();
        }

		//starting url
		UrlPending.add("http://pec.ac.in");
        UrlVisited.add("http://pec.ac.in");

        //till stack is not empty and we have not reached maximum limit of our
        //dfs crawler
        while (!UrlPending.isEmpty() && depth>0)
        {
        		depth--;
        		String newBase=UrlPending.pop();
        		//first argument is the page which will be crawled and it will only be the base url for all the relative urls present on that page
            	getUrls(newBase,newBase);
        }

		 try {
	         UrlFile.close();
	         ParaFile.close();
	         System.out.println("Files Closed Successfully");
	     } catch (IOException e) {
	         e.printStackTrace();
	     }

	}

}
