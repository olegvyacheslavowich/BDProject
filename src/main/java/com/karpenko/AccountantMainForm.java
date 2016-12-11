package com.karpenko;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Vector;


/**
 * Created by Олег on 03.11.2016.
 */
public class AccountantMainForm extends JFrame {
    private Object[] object = {"Да", "Нет"};
    static String sYear[] = {"2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025"};
    static String[] sMonth = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль",
            "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    static JComboBox cbMonth = new JComboBox(sMonth);
    static JComboBox cbYear = new JComboBox(sYear);
    static JButton bCreat = new JButton("Создать");
    static JButton bChange = new JButton("Изменить");
    static JButton bToExcel = new JButton("Конвертировать в Excel");
    static JButton bClose = new JButton("Выход");
    static JButton bAdded;
    static String addFormName = "Ведомость начисления заработной платы " + cbMonth.getSelectedItem() + " " + cbYear.getSelectedItem();

    AccountantMainForm() {
        super("Укажите месяц и год");
        setLayout(new GridLayout(2, 1));
        setVisible(false);                              //видимость выкл
        setSize(520, 300);                             //начальный размер
        setResizable(false);                             //без изменения размера окна
        setLocationRelativeTo(null);                        //отобразить в центре экрана
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);  //включаем кнопку закрытия окна

        ImageIcon icon = new ImageIcon("home.png");
        setIconImage(icon.getImage());

        final AccountantCreatedForm accountantCreatedForm = new AccountantCreatedForm();
        final AccountantAddForm accountantAddForm = new AccountantAddForm();
        final AccountantBDToExcel accountantBDToExcel = new AccountantBDToExcel();

        Panel pDate = new Panel();
        JLabel lTitle = new JLabel("Укажите месяц и год табеля, на основе которого будет формироваться ведомость");
        lTitle.setHorizontalAlignment(SwingConstants.CENTER);
        pDate.setLayout(new GridLayout(3, 1));
        pDate.add(lTitle);
        pDate.add(cbMonth);
        pDate.add(cbYear);
        add(pDate);
        Panel pButton = new Panel();
        pButton.setLayout(new GridLayout(5, 1));
        pButton.add(bCreat);
        pButton.add(bChange);
        pButton.add(bToExcel);
        pButton.add(bClose);
        add(pButton);

