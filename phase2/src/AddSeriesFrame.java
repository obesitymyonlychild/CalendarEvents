import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class AddSeriesFrame extends JFrame implements ActionListener {

    JButton addButton = new JButton("add");
    JButton backButton = new JButton("back");

    Container container = getContentPane();
    JLabel nameLabel = new JLabel("SERIES NAME");
    JTextField nameTextField = new JTextField();




    AddSeriesFrame() {
        this.setTitle("Add New Series");
        this.setVisible(true);
        this.setBounds(10, 10, 500, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

        addButton.addActionListener(this);
        backButton.addActionListener(this);


    }
    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        nameLabel.setBounds(50, 150, 100, 30);

        nameTextField.setBounds(150, 150, 150, 30);

        addButton.setBounds(100, 500, 100, 30);
        backButton.setBounds(300, 500, 100, 30);

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
            SeriesFrame seriesFrame = new SeriesFrame();
        }

        if(e.getSource() == addButton){
            String name = nameTextField.getText();
            CalendarFacade.getCurrentUser().createSeries(name);
            JOptionPane.showMessageDialog(this, "Series added!");
            nameTextField.setText("");

        }
    }
}

