/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author johannesriedmueller
 */
public class myTableCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        WeatherStation ws = (WeatherStation) value;
        JLabel label = new JLabel();
        if (ws == null) {
            return label;
        }
        label.setOpaque(true);
        switch (column) {
            case 0:
                label.setText(ws.getPlace());
                break;
            case 1:
                label.setText(Integer.toString(ws.getSeaLevel()) + "m");
                break;
            case 2:
                label.setText(Double.toString(ws.getTemperature()) + "°C");
                break;
            case 3:
                label.setText(Integer.toString(ws.getHumidity()) + "%");
                break;
        }
        if (ws.getTemperature() > 25 && ws.getHumidity() < 20) {
            label.setBackground(Color.YELLOW);
        }
        else if(ws.getTemperature() < 0 && ws.getHumidity() < 30){
            label.setBackground(Color.BLUE);
            label.setForeground(Color.WHITE);
        }
        else if((ws.getTemperature() >= 0 && ws.getTemperature() <= 25) && ws.getHumidity() > 50){
            label.setBackground(Color.GREEN);
        }
        return label;
    }

}