        //запрос на добавление новой ведомости
        final String newVedQuery =
                "(\n" +
                        "  tabnum INT(10) NOT NULL,\n" +
                        "  fio VARCHAR(100) NOT NULL,\n" +
                        "  dolzh VARCHAR(100) NOT NULL,\n" +
                        "  razr INT(2) NOT NULL,\n" +
                        "  person VARCHAR(5) NOT NULL,\n" +
                        "  oklad_tarif DECIMAL (20,2) NOT NULL,\n" +
                        "  dney INT(2) NOT NULL,\n" +
                        "  chas INT(3) NOT NULL,\n" +
                        "  norma_chas INT(3) NOT NULL,\n" +
                        "  sum_za_chas INT(4) NOT NULL,\n" +
                        "  vyslugi DECIMAL (20,2) NOT NULL,\n" +
                        "  klass DECIMAL (20,2),\n" +
                        "  prem DECIMAL (20,2),\n" +
                        "  otopl DECIMAL (20,2),\n" +
                        "  osoboslozh DECIMAL (20,2),\n" +
                        "  RZO DECIMAL (20,2),\n" +
                        "  vred_chas INT(3),\n" +
                        "  vred_proc DECIMAL (20,2),\n" +
                        "  sverhur INT(2),\n" +
                        "  noch INT(3),\n" +
                        "  ekolog INT(3),\n" +
                        "  vyh_prazd INT(3),\n" +
                        "  tarif_proezd INT(3),\n" +
                        "  pers_dopl INT,\n" +
                        "  oklad DECIMAL (20,2),\n" +
                        "  oplata_netrud DECIMAL (20,2),\n" +
                        "  otpusk DECIMAL (20,2),\n" +
                        "  komp_za_otp DECIMAL (20,2),\n" +
                        "  raz_har_tr DECIMAL (20,2),\n" +
                        "  noch2 DECIMAL (20,2),\n" +
                        "  prazd_vyh DECIMAL (20,2),\n" +
                        "  sverh_ur DECIMAL (20,2),\n" +
                        "  osob_slozh_usl DECIMAL (20,2),\n" +
                        "  dop_za_zon_obls DECIMAL (20,2),\n" +
                        "  dop_za_eko DECIMAL (20,2),\n" +
                        "  tarif DECIMAL (20,2),\n" +
                        "  vysluga_let DECIMAL (20,2),\n" +
                        "  nadb_kassif DECIMAL (20,2),\n" +
                        "  tyazh_usk_trud DECIMAL (20,2),\n" +
                        "  ezh_prem_rab DECIMAL (20,2),\n" +
                        "  nad_zvan_poch_zhez DECIMAL (20,2),\n" +
                        "  vsego_nach DECIMAL (20,2),\n" +
                        "  obyaz_pens DECIMAL (20,2),\n" +
                        "  ind_nalog DECIMAL (20,2),\n" +
                        "  den_sred_v_kasse DECIMAL (20,2),\n" +
                        "  den_sred_bank_shet DECIMAL (20,2),\n" +
                        "  dolzh_zp DECIMAL (20,2),\n" +
                        "  dolzh_po_prochim DECIMAL (20,2),\n" +
                        "  dolzh_po_obuch DECIMAL (20,2),\n" +
                        "  osn_sum_zadolzh DECIMAL (20,2),\n" +
                        "  osn_sum_komand DECIMAL (20,2),\n" +
                        "  kratk_kred_zadolzh DECIMAL (20,2),\n" +
                        "  sum_aliment DECIMAL (20,2),\n" +
                        "  sum_ud_s_rab DECIMAL (20,2),\n" +
                        "  proch_kred_dolg DECIMAL (20,2),\n" +
                        "  vsego_ud DECIMAL (20,2),\n" +
                        "  sum_k_vydache DECIMAL (20,2),\n" +
                        "  ch_co DECIMAL (20,2),\n" +
                        "  sum_soc_nal DECIMAL (20,2),\n" +
                        "  sum_soc_och DECIMAL (20,2),\n" +
                        "  nalog_vychety DECIMAL (12,2),\n" +
                        "  lgota DECIMAL (12,2)\n" +
                        ");";
// класс - слушатель для кнопок
        class AddFormButtons implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBConnect dbc = null;
                Statement st = null;
                String queryTab = "SELECT fio FROM " + strToIntMonth(cbMonth); //запрос на наличие табеля
                String queryVed = "SELECT fio FROM ved_" + strToIntMonth(cbMonth); // запрос на наличие ведомости
                //событие для кнопки Создать
                if (e.getSource() == bCreat) {
                    //проверка на существование табеля
                    if (dataToString(queryTab) != null) {
                        //проверка на существование ведомости
                        if (dataToString(queryVed) == null) {
                            accountantAddForm.tfOkladTarif.setText(""); accountantAddForm.tfNormaChas.setText(""); accountantAddForm.tfVyslugi.setText("");
                            accountantAddForm.tfKlassnot.setText(""); accountantAddForm.tfPremi.setText("");accountantAddForm.tfOtoplenie.setText("");
                            accountantAddForm.tfOsoboclozh.setText(""); accountantAddForm.tfRZO.setText("");accountantAddForm.tfChas.setText("");
                            accountantAddForm.tfProc.setText("");accountantAddForm.tfEkolog.setText("");accountantAddForm.tfPersDopl.setText("");
                            accountantAddForm.tfOplVremNetred.setText(""); accountantAddForm.tfOtpusk.setText(""); accountantAddForm.tfKompZaOtp.setText("");
                            accountantAddForm.tfRazHarTr.setText(""); accountantAddForm.tfOsoboslozhUsl.setText("");accountantAddForm.tfPochZhelez.setText("");
                            accountantAddForm.tfDenSredVKas.setText("");accountantAddForm.tfDenSrNaTekB.setText("");accountantAddForm.tfOsnSumZadZP.setText("");
                            accountantAddForm.tfOsSumZadPoProch.setText(""); accountantAddForm.tfOsSumPoObuch.setText("");accountantAddForm.tfOsSumZad.setText("");
                            accountantAddForm.tfOsSumKomand.setText(""); accountantAddForm.tfKratkKredit.setText(""); accountantAddForm.tfSumAlim.setText("");
                            accountantAddForm.tfSumUdPoDok.setText("");accountantAddForm.tfProchKratk.setText(""); accountantAddForm.tfNalVych.setText("");
                            accountantAddForm.tfLgota.setText("");
                            dbc = new DBConnect();
                            try { // Трай кетч для ST
                                st = dbc.getConnection().createStatement();
                                st.execute("CREATE TABLE tabeldb.ved_" + strToIntMonth(cbMonth) + newVedQuery);
                                accountantAddForm.cbFio.removeAllItems();
                                Vector data = dataToString(queryTab);
                                //удаляем повторяющиеся ФИО
                                for (int i = 0; i < data.size(); i++) {
                                    for (int j = 0; j < data.size(); j++) {
                                        if (i != j) {
                                            if (data.get(i).equals(data.get(j)))
                                                data.remove(j);
                                        }
                                    }
                                }
                                // добавляем ФИО в комбобокс
                                for (int i = 0; i < data.size(); i++)
                                    accountantAddForm.cbFio.addItem((data.get(i)));
                                setVisible(false);
                                accountantAddForm.setVisible(true);
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            } finally {
                                try { // трай кетч для закрытия конекшена
                                    dbc.getConnection().close();
                                    st.close();
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }
                            }
                            // изменить на предложение изменить существующую ведомость
                        } else {
                            int result = JOptionPane.showOptionDialog(null, "Ведомость за  " + cbMonth.getSelectedItem() +
                                            " " + cbYear.getSelectedItem() + " была создана ранее. Хотите изменить её?", "Внимание!",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, object, object[0]);
                            if (result == JOptionPane.YES_NO_OPTION) {
                                getTablesValue();
                                accountantCreatedForm.setVisible(true);
                                setVisible(false);
                            }
                        }
                    } else JOptionPane.showMessageDialog(null, "Табель не существет. Обратитесь к экономисту");
                }

