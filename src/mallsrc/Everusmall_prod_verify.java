package mallsrc;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Everusmall_prod_verify {
	
	WebDriver driver;
	  @BeforeTest
	  public void openBro(){
		     driver= new FirefoxDriver();
		     driver.manage().window().setSize(new Dimension(1376,768));
			 driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
			 String URL="https://staging.everusmall.com/";
			 driver.get(URL);
			 verifyLinkActive("https://staging.everusmall.com/");
	  }
	  
	  @Test
	  public void getbrokenlinks(){
		     List<WebElement> links= driver.findElements(By.tagName("a"));
		     System.out.println("Total no of links" + links.size());
		     for(int i=0;i<links.size();i++){
		    	 WebElement ele1= links.get(i);
		    	 String url= ele1.getAttribute("href");
		    	 verifyLinkActive("https://staging.everusmall.com/");
		     }
	     }
	    public static void verifyLinkActive(String Url){
	    	try{
	    		URL url1=new URL(Url);
	    		HttpURLConnection httpurlconnect= (HttpURLConnection)url1.openConnection();
	    		httpurlconnect.setConnectTimeout(3000);
	    		httpurlconnect.connect();
	    		if(httpurlconnect.getResponseCode()==200){
	    			String Urlworking= Url+" -"+ httpurlconnect.getResponseMessage();
	    		}
	    		if(httpurlconnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND){
	    			System.out.println(Url+" - "+httpurlconnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
	    		}
	    	}catch(Exception e){
	    		System.out.println("Exception is" + e);
	    	}
	    }
	    
	    @AfterTest
	    public void closeBrowser(){
	    	driver.close();
	    }

}
