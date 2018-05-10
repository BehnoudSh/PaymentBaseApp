package ir.zarjame.haftrang.Models;

/**
 * Created by tinabehnoud on 5/10/18.
 */

public enum Operators {

    IRANCELL("ایرانسل", "Irancell", 0),

    MCI("همراه اول", "Mci", 1),

    RIGHTEL("رایتل", "Rightel", 2);

    private String stringValuePersian;
    private String stringValueEnglish;
    private int intValue;

    private Operators(String persianvalue, String englishvalue, int value) {
        stringValuePersian = persianvalue;
        this.stringValueEnglish = englishvalue;
        intValue = value;
    }

    public String getStringValuePersian() {
        return stringValuePersian;
    }

    public void setStringValuePersian(String stringValuePersian) {
        this.stringValuePersian = stringValuePersian;
    }

    public String getStringValueEnglish() {
        return stringValueEnglish;
    }

    public void setStringValueEnglish(String stringValueEnglish) {
        this.stringValueEnglish = stringValueEnglish;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }
}
