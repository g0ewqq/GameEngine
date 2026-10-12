package dev.gameengine.config;

/**
 * Thrown when a YAML configuration document cannot be loaded or is structurally invalid.
 */
public class GameEngineConfigException extends RuntimeException {

    public GameEngineConfigException(String message) {
        super(message);
    }

    public GameEngineConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
