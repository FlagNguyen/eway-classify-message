package eway.service.impl;

import eway.domain.Message;
import eway.file.impl.FileHandleImpl;
import eway.service.Service;
import eway.util.StringUtil;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceImpl implements Service {

    private final StringUtil stringUtil = new StringUtil();
    Logger logger = Logger.getLogger(ServiceImpl.class.getName());

    /**
     * @param messages
     * @return the map which has
     * key: date
     * value: messages which been sorted in this date
     */
    @Override
    public Map<String, List<Message>> classifyMessByDate(List<Message> messages) {
        Map<String, List<Message>> outputMap = new HashMap<>();
        Set<String> dateSet = getDateSet(messages);

        for (String date : dateSet) {
            List<Message> messageOfDate = new ArrayList<>();
            for (Message message : messages) {
                if (date.equals(stringUtil.getDateFromMessage(message.getTime()))) {
                    messageOfDate.add(message);
                }
                outputMap.put(date, messageOfDate);
            }
        }
        logger.log(Level.INFO,"Classify files successfully");
        return outputMap;
    }

    /**
     * @param messages
     * @return set of date in list message
     */
    @Override
    public Set<String> getDateSet(List<Message> messages) {
        Set<String> dateSet = new HashSet<>();
        for (Message mess : messages) {
            dateSet.add(stringUtil.getDateFromMessage(mess.getTime()));
        }
        return dateSet;
    }
}
