package Design;

import javax.swing.*;

public class ResultFrame extends JFrame 
{
    public ResultFrame(String message) 
    {
        setTitle("Result");/*Title creation*/
        setSize(300, 100);/*Size of Title*/
        setLayout(new java.awt.FlowLayout());/*Makes line wrap down when no more space is left*/
        setLocationRelativeTo(null);/*Centers frame*/
        add(new JLabel(message));/*Adds label*/
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);/*Closes only this window when crossed out*/

        ImageIcon icon = new ImageIcon(Login_GUI.class.getResource("/Design/images/Calling_Card.png"));
        /*Removes penguin icon and places custom image*/
        setIconImage(icon.getImage());/*Setting image*/

        setVisible(true);
    }
}
