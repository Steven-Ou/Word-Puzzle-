import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class FileMenuHandler implements ActionListener {
    JFrame jframe;
    public FileMenuHandler (JFrame jf) {
       jframe = jf;
    }
    public void actionPerformed(ActionEvent event) {
       String  menuName;
       menuName = event.getActionCommand();//menu selections
       if (menuName.equals("Open"))
          openFile(); 
       else if (menuName.equals("Quit"))
          System.exit(0);
    } //actionPerformed
 
     private void openFile(){
        JFileChooser chooser;
        int          status;
        chooser = new JFileChooser( );
        status = chooser.showOpenDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) 
           readSource(chooser.getSelectedFile());
        else 
           JOptionPane.showMessageDialog(null, "Open File dialog canceled");
     } //openFile
   
     private void readSource(File chosenFile) {
        String chosenFileName = chosenFile.getAbsolutePath();//getting absolute path.
    
        Project3.Game(chosenFileName);//choosing a file. 
      
  
     }
 }