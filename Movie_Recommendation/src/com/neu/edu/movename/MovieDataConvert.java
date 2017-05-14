package com.neu.edu.movename;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieDataConvert {

/**
* cat u.data | cut -f1,2,3 | tr "\\t" ","
* @throws IOException 
* 
*/

public static void main(String[] args) throws IOException {

/*BufferedReader br = new BufferedReader(new FileReader("data/u.data"));
BufferedWriter bw = new BufferedWriter(new FileWriter("data/movies.csv"));

String line;
while((line = br.readLine()) != null) {
String[] values = line.split("\\t", -1);
bw.write(values[0] + "," + values[1] + "," + values[2] + "\n");
}

br.close();
bw.close();*/
Pattern pattern = Pattern.compile("\\[(.*?)\\]");
BufferedReader data = new BufferedReader(new FileReader("data/part-r-00000"));
BufferedReader movies = new BufferedReader(new FileReader("data/u.item"));
Map<String, String> moviesMap = new HashMap<>();
String line;
while((line = movies.readLine()) != null){
StringTokenizer st = new StringTokenizer(line, "|");
moviesMap.put(st.nextToken(), st.nextToken());
}


System.out.println("Recommended movies");
while((line = data.readLine()) != null){

	String userid =line.substring(0,line.indexOf("\t"));

	if(userid.equals("10")){

		Matcher m = pattern.matcher(line);
while(m.find()){
String movieAndRating = m.group(1);

StringTokenizer st = new StringTokenizer(movieAndRating, ",");

while (st.hasMoreElements()) {

	String token = (String) st.nextElement();

	String[] movieValues = token.split(":", -1);

	if(movieValues != null && movieValues.length > 0){

		String movietitle = moviesMap.get(movieValues[0]);

		String rating = movieValues[1];

		System.out.println(movietitle + " : "+rating);
}
}

}

}
}

data.close();
movies.close();

}

}