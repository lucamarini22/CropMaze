package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Utilities.Pair;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreListImpl implements ScoreList{

	private final String fileName = System.getProperty("file.separator")+"ScoreList.txt";
	private List<Score> scoreList = new ArrayList<>();
	
	public ScoreListImpl() throws FileNotFoundException, IOException {
		final ObjectInputStream ostream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
		int size = ostream.readInt();
		for(int i = 0; i<=size; i++) {
			try {
				scoreList.add((Score) ostream.readObject());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		ostream.close();
	}
	
	@Override
	public void addScore(Pair<String, Integer> score) {
		//maximux size of the score is 10
		scoreList.add(new Score(score.getFst(),score.getSnd()));
		scoreList.sort((s1,s2) -> s1.getLevel()-s2.getLevel());
		if(scoreList.size() > 10){
			scoreList = scoreList.subList(0, 9);
		}
	}

	@Override
	public void writeOnFile() throws IOException {
		final ObjectOutputStream ostream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
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
						.map((s) -> new Pair<String, Integer>(s.getPlayerName(), s.getLevel()))
						.collect(Collectors.toList());
	}

	@Override
	public void deleteAll() throws IOException {
		final ObjectOutputStream ostream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
		ostream.writeChar(' ');
		ostream.close();
	}

}
