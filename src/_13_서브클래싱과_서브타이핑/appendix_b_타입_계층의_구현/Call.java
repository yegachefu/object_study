package _13_서브클래싱과_서브타이핑.appendix_b_타입_계층의_구현;

import java.time.Duration;
import java.time.LocalDateTime;

public class Call {
    private Duration duration;
    private LocalDateTime from;

    public Duration getDuration() {
        return this.duration;
    }

    public LocalDateTime getFrom() {
        return from;
    }
}
