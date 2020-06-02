package JBSElogic;

import Settings.SettingState;
import Settings.SettingsPlugin;
import com.intellij.openapi.components.PersistentStateComponent;

import java.io.IOException;
import java.util.Objects;

public class JBSEConnection {

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

    public void start2(){
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
}
