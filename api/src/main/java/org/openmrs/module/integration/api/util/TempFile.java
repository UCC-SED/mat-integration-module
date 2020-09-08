/**
 *
 * UCC:George Yusto
 *
 */
package org.openmrs.module.integration.api.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class TempFile {
    protected final  Log log ;
    protected static Path baseDir = FileSystems.getDefault().getPath("/media/sf_mat_temp");
    protected static String prefix = "MAT";
    protected static String suffix = ".meth";
    protected static Path tmpFile = null;
    public TempFile() {
        this.log = LogFactory.getLog((Class)this.getClass());
    }
    public  String createTemp(String Data) {
            try {
                log.info((Object) "MAT:Temp file Init");
                    tmpFile = Files.createTempFile(baseDir, prefix, suffix);
                    BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFile.toFile()));
                    bw.write(Data);
                    bw.close();

                log.info((Object) "MAT:Temp file Created");
            } catch (IOException e) {
            }
            return "success";
        }
}