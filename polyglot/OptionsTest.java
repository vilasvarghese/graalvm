import org.graalvm.polyglot.*;

class OptionsTest {

    public static void main(String[] args) {
        Context polyglot = Context.newBuilder()
            .allowExperimentalOptions(true)
            .option("js.shared-array-buffer", "true")
            .build();
        // the use of shared array buffer requires the 'js.shared-array-buffer' option to be 'true'
        polyglot.eval("js", "new SharedArrayBuffer(1024)");
    }
}