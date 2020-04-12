import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;


public class AddMemoFrame extends JFrame implements ActionListener {

    JButton addButton = new JButton("add");
    JButton backButton = new JButton("back");

    Container container = getContentPane();
    JLabel nameLabel = new JLabel("MEMO NAME");
    JTextField nameTextField = new JTextField();
    JLabel contentLabel = new JLabel("MEMO CONTENT");
    JTextField contextText = new JTextField();


    AddMemoFrame() {
        this.setTitle("Add New Memo");
        this.setVisible(true);
        this.setBounds(10, 10, 500, 400);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }
    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        nameLabel.setBounds(50, 50, 100, 30);
        nameTextField.setBounds(50, 80, 200, 30);
        contentLabel.setBounds(50, 180, 150, 30);
        contextText.setBounds(50, 210, 200, 30);

        addButton.setBounds(100, 350, 100, 30);
        backButton.setBounds(300, 350, 100, 30);

    }

    private void addComponentsToContainer() {
        container.add(addButton);
        container.add(backButton);
        container.add(nameLabel);
        container.add(nameTextField);
    }

    public void addActionEvent() {
        addButton.addActionListener(this);
        backButton.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        //Coding part of add and back button
        if (e.getSource() == backButton){
            ObjectOutputStream os = null;
            try {
                os = new ObjectOutputStream(new FileOutputStream(CalendarFacade.getCurrentUser().getName()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                assert os != null;
                os.writeObject(CalendarFacade.getCurrentUser());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                os.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            this.dispose();
            MemoFrame mf = new MemoFrame();
        }

        if(e.getSource() == addButton){
            String name = nameTextField.getText();
            String content = contextText.getText();
            // we store currentCalendar in CalendarFacade now  --- by Oliver
            CalendarFacade.getCurrentCalendar().createMemo(name, content);
            JOptionPane.showMessageDialog(this, "Memo added!");
            nameTextField.setText("");
            contextText.setText("");

        }

    }






}

