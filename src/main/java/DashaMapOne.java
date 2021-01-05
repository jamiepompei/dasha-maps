public class DashaMapOne {

    private Node[] hashArray;

    public Node[] getHashArray(){
        return hashArray;
    }

    public DashaMapOne() {
        this.hashArray = new Node[26];
        char a = 'a';
        for (int i = 0; i < 26; i++) {
            char alphabet = (char)(a+i);
            hashArray[i] = new Node(Character.toString(alphabet), null, null);
        }
    }

    private String HashFunctionOne(String input) {
        if (input.length() > 0) {
            return String.valueOf(input.charAt(0)).toLowerCase();
        }
        return null;
    }

    private Integer findHead(String keyHash) {
        int i = 0;
        for (Node node : hashArray) {
            if (node.key.equals(keyHash)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private void appendTo(String keyHash, Node newNode) {
        for(Node node: hashArray){
            if(node.getKey().equals(keyHash)){
                Node tempNode = node;
                while(tempNode.getNext() != null){
                    tempNode = tempNode.getNext();
                }
                tempNode.setNext(newNode);
            }
        }
    }


    private Node findIn(String keyHash, String key) {
        return getNode(hashArray, keyHash, key);

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

    @Override
    public void set(String key, String value) {
        String keyHash =  HashFunctionOne(key);
        Node newNode = new Node(key, value, null);
        appendTo(keyHash, newNode);
    }

    @Override
    public void delete(String key) {
        String keyHash = HashFunctionOne(key);
        removeIn(keyHash, key);
    }

    public static void reconnectNode(Node[] hashArray, String keyHash, String key){
        for(Node node: hashArray){
            if(node.getKey().equals(keyHash)){
                Node tempNode = node;
                Node nodeToReconnect = node;
                while(tempNode.getNext() != null && !tempNode.getKey().equals(key)){
                    nodeToReconnect = node;
                    tempNode = tempNode.getNext();
                }
                nodeToReconnect.setNext(tempNode.getNext());
                tempNode.setNext(null);
            }
        }
    }

    public void removeIn(String keyHash, String key){
        reconnectNode(hashArray, keyHash, key);
    }

    @Override
    public String get(String key) {
        String keyHash =  HashFunctionOne(key);
        Node newNode = findIn(keyHash, key);
        if (newNode != null) {
            return newNode.value;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < hashArray.length; i++) {
            if (hashArray[i].next != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public long size() {
        long count = 0;
        for (Node node : hashArray) {
            Integer num = bucketSize(node.key);
            count += num;
        }
        return count;
    }

    @Override
    public Integer bucketSize(String keyHash) {
        int count = 0;
        keyHash = HashFunctionOne(keyHash);
        for (int i = 0; i < hashArray.length; i++) {
            if(hashArray[i].getKey().equals(keyHash)){
                Node tempNode = hashArray[i];
                while(tempNode.getNext() != null){
                    tempNode = tempNode.getNext();
                    count++;
                }
            }
        }
        return count;
    }
}
