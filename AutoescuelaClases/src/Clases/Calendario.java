/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import javax.swing.JDialog;
import org.freixas.jcalendar.DateEvent;
import org.freixas.jcalendar.DateListener;
import org.freixas.jcalendar.JCalendar;

/**
 *
 * @author alumno
 */
public class Calendario extends JDialog implements DateListener {

    private String dia;
    private String mes;
    private String año;
    JCalendar calendario = new JCalendar();

    @Override
    public void dateChanged(DateEvent de) {
        if (de.getSelectedDate() != null) {
            año = Integer.toString(calendario.getCalendar().get(java.util.Calendar.YEAR));
            mes = Integer.toString(calendario.getCalendar().get(java.util.Calendar.MONTH) + 1);
            dia = Integer.toString(calendario.getCalendar().get(java.util.Calendar.DATE));
            if (mes.length() == 1) {
                mes = "0" + Integer.toString(calendario.getCalendar().get(java.util.Calendar.MONTH) + 1);

            }
            if (dia.length() == 1) {
                dia = "0" + Integer.toString(calendario.getCalendar().get(java.util.Calendar.DATE));
            }
        }
    }
}