                // события для кнопки Изменить
                if (e.getSource() == bChange) {
                    if (dataToString(queryVed) != null) {
                        getTablesValue();
                        setVisible(false);
                        accountantCreatedForm.setVisible(true);
                    } else {
                        int result = JOptionPane.showOptionDialog(null, "Ведомость за " + cbMonth.getSelectedItem() + " " +
                                        cbYear.getSelectedItem() + " не была создана. Хотите создать её?", "Внимание!",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, object, object[0]);
                        // в зависимости от того какую кнопку вернул OptionDialog вывести соответ-щий результат
                        if (result == JOptionPane.YES_NO_OPTION) {
                            //проверка на существование табеля
                            if (dataToString(queryTab) != null) {
                                //проверка на существование ведомости
                                if (dataToString(queryVed) == null) {
                                    dbc = new DBConnect();
                                    try { // Трай кетч для ST
                                        st = dbc.getConnection().createStatement();
                                        st.execute("CREATE TABLE tabeldb.ved_" + strToIntMonth(cbMonth) + newVedQuery);
                                        accountantAddForm.cbFio.removeAllItems();
                                        Vector data = dataToString(queryTab);
                                        //удаляем повторяющиеся ФИО
                                        for (int i = 0; i < data.size(); i++) {
                                            for (int j = 0; j < data.size(); j++) {
                                                if (i != j) {
                                                    if (data.get(i).equals(data.get(j)))
                                                        data.remove(j);
                                                }
                                            }
                                        }
                                        // добавляем ФИО в комбобокс
                                        for (int i = 0; i < data.size(); i++)
                                            accountantAddForm.cbFio.addItem((data.get(i)));
                                        setVisible(false);
                                        accountantAddForm.setVisible(true);
                                    } catch (SQLException e1) {
                                        e1.printStackTrace();
                                    } finally {
                                        try { // трай кетч для закрытия конекшена
                                            dbc.getConnection().close();
                                            st.close();
                                        } catch (SQLException e1) {
                                            e1.printStackTrace();
                                        }
                                    }
                                    // изменить на предложение изменить существующую ведомость
                                }
                            } else JOptionPane.showMessageDialog(null, "Табель не существет. Обратитесь к экономисту");
                        }

                    }
                }

