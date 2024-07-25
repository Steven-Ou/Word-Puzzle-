import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane; 

public class Project3 {
 /**  Name of text file  */
 private static String filename;
 private static String letter;
private static String newGame;
private static String line;
 /**  Buffered character stream from file  */
 private static BufferedReader br;  
 private static int score =0;//holder for the points n word count
private static int index=0;
private static int maybe=0;
 /**  Count of lines read so far.  */
 private int lineCount = 0;//counts line
private static PuzzleGUI GUI= new PuzzleGUI();//Create a new puzzleGUI variable
 public static UnsortedWordList unsort = new UnsortedWordList();// Unsort list
 public static SortedWordList sorted = new SortedWordList();// sorted list for answer
 private static Word w;
 private static WordNode temp;
 private static char c; 
   
 /**
    * Creates a buffered character input
    * strea, for the specified text file.
    *
    * @param filename the input text file.
    * @exception RuntimeException if an
    *          IOException is thrown when
    *          attempting to open the file.
    */   
    public Project3(String filename){
        this.filename= filename;// turn filename into a object
            try{
                br = new BufferedReader( //using tools from java tools.
                    new InputStreamReader(
                        new FileInputStream(filename)));
            } catch ( IOException ioe )  {
            throw new RuntimeException(ioe);
            }
    }
    
    public static void Game(String chosenfile){
   
        Project3 P=new Project3(chosenfile);
        P.readInputFile(); 
        /* 
        temp = unsort.first.next;
        while(temp!=null){
            System.out.println(temp.data.getWord());
            temp=temp.next;
        }
        */
        maybe =P.maybes();//possibilities of the words.
        
        P.playGame();//start game
        P.close();//close game
    }
    public void readInputFile() {
        try {     
            letter = br.readLine();// buffer reads the letters into the readLine() function.
            
            line = br.readLine();
            
            while(line != null){
                try{
                w = new Word(line);
                for(int t=0;t<w.getWord().length();t++){
                    if(Character.isUpperCase(w.getWord().charAt(t)))//checks all the word and the letters if it's upper.
                        throw new IllegalWordException("Illegal word!!!");
                }
                unsort.add(w);

                }
                catch(IllegalWordException e){
                    System.out.println("Illegal Word: " + w.getWord());// prints the word that's illegal.
                }
                finally{
                    line = br.readLine();
                }
            
            }
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }  
        
    }
    //the function that contains all logic.
    public void playGame() { 
        
        String inputWord;//initializing the variable.
       
        GUI.start(letter);// using PuzzleGui method.
        int check=0;// count variable.

        do {//while loop
       
            inputWord = JOptionPane.showInputDialog(null, "Enter a Word: (TYPE 'STOP' TO EXIT)");
            //conditions with if statements and also JOptionPane.
            if(inputWord.equalsIgnoreCase("STOP")){
                break;
            }
            //using stop to exit.

            //Check if the user uses a non given letter
            for(int i=0; i<inputWord.toCharArray().length;i++){// for loop to run and check/
                check=0;// count variable.
                for(int f=0;f<7;f++){// one more for loop to run and check the letters if they are equals to the letters, if its correct it will add to check and then it will break and continue if its 0 then it will break.
                    if(Character.toLowerCase(inputWord.charAt(i))==letter.charAt(f)){
                        check=1;
                        break;
                    }
                }
                if(check==0)break;
            }
            if(check==0) {// if its 0 the message will appear.
                JOptionPane.showMessageDialog(null, "ONLY use given letters!");
            }
            //Check if user entered less than 5 letters
            else if(inputWord.toCharArray().length < 5){ //if words are less than 5 it will also show a message.
                JOptionPane.showMessageDialog(null,"Enter more than 5 letters!");
            }
            
            // Check if the entered word is in the solutions
            else if(NotContainLetter(inputWord)){
                JOptionPane.showMessageDialog(null, "You must contains a letter "+c);
            }
            else if(isWordInSolutions(inputWord)){
                    check =0;
                    //check if the user entered the word already
                    temp = sorted.first.next;
                    while(temp!=null){
                        if(inputWord.equalsIgnoreCase(temp.data.getWord())){
                            JOptionPane.showMessageDialog((null), "This Word is already found!");
                            check =1;
                        }
                        temp = temp.next;
                    }
                    if(check==0){
                        //works only if its a newly found word.
                        index++;
                        score++;
                        if(usedAllLetters(inputWord)){//Checks if all letters are used such as optimal.
                            score = score+2;
                        }
                    
                    GUI.GUI(sorted, inputWord, score);//Gui function from Puzzle Gui.
                    //check if all letters are found.
                    if(index == maybe){
                        JOptionPane.showMessageDialog(null, "Congrat! All words found!!");
                        newGame = JOptionPane.showInputDialog("Type 'z' to play again!");
                        if(!newGame.equalsIgnoreCase("z")){
                            System.exit(0);
                        }
                        score =0;
                        index=0;
                        sorted= new SortedWordList();
                        GUI.start(letter);
                    }
                }
            }
                    if(check ==1){
                         JOptionPane.showMessageDialog(null, "Word is not in solution!");
                    }
           
       
        } while (!inputWord.equalsIgnoreCase("STOP"));//the for loop.
        
    }
    
