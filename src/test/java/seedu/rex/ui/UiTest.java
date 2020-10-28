package seedu.rex.ui;

import org.junit.jupiter.api.Test;
import seedu.rex.data.exception.RexException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {

    @Test
    void readCommand_hello_returnsHelloString() throws RexException {

        String input = "hello";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals("hello", new Ui().readCommand());
    }
}