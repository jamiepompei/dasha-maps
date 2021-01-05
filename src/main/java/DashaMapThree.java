public class DashaMapThree implements HashMapX{

    private Node[] hashArray;

    public DashaMapThree(){
        this.hashArray = new Node[26];
        char a = 'a';
        for (int i = 0; i < 26; i++) {
            char alphabet = (char)(a+i);
            hashArray[i] = new Node(Character.toString(alphabet), null, null);
        }
    }

    private String HashFunctionThree(String input) {
        if (input.length() > 1) {
            return String.valueOf(input.charAt(0)+input.charAt(1)).toLowerCase();
        }
        return null;
    }

    public Node[] getHashArray(){
        return hashArray;
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

    private void appendTo(String keyHash, Node newNode){
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

    private Node findIn(String keyHash, String key){
        return getNode(hashArray, keyHash, key);
    }

    public Node getNode(Node[] hashArray, String keyHash, String key){
        for(Node node : hashArray){
            if(node.getKey().equals(keyHash)){
                Node tempNode = node;
                while(tempNode != null){
                    tempNode = tempNode.getNext();
                }
                return  tempNode;
            }
        }
        return null;
    }

    public void reconnectNode(Node[] hashArray, String hashKey, String key){
        for(Node node : hashArray){
            if(node.getKey().equals(hashKey)){
                Node tempNode = node;
                Node nodeToRecconnect = node;
                while(tempNode.getNext() != null && tempNode.getKey().equals(key)){
                    nodeToRecconnect = node;
                    tempNode = tempNode.getNext();
                }
                nodeToRecconnect.setNext(tempNode.getNext());
            }
        }
    }

    public void removeIn(String keyHash, String key){
        reconnectNode(hashArray, keyHash, key);
    }

    @Override
    public void set(String key, String value) {
        String keyHash = HashFunctionThree(key);
        Node newNode = new Node(key, value, null);
        appendTo(keyHash, newNode);

    }

    @Override
    public void delete(String key) {
        String keyHash = HashFunctionThree(key);
        removeIn(keyHash, key);

    }

    @Override
    public String get(String key) {
        String keyHash = HashFunctionThree(key);
        Node newNode = findIn(keyHash, key);
        if(newNode != null){
            return newNode.value;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < hashArray.length; i++) {
            if(hashArray[i].next != null){
                return false;
            }
        }
        return true;
    }

    @Override
    public long size() {
        long count = 0;
        for(Node node : hashArray){
            Integer num = bucketSize(node.key);
            count += num;
        }
        return count;
    }

    @Override
    public Integer bucketSize(String keyHash) {
        int count = 0;
        keyHash = HashFunctionThree(keyHash);
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
