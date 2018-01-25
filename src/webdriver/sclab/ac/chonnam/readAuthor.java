package webdriver.sclab.ac.chonnam;

import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.firefox.FirefoxDriver;

public class readAuthor {
	String id;
	String author;
	String Clutch_Power = "0";
	String _1k_Club = "0";
	String Socializer = "0";
	String Trailblazer = "0";
	String _5k_Club = "0";
	String _10k_Club = "0";
	String Autobiographer = "0";
	String Pioneer = "0";
	String Luminary = "0";
	
	

	public readAuthor() {

		Clutch_Power = "0";
		_10k_Club = "0";
		_1k_Club = "0";
		_5k_Club = "0";
		Autobiographer = "0";
		Pioneer = "0";
		Luminary = "0";
		Trailblazer = "0";
		Socializer = "0";
	}

	public readAuthor(String name, String id) {

		// Create a new instance of the Firefox driver
		System.setProperty("webdriver.firefox.profile", "default");
		FirefoxDriver driver = new FirefoxDriver();
		// Dimension n = new Dimension(20,40);
		// driver.manage().window().setSize(n);
		driver.manage().window().setPosition(new Point(-2000, 0));
		// Wait For Page To Load
		// Put a Implicit wait, this means that any search for elements on the
		// page
		// could take the time the implicit wait is set for before throwing
		// exception
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		// Navigate to URL
		driver.get("https://ideas.lego.com/profile/" + name + "/activity");

		// Enter UserName
		driver.findElement(By.id("fieldUsername")).sendKeys("quanap5");
		// Enter Password
		driver.findElement(By.id("fieldPassword")).sendKeys("Chonn@m911005");
		// Wait For Page To Load

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		// Click on 'Sign In' button
		driver.findElement(By.id("buttonSubmitLogin")).click();
		if (name.contains(" ")) {
			driver.navigate().back();
			//driver.findElement(By.id("fieldUsername")).sendKeys("quanap5");
			// Enter Password
			driver.findElement(By.id("fieldPassword")).sendKeys("Chonn@m911005");
			// Wait For Page To Load
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.id("buttonSubmitLogin")).click();
		}
		// Get the page content:

		Document doc = Jsoup.parse(driver.getPageSource());
		// System.out.println(doc.toString());
		Elements secs = doc.select("section.header-user-achievement");
		// System.out.println(secs.toString());
		this.author = name;
		this.id = id;
		for (final Element sec : secs) {
			final Elements heading2s = sec.select("h2");
			for (final Element heading2 : heading2s) {
				System.out.print(heading2.text() + ": ");
				System.out.println(heading2.select("span").text());
				this.Clutch_Power = heading2.select("span").text();
			}

			final Elements lis = sec.select("li");
			for (final Element li : lis) {
				if (li.attr("title").equals("10k Club")) {
					System.out.print(li.attr("title").toString() + ": ");
					System.out.println(li.select("span").text());
					this._10k_Club = li.select("span").text();
					if (_10k_Club.equals(null)||_10k_Club.equals("")) { this._10k_Club="0";}
				}

				if (li.attr("title").equals("1k Club")) {
					System.out.print(li.attr("title").toString() + ": ");
					System.out.println(li.select("span").text());
					this._1k_Club = li.select("span").text();
					if (_1k_Club.equals(null)||_1k_Club.equals("")) { this._1k_Club="0";}
				}

				if (li.attr("title").equals("5k Club")) {
					System.out.print(li.attr("title").toString() + ": ");
					System.out.println(li.select("span").text());
					this._5k_Club = li.select("span").text();
					if (_5k_Club.equals(null)||_5k_Club.equals("")) { this._5k_Club="0";}
				}

				if (li.attr("title").equals("Pioneer")) {
					System.out.print(li.attr("title").toString() + ": ");
					System.out.println(li.select("span").text());
					this.Pioneer = li.select("span").text();
					if (Pioneer.equals(null)||Pioneer.equals("")) { this.Pioneer="0";}
				}

				if (li.attr("title").equals("Luminary")) {
					System.out.print(li.attr("title").toString() + ": ");
					System.out.println(li.select("span").text());
					this.Luminary = li.select("span").text();
					if (Luminary.equals(null)||Luminary.equals("")) { this.Luminary="0";}
				}

				if (li.attr("title").equals("Autobiographer")) {
					System.out.print(li.attr("title").toString() + ": ");
					System.out.println(li.select("span").text());
					this.Autobiographer = li.select("span").text();
					if (Autobiographer.equals(null)||Autobiographer.equals("")) { this.Autobiographer="0";}
				}

				if (li.attr("title").equals("Trailblazer")) {
					System.out.print(li.attr("title").toString() + ": ");
					System.out.println(li.select("span").text());
					this.Trailblazer = li.select("span").text();
					if (Trailblazer.equals(null)||Trailblazer.equals("")) { this.Trailblazer="0";}
				}

				if (li.attr("title").equals("Socializer")) {
					System.out.print(li.attr("title").toString() + ": ");
					System.out.println(li.select("span").text());
					this.Socializer = li.select("span").text();
					if (Socializer.equals(null)||Socializer.equals("")) { this.Socializer="0";}
				}

			}
		}

		// Close the browser.
		driver.close();
	}
}