package com.plural.rank.student;

import java.util.Spliterator;
import java.util.function.Consumer;

public class RankSpliterator implements Spliterator<RankData> {

	final Spliterator<String> lineSpliterator;
	public RankSpliterator(Spliterator<String> lineSpliterator) {
		super();
		this.lineSpliterator = lineSpliterator;
	}

	@Override
	public int characteristics() {
		return lineSpliterator.characteristics();
	}

	@Override
	public long estimateSize() {
		return lineSpliterator.estimateSize()/2;
	}

	@Override
	public boolean tryAdvance(Consumer<? super RankData> action) {
	    RankData rankData=new RankData();
		if(lineSpliterator.tryAdvance(line->rankData.setIdNumber(line))&&
		lineSpliterator.tryAdvance(line->rankData.setRank(Integer.parseInt(line))))
		{
			action.accept(rankData);
			return true;
		}
		else
		{
			return false;
		}
		
 	}

	@Override
	public Spliterator<RankData> trySplit() {
		// TODO Auto-generated method stub
		return null;
	}

}
