package mallsrc;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Onclick_link {
	
	WebDriver driver;
	
	public static int invalidLink;
    String currentLink;
    String temp;
    @BeforeTest
    public void openUrl() {
    	File file = new File("E:\\ChromeDriver_3.4\\chromedriver.exe");
    	System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
    	// Launch The Browser
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        // Enter URL
        driver.get("https://uat2.everus.org/");
    }
    @Test
    public void getbrokenLinks() throws IOException {
    	
        Set<String> s1= new HashSet<>();
        s1.add("tangi");
        driver.findElement(By.tagName("a")).isDisplayed();

        // Get all the links URL
        List<WebElement> ele = driver.findElements(By.tagName("a"));
        System.out.println("size:" + ele.size());
        boolean isValid = false;
        for (int i = 0; i < ele.size(); i++) {

            isValid = getResponseCode(ele.get(i).getAttribute("href"));
            if (isValid) {
                System.out.println("ValidLinks:" + ele.get(i).getAttribute("href"));
                driver.get(ele.get(i).getAttribute("href"));
                List<WebElement> ele1 = driver.findElements(By.tagName("a"));
                System.out.println("InsideSize:" + ele1.size());
                for (int j=0; j<ele1.size(); j++){
                    isValid = getResponseCode(ele.get(j).getAttribute("href"));
                    if (isValid) {
                        System.out.println("ValidLinks:" + ele.get(j).getAttribute("href"));
                    }
                    else{
                        System.out.println("InvalidLinks:"+ ele.get(j).getAttribute("href"));
                    }
                }

                } else {
                    System.out.println("InvalidLinks:"
                            + ele.get(i).getAttribute("href"));
                }

            }
        }
    

    public static boolean getResponseCode(String urlString) {
        boolean isValid = false;
        try {
            URL u = new URL(urlString);
            HttpURLConnection h = (HttpURLConnection) u.openConnection();
            h.setRequestMethod("GET");
            h.connect();
            System.out.println(h.getResponseCode());
            if (h.getResponseCode() != 404) {
                isValid = true;
            }
        } catch (Exception e) {

        }
        return isValid;
    }
    @AfterTest
    public void closeBrowser() {
    	driver.close();
    }
    

}



