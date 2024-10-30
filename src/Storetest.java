
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Storetest {

	WebDriver driver = new ChromeDriver();

	String mywebsite = "https://automationteststore.com/";
	String mysignup = "https://automationteststore.com/index.php?rt=account/create";
	String[] firstname = { "Anees", "younis", "rami", "bader" };

	String[] lastname = { "ahmad", "raeed", "mohammad", "hawas" };
	String passstrong = "Anees@66#";
	Random rand = new Random();

	int indexfirstname = rand.nextInt(firstname.length);
	int indexlastname = rand.nextInt(lastname.length);
	String globalname = "";
	String globallogin = "";

	@BeforeTest
	public void setup() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		driver.manage().window().maximize();

		driver.get(mywebsite);

	}

	@Test(priority = 1)
	public void signup() throws InterruptedException {
		String userfirst = firstname[indexfirstname];
		String userlast = lastname[indexlastname];//
		int randnumber = rand.nextInt(588889);
		String domain = "@gmail.com";
		driver.get(mysignup);

		WebElement newfirst = driver.findElement(By.id("AccountFrm_firstname"));
		newfirst.sendKeys(userfirst);
		globalname = userfirst;

		WebElement newalast = driver.findElement(By.id("AccountFrm_lastname"));
		newalast.sendKeys(userlast);

		WebElement newemail = driver.findElement(By.id("AccountFrm_email"));
		newemail.sendKeys(userfirst + userlast + randnumber + domain);

		WebElement newadddres = driver.findElement(By.id("AccountFrm_address_1"));
		newadddres.sendKeys("Amman-city-city-mall");
		WebElement newcity = driver.findElement(By.id("AccountFrm_city"));
		newcity.sendKeys("capital-city");

		WebElement newcountry = driver.findElement(By.id("AccountFrm_country_id"));

		Select selector = new Select(newcountry);
		int randomcountry = rand.nextInt(1, 140);

		selector.selectByIndex(randomcountry);
		Thread.sleep(4000);
		WebElement newRegion = driver.findElement(By.id("AccountFrm_zone_id"));

		Select selector2 = new Select(newRegion);
		int randomregion = rand.nextInt(1, 3);
		selector2.selectByIndex(randomregion);

		WebElement newcode = driver.findElement(By.id("AccountFrm_postcode"));
		newcode.sendKeys("13310");

		WebElement newloginname = driver.findElement(By.id("AccountFrm_loginname"));
		newloginname.sendKeys(userfirst + userlast + randnumber);
		globallogin = userfirst + userlast + randnumber;
		WebElement newpass = driver.findElement(By.id("AccountFrm_password"));
		newpass.sendKeys(passstrong);

		WebElement confpassw = driver.findElement(By.id("AccountFrm_confirm"));
		confpassw.sendKeys(passstrong);

		WebElement checkboxx = driver.findElement(By.id("AccountFrm_agree"));
		checkboxx.click();

		WebElement clickcontenue = driver.findElement(By.xpath("//button[@title='Continue']"));
		clickcontenue.click();

	}

	@Test(priority = 2)
	public void logout() throws InterruptedException {

		WebElement howver = driver.findElement(By.id("customernav"));
		Actions action = new Actions(driver);
		action.moveToElement(howver).perform();
		Thread.sleep(9000);

		driver.findElement(By.linkText("Not " + globalname + "? Logoff")).click();
	}

	@Test(priority = 3)
	public void login() throws InterruptedException {

		driver.findElement(By.linkText("Login or register")).click();
		Thread.sleep(4000);
		WebElement logname = driver.findElement(By.id("loginFrm_loginname"));
		logname.sendKeys(globallogin);
		Thread.sleep(4000);
		WebElement passname = driver.findElement(By.id("loginFrm_password"));
		passname.sendKeys(passstrong);
		Thread.sleep(4000);
		WebElement clicklogin = driver.findElement(By.xpath("//button[@title='Login']"));
		clicklogin.click();

	}

	@Test(priority = 4)
	public void selectitems() throws InterruptedException {
		String[] categorys = { "https://automationteststore.com/index.php?rt=product/category&path=68",
				" https://automationteststore.com/index.php?rt=product/category&path=36",
				"https://automationteststore.com/index.php?rt=product/category&path=43",
				"https://automationteststore.com/index.php?rt=product/category&path=49",
				"https://automationteststore.com/index.php?rt=product/category&path=58",
				"https://automationteststore.com/index.php?rt=product/category&path=52",
				"https://automationteststore.com/index.php?rt=product/category&path=65" };

		int randomindex = rand.nextInt(categorys.length);

		driver.get(categorys[randomindex]);
		// define for the webelement which is a UL tag
		Thread.sleep(4000);
		// ابحث عن UL tag
		WebElement listofitems = driver.findElement(By.cssSelector(".thumbnails.row"));
		// بشكل عشوائي بناءاً على الرقم الاجمالي liاعطيني حجم ال
		int totalnumberitems = listofitems.findElements(By.tagName("li")).size();
		int randomitems = rand.nextInt(totalnumberitems);
		// اكبس عليه بشكل عشوائي
		listofitems.findElements(By.tagName("li")).get(randomitems).click();

		// حطيت جميع العناصر بكيس بناءً على اسم الكلاس الكونتينر الرئيسي
		WebElement container = driver.findElement(By.cssSelector(".thumbnails.grid.row.list-inline"));
		// اخذنا عدد العناصر بناءًعلى الكلاس المشترك بينهم لكل عنصر
		int randomitemss = container.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).size();
		// بشكل عشوائي بناءاً على الرقم الاجمالي اعطيني اندكس

		int randomproduct = rand.nextInt(randomitemss);
		// اكبسلي على العنصر بشكل عشوائي بناءاً على الرقم الاجمالي
		container.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).get(randomproduct).click();
		///////
		// هنا لدي علامة UL التي تحتوي إما على إضافة إلى سلة التسوق أو نفاد المخزون
		WebElement ullist = driver.findElement(By.className("productpagecart"));

		// داخلul الذي وجدته في السطر السابق من التعليمات البرمجية، أبحث عن العلامة التي
		// تشير الى -
		// -(a) tag if the item in the stock- and -span tag if the item out of the
		// stock-
		int lilist = ullist.findElements(By.tagName("li")).get(0).findElements(By.tagName("a")).size();
		// this liItem will give me either 0 or 1 ( 0 if the item out of the stock
		// so it will go back to the home page and print a message says that the item
		// out of the stock ,
		// if it gives 1 that means the item inside the stock and i can press on the add
		// to cart button
		if (lilist > 0) {
			driver.findElement(By.className("cart")).click();

			String actual = driver.findElement(By.className("heading1")).getText();
			String expected = "SHOPPING CART";
			Assert.assertEquals(actual, expected);
			boolean actual2 = driver.findElement(By.id("cart_checkout1")).isDisplayed();

			boolean expected2 = true;
			Assert.assertEquals(actual2, expected2);
		} else {

			driver.get(mywebsite);

			System.out.println("sorry no items ");

			String actualresult = driver.getCurrentUrl();
			String expectedresult = "https://automationteststore.com/";
			Assert.assertEquals(actualresult, expectedresult);

		}

	}

}
