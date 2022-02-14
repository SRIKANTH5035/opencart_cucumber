package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class login_page {

    public WebDriver driver;
    public login_page(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//input[@id='input-email']")
    WebElement txt_email_address;
    @FindBy(xpath="//input[@id='input-password']")
    WebElement txt_Password;
    @FindBy(xpath="//input[@value='Login']")
    WebElement  btn_login;
    @FindBy(xpath="//h2[text()='My Account']")
    WebElement msgHeading;

    public void set_Email(String email)
    {
        txt_email_address.sendKeys(email);
    }
     public void set_password(String pswd){
        txt_Password.sendKeys(pswd);
     }
     public void Click_Login_Button(){
        btn_login.click();
     }

     public boolean is_myaccount_exists()
     {
         try
         {
             return(msgHeading.isDisplayed());
         }
         catch (Exception e){
             return false;
         }
     }

}
