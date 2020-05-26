//package JBSElogic;
//
//import jbse.apps.run.Run;
//import jbse.apps.run.RunParameters;
//
//public class JBSEConnection {
///*
//java -jar all-in-one-jar-1.0-SNAPSHOT.jar
//-jbse_home C:/Users/I535031/Desktop/Study/Huawei-Project/JavaAutoTest/
//-z3_path C:/Users/I535031/Desktop/Study/Huawei-Project/z3/build/Release/z3.exe
//-test_home C:/Users/I535031/Desktop/Study/Huawei-Project/jbse-examples/
//-mc smalldemos/array_3/ArrayDemo3 -
//md (II)V -mn entryPoint
//-tn out/ArrayDemoTest1.java
//-jre C:/Program_Files/AdoptOpenJDK/jdk-8.0.242.08-hotspot/
//-format 5
//
////TODO: 1. Окошко плагина - с названием AutoTestGen
////TODO: 2. Сделать 2 пункта - settings и запуск генерации
////TODO: 3. Сделать доступным ввод всех настроек для проекта внутри settings
////TODO: 4. Сделать заполнение как можно большего числа параметров автоматически
// */
//    private String JBSE_HOME;
//    private String Z3_PATH;
//    private String TEST_HOME;
//    private String METHOD_CLASS;
//    private String METHOD_DESCRIPTOR;
//    private String METHOD_NAME;
//    private String TEST_NAME;
//    private String JRE_SOURCEPATH = System.getProperty("java.home", "") + "src.zip";
//
//    private int formatMode;
//
//    private String JBSE_CLASSPATH;
//    private String JBSE_SOURCEPATH;
//    private String TARGET_CLASSPATH;
//    private String TARGET_SOURCEPATH;
//    private String[] CLASSPATH;
//    private String[] SOURCEPATH;
//    private String OUT_FILE;
//
//    private void start(){
//        JBSE_CLASSPATH    = JBSE_HOME + "build/classes/java/main";
//        JBSE_SOURCEPATH   = JBSE_HOME + "src/main/java/";
//        TARGET_CLASSPATH  = TEST_HOME + "bin/";
//        TARGET_SOURCEPATH = TEST_HOME + "src/";
//        CLASSPATH       = new String[]{TARGET_CLASSPATH};
//        SOURCEPATH      = new String[]{JBSE_SOURCEPATH, TARGET_SOURCEPATH, JRE_SOURCEPATH};
//        OUT_FILE        = TEST_HOME + TEST_NAME;
//
//        final RunParameters p = new RunParameters();
//        set(p);
//        final Run r = new Run(p);
//        r.run();
//    }
//
//    private void set(RunParameters p) {
//        p.setJBSELibPath(JBSE_CLASSPATH);
//        p.addUserClasspath(CLASSPATH);
//        p.addSourcePath(SOURCEPATH);
//        p.setMethodSignature(METHOD_CLASS, METHOD_DESCRIPTOR, METHOD_NAME);
//        p.setOutputFileName(OUT_FILE);
//        p.setDecisionProcedureType(RunParameters.DecisionProcedureType.Z3);
//        p.setExternalDecisionProcedurePath(Z3_PATH);
//        switch (formatMode){
//            case 1:
//                p.setStateFormatMode(RunParameters.StateFormatMode.FULLTEXT);
//                break;
//            case 2:
//                p.setStateFormatMode(RunParameters.StateFormatMode.TEXT);
//                break;
//            case 3:
//                p.setStateFormatMode(RunParameters.StateFormatMode.GRAPHVIZ);
//                break;
//            case 4:
//                p.setStateFormatMode(RunParameters.StateFormatMode.PATH);
//                break;
//            case 5:
//                p.setStateFormatMode(RunParameters.StateFormatMode.JUNIT_TEST);
//                break;
//            default:
//                break;
//        }
//        p.setStepShowMode(RunParameters.StepShowMode.LEAVES);
//    }
//
//}
