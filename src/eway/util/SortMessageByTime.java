package eway.util;

import java.util.Comparator;

public class SortMessageByTime implements Comparator<String> {
    private final StringUtil stringUtil = new StringUtil();

    /**
     * @param mess1
     * @param mess2
     * @return compare 02 mess by time
     */
    @Override
    public int compare(String mess1, String mess2) {
        return stringUtil.getTimeFromMessage(mess1)
                .compareTo(stringUtil.getTimeFromMessage(mess2));
    }

}
