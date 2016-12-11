package com.karpenko;

import javafx.scene.control.Cell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 * Created by Олег on 11.10.2016.
 */
public class EconomistCreatedForm extends JFrame {
    public static JButton bAdd = new JButton("Добавить");
    public static JButton bChange = new JButton("Изменить");
    public static JButton bDelete = new JButton("Удалить");
    public static JButton bSearch = new JButton("Поиск");
    private JButton bToExcel = new JButton("Конвертировать в Excel");
    public  JButton bClose = new JButton("Закрыть");
    public static JTable table = new JTable();

    EconomistCreatedForm() {
        super("Добавленные работники");
        setLayout(new GridLayout(2,1));
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setExtendedState(Frame.MAXIMIZED_BOTH);
          setVisible(false);

        ImageIcon icon = new ImageIcon("home.png");
        setIconImage(icon.getImage());
          // переопределяем метод isCellEditable для того, чтобы ячейки таблицы были недоступны для изменения на прямую
          table = new JTable(){
              @Override
              public boolean isCellEditable(int row, int column) {
                  return false;
              }
          };
          table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // для выбора только одной записи
          table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

          JPanel buttonPan = new JPanel();
          buttonPan.setLayout(new GridLayout(6,1));
          //добавляем кнопки на панель
          buttonPan.add(bAdd);buttonPan.add(bChange); buttonPan.add(bDelete); buttonPan.add(bSearch); buttonPan.add(bToExcel);
          buttonPan.add(bClose);
        add(new JScrollPane(table));
        add(buttonPan); // добавляем панель на форму
          final Vector column = new Vector();
        column.add("№пп");
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
          column.add("Доплата за вахтовый метод");
          column.add("Заказ");

          // создаем объект слушателя конвертации в эксель
          ActionListener acl = new ToExcelListener();
          // присваиваем слушатель к кнопке
          bToExcel.addActionListener(acl);
          EconomistMainForm.bToExcel.addActionListener(acl);
          //слушатель для кнопки Поиск
          bSearch.addActionListener(new ActionListener() {
              DBConnect dbc = null;
              Statement st = null;
              String query = null;
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
                      query = "SELECT * FROM " + EconomistMainForm.strToIntMonth(EconomistMainForm.cbMonth) +
                              " WHERE fio = " + "'" + name + "'";

                      if (EconomistMainForm.getData(query).isEmpty()) {
                          JOptionPane.showMessageDialog(null, "ФИО " + name + " не найдено");
                      } else {
                          EconomistCreatedForm.table.setModel(new DefaultTableModel(
                                  EconomistMainForm.getData(query), column)); // отобразить данные в таблице
                          // выставление размера столбцов
                          for (int i = 0; i < 56; i++) {
                              EconomistCreatedForm.table.getColumnModel().getColumn(i).setPreferredWidth(200);
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

    }

// слушателья для конвертации в Excel
    class ToExcelListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String selFile = null;
            try {
                EconomistMainForm.getData("SELECT * FROM " + EconomistMainForm.strToIntMonth(EconomistMainForm.cbMonth));
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File("Табель за " + EconomistMainForm.cbMonth.getSelectedItem() + " " + EconomistMainForm.cbYear.getSelectedItem()));
                if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    selFile = String.valueOf(fileChooser.getSelectedFile());
                    new EconomistDBDataToExcel().writeIntoExcel(selFile);
                    JOptionPane.showMessageDialog(null, "Табель конвертирован");
                    int n = JOptionPane.showConfirmDialog(
                            null,
                            "Открыть файл Табель за " + EconomistMainForm.cbMonth.getSelectedItem() + " " + EconomistMainForm.cbYear.getSelectedItem(),
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
            } catch (NullPointerException ex) {JOptionPane.showMessageDialog(null,"Табель не существует");}
            catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "Файл используется");
            }

        }
    }




}