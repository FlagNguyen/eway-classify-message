package eway.file.impl

import eway.constant.Constant
import eway.domain.Message
import eway.file.FileHandle
import eway.util.SortMessageByTime
import eway.util.StringUtil
import org.apache.commons.io.*

import java.util.logging.Level
import java.util.logging.Logger

class FileHandleImpl implements FileHandle {

    private final StringUtil stringUtil = new StringUtil()
    Logger logger = Logger.getLogger(FileHandleImpl.class.getName())

    /**
     * @param inputPhoneNumberFolderPath
     * @return list of all message in input directory which sorted by time and formatted by output format
     */
    @Override
    List<Message> readInputPhoneNumberFolder(String inputPhoneNumberFolderPath) {
        List<Message> messages = new ArrayList<>()
        File inputPhoneNumberFolder = new File(inputPhoneNumberFolderPath)

        for (childFile in inputPhoneNumberFolder.list()) {
            try {
                File phoneNumberFile = new File(inputPhoneNumberFolder, childFile)
                List phoneNumbers = FileUtils.readLines(phoneNumberFile, "UTF-8")

                for (line in phoneNumbers) {
                    String content = stringUtil.getContentFromLine(line)
                    String phoneNumber = childFile.replace(".txt", "")
                    String time = stringUtil.getTimeStringFromLine(line)
                    messages.add(new Message(phoneNumber, content, time))
                }
            } catch (e) {
                logger.warning("Error: error when read file " + childFile)
            }
        }

        Collections.sort(messages, new SortMessageByTime())
        logger.info("Read files successfully")
        return messages
    }

    /**
     * @param messageMapByDate (
     * K - Date
     * V - Messages which sent in this date )
     * @implNote write sorted messages into file.txt for each date.
     */
    @Override
    void writeIntoFiles(Map<String, List<Message>> messageMapByDate) {
        for (date in messageMapByDate.keySet()) {
            try {
                String outputFileName = Constant.OUTPUT_DIR_PATH + date.replace("/", "") + ".txt"
                File outputPhoneNumberFile = new File(outputFileName)
                List<Message> messageOfDate = messageMapByDate.get(date)
                for (mess in messageOfDate) {
                    FileUtils.write(outputPhoneNumberFile, mess.toString() + "\n", "UTF-8", true)
                }
            } catch (e) {
                logger.warning("Error: error when write into file: "+e)
            }
        }
        logger.info("Write into files end")
    }
}
