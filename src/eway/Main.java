package eway;

import eway.constant.Constant;
import eway.file.FileHandle;
import eway.file.impl.FileHandleImpl;
import eway.service.Service;
import eway.service.impl.ServiceImpl;
import eway.util.StringUtil;

public class Main {

    static final FileHandle fileHandle = new FileHandleImpl();
    static final StringUtil stringUtil = new StringUtil();
    static final Service service = new ServiceImpl();

    public static void main(String[] args) {
        fileHandle.writeIntoFile(
                service.classifyMessByDate(
                        fileHandle.readFile(Constant.INPUT_DIR_PATH)));
    }
}
