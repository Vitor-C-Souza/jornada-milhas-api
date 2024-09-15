package me.vitorcsouza.jornada_milhas_api.infra.exception;

public class destinoNotFound extends RuntimeException {
    private final String message;
    public destinoNotFound(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
