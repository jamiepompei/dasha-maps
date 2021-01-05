import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DashaMapOneTest {

    @Test
    public void set() {
        DashaMapOne dm = new DashaMapOne();

        dm.set("aaa", "5");
        Integer expected = 1;
        Integer actual = dm.bucketSize("a");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void set2() {
        DashaMapOne dm = new DashaMapOne();

        Integer expected = 0;
        Integer actual = dm.bucketSize("a");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        DashaMapOne dm = new DashaMapOne();

        dm.set("Kyle", "1");
        dm.set("Pompy", "2");
        dm.set("Jon", "3");

        Integer expectedBeforeDelete = 1;
        Integer actualBeforeDelete = dm.bucketSize("p");
        Assert.assertEquals(expectedBeforeDelete, actualBeforeDelete);

        dm.delete("Pompy");

        Integer expectedAfterDelete = 0;
        Integer actualAfterDelete = dm.bucketSize("p");
        Assert.assertEquals(expectedAfterDelete, actualAfterDelete);
    }

    @Test
    public void get() {
        DashaMapOne dm = new DashaMapOne();

        dm.set("aaa", "5");
        String expected = "5";
        String actual = dm.get("aaa");
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void get2() {
        DashaMapOne dm = new DashaMapOne();

        dm.set("aaa", "5");
        dm.set("aba", "3");
        dm.set("aac", "1");
        String expected = "5";
        String actual = dm.get("aaa");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isEmpty() {
        DashaMapOne dm = new DashaMapOne();

        Boolean expected = false;

        dm.set("aaa", "5");

        Boolean actual = dm.isEmpty();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isEmpty2() {
        DashaMapOne dm = new DashaMapOne();

        Boolean expected = true;
        Boolean actual = dm.isEmpty();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void size() {
        DashaMapOne dm = new DashaMapOne();

        dm.set("aaa", "5");
        dm.set("ab", "5");
        Long expected = 2L;
        Long actual = dm.size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void bucketSize() {
        DashaMapOne dm = new DashaMapOne();

        dm.set("aaa", "5");
        dm.set("ab", "5");
        Integer expected = 2;
        Integer actual = dm.bucketSize("a");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReadList() {
        DashaMapOne dm = new DashaMapOne();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "word-list.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] words = line.split(" ",2);
                dm.set(words[0], words[1]);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Long actual = dm.size();
        Long expected = 124L;
        Assert.assertEquals(expected, actual);
    }

}
