package main.com.sun.h5weixin.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SUN on 2017/2/20.
 */
public class IsMobile {

    public static boolean isMobileNo(String mobiles)
    {
        boolean result =false;
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";

        Pattern pattern = Pattern.compile(regExp);

        if(mobiles != null)
        {
            Matcher matcher = pattern.matcher(mobiles);
            result = matcher.matches();
        }

        return result;
    }
}
