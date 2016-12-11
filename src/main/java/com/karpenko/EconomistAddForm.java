package com.karpenko;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

/**
 * Created by Олег on 11.10.2016.
 */
public class EconomistAddForm extends JFrame {
    Statement st;
    JPanel panelMes1 = new JPanel();
    JPanel panelMes2 = new JPanel();
    static String[] mesBukvy = {" ","8","11","12","8/3","3/8","8/4","4/8","2/9","4/7","7/4","6/5","5/6","4/6","2/1","6/3","2/8","1/10",
            "8д","3д","4д","5д","6д","7д","2д","1д","О","Р","Б","Г","Д","У","П","К","Кд","В","А"};
    JTextField tfFIO = new JTextField();
    JTextField tfTNum = new JTextField();
    JTextField tfDolzh = new JTextField();
    JTextField tfRazr = new JTextField();
    JTextField tfMVZ = new JTextField();
    JTextField tfVidRab = new JTextField();

    // ОГРОМНЫЙ И НЕУДОБНЫЙ блок создания комбобоксов
    static JComboBox cbMesBukvy1 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy2 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy3 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy4 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy5 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy6 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy7 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy8 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy9 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy10 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy11 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy12 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy13 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy14 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy15 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy16 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy17 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy18 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy19 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy20 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy21 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy22 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy23 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy24 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy25 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy26 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy27 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy28 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy29 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy30 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy31 = new JComboBox(mesBukvy);

    public JButton bViewAdd = new JButton("Посмотреть добавленных");         // кнопка просмотра добавленных
    public static JButton bClose = new JButton("Выход");           //кнопка выхода
    public static JButton bAdd;          //кнопка добавить

