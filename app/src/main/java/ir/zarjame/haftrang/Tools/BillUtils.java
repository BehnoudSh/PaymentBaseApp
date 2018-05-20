package ir.zarjame.haftrang.Tools;

import java.util.HashMap;
import java.util.Map;

import ir.zarjame.haftrang.R;

/**
 * Created by tinabehnoud on 5/20/18.
 */

public class BillUtils {

    public static Map<String, Integer> getBillTypeInfo(int billType) {
        Map<String, Integer> map = new HashMap<>();
        int billTypeTitleRes = 0;
        int billImageRes = 0;
        switch (billType) {
            case 1:
                billTypeTitleRes = R.string.ab;
                billImageRes = R.drawable.ablogo;
                break;
            case 2:
                billTypeTitleRes = R.string.bargh;
                billImageRes = R.drawable.barghlogo;
                break;
            case 3:
                billTypeTitleRes = R.string.gaz;
                billImageRes = R.drawable.gazlogo;
                break;
            case 4:
                billTypeTitleRes = R.string.tell;
                billImageRes = R.drawable.telllogo;
                break;
            case 5:
                billTypeTitleRes = R.string.mobile;
                billImageRes = R.drawable.mobilebilllogo;
                break;
            case 6:
            case 7:
                billTypeTitleRes = R.string.municipal_charges;
                billImageRes = R.drawable.municipallogo;
                break;
            case 8:
                billTypeTitleRes = R.string.tax;
                billImageRes = R.drawable.taxlogo;
                break;
            case 9:
                billTypeTitleRes = R.string.fine;
                billImageRes = R.drawable.rahvarlogo;
                break;
            default:
                billTypeTitleRes = R.string.not_found;
                break;
        }
        map.put("title", billTypeTitleRes);
        map.put("icon1", billImageRes);
        return map;
    }

    public static long getAmount(String payIdentity) {
        String zero = "";
        int payLength = payIdentity.length();
        if (payLength < 13) {
            for (int i = 0; i < 13 - payLength; i++) {
                zero += "0";
            }

            payIdentity = zero + payIdentity;

        }
        payIdentity = payIdentity.substring(0, 8);
        long identity = Long.parseLong(payIdentity) * 1000;
        return identity;
    }
}