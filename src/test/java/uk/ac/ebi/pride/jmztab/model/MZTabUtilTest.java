package uk.ac.ebi.pride.jmztab.model;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.*;
import static uk.ac.ebi.pride.jmztab.model.MZTabUtils.*;

/**
 * @author qingwei
 * @since 09/07/13
 */
public class MZTabUtilTest {
    private static Logger logger = LoggerFactory.getLogger(MZTabUtilTest.class);

    @Test
    public void testParam() {

        Param param = new UserParam("Sou,rce", "Sigma-Aldrich, catalog #H4522, lot #043K0502");
        assertEquals(parseParam("[,,\"Sou,rce\", \"Sigma-Aldrich, catalog #H4522, lot #043K0502\"]"), param);
        logger.debug(param.toString());

        param = new UserParam("Source", "Sigma-Aldrich, [catalog #H4522, lot #043K0502]");
        assertEquals(parseParam("[,,Source, \"Sigma-Aldrich, [catalog #H4522, lot #043K0502]\"]"), param);
        logger.debug(param.toString());

        param = new UserParam("\"Sou,rce\"", "\"Sigma-Aldrich, [catalog #H4522, lot #043K0502]\"");
        assertEquals(parseParam("[,,\"Sou,rce\", \"Sigma-Aldrich, [catalog #H4522, lot #043K0502]\"]"), param);
        logger.debug(param.toString());

        param = new UserParam("Source", "Sigma-Aldrich, ,catalog #H4522, lot #043K0502,");
        assertEquals(parseParam("[,,Source, \"Sigma-Aldrich, ,catalog #H4522, lot #043K0502,\"]"), param);
        logger.debug(param.toString());

        assertTrue(parseParam("[PRIDE,PRIDE:0000114,iTRAQ reagent 114,]") instanceof CVParam);
        assertTrue(parseParam("[, ,tolerance,0.5]") instanceof UserParam);
        assertNull(parseParam("[, ,,0.5]"));
        assertNull(parseParam("null"));

        assertEquals(parseParam("[PRIDE,PRIDE:0000114,\"N,O-diacetylated L-serine\",]").getName(),"N,O-diacetylated L-serine");
        logger.debug(String.valueOf(parseParam("[PRIDE,PRIDE:0000114,\"N[12],O-diacetylated L-serine\",]")));
    }

    @Test
    public void testParamSquareBrackets() {

        Param param = new UserParam("Some parameter", "[..,.]");
        logger.debug(param.getValue());
        assertEquals(parseParam("[,,Some parameter,\"[..,.]\"]"), param);
        logger.debug(param.toString());

        param = new UserParam("Some parameter", "\"[...]\"");
        logger.debug(param.getValue());
        assertEquals(parseParam("[,,Some parameter,\"[...]\"]"), param);
        logger.debug(param.toString());

        param = new UserParam("Some parameter", "\"[...]\"");
        logger.debug(param.getValue());
        assertEquals(parseParam("[,,Some parameter,[...]]"), param);
        logger.debug(param.toString());

        param = new UserParam("Some parameter", "[...]");
        logger.debug(param.getValue());
        assertEquals(parseParam("[,,Some parameter,[...]]"), param);
        logger.debug(param.toString());

        param = new UserParam("Some parameter", "[...]");
        logger.debug(param.getValue());
        assertEquals(parseParam("[,,Some parameter,\"[...]\"]"), param);
        logger.debug(param.toString());
    }


    @Test
    public void testOnlyNameParam() {

        Param param = new UserParam("Some parameter", null);
        assertEquals(parseParam("[,,Some parameter,]"), param);
        logger.debug(param.toString());

        param = new UserParam("Some parameter", "");
        assertEquals(parseParam("[,,Some parameter,]"), param);
        logger.debug(param.toString());

        param = new UserParam("Some parameter", " ");
        assertEquals(parseParam("[,,Some parameter, ]"), param);
        logger.debug(param.toString());

        param = new UserParam("Some parameter", "  ");
        assertEquals(parseParam("[,,Some parameter,  ]"), param);
        logger.debug(param.toString());
    }

    @Test
    public void testDouble() {
        Double value;
        value = parseDouble("NaN");
        assertEquals(value, Double.NaN, 0.0);
    }

    @Test
    public void testParamList() {
        SplitList<Param> paramList;

        StringBuilder sb = new StringBuilder();

        paramList = parseParamList(sb.toString());
        assertEquals(0, paramList.size());

        sb.append("[MS,MS:1001207,Mascot,]");
        paramList = parseParamList(sb.toString());
        assertEquals(1, paramList.size());
        assertTrue(paramList.get(0) instanceof CVParam);

        sb.append("|[, ,name,0.5]");
        paramList = parseParamList(sb.toString());
        assertEquals(2, paramList.size());
        assertTrue(paramList.get(1) instanceof UserParam);

        sb.append("|[MS,MS:1001171,Mascot:score,30]");
        paramList = parseParamList(sb.toString());
        assertEquals(3, paramList.size());
        assertTrue(paramList.get(2) instanceof CVParam);

        sb.append("|[, ,,0.5]");
        paramList = parseParamList(sb.toString());
        assertEquals(0, paramList.size());

        sb = new StringBuilder();
        sb.append("null");
        paramList = parseParamList(sb.toString());
        assertEquals(0, paramList.size());
    }

    @Test
    public void testGOTermList() {
        SplitList<String> goList;

        StringBuilder sb = new StringBuilder();
        goList = parseGOTermList(sb.toString());
        assertEquals(0, goList.size());

        sb.append("GO:0005515");
        goList = parseGOTermList(sb.toString());
        assertEquals(1, goList.size());

        sb.append(",GO:0008270, GO:0043167");
        goList = parseGOTermList(sb.toString());
        assertEquals(3, goList.size());

        sb.append(",GO:000, 8270, GO:0043167");
        goList = parseGOTermList(sb.toString());
        assertEquals(0, goList.size());
    }

