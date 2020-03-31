import java.awt.*;        // Using AWT layouts
import java.awt.event.*;  // Using AWT event classes and listener interfaces
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;     // Using Swing components and containers
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MemoFrame extends JFrame implements ActionListener, ListSelectionListener {

    Container container = getContentPane();
    JList<String> memoList = new JList<>();
    JList<String> eventList = new JList<>();
    JLabel memosLabel = new JLabel("memos");
    JLabel eventsLabel = new JLabel("events");
    JTextField memoContentTextField = new JTextField();
    JButton goButton = new JButton("GO");
    JButton backButton = new JButton("BACK");
    JLabel memoContentLabel = new JLabel("MEMO CONTENT");
    ArrayList<Event> events = new ArrayList<>();
    Memo selectedMemo;
    Event selectedEvent;

    MemoFrame() {
        this.setTitle("Memo");
        this.setVisible(true);
        this.setBounds(10, 10, 650, 550);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        ArrayList<Memo> memos = CalendarFacade.showMemo();
        String[] memoString = new String[memos.size()];
        for (int j = 0; j < memos.size(); j++) {
            memoString[j] = memos.get(j).toString();
        }
        memoList.setListData(memoString);
    }

    private void setLayoutManager() {
        container.setLayout(null);
    }

    private void setLocationAndSize() {
        memosLabel.setBounds(30, 30, 100, 30);
        eventsLabel.setBounds(200, 30, 100, 30);
        memoContentTextField.setBounds(30, 450, 400, 50);
        memoList.setBounds(30, 60, 200, 250);
        eventList.setBounds(200, 60, 200, 250);
        goButton.setBounds(440, 60, 80, 30);
        backButton.setBounds(440, 100, 80, 30);
        memoContentLabel.setBounds(30, 350, 400, 50);

    }

    private void addComponentsToContainer() {
        container.add(memosLabel);
        container.add(eventsLabel);
        container.add(memoList);
        container.add(eventList);
        container.add(goButton);
        container.add(backButton);
        container.add(memoContentTextField);
    }

    private void addActionEvent() {
        memoList.addListSelectionListener(this);
        goButton.addActionListener(this);
        backButton.addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == goButton) {
            int i = eventList.getSelectedIndex();
            EditEventFrame targetEventFrame = new EditEventFrame(events.get(i));
            // need to set some default
        }
        if (e.getSource() == backButton) {
            this.dispose();

        }
    }

    public void valueChanged(ListSelectionEvent e) {
        String targetMemoString = memoList.getSelectedValue();
        events = CalendarFacade.searchEventByMemo(targetMemoString);
        String[] eventString;
        eventString = new String[events.size()];
        for (int i = 0; i < events.size(); i++) {
            eventString[i] = events.get(i).toString();
        }
        eventList.setListData(eventString);
        memoContentTextField.setText(CalendarFacade.getMemoContent(targetMemoString));

    }
}
