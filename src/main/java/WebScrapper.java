import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class WebScrapper {

    private static  String url="https://scrapeme.live/shop/?orderby=rating";
    private static String fileName="ScrapperInfo.csv";

    public static void main(String args[]){
        try{
            Document document= Jsoup.connect(url).get();

            //System.out.println(document.outerHtml());  //DISPLAYS HTML CODE

            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(fileName));
            bufferedWriter.write("PRODUCT,PRICE");
            bufferedWriter.newLine();
            for(Element element:document.select("li.product")) {
                String products = element.select("h2.woocommerce-loop-product__title").text();
                String prices = element.select("span.woocommerce-Price-amount.amount").text();

                /*for these CSS query lines (above 3 lines)go to the url
                right click on chosen field+inspect and get the class/div/table name */

                System.out.println(products+" : "+ prices);
                bufferedWriter.write(products+","+ prices); //"," is used for Excel friendly work.
                bufferedWriter.newLine();
                }
            bufferedWriter.close();
            System.out.println("csv file created successfully and product details added!");
            }
         catch (Exception e) {
             System.out.println("Error occurred while web Scrapping "+e.getMessage());
             e.printStackTrace();
        }
        finally {
            System.out.println("Finally block is always executed :)");

            /*note:if BufferedWriter has to be accessed in the finally block declare it outside try
            block (global scope) like->   BufferedWriter bufferedWriter=Null;*/

        }
    }
}