package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home_page {

    public WebDriver driver;

public Home_page(WebDriver driver)
{
    this.driver=driver;
    PageFactory.initElements(driver,this);
}
@FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement lnk_myAccount;
@FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement lnk_register;
@FindBy(xpath="//a[normalize-space()='Login']")
    WebElement lnk_login;
public void click_myAccount()
{
    lnk_myAccount.click();
}
public void Click_register(){
    lnk_register.click();
}
public void Click_login(){
    lnk_login.click();
}
}
