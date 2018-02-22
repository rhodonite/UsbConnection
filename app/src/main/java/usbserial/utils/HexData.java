package usbserial.utils;

import java.math.BigDecimal;

public class HexData
{
    private static final String HEXES = "0123456789ABCDEF";
    private static final String HEX_INDICATOR = "0x";
    private static final String SPACE = " ";
    private final static char[] mChars = "0123456789ABCDEF".toCharArray();
    //private final static String mHexStr = "0123456789ABCDEF";
    private HexData()
    {

    }
    private static final int DEF_DIV_SCALE = 10;
    public static String hexToString(byte[] data)
    {
        if(data != null)
        {
            StringBuilder hex = new StringBuilder(2*data.length);
            for(int i=0;i<=data.length-1;i++)
            {
                byte dataAtIndex = data[i];
                //hex.append(HEX_INDICATOR);
                hex.append(HEXES.charAt((dataAtIndex & 0xF0) >> 4))
                        .append(HEXES.charAt((dataAtIndex & 0x0F)));
                hex.append(SPACE);
            }
            return hex.toString();
        }else
        {
            return null;
        }
    }

    public static byte[] stringTobytes(String hexString)
    {
        String stringProcessed = hexString.trim().replaceAll("0x", "");
        stringProcessed = stringProcessed.replaceAll("\\s+","");
        byte[] data = new byte[stringProcessed.length()/2];
        int i = 0;
        int j = 0;
        while(i <= stringProcessed.length()-1)
        {
            byte character = (byte) Integer.parseInt(stringProcessed.substring(i, i+2), 16);
            data[j] = character;
            j++;
            i += 2;
        }
        return data;
    }

    public static String hex4digits(String id)
    {
        if(id.length() == 1) return "000" + id;
        if(id.length() == 2) return "00" + id;
        if(id.length() == 3) return "0" + id;
        else return id;
    }

    public static byte[] hexToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }


    public String getStringToHex(String strValue) {
        byte byteData[] = null;
        int intHex = 0;
        String strHex = "";
        String strReturn = "";

        try {
            byteData = strValue.getBytes("UTF-8");
            for (int intI=0;intI<byteData.length;intI++)
            {
                intHex = (int)byteData[intI];
                if (intHex<0)
                    intHex += 256;
                if (intHex<16)
                    strHex += "0" + Integer.toHexString(intHex).toUpperCase();
                else
                    strHex += Integer.toHexString(intHex).toUpperCase();
            }
            strReturn = strHex;

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return strReturn;
    }

    public static String str2HexStr(String str){
        StringBuilder sb = new StringBuilder();
        byte[] bs = str.getBytes();

        for (int i = 0; i < bs.length; i++){
            sb.append(mChars[(bs[i] & 0xFF) >> 4]);
            sb.append(mChars[bs[i] & 0x0F]);
            sb.append(' ');
        }
        return sb.toString().trim();
    }
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static byte[] hexstringtobyte(String hexString){

        if(hexString==null || hexString.equals(""))
            return null;

        hexString= hexString.toUpperCase();
        int length = hexString.length()/2;
        char[] hexchars = hexString.toCharArray();
        byte[] d=new byte[length];
        for (int i=0;i<length;i++) {
            int pos = i * 2;
            d[i]=(byte)(charToByte(hexchars[pos])<<4|charToByte(hexchars[pos+1]));
        }
            return d;
    }
    private static byte charToByte(char c)
    {
        return (byte)"0123456789ABCDEF".indexOf(c);
    }
}
