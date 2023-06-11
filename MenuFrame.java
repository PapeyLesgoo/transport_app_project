import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MenuFrame extends JFrame {

    JLayeredPane LayPane = new JLayeredPane();
    JPanel MenuPan = new JPanel();
    ImageIcon img = new ImageIcon("FastLogo.jpg");
    JLabel lab1 = new JLabel(img);
    JLabel lab2 = new JLabel("FAST POINT MANAGEMENT HOME PAGE");
    JButton DriButton = new JButton();
    JButton StuButton = new JButton();
    JButton RouButton = new JButton();
    JButton DriBackButton = new JButton();
    JButton RouBackButton = new JButton();
    JButton StuBackButton = new JButton();
    DriverPanel dp = new DriverPanel();
    RoutePanel rp = new RoutePanel();
    StudentPanel sp = new StudentPanel();

    MenuFrame() throws IOException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(350,50,710,600);
        this.setLayout(null);
        this.setBackground(Color.BLUE);
        this.setResizable(false);
        this.setVisible(true);

        LayPane.setBounds(0,0,700,700);

        MenuPan.setBounds(0,0,700,700);
        MenuPan.setBackground(new Color(0, 160, 200));

        DriButton.setText("Driver");
        DriButton.setBounds(100,0,100,50);
        DriButton.addActionListener(e->DriBtnPressed());
        MenuPan.add(DriButton);

        StuButton.setText("Student");
        StuButton.setBounds(250,0,100,50);
        StuButton.addActionListener(e -> StuBtnPressed());
        MenuPan.add(StuButton);

        RouButton.setText("Route");
        RouButton.setBounds(400,400,100,50);
        RouButton.addActionListener(e -> RouBtnPressed());
        MenuPan.add(RouButton);

        lab1.setBounds(0,200,700,360);
        MenuPan.add(lab1);

        lab2.setBounds(0,500,700,200);
        lab2.setFont(new Font("Serif", Font.BOLD, 25));
        MenuPan.add(lab2);

        DriBackButton.setText("Back");
        DriBackButton.setBounds(0,0,100,50);
        DriBackButton.addActionListener(e -> b1Pressed());

        RouBackButton.setText("Back");
        RouBackButton.setBounds(0,0,100,50);
        RouBackButton.addActionListener(e -> b2Pressed());

        StuBackButton.setText("Back");
        StuBackButton.setBounds(0,0,100,50);
        StuBackButton.addActionListener(e -> b3Pressed());



        LayPane.add(MenuPan);
        LayPane.add(dp.DriPan);
        LayPane.add(rp.RouPan);
        LayPane.add(sp.StuPan);

        //Moves Argument Panel to front
        LayPane.moveToFront(MenuPan);


        this.add(LayPane);
    }

    //Event when Driver Button is pressed, goes to Driver Panel
    void DriBtnPressed(){
        //Removing and adding buttons and table as they appear through layered pane
        MenuPan.remove(StuButton);
        MenuPan.remove(DriButton);
        MenuPan.remove(RouButton);
        dp.DriPan.add(dp.UpButton);
        dp.DriPan.add(DriBackButton);
        dp.DriPan.add(dp.AddDriButton);
        dp.DriPan.add(dp.RemDriverButton);
        dp.DriPan.add(dp.Scroll);
        LayPane.moveToFront(dp.DriPan);
    }

    void RouBtnPressed(){
        MenuPan.remove(StuButton);
        MenuPan.remove(DriButton);
        MenuPan.remove(RouButton);
        rp.RouPan.add(rp.UpButton);
        rp.RouPan.add(RouBackButton);
        rp.RouPan.add(rp.AddDRouButton);
        rp.RouPan.add(rp.RemRouButton);
        rp.RouPan.add(rp.Scroll);
        LayPane.moveToFront(rp.RouPan);
    }

    void StuBtnPressed(){
        MenuPan.remove(StuButton);
        MenuPan.remove(DriButton);
        MenuPan.remove(RouButton);
        sp.StuPan.add(sp.UpButton);
        sp.StuPan.add(StuBackButton);
        sp.StuPan.add(sp.AddStuButton);
        sp.StuPan.add(sp.RemStuButton);
        sp.StuPan.add(sp.Scroll);
        LayPane.moveToFront(sp.StuPan);
    }

    //Event that occurs when back is pressed on appropriate Panel, returns to Main Panel
    //For Driver Panel
    void b1Pressed(){
        MenuPan.add(StuButton);
        MenuPan.add(DriButton);
        MenuPan.add(RouButton);
        dp.DriPan.remove(dp.UpButton);
        dp.DriPan.remove(DriBackButton);
        dp.DriPan.remove(dp.AddDriButton);
        dp.DriPan.remove(dp.RemDriverButton);
        dp.DriPan.remove(dp.Scroll);
        LayPane.moveToFront(MenuPan);
    }

    //For Route Panel
    void b2Pressed(){
        MenuPan.add(StuButton);
        MenuPan.add(DriButton);
        MenuPan.add(RouButton);
        rp.RouPan.remove(RouBackButton);
        rp.RouPan.remove(rp.UpButton);
        rp.RouPan.remove(rp.AddDRouButton);
        rp.RouPan.remove(rp.RemRouButton);
        rp.RouPan.remove(rp.Scroll);
        LayPane.moveToFront(MenuPan);
    }

    //For Student Panel
    void b3Pressed(){
        MenuPan.add(StuButton);
        MenuPan.add(DriButton);
        MenuPan.add(RouButton);
        sp.StuPan.remove(StuBackButton);
        sp.StuPan.remove(sp.UpButton);
        sp.StuPan.remove(sp.AddStuButton);
        sp.StuPan.remove(sp.RemStuButton);
        sp.StuPan.remove(sp.Scroll);
        LayPane.moveToFront(MenuPan);
    }
}
