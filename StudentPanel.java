import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.*;

public class StudentPanel implements FileTabHand{
    JPanel StuPan = new JPanel();
    JTable StuTab = new JTable();
    File StuFile = new File("Students.txt");
    JButton UpButton = new JButton("Update");
    JButton AddStuButton = new JButton("Add Student");
    JButton RemStuButton = new JButton("Remove Student");
    DefaultTableModel model = (DefaultTableModel)StuTab.getModel();
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
    JScrollPane Scroll = new JScrollPane(StuTab);

    StudentPanel() throws FileNotFoundException {
        StuPan.setBounds(0,0,600,600);
        StuPan.setBackground(new Color(0, 160, 200));

        UpButton.setBounds(150,0,100,50);
        UpButton.addActionListener(e -> {
            try {
                Update();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        AddStuButton.setBounds(300,0,100,50);
        AddStuButton.addActionListener(e -> Add());

        RemStuButton.setBounds(450,0,100,50);
        RemStuButton.addActionListener(e -> Remove());

        StuTab.setBounds(0,0,500,500);
        StuTab.setRowSorter(sorter);

        Scroll.setBounds(0,50,500,500);

        BufferedReader br = new BufferedReader(new FileReader(StuFile));

        Object[] tablines = br.lines().toArray();

        Object[] ColName = {"ID","Name","Phone Number","Email","Route"};
        model.setColumnIdentifiers(ColName);

        JTableHeader Theader = StuTab.getTableHeader();
        Theader.setBackground(Color.red);
        Theader.setForeground(Color.white);

        for(int i = 0; i < tablines.length;i++){
            String line = tablines[i].toString().trim();
            String[] dataRow = line.split(",");
            model.addRow(dataRow);
        }

    }

    public void Remove() {
        int i = StuTab.getSelectedRow();
        if(i>=0){
            model.removeRow(i);
            JOptionPane.showMessageDialog(null,"Deleted Succesfully");
        }
        else{
            JOptionPane.showMessageDialog(null,"Please Select a row first");
        }
    }

    public void Add(){
        model.addRow(new Object[]{});
    }

    public void Update() throws IOException {
        if(UniqueCheck()){
            JOptionPane.showMessageDialog(null,"The update was Successful");
            if(!StuFile.exists()){
                StuFile.createNewFile();
            }
            FileWriter fw = new FileWriter(StuFile.getAbsolutePath());
            BufferedWriter bw = new BufferedWriter(fw);

            for(int i = 0;i<StuTab.getRowCount();i++){
                for(int j = 0;j<StuTab.getColumnCount();j++){
                    String temp = ((String)StuTab.getModel().getValueAt(i,j));
                    if(temp!=null){
                        if(j!=4){
                            bw.write(temp + ",");
                        }
                        else{
                            bw.write(temp);
                        }
                    }
                }
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
    }

    public boolean UniqueCheck(){
        for(int i=0;i<model.getRowCount();i++){
            Object obj = model.getValueAt(i,0);
            for(int j=0;j<model.getRowCount();j++){
                if(i!=j){
                    if(obj.equals(model.getValueAt(j,0))){
                        JOptionPane.showMessageDialog(null,obj+" ID already exists");
                        return false;
                    }
                }
            }
        }
        return true;
    }
}