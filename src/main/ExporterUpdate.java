package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import manager.TweetManager;
import manager.TwitterCriteria;
import model.Tweet;

public class ExporterUpdate {
	public static void main(String[] args){
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		TwitterCriteria criteria = TwitterCriteria.create()
				.setQuerySearch("hillary clinton")
				.setSince("2016-03-10")
				.setMaxTweets(10000);
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("output_hillary32.csv"));
			bw.write("username;date;retweets;favorites;text;geo;mentions;hashtags;id;permalink");
			bw.newLine();
			
			System.out.println("Searching... \n");
			int count=0;
			for (Tweet t : TweetManager.getTweets(criteria)) {
				System.out.println(count);
				count++;
				bw.write(String.format("%s;%s;%d;%d;\"%s\";%s;%s;%s;\"%s\";%s", t.getUsername(), sdf.format(t.getDate()), t.getRetweets(), t.getFavorites(), t.getText(), t.getGeo(), t.getMentions(), t.getHashtags(), t.getId(), t.getPermalink()));
				bw.newLine();
			}
			
			bw.close();
			
			System.out.println("Done. Output file generated \"output_got.csv\".");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}


