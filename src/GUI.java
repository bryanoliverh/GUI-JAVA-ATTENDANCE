/**
 * Class GUI
 *
 * @author Bryan Oliver
 * @version 24.5.2021
 */
import java.awt.*;
import java.awt.event.*;
import java.io.ObjectStreamException;
import java.time.MonthDay;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;

public class GUI extends JFrame {

    JFrame frame = new JFrame();
    JTable table = new JTable();

    DefaultTableModel tableModel = new DefaultTableModel();

    Panel panela = new Panel();
    JScrollPane panelb = new JScrollPane(table);


    JTextField NameField = new JTextField();
    JTextField DayField = new JTextField();
    JTextField MonthField = new JTextField();
    JTextField YearField = new JTextField();
    JTextField ArriveField = new JTextField();
    JTextField EndField = new JTextField();


    JButton submitButton = new JButton("Submit");
    JButton clearButton = new JButton("Clear");

    public GUI() {
        panela.setLayout(new GridLayout(10, 20));
        panela.add(new JLabel("Name: "));
        panela.add(NameField);
        panela.add(new JLabel("Date of Work (DD): "));
        panela.add(DayField);
        panela.add(new JLabel("Date of Work (MM): "));
        panela.add(MonthField);
        panela.add(new JLabel("Date of Work (YYYY): "));
        panela.add(YearField);
        panela.add(new JLabel("Arrive Time : "));
        panela.add(ArriveField);
        panela.add(new JLabel("End Time : "));
        panela.add(EndField);

        panela.add(submitButton);
        panela.add(clearButton);

        Object[] columns = { "Name", "Day","Month", "Year", "Arrived Time", "Finished Time" };

        tableModel.setColumnIdentifiers(columns);
        table.setModel(tableModel);
        panelb.setPreferredSize(new Dimension(800, 200));

        frame.setLayout(new FlowLayout());
        frame.add(panela);
        frame.add(panelb);

        frame.setTitle("Employee Attendance Form");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        submitButton.addActionListener(submitButtonListener);
        clearButton.addActionListener(clearButtonListener);
    }

    ActionListener submitButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            Object[] row = new Object[6];
            try {
                row[0] = checkIfEmpty(NameField.getText());
                row[1] = Integer.parseInt(checkIfEmpty(DayField.getText()));
                row[2] = Integer.parseInt(checkIfEmpty(MonthField.getText()));
                row[3] = Integer.parseInt(checkIfEmpty(YearField.getText()));
                row[4] = checkIfEmpty(ArriveField.getText());
                row[5] = checkIfEmpty(EndField.getText());
                tableModel.addRow(row);
            } catch (ExceptionBlankInput | NumberFormatException var) {
                JOptionPane.showMessageDialog(null, var.toString(), "Input Not Found", JOptionPane.ERROR_MESSAGE);
            }
        }
    };

    ActionListener clearButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            int rowCount = tableModel.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                tableModel.removeRow(i);
            }
        }
    };

    String checkIfEmpty(String input) throws ExceptionBlankInput{
        if (input.equals("")){
            throw new ExceptionBlankInput();
        }else{
            return input;
        }
    }
}
