package uk.ac.ebi.pride.jmztab.utils;

import org.junit.Before;
import org.junit.Test;
import uk.ac.ebi.pride.jmztab.utils.errors.MZTabErrorType;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author qingwei
 * @since 21/02/13
 */
public class MZTabFileParserRun {

    private URI uri;

    @Before
    public void SetUp() throws URISyntaxException {
        uri = (MZTabFileParserRun.class.getClassLoader().getResource("testset/Score_MzTab.mztab")) != null ?
                (MZTabFileParserRun.class.getClassLoader().getResource("testset/Score_MzTab.mztab")).toURI(): null;
    }

    public void check(File tabFile) throws Exception {
        System.out.println("checking " + tabFile.getName() + " with Error level");
        MZTabFileParser mzTabFileParser = new MZTabFileParser(tabFile, System.out, MZTabErrorType.Level.Error);
        mzTabFileParser.getMZTabFile();

        System.out.println("Finish!");
        System.out.println();
    }

    @Test
    public void testMztabValidator() throws IOException {
        if(uri == null){
            System.out.println("Error parsing the corresponding file");
        }
        File tabFile = new File(uri);
        System.out.println("checking " + tabFile.getName() + " with Error level");
        MZTabFileParser mzTabFileParser = new MZTabFileParser(tabFile, System.out, MZTabErrorType.Level.Error);
        mzTabFileParser.getMZTabFile();

        System.out.println("Finish!");
        System.out.println();
    }



    public static void main(String[] args) throws Exception {
        MZTabFileParserRun run = new MZTabFileParserRun();

        File inDir = new File("temp");
        for (File tabFile : inDir.listFiles()) {
            if(tabFile.isFile() && !tabFile.isHidden()){
                System.out.println(tabFile.getAbsolutePath());
                run.check(tabFile);
            }
        }
    }

}
