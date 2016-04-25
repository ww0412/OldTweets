package Sentiment;

public class test {
	public static void main(String[] args) {
        String topic = "we hate donald trump";
        NLP.init();
        System.out.println("score" + " : " + NLP.findSentiment(topic));
        
    }

}
