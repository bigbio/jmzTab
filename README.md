## jmzTab

The jmzTab library provide reading and writing capabilities, as well as supporting the validation of mzTab and the conversion of PRIDE XML and mzIdentML files to mzTab. Currently, the library contains converter for
  * [PRIDE XML 2.1](http://www.ebi.ac.uk/pride/schemaDocumentation.do)
  * [mzIdentML 1.1.0](http://www.psidev.info/sites/default/files/mzIdentML1.1.0.xsd)

In addition, it is important to highlight that jmzTab is already integrated and used in external software such as the [LipidDataAnalyzer](http://genome.tugraz.at/lda/), and in an mzQuantML to mzTab converter included in the [mzq-lib](https://mzq-lib.googlecode.com/) library.

The jmzTab library is divided in different modules:
  * jmztab-modular-model: Data Model for mzTab [jmztab-modular-model.zip (latest version)](http://www.ebi.ac.uk/pride/resources/tools/jmztab/latest/jmztab-modular-model.zip)
  * jmztab-modular-util: Parsing capabilities and error handling [jmztab-modular-util.zip (latest version)](http://www.ebi.ac.uk/pride/resources/tools/jmztab/latest/jmztab-modular-util.zip)
  * jmztab-modular-converters: jmzTab utilities to convert PRIDE XML and mzIdentML files to mzTab [jmztab-modular-converters.zip (latest version)](http://www.ebi.ac.uk/pride/resources/tools/jmztab/latest/jmztab-modular-converters.zip)

When you use jmzTab library, please cite the following publication:

  * **[Qing-Wei Xu et al., Proteomics 2014; Jun;14(11):1328-32](http://onlinelibrary.wiley.com/doi/10.1002/pmic.201300560/abstract). [PDF File](http://onlinelibrary.wiley.com/doi/10.1002/pmic.201300560/pdf).  [PubMed record](http://www.ncbi.nlm.nih.gov/pubmed/24659499).**

## Tools

As examples of implementation, two [Utilities](https://github.com/PRIDE-Utilities/jmzTab/wiki/jmzTab-Utilities) were developed:

  * mzTabGUI, which a simple desktop application which provides mzTab validation functionality (for Warn or Error levels), and conversion of PRIDE XML and mzIdentML files to mzTab. After the conversion, the tool will also perform an automatic validation of the mzTab file.
  * mzTabCLI, a command line interface (CLI), which provides a more flexible way of processing mzTab files in a batch mode. It also has validation and conversion functionality.

Currently, both tools can be downloaded from:

  * [jmztab-modular-gui.zip (latest version)](http://www.ebi.ac.uk/pride/resources/tools/jmztab/latest/jmztab-modular-gui.zip)
  * [jmztab-modular-cli.zip (latest version)](http://www.ebi.ac.uk/pride/resources/tools/jmztab/latest/jmztab-modular-cli.zip)

In [mzTabCLI Demo](https://github.com/PRIDE-Utilities/jmzTab/blob/master/etcs/command_demo.bat), we provide a couple of examples to help user call command line interface quickly.

  * print command line help
```
java -jar mzTabCLI.jar -help
```
The output will like following:
```
usage: jmztab
 -check inFile=<inFile>             Choose a file from input directory. This parameter should not be null!
 -convert inFile=<inFile format=<format>   Converts the given format file (PRIDE or mzIdentML) to an mztab file.
 -h,--help                   print help message
 -message code=<code>             print Error/Warn detail message based on code number.
 -outFile <arg>              Dump output data to the given file. If not set, output data will be dumped on stdout.
```

More information about the tools can be found in the [Wiki](https://github.com/PRIDE-Utilities/jmzTab/wiki/jmzTab-Utilities)

## Using the jmzTab library

All components of the jmzTab library were developed as maven projects. For information of how to using jmzTab API, please visit the [Wiki](https://github.com/PRIDE-Utilities/jmzTab/wiki) documents.

## Maven

The jmzTab library can easily be used **in Maven projects**. You can include the following snippets in your Maven pom file.

For parsing and writing mzTab

```
<properties>
    <jmztab.version>3.0.4</jmztab.version>
</properties>

<dependency>
    <groupId>uk.ac.ebi.pride</groupId>
    <artifactId>jmztab-modular-model</artifactId>
    <version>${jmztab.version}</version>
    <!-- based on mzTab specification version 1.0 -->
</dependency>
<dependency>
    <groupId>uk.ac.ebi.pride</groupId>
    <artifactId>jmztab-modular-util</artifactId>
    <version>${jmztab.version}</version>
    <!-- based on mzTab specification version 1.0 -->
</dependency>
```

For converting to mzTab

```
<properties>
    <jmztab.version>3.0.4</jmztab.version>
</properties>

<dependency>
    <groupId>uk.ac.ebi.pride</groupId>
    <artifactId>jmztab-modular-converters</artifactId>
    <version>${jmztab.version}</version>
    <!-- based on mzTab specification version 1.0 -->
</dependency>
```

The jmzTab library can currently only be found in the [EBI](http://www.ebi.ac.uk)'s **maven repository**:

```
<repository>
    <id>nexus-ebi-repo</id>
    <name>EBI Nexus Repository (Release)</name>
    <url>http://www.ebi.ac.uk/Tools/maven/repos/content/repositories/pst-release/</url>
    <releases/>
    <snapshots>
        <enabled>false</enabled>
    </snapshots>
</repository>
```


## News
**jmzTab version 3.0.4 - (18. October 2016)**
  * general refinements and bug fixing

**jmzTab version 3.0.2 - (12. Febreruary 2015)**
  * general refinements and bug fixing
  * removes from the mzTabCLI the need to separate the input and output directory from the file
  * allow parsing the file with the columns in different order to the one define in the specification.Due to this new feature the defaults columns need to be added manually to the factory. Examples:

``` java
MZTabColumnFactory prh = MZTabColumnFactory.getInstance(Section.Protein_Header);
prh.addDefaultStableColumns();
// add other optional columns
prh.addBestSearchEngineScoreOptionalColumn(ProteinColumn.BEST_SEARCH_ENGINE_SCORE, 1);

MZTabColumnFactor peh = MZTabColumnFactory.getInstance(Section.Peptide_Header);
peh.addDefaultStableColumns();
// add other optional columns
peh.addBestSearchEngineScoreOptionalColumn(ProteinColumn.BEST_SEARCH_ENGINE_SCORE, 1);

MZTabColumnFactory psh = MZTabColumnFactory.getInstance(Section.PSM_Header);
psh.addDefaultStableColumns();
// add other optional columns
psh.addSearchEngineScoreOptionalColumn(PSMColumn.SEARCH_ENGINE_SCORE, 1, null);

MZTabColumnFactory smh = MZTabColumnFactory.getInstance(Section.PSM_Header);
smh.addDefaultStableColumns();
// add other optional columns
smh.addSearchEngineScoreOptionalColumn(PSMColumn.SEARCH_ENGINE_SCORE, 1, null);
```

**jmzTab version 3.0.0 - (1. August 2014)**
  * update version of the library to the Specification Document 1.0.0 (June 2014)
  * new modular version of the jmzTab library. This version allows a easier integration in other projects because minimises the dependencies per module.
  * first version of mzIdentML converter
  * general refinements and bug fixing
  * update mzTab examples

**mzTab Specification Document 1.0 RC 5 - (11. December 2013)**
  * A completely updated version of the mzTab format specification
  * Version currently submitted to the PSI document process

For more information please see the [ReleaseNote](https://github.com/PRIDE-Utilities/jmzTab/wiki/jmztab-ReleaseNotes).


---
