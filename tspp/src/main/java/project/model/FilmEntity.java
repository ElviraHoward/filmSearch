package project.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Elvira on 07.03.2017.
 */
@Entity
@Table(name = "film", schema = "db_tsp")
public class FilmEntity {
    private int idFilm;
    private String name;
    private String description;
    private int release_date;
    private int country;
    private int director;
    private int actor;
    private CountryEntity filmByCountry;
    private DirectorEntity filmByDirector;
    private ActorEntity filmByActor;

    @Id
    @GenericGenerator(name = "num", strategy = "increment")
    @GeneratedValue(generator = "num")
    @Column(name = "id_film", nullable = false)
    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "release_date", nullable = false)
    public int getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(int releaseDate) {
        this.release_date = releaseDate;
    }

    @Basic
    @Column(name = "country", nullable = false)
    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    @Basic
    @Column(name = "director", nullable = false)
    public int getDirector() {
        return director;
    }

    public void setDirector(int director) {
        this.director = director;
    }

    @Basic
    @Column(name = "actor", nullable = false)
    public int getActor() {
        return actor;
    }

    public void setActor(int actor) {
        this.actor = actor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmEntity that = (FilmEntity) o;

        if (idFilm != that.idFilm) return false;
        if (release_date != that.release_date) return false;
        if (country != that.country) return false;
        if (director != that.director) return false;
        if (actor != that.actor) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idFilm;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + release_date;
        result = 31 * result + country;
        result = 31 * result + director;
        result = 31 * result + actor;
        result = 31 * result + (filmByCountry != null ? filmByCountry.hashCode() : 0);
        result = 31 * result + (filmByDirector != null ? filmByDirector.hashCode() : 0);
        result = 31 * result + (filmByActor != null ? filmByActor.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return name;
    }


    @ManyToOne(cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
    @JoinColumn(name = "country", referencedColumnName = "id_country", nullable = false, insertable = false, updatable = false)
    public CountryEntity getFilmByCountry() {
        return filmByCountry;
    }

    public void setFilmByCountry(CountryEntity filmByCountry) {
        this.filmByCountry = filmByCountry;
    }

    @ManyToOne(cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
    @JoinColumn(name = "director", referencedColumnName = "id_director", nullable = false, insertable = false, updatable = false)

    public DirectorEntity getFilmByDirector() {
        return filmByDirector;
    }

    public void setFilmByDirector(DirectorEntity filmByDirector) {
        this.filmByDirector = filmByDirector;
    }

    @ManyToOne(cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
    @JoinColumn(name = "actor", referencedColumnName = "id_actor", nullable = false, insertable = false, updatable = false)
    public ActorEntity getFilmByActor() {
        return filmByActor;
    }
    public void setFilmByActor(ActorEntity filmByActor) {
        this.filmByActor = filmByActor;
    }
}
