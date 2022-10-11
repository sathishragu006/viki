package com.makemytrip.pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.makemytrip.baseclass.BaseClass;
import com.makemytrip.util.UtilFunctions;

public class HomePage extends BaseClass {

	public static WebDriver driver;
	public static JavascriptExecutor js;
	public static WebDriverWait wait;

	@FindBy(xpath = "//a[contains(@href,'www.makemytrip.com/flights')]/span[contains(@class,'chFlights')]")
	private WebElement flightsMenu;

	@FindBy(xpath = "//li[contains(text(),'Round Trip')]")
	private WebElement roundTripMenu;

	@FindBy(xpath = "//span[text()='From']")
	private WebElement fromCityDrop;

	@FindBy(xpath = "//li[@class='react-autosuggest__suggestion react-autosuggest__suggestion--first']")
	private WebElement firstOption;

	@FindBy(xpath = "//input[@placeholder='From']")
	private WebElement searchFromCity;

	@FindBy(xpath = "//input[@placeholder='To']")
	private WebElement searchToCity;

	@FindBy(xpath = "//div[contains(@class,'dateFiled')][1]")
	private WebElement departureDrop;

	@FindBy(xpath = "//div[contains(@class,'dateFiled')][2]")
	private WebElement returnDrop;

	String departureDate = "//div[@aria-label='%replace%' and @aria-disabled='false']";
	String returnDate = "//div[@aria-label='%replace%' and @aria-disabled='false']";

	@FindBy(xpath = "//a[contains(@class,'widgetSearchBtn') and text()='Search']")
	private WebElement searchBtn;

	public HomePage(WebDriver ldriver) {
		this.driver = ldriver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getFlightsMenu() {
		return flightsMenu;
	}

	public WebElement getRoundTripMenu() {
		return roundTripMenu;
	}

	public WebElement getFromCityDrop() {
		return fromCityDrop;
	}

	public WebElement getFirstOption() {
		return firstOption;
	}

	public WebElement getSearchFromCity() {
		return searchFromCity;
	}

	public WebElement getSearchToCity() {
		return searchToCity;
	}

	public WebElement getDepartureDrop() {
		return departureDrop;
	}

	public WebElement getReturnDrop() {
		return returnDrop;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public void selectFlightsMenu() throws InterruptedException {
		 js = (JavascriptExecutor)driver;
		Thread.sleep(3000);
		js.executeScript("arguments[0].click();", flightsMenu);
	}

	public void selectRoundtripMenu() {
		js.executeScript("arguments[0].click();", roundTripMenu);
	}

	public void enterDepartureCity(String city) throws InterruptedException {
		js.executeScript("arguments[0].click();", fromCityDrop);
		
		searchFromCity.sendKeys(city);
		UtilFunctions.ExpWaitForWebelement(firstOption);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", firstOption);
	}

	public void enterReturnCity(String city) throws InterruptedException {
		searchToCity.sendKeys(city);
		UtilFunctions.ExpWaitForWebelement(firstOption);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", firstOption);
	}

	public void enterDepartureDate() throws Exception {
		js.executeScript("arguments[0].click();", departureDrop);
		UtilFunctions date = UtilFunctions.getCurrentAndReturnDates();
		
		WebElement deptDate=driver.findElement(UtilFunctions.customXpath(departureDate, date.departureDate));
		UtilFunctions.ExpWaitForWebelement(deptDate);
		js.executeScript("arguments[0].click();",deptDate );
	}

	public void enterReturnDate() throws Exception {
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();",returnDrop );
		UtilFunctions date = UtilFunctions.getCurrentAndReturnDates();
		js.executeScript("arguments[0].click();",driver.findElement(UtilFunctions.customXpath(returnDate, date.returnDate)) );
	}

	/*public FlightInfoPage search() {
		searchBtn.click();
		driver.manage().deleteAllCookies();
		return new FlightInfoPage();
	}*/

}
