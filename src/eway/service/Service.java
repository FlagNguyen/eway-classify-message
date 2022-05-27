package eway.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Service {
    Map<String, List<String>> classifyMessByDate(List<String> messages);

    Set<String> getDateSet(List<String> messages);
}
