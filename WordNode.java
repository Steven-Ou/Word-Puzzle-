public class WordNode {

    protected Word data;
    protected WordNode next;

    public WordNode(){
        data = null;
        next=null;
    }//constructor
    public WordNode(String data){
        this.data = new Word(data);
        this.next=null;
    }//constructor
    public WordNode(Word w){
        data= w;
        this.next=null;
    }
}