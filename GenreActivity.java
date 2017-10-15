package ngame.kinoman;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class GenreActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);

        SharedPreferences mFilms;

        Button buttonGenreAnime = (Button) findViewById(R.id.buttonAnime);
        Button buttonGenreAction = (Button) findViewById(R.id.buttonAction);
        Button buttonGenreDrama = (Button) findViewById(R.id.buttonDrama);
        Button buttonGenreComedy = (Button) findViewById(R.id.buttonComedy);
        Button buttonGenreTVseries = (Button) findViewById(R.id.buttonTVSeries);
        Button buttonGenreCartoon = (Button) findViewById(R.id.buttonCartoon);
        Button buttonGenreHorror = (Button) findViewById(R.id.buttonHorror);
        Button buttonGenreFantastic = (Button) findViewById(R.id.buttonFantastic);
        Button buttonGenreThriller = (Button) findViewById(R.id.buttonThriller);

        DataFilms dataFilms = (DataFilms)getApplicationContext();

        mFilms = getSharedPreferences("anime", Context.MODE_PRIVATE);
        buttonGenreAnime.setText(buttonGenreAnime.getText() + "\n" +
                mFilms.getInt("count", 0) + "/" + dataFilms.countFilmsToGenre(0));

        mFilms = getSharedPreferences("action", Context.MODE_PRIVATE);
        buttonGenreAction.setText(buttonGenreAction.getText() + "\n" +
                mFilms.getInt("count", 0) + "/" + dataFilms.countFilmsToGenre(1));

        mFilms = getSharedPreferences("drama", Context.MODE_PRIVATE);
        buttonGenreDrama.setText(buttonGenreDrama.getText() + "\n" +
                mFilms.getInt("count", 0) + "/" + dataFilms.countFilmsToGenre(2));

        mFilms = getSharedPreferences("comedy", Context.MODE_PRIVATE);
        buttonGenreComedy.setText(buttonGenreComedy.getText() + "\n" +
                mFilms.getInt("count", 0) + "/" + dataFilms.countFilmsToGenre(3));

        mFilms = getSharedPreferences("TVseries", Context.MODE_PRIVATE);
        buttonGenreTVseries.setText(buttonGenreTVseries.getText() + "\n" +
                mFilms.getInt("count", 0) + "/" + dataFilms.countFilmsToGenre(4));

        mFilms = getSharedPreferences("cartoon", Context.MODE_PRIVATE);
        buttonGenreCartoon.setText(buttonGenreCartoon.getText() + "\n" +
                mFilms.getInt("count", 0) + "/" + dataFilms.countFilmsToGenre(5));

        mFilms = getSharedPreferences("horror", Context.MODE_PRIVATE);
        buttonGenreHorror.setText(buttonGenreHorror.getText() + "\n" +
                mFilms.getInt("count", 0) + "/" + dataFilms.countFilmsToGenre(6));

        mFilms = getSharedPreferences("fantastic", Context.MODE_PRIVATE);
        buttonGenreFantastic.setText(buttonGenreFantastic.getText() + "\n" +
                mFilms.getInt("count", 0) + "/" + dataFilms.countFilmsToGenre(7));

        mFilms = getSharedPreferences("thriller", Context.MODE_PRIVATE);
        buttonGenreThriller.setText(buttonGenreThriller.getText() + "\n" +
                mFilms.getInt("count", 0) + "/" + dataFilms.countFilmsToGenre(8));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_genre, menu);
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

    public void onClickBackGenre(View view) {
        BackActivity();
    }

    public void onClickGenre(View view) {
        Intent intent = new Intent(GenreActivity.this, FilmsActivity.class);

        switch (view.getId())
        {
            case R.id.buttonAnime:
                intent.putExtra("Genre", 0);
                break;
            case R.id.buttonAction:
                intent.putExtra("Genre", 1);
                break;
            case R.id.buttonDrama:
                intent.putExtra("Genre", 2);
                break;
            case R.id.buttonComedy:
                intent.putExtra("Genre", 3);
                break;
            case R.id.buttonTVSeries:
                intent.putExtra("Genre", 4);
                break;
            case R.id.buttonCartoon:
                intent.putExtra("Genre", 5);
                break;
            case R.id.buttonHorror:
                intent.putExtra("Genre", 6);
                break;
            case R.id.buttonFantastic:
                intent.putExtra("Genre", 7);
                break;
            case R.id.buttonThriller:
                intent.putExtra("Genre", 8);
                break;
        }

        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        BackActivity();
    }

    void BackActivity()
    {
        Intent intent = new Intent(GenreActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
