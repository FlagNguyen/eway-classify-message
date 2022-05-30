package eway.file;

import eway.domain.Message;

import java.util.List;
import java.util.Map;

public interface FileHandle {
    List<Message> readFile(String inputDirPath);

    void writeIntoFile(Map<String, List<Message>> messageMapByDate);
}
