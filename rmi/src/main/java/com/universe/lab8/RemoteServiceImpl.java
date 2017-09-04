package com.universe.lab8;

import java.math.BigInteger;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JLabel;

public class RemoteServiceImpl implements RemoteService {

  public static final String BINDING_NAME = "sample/Service";

  public String numberToFrame;

  public JLabel setText() {
    JLabel text = new JLabel(numberToFrame);
    return text;
  }

  public String showNaturalValue(Integer[] number) {
    StringBuilder result = new StringBuilder();

    int index = 0;
    boolean status = false;
    while (index < number.length) {
      BigInteger bigInteger = BigInteger.valueOf(number[index]);
      boolean probablePrime = bigInteger
          .isProbablePrime((int) Math.log(number[1]));//тест Рабина-Миллера на полноту числа
      if (probablePrime) {
        result.append(bigInteger + ", ");
      }
      index++;

    }

    numberToFrame = result.toString();

    return result.toString();
  }

  public static void main(String... args) throws Exception {
    System.out.print("Starting registry...");
    final Registry registry = LocateRegistry.createRegistry(2099); //регист с ссылками
    System.out.println(" OK");

    final RemoteService service = new RemoteServiceImpl();
    Remote stub = UnicastRemoteObject.exportObject(service, 0);//rmi сервлет

    System.out.print("Binding service...");
    registry.bind(BINDING_NAME, stub);
    System.out.println(" OK");

    while (true) {
      Thread.sleep(Integer.MAX_VALUE);//запущенный поток сервера в ожидание
    }
  }
}