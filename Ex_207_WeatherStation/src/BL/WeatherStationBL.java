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
        if (stations.isEmpty()) {
            stations.add(ws);
        } else if (stations.size() == 1) {
            stations.add(binarySearch(stations.size() - 1, stations.size() - 1, ws.getPlace()), ws);
        } else {
            stations.add(binarySearch(0, stations.size() - 1, ws.getPlace()), ws);
        }

        fireTableRowsInserted(0, stations.size() - 1);
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

    public void save(File f) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
        for (WeatherStation station : stations) {
            oos.writeObject(station);
        }
        oos.flush();
        oos.close();
    }

    public void load(File f) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
        Object obj;
        try {
            while ((obj = ois.readObject()) != null) {
                if (obj instanceof WeatherStation) {
                    add((WeatherStation) obj);
                }
            }
        } catch (EOFException ex) {
            //Just to catch this...
        }
        ois.close();
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

    private int binarySearch(int li, int re, String place) {
        if (li > re) {
            return li;
        }

        int mi = (li + re) / 2;
        if (place.compareToIgnoreCase(stations.get(mi).getPlace()) >= 1) {
            return binarySearch(mi + 1, re, place);
        }
        if (place.compareToIgnoreCase(stations.get(mi).getPlace()) <= -1) {
            return binarySearch(li, mi - 1, place);
        }
        return li;
    }
}
