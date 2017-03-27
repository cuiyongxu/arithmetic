package org.openote.KMP;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.openote.StopWatch;

/**
 * KMP实现测试 及JAVA自带算法的比较
 * Created by cuiyongxu on 17/3/27.
 */
@Slf4j
public class KMPTest {

    StopWatch watch = null;

    @Before
    public void setup() {
        watch = StopWatch.create("KMP");
    }

    @Test
    public void execute() {
        int k = KMP.kmp("asdfhkasdsafabcabcdefasdfhasdf", "abcabcdef");
        log.info("{}", k);
        watch.logSlow(0);
    }

    @Test
    public void preProcessTest() {
        char[] chars = "abcabcdef".toCharArray(); //{'a', 'b', 'c', 'd', 'e', 'f', 'g'};
        int[] ints = KMP.preProcess(chars);
        log.info("ints {}", ints);
        watch.logSlow(0);
    }

    @Test
    public void JVMImpl() {
        log.info("{}", "asdfhkasdsafabcabcdefasdfhasdf".indexOf("abcabcdef"));
        watch.logSlow(0);
    }

    @Test
    public void JVMCode() {
        char[] source = "asdfhkasdsafabcabcdefasdfhasdf".toCharArray();
        char[] target = "abcabcdef".toCharArray();

        int sourceOffset = 0, targetOffset = 0, fromIndex = 0;
        int sourceCount = source.length;
        int targetCount = target.length;

        if (fromIndex >= sourceCount) {
            log.info("{}", (targetCount == 0 ? sourceCount : -1));
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (targetCount == 0) {
            log.info("{}", fromIndex);
        }

        char first = target[targetOffset];
        int max = sourceOffset + (sourceCount - targetCount);

        for (int i = sourceOffset + fromIndex; i <= max; i++) {
            /* Look for first character. */
            if (source[i] != first) {
                while (++i <= max && source[i] != first) ;
            }

            /* Found first character, now look at the rest of v2 */
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = targetOffset + 1; j < end && source[j]
                        == target[k]; j++, k++)
                    ;

                if (j == end) {
                    /* Found whole string. */
                    log.info("指针: {}", i - sourceOffset);
                }
            }
        }
        watch.logSlow(0);
    }
}
