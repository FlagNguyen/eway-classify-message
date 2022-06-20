package eway.util

import eway.constant.Constant

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.logging.Level
import java.util.logging.Logger

class StringUtil {

    private final Logger logger = Logger.getLogger(StringUtil.class.getName())

    String getContentFromLine(String line) {
        return line.split("\\|")[0]
    }

    String getTimeStringFromLine(String line) {
        return line.trim().split("\\|")[1]
    }

    String getTimeStringFromMessage(String message) {
        return message.trim().split("\\|")[0]
    }

    Date getTimeFromMessage(String message) {
        return convertStringToDate(getTimeStringFromMessage(message))
    }

    String getDateFromMessage(String mess) {
        return getTimeStringFromMessage(mess).split(" ")[0]
    }

    Date convertStringToDate(String time) {
        DateFormat dateFormat = new SimpleDateFormat(Constant.TIME_FORMAT)
        dateFormat.setLenient(false)
        try {
            return dateFormat.parse(time.trim())
        } catch (ParseException pe) {
            logger.log(Level.WARNING, "Error: convert string to date failure")
        }
        return new Date()
    }

}
