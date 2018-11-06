/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author johannesriedmueller
 */
public class WeatherStationBL extends AbstractTableModel {

    private ArrayList<WeatherStation> stations = new ArrayList<>();
    private String[] colNames = {"Place", "Sea Level", "Temperature", "rel. Humidity"};

    public void add(WeatherStation ws) {
        stations.add(ws);
        fireTableRowsInserted(stations.size() - 1, stations.size() - 1);
    }

    public void remove(int index){
        stations.remove(index);
        fireTableRowsDeleted(index-1, index);
    }

    public void remove(int[] indices){
        for (int index : indices) {
            stations.remove(index);
        }
        fireTableRowsDeleted(indices[0], indices[indices.length-1]);
    }
    
    public void setTemperature(int index, double temperature) throws Exception{
        stations.get(index).setTemperature(temperature);
    }
    
    public void setHumidity(int index, int humidity) throws Exception{
        stations.get(index).setHumidity(humidity);
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    @Override
    public int getRowCount() {
        return stations.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return stations.get(rowIndex);
    }
}
