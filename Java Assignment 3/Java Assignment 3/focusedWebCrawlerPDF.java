import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Stack;
import java.util.HashSet;

public class FocussedWebCrawlerPDF {

	//creating UrlFile that will contains anchor tags and content
	private static FileWriter UrlFilePDF;
    //stack for dfs crawler
    private static Stack<String> UrlPending = new Stack<>();
    //hashset to keep a check so that same url is not visited again and again
    private static HashSet<String> UrlVisited = new HashSet<>();
    //depth of the crawler set to 10
    private static int depth=200;


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
    				  //focussed crawler containing "Faculty" in url
    				  if(completeUrl.contains(".pdf")==true || completeUrl.contains(".PDF")==true )
    		    		{

    					  if(!UrlVisited.contains(completeUrl))
        				  {
        				  UrlPending.add(completeUrl);
        			      UrlVisited.add(completeUrl);
        				  try {
        						//adding it to the file
        			            String newElement = completeUrl + "," + linkText;
        			            UrlFilePDF.write(newElement);
        			            String line="\n";
        			            UrlFilePDF.write(line);

        			        }
        					catch (IOException e)
        					{
        						System.out.println("Error in writing in File");
        			            e.printStackTrace();
        			        }
        				  }
    		    		}


    			  }
    			  //if it is a complete url
    			  else if (linkHref.contains("http://pec.ac.in")== true || linkHref.contains("https://pec.ac.in")== true)
    			  {
    				  if(linkHref.contains(".PDF")==true || linkHref.contains(".pdf")==true)
  		    		{


        				  if(!UrlVisited.contains(linkHref))
        				  {
        				  UrlPending.add(linkHref);
        			      UrlVisited.add(linkHref);
        				  try {
        						//adding it to the file
        			            String newElement = linkHref + "," + linkText;
        			            UrlFilePDF.write(newElement);
        			            String line="\n";
        			            UrlFilePDF.write(line);
        			        }
        					catch (IOException e)
        					{
        						System.out.println("Error in writing in File");
        			            e.printStackTrace();
        			        }
        				  }

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
			UrlFilePDF = new FileWriter("UrlsPDF.csv");
            String header1 = "Anchor Tags , Text";
            UrlFilePDF.write(header1);
            String line="\n";
            UrlFilePDF.write(line);
            System.out.println("Url File Created");



        }
		catch (IOException e)
		{
			System.out.println("Error in Creating File");
            e.printStackTrace();
        }

		//starting url
		UrlPending.add("https://pec.ac.in/");
        UrlVisited.add("https://pec.ac.in/");

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
	         UrlFilePDF.close();
	         System.out.println("File Closed Successfully");
	     } catch (IOException e) {
	         e.printStackTrace();
	     }

	}

}
