package ir.chichand.chichand.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by bSherafati on 2/5/2018.
 */

public class List_Serializable<T> implements Serializable {
    ArrayList<T> list;

    public List_Serializable(ArrayList<T> list) {
        this.list = list;
    }

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }
}