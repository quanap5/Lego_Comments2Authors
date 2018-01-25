package comments.sclab.ac.chonnam;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import webdriver.sclab.ac.chonnam.readAuthor;

public class Lego_Comments {
	public static void main(String[] args) throws MalformedURLException, IOException {
	//	String project = "879f2041-22ea-4b06-9e34-ed074c4ce83a";
		String project = "c2d8ff8f-6a92-4ef3-9c32-bb40605af6be";//https://ideas.lego.com/projects/98263/comments
		String limit = "101";
		String url1 = "https://ideas.lego.com/comments/project/" + project
				+ "/comments?order=oldest&from=min&style=children&max_id=&min_id=&limit=" + limit
				+ "&id=comments-query-/comments" + "/project/dd25187b-2765-4cdb-826e-b21eb9fee67f/comments";
				// String url1=
				// "https://ideas.lego.com/comments/project/7a204071-7d33-4cfd-9306-15f1cdbfb798/comments?order=oldest&from=min&style=children&max_id=&min_id=&limit=100&id=comments-query-"
				// +
				// "/comments/project/7a204071-7d33-4cfd-9306-15f1cdbfb798/comments&_=1451983441123";

		// String
		// url1="https://ideas.lego.com/comments/project/7a204071-7d33-4cfd-9306-15f1cdbfb798/comments?order=oldest&from=min&style=children&limit=2&id=comments-query-/comments"
		// + "/project/7a204071-7d33-4cfd-9306-15f1cdbfb798/comments";
		//

		String next_index = "";
		int reply = 0;
		int comment = 0;
		ArrayList<MyComments> comments_Data = new ArrayList<MyComments>();

		while (true) {

			String url = url1 + next_index;
			String json = IOUtils.toString(new URL(url));
			Gson gson = new GsonBuilder().create();
			MyPojo myPojo = gson.fromJson(json, MyPojo.class);

			if (myPojo.getResults().length == 0) {
				break;
			}

			for (int i = 0; i < myPojo.getResults().length; i++) {
				MyComments myData1 = new MyComments(myPojo.getResults()[i]);
				comments_Data.add(myData1);

				System.out.println("======" + comment + "==========");
				System.out.print("Author: ");
				System.out.println(myPojo.getResults()[i].getAuthor().getAlias());
				System.out.print("Author_id: ");
				System.out.println(myPojo.getResults()[i].getAuthor().getId());
				System.out.print("Comment: ");
				System.out.println(myPojo.getResults()[i].getComment());
				System.out.print("Created_at: ");
				System.out.println(myPojo.getResults()[i].getCreated_at());
				System.out.print("User_vote: ");
				System.out.println(myPojo.getResults()[i].getUser_vote().getCount());
				System.out.print("Replies_count: ");
				System.out.println(myPojo.getResults()[i].getReplies_count());
				reply = reply + Integer.parseInt(myPojo.getResults()[i].getReplies_count());
				comment = comment + 1;
				System.out.print(myPojo.getResults()[i].getUser_vote().getDistribution().get_minus1());
				System.out.print(myPojo.getResults()[i].getUser_vote().getDistribution().get_zero0());
				System.out.println(myPojo.getResults()[i].getUser_vote().getDistribution().get_plus1());
			}

			next_index = "&min_id=" + myPojo.getResults()[myPojo.getResults().length - 1].getId();
			System.out.println("Reply + Comment=" + reply + " + " + comment + " = " + (reply + comment));
		}

		Gson gson1 = new Gson();
		String json1 = gson1.toJson(comments_Data);

		System.out.println(json1);
		// Date and current time
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
		Calendar cal = Calendar.getInstance();
		String mynamefile = "CommentsLego"+project + dateFormat.format(cal.getTime()) + ".txt";
		// String
		// mynamefileTotal="InfluenceTotal_quirky"+dateFormat.format(cal.getTime())+".txt";
		try {
			FileWriter writer = new FileWriter(mynamefile);
			writer.write(json1);
			writer.close();
			System.out.println("Write file: Success");
			System.out.println("COMMENTs: ");

		} catch (Exception e) {
			// TODO: handle exception

		}
// Crawl profile of authors
		ArrayList<readAuthor> commentAuthor=new ArrayList<readAuthor>();
		for (int i = 287; i <comments_Data.size(); i++) {
		System.out.println("======" + i + "==========");	
		readAuthor eachAuthor = new readAuthor(comments_Data.get(i).getAuthor(),comments_Data.get(i).getId());
		commentAuthor.add(eachAuthor);
		}
		Gson gson2 = new Gson();
		String json2 = gson2.toJson(commentAuthor);

		System.out.println(json2);
		String mynamefile2 = "AuthorsProfile"+project + dateFormat.format(cal.getTime()) + ".txt";
		// String
		// mynamefileTotal="InfluenceTotal_quirky"+dateFormat.format(cal.getTime())+".txt";
		try {
			FileWriter writer = new FileWriter(mynamefile2);
			writer.write(json2);
			writer.close();
			System.out.println("Write file: Success");
			System.out.print("AUTHOR-PROFILEs: ");

		} catch (Exception e) {
			// TODO: handle exception

		}
		
		
		
		
	}
}
