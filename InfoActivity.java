package ngame.kinoman;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class InfoActivity extends ActionBarActivity {

    private TextView textView;
    private TextView textViewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        textView = (TextView)findViewById(R.id.textViewInfo);
        textViewAll = (TextView)findViewById(R.id.textViewAll);

        if(getIntent().getIntExtra("InfoOrStatistics", 0) == 0)
        {
            textView.setText(R.string.info);
        }

        if(getIntent().getIntExtra("InfoOrStatistics", 0) == 1)
        {
            DataFilms dataFilms = (DataFilms)getApplicationContext();
            SharedPreferences mFilms;

            textView.setText(R.string.statistics);
            textViewAll.setText(R.string.all);
            int AllFilms = 0, AllFilmsG = 0;
            for(int i = 0; i < dataFilms.countGenres(); i++)
            {
                mFilms = getSharedPreferences(dataFilms.getGenre(i), Context.MODE_PRIVATE);
                AllFilmsG += mFilms.getInt("count", 0);
                AllFilms += dataFilms.countFilmsToGenre(i);
            }
            textViewAll.setText(textViewAll.getText() + ": " + AllFilmsG + "/" + AllFilms + "\n" + "\n");

            for(int i = 0; i < dataFilms.countGenres(); i++)
            {
                String genre = getString(getResources().getIdentifier
                        (dataFilms.getGenre(i), "string", getPackageName()));
                mFilms = getSharedPreferences(dataFilms.getGenre(i), Context.MODE_PRIVATE);
                textViewAll.setText(textViewAll.getText() + genre + ": " +
                        mFilms.getInt("count", 0) + "/" + dataFilms.countFilmsToGenre(i) + "\n");
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickBackInfo(View view) {
        BackActivity();
    }

    @Override
    public void onBackPressed() {
        BackActivity();
    }

    void BackActivity()
    {
        Intent intent = new Intent(InfoActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
