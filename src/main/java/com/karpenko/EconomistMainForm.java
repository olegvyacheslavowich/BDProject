package com.karpenko;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sun.plugin2.message.Message;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Formatter;
import java.util.Vector;

/**
 * Created by Олег on 11.10.2016.
 */
public class EconomistMainForm extends JFrame {
    private Object[] object = {"Да", "Нет"};
    static String sYear[] = {"2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025"};
    static String[] sMonth = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль",
            "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    static JComboBox cbMonth = new JComboBox(sMonth);
    static JComboBox cbYear = new JComboBox(sYear);
    private JButton bCreate = new JButton("Создать");
    public static JButton bChange = new JButton("Изменить");
    public static JButton bClose = new JButton("Выход");
    public static JButton bToExcel = new JButton("Конвертировать в Excel");
    static JButton bViewVed = new JButton("Просмотр ведомости");
    EconomistMainForm() {
        super("Укажите месяц и год");
        setLayout(new GridLayout(2, 1));
        setVisible(false);
        setSize(480, 300);
        setResizable(false);
        setLocationRelativeTo(null);                        //отобразить в центре экрана
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        ImageIcon icon = new ImageIcon("home.png");
        setIconImage(icon.getImage());

        final  EconomistCreatedForm cf = new  EconomistCreatedForm();
        final  EconomistChangeForm chf = new  EconomistChangeForm();
        final  EconomistAddForm af = new  EconomistAddForm();
        final EconomistVedView ecVedView = new EconomistVedView();

        // /создаем названия столбцов
        final Vector column = new Vector();
        column.add("ФИО");
        column.add("Табельный №");
        column.add("Должность");
        column.add("Разряд");
        column.add("МВЗ");
        column.add("Вид работ");
        column.add("1");
        column.add("2");
        column.add("3");
        column.add("4");
        column.add("5");
        column.add("6");
        column.add("7");
        column.add("8");
        column.add("9");
        column.add("10");
        column.add("11");
        column.add("12");
        column.add("13");
        column.add("14");
        column.add("15");
        column.add("16");
        column.add("17");
        column.add("18");
        column.add("19");
        column.add("20");
        column.add("21");
        column.add("22");
        column.add("23");
        column.add("24");
        column.add("25");
        column.add("26");
        column.add("27");
        column.add("28");
        column.add("29");
        column.add("30");
        column.add("31");
        column.add("Фактические отработано");
        column.add("Отпуск(О)");
        column.add("Отпуск по уходу за ребёнком (Р)");
        column.add("Лист временной нетрудоспособности (Б)");
        column.add("Разрешенные законом (Г,Д)");
        column.add("С разрешния администрации (А)");
        column.add(" Учёба (У)");
        column.add("Прогулы (П)");
        column.add("Командировка (К)");
        column.add("Выходные и праздничные дни (В)");
        column.add(" Всего календарных дней");
        column.add("Всего часов");
        column.add("Отработано часов фактически");
        column.add("Свехурочные");
        column.add("Доплата");
        column.add("Ночные");
        column.add("Время в пути");
        column.add("Доплата за вахтовый метод");
        column.add("Заказ");
         JPanel pDate = new JPanel();
         JPanel pButtons = new JPanel();
        pDate.setLayout(new GridLayout(2, 1));
        pDate.add(cbMonth);
        pDate.add(cbYear);
        pButtons.setLayout(new GridLayout(5,1));
        pButtons.add(bCreate);
        pButtons.add(bChange);
        pButtons.add(bToExcel);
        pButtons.add(bViewVed);
        pButtons.add(bClose);
        add(pDate);
        add(pButtons);
        // создаем новый запрос на добавление новой таблицы
        final String newTableQuery =
                "  `fio` VARCHAR(45) NOT NULL,\n" +
                        "  tab_num INT NOT NULL,\n" +
                        "  `dolzh` VARCHAR(45) NOT NULL,\n" +
                        "  `razr` VARCHAR(45) NOT NULL,\n" +
                        "  `MVZ` INT NOT NULL,\n" +
                        "  `vid_rab` VARCHAR(45) NULL,\n" +
                        "  `m1` VARCHAR(4) NULL,\n" +
                        "  `m2` VARCHAR(4) NULL,\n" +
                        "  `m3` VARCHAR(4) NULL,\n" +
                        "  `m4` VARCHAR(4) NULL,\n" +
                        "  `m5` VARCHAR(4) NULL,\n" +
                        "  `m6` VARCHAR(4) NULL,\n" +
                        "  `m7` VARCHAR(4) NULL,\n" +
                        "  `m8` VARCHAR(4) NULL,\n" +
                        "  `m9` VARCHAR(4) NULL,\n" +
                        "  `m10` VARCHAR(4) NULL,\n" +
                        "  `m11` VARCHAR(4) NULL,\n" +
                        "  `m12` VARCHAR(4) NULL,\n" +
                        "  `m13` VARCHAR(4) NULL,\n" +
                        "  `m14` VARCHAR(4) NULL,\n" +
                        "  `m15` VARCHAR(4) NULL,\n" +
                        "  `m16` VARCHAR(4) NULL,\n" +
                        "  `m17` VARCHAR(4) NULL,\n" +
                        "  `m18` VARCHAR(4) NULL,\n" +
                        "  `m19` VARCHAR(4) NULL,\n" +
                        "  `m20` VARCHAR(4) NULL,\n" +
                        "  `m21` VARCHAR(4) NULL,\n" +
                        "  `m22` VARCHAR(4) NULL,\n" +
                        "  `m23` VARCHAR(4) NULL,\n" +
                        "  `m24` VARCHAR(4) NULL,\n" +
                        "  `m25` VARCHAR(4) NULL,\n" +
                        "  `m26` VARCHAR(4) NULL,\n" +
                        "  `m27` VARCHAR(4) NULL,\n" +
                        "  `m28` VARCHAR(4) NULL,\n" +
                        "  `m29` VARCHAR(4) NULL,\n" +
                        "  `m30` VARCHAR(4) NULL,\n" +
                        "  `m31` VARCHAR(4) NULL,\n" +
                        "  `fakt_rab` INT(2) NULL,\n" +
                        "  `otp_O` INT(2) NULL,\n" +
                        "  `otp_reb_R` INT(2) NULL,\n" +
                        "  `netrud_B` INT(2) NULL,\n" +
                        "  `razr_zak_GD` INT(2) NULL,\n" +
                        "  `razr_adm_A` INT(2) NULL,\n" +
                        "  `uch_U` INT(2) NULL,\n" +
                        "  `progul_P` INT(2) NULL,\n" +
                        "  `komand_K` INT(2) NULL,\n" +
                        "  `vyh_prazd_V` INT(2) NULL,\n" +
                        "  `vsego_dney` INT(2) NULL,\n" +
                        "  `vsego_chas` INT(3) NULL,\n" +
                        "  `otrab_fakt` INT(3) NULL,\n" +
                        "  `sverh_ur` INT(3) NULL,\n" +
                        "  `doplata` INT(3) NULL,\n" +
                        "  `noch` INT(3) NULL,\n" +
                        "  `vputi` INT(2) NULL,\n" +
                        "  `zavaht` INT(3) NULL,\n" +
                        "  `zakaz` INT(3) NULL)\n" +
                        "ENGINE = InnoDB\n" +
                        "DEFAULT CHARACTER SET = utf8;\n";

// добавляем слушателя для кнопки просмотра добавленных на форме AddForm (добавление записи в табель)
        af.bViewAdd.addActionListener(new ActionListener() {
            DBConnect dbc = null;
            Statement st = null;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ///////////////////////////////
                    dbc = new DBConnect();
                    st = dbc.getConnection().createStatement();
                    cf.table.setModel(new DefaultTableModel(getData("SELECT * FROM " + strToIntMonth(cbMonth)), column));
                    // выставление размера столбцов
                    for (int i = 0; i < 56; i++) {
                        cf.table.getColumnModel().getColumn(i).setPreferredWidth(200);
                    }
                    /////////////////////////////////////////
                    af.setVisible(false);
                    cf.setVisible(true);
                } catch (SQLException sq) {
                    JOptionPane.showMessageDialog(null, "Ошибка отображения данных");
                } finally {
                    try {
                        dbc.getConnection().close();
                        st.close();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
//слушатель для кнопки добавления нового табеля в базу//////////////
        bCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBConnect dbc = null;
                Statement st = null;
                try {
                     dbc = new DBConnect();
                    st = dbc.getConnection().createStatement();
                    try {                                             // проверка на существование табеля в базе
                        st.execute("SELECT * FROM " + strToIntMonth(cbMonth));
                        // переменной result присваивается нажатая кнопка (Да ИЛИ Нет)
                        int result = JOptionPane.showOptionDialog(null, "Табель за " + cbMonth.getSelectedItem() + " " +
                                        cbYear.getSelectedItem() + " был создан ранее. Хотите изменить его?", "Внимание!",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, object, object[0]);
                        // в зависимости от того какую кнопку вернул OptionDialog вывести соответ-щий результат
                        if (result == JOptionPane.YES_NO_OPTION) {
                            ///////////////////////////////
                            cf.table.setModel(new DefaultTableModel(getData("SELECT * FROM " + strToIntMonth(cbMonth)), column)); // отобразить данные в таблице
                            // выставление размера столбцов
                            for (int i = 0; i < 56; i++) {
                                cf.table.getColumnModel().getColumn(i).setPreferredWidth(200);
                            }
                            /////////////////////////////////////////
                            dbc.getConnection().close();
                            cf.setVisible(true);
                            setVisible(false);
                        }
                    } catch (Exception e1) {
                        st.execute("CREATE TABLE `tabeldb`.`" + strToIntMonth(cbMonth) + "` (\n" + newTableQuery);
                        af.setTitle("Табель за " + cbMonth.getSelectedItem() + " " + cbYear.getSelectedItem());
                        setVisible(false);
                        af.setVisible(true);
                    }
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, "Ошибка создания табеля с датой " + cbMonth.getSelectedItem() + " " + cbYear.getSelectedItem());
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
        });
/////////////////////////////////////////////////////////////////////////////////////////
        // показать окно заполнения табеля при нажатии кнопки добавить в окне Просмотр добавленных
        cf.bAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                af.setTitle("Табель за " + cbMonth.getSelectedItem());
                af.setVisible(true);
                cf.setVisible(false);
            }
        });
        /////////////////////////////////////////////////////////////////////
        //кнопка изменения созданного табеля///////////////
        bChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DBConnect dbc = null;
                Statement st = null;
                try {
                    dbc = new DBConnect();
                    st = dbc.getConnection().createStatement();
                  //  ResultSet rs = null;
                    try {
                        st.executeQuery("select * FROM " + strToIntMonth(cbMonth));
                        cf.table.setModel(new DefaultTableModel(getData("SELECT * FROM " + strToIntMonth(cbMonth)), column));
                        // выставление размера столбцов
                        for (int i = 0; i < 56; i++) {
                            cf.table.getColumnModel().getColumn(i).setPreferredWidth(200);
                        }
                        cf.setVisible(true);
                        setVisible(false);
                        dbc.getConnection().close();
                    } catch (Exception exe) {
                        int result = JOptionPane.showOptionDialog(null, "Табель за " + cbMonth.getSelectedItem() + " " +
                                        cbYear.getSelectedItem() + " не был создан. Хотите создать его?", "Внимание!",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, object, object[0]);
                        // в зависимости от того какую кнопку вернул OptionDialog вывести соответ-щий результат
                        if (result == JOptionPane.YES_NO_OPTION) {
                            // добавить новую таблицу под именем ДАТЫ и ГОДА
                            try {
                                st.execute("CREATE TABLE `tabeldb`.`" + strToIntMonth(cbMonth) + "` (\n" + newTableQuery);
                            } catch (SQLException e1) {
                                JOptionPane.showMessageDialog(null, "Ошибка при добавлении табеля");
                            }
                            // показать форму заполнения работников
                            af.setVisible(true);
                            setVisible(false);

                        }
                    }
                    /////////////////////////////////////////
                    dbc.getConnection().close();
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, "Ошибка при отображении табеля");
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
        });
        ///////////////////////////////////////////////////////