    EconomistAddForm() {
        super();
       // Font font=new Font("TimesRoman",Font.ITALIC,14);
        setLayout(new GridLayout(18,1));
        //установить размер равный разрешению экрана
       setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        ImageIcon icon = new ImageIcon("home.png");
        setIconImage(icon.getImage());

        setVisible(false);

        bAdd = new JButton("Добавить");// bAdd.setFont(font); tfFam.setFont(font);

        bAdd.addActionListener(new ActionListener() {              //слушателья для кнопки добавления работника
            @Override
            public void actionPerformed(ActionEvent e) {
                final EconomistCalculate clclt = new  EconomistCalculate();
                final String addWorkerQuery = "INSERT INTO " + EconomistMainForm.strToIntMonth(EconomistMainForm.cbMonth)+
                        " (fio,tab_num,dolzh,razr,MVZ,vid_rab,m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13,m14," +
                        "m15,m16,m17,m18,m19,m20,m21,m22,m23,m24,m25,m26,m27,m28,m29,m30,m31,fakt_rab,otp_O,otp_reb_R," +
                        "netrud_B,razr_zak_GD,razr_adm_A,uch_U,progul_P,komand_K,vyh_prazd_V,vsego_dney,vsego_chas," +
                        "otrab_fakt,sverh_ur,doplata,noch,vputi)" +
                        "VALUES ('" + tfFIO.getText()
                        +"','" + tfTNum.getText() + "','" + tfDolzh.getText()+"','"+tfRazr.getText()+ "','" + tfMVZ.getText() +
                        "','" + tfVidRab.getText()+"','"+cbMesBukvy1.getSelectedItem()+ "','"
                        + cbMesBukvy2.getSelectedItem()+ "','" + cbMesBukvy3.getSelectedItem()+ "','"+
                        cbMesBukvy4.getSelectedItem()+ "','" + cbMesBukvy5.getSelectedItem()+ "','"+
                        cbMesBukvy6.getSelectedItem()+ "','" + cbMesBukvy7.getSelectedItem()+ "','"+
                        cbMesBukvy8.getSelectedItem()+ "','"+cbMesBukvy9.getSelectedItem()+ "','"+
                        cbMesBukvy10.getSelectedItem()+ "','" + cbMesBukvy11.getSelectedItem()+ "','" +
                        cbMesBukvy12.getSelectedItem()+ "','" + cbMesBukvy13.getSelectedItem()+ "','"+
                        cbMesBukvy14.getSelectedItem()+ "','" + cbMesBukvy15.getSelectedItem()+ "','"+
                        cbMesBukvy16.getSelectedItem()+ "','" + cbMesBukvy17.getSelectedItem()+ "','"+
                        cbMesBukvy18.getSelectedItem()+ "','"+cbMesBukvy19.getSelectedItem()+ "','"+
                        cbMesBukvy20.getSelectedItem()+ "','" + cbMesBukvy21.getSelectedItem()+ "','" +
                        cbMesBukvy22.getSelectedItem()+ "','" + cbMesBukvy23.getSelectedItem()+ "','"+
                        cbMesBukvy24.getSelectedItem()+ "','" + cbMesBukvy25.getSelectedItem()+ "','"+
                        cbMesBukvy26.getSelectedItem()+ "','" + cbMesBukvy27.getSelectedItem()+ "','"+
                        cbMesBukvy28.getSelectedItem()+ "','"+cbMesBukvy29.getSelectedItem()+ "','"+
                        cbMesBukvy30.getSelectedItem()+ "','" + cbMesBukvy31.getSelectedItem()+ "','" +
                        clclt.getFakt_rabAdd()+ "','"  + clclt.getOtp_OAdd()+ "','"  + clclt.getOtp_reb_RAdd() + "','" +
                        clclt.getnetrud_BAdd() + "','"  + clclt.getrazr_zak_GDADdd()+ "','" +clclt.getrazr_adm_AAdd() + "','" +
                        clclt.getuch_UAdd() + "','"  + clclt.getprogul_PAdd()+ "','" +clclt.getkomand_KAdd() + "','" +
                        clclt.getvyh_prazd_VAdd() + "','"  + clclt.getvsego_dneyAdd() + "','" +
                        clclt.getvsego_chasAdd() + "','"+ clclt.getotrab_faktAdd() +"','" + clclt.getsveth_UrAdd() + "','" +
                        clclt.getdoplataAdd() + "','" +clclt.getnochAdd() + "','" + clclt.getvputiAdd() + "')";

                    new Thread(new Runnable() { // выполение подключения и выполнения запроса в отдельном потоке
                        @Override
                        public void run() {
                            DBConnect dbc = new DBConnect();
                            try {
                                st = dbc.getConnection().createStatement();
                                st.executeUpdate(addWorkerQuery); // выполняем запрос добавление работника
                                dbc.getConnection().close();
                            } catch (SQLException e1) {JOptionPane.showMessageDialog(null,"Ошибка при добавлении данных");}
                        }
                    }).start();
                    // опустошаем все поля ввода и ставим курсор на первое поле ФАМИЛИЯ
                    tfFIO.setText("");  tfTNum.setText("");
                    tfDolzh.setText(""); tfRazr.setText(""); tfMVZ.setText(""); tfVidRab.setText("");
                    cbMesBukvy1.setSelectedIndex(0); cbMesBukvy2.setSelectedIndex(0); cbMesBukvy3.setSelectedIndex(0);
                    cbMesBukvy4.setSelectedIndex(0); cbMesBukvy5.setSelectedIndex(0); cbMesBukvy6.setSelectedIndex(0);
                    cbMesBukvy7.setSelectedIndex(0); cbMesBukvy8.setSelectedIndex(0); cbMesBukvy9.setSelectedIndex(0);
                    cbMesBukvy10.setSelectedIndex(0); cbMesBukvy11.setSelectedIndex(0); cbMesBukvy12.setSelectedIndex(0);
                    cbMesBukvy13.setSelectedIndex(0); cbMesBukvy14.setSelectedIndex(0); cbMesBukvy15.setSelectedIndex(0);
                    cbMesBukvy16.setSelectedIndex(0); cbMesBukvy17.setSelectedIndex(0); cbMesBukvy18.setSelectedIndex(0);
                    cbMesBukvy19.setSelectedIndex(0); cbMesBukvy20.setSelectedIndex(0); cbMesBukvy21.setSelectedIndex(0);
                    cbMesBukvy22.setSelectedIndex(0); cbMesBukvy23.setSelectedIndex(0); cbMesBukvy24.setSelectedIndex(0);
                    cbMesBukvy25.setSelectedIndex(0); cbMesBukvy26.setSelectedIndex(0); cbMesBukvy27.setSelectedIndex(0);
                    cbMesBukvy28.setSelectedIndex(0); cbMesBukvy29.setSelectedIndex(0); cbMesBukvy30.setSelectedIndex(0);
                    cbMesBukvy31.setSelectedIndex(0);
                    tfFIO.requestFocus();
            }
        });

        // блок добавления компонентов на форму
        panelMes1.setLayout(new GridLayout(2, 16));    // создаем панель для хранения дней месяцев
        for (int i = 1; i < 17; i ++){                       //добавление лэйблов для дней
            panelMes1.add(new JLabel(String.valueOf(i)));
        }
        // огромный блок добавления комбобоксов на форму ?? как сделать лучше??
        panelMes1.add(cbMesBukvy1); panelMes1.add(cbMesBukvy2); panelMes1.add(cbMesBukvy3); panelMes1.add(cbMesBukvy4);
        panelMes1.add(cbMesBukvy5); panelMes1.add(cbMesBukvy6); panelMes1.add(cbMesBukvy7); panelMes1.add(cbMesBukvy8);
        panelMes1.add(cbMesBukvy9); panelMes1.add(cbMesBukvy10); panelMes1.add(cbMesBukvy11); panelMes1.add(cbMesBukvy12);
        panelMes1.add(cbMesBukvy13); panelMes1.add(cbMesBukvy14); panelMes1.add(cbMesBukvy15); panelMes1.add(cbMesBukvy16);

        panelMes2.setLayout(new GridLayout(2, 16));    // создаем панель для хранения дней месяцев
        for (int i = 17; i < 32; i ++){                       //добавление лэйблов для дней
            panelMes2.add(new JLabel(String.valueOf(i)));
        }
        panelMes2.add(cbMesBukvy17); panelMes2.add(cbMesBukvy18); panelMes2.add(cbMesBukvy19); panelMes2.add(cbMesBukvy20);
        panelMes2.add(cbMesBukvy21); panelMes2.add(cbMesBukvy22); panelMes2.add(cbMesBukvy23); panelMes2.add(cbMesBukvy24);
        panelMes2.add(cbMesBukvy25); panelMes2.add(cbMesBukvy26); panelMes2.add(cbMesBukvy27); panelMes2.add(cbMesBukvy28);
        panelMes2.add(cbMesBukvy29); panelMes2.add(cbMesBukvy30); panelMes2.add(cbMesBukvy31);

        add(new JLabel("ФИО")); add(tfFIO); // фамилия
      //  add(new JLabel("Имя")); add(tfName); //имя
      //  add(new JLabel("Отчество")); add(tfOtch); // отчество
        add(new JLabel("Табель №")); add(tfTNum);
        add(new JLabel("Должность")); add(tfDolzh);
        add(new JLabel("Разряд")); add(tfRazr);
        add(new JLabel("МВЗ")); add(tfMVZ);
        add(new JLabel("Вид работ")); add(tfVidRab);
        add(panelMes1); add(panelMes2);                      //лэблы с днями и текстфилды с заполнением дней
        add(new JLabel());
        add(bAdd);  add(bViewAdd);  add(bClose);

    }
}

