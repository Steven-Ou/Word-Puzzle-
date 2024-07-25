public abstract class WordList {
    protected WordNode first;
    protected WordNode last;
    protected int length;


    public WordList(){
        first = new WordNode();
        last = first;
        length =0;

    }
    public void append(Word w){
        WordNode n = new WordNode(w);
        last.next = n;
        last = n;
        length++;
    }

    public void prepend(Word w){
        WordNode n = new WordNode(w);
        n.next = first.next;
        first.next = n;
        length++;
    }



}