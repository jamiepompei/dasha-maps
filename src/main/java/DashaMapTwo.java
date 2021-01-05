public class DashaMapTwo {

    private Node[] hashArray;

    public DashaMapTwo(){
        this.hashArray = new Node[26];
        char a = 'a';
        for (int i = 0; i < 26; i++) {
            char alphabet = (char)(a+i);
            hashArray[i] = new Node(Character.toString(alphabet), null, null);
        }
    }

    public Node[] getHashArray(){
        return hashArray;
    }

    private String HashFunctionTwo(String input) {
        if (input.length() > 0) {
            if(input.length() == 1){
                return (String.valueOf(input.charAt(0)).toLowerCase());
            }
            return String.valueOf(input.charAt(1)).toLowerCase();
        }
        return null;
    }

    private Integer findHead(String keyHash){
        int i = 0;
        for(Node node: hashArray){
            if(node.key.equals(keyHash)){
                return i;
            }
            i++;
        }
        return -1;
    }

    public Node getNode(Node[] hashArray, String keyHash, String key){
        for(Node node: hashArray){
            if(node.getKey().equals(keyHash)){
                Node tempNode = node;
                while(!tempNode.getKey().equals(key)){
                    tempNode = tempNode.getNext();
                }
                return tempNode;
            }
        }
        return null;
    }

    private Node findIn(String hashKey, String key){
        return getNode(hashArray, hashKey, key);
    }

    @Override
    public void set(String key, String value) {
        String keyHash = HashFunctionTwo(key);
        Node newNode = new Node(key, value, null);
        appendTo(keyHash, newNode);
    }

    private void appendTo(String letter, Node newNode) {
        for(Node node: hashArray){
            if(node.getKey().equals(letter)){
                Node tempNode = node;
                while(tempNode.getNext() != null){
                    tempNode = tempNode.getNext();
                }
                tempNode.setNext(newNode);
            }
        }

    }

    @Override
    public void delete(String key) {
        String keyHash = HashFunctionTwo(key);
        removeIn(keyHash, key);
    }

    public void removeIn(String keyHash, String key){
        DashaMapOne.reconnectNode(hashArray, keyHash, key);
    }


    @Override
    public String get(String key) {
        String keyHash = HashFunctionTwo(key);
        Node newNode = findIn(keyHash, key);
        if(newNode != null){
            return newNode.getValue();
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < hashArray.length; i++) {
            if(hashArray[i] != null){
                return false;
            }
        }
        return true;
    }

    @Override
    public long size() {
        long count = 0;
        for(Node node: hashArray){
            Integer num = bucketSize(node.key);
            count += num;
        }
        return count;
    }

    @Override
    public Integer bucketSize(String keyHash) {
        int count = 0;
        keyHash = HashFunctionTwo(keyHash);
        return getSizeInteger(keyHash, count, hashArray);
    }

    public Integer getSizeInteger(String keyHash, int count, Node[] hashArray){
        for(Node node : hashArray){
            if(node.getKey().equals(keyHash)){
                Node tempNode = node;
                while(tempNode.getNext() != null){
                    tempNode = tempNode.getNext();
                    count++;
                }
            }
        }
        return count;
    }


}
