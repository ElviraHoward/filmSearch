package project.dao.interfaces;

import project.model.*;

import java.util.List;

/**
 * Created by Elvira on 12.03.2017.
 */
public interface DirectorDao {

    public void addDirector(DirectorEntity director);
    public void updateDirector(DirectorEntity director);
    public DirectorEntity getDirectorById(Integer idDirector);
    public List listDirectors();
    public void deleteDirector(DirectorEntity director);
}
