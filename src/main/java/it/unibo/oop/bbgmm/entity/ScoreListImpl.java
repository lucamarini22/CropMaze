package it.unibo.oop.bbgmm.entity;

import it.unibo.oop.bbgmm.utilities.Pair;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@link ScoreList} implementation.
 */
public final class ScoreListImpl implements ScoreList {

    private static final int RANKING_SIZE = 5;
    private static final String FILE_NAME = "ScoreList.txt";
    private static final String PATH = ClassLoader.getSystemResource(FILE_NAME).getPath();
    private List<Score> scoreList = new ArrayList<>();

    /**
     * {@link ScoreList} implementation.
     *
     * @throws IOException
     *          Exception if the file does not exist
     */
    public ScoreListImpl() throws IOException {
        final ObjectInputStream ostream = new ObjectInputStream(new BufferedInputStream(ClassLoader.getSystemResourceAsStream(FILE_NAME)));

        final int size = ostream.readInt();
        for (int i = 0; i < size; i++) {
            try {
                scoreList.add((Score) ostream.readObject());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        ostream.close();
    }

    @Override
    public void addScore(final Pair<String, Integer> score) {
        //maximux size of the scoreList is 5
        final Score newScore = new Score(score.getFst(), score.getSnd());
        if (!scoreList.contains(newScore)) {
            scoreList.add(newScore);
            scoreList.sort((s1, s2) -> s2.getScore() - s1.getScore());
            if (scoreList.size() > RANKING_SIZE) {
                scoreList = scoreList.subList(0, RANKING_SIZE);
            }
            try {
                writeOnFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } //if it contains the exact score it does nothing
    }

    /**
     * Method that writes the list on file.
     *
     * @throws IOException
     *          Exception if the file does not exist
     */
    private void writeOnFile() throws IOException {
        final ObjectOutputStream ostream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(PATH)));
        ostream.writeInt(scoreList.size());
        scoreList.forEach(s -> {
            try {
                ostream.writeObject(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        ostream.flush();
        ostream.close();
    }

    @Override
    public List<Pair<String, Integer>> getRanking() {
        return scoreList.stream()
                .map((s) -> new Pair<>(s.getPlayerName(), s.getScore()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() throws IOException {
        final ObjectOutputStream ostream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(PATH)));
        scoreList.clear();
        ostream.writeInt(0);
        ostream.flush();
        ostream.close();
    }
}
