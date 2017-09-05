package project;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.*;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import project.dao.factory.Factory;
import project.exceptions.UIException;
import project.model.ActorEntity;
import project.model.CountryEntity;
import project.model.DirectorEntity;
import project.model.FilmEntity;

import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@SpringUI
@Theme("valo")

public class MyUI extends UI{


    static final String ACTOR_TABLE_NAME = "ACTOR";

    static final String FILM_TABLE_NAME = "TOP OF FILMS";

    static final String DIRECTOR_TABLE_NAME = "DIRECTOR";

    static final String COUNTRY_TABLE_NAME = "COUNTRY";

    private static final BeanContainer<Long,ActorEntity> actorBeanContainer = new BeanContainer<>(ActorEntity.class);

    private static final BeanContainer<Long,FilmEntity> filmBeanContainer = new BeanContainer<>(FilmEntity.class);

    private static final BeanContainer<Long,DirectorEntity> directorBeanContainer = new BeanContainer<>(DirectorEntity.class);

    private static final BeanContainer<Long,CountryEntity> countryBeanContainer = new BeanContainer<>(CountryEntity.class);


    private final TabSheet tabsheet = new TabSheet();
    private Table actorTable;
    private Table filmTable;
    private Table directorTable;
    private Table countryTable;


    private final HorizontalLayout buttonSet = new HorizontalLayout();

    private final Button newItem = new Button("New");

    private final Button deleteItem = new Button("Delete");

    private final Button modifyItem = new Button("Modify");


