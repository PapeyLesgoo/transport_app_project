import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.*;

public class RoutePanel implements FileTabHand{
    JPanel RouPan = new JPanel();
    JTable RouTab = new JTable();
    File RouFile = new File("Routes.txt");
    JButton UpButton = new JButton("Update");
    JButton AddDRouButton = new JButton("Add Route");
    JButton RemRouButton = new JButton("Remove Route");
    DefaultTableModel model = (DefaultTableModel)RouTab.getModel();
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
    JScrollPane Scroll = new JScrollPane(RouTab);

    RoutePanel() throws FileNotFoundException {
        RouPan.setBounds(0,0,600,600);
        RouPan.setBackground(new Color(0, 160, 200));

        UpButton.setBounds(150,0,100,50);
        UpButton.addActionListener(e -> {
            try {
                Update();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        AddDRouButton.setBounds(300,0,100,50);
        AddDRouButton.addActionListener(e -> Add());

        RemRouButton.setBounds(450,0,100,50);
        RemRouButton.addActionListener(e -> Remove());

        RouTab.setBounds(0,0,600,500);
        RouTab.setRowSorter(sorter);

        Scroll.setBounds(0,50,600,500);

        BufferedReader br = new BufferedReader(new FileReader(RouFile));

        Object[] tablines = br.lines().toArray();

        Object[] ColName = {"Route","6:50","7:00","7:10","7:20","7:30","7:45"};
        model.setColumnIdentifiers(ColName);

        JTableHeader Theader = RouTab.getTableHeader();
        Theader.setBackground(Color.red);
        Theader.setForeground(Color.white);

        for(int i = 0; i < tablines.length;i++){
            String line = tablines[i].toString().trim();
            String[] dataRow = line.split(",");
            model.addRow(dataRow);
        }

    }

    public void Remove() {
        int i = RouTab.getSelectedRow();
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
        if (UniqueCheck()) {
            JOptionPane.showMessageDialog(null,"The update was Successful");
            if(!RouFile.exists()){
                RouFile.createNewFile();
            }
            FileWriter fw = new FileWriter(RouFile.getAbsolutePath());
            BufferedWriter bw = new BufferedWriter(fw);

            for(int i = 0;i<RouTab.getRowCount();i++){
                for(int j = 0;j<RouTab.getColumnCount();j++){
                    String temp = ((String)RouTab.getModel().getValueAt(i,j));
                    if(temp!=null){
                        if(j!=6){
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
                        JOptionPane.showMessageDialog(null,"This Route already exists");
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
