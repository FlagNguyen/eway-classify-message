package eway.service

import eway.domain.Message

interface Service {
    Map<String, List<Message>> classifyMessByDate(List<Message> messages)

    Set<String> getDistinctDates(List<Message> messages)
}
