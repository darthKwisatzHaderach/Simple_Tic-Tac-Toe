package tictactoe;

public enum Result {
    GAME_NOT_FINISHED("Game not finished"),
    DRAW("Draw"),
    X_WINS("X wins"),
    O_WINS("O wins"),
    IMPOSSIBLE("Impossible"),
    UNKNOWN("Unknown");

    public final String description;

    private Result(String description) {
        this.description = description;
    }
}
