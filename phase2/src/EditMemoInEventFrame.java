import com.sun.scenario.effect.impl.sw.java.JSWBlend_COLOR_BURNPeer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditMemoInEventFrame extends MemoFrame implements ActionListener, ListSelectionListener {

    Event event;
    JButton addToButton = new JButton("Add To Existed Memo");
    JButton addMemoButton = new JButton();

    EditMemoInEventFrame(Event event) {
        super();
        this.event = event;
        memos = event.getMemos();
        container.add(addToButton);
        container.add(addMemoButton);
        addMemoButton.setText("Add A New Memo");
        addToButton.addActionListener(this);
        addMemoButton.addActionListener(this);
        addToButton.setBounds(460, 180, 200, 30);
        addMemoButton.setBounds(460, 300, 180, 30);
    }

    public void actionPerformed(ActionEvent e) {
        String memoName = memoList.getSelectedValue();
        if (e.getSource() == goButton) {
            int i = eventList.getSelectedIndex();
            EditEventFrame targetEventFrame = new EditEventFrame(events.get(i));
            // need to set some default
        }
        if (e.getSource() == backButton) {
            this.dispose();
            EditEventFrame ed = new EditEventFrame(event);
        }
        if (e.getSource() == addMemoButton) {
            AddMemoFrame addMemoFrame = new AddMemoFrame(event);
            this.dispose();
        }

        //delete this memo in this event and delete the event from that memo
        if (e.getSource() == deleteMemoButton) {
            int i = eventList.getSelectedIndex();
            event.deleteMemo(memos.get(i).getName());
            this.dispose();
            EditMemoInEventFrame ed = new EditMemoInEventFrame(event);
        }

        if(e.getSource() == addToButton){
            SelectMemoFrame sm = new SelectMemoFrame(event);
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
