package eway.file.impl;

import eway.constant.Constant;
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
    private final Service service = new ServiceImpl();
    Logger logger = Logger.getLogger(FileHandleImpl.class.getName());

    /**
     * @param inputDirPath
     * @return list of all message in input directory which sorted by time and formatted by output format
     */
    @Override
    public List<String> readFile(String inputDirPath) {
        List<String> messages = new ArrayList<>();
        File dir = new File(inputDirPath);
        String[] files = dir.list();

        for (String file : files) {
            File f = new File(dir, file);
            String line = "";
            try {
                BufferedReader reader = new BufferedReader(new FileReader(f));
                while ((line = reader.readLine()) != null) {
                    messages.add(stringUtil.getTimeStringFromLine(line) + "|"
                            + file.replace(".txt", "") + "|"
                            + stringUtil.getContentFromLine(line));
                }
            } catch (IOException e) {
                logger.log(Level.WARNING, "Error: error when read file " + file);
            }
        }

        Collections.sort(messages, new SortMessageByTime());
        return messages;
    }

    /**
     * @param messageMapByDate
     * @implNote write sorted messages into file.txt for each date.
     */
    @Override
    public void writeIntoFile(Map<String, List<String>> messageMapByDate) {
        Set<String> dateSet = service.getDateSet(readFile(Constant.INPUT_DIR_PATH));
        for (String date : dateSet) {
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(Constant.OUTPUT_DIR_PATH
                        + date.replace("/", "") + ".txt", false);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                List<String> messageOfDate = messageMapByDate.get(date);
                for (String mess : messageOfDate) {
                    bufferedWriter.write(mess + "\n");
                }
                bufferedWriter.flush();
            } catch (IOException e) {
                logger.log(Level.WARNING, "Error: error when write into file");
            }
        }
    }
}
