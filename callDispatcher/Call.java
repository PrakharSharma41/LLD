public class Call implements Comparable<Call>{
    int callId;
    CallStatus status;
    Employee employee;
    long timestamp;
    int priorityLevel; // 0: low, 1: mid, 2: high

    @Override
    public int compareTo(Call other) {
        // Higher priority first; older timestamp breaks tie
        if (this.priorityLevel != other.priorityLevel) {
            return Integer.compare(other.priorityLevel, this.priorityLevel);
        }
        return Long.compare(this.timestamp, other.timestamp);
    }
}