//кнопка ИЗМЕНИТЬ на форме CreatedForm (добавленных записей)///////
        cf.bChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cf.table.getSelectedRow() != -1) {
                    chf.tfFIO.setText((String) cf.table.getValueAt(cf.table.getSelectedRow(), 0));
                    chf.tfTNum.setText((String) cf.table.getValueAt(cf.table.getSelectedRow(), 1));
                    chf.tfDolzh.setText((String) cf.table.getValueAt(cf.table.getSelectedRow(), 2));
                    chf.tfRazr.setText((String) cf.table.getValueAt(cf.table.getSelectedRow(), 3));
                    chf.tfMVZ.setText((String) cf.table.getValueAt(cf.table.getSelectedRow(), 4));
                    chf.tfVidRab.setText((String) cf.table.getValueAt(cf.table.getSelectedRow(), 5));

                    for (int i = 0; i < chf.mesBukvy.length; i++) {
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 6).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy1.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 7).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy2.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 8).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy3.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 9).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy4.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 10).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy5.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 11).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy6.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 12).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy7.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 13).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy8.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 14).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy9.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 15).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy10.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 16).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy11.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 17).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy12.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 18).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy13.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 19).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy14.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 20).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy15.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 21).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy16.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 22).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy17.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 23).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy18.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 24).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy19.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 25).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy20.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 26).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy21.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 27).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy22.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 28).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy23.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 29).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy24.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 30).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy25.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 31).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy26.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 32).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy27.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 33).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy28.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 34).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy29.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 35).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy30.setSelectedIndex(i);
                        if (cf.table.getValueAt(cf.table.getSelectedRow(), 36).toString().equals(chf.mesBukvy[i]))
                            chf.cbMesBukvy31.setSelectedIndex(i);
                    }
                    chf.setTitle("Табель за " + cbMonth.getSelectedItem() + " " + cbYear.getSelectedItem());
                    cf.setVisible(false); // скрыть CreatedForm
                    chf.setVisible(true);  //показать форму изменения
                } else JOptionPane.showMessageDialog(null, "Выберите интересующую строку в таблице");
            }
        });
        ////////////////////////////////////////////////////////////////////////////
        //удаление записи
        cf.bDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() { // выполение подключения и выполнения запроса в отдельном потоке
                    @Override
                    public void run() {
                        DBConnect dbc = null;
                        Statement st = null;
                        try {
                            dbc = new DBConnect();
                            st = dbc.getConnection().createStatement();
                            try {
                                if (cf.table.getSelectedRow() != -1) {
                                    final String addWorkerQuery = "DELETE FROM `" + strToIntMonth(cbMonth) +
                                            "`  WHERE fio = '" + cf.table.getValueAt(cf.table.getSelectedRow(), 0) +
                                            "' AND MVZ = " + cf.table.getValueAt(cf.table.getSelectedRow(), 4);
                                    st.executeUpdate(addWorkerQuery); // выполняем запрос добавление работника
                                    JOptionPane.showMessageDialog(null, "Запись удалена");
                                    cf.table.setModel(new DefaultTableModel(getData("SELECT * FROM " + strToIntMonth(cbMonth)), column));
                                    for (int i = 0; i < 56; i++) {
                                        cf.table.getColumnModel().getColumn(i).setPreferredWidth(200);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Выберите строку которую желаете удалить");
                                }
                            } catch (Exception qe) {
                                JOptionPane.showMessageDialog(null, "Ошибка удаления");
                            }
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
        chf.bViewAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBConnect dbc = null;
                Statement st = null;
                try {
                    ///////////////////////////////
                    dbc = new DBConnect();
                    st = dbc.getConnection().createStatement();
                    cf.table.setModel(new DefaultTableModel(getData("SELECT * FROM " + strToIntMonth(cbMonth)), column));
                    // выставление размера столбцов
                    for (int i = 0; i < 56; i++) {
                        cf.table.getColumnModel().getColumn(i).setPreferredWidth(200);
                    }
                    /////////////////////////////////////////
                    chf.setVisible(false);
                    cf.setVisible(true);
                } catch (SQLException sq) {
                    JOptionPane.showMessageDialog(null, "Ошибка отображения данных");
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
        });
                /////////слушателья для окна AddForm (добавления нового табеля)/////////////
        af.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                af.setVisible(false);
                setVisible(true);
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        //////////////////////////////////////////////////////////
        // добавляем слушателя для кнопки закрытия окна AddForm//////////
        af.bClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                af.setVisible(false);
                setVisible(true);
            }
        });
        ////////////////////////////////////////////////////////////////
