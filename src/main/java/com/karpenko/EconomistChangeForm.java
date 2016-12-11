package com.karpenko;

import com.sun.deploy.security.CredentialInfo;
import com.sun.deploy.security.ruleset.ExceptionRule;

import javax.management.QueryExp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import static com.sun.glass.ui.Cursor.setVisible;

/**
 * Created by Олег on 19.10.2016.
 */
public class EconomistChangeForm extends JFrame{
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
    static  JComboBox cbMesBukvy4 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy5 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy6 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy7 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy8 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy9 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy10 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy11 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy12 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy13 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy14 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy15 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy16 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy17 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy18 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy19 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy20 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy21 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy22 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy23 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy24 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy25 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy26 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy27 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy28 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy29 = new JComboBox(mesBukvy);
    static  JComboBox cbMesBukvy30 = new JComboBox(mesBukvy);
    static JComboBox cbMesBukvy31 = new JComboBox(mesBukvy);

    public static JButton bViewAdd = new JButton("Просмотр изменений");         // кнопка просмотра добавленных
    public static JButton bClose = new JButton("Выход");           //кнопка выхода
    public JButton bToChange = new JButton("Изменить");          //кнопка добавить

    EconomistChangeForm() {
        super();
        setLayout(new GridLayout(18,1));
        //установить размер равный разрешению экрана
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setExtendedState(Frame.MAXIMIZED_BOTH);

        setVisible(false);

        ImageIcon icon = new ImageIcon("home.png");
        setIconImage(icon.getImage());

        bToChange.addActionListener(new ActionListener() {              //слушателья для кнопки добавления работника
            @Override
            public void actionPerformed(ActionEvent e) {
                final  EconomistCalculate clclt = new  EconomistCalculate();
                final String addWorkerQuery = "UPDATE " + EconomistMainForm.strToIntMonth(EconomistMainForm.cbMonth)+
                        " SET fio = '" + tfFIO.getText() + "',tab_num = '" + tfTNum.getText() + "',dolzh = '" + tfDolzh.getText()+
                        "',razr = '" + tfRazr.getText() + "',MVZ = '" +  tfMVZ.getText() +"',vid_rab = '" +tfVidRab.getText()+
                        "',m1 = '" +cbMesBukvy1.getSelectedItem()+ "',m2 = '" +cbMesBukvy2.getSelectedItem()+ "',m3 = '" + cbMesBukvy3.getSelectedItem()+
                        "',m4 = '" +cbMesBukvy4.getSelectedItem() + "',m5 = '" + cbMesBukvy5.getSelectedItem()+ "',m6 = '" +cbMesBukvy6.getSelectedItem()+
                "',m7 = '" +cbMesBukvy7.getSelectedItem()+ "',m8 = '" + cbMesBukvy8.getSelectedItem()+"',m9 = '" + cbMesBukvy9.getSelectedItem()+
                        "',m10 = '" + cbMesBukvy10.getSelectedItem()+"',m11 = '" + cbMesBukvy11.getSelectedItem()+"',m12 = '" + cbMesBukvy12.getSelectedItem()+
                        "',m13 = '" + cbMesBukvy13.getSelectedItem()+"',m14 = '" + cbMesBukvy14.getSelectedItem()+"',m15 = '" +cbMesBukvy15.getSelectedItem()+
                "',m16 = '" + cbMesBukvy16.getSelectedItem()+"',m17 = '" + cbMesBukvy17.getSelectedItem()+"',m18 ='" + cbMesBukvy18.getSelectedItem()+
                        "',m19 = '" + cbMesBukvy19.getSelectedItem()+"',m20 = '" + cbMesBukvy20.getSelectedItem()+"',m21 = '" + cbMesBukvy21.getSelectedItem()+
                        "',m22 = '" + cbMesBukvy22.getSelectedItem()+"',m23 = '" + cbMesBukvy23.getSelectedItem()+"',m24 = '" +cbMesBukvy24.getSelectedItem()+
                "',m25 = '" + cbMesBukvy25.getSelectedItem()+"',m26 = '" + cbMesBukvy26.getSelectedItem()+"',m27 = '" + cbMesBukvy27.getSelectedItem()+
                        "',m28 = '"+ cbMesBukvy28.getSelectedItem()+"',m29 = '" + cbMesBukvy29.getSelectedItem()+"',m30 = '" + cbMesBukvy30.getSelectedItem()+
                        "',m31 = '" +cbMesBukvy31.getSelectedItem() + "',fakt_rab = '" + clclt.getFakt_rabChange() + "',otp_O = '" + clclt.getOtp_OChange() +
                        "',otp_reb_R = '" + clclt.getOtp_reb_RChange() + "',netrud_B = '" + clclt.getnetrud_BChange() +
                        "',razr_zak_GD = '" + clclt.getrazr_zak_GDChange() + "',razr_adm_A = '" + clclt.getrazr_adm_AChange() +
                        "',uch_U = '" + clclt.getuch_UChange() + "',progul_P = '" + clclt.getprogul_PChange() +
                        "',komand_K = '" + clclt.getkomand_KChange() + "',vyh_prazd_V = '" + clclt.getvyh_prazd_VChange() +
                        "',vsego_dney = '" + clclt.getvsego_dneyChange() + "',vsego_chas = '" + clclt.getvsego_chasChange() +
                        "',otrab_fakt = '" + clclt.getotrab_faktChange() + "',sverh_ur = '" + clclt.getsveth_UrChange() +
                        "',doplata = '" + clclt.getdoplataChange() +
                        "',noch = '" + clclt.getnochChange() + "',vputi = '" + clclt.getvputiChange() +

                        "' WHERE fio= '" +  EconomistCreatedForm.table.getValueAt( EconomistCreatedForm.table.getSelectedRow(),0) +
                        "' AND MVZ = " +  EconomistCreatedForm.table.getValueAt( EconomistCreatedForm.table.getSelectedRow(),4);

                new Thread(new Runnable() { // выполение подключения и выполнения запроса в отдельном потоке
                    @Override
                    public void run() {
                        DBConnect dbc = null;
                        Statement st = null;
                        try {
                            dbc = new DBConnect();
                            st = dbc.getConnection().createStatement();
                            try {
                                st.executeUpdate(addWorkerQuery); // выполняем запрос добавление работника
                                JOptionPane.showMessageDialog(null,"Запись изменена");
                            }catch (Exception qe){
                                JOptionPane.showMessageDialog(null,"Ошибка добавления изменений");}
                            dbc.getConnection().close();

                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        finally {
                            try {
                                dbc.getConnection().close();
                                st.close();
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }).start();
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
        add(bToChange);  add(bViewAdd);  add(bClose);

    }
}

