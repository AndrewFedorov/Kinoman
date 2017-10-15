package ngame.kinoman;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


public class FilmsActivity extends ActionBarActivity {

    private int mGenreId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);

        mGenreId = getIntent().getIntExtra("Genre", 0);

        GridView gridView = (GridView)findViewById(R.id.gridViewFilms);
        gridView.setAdapter(new ImageAdapter(this, mGenreId));
        gridView.setOnItemClickListener(gridViewOnItemClickListener);

        GridView gridView1 = (GridView)findViewById(R.id.gridViewFilms1);
        gridView1.setAdapter(new ImageAdapter1(this, mGenreId));
        gridView1.setOnItemClickListener(gridViewOnItemClickListener);
    }

    private GridView.OnItemClickListener gridViewOnItemClickListener = new GridView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id)
        {
            Intent intent = new Intent(FilmsActivity.this, FilmActivity.class);
            intent.putExtra("Film", (int)id);
            intent.putExtra("Genre", mGenreId);
            startActivity(intent);
            finish();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_comedy, menu);
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

    public void onClickBackFilms(View view) {
        BackActivity();
    }

    @Override
    public void onBackPressed() {
        BackActivity();
    }

    void BackActivity()
    {
        Intent intent = new Intent(FilmsActivity.this, GenreActivity.class);
        startActivity(intent);
        finish();
    }
}