    @Test
    public void testDoubleList() {
        SplitList<Double> valueList;

        StringBuilder sb = new StringBuilder();
        valueList = parseDoubleList(sb.toString());
        assertEquals(0, valueList.size());

        sb.append("2.3");
        valueList = parseDoubleList(sb.toString());
        assertEquals(1, valueList.size());

        sb.append("| 10.2|11.5");
        valueList = parseDoubleList(sb.toString());
        assertEquals(3, valueList.size());

        sb.append("|1|2|3");
        valueList = parseDoubleList(sb.toString());
        assertEquals(6, valueList.size());

        sb.append("1000,100");
        valueList = parseDoubleList(sb.toString());
        assertEquals(0, valueList.size());
    }

    @Test
    public void testPublication() {
        SplitList<PublicationItem> items;

        StringBuilder sb = new StringBuilder();
        items = parsePublicationItems(sb.toString());
        assertEquals(0, items.size());

        sb.append("pubmed:21063943|doi:10.1007/978-1-60761-987-1_6");
        items = parsePublicationItems(sb.toString());
        assertTrue(items.toString().contains("pubmed:21063943|doi:10.1007/978-1-60761-987-1_6"));

        sb.append("| doi:1231-60761-987-1_6");
        items = parsePublicationItems(sb.toString());
        assertEquals(3, items.size());

        sb.append("|cnki:1231-60761-987-1_6");
        items = parsePublicationItems(sb.toString());
        assertEquals(0, items.size());
    }

    @Test
    public void testModification() {
        List<Modification> modList;
        Modification modification;

        StringBuilder sb = new StringBuilder();

        sb.append("3-MOD:00412");
        modList = parseModificationList(Section.Protein, sb.toString());
        assertEquals(1, modList.size());
        modification = modList.get(0);
        assertTrue(modification.getPositionMap().containsKey(3));
        assertEquals("MOD", modification.getType().name());
        assertEquals("00412", modification.getAccession());
        logger.debug(sb.toString());

        sb.append(",3|4-UNIMOD:00412");
        modList = parseModificationList(Section.Protein, sb.toString());
        assertEquals(2, modList.size());
        modification = modList.get(1);
        assertTrue(modification.getPositionMap().containsKey(3));
        assertTrue(modification.getPositionMap().containsKey(4));
        assertEquals("UNIMOD", modification.getType().name());
        assertEquals("00412", modification.getAccession());
        logger.debug(sb.toString());

        sb.append(",3[MS, MS:100xxxx, Probability Score Y, 0.8]|4[MS, MS:100xxxx, Probability Score Y, 0.2]-MOD:00412");
        modList = parseModificationList(Section.Protein, sb.toString());
        assertEquals(3, modList.size());
        modification = modList.get(2);
        assertTrue(modification.getPositionMap().containsKey(3));
        assertTrue(modification.getPositionMap().get(3).getValue().contains("0.8"));
        assertTrue(modification.getPositionMap().containsKey(4));
        assertTrue(modification.getPositionMap().get(4).getValue().contains("0.2"));
        assertEquals("MOD", modification.getType().name());
        assertEquals("00412", modification.getAccession());
        logger.debug(sb.toString());

        sb.append(",4-[MS, MS:1001524, fragment neutral loss, 63.998285]");
        modList = parseModificationList(Section.Protein, sb.toString());
        assertEquals(4, modList.size());
        modification = modList.get(3);
        assertTrue(modification.getPositionMap().containsKey(4));
        assertTrue(modification.getNeutralLoss().getName().contains("fragment neutral loss"));
        logger.debug(sb.toString());

        sb.append(",CHEMMOD:+159.93");
        modList = parseModificationList(Section.Protein, sb.toString());
        assertEquals(5, modList.size());
        modification = modList.get(4);
        assertTrue(modification.getPositionMap().isEmpty());
        assertEquals("CHEMMOD", modification.getType().name());
        assertEquals("+159.93", modification.getAccession());
        logger.debug(sb.toString());

        sb.append(",3-SUBST:R");
        modList = parseModificationList(Section.Protein, sb.toString());
        assertEquals(6, modList.size());
        modification = modList.get(5);
        assertTrue(modification.getPositionMap().containsKey(3));
        assertEquals("SUBST", modification.getType().name());
        assertEquals("R", modification.getAccession());
        logger.debug(sb.toString());

        sb.append(",3-MOD:00412");
        modList = parseModificationList(Section.Protein, sb.toString());
        assertEquals(7, modList.size());
        logger.debug(sb.toString());

        sb.append(",3|4-UNIMOD:00412");
        modList = parseModificationList(Section.Protein, sb.toString());
        assertEquals(8, modList.size());
        logger.debug(sb.toString());

        sb.append(",CHEMMOD:+NH4-H");
        modList = parseModificationList(Section.Protein, sb.toString());
        assertEquals(9, modList.size());
        modification = modList.get(8);
        assertTrue(modification.getPositionMap().isEmpty());
        assertEquals("CHEMMOD", modification.getType().name());
        assertEquals("+NH4-H", modification.getAccession());
        logger.debug(sb.toString());

        assertEquals(sb.toString(), modList.toString());

        // test no modification.
        Modification mod = parseModification(Section.Protein, "0");
        assertTrue(mod != null && mod.toString().equals("0"));
        modList = parseModificationList(Section.Protein, "0");
        assertEquals(1, modList.size());
    }
}
