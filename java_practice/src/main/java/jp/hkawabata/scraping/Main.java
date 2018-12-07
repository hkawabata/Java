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

        // 通常のChrome ブラウザ（GUI あり）
        WebDriver guiDriver = new ChromeDriver();
        // ヘッドレスブラウザ（GUI なし）
        WebDriver headLessDriver = new ChromeDriver(new ChromeOptions().setHeadless(true));
        try {
            //app1(guiDriver);
            app2(guiDriver);
        } catch (Exception e) {
            throw new Exception(e.toString(), e);
        } finally {
            guiDriver.quit();
            headLessDriver.quit();
        }
    }

    /**
     * Google トップページを開き、キーワードで検索する
     *
     * @param driver webdriver
     * @throws InterruptedException
     */
    private static void app1(WebDriver driver) throws InterruptedException {
        driver.get("http://www.google.com/xhtml");
        // ユーザが画面を確認できるように数秒間待機
        Thread.sleep(2000);
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("ChromeDriver");
        searchBox.submit();
        // ユーザが画面を確認できるように数秒間待機
        Thread.sleep(2000);
        // ウインドウを閉じる
        driver.close();
    }

    /**
     * Wikipedia トップページを開き、英語版ページに飛び、"Random Article" のリンクを複数回クリックする
     *
     * @param driver
     * @throws InterruptedException
     */
    public static void app2(WebDriver driver) throws InterruptedException {
        driver.get("https://www.wikipedia.org");
        Thread.sleep(2000);
        driver.findElement(By.id("js-link-box-en")).click();
        Thread.sleep(2000);
        for (int i = 0; i < 5; i++) {
            driver.findElement(By.id("n-randompage")).click();
            Thread.sleep(1000);
        }
    }
}
