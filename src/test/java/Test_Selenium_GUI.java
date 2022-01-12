import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;

public class Test_Selenium_GUI {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\imane\\OneDrive\\Documentos\\helseVITA - DWS\\helseVITA-main\\src\\test\\java\\Drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://127.0.0.1:8000/");

        driver.findElement(By.xpath("/html/body/div/div[2]/button[3]")).click();
        driver.findElement(By.xpath("/html/body/div/div[3]/p[2]/a")).click();
        driver.findElement(By.xpath("/html/body/nav/div/div[2]/button")).click();
        driver.findElement(By.xpath("/html/body/main/section/form/center/button[1]")).click();






    }
}