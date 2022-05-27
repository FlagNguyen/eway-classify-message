package eway.service.impl;

import eway.service.Service;
import eway.util.StringUtil;

import java.util.*;

public class ServiceImpl implements Service {

    private final StringUtil stringUtil = new StringUtil();

    /**
     * @param messages
     * @return the map which has
     * key: date
     * value: messages which been sorted in this date
     */
    @Override
    public Map<String, List<String>> classifyMessByDate(List<String> messages) {
        Map<String, List<String>> outputMap = new HashMap<>();
        Set<String> dateSet = getDateSet(messages);

        for (String date : dateSet) {
            List<String> messageOfDate = new ArrayList<>();
            for (String message : messages) {
                if (date.equals(stringUtil.getDateFromMessage(message))) {
                    messageOfDate.add(message);
                }
                outputMap.put(date, messageOfDate);
            }
        }
        return outputMap;
    }

    /**
     * @param messages
     * @return set of date in list message
     */
    @Override
    public Set<String> getDateSet(List<String> messages) {
        Set<String> dateSet = new HashSet<>();
        for (String mess : messages) {
            dateSet.add(stringUtil.getDateFromMessage(mess));
        }
        return dateSet;
    }
}
