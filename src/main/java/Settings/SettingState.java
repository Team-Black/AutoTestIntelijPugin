package Settings;

public class SettingState {
    /*
    java -jar all-in-one-jar-1.0-SNAPSHOT.jar
    -jbse_home C:/Users/I535031/Desktop/Study/Huawei-Project/JavaAutoTest/
    -z3_path C:/Users/I535031/Desktop/Study/Huawei-Project/z3/build/Release/z3.exe
    -test_home C:/Users/I535031/Desktop/Study/Huawei-Project/jbse-examples/
    -mc smalldemos/array_3/ArrayDemo3 -
     */

    public String jbseHome = "C:/Users/PC/Documents/1.Projects/TRPO/JavaAutoTest-master/";
    public String z3Path  = "C:/Users/PC/Documents/1.Projects/0.mylibs/z3-4.8.7-x64-win/bin/z3.exe";
    public String testHome = "C:/Users/PC/Documents/1.Projects/TRPO/examplesJBSE/";
    public String methodClass = "smalldemos/array_3/ArrayDemo3";
    public String methodDescriptor = "(II)V";
    public String methodName = "entryPoint";
    public String testName ="out/ArrayDemoTest1.java";

    public String inputPath = "/target/classes";
    public String outputPath = "/src/test/TestPlugin";
}
