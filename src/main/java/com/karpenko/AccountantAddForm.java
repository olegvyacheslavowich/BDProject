package com.karpenko;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Vector;

/**
 * Created by Олег on 09.11.2016.
 */
public class AccountantAddForm extends JFrame {
    static JButton bChange = new JButton("Изменить");
    static JButton bCreate = new JButton("Добавить");
    static JButton bView = new JButton("Просмотр добавленных");
    static JButton bClose = new JButton("Закрыть");
    static String [] personal = {"АУР","ИТР", "осн", "всп", "прочий"};
    static JComboBox cbFio = new JComboBox();
    static JComboBox cbPersonal = new JComboBox(personal);
    static JTextField tfOkladTarif = new JTextField();
    static JTextField tfNormaChas = new JTextField();
    static JTextField tfVyslugi = new JTextField();
    static JTextField tfKlassnot = new JTextField();
    static JTextField tfPremi = new JTextField();
    static JTextField tfOtoplenie = new JTextField();
    static JTextField tfOsoboclozh = new JTextField();
    static JTextField tfRZO = new JTextField();
    static JTextField tfChas = new JTextField();
    static JTextField tfProc = new JTextField();
    static JTextField tfEkolog = new JTextField();
    static JTextField tfPersDopl = new JTextField();
    static JTextField tfOplVremNetred = new JTextField();
    static JTextField tfOtpusk = new JTextField();
    static JTextField tfKompZaOtp = new JTextField();
    static JTextField tfRazHarTr = new JTextField();
    static JTextField tfOsoboslozhUsl = new JTextField();
    static JTextField tfPochZhelez = new JTextField();
    static JTextField tfDenSredVKas = new JTextField();
    static JTextField tfDenSrNaTekB = new JTextField();
    static JTextField tfOsnSumZadZP = new JTextField();
    static JTextField tfOsSumZadPoProch = new JTextField();
    static JTextField tfOsSumPoObuch = new JTextField();
    static JTextField tfOsSumZad = new JTextField();
    static JTextField tfOsSumKomand = new JTextField();
    static JTextField tfKratkKredit = new JTextField();
    static JTextField tfSumAlim = new JTextField();
    static JTextField tfSumUdPoDok = new JTextField();
    static JTextField tfProchKratk = new JTextField();
    static JTextField tfNalVych = new JTextField();
    static JTextField tfLgota = new JTextField();

    AccountantAddForm() {
        super(AccountantMainForm.addFormName);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);  //включаем кнопку закрытия окна
        setVisible(false);                              //видимость выкл
        add(cbFio);

        JPanel pCreateItems1 = new JPanel();
        pCreateItems1.setLayout(new GridLayout(30,2));
        pCreateItems1.add(new JLabel("ФИО")); pCreateItems1.add(cbFio);
        pCreateItems1.add(new JLabel("Персонал")); pCreateItems1.add(cbPersonal);
        pCreateItems1.add(new JLabel("Оклад/Тариф")); pCreateItems1.add(tfOkladTarif);
        pCreateItems1.add(new JLabel("Норма часов")); pCreateItems1.add(tfNormaChas);
        pCreateItems1.add(new JLabel("% выслуги")); pCreateItems1.add(tfVyslugi);
        pCreateItems1.add(new JLabel("% классность")); pCreateItems1.add(tfKlassnot);
        pCreateItems1.add(new JLabel("% премии")); pCreateItems1.add(tfPremi);
        pCreateItems1.add(new JLabel("Отопление")); pCreateItems1.add(tfOtoplenie);
        pCreateItems1.add(new JLabel("Особосложные")); pCreateItems1.add(tfOsoboclozh);
        pCreateItems1.add(new JLabel("РЗО %")); pCreateItems1.add(tfRZO);
        pCreateItems1.add(new JLabel("Часы")); pCreateItems1.add(tfChas);
        pCreateItems1.add(new JLabel("%")); pCreateItems1.add(tfProc);
        pCreateItems1.add(new JLabel("Экология")); pCreateItems1.add(tfEkolog);
        pCreateItems1.add(new JLabel("Персональные доплаты")); pCreateItems1.add(tfPersDopl);
        pCreateItems1.add(new JLabel("Оплата временной нетрудоспособности")); pCreateItems1.add(tfOplVremNetred);
        pCreateItems1.add(new JLabel("Отпускные")); pCreateItems1.add(tfOtpusk);
        pCreateItems1.add(new JLabel("Компенсация за неиспользованный отпуск")); pCreateItems1.add(tfKompZaOtp);
        pCreateItems1.add(new JLabel("Разъездной характер труда")); pCreateItems1.add(tfRazHarTr);
        pCreateItems1.add(new JLabel("Особые сложные условия труда")); pCreateItems1.add(tfOsoboslozhUsl);
        pCreateItems1.add(new JLabel("Надбавка за звание Почетный железнодорожник")); pCreateItems1.add(tfPochZhelez);
        pCreateItems1.add(new JLabel("Денежные средства в кассе в тенге (1010)"));
        pCreateItems1.add(tfDenSredVKas);
        pCreateItems1.add(new JLabel("Основная сумма задолженности (12511)"));
        pCreateItems1.add(tfOsSumZad);

