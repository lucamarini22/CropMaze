package it.unibo.oop.bbgmm.entity;

import it.unibo.oop.bbgmm.utilities.Pair;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@link ScoreList} implementation.
 */
public final class ScoreListImpl implements ScoreList {
    private static final String FILE_NAME = "Ranking.txt";
    private static final int RANKING_SIZE = 5;
    private List<Score> scoreList = new ArrayList<>();
    private File rankingFile;

    /**
     * {@link ScoreList} implementation.
     *
     * @throws IOException
     *          Exception if the file does not exist
     */
    public ScoreListImpl() throws IOException {
        initiate();
        final ObjectInputStream ostream = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(this.rankingFile.toPath())));

        final int size = ostream.readInt();
        for (int i = 0; i < size; i++) {
            try {
                this.scoreList.add((Score) ostream.readObject());
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
        if (!this.scoreList.contains(newScore)) {
            this.scoreList.add(newScore);
            this.scoreList.sort((s1, s2) -> s2.getScore() - s1.getScore());
            if (this.scoreList.size() > RANKING_SIZE) {
                this.scoreList = this.scoreList.subList(0, RANKING_SIZE);
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
        final ObjectOutputStream ostream = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(this.rankingFile.toPath())));
        ostream.writeInt(this.scoreList.size());
        this.scoreList.forEach(s -> {
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
        return this.scoreList.stream()
                             .map((s) -> new Pair<>(s.getPlayerName(), s.getScore()))
                             .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() throws IOException {
        final ObjectOutputStream ostream = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(this.rankingFile.toPath())));
        this.scoreList.clear();
        ostream.writeInt(0);
        ostream.flush();
        ostream.close();
    }

    /**
     * Method that creates the rankingFile in the home folder.
     *
     * @throws IOException
     *          Exception thrown if the file can't be created
     */
    private void initiate() throws IOException {
        final File folder = new File(System.getProperty("user.home"), ".cropMaze");
        if (!folder.exists()) {
            if (!folder.mkdir()) {
                throw new RuntimeException("Failed to crate the directory");
            }
            this.rankingFile = new File(folder, FILE_NAME);
            if (!this.rankingFile.createNewFile()) {
                throw new IOException("Failed to crate the file");
            }
            this.rankingFile.createNewFile();
            deleteAll();
        } else {
            this.rankingFile = folder.listFiles()[0];
        }
    }
}
