package com.inspiro.views.admin;


import com.inspiro.data.entity.Person;
import com.inspiro.data.service.PersonServiceImp;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Admin")
@CssImport("./styles/views/admin/admin-view.css")
public class AdminView extends Div {

    private Grid<Person> grid;

    private final TextField firstName = new TextField("First name");
    private final TextField lastName = new TextField("Last name");
    private final TextField email = new TextField("Email");
    private final TextField phone = new TextField("Phone");
    private final DatePicker dateOfBirth = new DatePicker("Date of birth");
    private final TextField occupation = new TextField("Occupation");

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");

    private Binder<Person> binder;

    private Person person = new Person();

    private PersonServiceImp personServiceImp;

    public AdminView(@Autowired PersonServiceImp personServiceImp) {
//        setId("admin-view");
//        this.personServiceImp = personServiceImp;
//        // Configure Grid
//        grid = new Grid<>(Person.class);
//        grid.setColumns("firstName", "lastName", "email", "phone", "dateOfBirth", "occupation");
//        grid.getColumns().forEach(column -> column.setAutoWidth(true));
//      //  grid.setDataProvider(new CrudServiceDataProvider<Person, Void>(personServiceImp));
//        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
//        grid.setHeightFull();
//
//        // when a row is selected or deselected, populate form
//        grid.asSingleSelect().addValueChangeListener(event -> {
//            if (event.getValue() != null) {
//                Optional<Person> personFromBackend = personServiceImp.get(event.getValue().getId());
//                // when a row is selected but the data is no longer available, refresh grid
//                if (personFromBackend.isPresent()) {
//                    populateForm(personFromBackend.get());
//                } else {
//                    refreshGrid();
//                }
//            } else {
//                clearForm();
//            }
//        });
//
//        // Configure Form
//        binder = new Binder<>(Person.class);
//
//        // Bind fields. This where you'd define e.g. validation rules
//        binder.bindInstanceFields(this);
//
//        cancel.addClickListener(e -> {
//            clearForm();
//            refreshGrid();
//        });
//
//        save.addClickListener(e -> {
//            try {
//                if (this.person == null) {
//                    this.person = new Person();
//                }
//                binder.writeBean(this.person);
//                personServiceImp.update(this.person);
//                clearForm();
//                refreshGrid();
//                Notification.show("Person details stored.");
//            } catch (ValidationException validationException) {
//                Notification.show("An exception happened while trying to store the person details.");
//            }
//        });
//
//        SplitLayout splitLayout = new SplitLayout();
//        splitLayout.setSizeFull();
//
//        createGridLayout(splitLayout);
//        createEditorLayout(splitLayout);
//
//        add(splitLayout);
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setId("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setId("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        AbstractField[] fields = new AbstractField[] { firstName, lastName, email, phone, dateOfBirth, occupation };
        for (AbstractField field : fields) {
            ((HasStyle) field).addClassName("full-width");
        }
        formLayout.add(fields);
        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setId("button-layout");
        buttonLayout.setWidthFull();
        buttonLayout.setSpacing(true);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Person value) {
        this.person = value;
        binder.readBean(this.person);
    }
}
