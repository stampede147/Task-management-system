package ru.ekudashov.taskms.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.Collections;

@Data
public class PageDto<T> {

    Collection<T> content;

    int totalPages;

    long totalElements;

    int size;

    int number;

    @JsonGetter
    @JsonInclude
    public Collection<T> getContent() {
        return content == null ? Collections.emptyList()
                : content;


    }
}