    final transient Factory factory = Factory.getInstance();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        initTabSheet();
        initButtonSet();
        layout.addComponent(tabsheet);
        layout.addComponent(buttonSet);
        //layout.addComponents(label);
        setContent(layout);
    }

    private void initButtonSet(){

        buttonSet.addComponent(newItem);
        newItem.setStyleName(ValoTheme.BUTTON_PRIMARY);
        buttonSet.addComponent(deleteItem);
        deleteItem.setStyleName(ValoTheme.BUTTON_DANGER);
        buttonSet.addComponent(modifyItem);
        modifyItem.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        buttonSet.setSpacing(true);
        buttonSet.setMargin(true);
        this.newButtonListener();
        this.deleteButtonListener();
        this.modifyButtonListener();
    }


    private void initTabSheet(){
        fillFilmTable();
        filmTable.setSizeFull();
        VerticalLayout generalTable = new VerticalLayout();
        generalTable.setCaption(FILM_TABLE_NAME);
        generalTable.addComponent(filmTable);
        tabsheet.addTab(generalTable, FILM_TABLE_NAME);
        generalTable.setMargin(true);

        fillDirectorTable();
        directorTable.setSizeFull();
       // directorTable.setHeight("40%");
        VerticalLayout tab2 = new VerticalLayout();
        tab2.setCaption(DIRECTOR_TABLE_NAME);
        tab2.addComponent(directorTable);
        tabsheet.addTab(tab2, DIRECTOR_TABLE_NAME);
        tab2.setMargin(true);

        fillActorTable();
        actorTable.setSizeFull();
     //   actorTable.setHeight("60%");
        VerticalLayout tab3 = new VerticalLayout();
        tab3.setCaption(ACTOR_TABLE_NAME);
        tab3.addComponent(actorTable);
        tabsheet.addTab(tab3, ACTOR_TABLE_NAME);
        tab3.setMargin(true);

        fillCountryTable();
        countryTable.setSizeFull();
     //   countryTable.setHeight("60%");
        VerticalLayout tab4 = new VerticalLayout();
        tab4.setCaption(COUNTRY_TABLE_NAME);
        tab4.addComponent(countryTable);
        tabsheet.addTab(tab4, COUNTRY_TABLE_NAME);
        tab4.setMargin(true);
    }

    private void fillFilmTable(){
        try {
            filmBeanContainer.setBeanIdProperty("idFilm");
            filmBeanContainer.addAll(factory.getFilmDao().listFilms());
            filmTable = new Table(FILM_TABLE_NAME,filmBeanContainer);
            filmTable.setSelectable(true);
        }catch (Exception e) {
            throw new UIException("Error of fill table");
        }

    }

    private void fillDirectorTable(){
        try {
            directorBeanContainer.setBeanIdProperty("idDirector");
            directorBeanContainer.addAll(factory.getDirectorDao().listDirectors());
            directorTable = new Table(DIRECTOR_TABLE_NAME,directorBeanContainer);
            directorTable.setSelectable(true);
        }catch (Exception e) {
            throw new UIException("Error of fill table");
        }

    }

    private void fillActorTable(){
        try {
            actorBeanContainer.setBeanIdProperty("idActor");
            actorBeanContainer.addAll(factory.getActorDao().listActors());
            actorTable = new Table(ACTOR_TABLE_NAME,actorBeanContainer);
            actorTable.setSelectable(true);
        }catch (Exception e) {
            throw new UIException("Error of fill table");
        }

    }

    private void fillCountryTable(){
        try {
            countryBeanContainer.setBeanIdProperty("idCountry");
            countryBeanContainer.addAll(factory.getCountryDao().listCountries());
            countryTable = new Table(COUNTRY_TABLE_NAME,countryBeanContainer);
            countryTable.setSelectable(true);
        }catch (Exception e) {
            throw new UIException("Error of fill table");
        }

    }

    private void newButtonListener(){
        newItem.addClickListener((Button.ClickListener) clickEvent ->
                WindowDialog.addWindow(tabsheet.getSelectedTab().getCaption()));
    }

    private void deleteButtonListener(){
        deleteItem.addClickListener((Button.ClickListener) clickEvent -> {
            Integer ID;
            if(tabsheet.getSelectedTab().getCaption().equals(FILM_TABLE_NAME) && filmTable.getValue()!=null){
                ID = (Integer)filmTable.getValue();
                WindowDialog.deleteWindow(tabsheet.getSelectedTab().getCaption(), ID);
            }else if(tabsheet.getSelectedTab().getCaption().equals(DIRECTOR_TABLE_NAME) && directorTable.getValue()!=null){
                ID = (Integer) directorTable.getValue();
                WindowDialog.deleteWindow(tabsheet.getSelectedTab().getCaption(), ID);
            }else if (tabsheet.getSelectedTab().getCaption().equals(ACTOR_TABLE_NAME) && actorTable.getValue()!= null){
                ID = (Integer) actorTable.getValue();
                WindowDialog.deleteWindow(tabsheet.getSelectedTab().getCaption(), ID);
            }else if (tabsheet.getSelectedTab().getCaption().equals(COUNTRY_TABLE_NAME) && countryTable.getValue()!= null){
                ID = (Integer) countryTable.getValue();
                WindowDialog.deleteWindow(tabsheet.getSelectedTab().getCaption(), ID);
            }else{
                Notification.show("No row selected!", Notification.Type.ERROR_MESSAGE);
            }
        });
    }

    private void modifyButtonListener(){
        modifyItem.addClickListener((Button.ClickListener) clickEvent -> {
            Integer ID;
            if(tabsheet.getSelectedTab().getCaption().equals(FILM_TABLE_NAME) && filmTable.getValue()!=null){
                ID = (Integer)filmTable.getValue();
                WindowDialog.updateWindow(tabsheet.getSelectedTab().getCaption(), ID);
            }else if(tabsheet.getSelectedTab().getCaption().equals(DIRECTOR_TABLE_NAME) && directorTable.getValue()!=null){
                ID = (Integer) directorTable.getValue();
                WindowDialog.updateWindow(tabsheet.getSelectedTab().getCaption(), ID);
            }else if (tabsheet.getSelectedTab().getCaption().equals(ACTOR_TABLE_NAME) && actorTable.getValue()!= null){
                ID = (Integer) actorTable.getValue();
                WindowDialog.updateWindow(tabsheet.getSelectedTab().getCaption(), ID);
            }else if (tabsheet.getSelectedTab().getCaption().equals(COUNTRY_TABLE_NAME) && countryTable.getValue()!= null){
                ID = (Integer) countryTable.getValue();
                WindowDialog.updateWindow(tabsheet.getSelectedTab().getCaption(), ID);
            }else{
                Notification.show("No row selected!", Notification.Type.ERROR_MESSAGE);
            }
        });
    }

    public static BeanContainer<Long, FilmEntity> getFilmBeanContainer() {
        return filmBeanContainer;
    }

    public static BeanContainer<Long, DirectorEntity> getDirectorBeanContainer() {
        return directorBeanContainer;
    }

    public static BeanContainer<Long, ActorEntity> getActorBeanContainer() {
        return actorBeanContainer;
    }

    public static BeanContainer<Long, CountryEntity> getCountryBeanContainer() {
        return countryBeanContainer;
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyUI)) return false;
        if (!super.equals(o)) return false;

        MyUI that = (MyUI) o;

        if (tabsheet != null ? !tabsheet.equals(that.tabsheet) : that.tabsheet != null) return false;
        if (actorTable != null ? !actorTable.equals(that.actorTable) : that.actorTable != null) return false;
        if (filmTable != null ? !filmTable.equals(that.filmTable) : that.filmTable != null) return false;
        if (directorTable != null ? !directorTable.equals(that.directorTable) : that.directorTable != null)
            return false;
        if (countryTable != null ? !countryTable.equals(that.countryTable) : that.countryTable != null) return false;
        if (buttonSet != null ? !buttonSet.equals(that.buttonSet) : that.buttonSet != null) return false;
        if (!newItem.equals(that.newItem)) return false;
        if (!deleteItem.equals(that.deleteItem)) return false;
        return modifyItem.equals(that.modifyItem);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        return result;
    }
}
