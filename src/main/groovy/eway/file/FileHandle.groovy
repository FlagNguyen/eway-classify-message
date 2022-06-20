package eway.file

import eway.domain.Message

interface FileHandle {
    List<Message> readFile(String inputDirPath)

    void writeIntoFile(Map<String, List<Message>> messageMapByDate)
}
