package ru.mai.autocomplete;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StringSpliterImplTest {
    @Test
    public void testSplit() throws Exception {
        {
            StringSpliter spliter = new StringSpliterImpl(" ");
            List<String> result = new ArrayList<>();
            result.add("q");
            result.add("w");

            Assert.assertEquals(result, spliter.split("    q    w    "));
        }

        {
            StringSpliter spliter = new StringSpliterImpl(" ,!.:;");
            List<String> result = new ArrayList<>();
            result.add("q");
            result.add("w");

            Assert.assertEquals(result, spliter.split("    q!,  .;  .w,    !"));
        }

        {
            StringSpliter spliter = new StringSpliterImpl(" ,!.:;");
            List<String> result = new ArrayList<>();
            result.add("q");
            result.add("w");

            Assert.assertEquals(result, spliter.split("q w"));
        }
    }
}
