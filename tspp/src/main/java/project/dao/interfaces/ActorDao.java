package project.dao.interfaces;

import project.model.*;

import java.util.List;

/**
 * Created by Elvira on 12.03.2017.
 */
public interface ActorDao {

    public void addActor(ActorEntity actor);
    public void updateActor(ActorEntity actor);
    public ActorEntity getActorById(Integer idActor);
    public List<ActorEntity> listActors();
    public void deleteActor(ActorEntity actor);
}
