package JBSElogic;

import Settings.SettingState;
import Settings.SettingsPlugin;
import com.intellij.openapi.components.PersistentStateComponent;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters;

import java.io.IOException;
import java.util.Objects;

public class JBSEConnection {
    private int formatMode;

    private String JBSE_CLASSPATH;
    private String JBSE_SOURCEPATH;
    private String TARGET_CLASSPATH;
    private String TARGET_SOURCEPATH;
    private String[] CLASSPATH;
    private String[] SOURCEPATH;
    private String OUT_FILE;

    public JBSEConnection() {
    }

    PersistentStateComponent<SettingState> state = new SettingsPlugin().getInstance();
    private String JBSE_HOME = Objects.requireNonNull(state.getState()).jbseHome;
    private String Z3_PATH=Objects.requireNonNull(state.getState()).z3Path;
    private String TEST_HOME=Objects.requireNonNull(state.getState()).testHome;
    private String METHOD_CLASS=Objects.requireNonNull(state.getState()).methodClass;
    private String METHOD_DESCRIPTOR=Objects.requireNonNull(state.getState()).methodDesc;
    private String METHOD_NAME=Objects.requireNonNull(state.getState()).methodName;
    private String TEST_NAME=Objects.requireNonNull(state.getState()).testName;
    private String JAR_PATH = Objects.requireNonNull(state.getState().jarPath);
    private String JRE_SOURCEPATH = Objects.requireNonNull(state.getState().jrePath);

    public void start(){
        JBSE_CLASSPATH    = JBSE_HOME + "build/classes/java/main";
        JBSE_SOURCEPATH   = JBSE_HOME + "src/main/java/";
        TARGET_CLASSPATH  = TEST_HOME + "bin/";
        TARGET_SOURCEPATH = TEST_HOME + "src/";
        CLASSPATH       = new String[]{TARGET_CLASSPATH};
        SOURCEPATH      = new String[]{JBSE_SOURCEPATH, TARGET_SOURCEPATH, JRE_SOURCEPATH};
        OUT_FILE        = TEST_HOME + TEST_NAME;

        final RunParameters p = new RunParameters();
        set(p);
        final Run r = new Run(p);
        r.run();
    }

    public void start2() throws IOException{
        String jarParam = JAR_PATH;
        String jbseHomeParam = " -jbse_home " + JBSE_HOME;
        String z3Param = " -z3_path " + Z3_PATH;
        String testHomeParam = " -test_home " + TEST_HOME;
        String classParam = " -mc " + METHOD_CLASS;
        String descriptParam = " -md " + METHOD_DESCRIPTOR;
        String methodNameParam = " -mn " + METHOD_NAME;
        String nameOfTestParam = " -tn " + TEST_NAME;
        String jreParam = " -jre " + JRE_SOURCEPATH;
        Runtime re = Runtime.getRuntime();

        try{
            re.exec("java -jar " + jarParam + jbseHomeParam + z3Param + testHomeParam+ classParam + descriptParam + methodNameParam+ nameOfTestParam + jreParam);
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    private void set(RunParameters p) {
        p.setJBSELibPath(JBSE_CLASSPATH);
        p.addUserClasspath(CLASSPATH);
        p.addSourcePath(SOURCEPATH);
        p.setMethodSignature(METHOD_CLASS, METHOD_DESCRIPTOR, METHOD_NAME);
        p.setOutputFileName(OUT_FILE);
        p.setDecisionProcedureType(RunParameters.DecisionProcedureType.Z3);
        p.setExternalDecisionProcedurePath(Z3_PATH);
        switch (formatMode){
            case 1:
                p.setStateFormatMode(RunParameters.StateFormatMode.FULLTEXT);
                break;
            case 2:
                p.setStateFormatMode(RunParameters.StateFormatMode.TEXT);
                break;
            case 3:
                p.setStateFormatMode(RunParameters.StateFormatMode.GRAPHVIZ);
                break;
            case 4:
                p.setStateFormatMode(RunParameters.StateFormatMode.PATH);
                break;
            case 5:
                p.setStateFormatMode(RunParameters.StateFormatMode.JUNIT_TEST);
                break;
            default:
                break;
        }
        p.setStepShowMode(RunParameters.StepShowMode.LEAVES);
    }

}
