package com.karpenko;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 * Created by Олег on 29.11.2016.
 */
public class EconomistVedView extends JFrame {
    public static JTable table = new JTable();
    final static Vector column = new Vector();
    public static JButton bSearch = new JButton("Поиск");
    EconomistVedView() {
        super();
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setVisible(false);

        table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // для выбора только одной записи
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bSearch, BorderLayout.SOUTH);
        column.add("Табельный №");
        column.add("ФИО");
        column.add("Должность");
        column.add("Разряд");
        column.add("Персонал");
        column.add("Оклад/Тариф");
        column.add("Отработано дней");
        column.add("Отработано часов");
        column.add("Норма часов");
        column.add("сумма за час");
        column.add("% выслуги");
        column.add("% классность");
        column.add("% премии");
        column.add("отопление");
        column.add("особосложные");
        column.add("РЗО%");
        column.add("Вредность часы");
        column.add("Вредность %");
        column.add("Сверхурочные");
        column.add("Ночные");
        column.add("Экология");
        column.add("Выходные, праздничные");
        column.add("Тариф за проезд");
        column.add("Персональные доплаты");
        column.add("Оклад");
        column.add("Оплата временной нетрудоспособности");
        column.add("Отпускные");
        column.add("Компенсация за неиспользованный отпуск");
        column.add("Разъездной характер труда");
        column.add("Ночные");
        column.add("Праздничные и выходные");
        column.add("Сверхурочные");
        column.add("Особые сложные условия труда");
        column.add("Доплата за расширение зон обслуживания");
        column.add("Доплата за экологию (проживание в местах экологического бедствия)");
        column.add("Тариф");
        column.add("Выслуга лет");
        column.add("Надбавка за классность, квалификацию");
        column.add("Тяжелые (вредные) условия труда");
        column.add("Ежемесячная премия рабочим");
        column.add("Надбавка за звание 'Почетный железнодорожник'");
        column.add("Всего начислено");
        column.add("Обязательства по пенсионным отчислениям (32.2.1.01)");
        column.add("Индивидуальный подоходный налог (31.2.1.01)");
        column.add("Денежные средства в кассе в тенге (1010)");
        column.add("Денежные средства на текущих банковских счетах в тенге (1030)");
        column.add("Основная сумма задолженности по заработной плате (3350)");
        column.add("Основная сумма задолженности по прочим, в тенге (1210)");
        column.add("Основная сумма задолженности по обучению , в тенге (1210)");
        column.add("Основная сумма задолженности (12511)");
        column.add("Основная сумма задолженности по заработной плате, командировочные (12512)");
        column.add("Краткосрочная кредиторская задолженность филиалам и структурным подразделениям (3340)");
        column.add("Суммы алиментов, удержанные с работников (33.9.1.01.2)");
        column.add("Суммы  удержанные с работников по исполнительным документам (33.9.1.01.1)");
        column.add("Прочая краткосрочная кредиторская задолженность с контрагентами (33.9.1.01.5)");
        column.add("Всего удержано");
        column.add("Сумма к выдаче");
        column.add("СН+СО");
        column.add("Сумма социального налога");
        column.add("Сумма соц. отчислений");
        column.add("Налоговые вычеты");
        column.add("Льгота");

    }
}

