
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.*;

public class DriverPanel implements FileTabHand{
    JPanel DriPan = new JPanel();
    JTable DriTab = new JTable();
    File DriFile = new File("Driver.txt");
    JButton UpButton = new JButton();
    JButton AddDriButton = new JButton();
    JButton RemDriverButton = new JButton();
    DefaultTableModel model = (DefaultTableModel)DriTab.getModel();
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
    JScrollPane Scroll = new JScrollPane(DriTab);
    DriverPanel() throws IOException {

        DriPan.setBounds(0,0,600,600);
        DriPan.setBackground(new Color(0, 160, 200));

        UpButton.setBounds(150,0,100,50);
        UpButton.setText("Update");
        UpButton.addActionListener(e -> {
            try {
                Update();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        AddDriButton.setBounds(300,0,100,50);
        AddDriButton.setText("Add Driver");
        AddDriButton.addActionListener(e -> Add());

        RemDriverButton.setBounds(450,0,100,50);
        RemDriverButton.setText("Remove Driver");
        RemDriverButton.addActionListener(e -> Remove());

        DriTab.setBounds(100,100,500,500);
        DriTab.setRowSorter(sorter);

        Scroll.setBounds(0,50,500,500);

        BufferedReader br = new BufferedReader(new FileReader(DriFile));

        Object[] tablines = br.lines().toArray();
        String[] ColName = {"ID","Name","Phone Number","Salary","Route"};
        model.setColumnIdentifiers(ColName);

        JTableHeader Theader = DriTab.getTableHeader();
        Theader.setBackground(Color.red);
        Theader.setForeground(Color.white);


        for(int i = 0; i < tablines.length;i++){
            String line = tablines[i].toString().trim();
            String[] dataRow = line.split(",");
            model.addRow(dataRow);
        }

    }

    public void Remove() {
        int i = DriTab.getSelectedRow();
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
            if(!DriFile.exists()){
                DriFile.createNewFile();
            }
            FileWriter fw = new FileWriter(DriFile.getAbsolutePath());
            BufferedWriter bw = new BufferedWriter(fw);

            for(int i = 0;i<DriTab.getRowCount();i++){
                for(int j = 0;j<DriTab.getColumnCount();j++){
                    String temp = ((String)DriTab.getModel().getValueAt(i,j));
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

