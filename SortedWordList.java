public class SortedWordList extends WordList{
    WordNode temp = new WordNode();
    WordNode node;
    WordNode before;
    String firstData,nodeData,tempData;
    int c,checkAdded;
    int check;

    public SortedWordList(){
        super();
    }

    public void add(Word w){

        if(length == 0){//when array is empty, append the word
            append(w);
        }
        else if(length == 1){//when array only has one word
            node = new WordNode(w);
            firstData = first.next.data.getWord();
            nodeData = node.data.getWord();
            if(firstData.length() <= nodeData.length()){//set c as shorer length between first word and new word
                c = firstData.length();
            }
            else{
                c = nodeData.length();
            }

            check = 0;

            for(int i=0;i<c;i++){//compare each character. if new word is less than first word, prepend the new word
                if(nodeData.charAt(i) == firstData.charAt(i)){
                    //do nothing and move to next loop
                }
                else if(nodeData.charAt(i) - firstData.charAt(i) < 0){
                    check = 1;
                    prepend(w);
                    break;
                }
                else{
                    check = 0;
                    break;
                }
            }

            if(check == 0){//if first word is less than new word, append the new word
                append(w);
            }


        }
        else{//when array has more than one word
            node = new WordNode(w);
            temp = first.next;
            before = first;//Wordnode which is one befor the temp
            while(temp != null){

                tempData = temp.data.getWord();//data to String
                nodeData = node.data.getWord();//data to String


                if(tempData.length() <= nodeData.length()){//set c as shorter length between temp word and new word
                    c = firstData.length();
                }
                else{
                    c = nodeData.length();
                }


                check = 0;
                checkAdded = 0;
                //Character.compare(nodeData.charAt(i) , tempData.charAt(i))
                //nodeData.charAt(i) - tempData.charAt(i)
                for(int i=0;i<c;i++){
                    if(nodeData.charAt(i) - tempData.charAt(i) == 0){//when character are same
                        //do nothing and move on to next character
                    }
                    else if(nodeData.charAt(i) - tempData.charAt(i) > 0){
                        check = 1;//to check this code went through
                        break;
                    }
                    else if(nodeData.charAt(i) - tempData.charAt(i) < 0){
                        check = 2;//to check this code went through
                        break;
                    }
                }

                if(check == 1){
                    
                    before = temp;
                    temp = temp.next;
                    
                }
                else if(check == 2){
                    checkAdded = 1;
                    before.next = node;
                    node.next = temp;
                    length++;
                    break;
                }
                
            }
            if(checkAdded == 0){
                append(w);
            }

            


        }
    }
}
