package com.universe.lab8;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.swing.JLabel;

public interface RemoteService extends Remote {

  String showNaturalValue(Integer[] number) throws RemoteException;

  JLabel setText() throws RemoteException;


}