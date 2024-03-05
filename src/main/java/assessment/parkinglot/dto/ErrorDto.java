package assessment.parkinglot.dto;

import java.util.Objects;

public class ErrorDto implements Comparable<ErrorDto> {

    private final String code;
    private final String message;

    public ErrorDto(final Builder builder) {
        this.code = builder.code;
        this.message = builder.message;
    }

    @Override
    public int compareTo(ErrorDto errorDto) {
        final Integer code1 = Integer.parseInt(code);
        final Integer code2 = Integer.parseInt(errorDto.code);
        return code1.compareTo(code2);
    }

    public static final class Builder {
        private String code;
        private String message;

        public ErrorDto build() {
            return new ErrorDto(this);
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorDto errorDto = (ErrorDto) o;
        return code.equals(errorDto.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "{\"code\":\""+ code +"\"" + ",\"message\":\""+ message +"\"}";
    }
}
