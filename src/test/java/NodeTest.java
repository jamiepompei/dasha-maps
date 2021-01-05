import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NodeTest {

    private Node node;

    @Before
    public void setUp(){
        String key = "hello";
        String value = "1";

        node = new Node(key, value, null);
    }

    @Test
    public void constructorTest(){

        String expectedKey = "hello";
        String expectedValue = "1";

        String actualKey = node.getKey();
        String actualValue = node.getValue();



        Assert.assertEquals(expectedKey, actualKey);
        Assert.assertEquals(expectedValue, actualValue);
        Assert.assertEquals(null, node.getNext());
    }

    @Test
    public void setKeyTest(){
        String expectedKey = "c";
        node.setKey(expectedKey);

        String actualKey = node.getKey();

        Assert.assertEquals(expectedKey, actualKey);
    }

    @Test
    public void setValueTest(){
        String expectedValue = "13";
        node.setValue(expectedValue);
        String actualValue = node.getValue();

        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void setNodeTest(){
        Node expected = new Node(null, null, null);
        node.setNext(expected);

        Node actual = node.getNext();

        Assert.assertEquals(expected, actual);
    }
}
