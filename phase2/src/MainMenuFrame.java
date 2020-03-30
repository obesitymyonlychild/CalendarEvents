import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.Future;

public class MainMenuFrame extends JFrame implements ActionListener {
    Container container = getContentPane();
    JLabel todayEvents = new JLabel("Today's events");
    JButton pastButton = new JButton("Past Event");
    JButton ongoingButton = new JButton("Ongoing Event");
    JButton futureButton = new JButton("Future Event");
    JButton allEventButton = new JButton("All Event");
    JButton seriesButton = new JButton("Series");
    JButton memoButton = new JButton("Memo");
    JButton searchButton = new JButton("Search");
    JButton addEventButton = new JButton("Add Event");
    JButton logoutButton = new JButton("logout");


    MainMenuFrame(){
        this.setTitle("Main Menu");
        this.setVisible(true);
        this.setBounds(10, 10, 800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager(){
        container.setLayout(null);
    }

    public void setLocationAndSize(){
        pastButton.setBounds(50, 150, 150, 30);
        ongoingButton.setBounds(180, 150, 200, 30);
        futureButton.setBounds(360, 150, 150, 30);
        allEventButton.setBounds(550, 150, 150, 30);
        seriesButton.setBounds(200, 300, 100, 30);
        memoButton.setBounds(400, 300, 100, 30);
        searchButton.setBounds(200, 450, 200, 50);
        addEventButton.setBounds(400, 450, 200, 50);
        logoutButton.setBounds(400, 600, 100, 20);
        ArrayList<Event> today = CalendarFacade.showTodayEvents();
        todayEvents.setText("Today's events: \n"+today.toString());
        todayEvents.setBounds(300, 700, 200, 30);
    }

    public void addComponentsToContainer(){
        container.add(pastButton);
        container.add(ongoingButton);
        container.add(futureButton);
        container.add(allEventButton);
        container.add(seriesButton);
        container.add(memoButton);
        container.add(searchButton);
        container.add(addEventButton);
        container.add(logoutButton);
        container.add(todayEvents);
    }

    public void addActionEvent(){
        pastButton.addActionListener(this);
        ongoingButton.addActionListener(this);
        futureButton.addActionListener(this);
        allEventButton.addActionListener(this);
        seriesButton.addActionListener(this);
        memoButton.addActionListener(this);
        searchButton.addActionListener(this);
        addEventButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding part of past
        if(e.getSource() == pastButton){
            PastEventFrame past = new PastEventFrame();
        }

        if(e.getSource() == ongoingButton){
            //wrong spelling in class ongoing event frame.
            OngingEventFrame ing = new OngingEventFrame();
        }

        if(e.getSource() == futureButton){
            FutureEventFrame fut = new FutureEventFrame();
        }

        if(e.getSource() == allEventButton){
            //
        }

        if(e.getSource() == seriesButton){
            JOptionPane.showMessageDialog(this, "hiya");
        }

        if(e.getSource() == memoButton){
            JOptionPane.showMessageDialog(this, "hiya");
        }

        if(e.getSource() == searchButton){
            JOptionPane.showMessageDialog(this, "hiya");
        }

        if(e.getSource() == addEventButton){
            AddEventFrame newEvent = new AddEventFrame();
        }


        if (e.getSource() == logoutButton){
            CalendarFacade.setCurrentUser(null);
            this.dispose();
            try {
                LoginSystem.login();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
