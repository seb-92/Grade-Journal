package pakage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class GradeBookPage extends JFrame {

    private JPanel mainPage;
    private JButton addButton;
    private JButton deleteButton;
    private JButton sortButton;
    private JTable GradeTable;
    private JTextField NameField;
    private JTextField GradeField;
    private JLabel AvgLabel;
    private JLabel gradeErrorLabel;
    private JLabel nameErrorLabel;
    private final DefaultTableModel model = new DefaultTableModel();

    public GradeBookPage(){
        setupFrame();
        addButton.addActionListener(e -> addGradeToTable());
        deleteButton.addActionListener(e -> removeGrade());
        sortButton.addActionListener(e -> sortGrade());
        NameField.addActionListener(e -> GradeField.grabFocus());
        GradeField.addActionListener(e -> addGradeToTable());
    }

    private void setupFrame(){
        setContentPane(mainPage);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(450, 500));
        setVisible(true);
        GradeTable.setModel(model);
        model.setColumnCount(2);
    }

    private void addGradeToTable(){
        String name = null;
        Integer gradeInt = null;

        try {
            name = Validators.getName(NameField.getText());
            nameErrorLabel.setText("");
        } catch (GradeBookExceptions f){
            nameErrorLabel.setText(f.toString());
        }

        try {
            gradeInt = Validators.getGrade(GradeField.getText());
            gradeErrorLabel.setText("");
        } catch (GradeBookExceptions e) {
            gradeErrorLabel.setText(e.toString());
        }

        if (gradeInt != null && name != null){
            addRowToTable(name, gradeInt);
            updateAvg();
            NameField.setText("");
            GradeField.setText("");
            NameField.grabFocus();
        } else if (name == null){
            NameField.grabFocus();
        } else {
            GradeField.grabFocus();
        }
    }

    private void removeGrade(){
        if(GradeTable.getSelectedRow() != -1)
        {
            model.removeRow(GradeTable.getSelectedRow());
        }
        updateAvg();
    }

    private void sortGrade(){
        int rowCount = model.getRowCount();
        ArrayList<String> NameList = new ArrayList<>();
        ArrayList<Integer> GradeList = new ArrayList<>();
        while(rowCount != 0) {
            String name = (String) model.getValueAt(0, 0);
            int grade = (Integer) model.getValueAt(0, 1);
            int number = 0;
            for (int i = 1; i < rowCount; i++) {
                if ((Integer) model.getValueAt(i, 1) > grade) {
                    name = (String) model.getValueAt(i, 0);
                    grade = (Integer) model.getValueAt(i, 1);
                    number = i;
                }
            }
            model.removeRow(number);
            NameList.add(name);
            GradeList.add(grade);
            rowCount = model.getRowCount();
        }
        while(!NameList.isEmpty()){
            addRowToTable(NameList.get(0), GradeList.get(0));
            NameList.remove(0);
            GradeList.remove(0);
        }
    }

    private void addRowToTable(String name, Integer grade){
        Object[] row = {name, grade};
        model.addRow(row);
    }

    private void updateAvg(){
        Integer sum = 0;
        int rowCount = model.getRowCount();
        if (rowCount==0){
            AvgLabel.setText(String.format("Średnia: 0"));
        }
        else {
            for (int i = 0; i < rowCount; i++) {
                sum += (Integer) model.getValueAt(i, 1);
            }
            Double avg = sum.doubleValue() / rowCount;
            AvgLabel.setText(String.format("Średnia: %.2f", avg));
        }
    }
}
