package JBSElogic;

import Settings.SettingState;
import Settings.SettingsPlugin;
import com.intellij.openapi.components.PersistentStateComponent;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters;

import java.util.Objects;

public class JBSEConnection {
/*
//TODO: 3. Сделать доступным ввод всех настроек для проекта внутри settings
//TODO: 4. Сделать заполнение как можно большего числа параметров автоматически

 */

    PersistentStateComponent<SettingState> state = new SettingsPlugin().getInstance();
    private String JBSE_HOME = Objects.requireNonNull(state.getState()).jbseHome;
    private String Z3_PATH=Objects.requireNonNull(state.getState()).z3Path;
    private String TEST_HOME=Objects.requireNonNull(state.getState()).testHome;
    private String METHOD_CLASS=Objects.requireNonNull(state.getState()).methodClass;
    private String METHOD_DESCRIPTOR=Objects.requireNonNull(state.getState()).methodDescriptor;
    private String METHOD_NAME=Objects.requireNonNull(state.getState()).methodName;
    private String TEST_NAME=Objects.requireNonNull(state.getState()).testName;

    private String JRE_SOURCEPATH = System.getProperty("java.home", "") + "src.zip";

    private int formatMode;

    private String JBSE_CLASSPATH;
    private String JBSE_SOURCEPATH;
    private String TARGET_CLASSPATH;
    private String TARGET_SOURCEPATH;
    private String[] CLASSPATH;
    private String[] SOURCEPATH;
    private String OUT_FILE;

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
