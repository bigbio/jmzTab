## jmzTab: Java library for reading and writing mzTab

The jmzTab library provide reading and writing capabilities, as well as supporting the validation of mzTab and the conversion of PRIDE XML and mzIdentML files to mzTab. Currently, the library contains converter for

  * [PRIDE XML 2.1](http://www.ebi.ac.uk/pride/schemaDocumentation.do)
  * [mzIdentML 1.1.0](http://www.psidev.info/sites/default/files/mzIdentML1.1.0.xsd)

The jmzTab library is divided in two modules:
  * jmztab-modular-model: Data Model for mzTab [jmztab-modular-model.zip (latest version)](http://www.ebi.ac.uk/pride/resources/tools/jmztab/latest/jmztab-modular-model.zip)

  * jmztab-modular-converters: jmzTab utilities to convert PRIDE XML and mzIdentML files to mzTab [jmztab-modular-converters.zip (latest version)](http://www.ebi.ac.uk/pride/resources/tools/jmztab/latest/jmztab-modular-converters.zip)

## Tools

As examples of implementation, two [Utilities](https://github.com/PRIDE-Utilities/jmzTab/wiki/jmzTab-Utilities) were developed:

  * mzTabCLI, a command line interface (CLI), which provides a more flexible way of processing mzTab files in a batch mode. It also has validation and conversion functionality.

Currently, both tools can be downloaded from:

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

All components of the jmzTab library were developed as maven projects. For information of how to using jmzTab API, please visit the [Wiki](https://github.com/bigbio/jmzTab/wiki) documents.

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

**mzTab Specification Document 1.0 RC 5 - (11. December 2013)**
  * A completely updated version of the mzTab format specification
  * Version currently submitted to the PSI document process

For more information please see the [ReleaseNote](https://github.com/PRIDE-Utilities/jmzTab/wiki/jmztab-ReleaseNotes).


---