        JPanel pCreateItems2 = new JPanel();
        pCreateItems2.setLayout(new GridLayout(32,1));
        pCreateItems2.add(new JLabel("Денежные средства на текущих банковских счетах в тенге (1030)"));
        pCreateItems2.add(tfDenSrNaTekB);
        pCreateItems2.add(new JLabel("Основная сумма задолженности по заработной плате (3350)"));
        pCreateItems2.add(tfOsnSumZadZP);
        pCreateItems2.add(new JLabel("Основная сумма задолженности по прочим, в тенге (1210)"));
        pCreateItems2.add(tfOsSumZadPoProch);
        pCreateItems2.add(new JLabel("Основная сумма задолженности по обучению ,   в тенге (1210)"));
        pCreateItems2.add(tfOsSumPoObuch);
        pCreateItems2.add(new JLabel("Основная сумма задолженности по заработной плате, командировочные (12512)"));
        pCreateItems2.add(tfOsSumKomand);
        pCreateItems2.add(new JLabel("Краткосрочная кредиторская задолженность филиалам и структурным подразделениям (3340)"));
        pCreateItems2.add(tfKratkKredit);
        pCreateItems2.add(new JLabel("Суммы алиментов, удержанные с работников (33.9.1.01.2)"));
        pCreateItems2.add(tfSumAlim);
        pCreateItems2.add(new JLabel("Суммы  удержанные с работников по исполнительным документам (33.9.1.01.1)"));
        pCreateItems2.add(tfSumUdPoDok);
        pCreateItems2.add(new JLabel("Прочая краткосрочная кредиторская задолженность с контрагентами (33.9.1.01.5)"));
        pCreateItems2.add(tfProchKratk);
        pCreateItems2.add(new JLabel("Налоговые вычеты"));
        pCreateItems2.add(tfNalVych);
        pCreateItems2.add(new JLabel("Льгота"));
        pCreateItems2.add(tfLgota);

        JPanel pForTwoItems = new JPanel();
        pForTwoItems.setLayout(new GridLayout(1,2));
        pForTwoItems.add(pCreateItems1);
        pForTwoItems.add(pCreateItems2);

        JPanel pButtons = new JPanel();
        pButtons.setLayout(new GridLayout(4,1));
        bChange.setVisible(false);
        pButtons.add(bChange);
        pButtons.add(bCreate); pButtons.add(bView); pButtons.add(bClose);

        ImageIcon icon = new ImageIcon("home.png");
        setIconImage(icon.getImage());
        add(pButtons, BorderLayout.SOUTH);
        add(pForTwoItems,BorderLayout.NORTH);

        //слушатель для кнопок формы AddForm
        class ButtonListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                DBConnect dbc = null;
                ResultSet rs = null;
                Statement st = null;
                // запросы на получение некоторых данных из табеля
                String getTabNumFromTabel = "SELECT tab_num FROM " + AccountantMainForm.strToIntMonth(AccountantMainForm.cbMonth) + " WHERE fio = '" + cbFio.getSelectedItem()+"'";
                String getDolzhFromTabel = "SELECT dolzh FROM " + AccountantMainForm.strToIntMonth(AccountantMainForm.cbMonth) + " WHERE fio = '" + cbFio.getSelectedItem()+"'";
                String getRazrFromTabel = "SELECT razr FROM " + AccountantMainForm.strToIntMonth(AccountantMainForm.cbMonth) + " WHERE fio = '" + cbFio.getSelectedItem()+"'";
                String getFaktDneyFTab = "SELECT fakt_rab FROM " + AccountantMainForm.strToIntMonth(AccountantMainForm.cbMonth) + " WHERE fio = '" + cbFio.getSelectedItem()+"'";
                String getOtrabChasFTab ="SELECT otrab_fakt FROM " + AccountantMainForm.strToIntMonth(AccountantMainForm.cbMonth) + " WHERE fio = '" + cbFio.getSelectedItem()+"'";
                String getSverhUrFTab = "SELECT sverh_ur FROM " + AccountantMainForm.strToIntMonth(AccountantMainForm.cbMonth) + " WHERE fio = '" + cbFio.getSelectedItem()+"'";
                String getNochFTab = "SELECT noch FROM " + AccountantMainForm.strToIntMonth(AccountantMainForm.cbMonth) + " WHERE fio = '" + cbFio.getSelectedItem()+"'";
                String getDoplFTab = "SELECT doplata FROM " + AccountantMainForm.strToIntMonth(AccountantMainForm.cbMonth) + " WHERE fio = '" + cbFio.getSelectedItem()+"'";
                String getVPutiFTab = "SELECT vputi FROM " + AccountantMainForm.strToIntMonth(AccountantMainForm.cbMonth) + " WHERE fio = '" + cbFio.getSelectedItem()+"'";

