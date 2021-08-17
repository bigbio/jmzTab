# jmzTab: Java library for reading and writing mzTab

The jmzTab library provide reading and writing capabilities, as well as supporting the validation of mzTab and the conversion of mzIdentML files to mzTab. Currently, the library contains converter for

  * [mzIdentML 1.1.0](http://www.psidev.info/sites/default/files/mzIdentML1.1.0.xsd)

## jmzTabValidator

  * jmzTabValidator, a command line interface (CLI), which provides a more flexible way of processing mzTab files in a batch mode. It also has validation and conversion functionality.

Currently, the tool can be downloaded from the releases section:

  * [jzmTabValidator Release](https://github.com/bigbio/jmzTab/releases/tag/v3.0.10)

```
java -jar jmztab-{version}.jar -help
```
The output will like following:
```
usage: jmztab
 -check inFile=<inFile>             Choose a file from input directory. This parameter should not be null!
 -convert inFile=<inFile format=<format>   Converts the given format file (mzIdentML) to an mztab file.
 -h,--help                   print help message
 -message code=<code>             print Error/Warn detail message based on code number.
 -outFile <arg>              Dump output data to the given file. If not set, output data will be dumped on stdout.
```

## Maven

The jmzTab library can easily be used **in Maven projects**. You can include the following snippets in your Maven pom file.

For parsing and writing mzTab

```
<properties>
    <jmztab.version>3.0.4</jmztab.version>
</properties>

<dependency>
    <groupId>io.github.bigbio.external</groupId>
    <artifactId>jmztab</artifactId>
    <version>${jmztab.version}</version>
    <!-- based on mzTab specification version 1.0 -->
</dependency>
```

The jmzTab library can currently only be found in the [maven central]'s **maven repository**:

```
 <repository>
      <id>sonatype-release</id>
      <url>https://oss.sonatype.org/service/local/staging/deploy/maven2</url>
    </repository>
    <repository>
      <id>sonatype-snapshopt</id>
      <url>https://oss.sonatype.org/content/repositories/snapshots</url>
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
