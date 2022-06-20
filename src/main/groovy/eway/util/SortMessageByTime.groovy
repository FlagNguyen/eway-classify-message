package eway.util

import eway.domain.Message

class SortMessageByTime implements Comparator<Message> {
    private final StringUtil stringUtil = new StringUtil();

    /**
     * @param mess1
     * @param mess2
     * @return compare 02 mess by time
     */
    @Override
    int compare(Message mess1, Message mess2) {
        return stringUtil.convertStringToDate(mess1.getTime())
                .compareTo(stringUtil.convertStringToDate(mess2.getTime()));
    }

}
