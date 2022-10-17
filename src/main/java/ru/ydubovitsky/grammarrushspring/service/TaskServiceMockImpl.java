package ru.ydubovitsky.grammarrushspring.service;

import com.thedeanda.lorem.LoremIpsum;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.grammarrushspring.entity.Task;
import ru.ydubovitsky.grammarrushspring.entity.Theme;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Qualifier("MockTaskService")
@Primary
@AllArgsConstructor
public class TaskServiceMockImpl implements TaskService {

    private final LoremIpsum loremIpsum;
    private static final int MIN = 50;
    private static final int MAX = 100;

    @Override
    public List<Task> getAllTasks() {
        return Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8)
                .map(value -> Task.builder()
                        .id(Long.valueOf(value))
                        .task(loremIpsum.getWords(ThreadLocalRandom.current().nextInt(MIN, MAX + 1)))
                        .theme(Theme.builder()
                                .name(loremIpsum.getTitle(2))
                                .build())
                        .answer(loremIpsum.getWords(2))
                        .build()
                ).collect(Collectors.toList());
    }
}
