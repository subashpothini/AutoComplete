package ru.mai.autocomplete;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteImplTest {
    @Test
    public void testGetObjects() throws Exception {
        AutoComplete<Integer> autoComplete = new AutoCompleteImpl<>();

        {
            autoComplete.addObject("privet mir", 0);

            {
                List<Result<Integer>> result = new ArrayList<>();
                result.add(new Result<>("privet mir", 0));

                Assert.assertEquals(result, autoComplete.getObjects("privet m"));
            }

            {
                List<Result<Integer>> result = new ArrayList<>();
                result.add(new Result<>("privet mir", 0));

                Assert.assertEquals(result, autoComplete.getObjects("mir pr"));
            }
        }
    }
}
