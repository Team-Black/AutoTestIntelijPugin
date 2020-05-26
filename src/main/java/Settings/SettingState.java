package Settings;

public class SettingState {
    /*
    java -jar all-in-one-jar-1.0-SNAPSHOT.jar
    -jbse_home C:/Users/I535031/Desktop/Study/Huawei-Project/JavaAutoTest/
    -z3_path C:/Users/I535031/Desktop/Study/Huawei-Project/z3/build/Release/z3.exe
    -test_home C:/Users/I535031/Desktop/Study/Huawei-Project/jbse-examples/
    -mc smalldemos/array_3/ArrayDemo3 -
    md (II)V -mn entryPoint
    -tn out/ArrayDemoTest1.java
    -jre C:/Program_Files/AdoptOpenJDK/jdk-8.0.242.08-hotspot/
    -format 5

     */
    public String JBSE_HOME;
    public String Z3_PATH;
    public String TEST_HOME;
    public String METHOD_CLASS;
    public String METHOD_DESCRIPTOR;
    public String METHOD_NAME;
    public String TEST_NAME;
    public String JRE_SOURCEPATH;

    public String inputPath = "/target/classes";
    public String outputPath = "/src/test/TestPlugin";
}
