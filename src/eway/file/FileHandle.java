package eway.file;

import java.util.List;
import java.util.Map;

public interface FileHandle {
    List<String> readFile(String inputDirPath);

    void writeIntoFile(Map<String, List<String>> messageMapByDate);
}
