/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author johannesriedmueller
 */
public class WeatherStationBL extends AbstractTableModel {

    private ArrayList<WeatherStation> stations = new ArrayList<>();
    private String[] colNamesNormal = {"Place", "Sea Level", "Temperature", "rel. Humidity"};
    private String[] colNamesHiddenSea = {"Place", "Temperature", "rel. Humidity"};
    private boolean hiddenSeaLevel = false;

    public void add(WeatherStation ws) {
        stations.add(ws);
        fireTableRowsInserted(stations.size() - 1, stations.size() - 1);
    }

    public void remove(int index) {
        stations.remove(index);
        fireTableRowsDeleted(index - 1, index);
    }

    public void remove(int[] indices) {
        for (int index : indices) {
            stations.remove(index);
        }
        fireTableRowsDeleted(indices[0], indices[indices.length - 1]);
    }

    public void setTemperature(int index, double temperature) throws Exception {
        stations.get(index).setTemperature(temperature);
    }

    public void setHumidity(int index, int humidity) throws Exception {
        stations.get(index).setHumidity(humidity);
    }

    public void hideShowSeaLevel() {
        if (hiddenSeaLevel) {
            hiddenSeaLevel = false;
        } else {
            hiddenSeaLevel = true;
        }
        fireTableStructureChanged();
    }

    @Override
    public String getColumnName(int column) {
        if (hiddenSeaLevel) {
            return colNamesHiddenSea[column];
        }
        return colNamesNormal[column];
    }

    @Override
    public int getRowCount() {
        return stations.size();
    }

    @Override
    public int getColumnCount() {
        if (hiddenSeaLevel) {
            return colNamesHiddenSea.length;
        }
        return colNamesNormal.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return stations.get(rowIndex);
    }
}
