package com.np;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        WordOccurance wc = new WordOccurance("input.txt", "report.html");      
        wc.writeToFile();
    }
}
