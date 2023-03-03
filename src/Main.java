import java.util.ArrayList;
import java.util.List;

public class Main {
    private List<String> contestants;
    private List<Integer> songStopsInSec;

    public Main(String[] contestants, int[] songStopsInSec) {
        if (contestants.length != songStopsInSec.length + 1) {
            throw new IllegalArgumentException("Number of contestants should be exactly 1 more than number of stops");
        }
        this.contestants = new ArrayList<>(List.of(contestants));
        this.songStopsInSec = new ArrayList<>(List.of(toIntegerArray(songStopsInSec)));
    }

    public String play() {
        int currentContestantIndex = 0;
        for (int stopDuration : songStopsInSec) {
            int loserIndex = currentContestantIndex;
            for (int i = 0; i < stopDuration; i++) {
                loserIndex = getNextContestantIndex(loserIndex);
            }
            contestants.set(loserIndex, null);
            currentContestantIndex = getNextContestantIndex(loserIndex);
        }
        return contestants.stream()
                .filter(c -> c != null)
                .findFirst()
                .orElse(null);
    }

    private int getNextContestantIndex(int currentIndex) {
        int nextIndex = (currentIndex + 1) % contestants.size();
        while (contestants.get(nextIndex) == null) {
            nextIndex = (nextIndex + 1) % contestants.size();
        }
        return nextIndex;
    }

    private static Integer[] toIntegerArray(int[] arr) {
        Integer[] result = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    public static void main(final String[] args) {
        String[] contestants = { "sanskar", "yuvraj", "rabindra", "shubh" };
        int[] songStopsInSec = { 3, 5, 7 };
        Main game = new Main(contestants, songStopsInSec);
        String winner = game.play();
        System.out.println(winner);
    }
}
