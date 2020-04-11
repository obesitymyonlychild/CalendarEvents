import sun.misc.JavaLangAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EditEventFrame extends JFrame implements ActionListener{


    // i want this event info on this edit page or not
    Container container = getContentPane();
    //panel should be added to show the
    JLabel info = new JLabel("Event Info:");
    JLabel name = new JLabel();
    JLabel start = new JLabel();
    JLabel duration = new JLabel();
    JLabel ads = new JLabel();
    JLabel userLabel = new JLabel("users:");
    JLabel tagLabel = new JLabel("tags:");
    JLabel alertLabel = new JLabel("alerts");
    JLabel onLabel = new JLabel();

    JList<String> tagList = new JList<>();
    JList<String> alertList = new JList<>();
    JList<String> userList = new JList<>();

    ArrayList<String> tags = new ArrayList<>();
    ArrayList<Alert> alerts = new ArrayList<>();
    ArrayList<String> users = new ArrayList<>();

    JTextField userText = new JTextField();
    JTextField tagText = new JTextField();
    JTextField alertText = new JTextField();

    JButton editEventButton = new JButton("EDIT");
    JButton deleteEventButton = new JButton("DELETE");
    JButton addTagButton = new JButton("ADD");
    JButton tagDeleteButton = new JButton("DELETE");
    JButton memoDeleteButton = new JButton("DELETE");
    JButton viewMemoButton = new JButton("VIEW MEMOS");
    JButton addAlertButton = new JButton("ADD");
    JButton alertDeleteButton = new JButton("DELETE");
    JButton turnOnOrOffButton = new JButton("ON/OFF");
    private Event event;
    JButton backButton = new JButton("BACK");
    JButton userButton = new JButton("INVITE");



    EditEventFrame(Event event) {
        this.event = event;
        this.setTitle("Edit Event");
        this.setVisible(true);
        this.setBounds(10, 10, 500, 800);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

        tags = event.getTags();
        String[] tagString = new String[tags.size()];
        for (int j = 0; j < tags.size(); j++) {
            tagString[j] = tags.get(j);
        }
        tagList.setListData(tagString);

        users = event.getUsers();
        String[] userString = new String[users.size()];
        for (int j = 0; j < users.size(); j++) {
            userString[j] = users.get(j);
        }
        userList.setListData(userString);

        alerts = event.getAlerts();
        String[] alertString = new String[alerts.size()];
        for (int j = 0; j < alerts.size(); j++) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String start = alerts.get(j).getStartTime().format(formatter);
            alertString[j] = start;
        }
        alertList.setListData(alertString);
        boolean on = event.isAlertOn();
        if (on) {
            onLabel.setText("Alerts On");
        }
        else{
            onLabel.setText("Alerts Off");
        }
        name.setText(event.getName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String time = event.getStartTime().format(formatter);
        start.setText(time);
        duration.setText(Integer.toString(event.getDuration()));
        ads.setText(event.getAddress());

    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Event getEvent(){
        return this.event;
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        info.setBounds(50, 30, 200, 30);
        name.setBounds(50, 80, 200, 30);
        start.setBounds(50, 130, 200, 30);
        duration.setBounds(50, 180, 200, 30);
        ads.setBounds(50, 230, 200,  30);
        editEventButton.setBounds(250, 90, 100,30);
        deleteEventButton.setBounds(250, 140, 100, 30);
        viewMemoButton.setBounds(250, 190, 150, 30);
        alertLabel.setBounds(50, 270, 100, 30);
        alertText.setBounds(50, 300, 200, 70);
        addAlertButton.setBounds(250, 310, 80, 30);
        alertDeleteButton.setBounds(350, 310, 100, 30);
        onLabel.setBounds(270, 350, 100, 30);
        turnOnOrOffButton.setBounds(350, 350, 80, 30);
        tagLabel.setBounds(50, 400, 100, 30);
        tagText.setBounds(50, 430, 200, 70);
        addTagButton.setBounds(250, 440, 80, 30);
        tagDeleteButton.setBounds(250, 480, 100, 30);
        userLabel.setBounds(50, 530, 100, 30);
        userText.setBounds(50, 560, 200, 70);
        userButton.setBounds(250,570 ,100, 30);
        backButton.setBounds(250, 700, 100, 30);


    }

    public void addComponentsToContainer() {
        container.add(deleteEventButton);
        container.add(editEventButton);
        container.add(alertDeleteButton);
        container.add(turnOnOrOffButton);
        container.add(editEventButton);
        container.add(backButton);
        container.add(tagDeleteButton);
        container.add(memoDeleteButton);
        container.add(alertDeleteButton);
        container.add(userButton);
        container.add(userLabel);
        container.add(tagLabel);
        container.add(alertLabel);
        container.add(info);
        container.add(name);
        container.add(start);
        container.add(duration);
        container.add(ads);
        container.add(tagList);
        container.add(alertList);
        container.add(viewMemoButton);
        container.add(userList);
        container.add(alertList);
        container.add(userText);
        container.add(alertText);
        container.add(tagText);
        container.add(addTagButton);
        container.add(onLabel);
        container.add(addAlertButton);
    }

    public void addActionEvent() {
        deleteEventButton.addActionListener(this);
        editEventButton.addActionListener(this);
        alertDeleteButton.addActionListener(this);
        addAlertButton.addActionListener(this);
        userButton.addActionListener(this);
        tagDeleteButton.addActionListener(this);
        backButton.addActionListener(this);
        viewMemoButton.addActionListener(this);
        addTagButton.addActionListener(this);
    }

    static ArrayList<String> Users = new ArrayList<String>();

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == deleteEventButton){
            CalendarFacade.getCurrentCalendar().deleteEvent(event.getName());
            System.out.println("Event Deleted");
        }

        if(e.getSource() == editEventButton){
            ModifyEventFrame mod = new ModifyEventFrame(event);
        }

        if(e.getSource() == userButton){
            JFrame frame = new JFrame();
            String result = JOptionPane.showInputDialog(frame, "Enter friend's name:");
            try {
                event.addUser(result);
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Can't find this user!");
            }
        }

        if(e.getSource() == viewMemoButton){
            MemoFrame mf = new MemoFrame();
            mf.memos = event.getMemos();
        }

        if (e.getSource() == tagDeleteButton){
            int i = tagList.getSelectedIndex();
            event.deleteTag(tags.get(i));
            JOptionPane.showMessageDialog(this,"Tag Deleted");
            this.dispose();
            EditEventFrame edit = new EditEventFrame(event);
        }

        if(e.getSource() == addTagButton){
            JFrame frame = new JFrame();
            String result = JOptionPane.showInputDialog(frame, "New Tag Name:");
            event.addTag(result);
            JOptionPane.showMessageDialog(this, "Tag Added");
            this.dispose();
            EditEventFrame edit = new EditEventFrame(event);
        }

        if(e.getSource() == addAlertButton){
            AlertFrame fr = new AlertFrame(event);
        }


        if (e.getSource()==alertDeleteButton){
            int i = alertList.getSelectedIndex();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String start = alerts.get(i).getStartTime().format(formatter);
            event.deleteAlert(start);
            this.dispose();
            EditEventFrame edit = new EditEventFrame(event);
        }


        if(e.getSource()==turnOnOrOffButton){
            String res = "ON";
            if(event.isAlertOn()){
                event.turnOffAlert();
                res = "OFF";
            }
            else{
                event.turnOnAlert();
            }
            onLabel.setText(res);
        }

        //update for all users regarding this event due to changes made.
        if(e.getSource() == backButton){
            try {
                event.updateSharedEvent();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            this.dispose();

        }

    }



}

