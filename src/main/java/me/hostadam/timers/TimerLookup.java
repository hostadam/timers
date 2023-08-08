package me.hostadam.timers;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class TimerLookup {

    @NonNull
    private String key;
    private Consumer<Timer<?>> presentConsumer, emptyConsumer;

    public TimerLookup ifPresent(Consumer<Timer<?>> consumer) {
        this.presentConsumer = consumer;
        return this;
    }

    public TimerLookup ifEmpty(Consumer<Timer<?>> consumer) {
        this.emptyConsumer = consumer;
        return this;
    }

    public void run() {
        Optional<Timer<?>> timer = Timer.find(key);
        if(timer.isPresent() && this.presentConsumer != null) {
            this.presentConsumer.accept(timer.get());
        } else if(timer.isEmpty() && this.emptyConsumer != null) {
            this.emptyConsumer.accept(null);
        }
    }
}
