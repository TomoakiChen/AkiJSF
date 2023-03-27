/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jsf.pf;

import org.primefaces.component.tabview.Tab;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author tomoaki
 */
public class PFHelper {

    public static Tab obtainCurrentTabView(TabChangeEvent event) {
        return event.getTab();
    }

    public static String obtainCurrentTabViewId(TabChangeEvent event) {
        return obtainCurrentTabView(event).getId();
    }
}
