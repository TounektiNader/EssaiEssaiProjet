/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Services.ServiceRealTime;
import java.util.Date;
import java.util.TimerTask;

/**
 *
 * @author hedih
 */

  public class testlive extends TimerTask {

@Override
public void run() {
ServiceRealTime t= new ServiceRealTime();
System.out.println("debut"+t.AfficherAction(14));
try {

Thread.sleep(45000);

} catch (InterruptedException e) {

e.printStackTrace();

}

System.out.println("fin"+t.AfficherAction(14));

}
}
