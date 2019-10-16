package com.np;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class WordOccurance {
	private String inputFile, outputFile;

	public WordOccurance(String inputFile, String outputFile) {
		this.inputFile = inputFile;
		this.outputFile = outputFile;
	}

	/**
	 * returns html headers to be written
	 * 
	 * @return
	 */
	public String getHeader() {
		return "<html></head>" + "<title>Test Project</title>" + "<style>\n" + "table{width:100%}"
				+ "  td, #customers th {\n" + "  border: 1px solid #ddd;\n" + "  padding: 8px;\n" + "}\n" + "\n"
				+ "tr:nth-child(even){background-color: #f2f2f2;}\n" + "\n" + "tr:hover {background-color: #ddd;}\n"
				+ "\n" + "th {\n" + "  padding-top: 12px;\n" + "  padding-bottom: 12px;\n" + "  text-align: left;\n"
				+ "  background-color: #C0C0C0;\n" + "  color: black;\n" + "}\n" + "</style>"
				+ "</head><body><h2>Input file name: " + inputFile;
	}

	/**
	 * generates footer
	 * 
	 * @return
	 */
	public String getFooter() {
		return "</body></html";
	}

	/**
	 * generates content of input file
	 * 
	 * @return
	 */
	public String getInputContent() {
		StringBuilder sb = new StringBuilder();

		sb.append("<table><tr><th>File Content</th></tr><tr><td>");
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line + "<br>");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		sb.append("</td></tr></table><br><br>");
		return sb.toString();
	}

	/**
	 * generates sorted list of report
	 * 
	 * @return
	 */
	public List<Entry<String, Integer>> getSortedContent() {
		HashMap<String, Integer> wordMap = new HashMap<>();
		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			while ((line = br.readLine()) != null) {
				if (line.length() > 0) {
					String[] tempWordArray = line.trim().replaceAll("[.!,;]", "").split("\\s+");
					for (String word : tempWordArray) {
						wordMap.computeIfPresent(word, (k, v) -> v + 1);
						wordMap.computeIfAbsent(word, k -> 1);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sortMapData(wordMap);
	}

	/**
	 * sorts the input map
	 * 
	 * @param wordMap
	 * @return
	 */

	public List<Entry<String, Integer>> sortMapData(Map<String, Integer> wordMap) {
		List<Map.Entry<String, Integer>> list = new ArrayList<>(wordMap.entrySet());
		Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
		return list;

	}

	/**
	 * builds the output to be written in files
	 * 
	 * @return
	 */
	public String getContentReport() {
		List<Entry<String, Integer>> entries = getSortedContent();
		StringBuilder sb = new StringBuilder();
		sb.append("<table><tr><th>Word</th><th>Occurance</th></tr>");
		for (Entry<String, Integer> entry : entries) {
			sb.append(String.format("<tr><td>%s</td><td>%s</td></tr>", entry.getKey(), entry.getValue()));
//			sb.append("<tr><td>" + entry.getKey() + "</td><td>" + entry.getValue() + "</td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

	/**
	 * method to write contents to file
	 */
	public void writeToFile() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
			System.out.println("Writing header initiated...");
			bw.write(getHeader());
			System.out.println("Writing input file initiated...");
			bw.write(getInputContent());
			System.out.println("Writing report initiated...");
			bw.write(getContentReport());
			bw.write(getFooter());
			System.out.println("File writing completed, please check file: " + outputFile);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
