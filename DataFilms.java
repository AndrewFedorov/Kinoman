package ngame.kinoman;

import android.app.Application;

public class DataFilms extends Application {

    private String[] Genres = {
            "anime", "action", "drama", "comedy",
            "TVseries", "cartoon", "horror", "fantastic", "thriller"
    };

    private String[][] NameImages = {
            {//anime

            },
            {//action
                "gladiator", "thedarkknight", "thedarkknightrises", "transformers"
            },
            {//drama
                "thewolfofwallstreet", "bigfish", "touchingthevoid", "silverliningsplaybook",
                    "octobersky", "theshawshankredemption", "wideawake", "theworldsfastestindian",
                    "thesocialnetwork", "birdman", "barfuss", "thepursuitofhappyness"
            },
            {//comedy
                "letsbecops", "snatch"
            },
            {//TVseries

            },
            {//cartoon
                "surfsup"
            },
            {//horror
                "mist", "saw"
            },
            {//fantastic
                "waroftheworlds", "worldwarz", "mrnobody", "ironman", "ironman2", "ironman3",
                    "interstellar", "thematrix", "inception", "jumper", "f2012", "avatar", "intime"
            },
            {//thriller
                "fightclub", "gonegirl", "shutterisland", "thedevilsadvocate", "danslamaison"
            },
    };

    private int[][] NameFilms = {
            {//anime

            },
            {//action
                R.string.gladiator, R.string.thedarkknight, R.string.thedarkknightrises, R.string.transformers
            },
            {//drama
                R.string.thewolfofwallstreet, R.string.bigfish, R.string.touchingthevoid, R.string.silverliningsplaybook,
                    R.string.octobersky, R.string.theshawshankredemption, R.string.wideawake, R.string.theworldsfastestindian,
                    R.string.thesocialnetwork, R.string.birdman, R.string.barfuss, R.string.thepursuitofhappyness
            },
            {//comedy
                    R.string.letsbecops, R.string.snatch
            },
            {//TVseries

            },
            {//cartoon
                R.string.surfsup
            },
            {//horror
                    R.string.mist, R.string.saw
            },
            {//fantastic
                R.string.waroftheworlds, R.string.worldwarz, R.string.mrnobody, R.string.ironman, R.string.ironman2, R.string.ironman3,
                    R.string.interstellar, R.string.thematrix, R.string.inception, R.string.jumper, R.string.f2012, R.string.avatar, R.string.intime

            },
            {//thriller
                R.string.fightclub, R.string.gonegirl, R.string.shutterisland, R.string.thedevilsadvocate, R.string.danslamaison
            },
    };

    public String getGenre(int id){
        return Genres[id];
    }

    public int countGenres(){
        return Genres.length;
    }

    public String getNameImage(int idGenre, int idFilm){
        return NameImages[idGenre][idFilm];
    }

    public int countFilmsToGenre(int idGenre){
        return NameImages[idGenre].length;
    }

    public int countAllFilms(){
        return NameImages.length;
    }

    public int getNameFilm(int idGenre, int idFilm){
        return NameFilms[idGenre][idFilm];
    }
}
