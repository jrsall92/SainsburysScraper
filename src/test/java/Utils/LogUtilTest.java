package Utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static Utils.LogUtil.setDebug;
import static org.junit.Assert.*;

public class LogUtilTest {

    private final ByteArrayOutputStream outLog = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errLog = new ByteArrayOutputStream();
    private final PrintStream originalSystemOut = System.out;
    private final PrintStream originalSystemErr = System.err;

    @Before
    public void setUp() {
        setDebug(true);
        System.setOut(new PrintStream(outLog));
        System.setErr(new PrintStream(errLog));
    }

    @Test
    public void log() {
        LogUtil.log("text1");
        assertEquals("text1\n", outLog.toString());
    }

    @Test
    public void logError() {
        LogUtil.logError("text1");
        assertEquals("text1\n", errLog.toString());
    }

    @Test
    public void testDebugFalse(){
        setDebug(false);
        LogUtil.log("text1");
        assertEquals("", outLog.toString());
    }

    @After
    public void tearDown(){
        System.setOut(originalSystemOut);
        System.setErr(originalSystemErr);
    }
}
