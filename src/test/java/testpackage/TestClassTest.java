package testpackage;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestClassTest {

    @Test
    public void setValue() {
        TestClass T = new TestClass();

        assertEquals(0, T.getValue());

        T.setValue(2020);

        assertEquals(2019, T.getValue());
    }
}