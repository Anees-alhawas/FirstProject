
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Storetest {

	WebDriver driver = new ChromeDriver();

	String mywebsite = "https://automationteststore.com/";
	String mysignup = "https://automationteststore.com/index.php?rt=account/create";
	String[] firstname = { "Anees", "Baser", "hammad", "Yswas" };

	String[] lastname = { "ahmad", "naseer", "mohammad", "hawas" };
	String passstrong = "Anees@66#";
	Random rand = new Random();
	int indexfirstname = rand.nextInt(firstname.length);
	int indexlastname = rand.nextInt(lastname.length);

	@BeforeTest
	public void setup() {
		driver.manage().window().maximize();
		driver.get(mywebsite);

	}

	@Test(priority = 1)
	public void signup() throws InterruptedException {
		String userfirst = firstname[indexfirstname];
		String userlast = firstname[indexlastname];
		int randnumber = rand.nextInt(588889);
		String domain = "@gmail.com";
		driver.get(mysignup);
		Thread.sleep(5000);
		WebElement newfirst = driver.findElement(By.id("AccountFrm_firstname"));
		newfirst.sendKeys(userfirst);

		WebElement newalast = driver.findElement(By.id("AccountFrm_lastname"));
		newalast.sendKeys(userlast);

		WebElement newemail = driver.findElement(By.id("AccountFrm_email"));
		newemail.sendKeys(userfirst + userlast + randnumber + domain);

		//////////////////

		WebElement newadddres = driver.findElement(By.id("AccountFrm_address_1"));
		newadddres.sendKeys("Amman-city-city-mall");
		WebElement newcity = driver.findElement(By.id("AccountFrm_city"));
		newcity.sendKeys("capital-city");

		WebElement newcountry = driver.findElement(By.id("AccountFrm_country_id"));

		Select selector = new Select(newcountry);
		int randomcountry = rand.nextInt(1, 140);

		selector.selectByIndex(randomcountry);
		Thread.sleep(5000);
		WebElement newRegion = driver.findElement(By.id("AccountFrm_zone_id"));

		Select selector2 = new Select(newRegion);
		int randomregion = rand.nextInt(1, 3);
		selector2.selectByIndex(randomregion);

		WebElement newcode = driver.findElement(By.id("AccountFrm_postcode"));
		newcode.sendKeys("13310");

		WebElement newloginname = driver.findElement(By.id("AccountFrm_loginname"));
		newloginname.sendKeys(userfirst + userlast + randnumber);

		WebElement newpass = driver.findElement(By.id("AccountFrm_password"));
		newpass.sendKeys(passstrong);

		WebElement confpassw = driver.findElement(By.id("AccountFrm_confirm"));
		confpassw.sendKeys(passstrong);

		WebElement checkboxx = driver.findElement(By.id("AccountFrm_agree"));
		checkboxx.click();
Thread.sleep(5000);
		WebElement clickcontenue = driver.findElement(By.xpath("//button[@title='Continue']"));
		clickcontenue.click();

	}

}
