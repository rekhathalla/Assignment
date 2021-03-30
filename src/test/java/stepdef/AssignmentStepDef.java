package stepdef;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AssignmentStepDef {

    WebDriver driver;

    @After
    public void afterScenario() {
      driver.close();
    }

    @Given("^user navigates to homepage$")
    public void user_navigates_to_homepage() throws Throwable {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://www.kfc.co.uk/");
        driver.manage().window().maximize();
        WebElement acceptCookies = new WebDriverWait(driver, 50)
                .until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")));
        acceptCookies.click();
    }

    @Given("^user navigates to menu item \"([^\"]*)\"$")
    public void user_navigates_to_menu_item(String menuItem) throws Throwable {
        driver.findElement(By.linkText(menuItem.toUpperCase())).click();

    }

    @When("^user searches for \"([^\"]*)\" location$")
    public void user_searches_for_location(String location) throws Throwable {
        driver.findElement(By.cssSelector("input.sc-gojNiO.jbCbKi")).sendKeys(location);
        driver.findElement(By.xpath(("//button[contains(@label,'search')]"))).click();
//        driver.findElement(By.cssSelector("button.sc-TOsTZ.gvMfeK sc-kgAjT.bvGaiC.remove-margin")).click();
    }


    @Then("^\"([^\"]*)\" location page is displayed$")
    public void location_page_is_displayed(String actualLocation) throws Throwable {
        String expectedLocation = driver.findElement(By.cssSelector(".sc-bJHhxl.fAoWvH")).getText().toUpperCase();
        Assert.assertTrue(expectedLocation.contains(actualLocation.toUpperCase()));
    }


    @When("^user chooses option for order collection$")
    public void user_chooses_option_for_order_collection() throws Throwable {
        driver.findElement(By.cssSelector("button.sc-TOsTZ.bcNAlW.sc-ksYbfQ.kAXbBV")).click();
    }

    @Then("^online order page is displayed for \"([^\"]*)\" location$")
    public void online_order_page_is_displayed_for_location(String arg1) throws Throwable {
        Assert.assertEquals(driver.getTitle().toString(),"KFC");
    }

    @When("^user navigates to Box Meals menu item$")
    public void user_navigates_to_Box_Meals_menu_item() throws Throwable {
        driver.findElement(By.cssSelector("h3.sc-jWojfa.bPDePC")).click();
    }

    @When("^user adds \"([^\"]*)\" product to the basket$")
    public void user_adds_product_to_the_basket(String product) throws Throwable {
        List<WebElement> allElements = driver.findElements(By.xpath("//span[contains(@class, 'sc-cvbbAY dwPYoS')]/h3"));
        Iterator<WebElement> iter = allElements.iterator();

        while (iter.hasNext()) {
            WebElement item = iter.next();
            if (item.getText().equals(product.toUpperCase())){
                item.click();
                break;
            }
        }
    }

    @Then("^\"([^\"]*)\" button is displayed to the user$")
    public void button_is_displayed(String buttonName) throws Throwable {
        WebElement headsUp = new WebDriverWait(driver, 50)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@label,'Continue with my order')]")));
        Assert.assertEquals( (driver.findElement(By.cssSelector(".sc-kgAjT.lnwZbP.sc-ksYbfQ.cRzxQK")).getText().toUpperCase()) , buttonName.toUpperCase());
        headsUp.click();
    }

    @Then("^when user adds to the order and views the order$")
    public void when_user_adds_to_the_order_and_views_the_order() throws Throwable {
        driver.findElement(By.xpath(("//button[contains(@label,'Add to my order')]"))).click();
        driver.findElement(By.xpath(("//button[contains(@label,'View Order')]"))).click();
    }

    @Then("^\"([^\"]*)\" is selected under the order$")
    public void is_selected_under_the_order(String actualProductName) throws Throwable {
        String expectedProductName = driver.findElement(By.cssSelector(".sc-fjdhpX.iOsOBM.sc-daURTG.kQbvty")).getText();
        Assert.assertEquals(expectedProductName, actualProductName);
    }


}
