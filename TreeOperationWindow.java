import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * A window that gathers nformation about amount of rabbits to spawn
 * @author Daniel Włudarczyk
 */
public class TreeOperationWindow extends JFrame 
{
    private ServerClient serverClient;

    /**
     * Constructor which sets up window and its content.
     * Sets listener of button in the window.
     */
    public TreeOperationWindow() 
    {
        super("Binary tree");
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setBounds(200, 100, 300, 155);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel typeOfTreeLabel = new JLabel("Choose type of the tree");

        JComboBox treeTypesComboBox = new JComboBox<String>(TreeType.values());

        JLabel insertLabel = new JLabel("Insert the value");

        JTextField valueToOperateTextField = new JTextField(1);

        JPanel buttons = new JPanel();
            buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));


            JButton searchButton = new JButton("Search");
                searchButton.addActionListener((e) ->
                {
                    MessageWindow.show(serverClient.sendOnServer((String)treeTypesComboBox.getSelectedItem(), ActionType.Search, valueToOperateTextField.getText()));
                });

            JButton insertButton = new JButton("Insert");
                insertButton.addActionListener((e) ->
                {
                    MessageWindow.show(serverClient.sendOnServer((String)treeTypesComboBox.getSelectedItem(), ActionType.Insert, valueToOperateTextField.getText()));
                });

            JButton deleteButton = new JButton("Delete");
                deleteButton.addActionListener((e) ->
                {
                    MessageWindow.show(serverClient.sendOnServer((String)treeTypesComboBox.getSelectedItem(), ActionType.Delete, valueToOperateTextField.getText()));
                });

            JButton drawButton = new JButton("Draw");
                drawButton.addActionListener((e) ->
                {
                    MessageWindow.show(serverClient.sendOnServer((String)treeTypesComboBox.getSelectedItem(), ActionType.Draw, null));
                });

            buttons.add(searchButton);
            buttons.add(insertButton);
            buttons.add(deleteButton);
            buttons.add(drawButton);
            for (Component comp : buttons.getComponents()) 
            {
                ((JComponent)comp).setAlignmentX(Component.CENTER_ALIGNMENT);
            }

        add(typeOfTreeLabel);
        add(treeTypesComboBox);
        add(insertLabel);
        add(valueToOperateTextField);
        add(buttons);
        for (Component comp : getComponents()) 
        {
            ((JComponent)comp).setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        setVisible(true);
        serverClient = new ServerClient();
    }
}