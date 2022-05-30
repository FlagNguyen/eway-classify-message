package eway.file.impl;

import eway.constant.Constant;
import eway.domain.Message;
import eway.file.FileHandle;
import eway.service.Service;
import eway.service.impl.ServiceImpl;
import eway.util.SortMessageByTime;
import eway.util.StringUtil;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileHandleImpl implements FileHandle {

    private final StringUtil stringUtil = new StringUtil();
    Logger logger = Logger.getLogger(FileHandleImpl.class.getName());

    /**
     * @param inputDirPath
     * @return list of all message in input directory which sorted by time and formatted by output format
     */
    @Override
    public List<Message> readFile(String inputDirPath) {
        List<Message> messages = new ArrayList<>();
        File dir = new File(inputDirPath);
        String[] files = dir.list();

        for (String file : files) {
            File f = new File(dir, file);
            String line = "";
            try {
                BufferedReader reader = new BufferedReader(new FileReader(f));
                while ((line = reader.readLine()) != null) {
                    String content = stringUtil.getContentFromLine(line);
                    String phoneNumber = file.replace(".txt", "");
                    String time = stringUtil.getTimeStringFromLine(line);
                    messages.add(new Message(phoneNumber, content, time));
                }
            } catch (IOException e) {
                logger.log(Level.WARNING, "Error: error when read file " + file);
            }
        }

        Collections.sort(messages, new SortMessageByTime());
        logger.log(Level.INFO, "Read files successfully");
        return messages;
    }

    /**
     * @param messageMapByDate
     * @implNote write sorted messages into file.txt for each date.
     */
    @Override
    public void writeIntoFile(Map<String, List<Message>> messageMapByDate) {
        Set<String> dateSet = messageMapByDate.keySet();
        for (String date : dateSet) {
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(Constant.OUTPUT_DIR_PATH
                        + date.replace("/", "") + ".txt", false);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                List<Message> messageOfDate = messageMapByDate.get(date);
                for (Message mess : messageOfDate) {
                    bufferedWriter.write(mess.toString() + "\n");
                }
                bufferedWriter.flush();
            } catch (IOException e) {
                logger.log(Level.WARNING, "Error: error when write into file");
            }
        }
        logger.log(Level.INFO, "Write into files successfully");
    }
}
