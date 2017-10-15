package ngame.kinoman;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

public class FilmActivity extends ActionBarActivity {

    private EditText editTextFilm;
    private TextView textViewSuccess;
    private TextView textViewSuccessFilm;
    private Button buttonCheck;

    private int mFilmId;
    private int mGenreId;
    private DataFilms dataFilms;

    private final int scoreFilm = 10;

    private SharedPreferences mFilms;
    private SharedPreferences.Editor editor;
    private SharedPreferences mScores;
    private SharedPreferences.Editor editorScores;

    AlertDialog.Builder ad;

    private GridView gridView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);

        mFilmId = getIntent().getIntExtra("Film", 0);
        mGenreId = getIntent().getIntExtra("Genre", 0);
        dataFilms = (DataFilms)getApplicationContext();

        editTextFilm = (EditText)findViewById(R.id.editTextFilm);
        textViewSuccess = (TextView)findViewById(R.id.textViewSuccess);
        textViewSuccessFilm = (TextView)findViewById(R.id.textViewSuccessFilm);
        buttonCheck = (Button)findViewById(R.id.buttonCheck);

        context = this;

        mFilms = getSharedPreferences(dataFilms.getGenre(mGenreId), Context.MODE_PRIVATE);
        editor = mFilms.edit();
        mScores = getSharedPreferences("scoresFile", Context.MODE_PRIVATE);
        editorScores = mScores.edit();

        ScoreFunction();

        if(mFilms.getBoolean(Integer.toString(mFilmId), false)) {
            textViewSuccessFilm.setText(getResources().getString(dataFilms.getNameFilm(mGenreId, mFilmId)));
            textViewSuccessFilm.setVisibility(View.VISIBLE);
            textViewSuccess.setText("Успех!");
            editTextFilm.setVisibility(View.INVISIBLE);
            buttonCheck.setVisibility(View.INVISIBLE);
        }
        else {
            textViewSuccessFilm.setText("");
            textViewSuccessFilm.setVisibility(View.INVISIBLE);
            textViewSuccess.setText("");
            editTextFilm.setVisibility(View.VISIBLE);
            buttonCheck.setVisibility(View.VISIBLE);
        }

        gridView = (GridView)findViewById(R.id.gridViewFilm);
        gridView.setAdapter(new ImageAdapterFilm(context, mGenreId, mFilmId));
        gridView.setOnItemClickListener(gridViewOnItemClickListener);
    }

    private GridView.OnItemClickListener gridViewOnItemClickListener = new GridView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id)
        {
            if(id != 0 && !mFilms.getBoolean(Integer.toString(mFilmId) +  Long.toString(id), false)) {
                if (mScores.getInt("scores", 0) < 10)
                    showDialog(100);
                else
                    showDialog((int)id);
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_film, menu);
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

    public void onClickBackFilm(View view) {
        BackActivity();
    }

    public void onClickCheck(View view) {
        if(editTextFilm.getText().toString().compareToIgnoreCase
                (getResources().getString(dataFilms.getNameFilm(mGenreId, mFilmId))) == 0)
        {
            FilmGuessed();
        }
        else
        {
            textViewSuccess.setText(R.string.failure);
            textViewSuccess.setTextColor(Color.RED);
        }
    }

    void ScoreFunction()
    {
        TextView textViewScore = (TextView)findViewById(R.id.textViewScore);
        textViewScore.setText(Integer.toString(mScores.getInt("scores", 0)));
    }

    @Override
    public void onBackPressed() {
        BackActivity();
    }

    public void onClickPerform(View view) {
        if(!mFilms.getBoolean(Integer.toString(mFilmId), false)) {
            if (mScores.getInt("scores", 0) < 50)
                showDialog(100);
            else
                showDialog(50);
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        int id_question = 0;
        final int prev_id = id;
        switch (id)
        {
            case 50:
                id_question = R.string.perform;
                break;
            case 100:
                id_question = R.string.buy;
                break;
            default:
                id_question = R.string.open;
                break;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(id_question))
                .setCancelable(false)
                .setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                if (prev_id == 50) {
                                    editorScores.putInt("scores", mScores.getInt("scores", 0) - 50);
                                    editorScores.commit();

                                    FilmGuessed();
                                }

                                if (prev_id < 4) {
                                    editor.putBoolean(Integer.toString(mFilmId) + Long.toString(prev_id), true);
                                    editor.commit();

                                    editorScores.putInt("scores", mScores.getInt("scores", 0) - scoreFilm);
                                    editorScores.commit();

                                    ScoreFunction();

                                    gridView.setAdapter(new ImageAdapterFilm(context, mGenreId, mFilmId));
                                }
                                if (prev_id == 100) {

                                }
                                dialog.cancel();
                            }
                        })
                .setNegativeButton(R.string.no,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                            }
                        });
        return builder.create();

    }

    void FilmGuessed()
    {
        editor.putBoolean(Integer.toString(mFilmId), true);
        editor.putInt("count", mFilms.getInt("count", 0) + 1);
        for(int i = 1; i < 4; i++)
            editor.putBoolean(Integer.toString(mFilmId) + i, true);
        gridView.setAdapter(new ImageAdapterFilm(context, mGenreId, mFilmId));
        editor.commit();

        editorScores.putInt("scores", mScores.getInt("scores", 0) + scoreFilm);
        editorScores.commit();
        ScoreFunction();

        textViewSuccessFilm.setText(getResources().getString(dataFilms.getNameFilm(mGenreId, mFilmId)));
        textViewSuccessFilm.setVisibility(View.VISIBLE);

        textViewSuccess.setText(R.string.success);
        textViewSuccess.setTextColor(Color.GREEN);
        editTextFilm.setVisibility(View.INVISIBLE);
        buttonCheck.setVisibility(View.INVISIBLE);
    }

    void BackActivity()
    {
        Intent intent = new Intent(FilmActivity.this, FilmsActivity.class);
        intent.putExtra("Genre", mGenreId);
        startActivity(intent);
        finish();
    }

    public void onClickAdd(View view) {
        showDialog(100);
    }
}
