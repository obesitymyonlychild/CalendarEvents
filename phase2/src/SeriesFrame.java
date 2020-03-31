import java.awt.*;        // Using AWT layouts
import java.awt.event.*;  // Using AWT event classes and listener interfaces
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;     // Using Swing components and containers
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SeriesFrame extends JFrame implements ActionListener, ListSelectionListener {

    Container container = getContentPane();
    JList<String> seriesList = new JList<>();
    JList<String> eventList = new JList<>();
    JLabel seriesLabel = new JLabel("series");
    JLabel eventsLabel = new JLabel("events");
//    JTextField seriesContentTextField = new JTextField();
    JButton goButton = new JButton("go to series");
    JButton backButton = new JButton("back");

    JButton addSeriesButton = new JButton("add series");
    JButton deleteSeriesButton = new JButton("delete series");

    ArrayList<Event> events = new ArrayList<>();
//    Series selectedSeries;
//    Event selectedEvent;


    SeriesFrame() {
        this.setTitle("Series");
        this.setVisible(true);
        this.setBounds(10, 10, 700, 550);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        ArrayList<Series> series = CalendarFacade.getCurrentUser().getSeries();
        String[] seriesString = new String[series.size()];
        for (int j = 0; j < series.size(); j++) {
            seriesString[j] = series.get(j).toString();
        }
        seriesList.setListData(seriesString);
    }

    private void setLayoutManager() {
        container.setLayout(null);
    }

    private void setLocationAndSize() {
        seriesLabel.setBounds(100, 30, 100, 30);
        eventsLabel.setBounds(300, 30, 100, 30);
//        seriesContentTextField.setBounds(30, 450, 400, 90);
        seriesList.setBounds(30, 60, 200, 350);
        eventList.setBounds(270, 60, 200, 350);
        goButton.setBounds(500, 30, 130, 30);
        backButton.setBounds(500, 90, 80, 30);
        addSeriesButton.setBounds(500, 150, 80, 30);
        deleteSeriesButton.setBounds(100, 90, 80, 30);


    }

    private void addComponentsToContainer() {
        container.add(seriesLabel);
        container.add(eventsLabel);
        container.add(seriesList);
        container.add(eventList);
        container.add(goButton);
        container.add(backButton);
        container.add(addSeriesButton);
        container.add(deleteSeriesButton);
//        container.add(seriesContentTextField);
    }

    private void addActionEvent() {
        seriesList.addListSelectionListener(this);
        goButton.addActionListener(this);
        backButton.addActionListener(this);
        addSeriesButton.addActionListener(this);
        deleteSeriesButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == goButton) {
            int i = seriesList.getSelectedIndex();

            ArrayList<Series> series = CalendarFacade.getCurrentUser().getSeries();
            String seriesName = series.get(i).getName();


//            ArrayList<Event> events = CalendarFacade.searchEventBySeriesName(seriesName);
//            String[] eventsString = new String[events.size()];
//            for (int i = 0; i < events.size(); i++) {
//                eventsString[i] = events.get(i).toString();
//            }
//            eventList.setListData(eventsString);
//        }


//            ArrayList<Series> series = CalendarFacade.getCurrentUser().getSeries();
            try {
                for (Series value : series) {
                    if (value.getName().equals(seriesName)) {
                        ArrayList<Event> events = value.getEvents();
                        String[] eventsString = new String[events.size()];
                        for (int j = 0; j < events.size(); j++) {
                            eventsString[j] = events.get(j).toString();
                        }
                        eventList.setListData(eventsString);
                    }

                }
            }
            catch (IndexOutOfBoundsException ee){
                JOptionPane.showMessageDialog(this, "No series!");

            }
        }


        if (e.getSource() == backButton) {
            this.dispose();

        }

        if (e.getSource() == addSeriesButton) {
            AddSeriesFrame newSeries = new AddSeriesFrame();

            ArrayList<Series> series = CalendarFacade.getCurrentUser().getSeries();
            String[] seriesString = new String[series.size()];
            for (int j = 0; j < series.size(); j++) {
                seriesString[j] = series.get(j).toString();
            }
            seriesList.setListData(seriesString);

        }

        if (e.getSource() == deleteSeriesButton) {
            int i = seriesList.getSelectedIndex();
            ArrayList<Series> series = CalendarFacade.getCurrentUser().getSeries();
            series.remove(i);
            String[] seriesString = new String[series.size()];
            for (int j = 0; j < series.size(); j++) {
                seriesString[j] = series.get(j).toString();
            }
            seriesList.setListData(seriesString);

        }


    }


    public void valueChanged(ListSelectionEvent e) {
        String targetSeriesString = seriesList.getSelectedValue();
        events = CalendarFacade.searchEventBySeriesName(targetSeriesString);
        String[] eventString;
        eventString = new String[events.size()];
        for (int i = 0; i < events.size(); i++) {
            eventString[i] = events.get(i).toString();
        }
        eventList.setListData(eventString);


    }
}




