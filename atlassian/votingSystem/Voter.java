class Voter implements Runnable {
    private final Party party;
    private final GroupManager groupManager;
    private final Runnable vote;

    public Voter(Party party, GroupManager groupManager, Runnable vote) {
        this.party = party;
        this.groupManager = groupManager;
        this.vote = vote;
    }

    @Override
    public void run() {
        try {
            groupManager.enterAndVote(party, vote);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
