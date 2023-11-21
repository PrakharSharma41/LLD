package Model;

public class Player {
    public PlayingPiece PlayingPiece;
    public String name;
    public PlayingPiece getPlayingPiece() {
        return PlayingPiece;
    }
    public void setPlayingPiece(PlayingPiece playingPiece) {
        PlayingPiece = playingPiece;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Player(PlayingPiece playingPiece, String name) {
        PlayingPiece = playingPiece;
        this.name = name;
    }

        
}
