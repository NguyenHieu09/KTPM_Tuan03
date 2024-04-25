package vn.edu.iuh.fit.jdepen;

import jdepend.framework.JavaPackage;
import jdepend.framework.PackageFilter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class JDependExample {
    public static void main(String[] args) throws IOException {
        jdepend.framework.JDepend d = new jdepend.framework.JDepend();
        d.addDirectory("E:\\HK1_n4\\www\\www_week07_NguyenThiTrungHieu_20020381");

        Collection<JavaPackage> cols = d.analyze();
        cols.forEach(pkg -> {
            System.out.printf("Pakage %s, Ca= %d; Ce=%d;\n", pkg.getName(),
                    pkg.getAfferents().size(), pkg.getEfferents().size());
        });

        try (PrintWriter out = new PrintWriter(new FileOutputStream("results.xml")))
        {

            jdepend.xmlui.JDepend xml = new jdepend.xmlui.JDepend(out);
            xml.addDirectory("E:\\HK1_n4\\www\\www_week07_NguyenThiTrungHieu_20020381");

            PackageFilter f = PackageFilter.all();
//            f.including("vn.edu.iuh");
            f.accept("vn.edu");
            f.excluding("org");
            xml.setFilter(f);

            xml.analyze();
        }
    }
}
