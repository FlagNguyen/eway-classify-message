package eway.file

import eway.domain.Message

interface FileHandle {
    List<Message> readInputPhoneNumberFolder(String inputDirPath)

    void writeIntoFiles(Map<String, List<Message>> messageMapByDate)
}
