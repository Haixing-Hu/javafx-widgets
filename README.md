# javafx-widgets

This project provides some useful components for JavaFX.

## Widgets

Currently the following widgets or components are supports:

* `Action`: An action can be used to separate functionality and state from a control. For example, if you have two or more controls that perform the same function and share the same state (enable, visible, select, graphic, text, style, etc), e.g., one in a `Menu` and another on a `ToolBar`, consider using a `Action` object to implement the function.
* `ActionGroup`: It is a special kind of `Action`, which could contains sub-actions. The button created from an `ActionGroup` is a `MenuButton` or `SplitMenuButton`; and the menu item created from an `ActionGroup` is a `Menu`.
* `ActionManager`: It could be used to manage all the actions in an application, and it provides convenient functions to access the properties of actions and create `MenuBar` or `ToolBar` from actions.
* `LabelPane`: It is a very simple `Pane` which displays a text label on the center. It is usually used for testing layout.
* `SplitPaneEx`: It is an extension of `SplitPane`, with the additional functions to hide or show its children.

More widgets will be added when I need them :-)

## Dependency

The project depends on the following projects of mine:

* [pom-root](https://github.com/Haixing-Hu/pom-root)
* [Java-Commons](https://github.com/Haixing-Hu/commons)

## Build

1. Checks out the codes of this project and all its depended projects;
2. Build the depended projects in the order of above (**building order is important!**).
3. Build this project.
4. All the projects are managed by [maven](http://maven.apache.org/), so the building is as easy as typing `mvn clean install`.
5. By the way, since this project is for the JavaFX 2.0, you should install and configure the JDK 8.0 or above before you play with it.