package com.lealcy.wordpermutation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected WordPermutation wp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wp = new WordPermutation(this);
    }

    public void showDictionary(View v)
    {
        Intent i = new Intent(getBaseContext(), DictionaryActivity.class);
        startActivity(i);
    }

    public void search(View v)
    {

        String s = ((EditText)findViewById(R.id.searchText)).getText().toString();
        int minLength = Integer.parseInt(((EditText)findViewById(R.id.minLength)).getText().toString());
        ArrayList<String> perms = wp.getPermutations(s);
        ArrayList<String> foundWords = new ArrayList<String>();
        for (int i = minLength; i <= s.length(); i++) {
            for (String perm : perms) {
                String word = perm.substring(0, i);
                if (!foundWords.contains(word) && wp.containsWord(word)) {
                    foundWords.add(word);
                }
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, foundWords);
        MainActivity.this.setResultAdapter(adapter);
    }

    public final void setResultAdapter(ArrayAdapter<String> adapter)
    {
        ListView resultListView = (ListView)findViewById(R.id.resultListView);
        resultListView.setAdapter(adapter);
    }
}
