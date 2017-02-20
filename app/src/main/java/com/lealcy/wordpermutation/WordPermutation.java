package com.lealcy.wordpermutation;

import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by lealcybj on 17/02/2017.
 */

public class WordPermutation {
    public static boolean wordsLoaded = false;
    public static ArrayList<String> words;
    private AppCompatActivity activity;


    public WordPermutation(AppCompatActivity activity) {
        this.activity = activity;
        loadWords();
    }

    public void loadWords()
    {
        if (wordsLoaded) {
            return;
        }
        wordsLoaded = true;
        InputStream is = activity.getResources().openRawResource(R.raw.words);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        words = new ArrayList<String>();
        String line;

        try {
            while((line = br.readLine()) != null) {
                words.add(line);
            }
            is.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean containsWord(String word)
    {
        return words.contains(word);
    }

    public ArrayList<String> getPermutations(String s)
    {
        ArrayList<String> output = new ArrayList<String>();
        if (s.length() == 1)
        {
            output.add(s);
        }
        else
        {
            for (int i = 0; i < s.length(); i++)
            {
                char c = s.charAt(i);
                // Remove one occurrence of the char (not all)
                String tail = splice(s, i);
                for (String tailPerms: getPermutations(tail))
                {

                    if (!output.contains(c + tailPerms)) {
                        output.add(c + tailPerms);
                    }
                }
            }
        }
        return output;
    }

    public String splice(String s, int i) {
        String newString = s.substring(0, i).concat(s.substring(i + 1));
        return newString;
    }


}