    //a function to check if the word is correct and if its not correct it will return false and it wont work.
    private static boolean isWordInSolutions(String word) {
        // Check if the guessed word is in the solutions
        temp = unsort.first.next;
        while(temp!=null){
            if(word.equalsIgnoreCase(temp.data.getWord())){
                return true;
            }
            temp = temp.next;
        }
            return false; // Guessed word is not found in the solutions
    }
    public int maybes(){
        int t = 0;

        temp = unsort.first.next;
        while(temp != null){
            if(!NotContainLetter(temp.data.getWord())){
                t++;
            }

            temp = temp.next; 
        }
        return t;
    }
    
    //the main file. That once runs the game would start. 
    public static void main(String[] args) {
        
        
        GUI.initialize();//starting the GUI
        JOptionPane.showMessageDialog(null,"Choose solution file by using menu bar");
    }
    public static boolean NotContainLetter(String ans){//check that input string has the first letter of the subject letter
        c = letter.charAt(0);//Looking for L
        for(int i=0;i<ans.length();i++){
            if(ans.charAt(i) == c){
                return false;
            }
        }

        return true;
    }



//These codes aren't used. But I just leave it here, since I took some of Doctor Nixon Code. Want to still give credits to Doctor Nixon!
  /**These are all codes from TextFIleINput. Created By Doctor Nixon!
    * Closes this character input stream.
    * No more characters can be read from
    * this TextFileInput once it is closed.
    * @exception NullPointerException if
    *        the file is already closed.
    * @exception RuntimeException if an
    *       IOException is thrown when
    *       closing the file.
    */
    public void close(){
      try  {
         br.close();
         br = null;
      } catch ( NullPointerException npe )  {
         throw new NullPointerException(
                        filename + "already closed.");
      } catch ( IOException ioe )  {
         throw new RuntimeException(ioe);
      }  // catch
    }//method close
    public static boolean usedAllLetters(String w){//to check the input used all letters
        char[] a = new char[letter.length()];

        for(int i = 0;i<letter.length();i++){
            a[i] = letter.charAt(i);
        }

        for(int i=0;i<w.length();i++){
            for(int f=0;f<letter.length();f++){
                if(w.charAt(i) == a[f]){
                    a[f] = '!';
                    break;
                }
            }
        }

        for(int i=0;i<letter.length();i++){
            if(a[i] != '!'){
                return false;
            }
        }

        return true;
    }
/**
    * Tests whether the specified string is one of the
    * specified options.  Checks whether the string
    * contains the same sequence of characters (ignoring
    * case) as one of the specified options.
    *
    * @param toBeChecked the String to be tested
    * @param options a set of Strings
    * @return true if <code>toBeChecked</code>
    *         contains the same sequence of
    *         characters, ignoring case, as one of the
    *         <code>options</code>, false otherwise.
    */
    public static boolean isOneOf(String toBeChecked,String[] options) {
      boolean oneOf = false;
      for ( int i = 0; i < options.length && !oneOf; i++ )
         if ( toBeChecked.equalsIgnoreCase(options[i]) )
            oneOf = true;
      return oneOf;
   }
   /**
    * Reads a line of text from the file and
    * positions cursor at 0 for the next line.
    * Reads from the current cursor position
    * to end of line.
    * Implementation does not invoke read.
    *
    * @return the line of text, with
    *         end-of-line marker deleted.
    * @exception RuntimeException if an
    *          IOException is thrown when
    *          attempting to read from the file.
    */
    public String readLine()
   {
      return readLineOriginal();
   }  // method readLine()

 /**
    * Reads a line from the text file and ensures that
    * it matches one of a specified set of options.
    *
    * @param options array of permitted replies
    *
    * @return the line of text, if it contains the same
    *         sequence of characters (ignoring case for
    *         letters) as one of the specified options,
    *         null otherwise.
    * @exception RuntimeException if the line of text
    *         does not match any of the specified options,
    *         or if an IOException is thrown when reading
    *         from the file.
    * @exception NullPointerException if no options are
    *         provided, or if the end of the file has been
    *         reached.
    */
    
    public String readSelection(String[] options)
    {
       if ( options == null || options.length == 0 )
          throw new NullPointerException(
                             "No options provided for "
                             + " selection to be read in file "
                             + filename + ", line " 
                             + (lineCount + 1) + ".");
 
       String answer = readLine();
 
       if ( answer == null )
          throw new NullPointerException(
                             "End of file "
                             + filename + "has been reached.");
 
       if ( !Project3.isOneOf(answer, options) )  {
          String optionString = options[0];
          for ( int i = 1; i < options.length; i++ )
             optionString += ", " + options[i];
          throw new RuntimeException("File " + filename
                             + ", line " + lineCount
                             + ": \"" + answer
                             + "\" not one of "
                             + optionString + ".");
       }  // if
       return answer;
   }  // method readSelection
   /**
    * Reads a line of text from the file and
    * increments line count.  (This method
    * is called by public readLine and is
    * final to facilitate avoidance of side
    * effects when public readLine is overridden.)
    *
    * @return the line of text, with
    *         end-of-line marker deleted.
    * @exception RuntimeException if an
    *          IOException is thrown when
    *          attempting to read from the file.
    */
    protected final String readLineOriginal()
    {
        try  {
           if ( br == null )
              throw new RuntimeException(
                                 "Cannot read from closed file "
                                 + filename + ".");
           String line = br.readLine();
           if ( line != null )
              lineCount++;
           return line;
        } catch (IOException ioe)  {
           throw new RuntimeException(ioe);
        }  // catch
    } 







}