                // события для кнопки Close для формы AccountantAddForm
                if (e.getSource().equals(accountantAddForm.bClose)){
                    accountantAddForm.setVisible(false);
                    setVisible(true);
                }
                //событие для кнопки посмотреть добавленных
                if (e.getSource().equals(accountantAddForm.bView)){
                    getTablesValue();
                    accountantAddForm.setVisible(false);
                    accountantCreatedForm.setVisible(true);
                }
                //событие для кнопки Добавить формы AccountantCreatedForm
                if (e.getSource().equals(accountantCreatedForm.bAdd)){
                    accountantAddForm.cbFio.removeAllItems();
                    accountantAddForm.tfOkladTarif.setText(""); accountantAddForm.tfNormaChas.setText(""); accountantAddForm.tfVyslugi.setText("");
                    accountantAddForm.tfKlassnot.setText(""); accountantAddForm.tfPremi.setText("");accountantAddForm.tfOtoplenie.setText("");
                    accountantAddForm.tfOsoboclozh.setText(""); accountantAddForm.tfRZO.setText("");accountantAddForm.tfChas.setText("");
                    accountantAddForm.tfProc.setText("");accountantAddForm.tfEkolog.setText("");accountantAddForm.tfPersDopl.setText("");
                    accountantAddForm.tfOplVremNetred.setText(""); accountantAddForm.tfOtpusk.setText(""); accountantAddForm.tfKompZaOtp.setText("");
                    accountantAddForm.tfRazHarTr.setText(""); accountantAddForm.tfOsoboslozhUsl.setText("");accountantAddForm.tfPochZhelez.setText("");
                    accountantAddForm.tfDenSredVKas.setText("");accountantAddForm.tfDenSrNaTekB.setText("");accountantAddForm.tfOsnSumZadZP.setText("");
                    accountantAddForm.tfOsSumZadPoProch.setText(""); accountantAddForm.tfOsSumPoObuch.setText("");accountantAddForm.tfOsSumZad.setText("");
                    accountantAddForm.tfOsSumKomand.setText(""); accountantAddForm.tfKratkKredit.setText(""); accountantAddForm.tfSumAlim.setText("");
                    accountantAddForm.tfSumUdPoDok.setText("");accountantAddForm.tfProchKratk.setText(""); accountantAddForm.tfNalVych.setText("");
                    accountantAddForm.tfLgota.setText("");
                    Vector data = dataToString(queryTab);
                    //удаляем повторяющиеся ФИО
                    for (int i = 0; i < data.size(); i++) {
                        for (int j = 0; j < data.size(); j++) {
                            if (i != j) {
                                if (data.get(i).equals(data.get(j)))
                                    data.remove(j);
                            }
                        }
                    }
                    // добавляем ФИО в комбобокс
                    for (int i = 0; i < data.size(); i++)
                        accountantAddForm.cbFio.addItem((data.get(i)));
                        accountantCreatedForm.setVisible(false);
                        accountantAddForm.setVisible(true);
                }
                //событие изменения записи в таблице
                if (e.getSource().equals(accountantCreatedForm.bChange)){
                    accountantAddForm.bCreate.setVisible(false);
                    accountantAddForm.bChange.setVisible(true);
                    if (accountantCreatedForm.table.getSelectedRow() != -1) {
                        String fioCb[] = {(String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 1)};
                        accountantAddForm.cbFio.removeAllItems();
                        accountantAddForm.cbFio.addItem(fioCb[0]);
                        accountantAddForm.tfOkladTarif.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 5));
                        accountantAddForm.tfNormaChas.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 8));
                        accountantAddForm.tfVyslugi.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 10));
                        accountantAddForm.tfKlassnot.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 11));
                        accountantAddForm.tfPremi.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 12));
                        accountantAddForm.tfOtoplenie.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 13));
                        accountantAddForm.tfOsoboclozh.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 14));
                        accountantAddForm.tfRZO.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 15));
                        accountantAddForm.tfChas.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 16));
                        accountantAddForm.tfProc.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 17));
                        accountantAddForm.tfEkolog.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 20));
                        accountantAddForm.tfPersDopl.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 23));
                        accountantAddForm.tfOplVremNetred.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 25));
                        accountantAddForm.tfOtpusk.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 26));
                        accountantAddForm.tfKompZaOtp.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 27));
                        accountantAddForm.tfRazHarTr.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 28));
                        accountantAddForm.tfOsoboslozhUsl.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 32));
                        accountantAddForm.tfPochZhelez.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 40));
                        accountantAddForm.tfDenSredVKas.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 44));
                        accountantAddForm.tfDenSrNaTekB.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 45));
                        accountantAddForm.tfOsnSumZadZP.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 46));
                        accountantAddForm.tfOsSumZadPoProch.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 47));
                        accountantAddForm.tfOsSumPoObuch.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 48));
                        accountantAddForm.tfOsSumZad.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 49));
                        accountantAddForm.tfOsSumKomand.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 50));
                        accountantAddForm.tfKratkKredit.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 51));
                        accountantAddForm.tfSumAlim.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 52));
                        accountantAddForm.tfSumUdPoDok.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 53));
                        accountantAddForm.tfProchKratk.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 54));
                        accountantAddForm.tfNalVych.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 60));
                        accountantAddForm.tfLgota.setText((String) accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 61));
                        accountantAddForm.setTitle("Изменить данные " + accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 1));
                        accountantCreatedForm.setVisible(false); // скрыть CreatedForm
                        accountantAddForm.setVisible(true);  //показать форму изменения*/
                    } else JOptionPane.showMessageDialog(null, "Выберите интересующую строку в таблице");
                }
                //событие удаления записи на форме AccountantChangeForm
                if (e.getSource().equals(accountantCreatedForm.bDelete)){
                    try {
                        dbc = new DBConnect();
                        st = dbc.getConnection().createStatement();
                        try {
                            if (accountantCreatedForm.table.getSelectedRow() != -1) {
                                final String addWorkerQuery = "DELETE FROM ved_" + strToIntMonth(cbMonth) +
                                        " WHERE fio = '" +
                                        accountantCreatedForm.table.getValueAt(accountantCreatedForm.table.getSelectedRow(), 1) + "'";
                                st.executeUpdate(addWorkerQuery); // выполняем запрос удаления
                                JOptionPane.showMessageDialog(null, "Запись удалена");
                                getTablesValue();
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
                //событие поиска записи на форме AccountantChangeForm
                if (e.getSource().equals(accountantCreatedForm.bSearch)){
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
                        EconomistMainForm.getData(searchQuery);
                        if (EconomistMainForm.getData(searchQuery).isEmpty()) {
                            JOptionPane.showMessageDialog(null, "ФИО " + name + " не найдено");
                        } else {
                            AccountantCreatedForm.table.setModel(
                                    new DefaultTableModel(EconomistMainForm.getData(searchQuery),
                                            AccountantCreatedForm.column)); // отобразить данные в таблице
                            // выставление размера столбцов
                            for (int i = 0; i < 62; i++) {
                                AccountantCreatedForm.table.getColumnModel().getColumn(i).setPreferredWidth(200);
                            }
                        }
                    }
                    try {
                        dbc.getConnection().close();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                //событие закрытие формы AccountantCreatedForm
                if (e.getSource().equals(accountantCreatedForm.bClose)){
                    setVisible(true);
                    accountantCreatedForm.setVisible(false);
                }
                //событие конвертирование в Excel
                if (e.getSource().equals(accountantCreatedForm.bToExcel) || e.getSource().equals(bToExcel))
                {
                    String selFile = null;
                    try {
                        EconomistMainForm.getData("SELECT * FROM ved_" + strToIntMonth(cbMonth));
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setSelectedFile(new File("Ведомость за " + cbMonth.getSelectedItem() + " " + cbYear.getSelectedItem()));
                        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                            selFile = String.valueOf(fileChooser.getSelectedFile());
                            new AccountantBDToExcel().writeIntoExcel(selFile);
                            JOptionPane.showMessageDialog(null, "Ведомость конвертирована");
                            int n = JOptionPane.showConfirmDialog(
                                    null,
                                    "Открыть файл Ведомость за " + cbMonth.getSelectedItem() + " " + cbYear.getSelectedItem(),
                                    "Открыть файл",
                                    JOptionPane.YES_NO_OPTION);
                            if (n == JOptionPane.YES_NO_OPTION) {
                                try {
                                    Desktop.getDesktop().open(new File(selFile + ".xlsx"));
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    } catch (NullPointerException ex) {JOptionPane.showMessageDialog(null,"Ведомость не существует");}
                    catch (IOException e1) {
                        JOptionPane.showMessageDialog(null, "Файл используется");
                    }
                }
            }
        }

        ActionListener al = new AddFormButtons();
        bCreat.addActionListener(al);
        bChange.addActionListener(al);
        bToExcel.addActionListener(al);
        bClose.addActionListener(al);
        accountantAddForm.bClose.addActionListener(al);
        accountantAddForm.bView.addActionListener(al);
        accountantCreatedForm.bAdd.addActionListener(al);
        accountantCreatedForm.bClose.addActionListener(al);
        accountantCreatedForm.bChange.addActionListener(al);
        accountantCreatedForm.bDelete.addActionListener(al);
        accountantCreatedForm.bSearch.addActionListener(al);
        accountantCreatedForm.bToExcel.addActionListener(al);

        //класс - слушатель для окон
        class WindowsListener implements WindowListener {

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                if (e.getSource() == accountantAddForm) {
                    setVisible(true);
                    accountantAddForm.setVisible(false);
                }

                if (e.getSource() == accountantCreatedForm) {
                    setVisible(true);
                    accountantCreatedForm.setVisible(false);
                }
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
        }

        WindowListener wl = new WindowsListener();
        accountantAddForm.addWindowListener(wl);
        accountantCreatedForm.addWindowListener(wl);


    }
// метод для вытаскивания данных в массив зависимости от запроса
    static Vector dataToString(String query) {
        DBConnect dbc = null;
        Statement st = null;
        ResultSet rs = null;
        Vector v = null;
        try {
            dbc = new DBConnect();
            st = dbc.getConnection().createStatement();
            rs = st.executeQuery(query);
            v = new Vector();
            while (rs.next()) {
                v.add(rs.getString(1));
                System.out.println(v);}
        } catch (SQLException es) {}
        finally {
            try {
                st.close();
                dbc.getConnection().close();


            } catch (SQLException e1) {}
        }
        return v;
    }
    // перевод названия месяца в порядковый номер
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
// метод для вытаскивания данных из БД в таблицу на форме
    public static void getTablesValue()
    {
        AccountantCreatedForm.table.setModel(
                new DefaultTableModel(EconomistMainForm.getData("SELECT * FROM ved_" + strToIntMonth(cbMonth)),
                        AccountantCreatedForm.column)); // отобразить данные в таблице
        // выставление размера столбцов
        for (int i = 0; i < 62; i++) {
            AccountantCreatedForm.table.getColumnModel().getColumn(i).setPreferredWidth(200);
        }
    }

}