/////слушатель окна для ChangeForm (форма изменения табеля)/////
        chf.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                chf.setVisible(false);
                setVisible(true);
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        //////////////////////////////////////////////////////////////
        chf.bClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chf.setVisible(false);
                setVisible(true);
            }
        });

        cf.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                cf.setVisible(false);
                setVisible(true);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        //кнопка для закрытия формы CreatedForm
        cf.bClose.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cf.setVisible(false);
                setVisible(true);

            }
        });
        bViewVed.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                ecVedView.setTitle("Просмотр ведомости за " + EconomistMainForm.cbMonth.getSelectedItem() + " " +EconomistMainForm.cbYear.getSelectedItem());

                try {
                    getData("SELECT * FROM ved_" + strToIntMonth(cbMonth));
                    ecVedView.table.setModel(new DefaultTableModel(EconomistMainForm.getData("SELECT * FROM ved_" + EconomistMainForm.strToIntMonth(EconomistMainForm.cbMonth)),
                            EconomistVedView.column));
                    // выставление размера столбцов
                    for (int i = 0; i < 62; i++) {
                        ecVedView.table.getColumnModel().getColumn(i).setPreferredWidth(200);
                        ecVedView.setVisible(true);
                        setVisible(false);
                    }
                } catch (Exception ex){JOptionPane.showMessageDialog(null,"Ведомость не найдена");}

            }
        });

        ecVedView.bSearch.addActionListener(new ActionListener() {
            DBConnect dbc = null;
            Statement st = null;
            @Override
            public void actionPerformed(ActionEvent e) {
                dbc = new DBConnect();
                try {
                    st = dbc.getConnection().createStatement();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                String name = JOptionPane.showInputDialog(null, "Введите ФИО", "Поиск", JOptionPane.PLAIN_MESSAGE);
                if (!name.equals("null")) {
                    String searchQuery = "SELECT * FROM ved_" + strToIntMonth(cbMonth) +
                            " WHERE fio = '" + name + "'";
                    getData(searchQuery);
                    if (getData(searchQuery).isEmpty()) {
                        JOptionPane.showMessageDialog(null, "ФИО " + name + " не найдено");
                    } else {
                        EconomistVedView.table.setModel(
                                new DefaultTableModel(getData(searchQuery),
                                        EconomistVedView.column)); // отобразить данные в таблице
                        // выставление размера столбцов
                        for (int i = 0; i < 62; i++) {
                            EconomistVedView.table.getColumnModel().getColumn(i).setPreferredWidth(200);
                        }
                    }
                }
                try {
                    dbc.getConnection().close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        ecVedView.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                ecVedView.setVisible(false);
                setVisible(true);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    //метод преобразует название месяца в его номер
    //на выходе выдает будущее название новой таблицы(месяц и год табеля)
    static String strToIntMonth(JComboBox cb) {
        String answer = " ";
        int iMonth = -1;
        switch (cb.getSelectedIndex()) {
            case 0:
                iMonth = 1;
                break;
            case 1:
                iMonth = 2;
                break;
            case 2:
                iMonth = 3;
                break;
            case 3:
                iMonth = 4;
                break;
            case 4:
                iMonth = 5;
                break;
            case 5:
                iMonth = 6;
                break;
            case 6:
                iMonth = 7;
                break;
            case 7:
                iMonth = 8;
                break;
            case 8:
                iMonth = 9;
                break;
            case 9:
                iMonth = 10;
                break;
            case 10:
                iMonth = 11;
                break;
            case 11:
                iMonth = 12;
                break;
            default:
                JOptionPane.showMessageDialog(null, "Не знаю как, но вы выбрали не тот месяц!");
        }
        return answer = iMonth + "_" + cbYear.getSelectedItem();
    }

    /////////////////////////////////////////////////////////////
    //метод вытаскивает данные из таблицы БД в вектор data
    public static Vector getData(String query) {
        Vector data = new Vector();
        DBConnect dbc = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            dbc = new DBConnect();
            st = dbc.getConnection().createStatement();
            rs = st.executeQuery(query);
            int numColumns = rs.getMetaData().getColumnCount(); // считаем число столбцов
            while (rs.next()) {
                Vector row = new Vector();
                for (int i = 1; i <= numColumns; i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
        } catch (SQLException e) {
        } finally {
            try {
                st.close();
                rs.close();
                dbc.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

}
