package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import manager.TweetManager;
import manager.TwitterCriteria;
import model.Tweet;

public class Exporter2 {
	public static void main(String[] args){
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		TwitterCriteria criteria = TwitterCriteria.create()
				.setQuerySearch("hillary clinton")
				.setSince("2016-03-10")
				.setUntil("2016-03-31")
				.setMaxTweets(1000);
		
		try {
			int count=0;
			int file=0;
			BufferedWriter bw = new BufferedWriter(new FileWriter("hillary"+file+".csv"));
			bw.write("username;date;retweets;favorites;text;geo;mentions;hashtags;id;permalink");
			bw.newLine();
			System.out.println("Searching... \n");
			TweetManager.getTweets(criteria);
			for (Tweet t : TweetManager.getTweets(criteria)) {
				if(count == 100) {
					count = 0;
					bw.flush();
					bw.close();
					file++;
					bw = new BufferedWriter(new FileWriter("hillary"+file+".csv"));
					bw.write("username;date;retweets;favorites;text;geo;mentions;hashtags;id;permalink");
					bw.newLine();
					System.out.println("file"+file+"... \n");
				}
				bw.write(String.format("%s;%s;%d;%d;\"%s\";%s;%s;%s;\"%s\";%s", t.getUsername(), sdf.format(t.getDate()), t.getRetweets(), t.getFavorites(), t.getText(), t.getGeo(), t.getMentions(), t.getHashtags(), t.getId(), t.getPermalink()));
				bw.newLine();
				count++;
			}
			System.out.println("Done. Output file generated \"output_got.csv\".");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}


