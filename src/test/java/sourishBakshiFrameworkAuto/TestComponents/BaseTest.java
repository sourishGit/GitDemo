package sourishBakshiFrameworkAuto.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.FileUtils;

import sourishBakshiFrameworkAuto.pageobjects.LandingPage;



public class BaseTest {
	
	public WebDriver driver;
	String urlString = "https://rahulshettyacademy.com/client";
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//java//sourishBakshiFrameworkAuto//Resources//GlobalData.properties");
		prop.load(fis);
		
		String browser = System.getProperty("browser")!= null ? System.getProperty("browser") :prop.getProperty("browser");
		//String browser = prop.getProperty("browser");
		
		if(browser.contains("chrome"))
		{	
			ChromeOptions options = new ChromeOptions();
			if(browser.contains("headless"))
			{
				options.addArguments("headless");
			}
			System.setProperty("webdriver.chrome.driver", "C:/Users/SOURISH/eclipse-workspace/SeleniumFrameworkDesignSb/driver/chromedriver.exe");
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
			
			
		} else if(browser.equalsIgnoreCase("firefox"))	{
			System.setProperty("webdriver.gecko.driver", "C:/Users/SOURISH/eclipse-workspace/SeleniumFrameworkDesignSb/driver/geckodriver.exe");
			driver = new FirefoxDriver();
			
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		return driver;
		
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
		
	}
	
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"//reports//" + testCaseName + ".png"));
		return System.getProperty("user.dir")+"//reports//" + testCaseName + ".png";
	}
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.navigateToUrl(urlString);
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.quit();
	}
}