                String tabNum = "", dolzh = "", razr = "";
                double faktDney = 0, chas = 0, sverhUr = 0, noch = 0, dopl = 0, vPuti = 0,sum_za_chas = 1,oklad = 1,
                        nochnye = 1,prazd_i_vyh = 1,sverhuroch = 1, doplZaRashZon = 1, doplZaEkolog = 1, tarif = 1,
                vyslugaLet = 1, nadbZaKlass = 1, tyazhUslTrud = 1, ezhPremRab = 1, vsegoNach = 0, obyazPoPens = 0,
                indPodNal = 0, vsegoUd = 0, sumKVyd = 0, chco = 0, sumSoc = 0, sumSocOtch = 1,
                        okladTarif = 0, normaChasov=0,  procVysl =0, procKlas =0, procPrem =0, otoplenie =0,
                        osoboslozh =0, rzo =0, chasy =0, proc =0, ekolog = 0, persDopl = 0, oplVremNetrud = 0, otpusk =0,
                        kompen =0, razHarTruda =0, osobSlozhUsl = 0, pochZhelez =0, denSredVKass =0, osnSumZad= 0,
                        delSredNaBank = 0, osnPoZP =0, osnZadPoProch =0, osnSumPoObuch =0, osnKomand =0,
                        kratkKred = 0, sumAlim = 0, sumUderzh =0, prochKred =0, nalogVych =0,  lgota =0;

