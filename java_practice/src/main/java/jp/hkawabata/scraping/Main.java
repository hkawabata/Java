package jp.hkawabata.scraping;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Selenium による Javascript を含むウェブページのレンダリングプログラム
 * webdriver には chromedriver を用いる
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // 必要に応じて chromedriver のパスを指定（chromedriver は別でインストールが必要）
        // 以下は Mac の例（デフォルトなのか、指定しなくても動いた）
        //System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        // 通常の Chrome ブラウザ
        WebDriver driver = new ChromeDriver();
        // ヘッドレスブラウザ（ウインドウが開かない）
        WebDriver headLessDriver = new ChromeDriver(new ChromeOptions().setHeadless(true));
        try {
            app1(driver);
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    /**
     * Google トップページを開き、キーワードで検索する
     *
     * @param driver
     * @throws InterruptedException
     */
    private static void app1(WebDriver driver) throws InterruptedException {
        driver.get("http://www.google.com/xhtml");
        Thread.sleep(3000);  // ユーザが画面を眺めるための時間
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("ChromeDriver");
        searchBox.submit();
        Thread.sleep(3000);  // ユーザが画面を眺めるための時間
        driver.quit();
    }
}
