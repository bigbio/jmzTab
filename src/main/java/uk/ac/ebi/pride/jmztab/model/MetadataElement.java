package uk.ac.ebi.pride.jmztab.model;

/**
 * Define all elements used in metadata.
 *
 * @author qingwei
 * @since 23/05/13
 */
public enum MetadataElement {
    MZTAB                             ("mzTab"),
    TITLE                             ("title"),
    DESCRIPTION                       ("description"),
    SAMPLE_PROCESSING                 ("sample_processing"),
    INSTRUMENT                        ("instrument"),
    SOFTWARE                          ("software"),
    PROTEIN_SEARCH_ENGINE_SCORE       ("protein_search_engine_score"),
    PEPTIDE_SEARCH_ENGINE_SCORE       ("peptide_search_engine_score"),
    PSM_SEARCH_ENGINE_SCORE           ("psm_search_engine_score"),
    SMALLMOLECULE_SEARCH_ENGINE_SCORE ("smallmolecule_search_engine_score"),
    FALSE_DISCOVERY_RATE              ("false_discovery_rate"),
    PUBLICATION                       ("publication"),
    CONTACT                           ("contact"),
    URI                               ("uri"),
    FIXED_MOD                         ("fixed_mod"),
    VARIABLE_MOD                      ("variable_mod"),
    QUANTIFICATION_METHOD             ("quantification_method"),
    PROTEIN                           ("protein"),
    PEPTIDE                           ("peptide"),
    SMALL_MOLECULE                    ("small_molecule"),
    MS_RUN                            ("ms_run"),
    CUSTOM                            ("custom"),
    SAMPLE                            ("sample"),
    ASSAY                             ("assay"),
    STUDY_VARIABLE                    ("study_variable"),
    CV                                ("cv"),
    COLUNIT                           ("colunit");

    private String name;

    MetadataElement(String name) {
        this.name = name;
    }

    /**
     * @return element name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return element name.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Find element by name with case-insensitive match. If not find, return null.
     */
    public static MetadataElement findElement(String name) {
        if (name == null) {
            return null;
        }

        MetadataElement element;
        try {
            element = MetadataElement.valueOf(name.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            element = null;
        }

        return element;
    }
}
