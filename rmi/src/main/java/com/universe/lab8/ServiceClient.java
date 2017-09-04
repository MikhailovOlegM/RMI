package com.universe.lab8;

import java.awt.GridBagLayout;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ServiceClient {

  public static void main(String... args) throws Exception {
    Registry registry = LocateRegistry
        .getRegistry("localhost", 2099); //подключаемся на порт сервера в регистре
    RemoteService service = (RemoteService) registry.lookup("sample/Service");
    Integer[] number = {2, 7, 12, 4, 3, 33, 53, 51, 181};


    JFrame frame = new JFrame("My form");

    frame.setSize(300, 200);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setLayout(new GridBagLayout());

    JLabel textArea = service.setText();

    textArea.setHorizontalTextPosition(JLabel.CENTER);
    textArea.setVerticalTextPosition(JLabel.BOTTOM);

    System.out.println(service.showNaturalValue(number));

    textArea = service.setText();
    frame.add(textArea);
    frame.setVisible(true);
  }

}
