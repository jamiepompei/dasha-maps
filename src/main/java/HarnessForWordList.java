import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HarnessForWordList {

    private DashaMapOne dashaMapOne;
    private DashaMapTwo dashaMapTwo;
    private DashaMapThree dashaMapThree;

    public void readFile(){
        dashaMapOne = new DashaMapOne();
        dashaMapTwo = new DashaMapTwo();
        dashaMapThree = new DashaMapThree();

        String filePath = "word-list.txt";
        String line;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null){
                String[] words = line.split(" ", 2);
                if(words.length >= 2){
                    String key = words[0];
                    String value = words[1];
                    dashaMapOne.set(key, value);
                    dashaMapTwo.set(key, value);
                    dashaMapThree.set(key, value);
                }
            }
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public DashaMapOne getDashaMapOne(){
        return dashaMapOne;
    }

    public DashaMapTwo getDashaMapTwo(){
        return dashaMapTwo;
    }

    public DashaMapThree getDashaMapThree(){
        return dashaMapThree;
    }
}
