package eway.service;

import eway.domain.Message;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Service {
    Map<String, List<Message>> classifyMessByDate(List<Message> messages);

    Set<String> getDateSet(List<Message> messages);
}
