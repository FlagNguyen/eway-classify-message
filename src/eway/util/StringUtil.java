package eway.util;

import eway.constant.Constant;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StringUtil {

    private final Logger logger = Logger.getLogger(StringUtil.class.getName());

    public String getContentFromLine(String line) {
        return line.split("\\|")[0];
    }

    public String getTimeStringFromLine(String line) {
        return line.trim().split("\\|")[1];
    }

    public String getTimeStringFromMessage(String message) {
        return message.trim().split("\\|")[0];
    }

    public Date getTimeFromMessage(String message) {
        return convertStringToDate(getTimeStringFromMessage(message));
    }

    public String getDateFromMessage(String mess) {
        return getTimeStringFromMessage(mess).split(" ")[0];
    }

    public Date convertStringToDate(String time) {
        DateFormat dateFormat = new SimpleDateFormat(Constant.TIME_FORMAT);
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(time.trim());
        } catch (ParseException pe) {
            logger.log(Level.WARNING, "Error: convert string to date failure");
        }
        return new Date();
    }

}
