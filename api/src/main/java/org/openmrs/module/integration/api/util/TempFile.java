/**
 *
 * UCC:George Yusto
 *
 */
package org.openmrs.module.integration.api.util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class TempFile {
    private static final Log log = LogFactory.getLog(TempFile.class);
    protected static Path baseDir = FileSystems.getDefault().getPath("/media/sf_mat_temp");
    protected static String prefix = "MAT_";
    protected static String suffix = ".tmp";
    protected static Path tmpFile = null;

    public static String createTemp() {
        try {
            log.info((Object)"MAT:Temp file Init");
            tmpFile = Files.createTempFile(baseDir, prefix, suffix);
            BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFile.toFile()));
            bw.write("This is the temporary file content");
            bw.close();
            log.info((Object)"MAT:Temp file Created");
        } catch (IOException e) {
        }
        //DeleteTemp(tmpFile);

   return "success";
    }
   public static String DeleteTemp(Path file){
       try (BufferedWriter bw = Files.newBufferedWriter(tmpFile, StandardCharsets.UTF_8, StandardOpenOption.DELETE_ON_CLOSE)) {
           //simulate some operations with temp file until delete it
           Thread.sleep(10000);
           log.info((Object)"MAT:Temp file Deleted");
       } catch (IOException | InterruptedException e) {
       }
       return "Deleted";
   }
}