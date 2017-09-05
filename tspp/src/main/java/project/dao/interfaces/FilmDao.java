package project.dao.interfaces;

import project.model.*;

import java.util.List;

/**
 * Created by Elvira on 12.03.2017.
 */
public interface FilmDao {
    public void addFilm(FilmEntity film);
    public void updateFilm(FilmEntity film);
    public FilmEntity getFilmById(Integer idFilm);
    public List<FilmEntity> listFilms();
    public void deleteFilm(FilmEntity film);
}
