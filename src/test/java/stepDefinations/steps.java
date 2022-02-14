package stepDefinations;

import io.cucumber.java.Before;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang.RandomStringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.AccountRegistrationPage;
import pageObjects.Home_page;
import pageObjects.MyAccountPage;
import pageObjects.login_page;
import utilities.DataReader;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class steps {
    public WebDriver driver;
    public Home_page hp;
    public login_page lp;
    public MyAccountPage myaccpage;
    public AccountRegistrationPage accRegPage;
    public List<HashMap<String,String>> datamap;// data driven using excel
 public Logger logger;
 ResourceBundle rb;
 String br;

    @Before()
    public void setup()
    {
      logger= LogManager.getLogger(this.getClass());
      rb = ResourceBundle.getBundle("config");
     br= rb.getString("browser");
    }

    @Given("User launch browser")
    public void user_launch_browser() {
        if(br.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if(br.equals("firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            driver= new FirefoxDriver();
        }
        else if(br.equals("edge"))
        {
            WebDriverManager.edgedriver().setup();
            driver= new EdgeDriver();
        }
        logger.info("BRowser opended");
        driver.manage().window().maximize();
        logger.info(" Window maximise");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Given("opens URL {string}")
    public void opens_url(String string) {
        driver.get(string);
        logger.info("Url opened");
    }
    @When("user navigate to MyAccount menu")
    public void user_navigate_to_my_account_menu() {
        hp = new Home_page(driver);
        hp.click_myAccount();
        logger.info("Clicked on my account");
    }
    @When("Click on login")
    public void click_on_login() {
        hp.Click_login();
        logger.info("Clicked on login");
    }
    @When("user enters Email as {string} and Password as {string}")
    public void user_enters_email_as_and_password_as(String string, String string2) {
    lp= new login_page(driver);
    lp.set_Email(string);
    logger.info("Provided email");
    lp.set_password(string2);
    logger.info("Provided password");
    }
    @When("Click on login button")
    public void click_on_login_button() {
    lp.Click_Login_Button();
    logger.info("Clicked on login");
    }
    @Then("User navigates to MyAccount page")
    public void user_navigates_to_my_account_page() {
     boolean target=lp.is_myaccount_exists();
     driver.close();
     if(target)
     {
         Assert.assertTrue(true);
         logger.info("Navigated to registration page");
     }
     else{
         Assert.assertFalse(false);
         logger.info("not navigated to registration page");
     }
    }
    // Data driven test using excel
    @Then("check user navigates to MYAccount page by passing email and password with excel row {string}")
    public void check_user_navigates_to_my_account_page_by_passing_email_and_password_with_excel_row(String rows) {

datamap = DataReader.data(System.getProperty("user.dir")+"\\testData\\Opencart_LoginData.xlsx","Sheet1");
int index= Integer.parseInt(rows)-1;
String email=datamap.get(index).get("username");
String password= datamap.get(index).get("password");
String exp_res= datamap.get(index).get("res");
lp=new login_page(driver);
lp.set_Email(email);
lp.set_password(password);
lp.Click_Login_Button();

try
{
    boolean targetpage= lp.is_myaccount_exists();
    if (exp_res.equals("valid"))
    {
        if(targetpage==true)
        {
             myaccpage= new MyAccountPage(driver);
            myaccpage.clickLogout();
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertFalse(false);
        }
    }

}
catch (Exception e)
{
Assert.assertFalse(false);
}
driver.close();
logger.info("Browser closed");

    }

// Account registration steps
    @When("click on Register")
    public void click_on_register() {
        hp.Click_register();
        logger.info("Clicked on register");

    }
    @Then("user navigates to register account page")
    public void user_navigates_to_register_account_page() {
       accRegPage = new AccountRegistrationPage(driver);
       if(accRegPage.isRegisterAccountPageDiplayed())
       {
           Assert.assertTrue(true);
           logger.info("Account registration page displayed");
       }
       else
       {
           Assert.assertFalse(false);
           logger.info("Account registration not page displayed");
       }

    }
    @When("user provide valid details")
    public void user_provide_valid_details() {
        accRegPage.setFirstName("sairam");
        logger.info("First name entred");
        accRegPage.setLastName("om");
        logger.info("Last name entred");
        accRegPage.setEmail(RandomStringUtils.randomAlphabetic(5)+"@gmail.com");
        logger.info("email entred");
        accRegPage.setTelephone("034987261");
        logger.info("Telephone number entred");
        accRegPage.setPassword("abcxyz");
        logger.info("Password entred");
        accRegPage.setConfirmPassword("abcxyz");
        logger.info("Confirm password entred");
        accRegPage.setPrivacyPolicy();
        logger.info("Accepted privacy policy");

    }
    @Then("user should see {string} message")
    public void user_should_see_message(String expmsg) {
        String cnfmessage= accRegPage.getConfirmationMsg();
        driver.close();
        if(cnfmessage.equals(expmsg))
        {
            Assert.assertTrue(true);
            logger.info("Accout registration succesfull");
        }
        else
        {
            Assert.assertFalse(false);
            logger.info("Accout registration  not succesfull");
        }


    }

    @Then("click on continue")
    public void click_on_continue() {
accRegPage.clickContinue();
logger.info("Clicked on continue");
    }


}
