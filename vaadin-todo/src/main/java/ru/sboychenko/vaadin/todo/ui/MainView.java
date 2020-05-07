package ru.sboychenko.vaadin.todo.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

@Route
@CssImport("styles/shared-styles.css")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
public class MainView extends VerticalLayout {

    private final TodoListLayout todoList;
    private final AddLayout addLayout;

    @Autowired
    public MainView(TodoListLayout todoList, AddLayout addLayout) {
        this.todoList = todoList;
        this.addLayout = addLayout;

        setAlignItems(Alignment.CENTER);
        setWidth("100%");
        add(createLayout());
    }

    private VerticalLayout createLayout() {
        VerticalLayout layout = new VerticalLayout();
        layout.setMaxWidth("800px");
        layout.setAlignItems(Alignment.CENTER);

        addLayout.addButtonClickListener(click -> {
            todoList.save(addLayout.getTodo());
        });

        Button toggleButton = new Button("Dark", new Icon(VaadinIcon.MOON), click -> {
            ThemeList themeList = UI.getCurrent().getElement().getThemeList();
            if (themeList.contains(Lumo.DARK)) {
                themeList.remove(Lumo.DARK);
                click.getSource().setText("Dark");
                click.getSource().setIcon(new Icon(VaadinIcon.MOON));
            } else {
                themeList.add(Lumo.DARK);
                click.getSource().setText("Light");
                click.getSource().setIcon(new Icon(VaadinIcon.MOON_O));
            }
        });
        VerticalLayout header = new VerticalLayout(toggleButton, new H1("Vaadin TODO"));
        header.setAlignItems(Alignment.CENTER);
        header.setHorizontalComponentAlignment(Alignment.END, toggleButton);

        layout.add(
                header,
                addLayout,
                todoList
        );
        return layout;
    }
}
