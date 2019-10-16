package com.np.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.np.WordOccurance;

public class WordOccuranceTest {
	
	@Test
	public void test() {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("apple", 4);
		map.put("ball", 1);
		map.put("cat", 3);
		map.put("dog", 2);
		
		String [] expectedKeys = {"apple","cat","dog","ball"};
		Integer [] expectedValues= {4,3,2,1};
		
		WordOccurance wc = new WordOccurance("input.txt", "report.html");
		List<Entry<String,Integer>> list = wc.sortMapData(map);
		for (int i=0;i<list.size();i++) {
			Assertions.assertEquals(expectedKeys[i],list.get(i).getKey());
			Assertions.assertEquals(expectedValues[i],list.get(i).getValue());
		}
		
	}

}
