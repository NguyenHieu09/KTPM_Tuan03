package vn.edu.iuh.fit.ckjm;

import gr.spinellis.ckjm.ClassMetrics;

import java.util.concurrent.CountDownLatch;

import java.io.File;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;


import gr.spinellis.ckjm.CkjmOutputHandler;

import gr.spinellis.ckjm.MetricsFilter;
public class MetricsFilterTest {

    public void test() throws Exception {
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicReference<ClassMetrics> ref = new AtomicReference<>();
        CkjmOutputHandler outputHandler = new CkjmOutputHandler() {
            @Override
            public void handleClass(String name, ClassMetrics c) {
                System.out.println("name: " + name + ", WMC: " + c.getWmc());
                System.out.println("name: " + name + ", LCOM: " + c.getLcom());
//           System.out.println("name: " + name + ", LCOM: " + c.*******());
                ref.set(c);
                latch.countDown();
            }
        };
        File f = new
                File("build/classes/java/main/vn/edu/iuh/fit/ckjm/MetricsFilterTest.class");
        MetricsFilter.runMetrics(new String[] { f.getAbsolutePath() },
                outputHandler, false);
        latch.await(1, TimeUnit.SECONDS);
    }

    public static void main(String[] args)throws Exception {
        new MetricsFilterTest().test();
    }
}


