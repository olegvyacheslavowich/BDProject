package com.karpenko;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

/**
 * Created by Олег on 20.11.2016.
 */
public class AccountantBDToExcel {
    public String[][] arr = null;
    public int numRow;
    public int numColumns;

    public void getToArray() {
        DBConnect dbc = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            dbc = new DBConnect();
            st = dbc.getConnection().createStatement();
            rs = st.executeQuery("SELECT * FROM ved_" + AccountantMainForm.strToIntMonth(AccountantMainForm.cbMonth) + " ORDER BY ved_" +
                    AccountantMainForm.strToIntMonth(AccountantMainForm.cbMonth) + ".fio");
            // тут извлекаем количество строк
            rs.last(); //переходим к последней строке
            numRow = rs.getRow();  //получаем номер последнего элемента
            rs.beforeFirst(); //возвращаемся к первой строке
            numColumns = rs.getMetaData().getColumnCount(); // считаем число столбцов
            arr = new String[numRow][numColumns+1];   //объявляем массив
            while (rs.next()) { //пока переходим к след.строке выполнить:
                // вытаскиваем  результат запроса из ResultSet в массив
                for (int i = 0; i < numRow; i++) {
                    arr[i][0] = String.valueOf(i + 1);
                    for (int j = 1; j <= numColumns; j++) {
                        arr[i][j] = rs.getString(j);
                    }
                    rs.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                dbc.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void writeIntoExcel(String file) throws FileNotFoundException, IOException {

        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Ведомость " + AccountantMainForm.cbMonth.getSelectedItem() +
                " " + AccountantMainForm.cbYear.getSelectedItem());
        Font font = book.createFont();
        font.setFontHeightInPoints((short)10);
        font.setFontName("Arial");
        font.setBold(true);

        Row row0 = sheet.createRow(0); row0.setHeight((short) 250);
        CellRangeAddress regionTOO = new CellRangeAddress(0,0,0,2);
        sheet.addMergedRegion(regionTOO);
        CellRangeAddress regionVedomost = new CellRangeAddress(1,1,0,2);
        sheet.addMergedRegion(regionVedomost);
        CellRangeAddress regionMonth = new CellRangeAddress(2,2,0,2);
        sheet.addMergedRegion(regionMonth);
        CellRangeAddress regionKarDir = new CellRangeAddress(3,3,0,2);
        sheet.addMergedRegion(regionKarDir);

        Row row1 = sheet.createRow(1); row1.setHeight((short) 250);
        Row row2 = sheet.createRow(2); row2.setHeight((short) 250);
        Row row3 = sheet.createRow(3); row3.setHeight((short) 250);
        Row row4 = sheet.createRow(4); row4.setHeight((short) 1000);
        Row row5 = sheet.createRow(5); row5.setHeight((short) 1000);

        getToArray(); // записать в массив данные из таблицы

        for (int i = 0; i < numRow; i ++) {
            Row row = sheet.createRow(i+6);
            for (int j = 0; j < numColumns+1; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(arr[i][j]);
                sheet.autoSizeColumn(j);
            }
        }
        //объединение
        CellRangeAddress regionNum = new CellRangeAddress(4,5,0,0);
        sheet.addMergedRegion(regionNum);
        CellRangeAddress regionTab = new CellRangeAddress(4,5,1,1);
        sheet.addMergedRegion(regionTab);
        CellRangeAddress regionFIO = new CellRangeAddress(4,5,2,2);
        sheet.addMergedRegion(regionFIO);
        CellRangeAddress regionDolzh = new CellRangeAddress(4,5,3,3);
        sheet.addMergedRegion(regionDolzh);
        CellRangeAddress regionRazr = new CellRangeAddress(4,5,4,4);
        sheet.addMergedRegion(regionRazr);
        CellRangeAddress regionPers = new CellRangeAddress(4,5,5,5);
        sheet.addMergedRegion(regionPers);
        CellRangeAddress regionOklTarif = new CellRangeAddress(4,5,6,6);
        sheet.addMergedRegion(regionOklTarif);
        CellRangeAddress regionOtrab = new CellRangeAddress(4,4,7,8);
        sheet.addMergedRegion(regionOtrab);
        CellRangeAddress regionNormChas = new CellRangeAddress(4,5,9,9);
        sheet.addMergedRegion(regionNormChas);
        CellRangeAddress regionSumZaChas = new CellRangeAddress(4,5,10,10);
        sheet.addMergedRegion(regionSumZaChas);
        CellRangeAddress procVysl = new CellRangeAddress(4,5,11,11);
        sheet.addMergedRegion(procVysl);
        CellRangeAddress procKlas = new CellRangeAddress(4,5,12,12);
        sheet.addMergedRegion(procKlas);
        CellRangeAddress procPrem = new CellRangeAddress(4,5,13,13);
        sheet.addMergedRegion(procPrem);
        CellRangeAddress otop = new CellRangeAddress(4,5,14,14);
        sheet.addMergedRegion(otop);
        CellRangeAddress osoboslozh = new CellRangeAddress(4,5,15,15);
        sheet.addMergedRegion(osoboslozh);
        CellRangeAddress rzo = new CellRangeAddress(4,5,16,16);
        sheet.addMergedRegion(rzo);
        CellRangeAddress vred = new CellRangeAddress(4,4,17,18);
        sheet.addMergedRegion(vred);
        for (int i = 19; i < 63; i++) {
            CellRangeAddress kompens = new CellRangeAddress(4, 5, i, i);
            sheet.addMergedRegion(kompens);
        }

        /////////////////////////////////////
        //создаем стиль поворота текста на 90 гр.
        CellStyle textRotationAndAlign = book.createCellStyle(); //создаем новый стиль
        textRotationAndAlign.setRotation((short) 90);
        textRotationAndAlign.setAlignment(CellStyle.ALIGN_CENTER);
        textRotationAndAlign.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        textRotationAndAlign.setFont(font); // изменяем размер текста до 10

        CellStyle vAllign = book.createCellStyle();
        vAllign.setAlignment(CellStyle.ALIGN_CENTER);
        vAllign.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        textRotationAndAlign.setBorderBottom((short)5);
        vAllign.setFont(font);


        /////////////////////////////////////////////
        //заполнение строки 0
        Cell too = row0.createCell(0); // создаем нулевой столбец
        too.setCellValue("ТОО Компания 'Жол жондеуші'");       //добавляем значение в столбец

        Cell vedomost = row1.createCell(0);
        vedomost.setCellValue("Ведомость начисления заработной платы");

        Cell month = row2.createCell(0);
        month.setCellValue(AccountantMainForm.cbMonth.getSelectedItem()+ " " + AccountantMainForm.cbYear.getSelectedItem());

        Cell karder = row3.createCell(0);
        karder.setCellValue("Карагандинская дирекция по ремонту пути");

        Cell num = row4.createCell(0);
        num.setCellValue("№пп");
        num.setCellStyle(textRotationAndAlign);

        Cell tab = row4.createCell(1);
        tab.setCellValue("Табельный номер");
        tab.setCellStyle(textRotationAndAlign);

        Cell fio = row4.createCell(2);
        fio.setCellValue("Фамилия Имя Отчество");
        fio.setCellStyle(textRotationAndAlign);

        Cell dolzh = row4.createCell(3);
        dolzh.setCellValue("Должность");
        dolzh.setCellStyle(textRotationAndAlign);

        Cell razr = row4.createCell(4);
        razr.setCellValue("Разряд");
        razr.setCellStyle(textRotationAndAlign);

        Cell pers = row4.createCell(5);
        pers.setCellValue("Персонал");
        pers.setCellStyle(textRotationAndAlign);

        Cell okl = row4.createCell(6);
        okl.setCellValue("Оклад/Тариф");
        okl.setCellStyle(textRotationAndAlign);

        Cell otr = row4.createCell(7);
        otr.setCellValue("Отработано");
        otr.setCellStyle(textRotationAndAlign);

        Cell dn = row5.createCell(7);
        dn.setCellValue("дней");
        dn.setCellStyle(textRotationAndAlign);

        Cell ch = row5.createCell(8);
        ch.setCellValue("часов");
        ch.setCellStyle(textRotationAndAlign);

        Cell norm  = row4.createCell(9);
        norm.setCellValue("Норма часов");
        norm.setCellStyle(textRotationAndAlign);

        Cell sumzachas = row4.createCell(10);
        sumzachas.setCellValue("Сумма за час");
        sumzachas.setCellStyle(textRotationAndAlign);

        Cell provysl = row4.createCell(11);
        provysl.setCellValue("% выслуги");
        provysl.setCellStyle(textRotationAndAlign);

        Cell klas = row4.createCell(12);
        klas.setCellValue("% классность");
        klas.setCellStyle(textRotationAndAlign);

        Cell prem = row4.createCell(13);
        prem.setCellValue("% премии");
        prem.setCellStyle(textRotationAndAlign);

        Cell otopl = row4.createCell(14);
        otopl.setCellValue("Отопление");
        otopl.setCellStyle(textRotationAndAlign);

        Cell osob = row4.createCell(15);
        osob.setCellValue("Особосложные");
        osob.setCellStyle(textRotationAndAlign);

        Cell RZO = row4.createCell(16);
        RZO.setCellValue("РЗО %");
        RZO.setCellStyle(textRotationAndAlign);

        Cell vredn = row4.createCell(17);
        vredn.setCellValue("Вредность");
        vredn.setCellStyle(textRotationAndAlign);

        Cell chas = row5.createCell(17);
        chas.setCellValue("Часы");
        chas.setCellStyle(textRotationAndAlign);

        Cell proc = row5.createCell(18);
        proc.setCellValue("%");
        proc.setCellStyle(vAllign);

        Cell sverh = row4.createCell(19);
        sverh.setCellValue("Сверхурочные");
        sverh.setCellStyle(textRotationAndAlign);

        Cell noch = row4.createCell(20);
        noch.setCellValue("Ночные");
        noch.setCellStyle(textRotationAndAlign);

        Cell ek = row4.createCell(21);
        ek.setCellValue("Экология");
        ek.setCellStyle(textRotationAndAlign);

        Cell vyh  = row4.createCell(22);
        vyh.setCellValue("Выходные,празничные");
        vyh.setCellStyle(textRotationAndAlign);

        Cell tar= row4.createCell(23);
        tar.setCellValue("Тариф за проезд");
       tar.setCellStyle(textRotationAndAlign);

        Cell person = row4.createCell(24);
        person.setCellValue("Персональные доплаты");
        person.setCellStyle(textRotationAndAlign);

        Cell okla = row4.createCell(25);
        okla.setCellValue("Оклад");
        okla.setCellStyle(textRotationAndAlign);

        Cell netr = row4.createCell(26);
        netr.setCellValue("Оплата временной нетрудоспособности");
        netr.setCellStyle(textRotationAndAlign);

        Cell otp = row4.createCell(27);
        otp.setCellValue("Отпускные");
        otp.setCellStyle(textRotationAndAlign);

        Cell komp = row4.createCell(28);
        komp.setCellValue("Компенсация за неиспользованный отпуск");
        komp.setCellStyle(textRotationAndAlign);

        Cell raz = row4.createCell(29);
        raz.setCellValue("Разъездной характер труда");
        raz.setCellStyle(textRotationAndAlign);

        Cell nochn = row4.createCell(30);
        nochn.setCellValue("Ночные");
        nochn.setCellStyle(textRotationAndAlign);

        Cell pr = row4.createCell(31);
        pr.setCellValue("Праздничные и выходные");
        pr.setCellStyle(textRotationAndAlign);

        Cell sver = row4.createCell(32);
        sver.setCellValue("Сверхурочные");
        sver.setCellStyle(textRotationAndAlign);

        Cell oso = row4.createCell(33);
        oso.setCellValue("Особые сложные условия труда");
        oso.setCellStyle(textRotationAndAlign);

        Cell dop = row4.createCell(34);
        dop.setCellValue("Доплата за расширение зон обслуживания");
        dop.setCellStyle(textRotationAndAlign);

        Cell dopek  = row4.createCell(35);
        dopek.setCellValue("Доплата за экологию (проживание в местах экологического бедствия)");
        dopek.setCellStyle(textRotationAndAlign);

        Cell tarif = row4.createCell(36);
        tarif.setCellValue("Тариф");
        tarif.setCellStyle(textRotationAndAlign);

        Cell vys = row4.createCell(37);
        vys.setCellValue("Выслуга лет");
        vys.setCellStyle(textRotationAndAlign);

        Cell nadbav = row4.createCell(38);
        nadbav.setCellValue("Надбавка за классность, квалификацию");
        nadbav.setCellStyle(textRotationAndAlign);

        Cell tzh = row4.createCell(39);
        tzh.setCellValue("Тяжелые (вредные) условия труда");
        tzh.setCellStyle(textRotationAndAlign);

        Cell ezh = row4.createCell(40);
        ezh.setCellValue("Ежемесячная премия рабочим");
        ezh.setCellStyle(textRotationAndAlign);

        Cell poch = row4.createCell(41);
        poch.setCellValue("Надбавка за звание \"Почетный железнодорожник\"");
        poch.setCellStyle(textRotationAndAlign);

        Cell vs = row4.createCell(42);
        vs.setCellValue("Всего начислено");
        vs.setCellStyle(textRotationAndAlign);

        Cell pens = row4.createCell(43);
        pens.setCellValue("Обязательства по пенсионным отчислениям (32.2.1.01)");
        pens.setCellStyle(textRotationAndAlign);

        Cell ind = row4.createCell(44);
        ind.setCellValue("Индивидуальный подоходный налог (31.2.1.01)");
        ind.setCellStyle(textRotationAndAlign);

        Cell den = row4.createCell(45);
        den.setCellValue("Денежные средства в кассе в тенге (1010)");
        den.setCellStyle(textRotationAndAlign);

        Cell bank = row4.createCell(46);
        bank.setCellValue("Денежные средства на текущих банковских счетах в тенге (1030)");
        bank.setCellStyle(textRotationAndAlign);

        Cell osn = row4.createCell(47);
        osn.setCellValue("Основная сумма задолженности по заработной плате (3350)");
        osn.setCellStyle(textRotationAndAlign);

        Cell proch = row4.createCell(48);
        proch.setCellValue("Основная сумма задолженности по прочим, в тенге (1210)");
        proch.setCellStyle(textRotationAndAlign);

        Cell obuch  = row4.createCell(49);
        obuch.setCellValue("Основная сумма задолженности по обучению,в тенге (1210)");
        obuch.setCellStyle(textRotationAndAlign);

        Cell sumzad = row4.createCell(50);
        sumzad.setCellValue("Основная сумма задолженности (12511)");
        sumzad.setCellStyle(textRotationAndAlign);

        Cell osnkom = row4.createCell(51);
        osnkom.setCellValue("Основная сумма задолженности по заработной плате, командировочные (12512)");
        osnkom.setCellStyle(textRotationAndAlign);

        Cell podr = row4.createCell(52);
        podr.setCellValue("Краткосрочная кредиторская задолженность филиалам и структурным подразделениям (3340)");
        podr.setCellStyle(textRotationAndAlign);

        Cell sumAl = row4.createCell(53);
        poch.setCellValue("Суммы алиментов, удержанные с работников (33.9.1.01.2)");
        poch.setCellStyle(textRotationAndAlign);

        Cell ud = row4.createCell(54);
        ud.setCellValue("Суммы  удержанные с работников по исполнительным документам (33.9.1.01.1)");
        ud.setCellStyle(textRotationAndAlign);

        Cell kratk = row4.createCell(55);
        kratk.setCellValue("Прочая краткосрочная кредиторская задолженность с контрагентами (33.9.1.01.5)");
        kratk.setCellStyle(textRotationAndAlign);

        Cell vsud = row4.createCell(56);
        vsud.setCellValue("Всего удержано");
        vsud.setCellStyle(textRotationAndAlign);

        Cell sumk = row4.createCell(57);
        sumk.setCellValue("Сумма к выдаче");
        sumk.setCellStyle(textRotationAndAlign);

        Cell СНСО = row4.createCell(58);
        СНСО.setCellValue("СН+СО");
        СНСО.setCellStyle(textRotationAndAlign);

        Cell nal = row4.createCell(59);
        nal.setCellValue("Сумма социального налога");
        nal.setCellStyle(textRotationAndAlign);

        Cell otch = row4.createCell(60);
        otch.setCellValue("Сумма соц. отчислений");
        otch.setCellStyle(textRotationAndAlign);

        Cell nalog  = row4.createCell(61);
        nalog.setCellValue("Налоговые вычеты");
        nalog.setCellStyle(textRotationAndAlign);

        Cell lg = row4.createCell(62);
        lg.setCellValue("Льгота)");
        lg.setCellStyle(textRotationAndAlign);


///////////////////////////////////

        // Записываем всё в файл
        book.write(new FileOutputStream(file + ".xlsx"));
        book.close();
    }
}

