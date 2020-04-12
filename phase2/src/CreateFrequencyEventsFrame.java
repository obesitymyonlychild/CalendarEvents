import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class CreateFrequencyEventsFrame extends AddEventFrame implements ActionListener {
    JLabel frequencyLabel = new JLabel("Frequency of the event");
    JTextField frequencyTextField = new JTextField();
    JLabel hoursApartLabel = new JLabel("How many hours between two adjacent events");
    JTextField hoursApartTextField = new JTextField();
    String seriesName = null;

    CreateFrequencyEventsFrame(String seriesName){
        super();
        this.setTitle("Add Frequency Event");

        frequencyLabel.setBounds(50, 400, 150, 20);
        frequencyTextField.setBounds(210, 400, 40, 25);
        container.add(frequencyLabel);
        container.add(frequencyTextField);

        hoursApartLabel.setBounds(50, 430, 300, 20);
        hoursApartTextField.setBounds(360, 430, 40, 25);
        container.add(hoursApartLabel);
        container.add(hoursApartTextField);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        //Coding part of go back button
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


        }

        if(e.getSource() == addButton){
            String name = nameTextField.getText();
            String startTime = dateTextField.getText();
            int duration = Integer.parseInt(durationTextField.getText());
            String address = addressTextField.getText();
            int fre = Integer.parseInt(frequencyTextField.getText());
            int hours = Integer.parseInt(frequencyTextField.getText());

            try {
                CalendarFacade.getCurrentCalendar().createFrequencyEvent(seriesName, name, startTime, duration,
                        address, fre, hours);

                JOptionPane.showMessageDialog(this, "Event added!");
                nameTextField.setText("");
                dateTextField.setText("");
                durationTextField.setText("");
                addressTextField.setText("");
            } catch (Exception e1){
                JOptionPane.showMessageDialog(this, "Wrong input!");
            }
            nameTextField.setText("");
            dateTextField.setText("");
            durationTextField.setText("");
            addressTextField.setText("");
            frequencyTextField.setText("");
            hoursApartTextField.setText("");
        }
    }





    }

