/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores.management;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author home
 */
 
public class log {
   
    
  public boolean checkCredentials(String user, String pass) {
      if(user.equals("admin")&&pass.equals("admin")) {
          return true;
      } else {
          return false;
      }
  }
}
