import org.example.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.example.constantText.*;


@RunWith(Parameterized.class)
public class WebTest {
    WebDriver driver;

        private final String constantText;
        private final int index;
        public WebTest(String constantText, int index) {
            this.constantText = constantText;
            this.index = index;
       }
        @Parameterized.Parameters
        public static Object[][] getParameters() {
            return new Object[][]{
                    {ANSWER_TEXT_HOW_MUCH_AND_PAYMENT, 0},
                    {ANSWER_TEXT_ORDERING_SEVERAL_SCOOTERS, 1},
                    {ANSWER_TEXT_RENTAL_TIME, 2},
                    {ANSWER_TEXT_ORDER_TODAY, 3},
                    {ANSWER_TEXT_ORDER_EXTENSION_AND_RETURN, 4},
                    {ANSWER_TEXT_SCOOTER_CHARGING, 5},
                    {ANSWER_TEXT_ORDER_CANCELLATION, 6},
                    {ANSWER_TEXT_DELIVERY_OUTSIDE_MOSCOW, 7},
            };
        }
    @Before
    public void setUp(){
      driver = new ChromeDriver();
       // driver = new FirefoxDriver();
    }

    //Тест для выпадающего списка в разделе «Вопросы о важном».
    @Test
    public void checkClickArrow_openСorrespondingText(){
       MainPage mainPage = new MainPage(driver);
        mainPage.open();
        new WebDriverWait(driver, 5);
        mainPage.clickCloseCookies();
        mainPage.scrollQuestions();
        mainPage.clickOnQuestion(index);
        boolean actual = mainPage.isAnswerVisible(constantText, index);
        Assert.assertTrue(actual);
    }

    @After
    public void cleanUp() {
        driver.quit();//Закрыть браузер
    }
}
