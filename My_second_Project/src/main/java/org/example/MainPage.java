package org.example;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertTrue;


public class MainPage {

    private final static String PAGE_URL = "https://qa-scooter.praktikum-services.ru";//открытие страницы
    private final static  By ORDER_BUTTON = By.xpath("/html/body/div/div/div/div[1]/div[2]/button[1]");
    private final static By NAME = By.xpath("//div[@class='Order_Form__17u6u']/div[1]/input");// поле Имя
    private final static By SURNAME = By.xpath("//div[@class='Order_Form__17u6u']/div[2]/input");//поле фамилия
    private final static By ADDRESS= By.xpath("//div[@class='Order_Form__17u6u']/div[3]/input");//поле адрес
    private final static By METRO_STATION =
            By.xpath("//div[@class='Order_Form__17u6u']/div[4]"); //поле станции
    private final  By COOKIE =  By.id("rcc-confirm-button");//куки
    private final By HOME_FAQ = By.cssSelector("div.Home_FAQ__3uVm4");
    private final static By METRO_STATION_CHOICE =
            By.xpath("//div[@class='select-search__select']/ul/li/button/div[2]");// выбора станции, выбор элемента списка для тестового набора
    private final static By PHONE_NUMBER = By.xpath("//div[@class='Order_Form__17u6u']/div[5]/input");//поле номер телефона
    private final static  By CLICK_NEXT_BUTTON =
            By.cssSelector("div.Order_NextButton__1_rCA > button.Button_Middle__1CSJM");
    private final static  By ORDER_DATE = By.xpath("//div[@class='Order_Form__17u6u']/div/div/div/input");
    private final static  By RENT =
            By.xpath("//div[@class='Dropdown-control']");//поле аренды
    private final static  By RENT_CHOICE =
            By.xpath("//div[@class='Dropdown-menu']/div[2]");//выбор срока аренды
    private final static  By CLICK_ORDER_BUTTON =
           By.xpath("//div[@class='Order_Buttons__1xGrp']/button[2]"); //кнопка заказать в форме аренды
    private final   By ORDER_CREATED_SUCCESSFULLY = By.xpath("//*[contains(text(), 'Заказ оформлен')]");

    private final   By BUTTON_ORDER_BELOW = By.cssSelector("div.Home_RoadMap__2tal_ > div.Home_FinishButton__1_cWm > button");
    private final   By  ORDER_CONFIRM_BUTTON = By.xpath("//div[@class='Order_Modal__YZ-d3']/div[2]/button[2]"); //модальное окно подтверждения заказа, кнопка "Да"
    private final WebDriver driver;
    public MainPage(WebDriver driver){
        this.driver=driver;
    }

   public void clickOrderButton(){
        driver.findElement(ORDER_BUTTON).click();
}

    public void name(String name){
        driver.findElement(NAME).sendKeys(name);
    }

    public void surname(String surname){
        driver.findElement(SURNAME).sendKeys(surname);
    }

    public void address(String address){
        driver.findElement(ADDRESS).sendKeys(address);
    }

    public MainPage metroStation(){
        driver.findElement(METRO_STATION).click();
        return this;
    }

    public MainPage metroStationCHOICE (){
        driver.findElement(METRO_STATION_CHOICE).click();
     return this;
         }

    public void phoneNumber(String phoneNumber){
        driver.findElement(PHONE_NUMBER).sendKeys(phoneNumber);
    }

    public MainPage clickNextButton(){
        driver.findElement(CLICK_NEXT_BUTTON).click();
        return this;
    }
public MainPage date (){
    // Заполнение даты при оформлении заказа
    driver.findElement(ORDER_DATE).click();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy"); // готовим нужный форматтер
    String date = LocalDate.now().plusDays(2).format(formatter); // получаем текущую дату, прибавляем к ней 2 дня и форматируем
    driver.findElement(ORDER_DATE).sendKeys(date);
    driver.findElement(ORDER_DATE).sendKeys(Keys.ENTER);
    return this;
  }

public MainPage rent (){
    driver.findElement(RENT).click();
    driver.findElement(RENT_CHOICE).click();
    return this;
}
    public boolean isOrderCreated(){
        try {
            return driver.findElement(ORDER_CREATED_SUCCESSFULLY).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isOrderButton(){
        return (driver.findElement(ORDER_BUTTON) != null);
    }

    public void clickOrderButtonInRentPage(){
        driver.findElement(CLICK_ORDER_BUTTON).click();
         }

    public void clicksNextOrderButton(){
        scrollToOrderButton();
        driver.findElement(BUTTON_ORDER_BELOW).click();

           }

       private void scrollToOrderButton() {
        new WebDriverWait(driver, (5)).until(ExpectedConditions.visibilityOfElementLocated(BUTTON_ORDER_BELOW));
                WebElement elementOrderButton = driver.findElement(BUTTON_ORDER_BELOW);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementOrderButton);
    }

    public MainPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    public void clickCloseCookies() {
        driver.findElement(COOKIE).click();
    }

    public void clickOnQuestion(int index) {
        By buttonText = By.id("accordion__heading-" + index);
        driver.findElement(buttonText).click();
    }

public void scrollQuestions(){
    new WebDriverWait(driver, (5)).until(ExpectedConditions.visibilityOfElementLocated(HOME_FAQ));
    WebElement elementFAQ = driver.findElement(HOME_FAQ);
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementFAQ);
}

    public void clickButtonHeader(){
        assertTrue("Кнопка не найдена", isOrderButton());
        clickOrderButton();
    }

    public void orderConfirmButton() {
        driver.findElement(ORDER_CONFIRM_BUTTON).click();
    }

    public boolean isAnswerVisible(String constantText, int index) {
        By panelAnswer = By.id("accordion__panel-" + index);
        return driver.findElement(panelAnswer).isDisplayed();
    }
}