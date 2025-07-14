class TextState {
    String content;
    TextState prev, next;

    TextState(String content) {
        this.content = content;
    }
}

class TextEditor {
    TextState current;
    int size = 0;
    final int MAX_HISTORY = 10;

    void addState(String newText) {
        TextState newState = new TextState(newText);
        if (current != null) {
            current.next = newState;
            newState.prev = current;
        }
        current = newState;
        trimHistory();
        size++;
    }

    void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
        }
    }

    void redo() {
        if (current != null && current.next != null) {
            current = current.next;
        }
    }

    void displayCurrent() {
        if (current != null) {
            System.out.println("Current State: " + current.content);
        } else {
            System.out.println("No text available.");
        }
    }

    void trimHistory() {
        int count = 0;
        TextState temp = current;
        while (temp != null && count < MAX_HISTORY) {
            temp = temp.prev;
            count++;
        }
        if (temp != null && temp.prev != null) {
            temp.prev.next = null;
            temp.prev = null;
        }
    }
}

public class TextEditorHistory {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        editor.addState("Hello");
        editor.addState("Hello World");
        editor.addState("Hello World!");

        editor.displayCurrent();
        editor.undo();
        editor.displayCurrent();
        editor.undo();
        editor.displayCurrent();
        editor.redo();
        editor.displayCurrent();
    }
}
