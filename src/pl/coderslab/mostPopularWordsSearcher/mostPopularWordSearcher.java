package pl.coderslab.mostPopularWordsSearcher;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;


public class mostPopularWordSearcher {


    public static void main(String[] args) {


        String url = "http://www.nytimes.com/";
        String headlinesTag = "h2.story-heading";
        String[] excludedWords = {"and", "wether", "either", "but","with","the", "because", "neither", "nor",
                                  "for", "upon", "how", "she", "they", "you", "are", "were", "was", "had", "has", "have"};
        
        String popularWordsFile = "popular_words.txt";
        String filteredPopularWordsFile = "filtered_popular_words.txt";


        readHeadlines(url, headlinesTag, popularWordsFile);
        filterHeadlines(popularWordsFile, excludedWords, filteredPopularWordsFile);
        countAndSortFilteredWords(filteredPopularWordsFile);

    }

    /**
     * This method read headlines from chosen website and write every single world
     * without any special characters avoiding words shorter than 3 characters
     *
     * @param url          website url
     * @param headlinesTag type of tags which contain headlines and their class, for example: span.headline
     */
    private static void readHeadlines(String url, String headlinesTag, String targetFile) {

        Connection conn = Jsoup.connect(url);
        try {
            Document doc = conn.get();
            Elements links = doc.select(headlinesTag);
            FileWriter fw = new FileWriter(targetFile, false);

            for (Element elem : links) {
                StringTokenizer strToken = new StringTokenizer(elem.text().trim().replaceAll("[^\\p{L} ]", ""));
                String element;
                while (strToken.hasMoreTokens()) {
                    element = strToken.nextToken();
                    if (element.length() > 2)
                        fw.append(element.toLowerCase() +"\n");
                }
            }
            fw.close();

        } catch
                (IOException e) {
            e.printStackTrace();
        }

    }

    /** This method copies words from
     * @param popularWordsFile
     * excluding words from
     * @param wordsToExclude
     * and saves it to
     * @param targetFile
     */
    private static void filterHeadlines(String popularWordsFile, String[] wordsToExclude, String targetFile) {

        File file = new File(popularWordsFile);
        Scanner scan;

        try {
            scan = new Scanner(file);
            FileWriter fw = new FileWriter(targetFile, false);
            boolean exclude = false;

            while (scan.hasNextLine()) {
                String word = scan.nextLine();
                for (int i = 0; i < wordsToExclude.length; i++) {
                    if (wordsToExclude[i].equals(word)) {
                        exclude = true;
                        break;
                    }
                }
                if (!exclude) {
                    fw.append(word +"\n");
                }

                exclude = false;
            }
            scan.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**This method turns file with words into file with the same words
     *  but without duplications and with amount of occurrences for every word from the initial file
     *  sorted by amount of occyrrences
     * @param filteredWordsFile
     */
    private static void countAndSortFilteredWords(String filteredWordsFile){

        File file = new File(filteredWordsFile);
        Scanner scan;
        ArrayList<String> tmp = new ArrayList<>(), result = new ArrayList<>();
        try {

            scan = new Scanner(file);

            while (scan.hasNextLine()){
                tmp.add(scan.nextLine());
            }
            scan.close();

            Collections.sort(tmp);
            int counter = 1;
            for (int i=0; i<tmp.size()-1; i++){
                if (tmp.get(i).equals(tmp.get(i+1))) counter++;

                else {
                    result.add(String.valueOf(counter) + " " + tmp.get(i));
                    counter = 1;
                }
            }


            Collections.sort(result,
                    new Comparator<String>(){
                        public int compare(String s1, String s2) {
                            int value1 = Integer.parseInt(s1.split(" ")[0]);
                            int value2 = Integer.parseInt(s2.split(" ")[0]);

                            return value1 - value2;
                        }
                    }
            );
        Path path = Paths.get(filteredWordsFile);
        Files.write(path, result);

        }catch (IOException e){e.printStackTrace();}

    }

}
