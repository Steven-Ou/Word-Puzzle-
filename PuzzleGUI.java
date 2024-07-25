import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class PuzzleGUI extends JFrame{
    static Container cPane;
    static TextArea left,right;
    JFrame Frame;
    WordNode temp = new WordNode();
    Word w;

    public void initialize(){//starting the function.
        
        Frame = new JFrame("Word Game");//creat JFrame that have a title "Word Game"
        left = new TextArea();//creat left side text area
        right = new TextArea();//creat right side text area

        Frame.setSize(300,300);
        Frame.setLocation(200,200);
        createFileMenu();// Create a file menu selector
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//set default close operation
        
       
        cPane = Frame.getContentPane();//set cPane as a contentPane
        Frame.setLayout(new GridLayout(1,2));
        cPane.add(left);//add the left side text area first
        cPane.add(right);//add the right side text area
        
    
        //auto change the size to fit
        Frame.setVisible(true);//make it visible
    }

    public void start(String letter){//for the beginning of the project. Like when there's no letter. We select a file.
        left.setText("");
        right.setText("");//reset the whole text area
        left.append("Letters: " + letter);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void createFileMenu( ){
        JMenuItem   item;
        JMenuBar    menuBar  = new JMenuBar();
        JMenu       fileMenu = new JMenu("File");
        FileMenuHandler fmh  = new FileMenuHandler(this);
  
        item = new JMenuItem("Open");    //Open...
        item.addActionListener( fmh );
        fileMenu.add( item );
  
        fileMenu.addSeparator();           //add a horizontal separator line
      
        item = new JMenuItem("Quit");       //Quit
        item.addActionListener( fmh );
        fileMenu.add( item );
  
        Frame.setJMenuBar(menuBar); // Set menubar in the Jframe
        menuBar.add(fileMenu); // Add fileMenu to menuBar
      
      
     } 
    
    /**
     * //display found answers in alphabedical order
     * @param solution
     * @param input
     * @param score
     */

    
    public void GUI(SortedWordList sorted, String input, int score){
      

        
        w = new Word(input);
        sorted.add(w);

        temp = sorted.first.next;

        right.setText(temp.data.getWord()+"          Score:"+score+"\n");
        temp = temp.next;

        while(temp != null){
            right.append(temp.data.getWord()+"\n");
            temp = temp.next;
        }
        

    }

    
}