                // для кнопки Добавить
                if (e.getSource().equals(bCreate)){
                    dbc = new DBConnect();
                    try {
                        st = dbc.getConnection().createStatement();
                        rs = st.executeQuery(getTabNumFromTabel);
                        while (rs.next()) tabNum = rs.getString(1);

                        rs = st.executeQuery(getDolzhFromTabel);
                        while (rs.next()) dolzh = rs.getString(1);

                        rs = st.executeQuery(getRazrFromTabel);
                        while (rs.next()) razr = rs.getString(1);

                        rs = st.executeQuery(getFaktDneyFTab);
                        while (rs.next()) faktDney += rs.getInt(1);

                        rs = st.executeQuery(getOtrabChasFTab);
                        while (rs.next()) chas += rs.getInt(1);

                        rs = st.executeQuery(getSverhUrFTab);
                        while (rs.next()) sverhUr += rs.getInt(1);

                        rs = st.executeQuery(getNochFTab);
                        while (rs.next()) noch += rs.getInt(1);

                        rs = st.executeQuery(getDoplFTab);
                        while (rs.next()) dopl += rs.getInt(1);

                        rs = st.executeQuery(getVPutiFTab);
                        while (rs.next()) vPuti += rs.getInt(1);

                        if (tfOkladTarif.getText().isEmpty()) okladTarif = 0;
                        else okladTarif = Double.parseDouble(tfOkladTarif.getText());
                        if (tfNormaChas.getText().equals("")) normaChasov = 0;
                        else normaChasov = Double.parseDouble(tfNormaChas.getText());
                        if (tfVyslugi.getText().equals("")) procVysl = 0;
                        else procVysl = Double.parseDouble(tfVyslugi.getText());
                        if (tfKlassnot.getText().equals("")) procKlas = 0;
                        else procKlas = Double.parseDouble(tfKlassnot.getText());
                        if (tfPremi.getText().equals("")) procPrem = 0;
                        else procPrem = Double.parseDouble(tfPremi.getText());
                        if (tfOtoplenie.getText().equals("")) otoplenie = 0;
                        else otoplenie = Double.parseDouble(tfOtoplenie.getText());
                        if (tfOsoboclozh.getText().equals("")) osoboslozh = 0;
                        else osoboslozh = Double.parseDouble(tfOsoboclozh.getText());
                        if (tfRZO.getText().equals("")) rzo = 0;
                        else rzo = Double.parseDouble(tfRZO.getText());
                        if (tfChas.getText().equals("")) chasy = 0;
                        else chasy = Double.parseDouble(tfChas.getText());
                        if (tfProc.getText().equals("")) proc = 0;
                        else proc = Double.parseDouble(tfProc.getText());
                        if (tfEkolog.getText().equals("")) ekolog = 0;
                        else ekolog = Double.parseDouble(tfEkolog.getText());
                        if (tfPersDopl.getText().equals("")) persDopl = 0;
                        else persDopl = Double.parseDouble(tfPersDopl.getText());
                        if (tfOplVremNetred.getText().equals("")) oplVremNetrud = 0;
                        else oplVremNetrud = Double.parseDouble(tfOplVremNetred.getText());
                        if (tfOtpusk.getText().equals("")) otpusk = 0;
                        else otpusk = Double.parseDouble(tfOtpusk.getText());
                        if (tfKompZaOtp.getText().equals("")) kompen = 0;
                        else kompen = Double.parseDouble(tfKompZaOtp.getText());
                        if (tfRazHarTr.getText().equals("")) razHarTruda = 0;
                        else razHarTruda = Double.parseDouble(tfRazHarTr.getText());
                        if (tfOsoboslozhUsl.getText().equals("")) osobSlozhUsl = 0;
                        else osobSlozhUsl = Double.parseDouble(tfOsoboslozhUsl.getText());
                        if (tfPochZhelez.getText().equals("")) pochZhelez = 0;
                        else pochZhelez = Double.parseDouble(tfPochZhelez.getText());
                        if (tfDenSredVKas.getText().equals("")) denSredVKass = 0;
                        else denSredVKass = Double.parseDouble(tfDenSredVKas.getText());
                        if (tfOsSumZad.getText().equals("")) osnSumZad = 0;
                        else osnSumZad = Double.parseDouble(tfOsSumZad.getText());
                        if (tfDenSrNaTekB.getText().equals("")) delSredNaBank = 0;
                        else delSredNaBank = Double.parseDouble(tfDenSrNaTekB.getText());
                        if (tfOsnSumZadZP.getText().equals("")) osnPoZP = 0;
                        else osnPoZP = Double.parseDouble(tfOsnSumZadZP.getText());
                        if (tfOsSumZadPoProch.getText().equals("")) osnZadPoProch = 0;
                        else osnZadPoProch = Double.parseDouble(tfOsSumZadPoProch.getText());
                        if (tfOsSumPoObuch.getText().equals("")) osnSumPoObuch = 0;
                        else osnSumPoObuch = Double.parseDouble(tfOsSumPoObuch.getText());
                        if (tfOsSumKomand.getText().equals("")) osnKomand = 0;
                        else osnKomand = Double.parseDouble(tfOsSumKomand.getText());
                        if (tfKratkKredit.getText().equals("")) kratkKred = 0;
                        else kratkKred = Double.parseDouble(tfKratkKredit.getText());
                        if (tfSumAlim.getText().equals("")) sumAlim = 0;
                        else sumAlim = Double.parseDouble(tfSumAlim.getText());
                        if (tfSumUdPoDok.getText().equals("")) sumUderzh = 0;
                        else sumUderzh = Double.parseDouble(tfSumUdPoDok.getText());
                        if (tfProchKratk.getText().equals("")) prochKred = 0;
                        else prochKred = Double.parseDouble(tfProchKratk.getText());
                        if (tfNalVych.getText().equals("")) nalogVych = 0;
                        else nalogVych = Double.parseDouble(tfNalVych.getText());
                        if (tfLgota.getText().equals("")) lgota = 0;
                        else lgota = Double.parseDouble(tfLgota.getText());

                        sum_za_chas = okladTarif/normaChasov;//K7
                        oklad = chas*sum_za_chas;//Z7
                        nochnye = (sum_za_chas * noch)/2;
                        prazd_i_vyh = (sum_za_chas* dopl)/2;
                        sverhuroch = (sum_za_chas * sverhUr)/2;
                        doplZaRashZon = oklad * rzo;
                        doplZaEkolog = (ekolog*sum_za_chas)*0.2;
                        tarif = sum_za_chas * vPuti;
                        vyslugaLet = chas * sum_za_chas * procVysl;
                        nadbZaKlass = oklad * procKlas;
                        tyazhUslTrud = sum_za_chas * chasy * proc;
                        ezhPremRab = oklad * procPrem;
                        vsegoNach = persDopl + oklad + oplVremNetrud + otpusk + kompen + razHarTruda + nochnye + prazd_i_vyh + sverhuroch +
                                    osobSlozhUsl + doplZaRashZon + doplZaEkolog + tarif + vyslugaLet + nadbZaKlass + tyazhUslTrud +
                                ezhPremRab + pochZhelez; //AR7
                        obyazPoPens = (vsegoNach - razHarTruda - kompen - doplZaRashZon) * 0.1;
                        indPodNal = (vsegoNach - obyazPoPens - razHarTruda - nalogVych - lgota - doplZaEkolog) * 0.1;
                        vsegoUd = obyazPoPens + indPodNal + denSredVKass + delSredNaBank + osnPoZP +
                                    osnZadPoProch + osnSumPoObuch + osnSumZad + osnKomand + kratkKred +
                                    sumAlim + sumUderzh + prochKred;
                        sumKVyd = vsegoNach - vsegoUd;
                            chco = (vsegoNach - obyazPoPens - razHarTruda - kompen - doplZaEkolog) * 0.11;
                            sumSocOtch = chco * 0.454545;
                            sumSoc = chco - sumSocOtch;
                        //формируем запрос на добавление полученных выше значений в ведомость
                        String addQuery = "INSERT INTO ved_" + AccountantMainForm.strToIntMonth(AccountantMainForm.cbMonth) +
                                " (tabnum,fio,dolzh,razr,person,oklad_tarif,dney,chas,norma_chas,sum_za_chas,vyslugi," +
                                "klass,prem,otopl,osoboslozh,RZO,vred_chas,vred_proc,sverhur,noch,ekolog,vyh_prazd," +
                                "tarif_proezd,pers_dopl,oklad,oplata_netrud,otpusk,komp_za_otp,raz_har_tr,noch2,prazd_vyh,sverh_ur," +
                                "osob_slozh_usl,dop_za_zon_obls,dop_za_eko,tarif,vysluga_let,nadb_kassif,tyazh_usk_trud," +
                                "ezh_prem_rab,nad_zvan_poch_zhez,vsego_nach,obyaz_pens,ind_nalog,den_sred_v_kasse,den_sred_bank_shet,"+
                                "dolzh_zp,dolzh_po_prochim,dolzh_po_obuch,osn_sum_zadolzh,osn_sum_komand,kratk_kred_zadolzh,sum_aliment," +
                                "sum_ud_s_rab,proch_kred_dolg,vsego_ud,sum_k_vydache,ch_co,sum_soc_nal,sum_soc_och," +
                                "nalog_vychety,lgota)" +
                                "VALUES ('" + tabNum + "','" + cbFio.getSelectedItem() + "','" + dolzh + "','"  + razr + "','" +
                                cbPersonal.getSelectedItem() + "','" + tfOkladTarif.getText() + "','" + faktDney + "','" + chas + "','" +
                                tfNormaChas.getText() + "','" + sum_za_chas +"','" + tfVyslugi.getText() + "','" + tfKlassnot.getText() + "','" +
                                tfPremi.getText() + "','" + tfOtoplenie.getText() + "','" + tfOsoboclozh.getText() + "','" + tfRZO.getText() + "','" +
                                tfChas.getText() + "','" + tfProc.getText() + "','" + sverhUr + "','" + noch + "','" + tfEkolog.getText() + "','" +
                                dopl + "','" + vPuti + "','" + tfPersDopl.getText() + "','" + oklad + "','" + tfOplVremNetred.getText() + "','" +
                                tfOtpusk.getText() + "','" + tfKompZaOtp.getText() + "','" + tfRazHarTr.getText() + "','" + nochnye + "','" +
                                prazd_i_vyh + "','" + sverhuroch + "','" + tfOsoboslozhUsl.getText() + "','" + doplZaRashZon + "','" + doplZaEkolog + "','" +
                                tarif + "','" + vyslugaLet +"','" + nadbZaKlass + "','" + tyazhUslTrud + "','" + ezhPremRab + "','" + tfPochZhelez.getText() + "','" +
                                vsegoNach + "','" + obyazPoPens + "','" + indPodNal +"','" + tfDenSredVKas.getText()+ "','" + tfDenSrNaTekB.getText() + "','" +
                                tfOsnSumZadZP.getText() + "','" + tfOsSumZadPoProch.getText() + "','" + tfOsSumPoObuch.getText()+ "','" + tfOsSumZad.getText() + "','"+
                                tfOsSumKomand.getText() + "','" + tfKratkKredit.getText() + "','" + tfSumAlim.getText() + "','" + tfSumUdPoDok.getText() + "','" +
                                tfProchKratk.getText() + "','" + vsegoUd + "','" + sumKVyd + "','" + chco + "','" + sumSoc + "','" + sumSocOtch + "','" +
                                tfNalVych.getText() + "','" + tfLgota.getText() + "')";

                            st.execute(addQuery);

                        tfOkladTarif.setText(""); tfNormaChas.setText(""); tfVyslugi.setText(""); tfKlassnot.setText(""); tfPremi.setText("");
                        tfOtoplenie.setText(""); tfOsoboclozh.setText(""); tfRZO.setText(""); tfChas.setText(""); tfProc.setText("");
                        tfEkolog.setText(""); tfPersDopl.setText(""); tfOplVremNetred.setText(""); tfOtpusk.setText(""); tfKompZaOtp.setText("");
                        tfRazHarTr.setText(""); tfOsoboslozhUsl.setText(""); tfPochZhelez.setText(""); tfDenSredVKas.setText("");
                        tfDenSrNaTekB.setText(""); tfOsnSumZadZP.setText(""); tfOsSumZadPoProch.setText(""); tfOsSumPoObuch.setText("");
                        tfOsSumZad.setText(""); tfOsSumKomand.setText(""); tfKratkKredit.setText(""); tfSumAlim.setText(""); tfSumUdPoDok.setText("");
                        tfProchKratk.setText(""); tfNalVych.setText(""); tfLgota.setText("");
                    }
                    catch(NumberFormatException exc)
                    {JOptionPane.showMessageDialog(null,"Введены неверные данные");}
                    catch (SQLException e1)
                    {JOptionPane.showMessageDialog(null,"Заполните все ячейки");}
                    finally {
                        try {
                            dbc.getConnection().close();
                            rs.close();
                        } catch (SQLException e1) {
                            JOptionPane.showMessageDialog(null,"Ошибка");
                        }
                    }
                }
                if (e.getSource().equals(bChange)){
                    try {
                        if (tfOkladTarif.getText().isEmpty()) okladTarif = 0;
                        else okladTarif = Double.parseDouble(tfOkladTarif.getText());
                        if (tfNormaChas.getText().equals("")) normaChasov = 0;
                        else normaChasov = Double.parseDouble(tfNormaChas.getText());
                        if (tfVyslugi.getText().equals("")) procVysl = 0;
                        else procVysl = Double.parseDouble(tfVyslugi.getText());
                        if (tfKlassnot.getText().equals("")) procKlas = 0;
                        else procKlas = Double.parseDouble(tfKlassnot.getText());
                        if (tfPremi.getText().equals("")) procPrem = 0;
                        else procPrem = Double.parseDouble(tfPremi.getText());
                        if (tfOtoplenie.getText().equals("")) otoplenie = 0;
                        else otoplenie = Double.parseDouble(tfOtoplenie.getText());
                        if (tfOsoboclozh.getText().equals("")) osoboslozh = 0;
                        else osoboslozh = Double.parseDouble(tfOsoboclozh.getText());
                        if (tfRZO.getText().equals("")) rzo = 0;
                        else rzo = Double.parseDouble(tfRZO.getText());
                        if (tfChas.getText().equals("")) chasy = 0;
                        else chasy = Double.parseDouble(tfChas.getText());
                        if (tfProc.getText().equals("")) proc = 0;
                        else proc = Double.parseDouble(tfProc.getText());
                        if (tfEkolog.getText().equals("")) ekolog = 0;
                        else ekolog = Double.parseDouble(tfEkolog.getText());
                        if (tfPersDopl.getText().equals("")) persDopl = 0;
                        else persDopl = Double.parseDouble(tfPersDopl.getText());
                        if (tfOplVremNetred.getText().equals("")) oplVremNetrud = 0;
                        else oplVremNetrud = Double.parseDouble(tfOplVremNetred.getText());
                        if (tfOtpusk.getText().equals("")) otpusk = 0;
                        else otpusk = Double.parseDouble(tfOtpusk.getText());
                        if (tfKompZaOtp.getText().equals("")) kompen = 0;
                        else kompen = Double.parseDouble(tfKompZaOtp.getText());
                        if (tfRazHarTr.getText().equals("")) razHarTruda = 0;
                        else razHarTruda = Double.parseDouble(tfRazHarTr.getText());
                        if (tfOsoboslozhUsl.getText().equals("")) osobSlozhUsl = 0;
                        else osobSlozhUsl = Double.parseDouble(tfOsoboslozhUsl.getText());
                        if (tfPochZhelez.getText().equals("")) pochZhelez = 0;
                        else pochZhelez = Double.parseDouble(tfPochZhelez.getText());
                        if (tfDenSredVKas.getText().equals("")) denSredVKass = 0;
                        else denSredVKass = Double.parseDouble(tfDenSredVKas.getText());
                        if (tfOsSumZad.getText().equals("")) osnSumZad = 0;
                        else osnSumZad = Double.parseDouble(tfOsSumZad.getText());
                        if (tfDenSrNaTekB.getText().equals("")) delSredNaBank = 0;
                        else delSredNaBank = Double.parseDouble(tfDenSrNaTekB.getText());
                        if (tfOsnSumZadZP.getText().equals("")) osnPoZP = 0;
                        else osnPoZP = Double.parseDouble(tfOsnSumZadZP.getText());
                        if (tfOsSumZadPoProch.getText().equals("")) osnZadPoProch = 0;
                        else osnZadPoProch = Double.parseDouble(tfOsSumZadPoProch.getText());
                        if (tfOsSumPoObuch.getText().equals("")) osnSumPoObuch = 0;
                        else osnSumPoObuch = Double.parseDouble(tfOsSumPoObuch.getText());
                        if (tfOsSumKomand.getText().equals("")) osnKomand = 0;
                        else osnKomand = Double.parseDouble(tfOsSumKomand.getText());
                        if (tfKratkKredit.getText().equals("")) kratkKred = 0;
                        else kratkKred = Double.parseDouble(tfKratkKredit.getText());
                        if (tfSumAlim.getText().equals("")) sumAlim = 0;
                        else sumAlim = Double.parseDouble(tfSumAlim.getText());
                        if (tfSumUdPoDok.getText().equals("")) sumUderzh = 0;
                        else sumUderzh = Double.parseDouble(tfSumUdPoDok.getText());
                        if (tfProchKratk.getText().equals("")) prochKred = 0;
                        else prochKred = Double.parseDouble(tfProchKratk.getText());
                        if (tfNalVych.getText().equals("")) nalogVych = 0;
                        else nalogVych = Double.parseDouble(tfNalVych.getText());
                        if (tfLgota.getText().equals("")) lgota = 0;
                        else lgota = Double.parseDouble(tfLgota.getText());

                        sum_za_chas = okladTarif / normaChasov;//K7
                        oklad = chas * sum_za_chas;//Z7
                        nochnye = (sum_za_chas * noch) / 2;
                        prazd_i_vyh = (sum_za_chas * dopl) / 2;
                        sverhuroch = (sum_za_chas * sverhUr) / 2;
                        doplZaRashZon = oklad * rzo;
                        doplZaEkolog = (ekolog * sum_za_chas) * 0.2;
                        tarif = sum_za_chas * vPuti;
                        vyslugaLet = chas * sum_za_chas * procVysl;
                        nadbZaKlass = oklad * procKlas;
                        tyazhUslTrud = sum_za_chas * chasy * proc;
                        ezhPremRab = oklad * procPrem;
                        vsegoNach = persDopl + oklad + oplVremNetrud + otpusk + kompen + razHarTruda + nochnye + prazd_i_vyh + sverhuroch +
                                osobSlozhUsl + doplZaRashZon + doplZaEkolog + tarif + vyslugaLet + nadbZaKlass + tyazhUslTrud +
                                ezhPremRab + pochZhelez; //AR7
                        obyazPoPens = (vsegoNach - razHarTruda - kompen - doplZaRashZon) * 0.1;
                        indPodNal = (vsegoNach - obyazPoPens - razHarTruda - nalogVych - lgota - doplZaEkolog) * 0.1;
                        vsegoUd = obyazPoPens + indPodNal + denSredVKass + delSredNaBank + osnPoZP +
                                osnZadPoProch + osnSumPoObuch + osnSumZad + osnKomand + kratkKred +
                                sumAlim + sumUderzh + prochKred;
                        sumKVyd = vsegoNach - vsegoUd;
                        chco = (vsegoNach - obyazPoPens - razHarTruda - kompen - doplZaEkolog) * 0.11;
                        sumSocOtch = chco * 0.454545;
                        sumSoc = chco - sumSocOtch;
                        final String addWorkerQuery = "UPDATE ved_" + AccountantMainForm.strToIntMonth(AccountantMainForm.cbMonth) +
                                " SET person = '" + cbPersonal.getSelectedItem() + "',oklad_tarif = '" + tfOkladTarif.getText() +
                                "',dney = '" + faktDney + "',norma_chas = '" + tfNormaChas.getText() +
                                "',sum_za_chas = '" + sum_za_chas + "',vyslugi = '" + tfVyslugi.getText() +
                                "',klass = '" + tfKlassnot.getText() + "',prem = '" + tfPremi.getText() +
                                "',otopl = '" + tfOtoplenie.getText() + "',osoboslozh = '" + tfOsoboclozh.getText() +
                                "',RZO = '" + tfRZO.getText() + "',vred_chas = '" + tfChas.getText() +
                                "',vred_proc = '" + tfProc.getText() + "',ekolog = '" + tfEkolog.getText() +
                                "',pers_dopl = '" + tfPersDopl.getText() + "',oklad = '" + oklad +
                                "',oplata_netrud = '" + tfOplVremNetred.getText() + "',otpusk = '" + tfOtpusk.getText() +
                                "',komp_za_otp = '" + tfKompZaOtp.getText() + "',raz_har_tr = '" + tfRazHarTr.getText() +
                                "',noch2 = '" + nochnye + "',prazd_vyh = '" + prazd_i_vyh + "',sverh_ur = '" + sverhuroch +
                                "',osob_slozh_usl = '" + tfOsoboslozhUsl.getText() + "',dop_za_zon_obls = '" + doplZaRashZon +
                                "',dop_za_eko = '" + doplZaEkolog + "',tarif = '" + tarif + "',vysluga_let = '" + vyslugaLet +
                                "',nadb_kassif = '" + nadbZaKlass + "',tyazh_usk_trud = '" + tyazhUslTrud + "',ezh_prem_rab = '" + ezhPremRab +
                                "',nad_zvan_poch_zhez = '" + tfPochZhelez.getText() + "',vsego_nach = '" + vsegoNach +
                                "',obyaz_pens = '" + obyazPoPens + "',ind_nalog = '" + indPodNal + "',den_sred_v_kasse = '" + tfDenSredVKas.getText() +
                                "',den_sred_bank_shet = '" + tfDenSrNaTekB.getText() + "',dolzh_zp = '" + tfOsnSumZadZP.getText() +
                                "',dolzh_po_prochim ='" + tfOsSumZadPoProch.getText() + "',dolzh_po_obuch = '" + tfOsSumPoObuch.getText() +
                                "',osn_sum_zadolzh = '" + tfOsSumZad.getText() + "',osn_sum_komand = '" + tfOsSumKomand.getText() +
                                "',kratk_kred_zadolzh = '" + tfKratkKredit.getText() + "',sum_aliment = '" + tfSumAlim.getText() +
                                "',sum_ud_s_rab = '" + tfSumUdPoDok.getText() + "',proch_kred_dolg = '" + tfProchKratk.getText() +
                                "',vsego_ud = '" + vsegoUd + "',sum_k_vydache = '" + sumKVyd + "',ch_co = '" + chco +
                                "',sum_soc_nal = '" + sumSoc + "',sum_soc_och = '" + sumSocOtch + "',nalog_vychety = '" + tfNalVych.getText() +
                                "',lgota = '" + tfLgota.getText() +
                                "' WHERE fio = '" + AccountantCreatedForm.table.getValueAt(AccountantCreatedForm.table.getSelectedRow(), 1) + "'";
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
                                        JOptionPane.showMessageDialog(null, "Запись изменена");
                                    } catch (Exception qe) {
                                        JOptionPane.showMessageDialog(null, "Ошибка добавления изменений");
                                    }
                                    dbc.getConnection().close();

                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                } finally {
                                    try {
                                        dbc.getConnection().close();
                                        st.close();
                                    } catch (SQLException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            }
                        }).start();
                    }catch (NumberFormatException ex) {JOptionPane.showMessageDialog(null,"Введены неверные данные");}
                }
            }
        }
        ActionListener al = new ButtonListener();
        bCreate.addActionListener(al);
        bChange.addActionListener(al);

    }

}

