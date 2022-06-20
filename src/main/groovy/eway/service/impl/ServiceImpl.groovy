package eway.service.impl

import eway.domain.Message
import eway.service.Service
import eway.util.StringUtil

import java.util.logging.Level
import java.util.logging.Logger

class ServiceImpl implements Service {

    private final StringUtil stringUtil = new StringUtil()
    Logger logger = Logger.getLogger(ServiceImpl.class.getName())

    /**
     * @param messages
     * @return the map which has
     * key: date
     * value: messages which been sorted in this date
     */
    @Override
    Map<String, List<Message>> classifyMessByDate(List<Message> messages) {
            Map<String, List<Message>> receivedMessagesByDate = new HashMap<>()

        for (String date : getDistinctDates(messages)) {
            List<Message> messageOfDate = new ArrayList<>()
            for (Message message : messages) {
                if (date.equals(stringUtil.getDateFromMessage(message.getTime()))) {
                    messageOfDate.add(message)
                }
                receivedMessagesByDate.put(date, messageOfDate)
            }
        }
        logger.log(Level.INFO,"Classify files successfully")
        return receivedMessagesByDate
    }

    /**
     * @param messages
     * @return set of date in list message
     */
    @Override
    Set<String> getDistinctDates(List<Message> messages) {
        Set<String> distinctDates = new HashSet<>()
        for (Message mess : messages) {
            distinctDates.add(stringUtil.getDateFromMessage(mess.getTime()))
        }
        return distinctDates
    }
}
