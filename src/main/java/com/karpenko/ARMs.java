package com.karpenko;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;

/**
 * Created by Олег on 13.10.2016.
 */
public class ARMs extends JFrame {
    public static JButton bAccountant;   //баттоны для бухгалтера и экономиста
    public static JButton bEconomist;
    public static JTextField tfIp;
   static String serverIp = null;
    public ARMs(){
        super("Выберите должность");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,200);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4,1));
        ImageIcon icon = new ImageIcon("home.png");
        setIconImage(icon.getImage());

        // создаем панель меню
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Меню");      // создаем меню
        JMenuItem appItem = new JMenuItem("О приложении");   // создаем строчку "подключение к серверу"
        JMenuItem razrabItem = new JMenuItem("О разработчике");
        optionsMenu.add(appItem); optionsMenu.add(razrabItem);
        menuBar.add(optionsMenu);   // добавляем пункт в меню
        setJMenuBar(menuBar);
         // добавляем панель меню на форму*/

        appItem.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e)  {
                JOptionPane.showMessageDialog(null,
                        "АИС 'Wage' - это прогаммное обеспечение, " +
                                "созданное для наилучшего взаимодействия экономического и бухгалтерского отделов",
                        "О приложении",
                        JOptionPane.WARNING_MESSAGE);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        razrabItem.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Проект разработал и выпонил:\n студент группы ВТ-13-3\nКарпенко Олег" +
                                "\n\n\n@Все права защищены",
                        "О разработчике",
                        JOptionPane.WARNING_MESSAGE);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });




        final EconomistMainForm economistMainForm = new EconomistMainForm();
        final AccountantMainForm accountantMainForm = new AccountantMainForm();
        // строка ввода имени компьютера на котором утсановлен сервер
        tfIp = new JTextField();
        tfIp.setHorizontalAlignment(JTextField.CENTER);

        bEconomist = new JButton("Экономист");
        bAccountant = new JButton("Бухгалтер");

        final JLabel lIp = new JLabel("Введите имя компьютера и выберите должность");
        lIp.setHorizontalAlignment(JLabel.CENTER);
        add(lIp);
        add(tfIp);
        add(bEconomist);
        add(bAccountant);

        economistMainForm.bClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
                economistMainForm.setVisible(false);
            }
        });
        accountantMainForm.bClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
                accountantMainForm.setVisible(false);
            }
        });
        //слушатель для всех окон
       class AllWindowsListener implements WindowListener{
            public void windowOpened(WindowEvent e) {
                // если открыли окно АРМов то проверить на существование файл с именем сервера
                // если такого нет, то создать, если есть то прочесит данные и вывести в строку
                if (e.getSource() == ARMs.this) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (new File("serverNameFile.snf").exists()) {
                                    BufferedReader reader = new BufferedReader(new FileReader("serverNameFile.snf"));
                                    String line;
                                    while ((line = reader.readLine()) != null) {
                                        tfIp.setText(line);
                                    }
                                    reader.close();
                                } else {
                                    FileWriter fw = new FileWriter("serverNameFile.snf", false);
                                }
                            } catch (FileNotFoundException out) {
                                JOptionPane.showMessageDialog(null, "Файл не найден");
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
            @Override
            public void windowClosing(WindowEvent e) {
                // закрытие для окна экономиста
                if (e.getSource() == accountantMainForm){
                    setVisible(true);
                    accountantMainForm.setVisible(false);
                }
                //закрытие для окна бухгалтера
                if (e.getSource() == economistMainForm){
                    setVisible(true);
                    economistMainForm.setVisible(false);
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

        class AllActionListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                    DBConnect dbc = null;
                    FileWriter fw = null;
                    // если поле ввода имени компьютера заполнено, то:
                    if (tfIp.getText().trim().length() > 0) {
                        try {
                            fw = new FileWriter("serverNameFile.snf",false);
                            //присваиваем объекту localIP полученный имя компьютера/IP по имени компьютера
                            InetAddress localIP = InetAddress.getByName(tfIp.getText());
                            //вытаскиваем IP из полученных данных
                            serverIp = (String) localIP.toString().substring(tfIp.getText().length() + 1, localIP.toString().length());
                            fw.write(tfIp.getText()); // записываем в файл имя сервера
                            //проверяем подключиться ли клиент к серверу по написанному нами имени компьютера сервера
                            dbc = new DBConnect();
                            dbc.getConnection();
                            dbc.getConnection().close();
                            if (e.getSource() == bEconomist )
                            economistMainForm.setVisible(true);
                            if (e.getSource() == bAccountant)
                                accountantMainForm.setVisible(true);
                            setVisible(false);
                            fw.close();
                        } catch (UnknownHostException ex) {JOptionPane.showMessageDialog(null, "Не удалось установить подключение с компьютером '" + tfIp.getText() + "'");}
                        catch (IOException out) {JOptionPane.showMessageDialog(null,"Файл не найден");
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        finally {
                            try {
                                dbc.getConnection().close();
                                fw.close();
                            }  catch (IOException e2) {e2.printStackTrace();}
                            catch (SQLException e1) {e1.printStackTrace();}
                        }
                    } else JOptionPane.showMessageDialog(null,"Введите имя компьютера");
                }
            }

        // ссылаемся объектом AllWindowsListener на класс WindowListener
        WindowListener wl = new AllWindowsListener();
        economistMainForm.addWindowListener(wl); //добавляем слушатель для формы экономиста
        accountantMainForm.addWindowListener(wl);//добавляем слушатель для формы бухгалтера
        this.addWindowListener(wl); //добавляем слушатель для формы выбора АРМов
        setVisible(true);
        //ссылаемся объектом AllActionListener на класс ActionListener
        ActionListener al = new AllActionListener();
        bEconomist.addActionListener(al);
        bAccountant.addActionListener(al);
    }




}
