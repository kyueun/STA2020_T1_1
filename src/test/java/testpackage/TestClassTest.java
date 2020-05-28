package testpackage;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestClassTest {

    @Test
    public void setValue() {
        TestClass T = new TestClass();

        T.setValue(2020);

        assertEquals(2020, T.getValue());
    }
}