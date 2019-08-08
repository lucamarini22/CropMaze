package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Utilities.Pair;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@link ScoreList} implementation.
 */
public class ScoreListImpl implements ScoreList{

    private final URL fileName = ClassLoader.getSystemResource("ScoreList.txt");
    private List<Score> scoreList = new ArrayList<>();

    /**
     * {@link ScoreList} implementation.
     *
     * @throws IOException
     */
    public ScoreListImpl() throws IOException {
        final ObjectInputStream ostream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName.getPath())));
        int size = ostream.readInt();
        for(int i = 0; i<size; i++) {
            try {
                scoreList.add((Score) ostream.readObject());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        ostream.close();
    }

    @Override
    public void addScore(final Pair<String, Integer> score){
        //maximux size of the scoreList is 5
        Score newScore = new Score(score.getFst(),score.getSnd());
        if(!scoreList.contains(newScore)){
            scoreList.add(newScore);
            scoreList.sort((s1,s2) -> s2.getScore()-s1.getScore());
            if(scoreList.size() > 5){
                scoreList = scoreList.subList(0, 5);
            }
            try {
                writeOnFile();
            } catch (IOException e) {
                System.out.println("Error on writing on file\n");
                e.printStackTrace();
            }
        }//if it contains the exact score it does nothing
    }

    /**
     * Method that writes the list on file.
     *
     * @throws IOException
     */
    private void writeOnFile() throws IOException {
        final ObjectOutputStream ostream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName.getPath(), false)));
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
        final ObjectOutputStream ostream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName.getPath(), false)));
        scoreList.clear();
        ostream.writeInt(0);
        ostream.flush();
        ostream.close();
    }
}