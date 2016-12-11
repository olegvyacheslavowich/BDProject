package com.karpenko;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Vector;

/**
 * Created by Олег on 31.10.2016.
 */
public class EconomistDBDataToExcel {       //класс отвечает за извлечение данных из БД в массив
    public String[][] arr = null;
    public int numRow;
   public int numColumns;

    EconomistDBDataToExcel(){
        DBConnect dbc = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            dbc = new DBConnect();
            st = dbc.getConnection().createStatement();
            rs = st.executeQuery("SELECT * FROM " + EconomistMainForm.strToIntMonth(EconomistMainForm.cbMonth) + " ORDER BY " +
                    EconomistMainForm.strToIntMonth(EconomistMainForm.cbMonth) + ".fio");
            // тут извлекаем количество строк
            rs.last(); //переходим к последней строке
            numRow = rs.getRow();  //получаем номер последнего элемента
            rs.beforeFirst(); //возвращаемся к первой строке
            numColumns = rs.getMetaData().getColumnCount(); // считаем число столбцов
            arr = new String[numRow][numColumns+1];   //объявляем массив
            while (rs.next()) { //пока переходим к след.строке выполнить:
                // вытаскиваем  результат запроса из ResultSet в массив
                for (int i = 0; i < numRow; i++) {
                    arr[i][0] = String.valueOf(i+1);
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
    public  void writeIntoExcel(String file) throws FileNotFoundException, IOException {

        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Табель за " + EconomistMainForm.cbMonth.getSelectedItem() + " " + EconomistMainForm.cbYear.getSelectedItem());

        Font font = book.createFont();
        font.setFontHeightInPoints((short)12);
        font.setFontName("Arial");

        Row row0 = sheet.createRow(0); row0.setHeight((short) 2000);
        Row row1 = sheet.createRow(1); row1.setHeight((short) 500);
        Row row2 = sheet.createRow(2); row2.setHeight((short) 2000);
        Row row3 = sheet.createRow(3); row3.setHeight((short) 1000);

        for (int i = 0; i < numRow; i ++) {
            Row row = sheet.createRow(i+4);
            for (int j = 0; j < numColumns+1; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(arr[i][j]);
                sheet.autoSizeColumn(j);
            }
        }
        CellRangeAddress regionNum = new CellRangeAddress(0,3,0,0);
        sheet.addMergedRegion(regionNum);
        CellRangeAddress regionFio = new CellRangeAddress(0,3,1,1);
        sheet.addMergedRegion(regionFio);
        CellRangeAddress regionTabNum = new CellRangeAddress(0,3,2,2);
        sheet.addMergedRegion(regionTabNum);
        CellRangeAddress regionDolzh = new CellRangeAddress(0,3,3,3);
        sheet.addMergedRegion(regionDolzh);
        CellRangeAddress regionRazr = new CellRangeAddress(0,3,4,4);
        sheet.addMergedRegion(regionRazr);
        CellRangeAddress regionMvz = new CellRangeAddress(0,3,5,5);
        sheet.addMergedRegion(regionMvz);
        CellRangeAddress regionVidRab = new CellRangeAddress(0,3,6,6);
        sheet.addMergedRegion(regionVidRab);
        CellRangeAddress mouthName = new CellRangeAddress(0,2,7,37);
        sheet.addMergedRegion(mouthName);
        CellRangeAddress dniYav = new CellRangeAddress(0,2,38,38);
        sheet.addMergedRegion(dniYav);
        CellRangeAddress neYav = new CellRangeAddress(0,1,39,45);
        sheet.addMergedRegion(neYav);
        CellRangeAddress otp = new CellRangeAddress(2,3,39,39);
        sheet.addMergedRegion(otp);
        CellRangeAddress otpPoUhZaReb = new CellRangeAddress(2,3,40,40);
        sheet.addMergedRegion(otpPoUhZaReb);
        CellRangeAddress listVrem = new CellRangeAddress(2,3,41,41);
        sheet.addMergedRegion(listVrem);
        CellRangeAddress razrZak = new CellRangeAddress(2,3,42,42);
        sheet.addMergedRegion(razrZak);
        CellRangeAddress sRazrAdmin = new CellRangeAddress(2,3,43,43);
        sheet.addMergedRegion(sRazrAdmin);
        CellRangeAddress ucheba = new CellRangeAddress(2,3,44,44);
        sheet.addMergedRegion(ucheba);
        CellRangeAddress progul = new CellRangeAddress(2,3,45,45);
        sheet.addMergedRegion(progul);
        CellRangeAddress komand = new CellRangeAddress(0,3,46,46);
        sheet.addMergedRegion(komand);
        CellRangeAddress vyhodPrazd = new CellRangeAddress(0,3,47,47);
        sheet.addMergedRegion(vyhodPrazd);
        CellRangeAddress vsegoDney = new CellRangeAddress(0,3,48,48);
        sheet.addMergedRegion(vsegoDney);
        CellRangeAddress vsegoChas = new CellRangeAddress(0,3,49,49);
        sheet.addMergedRegion(vsegoChas);
        CellRangeAddress izNih = new CellRangeAddress(0,0,50,55);
        sheet.addMergedRegion(izNih);
        CellRangeAddress otrabChasFakt = new CellRangeAddress(1,3,50,50);
        sheet.addMergedRegion(otrabChasFakt);
        CellRangeAddress izNih1 = new CellRangeAddress(1,1,51,54);
        sheet.addMergedRegion(izNih1);
        CellRangeAddress sverhur = new CellRangeAddress(2,3,51,51);
        sheet.addMergedRegion(sverhur);
        CellRangeAddress vyhIPrazd = new CellRangeAddress(2,2,52,53);
        sheet.addMergedRegion(vyhIPrazd);
        CellRangeAddress doplata = new CellRangeAddress(3,3,52,53);
        sheet.addMergedRegion(doplata);
        CellRangeAddress noch = new CellRangeAddress(2,3,54,54);
        sheet.addMergedRegion(noch);
        CellRangeAddress vPuti = new CellRangeAddress(1,3,55,55);
        sheet.addMergedRegion(vPuti);
        CellRangeAddress doplataZaVaht = new CellRangeAddress(0,3,56,56);
        sheet.addMergedRegion(doplataZaVaht);
        CellRangeAddress zakaz = new CellRangeAddress(0,3,57,57);
        sheet.addMergedRegion(zakaz);

        /////////////////////////////////////
        //создаем стиль поворота текста на 90 гр.
        CellStyle textRotationAndAlign = book.createCellStyle(); //создаем новый стиль
        textRotationAndAlign.setRotation((short) 90);
        textRotationAndAlign.setAlignment(CellStyle.ALIGN_CENTER);
        textRotationAndAlign.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        textRotationAndAlign.setFont(font); // изменяем размер текста до 16

        CellStyle vAllign = book.createCellStyle();
        vAllign.setAlignment(CellStyle.ALIGN_CENTER);
        vAllign.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        vAllign.setFont(font);


        /////////////////////////////////////////////
        //заполнение строки 0
        Cell num = row0.createCell(0); // создаем нулевой столбец
        num.setCellValue("№ п/п  /  реттік №");       //добавляем значение в столбец
        num.setCellStyle(textRotationAndAlign);  //применяем стиль виртикального выравнивания

        Cell fio = row0.createCell(1);
        fio.setCellValue("Ф И О / Ф.А.Ә");
        fio.setCellStyle(vAllign);

        Cell tabNum = row0.createCell(2);
        tabNum.setCellValue("Табель №");
        tabNum.setCellStyle(textRotationAndAlign);

        Cell dolzh = row0.createCell(3);
        dolzh.setCellValue("Должность /   лауазым");
        dolzh.setCellStyle(vAllign);

        Cell razr = row0.createCell(4);
        razr.setCellValue("Hазряд / дәреже");
        razr.setCellStyle(textRotationAndAlign);

        Cell mvz = row0.createCell(5);
        mvz.setCellValue("МВЗ");
        mvz.setCellStyle(textRotationAndAlign);

        Cell vidRab = row0.createCell(6);
        vidRab.setCellValue("Вид работ");
        vidRab.setCellStyle(textRotationAndAlign);

        Cell mName = row0.createCell(7);
        mName.setCellValue(EconomistMainForm.cbMonth.getSelectedItem() + " " + EconomistMainForm.cbYear.getSelectedItem());
        mName.setCellStyle(vAllign);

        Cell dniYavCell = row0.createCell(38);
        dniYavCell.setCellValue("Келген күндері/Дни явок");
        dniYavCell.setCellStyle(textRotationAndAlign);

        Cell neYavCell = row0.createCell(39);
        neYavCell.setCellValue("Келмеген күндері (адам күн)/Неявки (чел дней)");
        neYavCell.setCellStyle(vAllign);

        Cell komandCell = row0.createCell(46);
        komandCell.setCellValue("Іссапар  /командировка (К)");
        komandCell.setCellStyle(textRotationAndAlign);

        Cell vyhodPrazdCell = row0.createCell(47);
        vyhodPrazdCell.setCellValue("Демалыс және мереке күндері  /Выходные и праздничные дни (В)");
        vyhodPrazdCell.setCellStyle(textRotationAndAlign);

        Cell vsegoDneyCell = row0.createCell(48);
        vsegoDneyCell.setCellValue("Барлық күнтізбелік күндер/ Всего календарных дней");
        vsegoDneyCell.setCellStyle(textRotationAndAlign);

        Cell vsegoChasCell  = row0.createCell(49);
        vsegoChasCell.setCellValue("БАРЛЫҒЫ / Всего часов");
        vsegoChasCell.setCellStyle(textRotationAndAlign);

        Cell izNihCell  = row0.createCell(50);
        izNihCell.setCellValue("Оның ішнде     /     Из них");
        izNihCell.setCellStyle(vAllign);

        Cell doplataZaVahtCell = row0.createCell(56);
        doplataZaVahtCell.setCellValue("Доплата за вахтовый метод");
        doplataZaVahtCell.setCellStyle(textRotationAndAlign);

        Cell zakazCell = row0.createCell(57);
        zakazCell.setCellValue("Заказ");
        zakazCell.setCellStyle(textRotationAndAlign);

///////////////////////////////////
        //заполнение строки 1
        Cell fakt = row1.createCell(50);
        fakt.setCellValue("Нақты жұмыс атқарған уақыты/Отработано часов фактически");
        fakt.setCellStyle(textRotationAndAlign);

        Cell izNih1Cell = row1.createCell(51);
        izNih1Cell.setCellValue("Оның ішнде / Из них");
        izNih1Cell.setCellStyle(vAllign);

        Cell vremVPutiCell = row1.createCell(55);
        vremVPutiCell.setCellValue("Жолға кеткен уақыты/Время в пути");
        vremVPutiCell.setCellStyle(textRotationAndAlign);

        /////////////////////
        //заполнение строки 2
        Cell otpCell = row2.createCell(39);
        otpCell.setCellValue("Кезекті еңбек демалыс / Очередной трудовой отпуск (О)");
        otpCell.setCellStyle(textRotationAndAlign);

        Cell otpPoUhZaRebCell = row2.createCell(40);
        otpPoUhZaRebCell.setCellValue("Бала күтуіне байланысты демалыс / Отпуск по уходу за ребёнком (Р)");
        otpPoUhZaRebCell.setCellStyle(textRotationAndAlign);

        Cell listVremCell = row2.createCell(41);
        listVremCell.setCellValue("уақытша еңбекке жарамсыздық қағазы /  лист врем. нетрудоспособности (Б)");
        listVremCell.setCellStyle(textRotationAndAlign);

        Cell razrZakCell = row2.createCell(42);
        razrZakCell.setCellValue("Заң бойынша келмеген уақыты / разрешенные законом (Г,Д)");
        razrZakCell.setCellStyle(textRotationAndAlign);

        Cell sRazrAdminCell = row2.createCell(43);
        sRazrAdminCell.setCellValue("Әкімшіліктің рұксатымен  келмеген уақыты/С разрешния администрации (А)");
        sRazrAdminCell.setCellStyle(textRotationAndAlign);

        Cell uchCell = row2.createCell(44);
        uchCell.setCellValue("Оқу / Учёба (У)");
        uchCell.setCellStyle(textRotationAndAlign);

        Cell progulCell = row2.createCell(45);
        progulCell.setCellValue("Себепсіз жағдайлармен жұмысқа шықпаған уақыты / Прогулы (П)");
        progulCell.setCellStyle(textRotationAndAlign);

        Cell sverhurCell = row2.createCell(51);
        sverhurCell.setCellValue("Жұмыс мерзімінен артық сағаты  /   Свехурочные");
        sverhurCell.setCellStyle(textRotationAndAlign);

        Cell vyhIPrazdCell = row2.createCell(52);
        vyhIPrazdCell.setCellValue("Демалыс және мереке / Выходные и праздничные");
        vyhIPrazdCell.setCellStyle(vAllign);

        Cell nochCell = row2.createCell(54);
        nochCell.setCellValue("Түнгі сағаттар/   Ночные");
        nochCell.setCellStyle(textRotationAndAlign);
        /////////////////////////////
//заполнение строки 3
        for (int i = 7; i < 38; i++) {
            Cell mouthsNum = row3.createCell(i);
            mouthsNum.setCellValue(i-6);
            mouthsNum.setCellStyle(vAllign);
            sheet.autoSizeColumn(i); //задаем размер
        }
        Cell faktRab = row3.createCell(38);
        faktRab.setCellValue("Нақтылы жұмыс уақыты  / Фактической работы");
        faktRab.setCellStyle(textRotationAndAlign);

        Cell doplataCell = row3.createCell(52);
        doplataCell.setCellValue("Доплата");
        doplataCell.setCellStyle(vAllign);
/////////////
        // Записываем всё в файл
        book.write(new FileOutputStream(file + ".xlsx"));
        book.close();
    }
}
